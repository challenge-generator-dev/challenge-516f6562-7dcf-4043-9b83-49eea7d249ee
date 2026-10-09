package com.pragma.payments.application;


import com.pragma.payments.domain.model.PaymentStatus;
import com.pragma.payments.domain.exception.ExternalServiceException;
import com.pragma.payments.domain.exception.IdempotencyKeyViolationException;
import com.pragma.payments.domain.exception.PaymentProcessingException;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.ports.ExternalPaymentService;
import com.pragma.payments.domain.ports.PaymentRepository;
import io.smallrye.faulttolerance.api.BulkheadDone;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.faulttolerance.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ExternalPaymentService externalPaymentService;

    @Inject
    public PaymentService(PaymentRepository paymentRepository,
                          ExternalPaymentService externalPaymentService) {
        this.paymentRepository = paymentRepository;
        this.externalPaymentService = externalPaymentService;
    }

    @Timeout(30000)
    @Retry(maxRetries = 3, delay = 1000, jitter = 200)
    @CircuitBreaker(requestVolumeThreshold = 5, failureRatio = 0.5, delay = 10000)
    @Bulkhead(value = 10)
    public Payment processPayment(String idempotencyKey, BigDecimal amount) {
        validateInputs(idempotencyKey, amount);
        checkIdempotency(idempotencyKey);

        Payment payment = createPayment(idempotencyKey, amount);

        try {
            String externalReference = externalPaymentService.processPayment(idempotencyKey, amount);
            Payment completedPayment = payment.withExternalReference(externalReference)
                                               .withStatus(Payment.PaymentStatus.COMPLETED);
            return paymentRepository.save(completedPayment);
        } catch (ExternalServiceException e) {
            Payment failedPayment = payment.withStatus(Payment.PaymentStatus.FAILED);
            paymentRepository.save(failedPayment);
            throw new PaymentProcessingException("Error al procesar el pago en el servicio externo", e);
        }
    }

    private void validateInputs(String idempotencyKey, BigDecimal amount) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            throw new IllegalArgumentException("La clave de idempotencia no puede estar vacía");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero");
        }
    }

    private void checkIdempotency(String idempotencyKey) {
        Optional<Payment> existingPayment = paymentRepository.findByIdempotencyKey(idempotencyKey);
        if (existingPayment.isPresent()) {
            Payment existing = existingPayment.get();
            throw new IdempotencyKeyViolationException(idempotencyKey, existing.id());
        }
    }

    private Payment createPayment(String idempotencyKey, BigDecimal amount) {
        String paymentId = UUID.randomUUID().toString();
        Payment payment = new Payment(
            paymentId,
            idempotencyKey,
            amount,
            Payment.PaymentStatus.PENDING,
            Instant.now(),
            null
        );
        return paymentRepository.save(payment);
    }

    public Optional<Payment> findPaymentById(String id) {
        if (id == null || id.isBlank()) {
            return Optional.empty();
        }
        return paymentRepository.findById(id);
    }

    public Optional<Payment> findPaymentByIdempotencyKey(String idempotencyKey) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            return Optional.empty();
        }
        return paymentRepository.findByIdempotencyKey(idempotencyKey);
    }

    public Payment retryPayment(String paymentId) {
        Optional<Payment> existingPayment = paymentRepository.findById(paymentId);
        if (existingPayment.isEmpty()) {
            throw new PaymentProcessingException("Pago no encontrado: " + paymentId);
        }

        Payment payment = existingPayment.get();
        if (payment.status() == Payment.PaymentStatus.COMPLETED) {
            throw new PaymentProcessingException("El pago ya fue completado: " + paymentId);
        }

        String newIdempotencyKey = payment.idempotencyKey() + "_retry_" + System.currentTimeMillis();
        return processPayment(newIdempotencyKey, payment.amount());
    }
}
package com.pragma.payments.application;

import com.pragma.payments.domain.exception.ExternalServiceException;
import com.pragma.payments.domain.exception.PaymentProcessingException;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.Payment.PaymentStatus;
import com.pragma.payments.domain.ports.ExternalPaymentService;
import com.pragma.payments.domain.ports.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class PaymentServiceTest {

    private PaymentRepository paymentRepository;
    private ExternalPaymentService externalPaymentService;
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        paymentRepository = mock(PaymentRepository.class);
        externalPaymentService = mock(ExternalPaymentService.class);
        paymentService = new PaymentService(paymentRepository, externalPaymentService);
    }

    @Test
    void processPayment_Success_ReturnsPaymentWithApprovedStatus() throws Exception {
        String idempotencyKey = "test-key-123";
        BigDecimal amount = new BigDecimal("100.00");
        String currency = "USD";
        String description = "Test payment";
        String externalRef = "ext-ref-456";

        when(paymentRepository.findByIdempotencyKey(idempotencyKey)).thenReturn(Optional.empty());
        when(externalPaymentService.processPayment(idempotencyKey, amount)).thenReturn(externalRef);

        Payment savedPayment = new Payment(
            "payment-id-789",
            amount,
            currency,
            description,
            PaymentStatus.APPROVED,
            externalRef,
            idempotencyKey,
            Instant.now()
        );
        when(paymentRepository.save(any(Payment.class))).thenReturn(savedPayment);

        Payment result = paymentService.processPayment(idempotencyKey, amount, currency, description);

        assertNotNull(result);
        assertEquals(PaymentStatus.APPROVED, result.status());
        assertEquals(externalRef, result.externalReference());
        verify(paymentRepository).save(any(Payment.class));
    }

    @Test
    void processPayment_IdempotencyKeyExists_ReturnsExistingPayment() throws Exception {
        String idempotencyKey = "existing-key";
        BigDecimal amount = new BigDecimal("50.00");

        Payment existingPayment = new Payment(
            "existing-id",
            amount,
            "EUR",
            "Original payment",
            PaymentStatus.APPROVED,
            "ext-ref",
            idempotencyKey,
            Instant.now()
        );
        when(paymentRepository.findByIdempotencyKey(idempotencyKey))
            .thenReturn(Optional.of(existingPayment));

        Payment result = paymentService.processPayment(idempotencyKey, amount, "EUR", "New description");

        assertEquals(existingPayment.id(), result.id());
        verify(externalPaymentService, never()).processPayment(any(), any());
        verify(paymentRepository, never()).save(any());
    }

    @Test
    void processPayment_ExternalServiceFails_ThrowsPaymentProcessingException() {
        String idempotencyKey = "fail-key";
        BigDecimal amount = new BigDecimal("200.00");

        when(paymentRepository.findByIdempotencyKey(idempotencyKey)).thenReturn(Optional.empty());
        when(externalPaymentService.processPayment(idempotencyKey, amount))
            .thenThrow(new ExternalServiceException("Servicio externo falló"));

        assertThrows(PaymentProcessingException.class, () -> {
            paymentService.processPayment(idempotencyKey, amount, "USD", "Test");
        });

        ArgumentCaptor<Payment> paymentCaptor = ArgumentCaptor.forClass(Payment.class);
        verify(paymentRepository).save(paymentCaptor.capture());
        assertEquals(PaymentStatus.FAILED, paymentCaptor.getValue().status());
    }

    @Test
    void processPayment_ExternalServiceThrowsAfterRetries_ThrowsExternalServiceException() {
        String idempotencyKey = "retry-fail-key";
        BigDecimal amount = new BigDecimal("300.00");

        when(paymentRepository.findByIdempotencyKey(idempotencyKey)).thenReturn(Optional.empty());
        when(externalPaymentService.processPayment(eq(idempotencyKey), eq(amount)))
            .thenThrow(new ExternalServiceException("Error temporal"));

        ExternalServiceException exception = assertThrows(ExternalServiceException.class, () -> {
            paymentService.processPayment(idempotencyKey, amount, "USD", "Test");
        });

        assertTrue(exception.getMessage().contains("Error temporal"));
    }

    @Test
    void findById_ExistingId_ReturnsPayment() {
        String paymentId = "find-me";
        Payment expectedPayment = new Payment(
            paymentId,
            new BigDecimal("150.00"),
            "GBP",
            "Found payment",
            PaymentStatus.APPROVED,
            "ext-123",
            "idem-123",
            Instant.now()
        );
        when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(expectedPayment));

        Optional<Payment> result = paymentService.findById(paymentId);

        assertTrue(result.isPresent());
        assertEquals(paymentId, result.get().id());
    }

    @Test
    void findById_NonExistingId_ReturnsEmpty() {
        String paymentId = "not-found";
        when(paymentRepository.findById(paymentId)).thenReturn(Optional.empty());

        Optional<Payment> result = paymentService.findById(paymentId);

        assertTrue(result.isEmpty());
    }

    @Test
    void processPayment_ValidatesAmount_MustBePositive() {
        String idempotencyKey = "zero-amount";
        BigDecimal zeroAmount = BigDecimal.ZERO;

        when(paymentRepository.findByIdempotencyKey(idempotencyKey)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.processPayment(idempotencyKey, zeroAmount, "USD", "Test");
        });
    }

    @Test
    void processPayment_ValidatesCurrency_CannotBeBlank() {
        String idempotencyKey = "blank-currency";
        BigDecimal amount = new BigDecimal("100.00");

        when(paymentRepository.findByIdempotencyKey(idempotencyKey)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.processPayment(idempotencyKey, amount, "", "Test");
        });
    }
}
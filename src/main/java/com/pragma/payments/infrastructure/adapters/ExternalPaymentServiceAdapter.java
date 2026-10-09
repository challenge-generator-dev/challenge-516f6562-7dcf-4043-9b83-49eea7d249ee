package com.pragma.payments.infrastructure.adapters;

import com.pragma.payments.domain.exception.ExternalServiceException;
import com.pragma.payments.domain.ports.ExternalPaymentService;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.Random;

@ApplicationScoped
public class ExternalPaymentServiceAdapter implements ExternalPaymentService {

    private static final Logger LOG = Logger.getLogger(ExternalPaymentServiceAdapter.class);
    private static final double FAILURE_RATE = 0.3;
    private final Random random = new Random();

    @Override
    public String processPayment(String idempotencyKey, BigDecimal amount) {
        LOG.infof("Procesando pago con clave de idempotencia: %s, monto: %s", idempotencyKey, amount);

        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            throw new ExternalServiceException("La clave de idempotencia no puede ser nula o vacía");
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ExternalServiceException("El monto del pago debe ser mayor a cero");
        }

        simulateNetworkDelay();

        if (shouldFail()) {
            LOG.warn("El servicio externo de pagos ha fallado simuladamente");
            throw new ExternalServiceException("Error de comunicación con el servicio externo de pagos");
        }

        String externalReference = generateExternalReference();
        LOG.infof("Pago procesado exitosamente. Referencia externa: %s", externalReference);
        return externalReference;
    }

    private void simulateNetworkDelay() {
        try {
            Thread.sleep(random.nextInt(200) + 50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOG.warn("Hilo interrumpido durante la simulación de delay de red");
        }
    }

    private boolean shouldFail() {
        return random.nextDouble() < FAILURE_RATE;
    }

    private String generateExternalReference() {
        return "PAY-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
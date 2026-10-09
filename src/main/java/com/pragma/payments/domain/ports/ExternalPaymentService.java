package com.pragma.payments.domain.ports;

import java.math.BigDecimal;

public interface ExternalPaymentService {
    String processPayment(String idempotencyKey, BigDecimal amount);
}
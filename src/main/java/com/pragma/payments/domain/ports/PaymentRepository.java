package com.pragma.payments.domain.ports;

import com.pragma.payments.domain.model.Payment;
import java.util.Optional;

public interface PaymentRepository {
    Payment save(Payment payment);
    
    Optional<Payment> findById(String id);
    
    Optional<Payment> findByIdempotencyKey(String idempotencyKey);
}
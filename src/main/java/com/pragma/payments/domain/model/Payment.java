package com.pragma.payments.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public record Payment(
    String id,
    BigDecimal amount,
    PaymentStatus status,
    String idempotencyKey,
    String externalReference,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public enum PaymentStatus {
        PENDING,
        PROCESSING,
        COMPLETED,
        FAILED
    }

    public Payment {
        Objects.requireNonNull(amount, "Amount cannot be null");
        Objects.requireNonNull(status, "Status cannot be null");
        Objects.requireNonNull(idempotencyKey, "Idempotency key cannot be null");
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (updatedAt == null) {
            updatedAt = LocalDateTime.now();
        }
    }

    public Payment withStatus(PaymentStatus newStatus) {
        return new Payment(
            this.id,
            this.amount,
            newStatus,
            this.idempotencyKey,
            this.externalReference,
            this.createdAt,
            LocalDateTime.now()
        );
    }

    public Payment withExternalReference(String reference) {
        return new Payment(
            this.id,
            this.amount,
            this.status,
            this.idempotencyKey,
            reference,
            this.createdAt,
            LocalDateTime.now()
        );
    }
}
package com.pragma.payments.infrastructure.adapters;


import com.pragma.payments.domain.model.PaymentStatus;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.ports.PaymentRepository;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.Optional;

@MongoEntity(collection = "payments")
@ApplicationScoped
public class MongoPaymentRepository implements PaymentRepository, PanacheMongoRepository<Payment> {

    @Override
    public Payment save(Payment payment) {
        if (payment == null) {
            throw new IllegalArgumentException("El pago no puede ser nulo");
        }
        persist(payment);
        return payment;
    }

    @Override
    public Optional<Payment> findById(String id) {
        if (id == null || id.isBlank()) {
            return Optional.empty();
        }
        try {
            ObjectId objectId = new ObjectId(id);
            return findByIdOptional(objectId);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Payment> findByIdempotencyKey(String idempotencyKey) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            return Optional.empty();
        }
        return find("idempotencyKey", idempotencyKey).firstResultOptional();
    }

    public boolean existsByIdempotencyKey(String idempotencyKey) {
        return findByIdempotencyKey(idempotencyKey).isPresent();
    }

    public Payment updatePaymentStatus(String id, Payment.PaymentStatus newStatus) {
        Optional<Payment> paymentOpt = findById(id);
        if (paymentOpt.isEmpty()) {
            return null;
        }
        Payment payment = paymentOpt.get();
        Payment updatedPayment = payment.withStatus(newStatus);
        update(updatedPayment);
        return updatedPayment;
    }

    public long countByStatus(Payment.PaymentStatus status) {
        return count("status", status);
    }
}
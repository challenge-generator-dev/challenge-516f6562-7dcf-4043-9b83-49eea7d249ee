package com.pragma.payments.domain.exception;

public class IdempotencyKeyViolationException extends RuntimeException {
    private final String idempotencyKey;
    private final String paymentId;

    public IdempotencyKeyViolationException(String idempotencyKey, String paymentId) {
        super(buildMessage(idempotencyKey, paymentId));
        this.idempotencyKey = idempotencyKey;
        this.paymentId = paymentId;
    }

    public IdempotencyKeyViolationException(String idempotencyKey, String paymentId, Throwable cause) {
        super(buildMessage(idempotencyKey, paymentId), cause);
        this.idempotencyKey = idempotencyKey;
        this.paymentId = paymentId;
    }

    private static String buildMessage(String idempotencyKey, String paymentId) {
        return String.format("La clave de idempotencia '%s' ya fue utilizada para el pago '%s'. " +
                "No se puede procesar un pago duplicado con la misma clave de idempotencia.",
                idempotencyKey, paymentId);
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public boolean isDuplicateRequest() {
        return paymentId != null && !paymentId.isBlank();
    }

    public String getDetailedInfo() {
        return String.format("IdempotencyKeyViolationException{idempotencyKey='%s', paymentId='%s', message='%s'}",
                idempotencyKey, paymentId, getMessage());
    }
}
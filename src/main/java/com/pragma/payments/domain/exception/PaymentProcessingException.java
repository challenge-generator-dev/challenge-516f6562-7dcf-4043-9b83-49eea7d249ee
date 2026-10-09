package com.pragma.payments.domain.exception;

import java.util.HashMap;
import java.util.Map;

public class PaymentProcessingException extends RuntimeException {

    private final String paymentId;
    private final ErrorCode errorCode;
    private final Map<String, Object> context;

    public enum ErrorCode {
        VALIDATION_ERROR("P001", "Error de validación en los datos del pago"),
        DUPLICATE_PAYMENT("P002", "Intento de procesamiento de pago duplicado"),
        INSUFFICIENT_FUNDS("P003", "Fondos insuficientes para completar el pago"),
        ACCOUNT_NOT_FOUND("P004", "Cuenta no encontrada para el pago"),
        EXTERNAL_SERVICE_ERROR("P005", "Error en el servicio externo de procesamiento"),
        TIMEOUT_ERROR("P006", "Tiempo de espera agotado durante el procesamiento"),
        DATABASE_ERROR("P007", "Error de base de datos al procesar el pago"),
        INTERNAL_ERROR("P999", "Error interno inesperado en el procesamiento");

        private final String code;
        private final String description;

        ErrorCode(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }

    public PaymentProcessingException(String message) {
        super(message);
        this.paymentId = null;
        this.errorCode = ErrorCode.INTERNAL_ERROR;
        this.context = new HashMap<>();
    }

    public PaymentProcessingException(String message, Throwable cause) {
        super(message, cause);
        this.paymentId = null;
        this.errorCode = ErrorCode.INTERNAL_ERROR;
        this.context = new HashMap<>();
    }

    public PaymentProcessingException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.paymentId = null;
        this.context = new HashMap<>();
    }

    public PaymentProcessingException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.paymentId = null;
        this.context = new HashMap<>();
    }

    public PaymentProcessingException(String paymentId, ErrorCode errorCode, String message) {
        super(message);
        this.paymentId = paymentId;
        this.errorCode = errorCode;
        this.context = new HashMap<>();
    }

    public PaymentProcessingException(String paymentId, ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.paymentId = paymentId;
        this.errorCode = errorCode;
        this.context = new HashMap<>();
    }

    public PaymentProcessingException(String paymentId, ErrorCode errorCode, String message, 
                                       Map<String, Object> context) {
        super(message);
        this.paymentId = paymentId;
        this.errorCode = errorCode;
        this.context = context != null ? new HashMap<>(context) : new HashMap<>();
    }

    public PaymentProcessingException(String paymentId, ErrorCode errorCode, String message, 
                                       Throwable cause, Map<String, Object> context) {
        super(message, cause);
        this.paymentId = paymentId;
        this.errorCode = errorCode;
        this.context = context != null ? new HashMap<>(context) : new HashMap<>();
    }

    public String getPaymentId() {
        return paymentId;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public Map<String, Object> getContext() {
        return new HashMap<>(context);
    }

    public String getErrorCodeValue() {
        return errorCode != null ? errorCode.getCode() : "P999";
    }

    public String getErrorDescription() {
        return errorCode != null ? errorCode.getDescription() : "Error desconocido";
    }

    public PaymentProcessingException withContext(String key, Object value) {
        Map<String, Object> newContext = new HashMap<>(this.context);
        newContext.put(key, value);
        return new PaymentProcessingException(this.paymentId, this.errorCode, 
                                               this.getMessage(), this.getCause(), newContext);
    }

    public boolean isRetryable() {
        return this.errorCode == ErrorCode.EXTERNAL_SERVICE_ERROR ||
               this.errorCode == ErrorCode.TIMEOUT_ERROR ||
               this.errorCode == ErrorCode.DATABASE_ERROR;
    }

    @Override
    public String toString() {
        return String.format("PaymentProcessingException{paymentId='%s', errorCode=%s, message='%s'}",
                paymentId, errorCode, getMessage());
    }
}
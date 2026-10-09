package com.pragma.payments.domain.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ExternalServiceException extends RuntimeException {

    private final String serviceName;
    private final String externalReference;
    private final ServiceType serviceType;
    private final FailureType failureType;
    private final int httpStatusCode;
    private final Instant timestamp;
    private final Map<String, Object> metadata;
    private final int retryCount;

    public enum ServiceType {
        PAYMENT_GATEWAY("payment_gateway"),
        FRAUD_CHECK("fraud_check"),
        NOTIFICATION("notification"),
        ACCOUNT_VERIFICATION("account_verification"),
        RISK_ANALYSIS("risk_analysis");

        private final String value;

        ServiceType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum FailureType {
        TIMEOUT("timeout", true),
        CONNECTION_ERROR("connection_error", true),
        SERVER_ERROR("server_error", true),
        CLIENT_ERROR("client_error", false),
        AUTHENTICATION_ERROR("authentication_error", false),
        RATE_LIMIT_EXCEEDED("rate_limit_exceeded", true),
        SERVICE_UNAVAILABLE("service_unavailable", true),
        UNKNOWN("unknown", true);

        private final String code;
        private final boolean retryable;

        FailureType(String code, boolean retryable) {
            this.code = code;
            this.retryable = retryable;
        }

        public String getCode() {
            return code;
        }

        public boolean isRetryable() {
            return retryable;
        }
    }

    public ExternalServiceException(String serviceName, String message) {
        super(message);
        this.serviceName = serviceName;
        this.externalReference = UUID.randomUUID().toString();
        this.serviceType = inferServiceType(serviceName);
        this.failureType = FailureType.UNKNOWN;
        this.httpStatusCode = 500;
        this.timestamp = Instant.now();
        this.metadata = new HashMap<>();
        this.retryCount = 0;
    }

    public ExternalServiceException(String serviceName, String message, Throwable cause) {
        super(message, cause);
        this.serviceName = serviceName;
        this.externalReference = UUID.randomUUID().toString();
        this.serviceType = inferServiceType(serviceName);
        this.failureType = FailureType.UNKNOWN;
        this.httpStatusCode = 500;
        this.timestamp = Instant.now();
        this.metadata = new HashMap<>();
        this.retryCount = 0;
    }

    public ExternalServiceException(ServiceType serviceType, FailureType failureType, 
                                     String message, int httpStatusCode) {
        super(message);
        this.serviceName = serviceType.getValue();
        this.serviceType = serviceType;
        this.failureType = failureType;
        this.httpStatusCode = httpStatusCode;
        this.externalReference = UUID.randomUUID().toString();
        this.timestamp = Instant.now();
        this.metadata = new HashMap<>();
        this.retryCount = 0;
    }

    public ExternalServiceException(ServiceType serviceType, FailureType failureType, 
                                     String message, int httpStatusCode, Throwable cause) {
        super(message, cause);
        this.serviceName = serviceType.getValue();
        this.serviceType = serviceType;
        this.failureType = failureType;
        this.httpStatusCode = httpStatusCode;
        this.externalReference = UUID.randomUUID().toString();
        this.timestamp = Instant.now();
        this.metadata = new HashMap<>();
        this.retryCount = 0;
    }

    public ExternalServiceException(String serviceName, ServiceType serviceType, 
                                     FailureType failureType, String message, 
                                     int httpStatusCode, String externalReference,
                                     Map<String, Object> metadata, int retryCount) {
        super(message);
        this.serviceName = serviceName;
        this.serviceType = serviceType;
        this.failureType = failureType;
        this.httpStatusCode = httpStatusCode;
        this.externalReference = externalReference != null ? externalReference : UUID.randomUUID().toString();
        this.timestamp = Instant.now();
        this.metadata = metadata != null ? new HashMap<>(metadata) : new HashMap<>();
        this.retryCount = retryCount;
    }

    private ServiceType inferServiceType(String serviceName) {
        if (serviceName == null) {
            return ServiceType.PAYMENT_GATEWAY;
        }
        String lowerName = serviceName.toLowerCase();
        if (lowerName.contains("payment") || lowerName.contains("gateway")) {
            return ServiceType.PAYMENT_GATEWAY;
        } else if (lowerName.contains("fraud")) {
            return ServiceType.FRAUD_CHECK;
        } else if (lowerName.contains("notification")) {
            return ServiceType.NOTIFICATION;
        } else if (lowerName.contains("account")) {
            return ServiceType.ACCOUNT_VERIFICATION;
        } else if (lowerName.contains("risk")) {
            return ServiceType.RISK_ANALYSIS;
        }
        return ServiceType.PAYMENT_GATEWAY;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getExternalReference() {
        return externalReference;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public FailureType getFailureType() {
        return failureType;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Map<String, Object> getMetadata() {
        return new HashMap<>(metadata);
    }

    public int getRetryCount() {
        return retryCount;
    }

    public boolean isRetryable() {
        return failureType != null && failureType.isRetryable();
    }

    public ExternalServiceException withMetadata(String key, Object value) {
        Map<String, Object> newMetadata = new HashMap<>(this.metadata);
        newMetadata.put(key, value);
        return new ExternalServiceException(this.serviceName, this.serviceType, this.failureType,
                this.getMessage(), this.httpStatusCode, this.externalReference, 
                newMetadata, this.retryCount);
    }

    public ExternalServiceException withRetryCount(int retryCount) {
        return new ExternalServiceException(this.serviceName, this.serviceType, this.failureType,
                this.getMessage(), this.httpStatusCode, this.externalReference, 
                this.metadata, retryCount);
    }

    @Override
    public String toString() {
        return String.format("ExternalServiceException{serviceName='%s', serviceType=%s, " +
                        "failureType=%s, httpStatusCode=%d, externalReference='%s', retryable=%b}",
                serviceName, serviceType, failureType, httpStatusCode, externalReference, isRetryable());
    }
}
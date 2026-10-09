package com.pragma.payments.infrastructure.config;

import com.pragma.payments.domain.exception.ExternalServiceException;
import com.pragma.payments.domain.exception.IdempotencyKeyViolationException;
import com.pragma.payments.domain.exception.PaymentProcessingException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    private static final Logger LOG = Logger.getLogger(GlobalExceptionHandler.class);

    @Override
    public Response toResponse(Exception exception) {
        LOG.error("Excepción no controlada", exception);

        if (exception instanceof IdempotencyKeyViolationException) {
            return buildErrorResponse(
                Response.Status.CONFLICT,
                "CONFLICT",
                exception.getMessage()
            );
        }

        if (exception instanceof ExternalServiceException) {
            return buildErrorResponse(
                Response.Status.SERVICE_UNAVAILABLE,
                "SERVICE_UNAVAILABLE",
                exception.getMessage()
            );
        }

        if (exception instanceof PaymentProcessingException) {
            return buildErrorResponse(
                Response.Status.BAD_REQUEST,
                "PAYMENT_ERROR",
                exception.getMessage()
            );
        }

        if (exception instanceof IllegalArgumentException) {
            return buildErrorResponse(
                Response.Status.BAD_REQUEST,
                "INVALID_ARGUMENT",
                exception.getMessage()
            );
        }

        return buildErrorResponse(
            Response.Status.INTERNAL_SERVER_ERROR,
            "INTERNAL_ERROR",
            "Error interno del servidor"
        );
    }

    private Response buildErrorResponse(Response.Status status, String code, String message) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", Instant.now().toString());
        errorBody.put("status", status.getStatusCode());
        errorBody.put("error", code);
        errorBody.put("message", message);

        return Response
            .status(status)
            .type(MediaType.APPLICATION_JSON)
            .entity(errorBody)
            .build();
    }
}
package com.pragma.payments.infrastructure.adapters;




import com.pragma.payments.domain.exception.ExternalServiceException;
import com.pragma.payments.domain.exception.PaymentProcessingException;
import com.pragma.payments.domain.exception.IdempotencyKeyViolationException;
import com.pragma.payments.application.PaymentService;
import com.pragma.payments.domain.model.Payment;
import com.pragma.payments.domain.model.Payment.PaymentStatus;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.Map;

@Path("/api/payments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentResource {

    @Inject
    PaymentService paymentService;

    @POST
    public Response processPayment(PaymentRequest request) {
        if (request.amount() == null || request.amount().compareTo(BigDecimal.ZERO) <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "El monto debe ser mayor a cero"))
                    .build();
        }
        if (request.currency() == null || request.currency().isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "La moneda es obligatoria"))
                    .build();
        }

        String idempotencyKey = request.idempotencyKey();
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            idempotencyKey = java.util.UUID.randomUUID().toString();
        }

        try {
            Payment payment = paymentService.processPayment(
                idempotencyKey,
                request.amount(),
                request.currency(),
                request.description()
            );

            PaymentResponse response = new PaymentResponse(
                payment.id(),
                payment.amount(),
                payment.currency(),
                payment.status().name(),
                payment.externalReference(),
                payment.createdAt().toString()
            );

            return Response.status(Response.Status.CREATED).entity(response).build();
        } catch (com.pragma.payments.domain.exception.IdempotencyKeyViolationException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(Map.of("error", e.getMessage()))
                    .build();
        } catch (com.pragma.payments.domain.exception.PaymentProcessingException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("error", "Error al procesar el pago", "detail", e.getMessage()))
                    .build();
        } catch (com.pragma.payments.domain.exception.ExternalServiceException e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity(Map.of("error", "Servicio externo no disponible", "detail", e.getMessage()))
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getPayment(@PathParam("id") String id) {
        return paymentService.findById(id)
            .map(payment -> {
                PaymentResponse response = new PaymentResponse(
                    payment.id(),
                    payment.amount(),
                    payment.currency(),
                    payment.status().name(),
                    payment.externalReference(),
                    payment.createdAt().toString()
                );
                return Response.ok(response).build();
            })
            .orElse(Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Pago no encontrado"))
                    .build());
    }

    public record PaymentRequest(
        BigDecimal amount,
        String currency,
        String description,
        String idempotencyKey
    ) {}

    public record PaymentResponse(
        String id,
        BigDecimal amount,
        String currency,
        String status,
        String externalReference,
        String createdAt
    ) {}
}
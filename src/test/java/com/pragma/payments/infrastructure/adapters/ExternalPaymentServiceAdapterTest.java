package com.pragma.payments.infrastructure.adapters;

import com.pragma.payments.domain.exception.ExternalServiceException;
import com.pragma.payments.domain.ports.ExternalPaymentService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@QuarkusTest
class ExternalPaymentServiceAdapterTest {

    @Inject
    ExternalPaymentServiceAdapter adapter;

    @InjectMock
    ExternalPaymentService externalPaymentService;

    private AtomicInteger callCount;

    @BeforeEach
    void setUp() {
        callCount = new AtomicInteger(0);
        reset(externalPaymentService);
    }

    @Test
    void processPayment_FirstCallSuccess_ReturnsExternalReference() {
        String idempotencyKey = "success-key";
        BigDecimal amount = new BigDecimal("100.00");
        String expectedRef = "ext-ref-success";

        when(externalPaymentService.processPayment(idempotencyKey, amount))
            .thenReturn(expectedRef);

        String result = adapter.processPayment(idempotencyKey, amount);

        assertEquals(expectedRef, result);
        verify(externalPaymentService, times(1)).processPayment(idempotencyKey, amount);
    }

    @Test
    void processPayment_SecondCallWithSameKey_ReturnsCachedReference() {
        String idempotencyKey = "cache-key";
        BigDecimal amount = new BigDecimal("200.00");
        String expectedRef = "ext-ref-cached";

        when(externalPaymentService.processPayment(idempotencyKey, amount))
            .thenReturn(expectedRef);

        String firstResult = adapter.processPayment(idempotencyKey, amount);
        String secondResult = adapter.processPayment(idempotencyKey, amount);

        assertEquals(expectedRef, firstResult);
        assertEquals(expectedRef, secondResult);
        verify(externalPaymentService, times(1)).processPayment(idempotencyKey, amount);
    }

    @Test
    void processPayment_ServiceFailsOnce_RetriesAndSucceeds() {
        String idempotencyKey = "retry-success-key";
        BigDecimal amount = new BigDecimal("300.00");
        String expectedRef = "ext-ref-retry";

        when(externalPaymentService.processPayment(eq(idempotencyKey), eq(amount)))
            .thenThrow(new ExternalServiceException("Temporal failure"))
            .thenReturn(expectedRef);

        String result = adapter.processPayment(idempotencyKey, amount);

        assertEquals(expectedRef, result);
        verify(externalPaymentService, times(2)).processPayment(idempotencyKey, amount);
    }

    @Test
    void processPayment_ServiceFailsMultipleTimes_ThrowsAfterMaxRetries() {
        String idempotencyKey = "retry-fail-key";
        BigDecimal amount = new BigDecimal("400.00");

        when(externalPaymentService.processPayment(any(), any()))
            .thenThrow(new ExternalServiceException("Persistent failure"));

        ExternalServiceException exception = assertThrows(ExternalServiceException.class, () -> {
            adapter.processPayment(idempotencyKey, amount);
        });

        assertTrue(exception.getMessage().contains("Persistent failure"));
        verify(externalPaymentService, atLeast(3)).processPayment(idempotencyKey, amount);
    }

    @Test
    void processPayment_InvalidAmount_ThrowsIllegalArgumentException() {
        String idempotencyKey = "invalid-amount";
        BigDecimal negativeAmount = new BigDecimal("-100.00");

        assertThrows(IllegalArgumentException.class, () -> {
            adapter.processPayment(idempotencyKey, negativeAmount);
        });

        verify(externalPaymentService, never()).processPayment(any(), any());
    }

    @Test
    void processPayment_NullIdempotencyKey_ThrowsIllegalArgumentException() {
        BigDecimal amount = new BigDecimal("500.00");

        assertThrows(IllegalArgumentException.class, () -> {
            adapter.processPayment(null, amount);
        });

        verify(externalPaymentService, never()).processPayment(any(), any());
    }

    @Test
    void processPayment_ZeroAmount_ThrowsIllegalArgumentException() {
        String idempotencyKey = "zero-amount";
        BigDecimal zeroAmount = BigDecimal.ZERO;

        assertThrows(IllegalArgumentException.class, () -> {
            adapter.processPayment(idempotencyKey, zeroAmount);
        });

        verify(externalPaymentService, never()).processPayment(any(), any());
    }
}
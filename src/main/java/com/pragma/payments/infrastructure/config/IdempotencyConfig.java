package com.pragma.payments.infrastructure.config;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigMapping(prefix = "payments.idempotency")
public interface IdempotencyConfig {

    @WithDefault("3600")
    int keyTtlSeconds();

    @WithDefault("true")
    boolean enabled();

    @WithDefault("PAY-")
    String keyPrefix();
}
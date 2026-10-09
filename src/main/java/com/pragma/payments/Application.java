package com.pragma.payments;


import com.pragma.payments.domain.model.Payment;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

@QuarkusMain
public class Application implements QuarkusApplication {

    private static final Logger LOG = Logger.getLogger(Application.class);

    @ConfigProperty(name = "quarkus.http.host", defaultValue = "0.0.0.0")
    String host;

    @ConfigProperty(name = "quarkus.http.port", defaultValue = "8080")
    int port;

    public static void main(String... args) {
        Quarkus.run(Application.class, args);
    }

    @Override
    public int run(String... args) {
        LOG.infof("Starting Payments Service on %s:%d", host, port);
        LOG.info("Payment processing system initialized successfully");
        LOG.info("Idempotency support enabled");
        LOG.info("Fault tolerance mechanisms configured");
        
        Quarkus.waitForExit();
        return 0;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
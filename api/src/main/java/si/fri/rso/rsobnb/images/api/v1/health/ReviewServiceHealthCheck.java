package si.fri.rso.rsobnb.images.api.v1.health;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import si.fri.rso.rsobnb.images.api.v1.configuration.RestProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.logging.Logger;

@Health
@ApplicationScoped
public class ReviewServiceHealthCheck implements HealthCheck {

    @Inject
    private RestProperties restProperties;

    private Logger log = Logger.getLogger(ReviewServiceHealthCheck.class.getName());

    @Override
    public HealthCheckResponse call() {

        if (restProperties.isHealthy()) {
            return HealthCheckResponse.named(ReviewServiceHealthCheck.class.getSimpleName()).up().build();
        } else {
            return HealthCheckResponse.named(ReviewServiceHealthCheck.class.getSimpleName()).down().build();
        }

    }

}

package eu.dar3.notatki.configuration;

import eu.dar3.notatki.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.Location;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

import java.nio.charset.StandardCharsets;

@Slf4j
@Configuration
public class FlywayConfiguration {

    private final Environment environment;

    public FlywayConfiguration(Environment environment) {
        this.environment = environment;
    }

    @SuppressWarnings("DataFlowIssue")
    @EventListener(ApplicationReadyEvent.class)
    public void setup() {
        Boolean isEnabled = environment.getProperty(Constants.FLYWAY_ENABLED, Boolean.class);

        if (isEnabled) {
            String url = environment.getProperty(Constants.FLYWAY_CONNECTION_URL);
            String username = environment.getProperty(Constants.FLYWAY_USER);
            String password = environment.getProperty(Constants.FLYWAY_PASSWORD);
            Boolean isRepair = environment.getProperty(Constants.FLYWAY_REPAIR, Boolean.class);
            Location migrations = new Location("db/migration");
            Location mixtures = new Location("db/mixture");
            Flyway flyway = Flyway.configure()
                    .encoding(StandardCharsets.UTF_8)
                    .locations(migrations, mixtures)
                    .dataSource(url, username, password)
                    .loggers(environment.getProperty(Constants.FLYWAY_LOGGER))
                    .placeholderReplacement(false)
                    .failOnMissingLocations(true)
                    .baselineOnMigrate(true)
                    .load();
//            log.info("Repair:{}", isRepair);
            if (isRepair) {
                flyway.repair();
            } else {
                flyway.migrate();
            }
        }
    }
}

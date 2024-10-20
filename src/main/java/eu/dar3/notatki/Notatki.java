package eu.dar3.notatki;

import eu.dar3.notatki.configuration.ContextFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication(exclude = FlywayAutoConfiguration.class)
public class Notatki {

    public static void main(String[] args) {

        new SpringApplicationBuilder()
            .sources(Notatki.class)
            .bannerMode(Banner.Mode.OFF)
            .contextFactory(new ContextFactory())
            .build()
            .run(args);
    }
}

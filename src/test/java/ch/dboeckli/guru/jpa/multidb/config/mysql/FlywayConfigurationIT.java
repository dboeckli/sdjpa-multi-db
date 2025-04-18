package ch.dboeckli.guru.jpa.multidb.config.mysql;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test_mysql")
@Slf4j
class FlywayConfigurationIT {

    @Autowired
    private Environment env;

    @Test
    void cardFlywayDataSourceProps() {
        String[] requiredProperties = {
            "spring.flyway.card.url",
            "spring.flyway.card.username",
            "spring.flyway.card.password",
            "spring.flyway.card.locations"
        };

        for (String property : requiredProperties) {
            log.info("Property: {} has value {}", property, env.getProperty(property));
            assertNotNull(env.getProperty(property), "Property " + property + " should not be null");
        }
    }

    @Test
    void cardholderFlywayDataSourceProps() {
        String[] requiredProperties = {
            "spring.flyway.cardholder.url",
            "spring.flyway.cardholder.username",
            "spring.flyway.cardholder.password",
            "spring.flyway.cardholder.locations"
        };

        for (String property : requiredProperties) {
            log.info("Property: {} has value {}", property, env.getProperty(property));
            assertNotNull(env.getProperty(property), "Property " + property + " should not be null");
        }
    }

    @Test
    void panFlywayDataSourceProps() {
        String[] requiredProperties = {
            "spring.flyway.pan.url",
            "spring.flyway.pan.username",
            "spring.flyway.pan.password",
            "spring.flyway.pan.locations"
        };

        for (String property : requiredProperties) {
            log.info("Property: {} has value {}", property, env.getProperty(property));
            assertNotNull(env.getProperty(property), "Property " + property + " should not be null");
        }
    }
}
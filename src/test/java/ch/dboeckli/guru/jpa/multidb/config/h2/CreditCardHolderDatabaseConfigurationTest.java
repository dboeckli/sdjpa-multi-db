package ch.dboeckli.guru.jpa.multidb.config.h2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
class CreditCardHolderDatabaseConfigurationTest {

    @Autowired
    private Environment env;

    @Test
    void testDatabasePropertiesExist() {
        String[] requiredProperties = {
            "spring.datasource.cardholder.url",
            "spring.datasource.cardholder.username",
            "spring.datasource.cardholder.password",
            "spring.datasource.cardholder.hikari.poolName"
        };

        for (String property : requiredProperties) {
            log.info("Property: {} has value {}", property, env.getProperty(property));
            assertNotNull(env.getProperty(property), "Property " + property + " should not be null");
        }
    }

    @Test
    void testDatabasePropertiesNotEmpty() {
        String[] requiredProperties = {
            "spring.datasource.cardholder.url",
            "spring.datasource.cardholder.username",
            "spring.datasource.cardholder.password",
            "spring.datasource.cardholder.hikari.poolName"
        };

        for (String property : requiredProperties) {
            String value = env.getProperty(property);
            if (value == null || value.trim().isEmpty()) {
                fail("Property " + property + " should not be empty");
            }
        }
    }

}
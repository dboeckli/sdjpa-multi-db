package ch.dboeckli.guru.jpa.multidb.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
@Profile({"mysql", "test_mysql"})
public class FlywayConfiguration {

    private final Environment env;

    private static final String FLYWAY_PREFIX = "spring.flyway";

    @PostConstruct
    public void validateFlywayConfigs() {
        List<String> databases = Arrays.asList("card", "cardholder", "pan");
        for (String db : databases) {
            String url = env.getProperty(FLYWAY_PREFIX + "." + db + ".url");
            String username = env.getProperty(FLYWAY_PREFIX + "." + db + ".username");
            String password = env.getProperty(FLYWAY_PREFIX + "." + db + ".password");
            String locations = env.getProperty(FLYWAY_PREFIX + "." + db + ".locations");

            log.info("Flyway configuration for {} database. url property is {}", db, url);
            log.info("Flyway configuration for {} database. username property is {}", db, username);
            log.info("Flyway configuration for {} database. password property is {}", db, password);
            log.info("Flyway configuration for {} database. locations property is {}", db, locations);
            HashSet<String> properties = new HashSet<>();
            properties.add("url=" + url);
            properties.add("username=" + username);
            properties.add("password=" + password);
            properties.add("locations=" + locations);

            if (url == null || username == null || password == null || locations == null) {
                throw new IllegalStateException("Incomplete Flyway configuration for " + db + " database. Properties were " + properties);
            }

            String[] locationPaths = locations.split(",");
            for (String location : locationPaths) {
                if (location.startsWith("classpath:") && getClass().getResource(location.substring(10)) == null) {
                    throw new IllegalStateException("Flyway migration location not found: " + location);
                }
            }
        }
    }

    @Bean
    @ConfigurationProperties("spring.flyway.card")
    public DataSourceProperties cardFlywayDataSourceProps(){
        return new DataSourceProperties();
    }

    @Bean(initMethod = "migrate")
    public Flyway flywayCard(@Qualifier("cardFlywayDataSourceProps") DataSourceProperties cardFlywayDataSourceProps,
                             @Value("${spring.flyway.card.locations}") String locations) {
        return Flyway.configure()
            .dataSource(cardFlywayDataSourceProps.getUrl(),
                cardFlywayDataSourceProps.getUsername(),
                cardFlywayDataSourceProps.getPassword())
            .locations(locations)
            .load();
    }

    @Bean
    @ConfigurationProperties("spring.flyway.cardholder")
    public DataSourceProperties cardholderFlywayDataSourceProps(){
        return new DataSourceProperties();
    }

    @Bean(initMethod = "migrate")
    public Flyway flywayCardHolder(@Qualifier("cardholderFlywayDataSourceProps") DataSourceProperties cardholderFlywayDataSourceProps,
                                   @Value("${spring.flyway.cardholder.locations}") String locations) {
        return Flyway.configure()
            .dataSource(cardholderFlywayDataSourceProps.getUrl(),
                cardholderFlywayDataSourceProps.getUsername(),
                cardholderFlywayDataSourceProps.getPassword())
            .locations(locations)
            .load();
    }

    @Bean
    @ConfigurationProperties("spring.flyway.pan")
    public DataSourceProperties panFlywayDataSourceProps(){
        return new DataSourceProperties();
    }

    @Bean(initMethod = "migrate")
    public Flyway flywayPan(@Qualifier("panFlywayDataSourceProps") DataSourceProperties panFlywayDataSourceProps,
                            @Value("${spring.flyway.pan.locations}") String locations) {
        return Flyway.configure()
            .dataSource(panFlywayDataSourceProps.getUrl(),
                panFlywayDataSourceProps.getUsername(),
                panFlywayDataSourceProps.getPassword())
            .locations(locations)
            .load();
    }
}
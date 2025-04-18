package ch.dboeckli.guru.jpa.multidb.config;

import ch.dboeckli.guru.jpa.multidb.domain.pan.CreditCardPan;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class PanDatabaseConfiguration {
    @Bean
    @Primary
    @ConfigurationProperties("spring.pan.datasource")
    public DataSourceProperties panDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    public DataSource panDataSource(@Qualifier("panDataSourceProperties") DataSourceProperties panDataSourceProperties){
        return panDataSourceProperties.initializeDataSourceBuilder()
            .type(HikariDataSource.class)
            .build();
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean panEntityManagerFactory(@Qualifier("panDataSource") DataSource panDataSource,
        EntityManagerFactoryBuilder builder){
        return builder.dataSource(panDataSource)
            .packages(CreditCardPan.class)
            .persistenceUnit("pan")
            .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager panTransactionManager(@Qualifier("entityManagerFactory") LocalContainerEntityManagerFactoryBean panEntityManagerFactory){
        return new JpaTransactionManager(panEntityManagerFactory.getObject());
    }
}

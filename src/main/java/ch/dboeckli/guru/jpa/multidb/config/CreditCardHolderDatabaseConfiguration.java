package ch.dboeckli.guru.jpa.multidb.config;

import ch.dboeckli.guru.jpa.multidb.domain.cardholder.CreditCardHolder;
import ch.dboeckli.guru.jpa.multidb.repository.cardholder.CreditCardHolderRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
    basePackageClasses = CreditCardHolderRepository.class,
    entityManagerFactoryRef = "cardholderEntityManagerFactory",
    transactionManagerRef = "cardholderTransactionManager")
public class CreditCardHolderDatabaseConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.cardholder")
    public DataSourceProperties cardHolderDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource cardholderDataSource(@Qualifier("cardHolderDataSourceProperties") DataSourceProperties cardHolderDataSourceProperties){
        return cardHolderDataSourceProperties.initializeDataSourceBuilder()
            .type(HikariDataSource.class)
            .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean cardholderEntityManagerFactory(
        @Qualifier("cardholderDataSource") DataSource cardholderDataSource,
        EntityManagerFactoryBuilder builder){
        return builder.dataSource(cardholderDataSource)
            .packages(CreditCardHolder.class)
            .persistenceUnit("cardholder")
            .build();
    }

    @Bean
    public PlatformTransactionManager cardholderTransactionManager(
        @Qualifier("cardholderEntityManagerFactory") LocalContainerEntityManagerFactoryBean cardholderEntityManagerFactory){

        return new JpaTransactionManager(cardholderEntityManagerFactory.getObject());
    }
}

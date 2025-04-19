package ch.dboeckli.guru.jpa.multidb.repository.cardholder.mysql;

import ch.dboeckli.guru.jpa.multidb.config.CreditCardHolderDatabaseConfiguration;
import ch.dboeckli.guru.jpa.multidb.config.FlywayConfiguration;
import ch.dboeckli.guru.jpa.multidb.domain.cardholder.CreditCardHolder;
import ch.dboeckli.guru.jpa.multidb.repository.cardholder.CreditCardHolderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test_mysql")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({
    FlywayConfiguration.class,
    CreditCardHolderDatabaseConfiguration.class
})
class CreditCardHolderRepositorySpliceIT {
    @Autowired
    CreditCardHolderRepository creditCardHolderRepository;

    @Test
    void testJpaTestSplice() {
        long countBefore = creditCardHolderRepository.count();

        creditCardHolderRepository.save(new CreditCardHolder());

        long countAfter = creditCardHolderRepository.count();

        assertEquals(countAfter, countBefore + 1);
    }
}
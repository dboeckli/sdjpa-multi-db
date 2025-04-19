package ch.dboeckli.guru.jpa.multidb.repository.creditcard.mysql;

import ch.dboeckli.guru.jpa.multidb.config.CreditCardDatabaseConfiguration;
import ch.dboeckli.guru.jpa.multidb.config.FlywayConfiguration;
import ch.dboeckli.guru.jpa.multidb.domain.creditcard.CreditCard;
import ch.dboeckli.guru.jpa.multidb.repository.creditcard.CreditCardRepository;
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
    CreditCardDatabaseConfiguration.class
})
class CreditCardRepositorySpliceIT {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Test
    void testJpaTestSplice() {
        long countBefore = creditCardRepository.count();

        creditCardRepository.save(new CreditCard());

        long countAfter = creditCardRepository.count();

        assertEquals(countAfter, countBefore + 1);
    }
}
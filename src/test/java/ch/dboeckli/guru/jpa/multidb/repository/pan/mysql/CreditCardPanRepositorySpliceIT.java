package ch.dboeckli.guru.jpa.multidb.repository.pan.mysql;

import ch.dboeckli.guru.jpa.multidb.config.CreditCardPanDatabaseConfiguration;
import ch.dboeckli.guru.jpa.multidb.config.FlywayConfiguration;
import ch.dboeckli.guru.jpa.multidb.domain.pan.CreditCardPan;
import ch.dboeckli.guru.jpa.multidb.repository.pan.CreditCardPanRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test_mysql")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({
    FlywayConfiguration.class,
    CreditCardPanDatabaseConfiguration.class
})
class CreditCardPanRepositorySpliceIT {
    @Autowired
    CreditCardPanRepository creditCardPanRepository;

    @Test
    void testJpaTestSplice() {
        long countBefore = creditCardPanRepository.count();

        creditCardPanRepository.save(new CreditCardPan());

        long countAfter = creditCardPanRepository.count();

        assertEquals(countAfter, countBefore + 1);
    }

}
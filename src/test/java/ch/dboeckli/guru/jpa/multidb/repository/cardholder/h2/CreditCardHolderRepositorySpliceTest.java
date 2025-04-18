package ch.dboeckli.guru.jpa.multidb.repository.cardholder.h2;

import ch.dboeckli.guru.jpa.multidb.domain.cardholder.CreditCardHolder;
import ch.dboeckli.guru.jpa.multidb.repository.cardholder.CreditCardHolderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class CreditCardHolderRepositorySpliceTest {
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
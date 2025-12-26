package ch.dboeckli.guru.jpa.multidb.repository.creditcard.h2;

import ch.dboeckli.guru.jpa.multidb.domain.creditcard.CreditCard;
import ch.dboeckli.guru.jpa.multidb.repository.creditcard.CreditCardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class CreditCardRepositorySpliceTest {

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
package ch.dboeckli.guru.jpa.multidb.repository.pan.h2;

import ch.dboeckli.guru.jpa.multidb.domain.pan.CreditCardPan;
import ch.dboeckli.guru.jpa.multidb.repository.pan.CreditCardPanRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class CreditCardPanRepositorySpliceTest {
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
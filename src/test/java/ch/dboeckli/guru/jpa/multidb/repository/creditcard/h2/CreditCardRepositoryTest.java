package ch.dboeckli.guru.jpa.multidb.repository.creditcard.h2;

import ch.dboeckli.guru.jpa.multidb.domain.creditcard.CreditCard;
import ch.dboeckli.guru.jpa.multidb.repository.DataHelper;
import ch.dboeckli.guru.jpa.multidb.repository.creditcard.CreditCardRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class CreditCardRepositoryTest {
    @Autowired
    CreditCardRepository creditCardRepository;

    @Test
    @Transactional
    void testSaveAndStoreCreditCard() {
        CreditCard saved = DataHelper.createAndSaveCreditCard(creditCardRepository);
        CreditCard fetched = creditCardRepository.findById(saved.getId()).orElseThrow(() -> new AssertionError("Credit card not found"));

        assertThat(saved.getCvv()).isEqualTo(fetched.getCvv());
    }
}

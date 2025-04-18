package ch.dboeckli.guru.jpa.multidb.repository.pan.h2;

import ch.dboeckli.guru.jpa.multidb.domain.pan.CreditCardPan;
import ch.dboeckli.guru.jpa.multidb.repository.DataHelper;
import ch.dboeckli.guru.jpa.multidb.repository.pan.CreditCardPanRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class CreditCardPanRepositoryTest {
    @Autowired
    CreditCardPanRepository creditCardPanRepository;

    @Test
    @Transactional
    void testSaveAndStoreCreditCard() {
        CreditCardPan saved = DataHelper.createAndSaveCreditCardPan(creditCardPanRepository);
        CreditCardPan fetched = creditCardPanRepository.findById(saved.getId()).orElseThrow(() -> new AssertionError("Credit card not found"));

        assertThat(saved.getCreditCardNumber()).isEqualTo(fetched.getCreditCardNumber());
    }

}
package ch.dboeckli.guru.jpa.multidb.repository.pan.mysql;

import ch.dboeckli.guru.jpa.multidb.domain.pan.CreditCardPan;
import ch.dboeckli.guru.jpa.multidb.repository.DataHelper;
import ch.dboeckli.guru.jpa.multidb.repository.pan.CreditCardPanRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test_mysql")
@Slf4j
class CreditCardPanRepositoryIT {
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
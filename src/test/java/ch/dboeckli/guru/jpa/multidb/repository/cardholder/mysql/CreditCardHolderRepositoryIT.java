package ch.dboeckli.guru.jpa.multidb.repository.cardholder.mysql;

import ch.dboeckli.guru.jpa.multidb.domain.cardholder.CreditCardHolder;
import ch.dboeckli.guru.jpa.multidb.repository.DataHelper;
import ch.dboeckli.guru.jpa.multidb.repository.cardholder.CreditCardHolderRepository;
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
class CreditCardHolderRepositoryIT {
    @Autowired
    CreditCardHolderRepository creditCardHolderRepository;

    @Test
    @Transactional
    void testSaveAndStoreCreditCard() {
        CreditCardHolder saved = DataHelper.createAndSaveCreditCardHolder(creditCardHolderRepository);
        CreditCardHolder fetched = creditCardHolderRepository.findById(saved.getId()).orElseThrow(() -> new AssertionError("Credit card Holder not found"));

        assertThat(saved.getFirstName()).isEqualTo(fetched.getFirstName());
    }

}
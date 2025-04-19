package ch.dboeckli.guru.jpa.multidb.service.mysql;

import ch.dboeckli.guru.jpa.multidb.domain.creditcard.CreditCard;
import ch.dboeckli.guru.jpa.multidb.service.CreditCardService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test_mysql")
@Slf4j
class CreditCardServiceImplIT {

    @Autowired
    CreditCardService creditCardService;

    @Test
    void saveCreditCard() {
        CreditCard cc = CreditCard.builder()
            .firstName("John")
            .lastName("Thompson")
            .zipCode("12345")
            .creditCardNumber("1234556")
            .cvv("123")
            .expirationDate("12/26")
            .build();

        CreditCard savedCC = creditCardService.saveCreditCard(cc);

        assertThat(savedCC).isNotNull();
        assertThat(savedCC.getId()).isNotNull();
        assertThat(savedCC.getCreditCardNumber()).isNotNull();

        CreditCard fetchedCreditCard = creditCardService.getCreditCardById(savedCC.getId());

        assertThat(fetchedCreditCard).isNotNull();
        assertThat(fetchedCreditCard.getId()).isNotNull();
        assertThat(fetchedCreditCard.getCreditCardNumber()).isNotNull();
    }
}
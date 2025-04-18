package ch.dboeckli.guru.jpa.multidb.service.h2;

import ch.dboeckli.guru.jpa.multidb.domain.creditcard.CreditCard;
import ch.dboeckli.guru.jpa.multidb.service.CreditCardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CreditCardServiceImplTest {

    @Autowired
    CreditCardService creditCardService;

    @Test
    void getCreditCardById() {
        // TODO: Implement test case
    }

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
    }
}
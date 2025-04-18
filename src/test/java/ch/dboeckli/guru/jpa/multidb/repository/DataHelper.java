package ch.dboeckli.guru.jpa.multidb.repository;


import ch.dboeckli.guru.jpa.multidb.domain.cardholder.CreditCardHolder;
import ch.dboeckli.guru.jpa.multidb.domain.creditcard.CreditCard;
import ch.dboeckli.guru.jpa.multidb.domain.pan.CreditCardPan;
import ch.dboeckli.guru.jpa.multidb.repository.cardholder.CreditCardHolderRepository;
import ch.dboeckli.guru.jpa.multidb.repository.creditcard.CreditCardRepository;
import ch.dboeckli.guru.jpa.multidb.repository.pan.CreditCardPanRepository;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DataHelper {

    public static final String CREDIT_CARD = "12345678900000";
    public static final String CVV = "123";
    public static final String EXPIRATION_DATE = "12/2028";
    public static final String FIRST_NAME = "Peter";
    public static final String LAST_NAME = "Müller";
    public static final String ZIP_CODE = "8703";

    public static final String UPDATED_CREDIT_CARD = "12345678900001";
    public static final String UPDATED_CVV = "321";
    public static final String UPDATED_EXPIRATION_DATE = "12/2029";

    public static CreditCard createAndSaveCreditCard(CreditCardRepository creditCardRepository) {
        CreditCard creditCard = new CreditCard();
        creditCard.setCvv(CVV);
        creditCard.setExpirationDate(EXPIRATION_DATE);

        return creditCardRepository.saveAndFlush(creditCard);
    }

    public static CreditCardPan createAndSaveCreditCardPan(CreditCardPanRepository creditCardPanRepository) {
        CreditCardPan creditCardPan = new CreditCardPan();
        creditCardPan.setCreditCardNumber(CREDIT_CARD);

        return creditCardPanRepository.saveAndFlush(creditCardPan);
    }

    public static CreditCardHolder createAndSaveCreditCardHolder(CreditCardHolderRepository creditCardHolderRepository) {
        CreditCardHolder creditCardHolder = new CreditCardHolder();
        creditCardHolder.setFirstName(FIRST_NAME);
        creditCardHolder.setLastName(LAST_NAME);
        creditCardHolder.setZipCode(ZIP_CODE);

        return creditCardHolderRepository.saveAndFlush(creditCardHolder);
    }

}

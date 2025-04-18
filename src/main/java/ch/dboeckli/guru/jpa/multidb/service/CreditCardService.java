package ch.dboeckli.guru.jpa.multidb.service;

import ch.dboeckli.guru.jpa.multidb.domain.creditcard.CreditCard;

public interface CreditCardService {
    CreditCard getCreditCardById(Long id);
    CreditCard saveCreditCard(CreditCard cc);
}

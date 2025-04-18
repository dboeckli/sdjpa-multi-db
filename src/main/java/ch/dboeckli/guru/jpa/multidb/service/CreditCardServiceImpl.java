package ch.dboeckli.guru.jpa.multidb.service;

import ch.dboeckli.guru.jpa.multidb.domain.cardholder.CreditCardHolder;
import ch.dboeckli.guru.jpa.multidb.domain.creditcard.CreditCard;
import ch.dboeckli.guru.jpa.multidb.domain.pan.CreditCardPan;
import ch.dboeckli.guru.jpa.multidb.repository.cardholder.CreditCardHolderRepository;
import ch.dboeckli.guru.jpa.multidb.repository.creditcard.CreditCardRepository;
import ch.dboeckli.guru.jpa.multidb.repository.pan.CreditCardPanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardHolderRepository creditCardHolderRepository;
    private final CreditCardRepository creditCardRepository;
    private final CreditCardPanRepository creditCardPANRepository;

    @Override
    public CreditCard getCreditCardById(Long id) {
        // TODO: Implement logic to retrieve credit card from the respective database based on the given ID
        return null;
    }

    @Override
    @Transactional
    public CreditCard saveCreditCard(CreditCard creditCard) {
        CreditCard savedCC = creditCardRepository.save(creditCard);
        savedCC.setFirstName(creditCard.getFirstName());
        savedCC.setLastName(creditCard.getLastName());
        savedCC.setZipCode(creditCard.getCreditCardNumber());
        savedCC.setCreditCardNumber(creditCard.getCreditCardNumber());

        creditCardHolderRepository.save(CreditCardHolder.builder()
            .creditCardId(savedCC.getId())
            .firstName(savedCC.getFirstName())
            .lastName(savedCC.getLastName())
            .zipCode(savedCC.getZipCode())
            .build());

        creditCardPANRepository.save(CreditCardPan.builder()
            .creditCardNumber(savedCC.getCreditCardNumber())
            .creditCardId(savedCC.getId())
            .build());

        return savedCC;
    }
}

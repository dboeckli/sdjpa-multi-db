package ch.dboeckli.guru.jpa.multidb.repository.creditcard;

import ch.dboeckli.guru.jpa.multidb.domain.creditcard.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}

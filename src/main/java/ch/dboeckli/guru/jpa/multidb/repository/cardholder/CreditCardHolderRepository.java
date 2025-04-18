package ch.dboeckli.guru.jpa.multidb.repository.cardholder;

import ch.dboeckli.guru.jpa.multidb.domain.cardholder.CreditCardHolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardHolderRepository extends JpaRepository<CreditCardHolder, Long> {
}

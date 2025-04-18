package ch.dboeckli.guru.jpa.multidb.repository.pan;

import ch.dboeckli.guru.jpa.multidb.domain.pan.CreditCardPan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardPanRepository extends JpaRepository<CreditCardPan, Long> {
}

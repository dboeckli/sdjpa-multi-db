package ch.dboeckli.guru.jpa.multidb.repository.pan;

import ch.dboeckli.guru.jpa.multidb.domain.pan.CreditCardPan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreditCardPanRepository extends JpaRepository<CreditCardPan, Long> {
    Optional<CreditCardPan> findByCreditCardId(Long id);
}

package io.github.lofi.mscards.infra.repository;

import io.github.lofi.mscards.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByEarningsLessThanEqual(BigDecimal earningsBigDecimal);
}

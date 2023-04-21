package io.github.lofi.mscards.application;

import io.github.lofi.mscards.domain.Card;
import io.github.lofi.mscards.infra.repository.CardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository repository;

    @Transactional
    public Card register(Card card) {
        return repository.save(card);
    }

    public List<Card> getCardsEarningsLessThanEqual(Long earnings) {
        var earningsBigDecimal = BigDecimal.valueOf(earnings);
        return repository.findByEarningsLessThanEqual(earningsBigDecimal);
    }
}

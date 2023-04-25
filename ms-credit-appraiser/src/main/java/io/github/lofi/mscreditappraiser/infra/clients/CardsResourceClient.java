package io.github.lofi.mscreditappraiser.infra.clients;


import io.github.lofi.mscreditappraiser.domain.model.Card;
import io.github.lofi.mscreditappraiser.domain.model.CardClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "ms-cards", path = "/cards")
public interface CardsResourceClient {
    @GetMapping(params = "cpf")
    ResponseEntity<List<CardClient>> getCardsByClient(@RequestParam("cpf") String cpf);

    @GetMapping(params = "earnings")
    ResponseEntity<List<Card>> getCardsEarningsUntil(@RequestParam("earnings") Long earnings);
}

package io.github.lofi.mscards.application;

import io.github.lofi.mscards.application.representation.CardSaveRequest;
import io.github.lofi.mscards.application.representation.CardsByClientResponse;
import io.github.lofi.mscards.domain.Card;
import io.github.lofi.mscards.domain.CardClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService service;
    private final CardClientService clientService;
    @GetMapping
    public String status() {
        return "Ok";
    }

    @PostMapping
    public ResponseEntity register(@RequestBody CardSaveRequest request) {
        Card card = request.toModel();
        service.register(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "earnings")
    public ResponseEntity<List<Card>> getCardsEarningsUntil(@RequestParam("earnings") Long earnings) {
        List<Card> cardList = service.getCardsEarningsLessThanEqual(earnings);
        return ResponseEntity.ok(cardList);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CardsByClientResponse>> getCardsByClient(@RequestParam("cpf") String cpf) {
        List<CardClient> cardClientList = clientService.listCardsByCpf(cpf);
        List<CardsByClientResponse> resultList = cardClientList.stream()
                .map(CardsByClientResponse::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }
}

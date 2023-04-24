package io.github.lofi.mscreditappraiser.application;

import io.github.lofi.mscreditappraiser.domain.model.ClientSituation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("credit-ratings")
@RequiredArgsConstructor
public class CreditAppraiserController {

    private final CreditAppraiserService service;

    @GetMapping
    public String status() {
        return "Ok";
    }

    @GetMapping(value = "customer-situation", params = "cpf")
    public ResponseEntity<ClientSituation> checkClientSituation(@RequestParam("cpf") String cpf) {
        ClientSituation clientSituation = service.getClientSituation(cpf);
        return ResponseEntity.ok(clientSituation);
    }
}

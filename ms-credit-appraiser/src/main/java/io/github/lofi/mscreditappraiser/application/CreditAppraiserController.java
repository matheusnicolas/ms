package io.github.lofi.mscreditappraiser.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.lofi.mscreditappraiser.application.exception.ClientDataNotFoundException;
import io.github.lofi.mscreditappraiser.application.exception.CommunicationErrorMicroservicesException;
import io.github.lofi.mscreditappraiser.application.exception.RequestCardErrorException;
import io.github.lofi.mscreditappraiser.domain.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity checkClientSituation(@RequestParam("cpf") String cpf) {
        try {
            ClientSituation clientSituation = service.getClientSituation(cpf);
            return ResponseEntity.ok(clientSituation);
        } catch (ClientDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (CommunicationErrorMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity doEvaluation(@RequestBody EvaluationData data) {
        try {
            ClientEvaluationResponse clientEvaluationResponse = service.doEvaluation(data.getCpf(), data.getEarnings());
            return ResponseEntity.ok(clientEvaluationResponse);
        } catch (ClientDataNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (CommunicationErrorMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping("cards-request")
    public ResponseEntity requestCard(@RequestBody CardEmissionRequestData data) {
        try {
            RequestCardProtocol requestCardProtocol = service.requestCardEmission(data);
            return ResponseEntity.ok(requestCardProtocol);
        } catch (RequestCardErrorException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}

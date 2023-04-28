package io.github.lofi.mscreditappraiser.application;


import com.fasterxml.jackson.core.JsonProcessingException;
import feign.FeignException;
import io.github.lofi.mscreditappraiser.application.exception.ClientDataNotFoundException;
import io.github.lofi.mscreditappraiser.application.exception.CommunicationErrorMicroservicesException;
import io.github.lofi.mscreditappraiser.application.exception.RequestCardErrorException;
import io.github.lofi.mscreditappraiser.domain.model.*;
import io.github.lofi.mscreditappraiser.infra.clients.CardsResourceClient;
import io.github.lofi.mscreditappraiser.infra.clients.ClientResourceClient;
import io.github.lofi.mscreditappraiser.infra.mqueue.CardEmissionRequestPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {

    private final ClientResourceClient clientsClient;
    private final CardsResourceClient cardsClient;
    private final CardEmissionRequestPublisher cardEmissionRequestPublisher;

    public ClientSituation getClientSituation(String cpf)
            throws ClientDataNotFoundException, CommunicationErrorMicroservicesException {

        try {
            ResponseEntity<ClientData> clientDataResponse = clientsClient.clientData(cpf);
            ResponseEntity<List<CardClient>> cardsResponse = cardsClient.getCardsByClient(cpf);

            return ClientSituation
                    .builder()
                    .cards(cardsResponse.getBody())
                    .client(clientDataResponse.getBody())
                    .build();

        } catch(FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new ClientDataNotFoundException();
            }

            throw new CommunicationErrorMicroservicesException(e.getMessage(), status);
        }
    }

    public ClientEvaluationResponse doEvaluation(String cpf, Long earnings)
            throws ClientDataNotFoundException, CommunicationErrorMicroservicesException {

        try {
            ResponseEntity<ClientData> clientDataResponse = clientsClient.clientData(cpf);
            ResponseEntity<List<Card>> cardsResponse = cardsClient.getCardsEarningsUntil(earnings);

            List<Card> cards = cardsResponse.getBody();

            var listApprovedCards = cards.stream().map(card -> {

                ClientData clientData = clientDataResponse.getBody();

                BigDecimal basicLimit = card.getBasicLimit();
                BigDecimal ageBD = BigDecimal.valueOf(clientData.getAge());
                var factor = ageBD.divide(BigDecimal.valueOf(10));

                BigDecimal approvedLimit = factor.multiply(basicLimit);

                ApprovedCard approvedCard = new ApprovedCard();
                approvedCard.setCard(card.getName());
                approvedCard.setCompany(card.getCompany());
                approvedCard.setApprovedLimit(approvedLimit);

                return approvedCard;
            }).collect(Collectors.toList());

            return new ClientEvaluationResponse(listApprovedCards);

        } catch(FeignException.FeignClientException e) {

            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new ClientDataNotFoundException();
            }

            throw new CommunicationErrorMicroservicesException(e.getMessage(), status);
        }
    }

    public RequestCardProtocol requestCardEmission(CardEmissionRequestData data) {
        try {
            cardEmissionRequestPublisher.requestCard(data);
            var protocol = UUID.randomUUID().toString();
            return new RequestCardProtocol(protocol);
        } catch (Exception e) {
            throw new RequestCardErrorException(e.getMessage());
        }
    }
}



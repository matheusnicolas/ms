package io.github.lofi.mscards.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lofi.mscards.domain.Card;
import io.github.lofi.mscards.domain.CardClient;
import io.github.lofi.mscards.domain.CardEmissionRequestData;
import io.github.lofi.mscards.infra.repository.CardClientRepository;
import io.github.lofi.mscards.infra.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardEmissionSubscriber {

    private final CardRepository cardRepository;
    private final CardClientRepository cardClientRepository;

    @RabbitListener(queues = "${mq.queues.card-emission}")
    public void receiveEmissionRequest(@Payload String payload) {

        try {
            var mapper = new ObjectMapper();
            CardEmissionRequestData data = mapper.readValue(payload, CardEmissionRequestData.class);
            Card card = cardRepository.findById(data.getCardId()).orElseThrow();

            CardClient cardClient = new CardClient(
                    card,
                    data.getCpf(),
                    data.getApprovedLimit());

            cardClientRepository.save(cardClient);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

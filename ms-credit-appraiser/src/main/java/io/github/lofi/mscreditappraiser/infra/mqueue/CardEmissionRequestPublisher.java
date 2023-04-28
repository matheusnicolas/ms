package io.github.lofi.mscreditappraiser.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lofi.mscreditappraiser.domain.model.CardEmissionRequestData;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardEmissionRequestPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueCardEmission;

    public void requestCard(CardEmissionRequestData data) throws JsonProcessingException {
        var json = convertIntoJson(data);
        rabbitTemplate.convertAndSend(queueCardEmission.getName(), json);
    }

    private String convertIntoJson(CardEmissionRequestData data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(data);
    }
}

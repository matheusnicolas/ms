package io.github.lofi.mscreditappraiser.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.card-emission}")
    private String cardsEmissionQueue;

    public Queue queueCardsEmission() {
        return new Queue(cardsEmissionQueue, true);
    }
}

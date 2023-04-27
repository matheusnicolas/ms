package io.github.lofi.mscards.infra.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CardEmissionSubscriber {

    @RabbitListener(queues = "${mq.queues.card-emission}")
    public void receiveEmissionRequest(@Payload String payload) {
        System.out.println(payload);
    }
}

package com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driven.messaging.microservices.kafka;

import com.dcastillo.hexagonalarchitecture.common.utils.annotations.adapter.DrivenAdapter;
import com.dcastillo.hexagonalarchitecture.common.utils.event.Event;
import com.dcastillo.hexagonalarchitecture.layers.application.event.authentication.UserLoggedInEvent;
import com.dcastillo.hexagonalarchitecture.layers.application.port.driven.messaging.MicroservicesPublisher;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Component
@DrivenAdapter
@Transactional(isolation = Isolation.READ_COMMITTED)
@RequiredArgsConstructor
public class KafkaPublisher implements MicroservicesPublisher {

    private final KafkaProducer<String, UserLoggedInEvent> userLoggedInEventProducer;

    @Override
    public void genericHandleEvent(Event event) {

    }

    @Override
    @EventListener
    public void handleUserLoggedInEvent(UserLoggedInEvent event) {
        ProducerRecord<String, UserLoggedInEvent> userLoggedInEventRecord =
                new ProducerRecord<>("users-test-topic", event);

        userLoggedInEventProducer.send(userLoggedInEventRecord);
    }
}

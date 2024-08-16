package com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driven.messaging.kafka;

import com.dcastillo.hexagonalarchitecture.common.utils.annotations.adapter.DrivenAdapter;
import com.dcastillo.hexagonalarchitecture.common.utils.message.Message;
import com.dcastillo.hexagonalarchitecture.layers.application.port.driven.messaging.MessagePublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@DrivenAdapter
@Transactional(isolation = Isolation.READ_COMMITTED)
@RequiredArgsConstructor
public class KafkaMessagePublisher implements MessagePublisher {
    @Override
    public void publish(List<? extends Message> messages) {

    }
}

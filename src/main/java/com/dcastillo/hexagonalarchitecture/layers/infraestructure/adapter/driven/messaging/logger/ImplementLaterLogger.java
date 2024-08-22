package com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driven.messaging.logger;

import com.dcastillo.hexagonalarchitecture.common.utils.annotations.adapter.DrivenAdapter;
import com.dcastillo.hexagonalarchitecture.common.utils.message.LogMessage;
import com.dcastillo.hexagonalarchitecture.layers.application.port.driven.messaging.LoggerPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@DrivenAdapter
public class ImplementLaterLogger implements LoggerPublisher {
    @Override
    public void publish(List<? extends LogMessage> messages) {

    }
}

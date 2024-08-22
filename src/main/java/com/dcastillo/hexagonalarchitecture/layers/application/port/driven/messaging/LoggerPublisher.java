package com.dcastillo.hexagonalarchitecture.layers.application.port.driven.messaging;

import com.dcastillo.hexagonalarchitecture.common.utils.message.LogMessage;

import java.util.List;

public interface LoggerPublisher {
    void publish(List<? extends LogMessage> messages);
}

package com.dcastillo.hexagonalarchitecture.layers.application.port.driven.messaging;

import com.dcastillo.hexagonalarchitecture.common.utils.annotations.port.DrivenPort;
import com.dcastillo.hexagonalarchitecture.common.utils.message.LogMessage;

import java.util.List;

@DrivenPort
public interface LoggerPublisher {
    void publish(List<? extends LogMessage> messages);
}

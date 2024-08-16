package com.dcastillo.hexagonalarchitecture.layers.application.port.driven.messaging;

import com.dcastillo.hexagonalarchitecture.common.utils.message.Message;

import java.util.List;

public interface MessagePublisher {
    void publish(List<? extends Message> messages);
}

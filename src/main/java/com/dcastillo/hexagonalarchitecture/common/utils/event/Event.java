package com.dcastillo.hexagonalarchitecture.common.utils.event;

import com.dcastillo.hexagonalarchitecture.common.utils.message.Message;

import java.time.Instant;

public interface Event extends Message {
    static Instant now() {
        return Instant.now();
    }

    EventId getId();
}

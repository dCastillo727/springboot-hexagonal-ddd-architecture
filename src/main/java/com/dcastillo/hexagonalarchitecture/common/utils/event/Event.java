package com.dcastillo.hexagonalarchitecture.common.utils.event;

import com.dcastillo.hexagonalarchitecture.common.utils.message.LogMessage;

import java.time.Instant;

public interface Event extends LogMessage {
    static Instant now() {
        return Instant.now();
    }

    EventId getId();
}

package com.dcastillo.hexagonalarchitecture.common.utils.command;

import java.time.Instant;

public interface Command {
    static Instant now() {
        return Instant.now();
    }

    CommandId id();
}

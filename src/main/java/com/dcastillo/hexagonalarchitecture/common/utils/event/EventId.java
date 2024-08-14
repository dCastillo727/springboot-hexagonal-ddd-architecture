package com.dcastillo.hexagonalarchitecture.common.utils.event;

import java.util.UUID;

public record EventId(UUID id) {
    public EventId {
        if (id == null) throw new NullPointerException("EventId cannot be null");
    }

    public static EventId fromString(String id) {
        return new EventId(UUID.fromString(id));
    }

    public static EventId of(UUID id) {
        return new EventId(id);
    }

    public static EventId generate() {
        return new EventId(UUID.randomUUID());
    }
}

package com.dcastillo.hexagonalarchitecture.layers.domain.model.user;

import java.util.UUID;

public record UserId(String id) {
    public UserId {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Invalid user id");
    }

    public static UserId from(final String id) {
        return new UserId(id);
    }

    public static UserId generate() {
        return new UserId(UUID.randomUUID().toString());
    }

    public String asString() {
        return id;
    }
}

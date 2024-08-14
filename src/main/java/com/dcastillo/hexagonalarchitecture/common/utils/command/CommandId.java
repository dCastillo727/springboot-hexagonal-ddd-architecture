package com.dcastillo.hexagonalarchitecture.common.utils.command;

import java.util.UUID;

public record CommandId(UUID id) {
    public CommandId {
        if (id == null) throw new NullPointerException("CommandId cannot be null");
    }

    public static CommandId from(String id) {
        return new CommandId(UUID.fromString(id));
    }

    public static CommandId of(UUID id) {
        return new CommandId(id);
    }

    public static CommandId generate() {
        return new CommandId(UUID.randomUUID());
    }
}

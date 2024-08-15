package com.dcastillo.hexagonalarchitecture.layers.domain.command.user;

import com.dcastillo.hexagonalarchitecture.common.utils.command.Command;
import com.dcastillo.hexagonalarchitecture.common.utils.command.CommandId;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.EmailAddress;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.role.Role;

import java.time.Instant;
import java.util.Optional;

public record RegisterUserCommand(
        CommandId id,
        Instant registeredAt,
        EmailAddress emailAddress,
        String username,
        String password,
        Role role
) implements Command {
    public static RegisterUserCommand issue(
            final EmailAddress emailAddress,
            final String username,
            final String password,
            final Role role
    ) {
        return new RegisterUserCommand(
                CommandId.generate(),
                Command.now(),
                emailAddress,
                username,
                password,
                role
        );
    }

    public static RegisterUserCommand issue(
            final EmailAddress emailAddress,
            final String username,
            final String password
    ) {
        return new RegisterUserCommand(
                CommandId.generate(),
                Command.now(),
                emailAddress,
                username,
                password,
                Role.USER
        );
    }
}

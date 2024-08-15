package com.dcastillo.hexagonalarchitecture.layers.domain.command.user;

import com.dcastillo.hexagonalarchitecture.common.utils.command.Command;
import com.dcastillo.hexagonalarchitecture.common.utils.command.CommandId;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.EmailAddress;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.UserId;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.role.Role;

import java.time.Instant;
import java.util.Optional;

public record ChangeUserInfoCommand(
        CommandId commandId,
        Instant registeredAt,
        UserId userId,
        Optional<EmailAddress> emailAddress,
        Optional<String> username,
        Optional<String> password,
        Optional<Role> role
) {
    public static ChangeUserInfoCommand issue(
            UserId userId,
            Optional<EmailAddress> emailAddress,
            Optional<String> username,
            Optional<String> password,
            Optional<Role> role
    ) {
        return new ChangeUserInfoCommand(
                CommandId.generate(),
                Command.now(),
                userId,
                emailAddress,
                username,
                password,
                role
        );
    }
}

package com.dcastillo.hexagonalarchitecture.layers.domain.model.user;

import com.dcastillo.hexagonalarchitecture.common.utils.aggregate.AggregateDomainEvent;
import com.dcastillo.hexagonalarchitecture.common.utils.event.DomainEvent;
import com.dcastillo.hexagonalarchitecture.layers.domain.command.user.ChangeUserInfoCommand;
import com.dcastillo.hexagonalarchitecture.layers.domain.command.user.RegisterUserCommand;
import com.dcastillo.hexagonalarchitecture.layers.domain.event.user.UserInfoChangedEvent;
import com.dcastillo.hexagonalarchitecture.layers.domain.event.user.UserRegisteredEvent;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.role.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@Getter
public class User implements AggregateDomainEvent {
    @Singular
    private final List<DomainEvent> events;
    private final UserId userId;
    private EmailAddress emailAddress;
    private String username;
    private String password;
    private Role role;

    public User(
            final UserId userId,
            final EmailAddress emailAddress,
            final String username,
            final String password,
            final Role role
    ) {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(emailAddress);
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        Objects.requireNonNull(role);

        this.events = new ArrayList<>();
        this.userId = userId;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public static User from(
            final UserId userId,
            final EmailAddress emailAddress,
            final String username,
            final String password,
            final Role role
    ) {
        return User.builder()
                .userId(userId)
                .emailAddress(emailAddress)
                .username(username)
                .password(password)
                .role(role)
                .build();
    }

    public static User registerBy(final RegisterUserCommand command) {
        if (command == null) throw new NullPointerException("RegisterUserCommand cannot be null");

        final UserId userId = UserId.generate();

        return User.builder()
                .userId(userId)
                .emailAddress(command.emailAddress())
                .username(command.username())
                .password(command.password())
                .role(command.role())
                .event(UserRegisteredEvent.issue(userId))
                .build();
    }

    public User changeInfoBy(final ChangeUserInfoCommand command) {
        if (command == null) throw new NullPointerException("ChangeUserInfoCommand cannot be null");

        command.emailAddress().ifPresent(emailAddress -> this.emailAddress = emailAddress);
        command.username().ifPresent(username -> this.username = username);
        command.password().ifPresent(password -> this.password = password);
        command.role().ifPresent(role -> this.role = role);

        this.events.add(UserInfoChangedEvent.issue(command.userId()));

        return this;
    }

    @Override
    public void clearEvents() {
        events.clear();
    }

    @Override
    public List<DomainEvent> listEvents() {
        return List.copyOf(events);
    }
}

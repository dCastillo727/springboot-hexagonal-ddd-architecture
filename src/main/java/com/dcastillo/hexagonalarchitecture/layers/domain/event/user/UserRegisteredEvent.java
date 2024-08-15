package com.dcastillo.hexagonalarchitecture.layers.domain.event.user;

import com.dcastillo.hexagonalarchitecture.common.utils.event.DomainEvent;
import com.dcastillo.hexagonalarchitecture.common.utils.event.EventId;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.UserId;

import java.time.Instant;

public record UserRegisteredEvent(EventId eventId, Instant registeredAt, UserId userId) implements DomainEvent {
    public static UserRegisteredEvent issue(final UserId userId) {
        return new UserRegisteredEvent(EventId.generate(), Instant.now(), userId);
    }
}

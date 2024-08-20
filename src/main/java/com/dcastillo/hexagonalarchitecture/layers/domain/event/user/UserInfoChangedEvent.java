package com.dcastillo.hexagonalarchitecture.layers.domain.event.user;

import com.dcastillo.hexagonalarchitecture.common.utils.event.DomainEvent;
import com.dcastillo.hexagonalarchitecture.common.utils.event.EventId;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.UserId;

import java.time.Instant;

public record UserInfoChangedEvent(EventId eventId, Instant registeredAt, UserId userId) implements DomainEvent {
    public static UserInfoChangedEvent issue(final UserId userId) {
        return new UserInfoChangedEvent(EventId.generate(), Instant.now(), userId);
    }

    @Override
    public EventId getId() {
        return eventId;
    }
}

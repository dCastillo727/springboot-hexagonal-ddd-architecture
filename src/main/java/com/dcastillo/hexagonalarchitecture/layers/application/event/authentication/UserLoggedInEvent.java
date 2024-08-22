package com.dcastillo.hexagonalarchitecture.layers.application.event.authentication;

import com.dcastillo.hexagonalarchitecture.common.utils.event.ApplicationEvent;
import com.dcastillo.hexagonalarchitecture.common.utils.event.Event;
import com.dcastillo.hexagonalarchitecture.common.utils.event.EventId;
import com.dcastillo.hexagonalarchitecture.layers.domain.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

import java.time.Instant;

@Builder
public record UserLoggedInEvent(EventId eventId, Instant registeredAt, User user) implements ApplicationEvent {
    public static UserLoggedInEvent issue(User user) {
        return new UserLoggedInEvent(EventId.generate(), Event.now(), user);
    }

    @Override
    @JsonIgnore
    public EventId getId() {
        return eventId;
    }
}

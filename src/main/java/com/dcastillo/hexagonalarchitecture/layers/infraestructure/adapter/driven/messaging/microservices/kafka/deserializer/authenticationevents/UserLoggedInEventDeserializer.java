package com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driven.messaging.microservices.kafka.deserializer.authenticationevents;

import com.dcastillo.hexagonalarchitecture.layers.application.event.authentication.UserLoggedInEvent;
import org.apache.kafka.common.serialization.Deserializer;

public class UserLoggedInEventDeserializer implements Deserializer<UserLoggedInEvent> {


    @Override
    public UserLoggedInEvent deserialize(String s, byte[] bytes) {
        return null;
    }
}

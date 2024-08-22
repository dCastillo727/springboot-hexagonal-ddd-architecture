package com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driven.messaging.microservices.kafka.serializer.authenticationevents;

import com.dcastillo.hexagonalarchitecture.layers.application.event.authentication.UserLoggedInEvent;
import com.dcastillo.hexagonalarchitecture.layers.infraestructure.configuration.serialization.ObjectMapperConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.hibernate.type.SerializationException;

import java.util.Map;

public class UserLoggedInEventSerializer implements Serializer<UserLoggedInEvent> {
    private final ObjectMapper objectMapper = ObjectMapperConfig.defaultMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public void close() {
    }

    @Override
    public byte[] serialize(String topic, UserLoggedInEvent data) {
        try {
            if (data == null) {
                return null;
            }
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Could not serialize UserLoggedInEvent", e);
        }
    }
}

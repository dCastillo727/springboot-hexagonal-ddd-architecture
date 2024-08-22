package com.dcastillo.hexagonalarchitecture.layers.infraestructure.configuration.kafka;

import com.dcastillo.hexagonalarchitecture.layers.application.event.authentication.UserLoggedInEvent;
import com.dcastillo.hexagonalarchitecture.layers.infraestructure.adapter.driven.messaging.microservices.kafka.serializer.authenticationevents.UserLoggedInEventSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public KafkaProducer<String, UserLoggedInEvent> userLoggedEventProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, UserLoggedInEventSerializer.class);

        return new KafkaProducer<>(props);
    }
}

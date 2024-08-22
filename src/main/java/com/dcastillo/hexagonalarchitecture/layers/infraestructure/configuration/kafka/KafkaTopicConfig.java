package com.dcastillo.hexagonalarchitecture.layers.infraestructure.configuration.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic usersTopic() {
        return TopicBuilder.name("users-test-topic").build();
    }
}

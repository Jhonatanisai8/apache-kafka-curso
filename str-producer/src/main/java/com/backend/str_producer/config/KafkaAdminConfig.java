package com.backend.str_producer.config;

import java.util.HashMap;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.boot.kafka.autoconfigure.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class KafkaAdminConfig {

    private final KafkaProperties kafkaProperties;

    // SERVIDOR DE ARRANQUE
    @Bean
    public KafkaAdmin kafkaAdmin() {
        var configs = new HashMap<String, Object>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,
                kafkaProperties.getBootstrapServers());
        return new KafkaAdmin(configs);
    }

    // TOPICOS CREADOS EN LA MAQUINA
    @Bean
    public KafkaAdmin.NewTopics topics() {
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name("str-topic")
                        .partitions(2)
                        .replicas(1)
                        .build());
    }

}

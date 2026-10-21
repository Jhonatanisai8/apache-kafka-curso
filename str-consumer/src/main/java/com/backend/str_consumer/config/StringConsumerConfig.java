package com.backend.str_consumer.config;

import java.util.HashMap;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.kafka.autoconfigure.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import lombok.RequiredArgsConstructor;

// Clase de configuración de Spring para el consumidor de mensajes de tipo String
@Configuration
@RequiredArgsConstructor
public class StringConsumerConfig {

    // Inyecta las propiedades de Kafka definidas en application.yml
    private final KafkaProperties kafkaProperties;

    /**
     * ConsumerFactory: Fábrica encargada de crear y configurar las instancias de consumidores de Kafka.
     * Define:
     * 1. BOOTSTRAP_SERVERS: Dirección del broker de Kafka (ej. localhost:9092).
     * 2. KEY_DESERIALIZER: Cómo convertir los bytes que llegan de la clave (Key) a String (StringDeserializer).
     * 3. VALUE_DESERIALIZER: Cómo convertir los bytes que llegan del contenido (Value) a String (StringDeserializer).
     */
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        var configs = new HashMap<String, Object>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(configs);
    }

    /**
     * ConcurrentKafkaListenerContainerFactory: Fábrica de contenedores para los listeners de Kafka.
     * Este componente es el que alimenta y da vida a las anotaciones @KafkaListener en tus servicios.
     * Permite gestionar hilos concurrentes, confirmación de mensajes (acknowledgments), reintentos, etc.
     * Recibe por inyección de dependencias el ConsumerFactory configurado arriba.
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> strContainerFactory(
            ConsumerFactory<String, String> consumerFactory) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}

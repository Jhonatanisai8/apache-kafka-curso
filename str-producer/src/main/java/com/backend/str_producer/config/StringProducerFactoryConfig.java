package com.backend.str_producer.config;

import java.util.HashMap;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.kafka.autoconfigure.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import lombok.RequiredArgsConstructor;

// Clase de configuración de Spring para el productor de mensajes de tipo String
@RequiredArgsConstructor
@Configuration
public class StringProducerFactoryConfig {

    // Inyecta las propiedades de Kafka leídas desde application.yml
    private final KafkaProperties kafkaProperties;

    /**
     * ProducerFactory: Fábrica encargada de crear y configurar las instancias de productores de Kafka.
     * Define:
     * 1. BOOTSTRAP_SERVERS: La dirección del broker de Kafka (ej. localhost:9092).
     * 2. KEY_SERIALIZER: Cómo convertir la clave (Key) del mensaje a bytes (StringSerializer).
     * 3. VALUE_SERIALIZER: Cómo convertir el contenido/valor (Value) del mensaje a bytes (StringSerializer).
     */
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        var configs = new HashMap<String, Object>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configs);
    }

    /**
     * KafkaTemplate: La herramienta de alto nivel que proporciona Spring para enviar mensajes a Kafka.
     * Es el componente que inyectarás en tus servicios o controladores para ejecutar template.send(topico, mensaje).
     * Se apoya internamente en el ProducerFactory que definimos arriba.
     */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}

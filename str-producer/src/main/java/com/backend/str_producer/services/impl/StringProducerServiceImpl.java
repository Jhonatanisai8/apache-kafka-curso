package com.backend.str_producer.services.impl;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.backend.str_producer.services.IStringProducerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class StringProducerServiceImpl implements IStringProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void enviarMensaje(String mensaje) {
        kafkaTemplate.send("str-topic", mensaje)
                .whenComplete((resultado, excepcion) -> {
                    if (excepcion != null) {
                        log.error("Error al enviar el mensaje: {}", excepcion.getMessage());
                        return;
                    }
                    log.info("Mensaje enviado correctamente: {}", resultado.getProducerRecord().value());
                    log.info("Partición: {}", resultado.getRecordMetadata().partition());
                    log.info("Offset: {}", resultado.getRecordMetadata().offset());
                });
    }

}

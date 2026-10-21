package com.backend.str_consumer.listeners;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class StrConsumerListener {

    @KafkaListener(groupId = "group-0", topics = "str-topic", containerFactory = "strContainerFactory")
    public void listener(String mensaje) {
        log.info("LISTENER0 ::: Recibiendo un mensaje {}", mensaje);
    }

    @KafkaListener(groupId = "group-1", topics = "str-topic", containerFactory = "strContainerFactory")
    public void listener1(String mensaje) {
        log.info("LISTENER1 ::: Recibiendo un mensaje {}", mensaje);
    }

    @KafkaListener(groupId = "group-2", topics = "str-topic", containerFactory = "strContainerFactory")
    public void listener2(String mensaje) {
        log.info("LISTENER2 ::: Recibiendo un mensaje {}", mensaje);
    }

    @KafkaListener(groupId = "group-3", topics = "str-topic", containerFactory = "strContainerFactory")
    public void listener3(String mensaje) {
        log.info("LISTENER3 ::: Recibiendo un mensaje {}", mensaje);
    }

    @KafkaListener(groupId = "group-4", topics = "str-topic", containerFactory = "strContainerFactory")
    public void listener4(String mensaje) {
        log.info("LISTENER4 ::: Recibiendo un mensaje {}", mensaje);
    }
}

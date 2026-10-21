package com.backend.str_producer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.str_producer.services.impl.StringProducerServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/producer")
@RequiredArgsConstructor
public class StringProducerController {
    private final StringProducerServiceImpl stringProducerService;

    @PostMapping
    public ResponseEntity<?> enviarMensaje(@RequestBody String mensaje) {
        stringProducerService.enviarMensaje(mensaje);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

package com.co.Challenge.infrastructure.kafkaproducer.mapper;

import com.co.Challenge.domain.dtos.Cliente;
import com.co.Challenge.domain.event.ClienteCreadoEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ClienteMessageMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public ClienteCreadoEvent toEvent(Cliente cliente) {
        return new ClienteCreadoEvent(
                null,
                cliente.getId(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getEdad(),
                cliente.getFechaNacimiento()
        );
    }

    public String toJson(ClienteCreadoEvent event) {
        try {
            return objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializando ClienteCreadoEvent", e);
        }
    }
}
package com.co.Challenge.infrastructure.kafkaproducer.adapters;

import com.co.Challenge.domain.dtos.Cliente;
import com.co.Challenge.domain.event.ClienteCreadoEvent;
import com.co.Challenge.infrastructure.kafkaproducer.mapper.ClienteMessageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class ClienteEventProducer {

    @Value("${spring.kafka.topics.clientes}")
    private String clientesTopic;

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ClienteMessageMapper clienteMessageMapper;

    public Cliente publishClienteCreadoEvent(Cliente cliente) {

        ClienteCreadoEvent event = clienteMessageMapper.toEvent(cliente);
        String clienteId = cliente.getId().toString();
        String payload = clienteMessageMapper.toJson(event);

        kafkaTemplate.send(clientesTopic, clienteId, payload)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("✅ Evento publicado - clienteId: {}, correlationId: {}", clienteId, event.getCorrelationId());
                    } else {
                        log.warn("⚠️ Error publicando clienteId: {}, payload: {}", clienteId, payload);
                        log.error("❌ Error detalle: ", ex);
                    }
                });

        return cliente;
    }
}
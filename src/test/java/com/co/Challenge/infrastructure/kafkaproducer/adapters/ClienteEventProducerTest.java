package com.co.Challenge.infrastructure.kafkaproducer.adapters;


import com.co.Challenge.domain.dtos.Cliente;
import com.co.Challenge.domain.event.ClienteCreadoEvent;
import com.co.Challenge.infrastructure.kafkaproducer.mapper.ClienteMessageMapper;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.*;

class ClienteEventProducerTest {

    @InjectMocks
    private ClienteEventProducer clienteEventProducer;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @Mock
    private ClienteMessageMapper clienteMessageMapper;

    @Captor
    private ArgumentCaptor<String> topicCaptor;

    @Captor
    private ArgumentCaptor<String> keyCaptor;

    @Captor
    private ArgumentCaptor<String> payloadCaptor;

    private static final String TOPIC = "clientes-topic";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Simula la propiedad @Value
        clienteEventProducer = new ClienteEventProducer(kafkaTemplate, clienteMessageMapper);

    }

    @Test
    void publishClienteCreadoEvent_shouldSendMessageToKafka() {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        ClienteCreadoEvent event = new ClienteCreadoEvent();
        event.setCorrelationId("corr-123");

        String payload = "{\"cliente\":\"test\"}";

        when(clienteMessageMapper.toEvent(cliente)).thenReturn(event);
        when(clienteMessageMapper.toJson(event)).thenReturn(payload);

        CompletableFuture completableFuture = CompletableFuture.completedFuture(
                new org.apache.kafka.clients.producer.RecordMetadata(
                        new TopicPartition(TOPIC, 0), 0, 0, 0L, 0L, 0, 0)
        );
        when(kafkaTemplate.send(eq(TOPIC), eq("1"), eq(payload))).thenReturn(completableFuture);

        // Act
        clienteEventProducer.publishClienteCreadoEvent(cliente);

        // Assert
        verify(kafkaTemplate, times(1)).send(eq(TOPIC), eq("1"), eq(payload));
        verify(clienteMessageMapper).toEvent(cliente);
        verify(clienteMessageMapper).toJson(event);
    }
}
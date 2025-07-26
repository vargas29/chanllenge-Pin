package com.co.Challenge.infrastructure.controller;

import com.co.Challenge.application.service.ClienteService;
import com.co.Challenge.domain.dtos.Cliente;
import com.co.Challenge.domain.dtos.EstadisticasDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ClienteControllerTest {

    private ClienteService service;
    private ClienteController controller;

    @BeforeEach
    void setUp() {
        service = mock(ClienteService.class);
        controller = new ClienteController(service);
    }

    @Test
    void crear_deberiaRetornarClienteRegistrado() {
        Cliente input = new Cliente(1L, "Juan", "Pérez", 30, LocalDate.of(1994, 1, 1));
        when(service.registrarCliente(input)).thenReturn(input);

        ResponseEntity<Cliente> response = controller.crear(input);

        assertThat(response.getBody()).isEqualTo(input);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        verify(service).registrarCliente(input);
    }

    @Test
    void listar_deberiaRetornarListaDeClientes() {
        List<Cliente> lista = Arrays.asList(
                new Cliente(1L, "Ana", "Gómez", 25, LocalDate.of(1999, 1, 1)),
                new Cliente(2L, "Luis", "Martínez", 40, LocalDate.of(1984, 1, 1))
        );
        when(service.listarClientes()).thenReturn(lista);

        ResponseEntity<List<Cliente>> response = controller.listar();

        assertThat(response.getBody()).isEqualTo(lista);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        verify(service).listarClientes();
    }

    @Test
    void metricas_deberiaRetornarEstadisticas() {
        EstadisticasDTO estadisticas = new EstadisticasDTO(35.0, 7.5);
        when(service.calcularEstadisticas()).thenReturn(estadisticas);

        ResponseEntity<EstadisticasDTO> response = controller.metricas();

        assertThat(response.getBody()).isEqualTo(estadisticas);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        verify(service).calcularEstadisticas();
    }

    @Test
    void obtener_deberiaRetornarClientePorId() {
        Cliente cliente = new Cliente(1L, "Carlos", "Ramírez", 28, LocalDate.of(1996, 1, 1));
        when(service.obtenerClientePorId(1L)).thenReturn(cliente);

        ResponseEntity<Cliente> response = controller.obtener(1L);

        assertThat(response.getBody()).isEqualTo(cliente);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        verify(service).obtenerClientePorId(1L);
    }
}
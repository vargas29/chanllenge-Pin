package com.co.Challenge.application.service;

import com.co.Challenge.domain.dtos.Cliente;
import com.co.Challenge.domain.dtos.EstadisticasDTO;
import com.co.Challenge.domain.exceptions.ClienteInvalidoException;
import com.co.Challenge.domain.exceptions.ClienteNotFoundException;
import com.co.Challenge.domain.exceptions.SinClientesRegistradosException;
import com.co.Challenge.domain.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    private ClienteRepository repository;
    private ClienteService service;

    @BeforeEach
    void setUp() {
        repository = mock(ClienteRepository.class);
        service = new ClienteService(repository);
    }

    @Test
    void registrarClienteValido_deberiaGuardarYRetornarCliente() {
        Cliente cliente = new Cliente(null, "Juan", "Pérez", 30, LocalDate.of(1993, 1, 1));
        when(repository.guardar(cliente)).thenReturn(cliente);

        Cliente resultado = service.registrarCliente(cliente);

        assertThat(resultado).isEqualTo(cliente);
        verify(repository).guardar(cliente);
    }

    @Test
    void registrarClienteInvalido_deberiaLanzarExcepcion() {
        Cliente cliente = new Cliente(null, "", "Apellido", -10, null);

        assertThatThrownBy(() -> service.registrarCliente(cliente))
                .isInstanceOf(ClienteInvalidoException.class)
                .hasMessageContaining("inválidos");
    }

    @Test
    void listarClientes_deberiaRetornarLista() {
        List<Cliente> clientes = List.of(new Cliente(1L, "Ana", "López", 25, LocalDate.of(1998, 5, 20)));
        when(repository.obtenerTodos()).thenReturn(clientes);

        List<Cliente> resultado = service.listarClientes();

        assertThat(resultado).hasSize(1).containsExactly(clientes.get(0));
        verify(repository).obtenerTodos();
    }

    @Test
    void obtenerClientePorIdExistente_deberiaRetornarCliente() {
        Cliente cliente = new Cliente(1L, "Carlos", "Mendez", 40, LocalDate.of(1983, 3, 3));
        when(repository.obtenerPorId(1L)).thenReturn(Optional.of(cliente));

        Cliente resultado = service.obtenerClientePorId(1L);

        assertThat(resultado).isEqualTo(cliente);
    }

    @Test
    void obtenerClientePorIdInexistente_deberiaLanzarExcepcion() {
        when(repository.obtenerPorId(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.obtenerClientePorId(99L))
                .isInstanceOf(ClienteNotFoundException.class)
                .hasMessageContaining("Cliente no encontrado");
    }

    @Test
    void calcularEstadisticas_conClientesValido_deberiaRetornarMetrica() {
        List<Cliente> clientes = List.of(
                new Cliente(1L, "A", "B", 20, LocalDate.of(2003, 1, 1)),
                new Cliente(2L, "C", "D", 30, LocalDate.of(1993, 1, 1))
        );
        when(repository.obtenerTodos()).thenReturn(clientes);

        EstadisticasDTO stats = service.calcularEstadisticas();

        assertThat(stats.getPromedioEdad()).isEqualTo(25.0);
        assertThat(stats.getDesviacionEstandarEdad()).isGreaterThan(0.0);
    }

    @Test
    void calcularEstadisticas_sinClientes_deberiaLanzarExcepcion() {
        when(repository.obtenerTodos()).thenReturn(List.of());

        assertThatThrownBy(() -> service.calcularEstadisticas())
                .isInstanceOf(SinClientesRegistradosException.class)
                .hasMessageContaining("No hay clientes registrados");
    }
}

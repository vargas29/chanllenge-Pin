package com.co.Challenge.application.service;

import com.co.Challenge.domain.dtos.Cliente;
import com.co.Challenge.domain.dtos.EstadisticasDTO;
import com.co.Challenge.domain.exceptions.ClienteNotFoundException;
import com.co.Challenge.domain.exceptions.ClienteInvalidoException;
import com.co.Challenge.domain.exceptions.SinClientesRegistradosException;
import com.co.Challenge.domain.repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente registrarCliente(Cliente cliente) {
        log.info("Registrando nuevo cliente: {}", cliente);

        if (cliente.getNombre() == null || cliente.getNombre().isBlank() ||
                cliente.getApellido() == null || cliente.getApellido().isBlank() ||
                cliente.getEdad() == null || cliente.getEdad() < 0 ||
                cliente.getFechaNacimiento() == null) {
            throw new ClienteInvalidoException("Los datos del cliente son inválidos o incompletos");
        }

        return repository.guardar(cliente);
    }

    public List<Cliente> listarClientes() {
        log.info("Listando todos los clientes");
        return repository.obtenerTodos();
    }

    public Cliente obtenerClientePorId(Long id) {
        log.info("Buscando cliente con ID: {}", id);
        return repository.obtenerPorId(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente no encontrado con ID: " + id));
    }
    public EstadisticasDTO calcularEstadisticas() {
        log.info("Calculando métricas de edad de los clientes");
        List<Cliente> clientes = repository.obtenerTodos();

        if (clientes.isEmpty()) {
            throw new SinClientesRegistradosException("No hay clientes registrados para calcular estadísticas");
        }

        IntSummaryStatistics stats = clientes.stream()
                .mapToInt(Cliente::getEdad)
                .summaryStatistics();

        double promedio = stats.getAverage();
        double desviacion = Math.sqrt(clientes.stream()
                .mapToDouble(c -> Math.pow(c.getEdad() - promedio, 2))
                .average().orElse(0));

        return new EstadisticasDTO(promedio, desviacion);
    }
}

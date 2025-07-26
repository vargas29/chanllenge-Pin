
package com.co.Challenge.infrastructure.controller;

import com.co.Challenge.application.service.ClienteService;
import com.co.Challenge.domain.dtos.Cliente;
import com.co.Challenge.domain.dtos.EstadisticasDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @Slf4j
    @RestController
    @RequestMapping("/clientes")
    public class ClienteController {

        private final ClienteService service;

        public ClienteController(ClienteService service) {
            this.service = service;
        }

        @Operation(summary = "Registrar nuevo cliente", description = "Crea un nuevo cliente con nombre, apellido, edad y fecha de nacimiento")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Cliente creado exitosamente",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
                @ApiResponse(responseCode = "400", description = "Datos inválidos",
                        content = @Content(mediaType = "application/json")),
                @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                        content = @Content(mediaType = "application/json"))
        })
        @PostMapping
        public ResponseEntity<Cliente> crear(@RequestBody Cliente cliente) {
            log.info("Creando cliente: {}", cliente);
            return ResponseEntity.ok(service.registrarCliente(cliente));
        }

        @Operation(summary = "Listar todos los clientes", description = "Devuelve una lista de todos los clientes registrados")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Lista de clientes",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
                @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                        content = @Content(mediaType = "application/json"))
        })
        @GetMapping
        public ResponseEntity<List<Cliente>> listar() {
            log.info("Listando todos los clientes");
            return ResponseEntity.ok(service.listarClientes());
        }

        @Operation(summary = "Obtener métricas de edad", description = "Devuelve el promedio y la desviación estándar de las edades de los clientes")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Métricas calculadas",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstadisticasDTO.class))),
                @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                        content = @Content(mediaType = "application/json"))
        })
        @GetMapping("/metricas")
        public ResponseEntity<EstadisticasDTO> metricas() {
            log.info("Calculando métricas de edad");
            return ResponseEntity.ok(service.calcularEstadisticas());
        }

        @Operation(summary = "Obtener cliente por ID", description = "Devuelve los datos del cliente con la fecha estimada de esperanza de vida")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
                @ApiResponse(responseCode = "404", description = "Cliente no encontrado",
                        content = @Content(mediaType = "application/json"))
        })
        @GetMapping("/{id}")
        public ResponseEntity<Cliente> obtener(
                @Parameter(description = "ID del cliente", example = "1", required = true)
                @PathVariable Long id) {
            log.info("Buscando cliente con ID: {}", id);
            Cliente cliente = service.obtenerClientePorId(id);
            return ResponseEntity.ok(cliente);
        }
    }



package com.co.Challenge.domain.event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteCreadoEvent {
    private String correlationId = UUID.randomUUID().toString();
    private Long id;
    private String nombre;
    private String apellido;
    private Integer edad;
    private LocalDate fechaNacimiento;
}
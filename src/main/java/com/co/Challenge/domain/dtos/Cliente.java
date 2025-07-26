package com.co.Challenge.domain.dtos;


import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    private Long id;
    private String nombre;
    private String apellido;
    private Integer edad;
    private LocalDate fechaNacimiento;

    public LocalDate getFechaEsperanzaVida() {
        return fechaNacimiento.plusYears(80); // lógica simple
    }
}
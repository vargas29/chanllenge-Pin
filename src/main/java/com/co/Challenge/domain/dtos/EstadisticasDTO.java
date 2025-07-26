package com.co.Challenge.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EstadisticasDTO {
    private double promedioEdad;
    private double desviacionEstandarEdad;

    public EstadisticasDTO(double promedio, double desviacion) {
        this.promedioEdad = promedio;
        this.desviacionEstandarEdad = desviacion;
    }


}
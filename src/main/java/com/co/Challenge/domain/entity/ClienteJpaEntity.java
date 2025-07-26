package com.co.Challenge.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Entity
@Setter
@Getter
public class ClienteJpaEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String apellido;
    private Integer edad;
    private LocalDate fechaNacimiento;
}
package com.example.lab8.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private LocalDate fecha;
    private String categoria;
    @Column(name = "capacidad_maxima")
    private int capacidadMaxima;
    @Column(name = "numero_reservas_actuales", columnDefinition = "integer default 0")
    private int numeroReservasActuales = 0;

}

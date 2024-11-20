package com.example.lab8.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "evento_id")
    private Evento evento;
    private String nombrePersona;
    private String correoPersona;
    private int numeroCupos;

}

package com.example.lab8.controller;

import com.example.lab8.model.Evento;
import com.example.lab8.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/eventos")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @GetMapping
    public List<Evento> listarEventos(@RequestParam(required = false) LocalDate fecha) {
    List<Evento> eventos = eventoService.listarEventos(fecha);
        if (fecha != null) {
            eventos = eventos.stream()
                    .filter(evento -> evento.getFecha().equals(fecha))
                    .collect(Collectors.toList());
        }
        return eventos.stream()
                .sorted(Comparator.comparing(Evento::getFecha))
                .collect(Collectors.toList());
    }

@PostMapping
public Evento crearEvento(@RequestBody Evento evento) {
    if (evento.getFecha().isBefore(LocalDate.now())) {
        throw new IllegalArgumentException("La fecha del evento no puede ser anterior a la fecha actual");
    }
    evento.setNumeroReservasActuales(0);
    return eventoService.crearEvento(evento);
}


}

package com.example.lab8.service;


import com.example.lab8.model.Evento;
import com.example.lab8.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> listarEventos(LocalDate fecha){
        if(fecha != null){
            return eventoRepository.findByFecha(fecha);
        }
        return eventoRepository.findAll();
    }

    public Evento crearEvento(Evento evento){
        if(evento.getFecha().isBefore((LocalDate.now()))){
            throw new IllegalArgumentException("La fecha del evento no puede ser anterior a la fecha actual");
        }
        evento.setNumeroReservasActuales(0);
        return eventoRepository.save(evento);
    }

}

package com.example.lab8.service;

import com.example.lab8.model.Evento;
import com.example.lab8.model.Reserva;
import com.example.lab8.repository.EventoRepository;
import com.example.lab8.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private EventoRepository eventoRepository;

    public Reserva crearReserva(Reserva reserva) {
        Evento evento = eventoRepository.findById(reserva.getEvento().getId())
                .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado"));

        if (evento.getNumeroReservasActuales() + reserva.getNumeroCupos() > evento.getCapacidadMaxima()) {
            throw new IllegalArgumentException("No hay suficientes cupos disponibles para este evento");
        }

        evento.setNumeroReservasActuales(evento.getNumeroReservasActuales() + reserva.getNumeroCupos());
        eventoRepository.save(evento);

        return reservaRepository.save(reserva);
    }

    public void cancelarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada"));

        Evento evento = eventoRepository.findById(reserva.getEvento().getId())
                .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado"));

        evento.setNumeroReservasActuales(evento.getNumeroReservasActuales() - reserva.getNumeroCupos());
        eventoRepository.save(evento);

        reservaRepository.deleteById(id);
    }
}
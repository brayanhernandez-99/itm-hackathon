package com.co.hackathon.itm_hackathon_web.services;

import com.co.hackathon.itm_hackathon_web.models.Evento;
import com.co.hackathon.itm_hackathon_web.repositories.EventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Servicio para la gestión de los eventos.
 */
@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public void guardarEvento(Evento evento) {
        Objects.requireNonNull(evento, "El evento no puede ser nulo");
        eventoRepository.save(evento);
    }

    public List<Evento> obtenerTodosLosEventos() {
        return eventoRepository.findAll();
    }

    public Evento obtenerEventoPorId(int id) {
        return eventoRepository.findByIdOrThrow(id);
    }

    public void eliminarEvento(int id) {
        Evento evento = eventoRepository.findByIdOrThrow(id);
        eventoRepository.delete(evento);
    }
}

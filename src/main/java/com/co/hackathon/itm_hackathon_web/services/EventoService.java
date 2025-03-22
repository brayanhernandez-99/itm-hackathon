package com.co.hackathon.itm_hackathon_web.services;

import com.co.hackathon.itm_hackathon_web.models.Evento;
import com.co.hackathon.itm_hackathon_web.repositories.EventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public List<Evento> obtenerTodosLosEventos() {
        return eventoRepository.findAll();
    }

    public Evento obtenerEventoPorId(int id) {
        Optional<Evento> evento = eventoRepository.findById(id);
        return evento.orElseThrow(() -> new RuntimeException("Evento no encontrado"));
    }

    public void guardarEvento(Evento evento) {
        eventoRepository.save(evento);
    }

    public void actualizarEvento(int id, Evento evento) {
        if (!eventoRepository.existsById(id)) {
            throw new RuntimeException("Evento no encontrado");
        }
        evento.setId(id);
        eventoRepository.save(evento);
    }

    public void eliminarEvento(int id) {
        if (!eventoRepository.existsById(id)) {
            throw new RuntimeException("Evento no encontrado");
        }
        eventoRepository.deleteById(id);
    }
}

package com.co.hackathon.itm_hackathon_web.services;

import com.co.hackathon.itm_hackathon_web.models.Evento;
import com.co.hackathon.itm_hackathon_web.repositories.EventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de la gestión de los eventos en el sistema.
 */
@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    /**
     * Constructor que inyecta el repositorio de eventos.
     *
     * @param eventoRepository Repositorio para la gestión de eventos.
     */
    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    /**
     * Guarda un nuevo evento en la base de datos.
     *
     * @param evento Objeto Evento a guardar.
     */
    public void guardarEvento(Evento evento) {
        eventoRepository.save(evento);
    }

    /**
     * Obtiene todos los eventos registrados en la base de datos.
     *
     * @return Lista de todos los eventos.
     */
    public List<Evento> obtenerTodosLosEventos() {
        return eventoRepository.findAll();
    }

    /**
     * Obtiene un evento por su ID.
     *
     * @param id ID del evento.
     * @return Evento correspondiente al ID proporcionado.
     * @throws RuntimeException Si el evento no se encuentra.
     */
    public Evento obtenerEventoPorId(int id) {
        Optional<Evento> evento = eventoRepository.findById(id);
        return evento.orElseThrow(() -> new RuntimeException("Evento no encontrado"));
    }

    /**
     * Elimina un evento de la base de datos si existe.
     *
     * @param id ID del evento a eliminar.
     * @throws RuntimeException Si el evento no se encuentra.
     */
    public void eliminarEvento(int id) {
        if (!eventoRepository.existsById(id)) {
            throw new RuntimeException("Evento no encontrado");
        }
        eventoRepository.deleteById(id);
    }
}

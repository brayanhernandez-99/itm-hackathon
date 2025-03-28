package com.co.hackathon.itm_hackathon_web.services;

import com.co.hackathon.itm_hackathon_web.models.AsistenciaAcompanante;
import com.co.hackathon.itm_hackathon_web.repositories.AsistenciaAcompananteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Servicio para la gestión de la asistencia de acompañantes.
 */
@Service
public class AsistenciaAcompananteService {

    private final AsistenciaAcompananteRepository asistenciaAcompananteRepository;

    public AsistenciaAcompananteService(AsistenciaAcompananteRepository asistenciaAcompananteRepository) {
        this.asistenciaAcompananteRepository = asistenciaAcompananteRepository;
    }

    public List<AsistenciaAcompanante> obtenerTodasLasAsistencias() {
        return asistenciaAcompananteRepository.findAll();
    }

    public void guardarAsistenciaAcompanante(AsistenciaAcompanante asistencia) {
        Objects.requireNonNull(asistencia, "La asistencia de acompañante no puede ser nula");
        asistenciaAcompananteRepository.save(asistencia);
    }

    public List<AsistenciaAcompanante> obtenerAsistenciasPorAcompanante(int acompananteId) {
        return asistenciaAcompananteRepository.findByAcompananteId(acompananteId);
    }

    public List<AsistenciaAcompanante> obtenerAsistenciasPorEvento(int eventoId) {
        return asistenciaAcompananteRepository.findByEventoId(eventoId);
    }

    public AsistenciaAcompanante obtenerAsistenciaPorId(int id) {
        return asistenciaAcompananteRepository.findByIdOrThrow(id);
    }

    public boolean eliminarAsistenciaAcompanante(int id) {
        if (asistenciaAcompananteRepository.existsById(id)) {
            AsistenciaAcompanante asistencia = asistenciaAcompananteRepository.findByIdOrThrow(id);
            asistenciaAcompananteRepository.delete(asistencia);
            return true;
        }
        return false;
    }
}

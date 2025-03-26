package com.co.hackathon.itm_hackathon_web.services;

import com.co.hackathon.itm_hackathon_web.models.Asistencia;
import com.co.hackathon.itm_hackathon_web.repositories.AsistenciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Servicio para la gestión de las asistencias.
 */
@Service
public class AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;

    public AsistenciaService(AsistenciaRepository asistenciaRepository) {
        this.asistenciaRepository = asistenciaRepository;
    }

    public void guardarAsistencia(Asistencia asistencia) {
        Objects.requireNonNull(asistencia, "La asistencia no puede ser nula");
        asistenciaRepository.save(asistencia);
    }

    public List<Asistencia> obtenerTodasLasAsistencias() {
        return asistenciaRepository.findAll();
    }

    public List<Asistencia> obtenerAsistenciasPorEvento(int eventoId) {
        return asistenciaRepository.findByEventoId(eventoId);
    }

    public List<Asistencia> obtenerAsistenciasPorMiembro(int miembroId) {
        return asistenciaRepository.findByMiembroId(miembroId);
    }

    public Asistencia obtenerAsistenciaPorId(int id) {
        return asistenciaRepository.findByIdOrThrow(id);
    }

    public boolean eliminarAsistencia(int id) {
        if (asistenciaRepository.existsById(id)) {
            Asistencia asistencia = asistenciaRepository.findByIdOrThrow(id);
            asistenciaRepository.delete(asistencia);
            return true;
        }
        return false;
    }
}

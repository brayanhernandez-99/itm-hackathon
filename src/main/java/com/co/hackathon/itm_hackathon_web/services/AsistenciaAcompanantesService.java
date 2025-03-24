package com.co.hackathon.itm_hackathon_web.services;

import com.co.hackathon.itm_hackathon_web.models.AsistenciaAcompanantes;
import com.co.hackathon.itm_hackathon_web.repositories.AsistenciaAcompanantesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsistenciaAcompanantesService {
    private final AsistenciaAcompanantesRepository asistenciaAcompanantesRepository;

    public AsistenciaAcompanantesService(AsistenciaAcompanantesRepository asistenciaAcompanantesRepository) {
        this.asistenciaAcompanantesRepository = asistenciaAcompanantesRepository;
    }

    // 🔹 Obtener todas las asistencias
    public List<AsistenciaAcompanantes> obtenerTodasLasAsistencias() {
        return asistenciaAcompanantesRepository.findAll();
    }

    // 🔹 Guardar nueva asistencia
    public void guardarAsistenciaAcompanante(AsistenciaAcompanantes asistencia) {
        asistenciaAcompanantesRepository.save(asistencia);  // Persistir en la base de datos
    }

    // 🔹 Obtener asistencias por Acompañante
    public List<AsistenciaAcompanantes> obtenerAsistenciasPorAcompanante(int acompananteId) {
        return asistenciaAcompanantesRepository.findByAcompananteId(acompananteId);
    }

    // 🔹 Obtener asistencia por ID
    public AsistenciaAcompanantes obtenerAsistenciaPorId(int id) {
        Optional<AsistenciaAcompanantes> asistencia = asistenciaAcompanantesRepository.findById(id);
        return asistencia.orElse(null);  // Retorna null si no se encuentra
    }

    // 🔹 Eliminar asistencia por ID
    public boolean eliminarAsistenciaAcompanante(int id) {
        if (asistenciaAcompanantesRepository.existsById(id)) {
            asistenciaAcompanantesRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

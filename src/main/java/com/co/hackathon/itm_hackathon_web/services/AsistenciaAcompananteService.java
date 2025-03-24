package com.co.hackathon.itm_hackathon_web.services;

import com.co.hackathon.itm_hackathon_web.models.AsistenciaAcompanantes;
import com.co.hackathon.itm_hackathon_web.repositories.AsistenciaAcompananteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsistenciaAcompananteService {
    private final AsistenciaAcompananteRepository asistenciaAcompananteRepository;

    public AsistenciaAcompananteService(AsistenciaAcompananteRepository asistenciaAcompananteRepository) {
        this.asistenciaAcompananteRepository = asistenciaAcompananteRepository;
    }

    // 🔹 Obtener todas las asistencias
    public List<AsistenciaAcompanantes> obtenerTodasLasAsistencias() {
        return asistenciaAcompananteRepository.findAll();
    }

    // 🔹 Guardar nueva asistencia
    public void guardarAsistenciaAcompanante(AsistenciaAcompanantes asistencia) {
        asistenciaAcompananteRepository.save(asistencia);  // Persistir en la base de datos
    }

    // 🔹 Obtener asistencias por Acompañante
    public List<AsistenciaAcompanantes> obtenerAsistenciasPorAcompanante(int acompananteId) {
        return asistenciaAcompananteRepository.findByAcompananteId(acompananteId);
    }

    // 🔹 Obtener asistencia por ID
    public AsistenciaAcompanantes obtenerAsistenciaPorId(int id) {
        Optional<AsistenciaAcompanantes> asistencia = asistenciaAcompananteRepository.findById(id);
        return asistencia.orElse(null);  // Retorna null si no se encuentra
    }

    // 🔹 Eliminar asistencia por ID
    public boolean eliminarAsistenciaAcompanante(int id) {
        if (asistenciaAcompananteRepository.existsById(id)) {
            asistenciaAcompananteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

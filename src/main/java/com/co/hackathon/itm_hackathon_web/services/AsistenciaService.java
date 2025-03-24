package com.co.hackathon.itm_hackathon_web.services;

import com.co.hackathon.itm_hackathon_web.models.Asistencia;
import com.co.hackathon.itm_hackathon_web.repositories.AsistenciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;

    public AsistenciaService(AsistenciaRepository asistenciaRepository) {
        this.asistenciaRepository = asistenciaRepository;
    }

    // 🔹 Registrar una nueva asistencia
    public void guardarAsistencia(Asistencia asistencia) {
        asistenciaRepository.save(asistencia);
    }

    // 🔹 Obtener todas las asistencias
    public List<Asistencia> obtenerTodasLasAsistencias() {
        return asistenciaRepository.findAll();
    }

    // 🔹 Obtener asistencias por evento
    public List<Asistencia> obtenerAsistenciasPorEvento(int eventoId) {
        return asistenciaRepository.findByEventoId(eventoId);
    }

    // 🔹 Obtener asistencias por miembro
    public List<Asistencia> obtenerAsistenciasPorMiembro(int miembroId) {
        return asistenciaRepository.findByMiembroId(miembroId);
    }

    // 🔹 Obtener asistencia por ID
    public Asistencia obtenerAsistenciaPorId(int id) {
        return asistenciaRepository.findById(id).orElse(null);
    }

    // 🔹 Eliminar una asistencia con validación
    public boolean eliminarAsistencia(int id) {
        if (asistenciaRepository.existsById(id)) {
            asistenciaRepository.deleteById(id);
            return true;
        }
        return false;
    }

}

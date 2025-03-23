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

    // 🔹 Registrar una nueva asistencia
    public void registrarAsistencia(Asistencia asistencia) {
        asistenciaRepository.save(asistencia);
    }

    // 🔹 Eliminar una asistencia con validación
    public boolean eliminarAsistencia(int id) {
        if (asistenciaRepository.existsById(id)) {
            asistenciaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // 🔹 Validar si una asistencia existe antes de eliminar
    public boolean existeAsistencia(int id) {
        return asistenciaRepository.existsById(id);
    }
}

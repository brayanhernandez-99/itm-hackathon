package com.co.hackathon.itm_hackathon_web.services;

import com.co.hackathon.itm_hackathon_web.models.Asistencia;
import com.co.hackathon.itm_hackathon_web.repositories.AsistenciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio encargado de la gestión de las asistencias en el sistema.
 */
@Service
public class AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;

    /**
     * Constructor que inyecta el repositorio de asistencia.
     *
     * @param asistenciaRepository Repositorio para la gestión de asistencias.
     */
    public AsistenciaService(AsistenciaRepository asistenciaRepository) {
        this.asistenciaRepository = asistenciaRepository;
    }

    /**
     * Guarda una nueva asistencia en la base de datos.
     *
     * @param asistencia Objeto de asistencia a guardar.
     */
    public void guardarAsistencia(Asistencia asistencia) {
        asistenciaRepository.save(asistencia);
    }

    /**
     * Obtiene todas las asistencias registradas en la base de datos.
     *
     * @return Lista de todas las asistencias.
     */
    public List<Asistencia> obtenerTodasLasAsistencias() {
        return asistenciaRepository.findAll();
    }

    /**
     * Obtiene todas las asistencias asociadas a un evento específico.
     *
     * @param eventoId ID del evento.
     * @return Lista de asistencias del evento.
     */
    public List<Asistencia> obtenerAsistenciasPorEvento(int eventoId) {
        return asistenciaRepository.findByEventoId(eventoId);
    }

    /**
     * Obtiene todas las asistencias asociadas a un miembro específico.
     *
     * @param miembroId ID del miembro.
     * @return Lista de asistencias del miembro.
     */
    public List<Asistencia> obtenerAsistenciasPorMiembro(int miembroId) {
        return asistenciaRepository.findByMiembroId(miembroId);
    }

    /**
     * Obtiene una asistencia por su ID.
     *
     * @param id ID de la asistencia.
     * @return Objeto Asistencia si se encuentra, de lo contrario null.
     */
    public Asistencia obtenerAsistenciaPorId(int id) {
        return asistenciaRepository.findById(id).orElse(null);
    }

    /**
     * Elimina una asistencia de la base de datos si existe.
     *
     * @param id ID de la asistencia a eliminar.
     * @return true si la asistencia fue eliminada, false si no existe.
     */
    public boolean eliminarAsistencia(int id) {
        if (asistenciaRepository.existsById(id)) {
            asistenciaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

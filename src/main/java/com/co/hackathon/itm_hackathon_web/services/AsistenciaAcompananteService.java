package com.co.hackathon.itm_hackathon_web.services;

import com.co.hackathon.itm_hackathon_web.models.AsistenciaAcompanante;
import com.co.hackathon.itm_hackathon_web.repositories.AsistenciaAcompananteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar la lógica de negocio relacionada con la asistencia de acompañantes.
 */
@Service
public class AsistenciaAcompananteService {
    private final AsistenciaAcompananteRepository asistenciaAcompananteRepository;

    /**
     * Constructor que inyecta el repositorio de asistencia de acompañantes.
     * @param asistenciaAcompananteRepository Repositorio de asistencia de acompañantes
     */
    public AsistenciaAcompananteService(AsistenciaAcompananteRepository asistenciaAcompananteRepository) {
        this.asistenciaAcompananteRepository = asistenciaAcompananteRepository;
    }

    /**
     * Obtiene todas las asistencias de acompañantes registradas en la base de datos.
     * @return Lista de todas las asistencias de acompañantes
     */
    public List<AsistenciaAcompanante> obtenerTodasLasAsistencias() {
        return asistenciaAcompananteRepository.findAll();
    }

    /**
     * Guarda una nueva asistencia de acompañante en la base de datos.
     * @param asistencia Objeto de asistencia de acompañante a guardar
     */
    public void guardarAsistenciaAcompanante(AsistenciaAcompanante asistencia) {
        asistenciaAcompananteRepository.save(asistencia);
    }

    /**
     * Obtiene todas las asistencias de un acompañante específico según su ID.
     * @param acompananteId ID del acompañante
     * @return Lista de asistencias del acompañante especificado
     */
    public List<AsistenciaAcompanante> obtenerAsistenciasPorAcompanante(int acompananteId) {
        return asistenciaAcompananteRepository.findByAcompananteId(acompananteId);
    }

    /**
     * Busca una asistencia de acompañante específica por su ID.
     * @param id ID de la asistencia
     * @return Objeto de asistencia si se encuentra, de lo contrario retorna null
     */
    public AsistenciaAcompanante obtenerAsistenciaPorId(int id) {
        Optional<AsistenciaAcompanante> asistencia = asistenciaAcompananteRepository.findById(id);
        return asistencia.orElse(null);
    }

    /**
     * Elimina una asistencia de acompañante por su ID.
     * @param id ID de la asistencia a eliminar
     * @return true si la asistencia fue eliminada, false si no se encontró
     */
    public boolean eliminarAsistenciaAcompanante(int id) {
        if (asistenciaAcompananteRepository.existsById(id)) {
            asistenciaAcompananteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

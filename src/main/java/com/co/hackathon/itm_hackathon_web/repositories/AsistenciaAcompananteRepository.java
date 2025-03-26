package com.co.hackathon.itm_hackathon_web.repositories;

import com.co.hackathon.itm_hackathon_web.models.AsistenciaAcompanante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la entidad {@link AsistenciaAcompanante}.
 */
@Repository
public interface AsistenciaAcompananteRepository extends JpaRepository<AsistenciaAcompanante, Integer> {

    /**
     * Obtiene todas las asistencias de acompañante asociadas a un determinado acompañante.
     *
     * @param acompananteId Identificador del acompañante.
     * @return Lista de asistencias.
     */
    List<AsistenciaAcompanante> findByAcompananteId(int acompananteId);

    /**
     * Busca una asistencia de acompañante por su ID o lanza una excepción si no se encuentra.
     *
     * @param id Identificador de la asistencia.
     * @return AsistenciaAcompanante encontrada.
     * @throws RuntimeException si no se encuentra.
     */
    default AsistenciaAcompanante findByIdOrThrow(int id) {
        return findById(id).orElseThrow(() -> new RuntimeException("Asistencia de acompañante no encontrada con id: " + id));
    }
}

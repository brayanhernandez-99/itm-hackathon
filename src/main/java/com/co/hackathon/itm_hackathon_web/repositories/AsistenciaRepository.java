package com.co.hackathon.itm_hackathon_web.repositories;

import com.co.hackathon.itm_hackathon_web.models.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la entidad {@link Asistencia}.
 */
@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Integer> {

    /**
     * Obtiene todas las asistencias asociadas a un evento.
     *
     * @param eventoId Identificador del evento.
     * @return Lista de asistencias del evento.
     */
    List<Asistencia> findByEventoId(int eventoId);

    /**
     * Obtiene todas las asistencias asociadas a un miembro.
     *
     * @param miembroId Identificador del miembro.
     * @return Lista de asistencias del miembro.
     */
    List<Asistencia> findByMiembroId(int miembroId);

    /**
     * Busca una asistencia por su ID o lanza una excepción si no se encuentra.
     *
     * @param id Identificador de la asistencia.
     * @return Asistencia encontrada.
     * @throws RuntimeException si no se encuentra.
     */
    default Asistencia findByIdOrThrow(int id) {
        return findById(id).orElseThrow(() -> new RuntimeException("Asistencia no encontrada con id: " + id));
    }
}

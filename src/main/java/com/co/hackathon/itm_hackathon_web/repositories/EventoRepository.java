package com.co.hackathon.itm_hackathon_web.repositories;

import com.co.hackathon.itm_hackathon_web.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad {@link Evento}.
 */
@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

    /**
     * Busca un evento por su ID o lanza una excepción si no se encuentra.
     *
     * @param id Identificador del evento.
     * @return Evento encontrado.
     * @throws RuntimeException si no se encuentra.
     */
    default Evento findByIdOrThrow(int id) {
        return findById(id).orElseThrow(() -> new RuntimeException("Evento no encontrado con id: " + id));
    }
}

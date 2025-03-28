package com.co.hackathon.itm_hackathon_web.repositories;

import com.co.hackathon.itm_hackathon_web.models.Acompanante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad {@link Acompanante}.
 */
@Repository
public interface AcompananteRepository extends JpaRepository<Acompanante, Integer> {

    /**
     * Busca un acompañante por su ID o lanza una excepción si no se encuentra.
     *
     * @param id Identificador del acompañante.
     * @return Acompanante encontrado.
     * @throws RuntimeException si no se encuentra.
     */
    default Acompanante findByIdOrThrow(int id) {
        return findById(id).orElseThrow(() -> new RuntimeException("Acompanante no encontrado con id: " + id));
    }
}

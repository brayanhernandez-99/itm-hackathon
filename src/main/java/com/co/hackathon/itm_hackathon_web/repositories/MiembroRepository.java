package com.co.hackathon.itm_hackathon_web.repositories;

import com.co.hackathon.itm_hackathon_web.models.Miembro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad {@link Miembro}.
 */
@Repository
public interface MiembroRepository extends JpaRepository<Miembro, Integer> {

    /**
     * Busca un miembro por su ID o lanza una excepción si no se encuentra.
     *
     * @param id Identificador del miembro.
     * @return Miembro encontrado.
     * @throws RuntimeException si no se encuentra.
     */
    default Miembro findByIdOrThrow(int id) {
        return findById(id).orElseThrow(() -> new RuntimeException("Miembro no encontrado con id: " + id));
    }
}

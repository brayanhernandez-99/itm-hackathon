package com.co.hackathon.itm_hackathon_web.repositories;

import com.co.hackathon.itm_hackathon_web.models.AsistenciaAcompanantes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsistenciaAcompanantesRepository extends JpaRepository<AsistenciaAcompanantes, Integer> {
    List<AsistenciaAcompanantes> findByEventoId(int eventoId);
    List<AsistenciaAcompanantes> findByAcompananteId(int acompananteId);
}
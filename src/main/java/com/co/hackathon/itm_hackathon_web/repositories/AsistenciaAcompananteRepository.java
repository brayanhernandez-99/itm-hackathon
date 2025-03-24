package com.co.hackathon.itm_hackathon_web.repositories;

import com.co.hackathon.itm_hackathon_web.models.AsistenciaAcompanante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsistenciaAcompananteRepository extends JpaRepository<AsistenciaAcompanante, Integer> {
    List<AsistenciaAcompanante> findByEventoId(int eventoId);
    List<AsistenciaAcompanante> findByAcompananteId(int acompananteId);
}
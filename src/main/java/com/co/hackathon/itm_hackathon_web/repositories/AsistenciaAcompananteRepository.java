package com.co.hackathon.itm_hackathon_web.repositories;

import com.co.hackathon.itm_hackathon_web.models.AsistenciaAcompanante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsistenciaAcompananteRepository extends JpaRepository<AsistenciaAcompanante, Integer> {
    List<AsistenciaAcompanante> findByAcompananteId(int acompananteId);
}

package com.co.hackathon.itm_hackathon_web.repositories;

import com.co.hackathon.itm_hackathon_web.models.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Integer> {
    List<Asistencia> findByEventoId(int eventoId);

    List<Asistencia> findByMiembroId(int miembroId);
}

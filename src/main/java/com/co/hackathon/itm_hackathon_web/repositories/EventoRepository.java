package com.co.hackathon.itm_hackathon_web.repositories;

import com.co.hackathon.itm_hackathon_web.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
}

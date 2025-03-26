package com.co.hackathon.itm_hackathon_web.services;

import com.co.hackathon.itm_hackathon_web.models.Acompanante;
import com.co.hackathon.itm_hackathon_web.repositories.AcompananteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Servicio para la gestión de los acompañantes.
 */
@Service
public class AcompananteService {

    private final AcompananteRepository acompananteRepository;

    public AcompananteService(AcompananteRepository acompananteRepository) {
        this.acompananteRepository = acompananteRepository;
    }

    public List<Acompanante> obtenerTodosLosAcompanantes() {
        return acompananteRepository.findAll();
    }

    public void guardarAcompanante(Acompanante acompanante) {
        Objects.requireNonNull(acompanante, "El acompañante no puede ser nulo");
        acompananteRepository.save(acompanante);
    }

    public Acompanante obtenerAcompanantePorId(int id) {
        return acompananteRepository.findByIdOrThrow(id);
    }

    public boolean eliminarAcompanante(int id) {
        if (acompananteRepository.existsById(id)) {
            Acompanante acomp = acompananteRepository.findByIdOrThrow(id);
            acompananteRepository.delete(acomp);
            return true;
        }
        return false;
    }
}

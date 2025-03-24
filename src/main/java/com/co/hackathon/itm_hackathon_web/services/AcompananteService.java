package com.co.hackathon.itm_hackathon_web.services;

import com.co.hackathon.itm_hackathon_web.models.Acompanante;
import com.co.hackathon.itm_hackathon_web.repositories.AcompananteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcompananteService {
    private final AcompananteRepository acompananteRepository;

    public AcompananteService(AcompananteRepository acompananteRepository) {
        this.acompananteRepository = acompananteRepository;
    }

    // 🔹 Obtener todos los acompañantes
    public List<Acompanante> obtenerTodosLosAcompanantes() {
        return acompananteRepository.findAll();
    }

    // 🔹 Guardar un nuevo acompañante
    public void guardarAcompanante(Acompanante acompanante) {
        acompananteRepository.save(acompanante);
    }

    // 🔹 Obtener un acompañante por ID
    public Acompanante obtenerAcompanantePorId(int id) {
        Optional<Acompanante> acompanante = acompananteRepository.findById(id);
        return acompanante.orElse(null);
    }

    // 🔹 Eliminar un acompañante por ID
    public boolean eliminarAcompanante(int id) {
        if (acompananteRepository.existsById(id)) {
            acompananteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
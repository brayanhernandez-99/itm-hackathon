package com.co.hackathon.itm_hackathon_web.services;

import com.co.hackathon.itm_hackathon_web.models.Miembro;
import com.co.hackathon.itm_hackathon_web.repositories.MiembroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Servicio para la gestión de los miembros.
 */
@Service
public class MiembroService {

    private final MiembroRepository miembroRepository;

    public MiembroService(MiembroRepository miembroRepository) {
        this.miembroRepository = miembroRepository;
    }

    public void guardarMiembro(Miembro miembro) {
        Objects.requireNonNull(miembro, "El miembro no puede ser nulo");
        miembroRepository.save(miembro);
    }

    public Miembro obtenerMiembroPorId(int id) {
        return miembroRepository.findByIdOrThrow(id);
    }

    public List<Miembro> obtenerTodosLosMiembros() {
        return miembroRepository.findAll();
    }

    public void eliminarMiembro(int id) {
        Miembro miembro = miembroRepository.findByIdOrThrow(id);
        miembroRepository.delete(miembro);
    }
}

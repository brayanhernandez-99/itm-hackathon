package com.co.hackathon.itm_hackathon_web.services;

import com.co.hackathon.itm_hackathon_web.models.Miembro;
import com.co.hackathon.itm_hackathon_web.repositories.MiembroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiembroService {

    private final MiembroRepository miembroRepository;

    public MiembroService(MiembroRepository miembroRepository) {
        this.miembroRepository = miembroRepository;
    }

    public List<Miembro> obtenerTodosLosMiembros() {
        return miembroRepository.findAll();
    }

    public Miembro obtenerMiembroPorId(int id) {
        return miembroRepository.findById(id).orElse(null);
    }

    public void guardarMiembro(Miembro miembro) {
        miembroRepository.save(miembro);
    }

    public void actualizarMiembro(int id, Miembro miembro) {
        if (miembroRepository.existsById(id)) {
            miembro.setId(id);
            miembroRepository.save(miembro);
        }
    }

    public void eliminarMiembro(int id) {
        miembroRepository.deleteById(id);
    }
}

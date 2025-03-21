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

    public List<Miembro> listarTodos() {
        return miembroRepository.findAll();
    }

    public void guardar(Miembro miembro) {
        miembroRepository.save(miembro);
    }

    public Miembro buscarPorId(Integer id) {
        return miembroRepository.findById(id).orElse(null);
    }

    public void eliminar(Integer id) {
        miembroRepository.deleteById(id);
    }
}

package com.co.hackathon.itm_hackathon_web.services;

import com.co.hackathon.itm_hackathon_web.models.Miembro;
import com.co.hackathon.itm_hackathon_web.repositories.MiembroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio encargado de la gestión de los miembros en el sistema.
 */
@Service
public class MiembroService {

    private final MiembroRepository miembroRepository;

    /**
     * Constructor que inyecta el repositorio de miembros.
     *
     * @param miembroRepository Repositorio para la gestión de miembros.
     */
    public MiembroService(MiembroRepository miembroRepository) {
        this.miembroRepository = miembroRepository;
    }

    /**
     * Guarda un nuevo miembro en la base de datos.
     *
     * @param miembro Objeto Miembro a guardar.
     */
    public void guardarMiembro(Miembro miembro) {
        miembroRepository.save(miembro);
    }

    /**
     * Obtiene un miembro por su ID.
     *
     * @param id ID del miembro.
     * @return Miembro correspondiente al ID proporcionado, o null si no se encuentra.
     */
    public Miembro obtenerMiembroPorId(int id) {
        return miembroRepository.findById(id).orElse(null);
    }

    /**
     * Obtiene todos los miembros registrados en la base de datos.
     *
     * @return Lista de todos los miembros.
     */
    public List<Miembro> obtenerTodosLosMiembros() {
        return miembroRepository.findAll();
    }

    /**
     * Elimina un miembro de la base de datos por su ID.
     *
     * @param id ID del miembro a eliminar.
     */
    public void eliminarMiembro(int id) {
        miembroRepository.deleteById(id);
    }
}

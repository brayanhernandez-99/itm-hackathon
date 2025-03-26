package com.co.hackathon.itm_hackathon_web.services;

import com.co.hackathon.itm_hackathon_web.models.Acompanante;
import com.co.hackathon.itm_hackathon_web.repositories.AcompananteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestión de los acompañantes.
 * Proporciona métodos para obtener, guardar y eliminar acompañantes.
 */
@Service
public class AcompananteService {

    private final AcompananteRepository acompananteRepository;

    /**
     * Constructor para inyectar el repositorio de acompañantes.
     * @param acompananteRepository Repositorio de Acompanante
     */
    public AcompananteService(AcompananteRepository acompananteRepository) {
        this.acompananteRepository = acompananteRepository;
    }

    /**
     * Obtiene todos los acompañantes almacenados en la base de datos.
     * @return Lista de acompañantes
     */
    public List<Acompanante> obtenerTodosLosAcompanantes() {
        return acompananteRepository.findAll();
    }

    /**
     * Guarda un nuevo acompañante en la base de datos.
     * @param acompanante Objeto Acompanante a guardar
     */
    public void guardarAcompanante(Acompanante acompanante) {
        acompananteRepository.save(acompanante);
    }

    /**
     * Busca un acompañante por su ID.
     * @param id Identificador del acompañante
     * @return Objeto Acompanante si se encuentra, null si no existe
     */
    public Acompanante obtenerAcompanantePorId(int id) {
        Optional<Acompanante> acompanante = acompananteRepository.findById(id);
        return acompanante.orElse(null);
    }

    /**
     * Elimina un acompañante por su ID si existe.
     * @param id Identificador del acompañante a eliminar
     * @return true si el acompañante fue eliminado, false si no existe
     */
    public boolean eliminarAcompanante(int id) {
        if (acompananteRepository.existsById(id)) {
            acompananteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
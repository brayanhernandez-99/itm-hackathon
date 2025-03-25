package com.co.hackathon.itm_hackathon_web.controllers;

import com.co.hackathon.itm_hackathon_web.models.Acompanante;
import com.co.hackathon.itm_hackathon_web.services.AcompananteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

/**
 * Controlador para manejar las operaciones relacionadas con los acompañantes.
 */
@Controller
@RequestMapping("/acompanante")
public class AcompananteController {
    private final AcompananteService acompananteService;

    /**
     * Constructor que inyecta el servicio de AcompananteService.
     *
     * @param acompananteService Servicio para manejar la lógica de negocio de los acompañantes.
     */
    public AcompananteController(AcompananteService acompananteService) {
        this.acompananteService = acompananteService;
    }

    /**
     * Método para obtener y mostrar todos los acompañantes.
     *
     * @param model Modelo de datos para la vista.
     * @return Vista con la lista de acompañantes.
     */
    @GetMapping
    public String obtenerTodosLosAcompanantes(Model model) {
        model.addAttribute("acompanantes", acompananteService.obtenerTodosLosAcompanantes());
        return "acompanante/listado";
    }

    /**
     * Método para mostrar el formulario de creación de un nuevo acompañante.
     *
     * @param model Modelo de datos para la vista.
     * @return Vista del formulario de creación de acompañante.
     */
    @GetMapping("/nuevo")
    public String nuevoAcompanante(Model model) {
        model.addAttribute("acompanante", new Acompanante());
        return "acompanante/formulario";
    }

    /**
     * Método para guardar un nuevo acompañante desde el formulario.
     *
     * @param acompanante Objeto acompañante recibido desde el formulario.
     * @return Redirección a la lista de acompañantes.
     */
    @PostMapping
    public String guardarAcompanante(@ModelAttribute Acompanante acompanante) {
        acompananteService.guardarAcompanante(acompanante);
        return "redirect:/acompanante";
    }

    /**
     * Método para mostrar el formulario de edición de un acompañante.
     *
     * @param id    Identificador del acompañante a editar.
     * @param model Modelo de datos para la vista.
     * @return Vista del formulario con los datos del acompañante o redirección si no existe.
     */
    @GetMapping("/editar/{id}")
    public String editarAcompanante(@PathVariable int id, Model model) {
        Acompanante acompanante = acompananteService.obtenerAcompanantePorId(id);
        if (acompanante != null) {
            model.addAttribute("acompanante", acompanante);
            return "acompanante/formulario";
        }
        return "redirect:/acompanante";
    }

    /**
     * Método para eliminar un acompañante y redirigir a la lista.
     *
     * @param id Identificador del acompañante a eliminar.
     * @return Redirección a la lista de acompañantes.
     */
    @GetMapping("/eliminar/{id}")
    public String eliminarAcompanante(@PathVariable int id) {
        acompananteService.eliminarAcompanante(id);
        return "redirect:/acompanante";
    }
}

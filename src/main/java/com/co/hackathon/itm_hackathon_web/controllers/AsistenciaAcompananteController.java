package com.co.hackathon.itm_hackathon_web.controllers;

import com.co.hackathon.itm_hackathon_web.models.AsistenciaAcompanante;
import com.co.hackathon.itm_hackathon_web.services.AsistenciaAcompananteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

/**
 * Controlador para manejar las operaciones relacionadas con la asistencia de acompañantes.
 */
@Controller
@RequestMapping("/acompanante/asistencia")
public class AsistenciaAcompananteController {
    private final AsistenciaAcompananteService asistenciaAcompananteService;

    /**
     * Constructor que inyecta el servicio de AsistenciaAcompananteService.
     *
     * @param asistenciaAcompananteService Servicio para manejar la lógica de negocio de la asistencia de acompañantes.
     */
    public AsistenciaAcompananteController(AsistenciaAcompananteService asistenciaAcompananteService) {
        this.asistenciaAcompananteService = asistenciaAcompananteService;
    }

    /**
     * Método para obtener y mostrar todas las asistencias registradas.
     *
     * @param model Modelo de datos para la vista.
     * @return Vista con la lista de asistencias de los acompañantes.
     */
    @GetMapping
    public String obtenerTodasLasAsistenciasOld(Model model) {
        model.addAttribute("asistenciaAcompanantes", asistenciaAcompananteService.obtenerTodasLasAsistencias());
        return "acompanante/asistencia/listado";
    }

    /**
     * Método para mostrar el formulario de registro de una nueva asistencia de acompañante.
     *
     * @param model Modelo de datos para la vista.
     * @return Vista del formulario de creación de asistencia.
     */
    @GetMapping("/nuevo")
    public String nuevaAsistenciaAcompanante(Model model) {
        model.addAttribute("asistenciaAcompanante", new AsistenciaAcompanante());
        return "acompanante/asistencia/formulario";
    }

    /**
     * Método para guardar una nueva asistencia de acompañante desde el formulario.
     *
     * @param asistenciaAcompanante Objeto asistencia recibido desde el formulario.
     * @return Redirección a la lista de asistencias.
     */
    @PostMapping
    public String guardarAsistenciaAcompanante(@ModelAttribute AsistenciaAcompanante asistenciaAcompanante) {
        asistenciaAcompananteService.guardarAsistenciaAcompanante(asistenciaAcompanante);
        return "redirect:/acompanante/asistencia";
    }

    /**
     * Método para mostrar el formulario de edición de una asistencia.
     *
     * @param id    Identificador de la asistencia a editar.
     * @param model Modelo de datos para la vista.
     * @return Vista del formulario con los datos de la asistencia o redirección si no existe.
     */
    @GetMapping("/editar/{id}")
    public String editarAsistenciaAcompanante(@PathVariable int id, Model model) {
        AsistenciaAcompanante asistencia = asistenciaAcompananteService.obtenerAsistenciaPorId(id);
        if (asistencia != null) {
            model.addAttribute("asistenciaAcompanante", asistencia);
            return "acompanante/asistencia/formulario";
        }
        return "redirect:/acompanante/asistencia";
    }

    /**
     * Método para obtener las asistencias de un acompañante específico.
     *
     * @param acompananteId Identificador del acompañante.
     * @param model         Modelo de datos para la vista.
     * @return Vista con la lista de asistencias del acompañante.
     */
    @GetMapping("/{acompananteId}")
    public String obtenerAsistenciasPorAcompanante(@PathVariable int acompananteId, Model model) {
        model.addAttribute("asistenciaAcompanantes", asistenciaAcompananteService.obtenerAsistenciasPorAcompanante(acompananteId));
        return "acompanante/asistencia/listado";
    }

    /**
     * Método para eliminar una asistencia de acompañante y redirigir a la lista.
     *
     * @param id Identificador de la asistencia a eliminar.
     * @return Redirección a la lista de asistencias.
     */
    @GetMapping("/eliminar/{id}")
    public String eliminarAsistenciaAcompanante(@PathVariable int id) {
        asistenciaAcompananteService.eliminarAsistenciaAcompanante(id);
        return "redirect:/acompanante/asistencia";
    }
}

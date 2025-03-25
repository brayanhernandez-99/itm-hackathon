package com.co.hackathon.itm_hackathon_web.controllers;

import com.co.hackathon.itm_hackathon_web.models.Asistencia;
import com.co.hackathon.itm_hackathon_web.models.Evento;
import com.co.hackathon.itm_hackathon_web.models.Miembro;
import com.co.hackathon.itm_hackathon_web.services.AsistenciaService;
import com.co.hackathon.itm_hackathon_web.services.EventoService;
import com.co.hackathon.itm_hackathon_web.services.MiembroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * Controlador para manejar las operaciones relacionadas con la asistencia.
 */
@Controller
@RequestMapping("/asistencia")
public class AsistenciaController {

    private final AsistenciaService asistenciaService;
    private final EventoService eventoService;
    private final MiembroService miembroService;

    /**
     * Constructor que inyecta los servicios necesarios.
     *
     * @param asistenciaService Servicio de gestión de asistencias.
     * @param eventoService Servicio de gestión de eventos.
     * @param miembroService Servicio de gestión de miembros.
     */
    public AsistenciaController(AsistenciaService asistenciaService, EventoService eventoService, MiembroService miembroService) {
        this.asistenciaService = asistenciaService;
        this.eventoService = eventoService;
        this.miembroService = miembroService;
    }

    /**
     * Método para obtener y mostrar todas las asistencias registradas.
     *
     * @param model Modelo de datos para la vista.
     * @return Vista con la lista de asistencias.
     */
    @GetMapping
    public String obtenerTodasLasAsistencias(Model model) {
        List<Asistencia> asistencias = asistenciaService.obtenerTodasLasAsistencias();
        if (asistencias == null || asistencias.isEmpty()) {
            throw new RuntimeException("No hay asistencias registradas");
        }
        model.addAttribute("asistencias", asistencias);
        return "asistencia/listado";
    }

    /**
     * Método para obtener asistencias por evento.
     *
     * @param eventoId Identificador del evento.
     * @param model Modelo de datos para la vista.
     * @param request Petición HTTP.
     * @return Vista con la lista de asistencias del evento.
     */
    @GetMapping("/evento/{eventoId}")
    public String listarAsistenciasPorEvento(@PathVariable int eventoId, Model model, HttpServletRequest request) {
        Evento evento = eventoService.obtenerEventoPorId(eventoId);
        List<Asistencia> asistencias = asistenciaService.obtenerAsistenciasPorEvento(eventoId);
        model.addAttribute("evento", evento);
        model.addAttribute("asistencias", asistencias);
        return "asistencia/listado";
    }

    /**
     * Método para obtener asistencias por miembro.
     *
     * @param miembroId Identificador del miembro.
     * @param model Modelo de datos para la vista.
     * @return Vista con la lista de asistencias del miembro.
     */
    @GetMapping("/miembro/{miembroId}")
    public String listarAsistenciasPorMiembro(@PathVariable int miembroId, Model model) {
        Miembro miembro = miembroService.obtenerMiembroPorId(miembroId);
        List<Asistencia> asistencias = asistenciaService.obtenerAsistenciasPorMiembro(miembroId);
        model.addAttribute("miembro", miembro);
        model.addAttribute("asistencias", asistencias);
        return "asistencia/listado";
    }

    /**
     * Método para mostrar el formulario de registro de una nueva asistencia.
     *
     * @param model Modelo de datos para la vista.
     * @return Vista del formulario de creación de asistencia.
     */
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("asistencia", new Asistencia());
        model.addAttribute("eventos", eventoService.obtenerTodosLosEventos());
        model.addAttribute("miembros", miembroService.obtenerTodosLosMiembros());
        return "asistencia/formulario";
    }

    /**
     * Método para guardar una nueva asistencia desde el formulario.
     *
     * @param asistencia Objeto asistencia recibido desde el formulario.
     * @return Redirección a la lista de asistencias.
     */
    @PostMapping
    public String guardarAsistencia(@ModelAttribute Asistencia asistencia) {
        asistenciaService.guardarAsistencia(asistencia);
        return "redirect:/asistencia";
    }

    /**
     * Método para mostrar el formulario de edición de una asistencia.
     *
     * @param id Identificador de la asistencia a editar.
     * @param model Modelo de datos para la vista.
     * @return Vista del formulario con los datos de la asistencia.
     */
    @GetMapping("/editar/{id}")
    public String editarAsistencia(@PathVariable int id, Model model) {
        Asistencia asistencia = asistenciaService.obtenerAsistenciaPorId(id);
        model.addAttribute("asistencia", asistencia);
        model.addAttribute("eventos", eventoService.obtenerTodosLosEventos());
        model.addAttribute("miembros", miembroService.obtenerTodosLosMiembros());
        return "asistencia/formulario";
    }

    /**
     * Método para eliminar una asistencia con validación.
     *
     * @param id Identificador de la asistencia a eliminar.
     * @return Redirección a la lista de asistencias.
     */
    @GetMapping("/eliminar/{id}")
    public String eliminarAsistencia(@PathVariable int id) {
        if (!asistenciaService.eliminarAsistencia(id)) {
            throw new RuntimeException("No se pudo eliminar asistencia");
        }
        return "redirect:/asistencia";
    }
}

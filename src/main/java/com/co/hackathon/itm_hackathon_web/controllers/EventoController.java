package com.co.hackathon.itm_hackathon_web.controllers;

import com.co.hackathon.itm_hackathon_web.models.Evento;
import com.co.hackathon.itm_hackathon_web.models.Miembro;
import com.co.hackathon.itm_hackathon_web.services.EventoService;
import com.co.hackathon.itm_hackathon_web.services.MiembroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para gestionar los eventos dentro del sistema.
 */
@Controller
@RequestMapping("/evento")
public class EventoController {

    private final EventoService eventoService;
    private final MiembroService miembroService;

    /**
     * Constructor que inyecta los servicios necesarios.
     *
     * @param eventoService Servicio para la gestión de eventos.
     * @param miembroService Servicio para la gestión de miembros.
     */
    public EventoController(EventoService eventoService, MiembroService miembroService) {
        this.eventoService = eventoService;
        this.miembroService = miembroService;
    }

    /**
     * Método para obtener y mostrar todos los eventos registrados.
     *
     * @param model Modelo de datos para la vista.
     * @return Vista con la lista de eventos.
     */
    @GetMapping
    public String obtenerTodosLosEventos(Model model) {
        List<Evento> eventos = eventoService.obtenerTodosLosEventos();
        if (eventos == null || eventos.isEmpty()) {
            throw new RuntimeException("No hay eventos registrados");
        }
        model.addAttribute("eventos", eventos);
        return "evento/listado";  // Vista de listado de eventos
    }

    /**
     * Método para mostrar el formulario de creación de un nuevo evento.
     *
     * @param model Modelo de datos para la vista.
     * @return Vista del formulario de creación de evento.
     */
    @GetMapping("/nuevo")
    public String crearEvento(Model model) {
        model.addAttribute("evento", new Evento());
        List<Miembro> miembros = miembroService.obtenerTodosLosMiembros();  // Obtener todos los miembros
        model.addAttribute("miembros", miembros);
        return "evento/formulario";  // Vista para añadir evento
    }

    /**
     * Método para guardar un nuevo evento desde el formulario.
     *
     * @param evento Objeto evento recibido desde el formulario.
     * @return Redirección a la lista de eventos.
     */
    @PostMapping
    public String guardarEvento(@ModelAttribute Evento evento) {
        eventoService.guardarEvento(evento);
        return "redirect:/evento";
    }

    /**
     * Método para mostrar el formulario de edición de un evento.
     *
     * @param id Identificador del evento a editar.
     * @param model Modelo de datos para la vista.
     * @return Vista del formulario con los datos del evento.
     */
    @GetMapping("/editar/{id}")
    public String editarEvento(@PathVariable int id, Model model) {
        Evento evento = eventoService.obtenerEventoPorId(id);
        model.addAttribute("evento", evento);
        List<Miembro> miembros = miembroService.obtenerTodosLosMiembros();  // Obtener todos los miembros
        model.addAttribute("miembros", miembros);
        return "evento/formulario";  // Vista de formulario para editar evento
    }

    /**
     * Método para eliminar un evento.
     *
     * @param id Identificador del evento a eliminar.
     * @return Redirección a la lista de eventos.
     */
    @GetMapping("/eliminar/{id}")
    public String eliminarEvento(@PathVariable int id) {
        eventoService.eliminarEvento(id);
        return "redirect:/evento";
    }
}

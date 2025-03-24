package com.co.hackathon.itm_hackathon_web.controllers;

import com.co.hackathon.itm_hackathon_web.models.Asistencia;
import com.co.hackathon.itm_hackathon_web.models.Evento;
import com.co.hackathon.itm_hackathon_web.models.Miembro;
import com.co.hackathon.itm_hackathon_web.services.EventoService;
import com.co.hackathon.itm_hackathon_web.services.MiembroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/evento")
public class EventoController {

    private final EventoService eventoService;
    private final MiembroService miembroService;

    public EventoController(EventoService eventoService, MiembroService miembroService) {
        this.eventoService = eventoService;
        this.miembroService = miembroService;
    }

    @GetMapping
    public String obtenerTodosLosEventos(Model model) {
        List<Evento> eventos = eventoService.obtenerTodosLosEventos();
        if (eventos == null || eventos.isEmpty()){
            throw new RuntimeException("No hay eventos registrados");
        }
        model.addAttribute("eventos", eventos);
        return "evento/listado";  // Vista de listado.html de eventos
    }

    @GetMapping("/nuevo")
    public String crearEvento(Model model) {
        model.addAttribute("evento", new Evento());
        List<Miembro> miembros = miembroService.obtenerTodosLosMiembros();  // Obtén todos los miembros para el formulario
        model.addAttribute("miembros", miembros);
        return "evento/formulario";  // Vista para añadir evento
    }

    @PostMapping
    public String guardarEvento(@ModelAttribute Evento evento) {
        eventoService.guardarEvento(evento);
        return "redirect:/evento";
    }

    @GetMapping("/editar/{id}")
    public String editarEvento(@PathVariable int id, Model model) {
        Evento evento = eventoService.obtenerEventoPorId(id);
        model.addAttribute("evento", evento);
        List<Miembro> miembros = miembroService.obtenerTodosLosMiembros();  // Obtén todos los miembros para el formulario
        model.addAttribute("miembros", miembros);
        return "evento/formulario";  // Vista de formulario para editar evento
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEvento(@PathVariable int id) {
        eventoService.eliminarEvento(id);
        return "redirect:/evento";
    }
}

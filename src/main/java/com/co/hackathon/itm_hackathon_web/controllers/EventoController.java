package com.co.hackathon.itm_hackathon_web.controllers;

import com.co.hackathon.itm_hackathon_web.models.Evento;
import com.co.hackathon.itm_hackathon_web.models.Miembro;
import com.co.hackathon.itm_hackathon_web.services.EventoService;
import com.co.hackathon.itm_hackathon_web.services.MiembroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    private final EventoService eventoService;
    private final MiembroService miembroService;

    public EventoController(EventoService eventoService, MiembroService miembroService) {
        this.eventoService = eventoService;
        this.miembroService = miembroService;
    }

    @GetMapping
    public String listarEventos(Model model) {
        List<Evento> eventos = eventoService.obtenerTodosLosEventos();
        model.addAttribute("eventos", eventos);
        return "eventos/listado";  // Vista de listado de eventos
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoEvento(Model model) {
        model.addAttribute("evento", new Evento());
        List<Miembro> miembros = miembroService.obtenerTodosLosMiembros();  // Obtén todos los miembros para el formulario
        model.addAttribute("miembros", miembros);
        return "eventos/formulario";  // Vista para añadir evento
    }

    @PostMapping
    public String guardarEvento(@ModelAttribute Evento evento) {
        eventoService.guardarEvento(evento);
        return "redirect:/eventos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        Evento evento = eventoService.obtenerEventoPorId(id);
        model.addAttribute("evento", evento);
        List<Miembro> miembros = miembroService.obtenerTodosLosMiembros();  // Obtén todos los miembros para el formulario
        model.addAttribute("miembros", miembros);
        return "eventos/formulario";  // Vista de formulario para editar evento
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarEvento(@PathVariable int id, @ModelAttribute Evento evento) {
        eventoService.actualizarEvento(id, evento);
        return "redirect:/eventos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEvento(@PathVariable int id) {
        eventoService.eliminarEvento(id);
        return "redirect:/eventos";
    }
}

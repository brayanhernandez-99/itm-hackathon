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

@Controller
@RequestMapping("/asistencias")
public class AsistenciaController {

    private final AsistenciaService asistenciaService;
    private final EventoService eventoService;
    private final MiembroService miembroService;

    public AsistenciaController(AsistenciaService asistenciaService, EventoService eventoService, MiembroService miembroService) {
        this.asistenciaService = asistenciaService;
        this.eventoService = eventoService;
        this.miembroService = miembroService;
    }

    // 🔹 Listar todas las asistencias
    @GetMapping
    public String listarAsistencias(Model model) {
        List<Asistencia> asistencias = asistenciaService.obtenerTodasLasAsistencias();
        if (asistencias == null || asistencias.isEmpty()) {
            throw new RuntimeException("No hay asistencias registradas");
        }
        model.addAttribute("asistencias", asistencias);
        return "asistencias/listado.html";
    }

    // 🔹 Listar asistencias por evento
    @GetMapping("/evento/{eventoId}")
    public String listarAsistenciasPorEvento(@PathVariable int eventoId, Model model, HttpServletRequest request) {
        Evento evento = eventoService.obtenerEventoPorId(eventoId);
        List<Asistencia> asistencias = asistenciaService.obtenerAsistenciasPorEvento(eventoId);
        model.addAttribute("evento", evento);
        model.addAttribute("asistencias", asistencias);
        return "asistencias/listado";
    }

    // 🔹 Listar asistencias por miembro
    @GetMapping("/miembro/{miembroId}")
    public String listarAsistenciasPorMiembro(@PathVariable int miembroId, Model model) {
        Miembro miembro = miembroService.obtenerMiembroPorId(miembroId);
        List<Asistencia> asistencias = asistenciaService.obtenerAsistenciasPorMiembro(miembroId);
        model.addAttribute("miembro", miembro);
        model.addAttribute("asistencias", asistencias);
        return "asistencias/listado";
    }

    // 🔹 Crear nueva asistencia
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("asistencia", new Asistencia());
        model.addAttribute("eventos", eventoService.obtenerTodosLosEventos());
        model.addAttribute("miembros", miembroService.obtenerTodosLosMiembros());
        return "asistencias/formulario";
    }

    @PostMapping
    public String guardarAsistencia(@ModelAttribute Asistencia asistencia) {
        asistenciaService.guardarAsistencia(asistencia);
        return "redirect:/asistencias";
    }

    // 🔹 Mostrar formulario para editar asistencia
    @GetMapping("/editar/{id}")
    public String editarAsistencia(@PathVariable int id, Model model) {
        Asistencia asistencia = asistenciaService.obtenerAsistenciaPorId(id);
        model.addAttribute("asistencia", asistencia);
        model.addAttribute("eventos", eventoService.obtenerTodosLosEventos());
        model.addAttribute("miembros", miembroService.obtenerTodosLosMiembros());
        return "asistencias/formulario";
    }

    // 🔹 Eliminar asistencia con validación
    @GetMapping("/eliminar/{id}")
    public String eliminarAsistencia(@PathVariable int id) {
        if (!asistenciaService.eliminarAsistencia(id)) {
            throw new RuntimeException("No se pudo eliminar asistencia");
        }
        return "redirect:/asistencias";
    }
}

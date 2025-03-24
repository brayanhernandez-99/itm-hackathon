package com.co.hackathon.itm_hackathon_web.controllers;

import com.co.hackathon.itm_hackathon_web.models.AsistenciaAcompanantes;
import com.co.hackathon.itm_hackathon_web.services.AsistenciaAcompanantesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@RestController
@RequestMapping("/acompanantes")
public class AsistenciaAcompanantesController {
    private final AsistenciaAcompanantesService service;

    public AsistenciaAcompanantesController(AsistenciaAcompanantesService service) {
        this.service = service;
    }

    // 🔹 Mostrar todas las asistencias
    @GetMapping
    public String obtenerTodasLasAsistencias(Model model) {
        model.addAttribute("asistencias", service.obtenerTodasLasAsistencias());
        return "asistencias/listado"; // Nombre de la vista donde se mostrarán las asistencias
    }

    // 🔹 Mostrar formulario de nueva asistencia
    @GetMapping("/nuevo")
    public String nuevaAsistenciaAcompanante(Model model) {
        model.addAttribute("asistencia", new AsistenciaAcompanantes());
        return "asistencias/formulario"; // Nombre del template del formulario
    }

    // 🔹 Guardar nueva asistencia desde el formulario
    @PostMapping
    public String guardarAsistenciaAcompanante(@ModelAttribute AsistenciaAcompanantes asistencia) {
        service.guardarAsistenciaAcompanante(asistencia);
        return "redirect:/acompanantes"; // Redirige a la lista después de guardar
    }

    // 🔹 Mostrar formulario de edición de asistencia
    @GetMapping("/editar/{id}")
    public String editarAsistenciaAcompanante(@PathVariable int id, Model model) {
        AsistenciaAcompanantes asistencia = service.obtenerAsistenciaPorId(id);
        if (asistencia != null) {
            model.addAttribute("asistencia", asistencia);
            return "asistencias/formulario"; // Usa el mismo formulario para editar
        }
        return "redirect:/acompanantes"; // Si no encuentra la asistencia, redirige a la lista
    }

    // 🔹 Mostrar asistencias por acompañante
    @GetMapping("/asistencias/{acompananteId}")
    public String obtenerAsistenciasPorAcompanante(@PathVariable int acompananteId, Model model) {
        model.addAttribute("asistencias", service.obtenerAsistenciasPorAcompanante(acompananteId));
        return "asistencias/listado";
    }

    // 🔹 Eliminar asistencia y redirigir
    @GetMapping("/eliminar/{id}")
    public String eliminarAsistenciaAcompanante(@PathVariable int id) {
        service.eliminarAsistenciaAcompanante(id);
        return "redirect:/acompanantes";
    }
}

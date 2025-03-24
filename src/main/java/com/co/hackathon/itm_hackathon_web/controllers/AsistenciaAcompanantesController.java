package com.co.hackathon.itm_hackathon_web.controllers;

import com.co.hackathon.itm_hackathon_web.models.AsistenciaAcompanantes;
import com.co.hackathon.itm_hackathon_web.services.AsistenciaAcompananteService;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


@RestController
@RequestMapping("/asistencia-acompanante")
public class AsistenciaAcompanantesController {
    private final AsistenciaAcompananteService service;

    public AsistenciaAcompanantesController(AsistenciaAcompananteService service) {
        this.service = service;
    }

    // 🔹 Mostrar todas las asistencias
    @GetMapping
    public String obtenerTodasLasAsistencias(Model model) {
        model.addAttribute("asistenciasAcompanantes", service.obtenerTodasLasAsistencias());
        return "acompanantes/listado";
    }

    // 🔹 Mostrar formulario de nueva asistencia
    @GetMapping("/nuevo")
    public String nuevaAsistenciaAcompanante(Model model) {
        model.addAttribute("asistenciasAcompanantes", new AsistenciaAcompanantes());
        return "acompanantes/formulario";
    }

    // 🔹 Guardar nueva asistencia desde el formulario
    @PostMapping
    public String guardarAsistenciaAcompanante(@ModelAttribute AsistenciaAcompanantes asistencia) {
        service.guardarAsistenciaAcompanante(asistencia);
        return "redirect:/acompanantes";
    }

    // 🔹 Mostrar formulario de edición de asistencia
    @GetMapping("/editar/{id}")
    public String editarAsistenciaAcompanante(@PathVariable int id, Model model) {
        AsistenciaAcompanantes asistencia = service.obtenerAsistenciaPorId(id);
        if (asistencia != null) {
            model.addAttribute("asistenciasAcompanantes", asistencia);
            return "acompanantes/formulario";
        }
        return "redirect:/acompanantes";
    }

    // 🔹 Mostrar asistencias por acompañante
    @GetMapping("/asistencias/{acompananteId}")
    public String obtenerAsistenciasPorAcompanante(@PathVariable int acompananteId, Model model) {
        model.addAttribute("asistenciasAcompanantes", service.obtenerAsistenciasPorAcompanante(acompananteId));
        return "acompanantes/listado";
    }

    // 🔹 Eliminar asistencia y redirigir
    @GetMapping("/eliminar/{id}")
    public String eliminarAsistenciaAcompanante(@PathVariable int id) {
        service.eliminarAsistenciaAcompanante(id);
        return "redirect:/acompanantes";
    }
}

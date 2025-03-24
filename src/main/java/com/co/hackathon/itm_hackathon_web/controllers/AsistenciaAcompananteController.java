package com.co.hackathon.itm_hackathon_web.controllers;

import com.co.hackathon.itm_hackathon_web.models.AsistenciaAcompanante;
import com.co.hackathon.itm_hackathon_web.services.AsistenciaAcompananteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/acompanante/asistencia")
public class AsistenciaAcompananteController{
    private final AsistenciaAcompananteService asistenciaAcompananteService;

    public AsistenciaAcompananteController(AsistenciaAcompananteService asistenciaAcompananteService) {
        this.asistenciaAcompananteService = asistenciaAcompananteService;
    }

    // 🔹 Mostrar todas las asistencias
    @GetMapping
    public String obtenerTodasLasAsistenciasOld(Model model) {
        model.addAttribute("asistenciaAcompanantes", asistenciaAcompananteService.obtenerTodasLasAsistencias());
        return "acompanante/asistencia/listado";
    }

    // 🔹 Mostrar formulario de nueva asistencia acompañante
    @GetMapping("/nuevo")
    public String nuevaAsistenciaAcompanante(Model model) {
        model.addAttribute("asistenciaAcompanante", new AsistenciaAcompanante());
        return "acompanante/asistencia/formulario";
    }

    // 🔹 Guardar nueva asistencia desde el formulario
    @PostMapping
    public String guardarAsistenciaAcompanante(@ModelAttribute AsistenciaAcompanante asistenciaAcompanante) {
        asistenciaAcompananteService.guardarAsistenciaAcompanante(asistenciaAcompanante);
        return "redirect:/acompanante/asistencia";
    }

    // 🔹 Mostrar formulario de edición de asistencia
    @GetMapping("/editar/{id}")
    public String editarAsistenciaAcompanante(@PathVariable int id, Model model) {
        AsistenciaAcompanante asistencia = asistenciaAcompananteService.obtenerAsistenciaPorId(id);
        if (asistencia != null) {
            model.addAttribute("asistenciaAcompanante", asistencia);
            return "acompanante/asistencia/formulario";
        }
        return "redirect:/acompanante/asistencia";
    }

    // 🔹 Mostrar asistencias por acompañante
    @GetMapping("/asistencia/{acompananteId}")
    public String obtenerAsistenciasPorAcompanante(@PathVariable int acompananteId, Model model) {
        model.addAttribute("asistenciaAcompanante", asistenciaAcompananteService.obtenerAsistenciasPorAcompanante(acompananteId));
        return "acompanante/asistencia/listado";
    }

    // 🔹 Eliminar asistencia y redirigir
    @GetMapping("/eliminar/{id}")
    public String eliminarAsistenciaAcompanante(@PathVariable int id) {
        asistenciaAcompananteService.eliminarAsistenciaAcompanante(id);
        return "redirect:/acompanante/asistencia";
    }
}

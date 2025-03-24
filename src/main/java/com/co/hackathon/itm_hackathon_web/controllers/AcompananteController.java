package com.co.hackathon.itm_hackathon_web.controllers;

import com.co.hackathon.itm_hackathon_web.models.AsistenciaAcompanantes;
import com.co.hackathon.itm_hackathon_web.services.AcompananteService;
import com.co.hackathon.itm_hackathon_web.services.AsistenciaAcompananteService;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@RestController
@RequestMapping("/acompanante")
public class AcompananteController {
    private final AcompananteService acompananteService;
    private final AsistenciaAcompananteService asistenciaAcompananteService;

    public AcompananteController(AcompananteService acompananteService, AsistenciaAcompananteService asistenciaAcompananteService) {
        this.acompananteService = acompananteService;
        this.asistenciaAcompananteService = asistenciaAcompananteService;
    }

    // 🔹 Mostrar todos los acompañantes
    @GetMapping
    public String obtenerTodosLosAcompanantes(Model model) {
        model.addAttribute("acompanantes", acompananteService.obtenerTodasLasAsistencias());
        return "acompanantes/listado";
    }

    // 🔹 Mostrar todas las asistencias
    @GetMapping
    public String obtenerTodasLasAsistencias(Model model) {
        model.addAttribute("asistenciasAcompanantes", asistenciaAcompananteService.obtenerTodasLasAsistencias());
        return "acompanantes/asistencias/listado";
    }

    // 🔹 Mostrar formulario de nuevo acompañante
    @GetMapping("/nuevo")
    public String nuevaAsistenciaAcompanante(Model model) {
        model.addAttribute("asistenciasAcompanantes", new AsistenciaAcompanantes());
        return "acompanantes/formulario";
    }

    // 🔹 Mostrar formulario de nueva asistencia acompañante
    @GetMapping("/asistencia/nuevo")
    public String nuevaAsistenciaAcompanante(Model model) {
        model.addAttribute("asistenciasAcompanantes", new AsistenciaAcompanantes());
        return "acompanantes/formulario";
    }

}
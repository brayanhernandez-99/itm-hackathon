package com.co.hackathon.itm_hackathon_web.controllers;

import com.co.hackathon.itm_hackathon_web.models.Acompanante;
import com.co.hackathon.itm_hackathon_web.services.AcompananteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/acompanante")
public class AcompananteController {
    private final AcompananteService acompananteService;

    public AcompananteController(AcompananteService acompananteService) {
        this.acompananteService = acompananteService;
    }

    // 🔹 Mostrar todos los acompañantes
    @GetMapping
    public String obtenerTodosLosAcompanantes(Model model) {
        model.addAttribute("acompanantes", acompananteService.obtenerTodosLosAcompanantes());
        return "acompanante/listado";
    }

    // 🔹 Mostrar formulario de nuevo acompañante
    @GetMapping("/nuevo")
    public String nuevoAcompanante(Model model) {
        model.addAttribute("acompanante", new Acompanante());
        return "acompanante/formulario";
    }

    // 🔹 Guardar nuevo acompañante desde el formulario
    @PostMapping
    public String guardarAcompanante(@ModelAttribute Acompanante acompanante) {
        acompananteService.guardarAcompanante(acompanante);
        return "redirect:/acompanante";
    }

    // 🔹 Mostrar formulario de edición de acompañante
    @GetMapping("/editar/{id}")
    public String editarAcompanante(@PathVariable int id, Model model) {
        Acompanante acompanante = acompananteService.obtenerAcompanantePorId(id);
        if (acompanante != null) {
            model.addAttribute("acompanante", acompanante);
            return "acompanante/formulario";
        }
        return "redirect:/acompanante";
    }

    // 🔹 Eliminar acompañante y redirigir
    @GetMapping("/eliminar/{id}")
    public String eliminarAcompanante(@PathVariable int id) {
        acompananteService.eliminarAcompanante(id);
        return "redirect:/acompanante";
    }
}

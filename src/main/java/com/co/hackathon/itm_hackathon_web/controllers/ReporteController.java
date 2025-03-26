package com.co.hackathon.itm_hackathon_web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReporteController {

    @GetMapping("/reportes")
    public String mostrarReportes(Model model) {
        return "reportes"; // Retorna la vista reportes.html en templates
    }
}

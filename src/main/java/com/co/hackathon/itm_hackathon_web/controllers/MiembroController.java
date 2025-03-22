package com.co.hackathon.itm_hackathon_web.controllers;

import com.co.hackathon.itm_hackathon_web.models.Miembro;
import com.co.hackathon.itm_hackathon_web.services.MiembroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/miembros")
public class MiembroController {

    private final MiembroService miembroService;

    public MiembroController(MiembroService miembroService) {
        this.miembroService = miembroService;
    }

    @GetMapping
    public String listarMiembros(Model model) {
        List<Miembro> miembros = miembroService.obtenerTodosLosMiembros();
        if (miembros == null || miembros.isEmpty()){
            throw new RuntimeException("No hay miembros registrados");
        }
        model.addAttribute("miembros", miembros);
        return "miembros/listado.html";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoMiembro(Model model) {
        model.addAttribute("miembro", new Miembro());
        return "miembros/formulario";
    }

    @PostMapping
    public String guardarMiembro(@ModelAttribute Miembro miembro) {
        miembroService.guardarMiembro(miembro);
        return "redirect:/miembros";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        Miembro miembro = miembroService.obtenerMiembroPorId(id);
        model.addAttribute("miembro", miembro);
        return "miembros/formulario";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarMiembro(@PathVariable int id, @ModelAttribute Miembro miembro) {
        miembroService.actualizarMiembro(id, miembro);
        return "redirect:/miembros";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarMiembro(@PathVariable int id) {
        miembroService.eliminarMiembro(id);
        return "redirect:/miembros";
    }
}

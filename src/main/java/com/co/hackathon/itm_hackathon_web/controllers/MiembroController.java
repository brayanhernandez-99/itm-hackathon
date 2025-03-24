package com.co.hackathon.itm_hackathon_web.controllers;

import com.co.hackathon.itm_hackathon_web.models.Miembro;
import com.co.hackathon.itm_hackathon_web.services.MiembroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/miembro")
public class MiembroController {

    private final MiembroService miembroService;

    public MiembroController(MiembroService miembroService) {
        this.miembroService = miembroService;
    }

    @GetMapping
    public String obtenerTodosLosMiembros(Model model) {
        List<Miembro> miembros = miembroService.obtenerTodosLosMiembros();
        if (miembros == null || miembros.isEmpty()){
            throw new RuntimeException("No hay miembros registrados");
        }
        model.addAttribute("miembros", miembros);
        return "miembro/listado";
    }

    @GetMapping("/nuevo")
    public String crearMiembro(Model model) {
        model.addAttribute("miembro", new Miembro());
        return "miembro/formulario";
    }

    @PostMapping
    public String guardarMiembro(@ModelAttribute Miembro miembro) {
        miembroService.guardarMiembro(miembro);
        return "redirect:/miembro";
    }

    @GetMapping("/editar/{id}")
    public String editarMiembro(@PathVariable int id, Model model) {
        Miembro miembro = miembroService.obtenerMiembroPorId(id);
        model.addAttribute("miembro", miembro);
        return "miembro/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarMiembro(@PathVariable int id) {
        miembroService.eliminarMiembro(id);
        return "redirect:/miembro";
    }
}

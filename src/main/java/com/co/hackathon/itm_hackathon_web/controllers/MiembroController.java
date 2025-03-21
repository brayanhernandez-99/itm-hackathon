package com.co.hackathon.itm_hackathon_web.controllers;

import com.co.hackathon.itm_hackathon_web.models.Miembro;
import com.co.hackathon.itm_hackathon_web.repositories.MiembroRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/miembros")
public class MiembroController {

    private final MiembroRepository miembroRepository;

    public MiembroController(MiembroRepository miembroRepository) {
        this.miembroRepository = miembroRepository;
    }

    // 📌 Método para mostrar todos los miembros en la vista HTML
    @GetMapping
    public String listarMiembros(Model model) {
        model.addAttribute("miembros", miembroRepository.findAll());
        return "miembros/lista"; // Renderiza el archivo lista.html
    }

    // 📌 Método para mostrar el formulario de nuevo miembro
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("miembro", new Miembro());
        return "miembros/formulario";
    }

    // 📌 Método para guardar un nuevo miembro
    @PostMapping
    public String guardarMiembro(@Valid @ModelAttribute Miembro miembro) {
        miembroRepository.save(miembro);
        return "redirect:/miembros"; // Redirige a la lista después de guardar
    }

    // 📌 Método para mostrar el formulario de edición
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        model.addAttribute("miembro", miembroRepository.findById(id).orElse(null));
        return "miembros/formulario";
    }

    // 📌 Método para eliminar un miembro
    @GetMapping("/eliminar/{id}")
    public String eliminarMiembro(@PathVariable Integer id) {
        miembroRepository.deleteById(id);
        return "redirect:/miembros";
    }
}

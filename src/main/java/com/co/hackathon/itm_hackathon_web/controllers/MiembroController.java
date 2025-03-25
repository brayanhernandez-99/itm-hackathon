package com.co.hackathon.itm_hackathon_web.controllers;

import com.co.hackathon.itm_hackathon_web.models.Miembro;
import com.co.hackathon.itm_hackathon_web.services.MiembroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para la gestión de miembros en el sistema.
 * Maneja las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para los miembros.
 */
@Controller
@RequestMapping("/miembro")
public class MiembroController {

    private final MiembroService miembroService;

    /**
     * Constructor que inyecta el servicio de miembros.
     * @param miembroService Servicio para la gestión de miembros.
     */
    public MiembroController(MiembroService miembroService) {
        this.miembroService = miembroService;
    }

    /**
     * Obtiene la lista de todos los miembros y la envía a la vista.
     * @param model Modelo para pasar datos a la vista.
     * @return Vista con la lista de miembros.
     */
    @GetMapping
    public String obtenerTodosLosMiembros(Model model) {
        List<Miembro> miembros = miembroService.obtenerTodosLosMiembros();
        if (miembros == null || miembros.isEmpty()){
            throw new RuntimeException("No hay miembros registrados");
        }
        model.addAttribute("miembros", miembros);
        return "miembro/listado"; // Devuelve la vista donde se listan los miembros.
    }

    /**
     * Muestra el formulario para crear un nuevo miembro.
     * @param model Modelo para pasar datos a la vista.
     * @return Vista del formulario de creación de miembro.
     */
    @GetMapping("/nuevo")
    public String crearMiembro(Model model) {
        model.addAttribute("miembro", new Miembro());
        return "miembro/formulario";
    }

    /**
     * Guarda un nuevo miembro en el sistema.
     * @param miembro Objeto Miembro a guardar.
     * @return Redirección a la lista de miembros.
     */
    @PostMapping
    public String guardarMiembro(@ModelAttribute Miembro miembro) {
        miembroService.guardarMiembro(miembro);
        return "redirect:/miembro";
    }

    /**
     * Muestra el formulario para editar un miembro existente.
     * @param id Identificador del miembro a editar.
     * @param model Modelo para pasar datos a la vista.
     * @return Vista del formulario de edición.
     */
    @GetMapping("/editar/{id}")
    public String editarMiembro(@PathVariable int id, Model model) {
        Miembro miembro = miembroService.obtenerMiembroPorId(id);
        model.addAttribute("miembro", miembro);
        return "miembro/formulario";
    }

    /**
     * Elimina un miembro por su ID.
     * @param id Identificador del miembro a eliminar.
     * @return Redirección a la lista de miembros.
     */
    @GetMapping("/eliminar/{id}")
    public String eliminarMiembro(@PathVariable int id) {
        miembroService.eliminarMiembro(id);
        return "redirect:/miembro";
    }
}

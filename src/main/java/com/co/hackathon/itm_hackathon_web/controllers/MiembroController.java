package com.co.hackathon.itm_hackathon_web.controllers;

import com.co.hackathon.itm_hackathon_web.models.Miembro;
import com.co.hackathon.itm_hackathon_web.services.MiembroService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
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
        if (miembros == null || miembros.isEmpty()) {
            model.addAttribute("error", "No hay miembros registrados");
            return "miembro/listado";
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
    public String guardarMiembro(@Valid @ModelAttribute Miembro miembro, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "miembro/formulario";
        }
        miembroService.guardarMiembro(miembro);
        return "redirect:/miembro";
    }

    @GetMapping("/editar/{id}")
    public String editarMiembro(@PathVariable int id, Model model) {
        Miembro miembro = miembroService.obtenerMiembroPorId(id);
        if (miembro == null) {
            model.addAttribute("error", "Miembro no encontrado");
            return "redirect:/miembro";
        }
        model.addAttribute("miembro", miembro);
        return "miembro/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarMiembro(@PathVariable int id, Model model) {
        try {
            miembroService.eliminarMiembro(id);
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
        }
        return "redirect:/miembro";
    }

    @GetMapping("/reporte")
    public String reporteMiembros(Model model) {
        List<Miembro> miembros = miembroService.obtenerTodosLosMiembros();
        model.addAttribute("miembros", miembros);

        try {
            Document document = new Document();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, outputStream);
            document.open();

            Paragraph title = new Paragraph("Listado de Miembros");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            for (Miembro miembro : miembros) {
                document.add(new Paragraph("Id: " + miembro.getId()));
                document.add(new Paragraph("Tipo: " + miembro.getTipo()));
                document.add(new Paragraph("Miembro: " + miembro.getNombre()));
                document.add(new Paragraph("Descripción: " + miembro.getDescripcion()));
                document.add(new Paragraph("-----------------------------------------------------"));
            }
            document.close();

            FileOutputStream reporte = new FileOutputStream("Miembros.pdf");
            outputStream.writeTo(reporte);
            reporte.close();

            model.addAttribute("Exito", "PDF generado exitosamente.");
        } catch (Exception e) {
            model.addAttribute("Error", "Error al generar PDF: " + e.getMessage());
        }
        return "miembro/listado";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }
}

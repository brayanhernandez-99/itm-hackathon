package com.co.hackathon.itm_hackathon_web.controllers;

import com.co.hackathon.itm_hackathon_web.models.Acompanante;
import com.co.hackathon.itm_hackathon_web.services.AcompananteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.List;

@Controller
@RequestMapping("/acompanante")
public class AcompananteController {

    private final AcompananteService acompananteService;

    public AcompananteController(AcompananteService acompananteService) {
        this.acompananteService = acompananteService;
    }

    @GetMapping
    public String obtenerTodosLosAcompanantes(Model model) {
        List<Acompanante> acompanantes = acompananteService.obtenerTodosLosAcompanantes();
        model.addAttribute("acompanantes", acompanantes);
        return "acompanante/listado";
    }

    @GetMapping("/nuevo")
    public String nuevoAcompanante(Model model) {
        model.addAttribute("acompanante", new Acompanante());
        return "acompanante/formulario";
    }

    @PostMapping
    public String guardarAcompanante(@Valid @ModelAttribute Acompanante acompanante, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "acompanante/formulario";
        }
        acompananteService.guardarAcompanante(acompanante);
        return "redirect:/acompanante";
    }

    @GetMapping("/editar/{id}")
    public String editarAcompanante(@PathVariable int id, Model model) {
        Acompanante acompanante = acompananteService.obtenerAcompanantePorId(id);
        if (acompanante != null) {
            model.addAttribute("acompanante", acompanante);
            return "acompanante/formulario";
        }
        model.addAttribute("error", "Acompañante no encontrado");
        return "redirect:/acompanante";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAcompanante(@PathVariable int id, Model model) {
        boolean eliminado = acompananteService.eliminarAcompanante(id);
        if (!eliminado) {
            model.addAttribute("error", "No se pudo eliminar el acompañante");
        }
        return "redirect:/acompanante";
    }

    @GetMapping("/reporte")
    public String reporteAcompanantes(Model model) {
        List<Acompanante> acompanantes = acompananteService.obtenerTodosLosAcompanantes();
        model.addAttribute("acompanantes", acompanantes);

        try {
            Document document = new Document();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, outputStream);
            document.open();

            Paragraph title = new Paragraph("Listado de Acompañantes");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Añadir contenido de acompañantes
            for (Acompanante acompanante : acompanantes) {
                document.add(new Paragraph("Id: " + acompanante.getId()));
                document.add(new Paragraph("Nombre: " + acompanante.getNombre()));
                document.add(new Paragraph("Descripción: " + acompanante.getDescripcion()));
                document.add(new Paragraph("-----------------------------------------------------"));
            }
            document.close();

            // Guardar el archivo
            FileOutputStream reporte = new FileOutputStream("Acompañantes.pdf");
            outputStream.writeTo(reporte);
            reporte.close();

            model.addAttribute("Exito", "PDF generado exitosamente.");
        } catch (Exception e) {
            model.addAttribute("Error", "Error al generar PDF: " + e.getMessage());
        }
        return "acompanante/listado";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }
}

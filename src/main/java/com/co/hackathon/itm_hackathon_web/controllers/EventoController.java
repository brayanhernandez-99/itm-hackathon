package com.co.hackathon.itm_hackathon_web.controllers;

import com.co.hackathon.itm_hackathon_web.models.Evento;
import com.co.hackathon.itm_hackathon_web.models.Miembro;
import com.co.hackathon.itm_hackathon_web.services.EventoService;
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
@RequestMapping("/evento")
public class EventoController {

    private final EventoService eventoService;
    private final MiembroService miembroService;

    public EventoController(EventoService eventoService, MiembroService miembroService) {
        this.eventoService = eventoService;
        this.miembroService = miembroService;
    }

    @GetMapping
    public String obtenerTodosLosEventos(Model model) {
        List<Evento> eventos = eventoService.obtenerTodosLosEventos();
        if (eventos == null || eventos.isEmpty()) {
            model.addAttribute("error", "No hay eventos registrados");
            return "evento/listado";
        }
        model.addAttribute("eventos", eventos);
        return "evento/listado";
    }

    @GetMapping("/nuevo")
    public String crearEvento(Model model) {
        model.addAttribute("evento", new Evento());
        List<Miembro> miembros = miembroService.obtenerTodosLosMiembros();
        model.addAttribute("miembros", miembros);
        return "evento/formulario";
    }

    @PostMapping
    public String guardarEvento(@Valid @ModelAttribute Evento evento, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Miembro> miembros = miembroService.obtenerTodosLosMiembros();
            model.addAttribute("miembros", miembros);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "evento/formulario";
        }
        eventoService.guardarEvento(evento);
        return "redirect:/evento";
    }

    @GetMapping("/editar/{id}")
    public String editarEvento(@PathVariable int id, Model model) {
        Evento evento = eventoService.obtenerEventoPorId(id);
        if (evento == null) {
            model.addAttribute("error", "Evento no encontrado");
            return "redirect:/evento";
        }
        model.addAttribute("evento", evento);
        List<Miembro> miembros = miembroService.obtenerTodosLosMiembros();
        model.addAttribute("miembros", miembros);
        return "evento/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEvento(@PathVariable int id, Model model) {
        try {
            eventoService.eliminarEvento(id);
        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage());
        }
        return "redirect:/evento";
    }

    @GetMapping("/reporte")
    public String reporteEventos(Model model) {
        List<Evento> eventos = eventoService.obtenerTodosLosEventos();
        model.addAttribute("eventos", eventos);

        try {
            Document document = new Document();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, outputStream);
            document.open();

            Paragraph title = new Paragraph("Listado de Eventos");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            for (Evento evento : eventos) {
                document.add(new Paragraph("Id: " + evento.getId()));
                document.add(new Paragraph("Descripción: " + evento.getDescripcion()));
                document.add(new Paragraph("Organizador: " + (evento.getOrganizador() != null ? evento.getOrganizador().getNombre() : "N/A")));
                document.add(new Paragraph("Fecha: " + evento.getFecha()));
                document.add(new Paragraph("Tipo: " + evento.getTipo()));
                document.add(new Paragraph("-----------------------------------------------------"));
            }
            document.close();

            FileOutputStream reporte = new FileOutputStream("Eventos.pdf");
            outputStream.writeTo(reporte);
            reporte.close();

            model.addAttribute("Exito", "PDF generado exitosamente.");
        } catch (Exception e) {
            model.addAttribute("Error", "Error al generar PDF: " + e.getMessage());
        }
        return "evento/listado";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }
}

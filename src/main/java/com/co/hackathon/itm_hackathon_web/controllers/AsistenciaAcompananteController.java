package com.co.hackathon.itm_hackathon_web.controllers;

import com.co.hackathon.itm_hackathon_web.models.Acompanante;
import com.co.hackathon.itm_hackathon_web.models.AsistenciaAcompanante;
import com.co.hackathon.itm_hackathon_web.models.Evento;
import com.co.hackathon.itm_hackathon_web.services.AcompananteService;
import com.co.hackathon.itm_hackathon_web.services.AsistenciaAcompananteService;
import com.co.hackathon.itm_hackathon_web.services.EventoService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.List;

@Controller
@RequestMapping("/acompanante/asistencia")
public class AsistenciaAcompananteController {

    private final AsistenciaAcompananteService asistenciaAcompananteService;
    private final EventoService eventoService;
    private final AcompananteService acompananteService;

    public AsistenciaAcompananteController(AsistenciaAcompananteService asistenciaAcompananteService, EventoService eventoService, AcompananteService acompananteService) {
        this.asistenciaAcompananteService = asistenciaAcompananteService;
        this.eventoService = eventoService;
        this.acompananteService = acompananteService;
    }

    @GetMapping
    public String obtenerTodasLasAsistencias(Model model) {
        model.addAttribute("asistenciaAcompanantes", asistenciaAcompananteService.obtenerTodasLasAsistencias());
        return "acompanante/asistencia/listado";
    }

    @GetMapping("/nuevo")
    public String nuevaAsistenciaAcompanante(Model model) {
        AsistenciaAcompanante nuevaAsistencia = new AsistenciaAcompanante();
        List<Evento> eventos = eventoService.obtenerTodosLosEventos();
        List<Acompanante> acompanantes = acompananteService.obtenerTodosLosAcompanantes();
        if (!eventos.isEmpty()) {
            nuevaAsistencia.setEvento(eventos.get(0));
        }
        if (!acompanantes.isEmpty()) {
            nuevaAsistencia.setAcompanante(acompanantes.get(0));
        }
        model.addAttribute("asistenciaAcompanante", nuevaAsistencia);
        model.addAttribute("eventos", eventos);
        model.addAttribute("acompanantes", acompanantes);
        return "acompanante/asistencia/formulario";
    }

    @PostMapping
    public String guardarAsistenciaAcompanante(@Valid @ModelAttribute AsistenciaAcompanante asistenciaAcompanante,
                                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("eventos", eventoService.obtenerTodosLosEventos());
            model.addAttribute("acompanantes", acompananteService.obtenerTodosLosAcompanantes());
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "acompanante/asistencia/formulario";
        }
        asistenciaAcompananteService.guardarAsistenciaAcompanante(asistenciaAcompanante);
        return "redirect:/acompanante/asistencia";
    }

    @GetMapping("/editar/{id}")
    public String editarAsistenciaAcompanante(@PathVariable int id, Model model) {
        AsistenciaAcompanante asistencia = asistenciaAcompananteService.obtenerAsistenciaPorId(id);
        if (asistencia != null) {
            List<Evento> eventos = eventoService.obtenerTodosLosEventos();
            List<Acompanante> acompanantes = acompananteService.obtenerTodosLosAcompanantes();
            model.addAttribute("asistenciaAcompanante", asistencia);
            model.addAttribute("eventos", eventos);
            model.addAttribute("acompanantes", acompanantes);
            return "acompanante/asistencia/formulario";
        }
        model.addAttribute("error", "Asistencia no encontrada");
        return "redirect:/acompanante/asistencia";
    }

    @GetMapping("/{acompananteId}")
    public String obtenerAsistenciasPorAcompanante(@PathVariable int acompananteId, Model model) {
        model.addAttribute("asistenciaAcompanantes", asistenciaAcompananteService.obtenerAsistenciasPorAcompanante(acompananteId));
        return "acompanante/asistencia/listado";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAsistenciaAcompanante(@PathVariable int id, Model model) {
        boolean eliminada = asistenciaAcompananteService.eliminarAsistenciaAcompanante(id);
        if (!eliminada) {
            model.addAttribute("error", "No se pudo eliminar la asistencia");
        }
        return "redirect:/acompanante/asistencia";
    }

    @GetMapping("/evento/{eventoId}")
    public String obtenerAsistenciasPorEvento(@PathVariable int eventoId, Model model) {
        List<AsistenciaAcompanante> asistencias = asistenciaAcompananteService.obtenerAsistenciasPorEvento(eventoId);
        model.addAttribute("asistenciaAcompanantes", asistencias);
        return "acompanante/asistencia/listado";
    }

    @GetMapping("/reporte")
    public String reporteAistenciaAcompanantes(Model model) {
        List<AsistenciaAcompanante> asistenciaAcompanantes = asistenciaAcompananteService.obtenerTodasLasAsistencias();
        model.addAttribute("asistenciaAcompanantes", asistenciaAcompanantes);

        try {
            Document document = new Document();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, outputStream);
            document.open();

            Paragraph title = new Paragraph("Listado de Asistencia Acompañantes");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Añadir contenido de acompañantes
            for (AsistenciaAcompanante asistenciaAcompanante : asistenciaAcompanantes) {
                document.add(new Paragraph("Id: " + asistenciaAcompanante.getId()));
                document.add(new Paragraph("Evento: " + asistenciaAcompanante.getEvento().getDescripcion()));
                document.add(new Paragraph("Acompañante: " + asistenciaAcompanante.getAcompanante().getNombre()));
                document.add(new Paragraph("Asistencia Moto: " + (asistenciaAcompanante.getAsistencia_moto() ? "Si" : "No" )));
                document.add(new Paragraph("Kilometraje: " + asistenciaAcompanante.getKilometraje()));
                document.add(new Paragraph("-----------------------------------------------------"));
            }
            document.close();

            // Guardar el archivo
            FileOutputStream reporte = new FileOutputStream("AsistenciaAcompañantes.pdf");
            outputStream.writeTo(reporte);
            reporte.close();

            model.addAttribute("Exito", "PDF generado exitosamente.");
        } catch (Exception e) {
            model.addAttribute("Error", "Error al generar PDF: " + e.getMessage());
        }
        return "acompanante/asistencia/listado";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }
}
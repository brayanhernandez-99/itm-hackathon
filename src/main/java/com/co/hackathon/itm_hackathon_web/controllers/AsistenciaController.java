package com.co.hackathon.itm_hackathon_web.controllers;

import com.co.hackathon.itm_hackathon_web.models.Asistencia;
import com.co.hackathon.itm_hackathon_web.models.Evento;
import com.co.hackathon.itm_hackathon_web.models.Miembro;
import com.co.hackathon.itm_hackathon_web.services.AsistenciaService;
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
import jakarta.servlet.http.HttpServletRequest;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.List;

@Controller
@RequestMapping("/asistencia")
public class AsistenciaController {

    private final AsistenciaService asistenciaService;
    private final EventoService eventoService;
    private final MiembroService miembroService;

    public AsistenciaController(AsistenciaService asistenciaService, EventoService eventoService, MiembroService miembroService) {
        this.asistenciaService = asistenciaService;
        this.eventoService = eventoService;
        this.miembroService = miembroService;
    }

    @GetMapping
    public String obtenerTodasLasAsistencias(Model model) {
        List<Asistencia> asistencias = asistenciaService.obtenerTodasLasAsistencias();
        if (asistencias == null || asistencias.isEmpty()) {
            model.addAttribute("error", "No hay asistencias registradas");
            return "asistencia/listado";
        }
        model.addAttribute("asistencias", asistencias);
        return "asistencia/listado";
    }

    @GetMapping("/evento/{eventoId}")
    public String listarAsistenciasPorEvento(@PathVariable int eventoId, Model model, HttpServletRequest request) {
        Evento evento = eventoService.obtenerEventoPorId(eventoId);
        if (evento == null) {
            model.addAttribute("error", "Evento no encontrado");
            return "asistencia/listado";
        }
        List<Asistencia> asistencias = asistenciaService.obtenerAsistenciasPorEvento(eventoId);
        model.addAttribute("evento", evento);
        model.addAttribute("asistencias", asistencias);
        return "asistencia/listado";
    }

    @GetMapping("/miembro/{miembroId}")
    public String listarAsistenciasPorMiembro(@PathVariable int miembroId, Model model) {
        Miembro miembro = miembroService.obtenerMiembroPorId(miembroId);
        if (miembro == null) {
            model.addAttribute("error", "Miembro no encontrado");
            return "asistencia/listado";
        }
        List<Asistencia> asistencias = asistenciaService.obtenerAsistenciasPorMiembro(miembroId);
        model.addAttribute("miembro", miembro);
        model.addAttribute("asistencias", asistencias);
        return "asistencia/listado";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("asistencia", new Asistencia());
        model.addAttribute("eventos", eventoService.obtenerTodosLosEventos());
        model.addAttribute("miembros", miembroService.obtenerTodosLosMiembros());
        return "asistencia/formulario";
    }

    @PostMapping
    public String guardarAsistencia(@Valid @ModelAttribute Asistencia asistencia, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("eventos", eventoService.obtenerTodosLosEventos());
            model.addAttribute("miembros", miembroService.obtenerTodosLosMiembros());
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "asistencia/formulario";
        }
        asistenciaService.guardarAsistencia(asistencia);
        return "redirect:/asistencia";
    }

    @GetMapping("/editar/{id}")
    public String editarAsistencia(@PathVariable int id, Model model) {
        Asistencia asistencia = asistenciaService.obtenerAsistenciaPorId(id);
        if (asistencia == null) {
            model.addAttribute("error", "Asistencia no encontrada");
            return "redirect:/asistencia";
        }
        model.addAttribute("asistencia", asistencia);
        model.addAttribute("eventos", eventoService.obtenerTodosLosEventos());
        model.addAttribute("miembros", miembroService.obtenerTodosLosMiembros());
        return "asistencia/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAsistencia(@PathVariable int id, Model model) {
        if (!asistenciaService.eliminarAsistencia(id)) {
            model.addAttribute("error", "No se pudo eliminar la asistencia");
        }
        return "redirect:/asistencia";
    }

    @GetMapping("/reporte")
    public String reporteAsistencias(Model model) {
        List<Asistencia> asistencias = asistenciaService.obtenerTodasLasAsistencias();
        model.addAttribute("asistencias", asistencias);

        try {
            Document document = new Document();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, outputStream);
            document.open();

            Paragraph title = new Paragraph("Listado de Asistencias");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Añadir contenido de acompañantes
            for (Asistencia asistencia : asistencias) {
                document.add(new Paragraph("Id: " + asistencia.getId()));
                document.add(new Paragraph("Evento Fecha: " + asistencia.getEvento().getFecha()));
                document.add(new Paragraph("Evento Descripción: " + asistencia.getEvento().getDescripcion()));
                document.add(new Paragraph("Miembro: " + asistencia.getMiembro().getNombre()));
                document.add(new Paragraph("Asistencia Moto: " + (asistencia.getAsistencia_moto() ? "Si" : "No")));
                document.add(new Paragraph("Kilometraje: " + asistencia.getKilometraje()));
                document.add(new Paragraph("-----------------------------------------------------"));
            }
            document.close();

            // Guardar el archivo
            FileOutputStream reporte = new FileOutputStream("Asistencias.pdf");
            outputStream.writeTo(reporte);
            reporte.close();

            model.addAttribute("Exito", "PDF generado exitosamente.");
        } catch (Exception e) {
            model.addAttribute("Error", "Error al generar PDF: " + e.getMessage());
        }
        return "asistencia/listado";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }
}
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Controlador para manejar las operaciones relacionadas con la asistencia de acompañantes.
 */
@Controller
@RequestMapping("/acompanante/asistencia")
public class AsistenciaAcompananteController {
    private final AsistenciaAcompananteService asistenciaAcompananteService;
    private final EventoService eventoService;
    private final AcompananteService acompananteService;

    /**
     * Constructor que inyecta el servicio de AsistenciaAcompananteService.
     *
     * @param asistenciaAcompananteService Servicio para manejar la lógica de negocio de la asistencia de acompañantes.
     */
    public AsistenciaAcompananteController(AsistenciaAcompananteService asistenciaAcompananteService, EventoService eventoService, AcompananteService acompananteService) {
        this.asistenciaAcompananteService = asistenciaAcompananteService;
        this.eventoService = eventoService;
        this.acompananteService = acompananteService;
    }

    /**
     * Método para obtener y mostrar todas las asistencias registradas.
     *
     * @param model Modelo de datos para la vista.
     * @return Vista con la lista de asistencias de los acompañantes.
     */
    @GetMapping
    public String obtenerTodasLasAsistenciasOld(Model model) {
        model.addAttribute("asistenciaAcompanantes", asistenciaAcompananteService.obtenerTodasLasAsistencias());
        return "acompanante/asistencia/listado";
    }

    /**
     * Método para mostrar el formulario de registro de una nueva asistencia de acompañante.
     *
     * @param model Modelo de datos para la vista.
     * @return Vista del formulario de creación de asistencia.
     */
    @GetMapping("/nuevo")
    public String nuevaAsistenciaAcompanante(Model model) {
        AsistenciaAcompanante nuevaAsistencia = new AsistenciaAcompanante();

        // Establece valores por defecto, por ejemplo, el primer evento y acompañante disponibles
        List<Evento> eventos = eventoService.obtenerTodosLosEventos();
        List<Acompanante> acompanantes = acompananteService.obtenerTodosLosAcompanantes();

        if (!eventos.isEmpty()) {
            nuevaAsistencia.setEvento(eventos.get(0));  // Primer evento como predeterminado
        }
        if (!acompanantes.isEmpty()) {
            nuevaAsistencia.setAcompanante(acompanantes.get(0)); // Primer acompañante como predeterminado
        }

        model.addAttribute("asistenciaAcompanante", nuevaAsistencia);
        model.addAttribute("eventos", eventos);
        model.addAttribute("acompanantes", acompanantes);

        return "acompanante/asistencia/formulario";
    }

    /**
     * Método para guardar una nueva asistencia de acompañante desde el formulario.
     *
     * @param asistenciaAcompanante Objeto asistencia recibido desde el formulario.
     * @return Redirección a la lista de asistencias.
     */
    @PostMapping
    public String guardarAsistenciaAcompanante(@ModelAttribute AsistenciaAcompanante asistenciaAcompanante) {
        asistenciaAcompananteService.guardarAsistenciaAcompanante(asistenciaAcompanante);
        return "redirect:/acompanante/asistencia";
    }

    /**
     * Método para mostrar el formulario de edición de una asistencia.
     *
     * @param id    Identificador de la asistencia a editar.
     * @param model Modelo de datos para la vista.
     * @return Vista del formulario con los datos de la asistencia o redirección si no existe.
     */
    @GetMapping("/editar/{id}")
    public String editarAsistenciaAcompanante(@PathVariable int id, Model model) {
        AsistenciaAcompanante asistencia = asistenciaAcompananteService.obtenerAsistenciaPorId(id);
        if (asistencia != null) {
            List<Evento> eventos = eventoService.obtenerTodosLosEventos();
            List<Acompanante> acompanantes = acompananteService.obtenerTodosLosAcompanantes();

            model.addAttribute("asistenciaAcompanante", asistencia);
            model.addAttribute("eventos", eventos);
            model.addAttribute("acompanantes", acompanantes);

            return "acompanante/asistencia/formulario"; // Redirige al formulario con los datos
        }
        return "redirect:/acompanante/asistencia"; // Si no encuentra el ID, vuelve al listado
    }

    /**
     * Método para obtener las asistencias de un acompañante específico.
     *
     * @param acompananteId Identificador del acompañante.
     * @param model         Modelo de datos para la vista.
     * @return Vista con la lista de asistencias del acompañante.
     */
    @GetMapping("/{acompananteId}")
    public String obtenerAsistenciasPorAcompanante(@PathVariable int acompananteId, Model model) {
        model.addAttribute("asistenciaAcompanantes", asistenciaAcompananteService.obtenerAsistenciasPorAcompanante(acompananteId));
        return "acompanante/asistencia/listado";
    }

    /**
     * Método para eliminar una asistencia de acompañante y redirigir a la lista.
     *
     * @param id Identificador de la asistencia a eliminar.
     * @return Redirección a la lista de asistencias.
     */
    @GetMapping("/eliminar/{id}")
    public String eliminarAsistenciaAcompanante(@PathVariable int id) {
        asistenciaAcompananteService.eliminarAsistenciaAcompanante(id);
        return "redirect:/acompanante/asistencia";
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
}

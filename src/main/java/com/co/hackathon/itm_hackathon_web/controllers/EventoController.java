package com.co.hackathon.itm_hackathon_web.controllers;

import com.co.hackathon.itm_hackathon_web.models.Evento;
import com.co.hackathon.itm_hackathon_web.models.Miembro;
import com.co.hackathon.itm_hackathon_web.services.EventoService;
import com.co.hackathon.itm_hackathon_web.services.MiembroService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Controlador para gestionar los eventos dentro del sistema.
 */
@Controller
@RequestMapping("/evento")
public class EventoController {

    private final EventoService eventoService;
    private final MiembroService miembroService;

    /**
     * Constructor que inyecta los servicios necesarios.
     *
     * @param eventoService Servicio para la gestión de eventos.
     * @param miembroService Servicio para la gestión de miembros.
     */
    public EventoController(EventoService eventoService, MiembroService miembroService) {
        this.eventoService = eventoService;
        this.miembroService = miembroService;
    }

    /**
     * Método para obtener y mostrar todos los eventos registrados.
     *
     * @param model Modelo de datos para la vista.
     * @return Vista con la lista de eventos.
     */
    @GetMapping
    public String obtenerTodosLosEventos(Model model) {
        List<Evento> eventos = eventoService.obtenerTodosLosEventos();
        if (eventos == null || eventos.isEmpty()) {
            throw new RuntimeException("No hay eventos registrados");
        }
        model.addAttribute("eventos", eventos);
        return "evento/listado";  // Vista de listado de eventos
    }

    /**
     * Método para mostrar el formulario de creación de un nuevo evento.
     *
     * @param model Modelo de datos para la vista.
     * @return Vista del formulario de creación de evento.
     */
    @GetMapping("/nuevo")
    public String crearEvento(Model model) {
        model.addAttribute("evento", new Evento());
        List<Miembro> miembros = miembroService.obtenerTodosLosMiembros();  // Obtener todos los miembros
        model.addAttribute("miembros", miembros);
        return "evento/formulario";  // Vista para añadir evento
    }

    /**
     * Método para guardar un nuevo evento desde el formulario.
     *
     * @param evento Objeto evento recibido desde el formulario.
     * @return Redirección a la lista de eventos.
     */
    @PostMapping
    public String guardarEvento(@ModelAttribute Evento evento) {
        eventoService.guardarEvento(evento);
        return "redirect:/evento";
    }

    /**
     * Método para mostrar el formulario de edición de un evento.
     *
     * @param id Identificador del evento a editar.
     * @param model Modelo de datos para la vista.
     * @return Vista del formulario con los datos del evento.
     */
    @GetMapping("/editar/{id}")
    public String editarEvento(@PathVariable int id, Model model) {
        Evento evento = eventoService.obtenerEventoPorId(id);
        model.addAttribute("evento", evento);
        List<Miembro> miembros = miembroService.obtenerTodosLosMiembros();  // Obtener todos los miembros
        model.addAttribute("miembros", miembros);
        return "evento/formulario";  // Vista de formulario para editar evento
    }

    /**
     * Método para eliminar un evento.
     *
     * @param id Identificador del evento a eliminar.
     * @return Redirección a la lista de eventos.
     */
    @GetMapping("/eliminar/{id}")
    public String eliminarEvento(@PathVariable int id) {
        eventoService.eliminarEvento(id);
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

            // Añadir contenido de acompañantes
            for (Evento evento : eventos) {
                document.add(new Paragraph("Id: " + evento.getId()));
                document.add(new Paragraph("Descripción: " + evento.getDescripcion()));
                document.add(new Paragraph("Organizador: " + evento.getOrganizador().getNombre()));
                document.add(new Paragraph("Fecha: " + evento.getFecha()));
                document.add(new Paragraph("Tipo: " + evento.getTipo()));
                document.add(new Paragraph("-----------------------------------------------------"));
            }
            document.close();

            // Guardar el archivo
            FileOutputStream reporte = new FileOutputStream("Eventos.pdf");
            outputStream.writeTo(reporte);
            reporte.close();

            model.addAttribute("Exito", "PDF generado exitosamente.");
        } catch (Exception e) {
            model.addAttribute("Error", "Error al generar PDF: " + e.getMessage());
        }
        return "evento/listado";
    }
}

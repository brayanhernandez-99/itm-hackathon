package com.co.hackathon.itm_hackathon_web.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.co.hackathon.itm_hackathon_web.models.Evento;
import com.co.hackathon.itm_hackathon_web.repositories.EventoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class EventoServiceTest {

    @Mock
    private EventoRepository eventoRepository;

    @InjectMocks
    private EventoService eventoService;

    private Evento evento;

    @BeforeEach
    void setUp() {
        evento = new Evento();
        evento.setId(1);
        evento.setDescripcion("Hackathon ITM");
        evento.setFecha(LocalDate.now());
    }

    @Test
    void testObtenerEventoPorId_Existente() {
        when(eventoRepository.findByIdOrThrow(1)).thenReturn(evento);
        Evento resultado = eventoService.obtenerEventoPorId(1);
        assertNotNull(resultado);
        assertEquals(evento.getDescripcion(), resultado.getDescripcion());
        verify(eventoRepository, times(1)).findByIdOrThrow(1);
    }

    @Test
    void testObtenerEventoPorId_NoExistente() {
        when(eventoRepository.findByIdOrThrow(1))
                .thenThrow(new RuntimeException("Evento no encontrado"));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            eventoService.obtenerEventoPorId(1);
        });
        assertEquals("Evento no encontrado", exception.getMessage());
        verify(eventoRepository, times(1)).findByIdOrThrow(1);
    }

    @Test
    void testGuardarEvento() {
        when(eventoRepository.save(evento)).thenReturn(evento);
        eventoService.guardarEvento(evento);
        verify(eventoRepository, times(1)).save(evento);
    }

    @Test
    void testObtenerTodosLosEventos() {
        Evento evento2 = new Evento();
        evento2.setId(2);
        evento2.setDescripcion("Workshop de Spring Boot");
        evento2.setFecha(LocalDate.now());
        when(eventoRepository.findAll()).thenReturn(Arrays.asList(evento, evento2));
        List<Evento> eventos = eventoService.obtenerTodosLosEventos();
        assertNotNull(eventos);
        assertEquals(2, eventos.size());
        verify(eventoRepository, times(1)).findAll();
    }

    @Test
    void testEliminarEvento_Existente() {
        when(eventoRepository.findByIdOrThrow(1)).thenReturn(evento);
        doNothing().when(eventoRepository).delete(evento);
        // El método eliminarEvento no retorna valor, solo se verifica que se realice la eliminación.
        eventoService.eliminarEvento(1);
        verify(eventoRepository, times(1)).findByIdOrThrow(1);
        verify(eventoRepository, times(1)).delete(evento);
    }

    @Test
    void testEliminarEvento_NoExistente() {
        when(eventoRepository.findByIdOrThrow(1))
                .thenThrow(new RuntimeException("Evento no encontrado"));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            eventoService.eliminarEvento(1);
        });
        assertEquals("Evento no encontrado", exception.getMessage());
        verify(eventoRepository, times(1)).findByIdOrThrow(1);
        verify(eventoRepository, never()).delete(any(Evento.class));
    }
}

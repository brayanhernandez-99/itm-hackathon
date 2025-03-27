package com.co.hackathon.itm_hackathon_web.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.co.hackathon.itm_hackathon_web.models.AsistenciaAcompanante;
import com.co.hackathon.itm_hackathon_web.repositories.AsistenciaAcompananteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AsistenciaAcompananteServiceTest {

    @Mock
    private AsistenciaAcompananteRepository asistenciaAcompananteRepository;

    @InjectMocks
    private AsistenciaAcompananteService asistenciaAcompananteService;

    private AsistenciaAcompanante asistencia;

    @BeforeEach
    void setUp() {
        asistencia = new AsistenciaAcompanante();
        asistencia.setId(1);
        // Inicializar otros atributos según el modelo, por ejemplo:
        // asistencia.setAcompananteId(10);
        // asistencia.setEventoId(20);
    }

    @Test
    void testObtenerTodasLasAsistencias() {
        AsistenciaAcompanante asistencia2 = new AsistenciaAcompanante();
        asistencia2.setId(2);
        when(asistenciaAcompananteRepository.findAll()).thenReturn(Arrays.asList(asistencia, asistencia2));
        List<AsistenciaAcompanante> asistencias = asistenciaAcompananteService.obtenerTodasLasAsistencias();
        assertNotNull(asistencias);
        assertEquals(2, asistencias.size());
        verify(asistenciaAcompananteRepository, times(1)).findAll();
    }

    @Test
    void testGuardarAsistenciaAcompanante() {
        when(asistenciaAcompananteRepository.save(asistencia)).thenReturn(asistencia);
        asistenciaAcompananteService.guardarAsistenciaAcompanante(asistencia);
        verify(asistenciaAcompananteRepository, times(1)).save(asistencia);
    }

    @Test
    void testObtenerAsistenciasPorAcompanante() {
        when(asistenciaAcompananteRepository.findByAcompananteId(10))
                .thenReturn(Arrays.asList(asistencia));
        List<AsistenciaAcompanante> asistencias = asistenciaAcompananteService.obtenerAsistenciasPorAcompanante(10);
        assertNotNull(asistencias);
        assertEquals(1, asistencias.size());
        verify(asistenciaAcompananteRepository, times(1)).findByAcompananteId(10);
    }

    @Test
    void testObtenerAsistenciasPorEvento() {
        when(asistenciaAcompananteRepository.findByEventoId(20))
                .thenReturn(Arrays.asList(asistencia));
        List<AsistenciaAcompanante> asistencias = asistenciaAcompananteService.obtenerAsistenciasPorEvento(20);
        assertNotNull(asistencias);
        assertEquals(1, asistencias.size());
        verify(asistenciaAcompananteRepository, times(1)).findByEventoId(20);
    }

    @Test
    void testObtenerAsistenciaPorId_Existente() {
        when(asistenciaAcompananteRepository.findByIdOrThrow(1)).thenReturn(asistencia);
        AsistenciaAcompanante resultado = asistenciaAcompananteService.obtenerAsistenciaPorId(1);
        assertNotNull(resultado);
        assertEquals(asistencia.getId(), resultado.getId());
        verify(asistenciaAcompananteRepository, times(1)).findByIdOrThrow(1);
    }

    @Test
    void testObtenerAsistenciaPorId_NoExistente() {
        when(asistenciaAcompananteRepository.findByIdOrThrow(1))
                .thenThrow(new RuntimeException("Asistencia de acompañante no encontrada"));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            asistenciaAcompananteService.obtenerAsistenciaPorId(1);
        });
        assertEquals("Asistencia de acompañante no encontrada", exception.getMessage());
        verify(asistenciaAcompananteRepository, times(1)).findByIdOrThrow(1);
    }

    @Test
    void testEliminarAsistenciaAcompanante_Existente() {
        when(asistenciaAcompananteRepository.existsById(1)).thenReturn(true);
        when(asistenciaAcompananteRepository.findByIdOrThrow(1)).thenReturn(asistencia);
        doNothing().when(asistenciaAcompananteRepository).delete(asistencia);
        boolean eliminado = asistenciaAcompananteService.eliminarAsistenciaAcompanante(1);
        assertTrue(eliminado);
        verify(asistenciaAcompananteRepository, times(1)).existsById(1);
        verify(asistenciaAcompananteRepository, times(1)).findByIdOrThrow(1);
        verify(asistenciaAcompananteRepository, times(1)).delete(asistencia);
    }

    @Test
    void testEliminarAsistenciaAcompanante_NoExistente() {
        when(asistenciaAcompananteRepository.existsById(1)).thenReturn(false);
        boolean eliminado = asistenciaAcompananteService.eliminarAsistenciaAcompanante(1);
        assertFalse(eliminado);
        verify(asistenciaAcompananteRepository, times(1)).existsById(1);
        verify(asistenciaAcompananteRepository, never()).delete(any(AsistenciaAcompanante.class));
    }
}

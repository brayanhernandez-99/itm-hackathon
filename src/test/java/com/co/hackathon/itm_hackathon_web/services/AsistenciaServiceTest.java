package com.co.hackathon.itm_hackathon_web.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.co.hackathon.itm_hackathon_web.models.Asistencia;
import com.co.hackathon.itm_hackathon_web.repositories.AsistenciaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AsistenciaServiceTest {

    @Mock
    private AsistenciaRepository asistenciaRepository;

    @InjectMocks
    private AsistenciaService asistenciaService;

    private Asistencia asistencia;

    @BeforeEach
    void setUp() {
        asistencia = new Asistencia();
        asistencia.setId(1);
    }

    @Test
    void testObtenerAsistenciaPorId() {
        when(asistenciaRepository.findById(1)).thenReturn(Optional.of(asistencia));
        Asistencia resultado = asistenciaService.obtenerAsistenciaPorId(1);
        assertNotNull(resultado);
        assertEquals(asistencia.getId(), resultado.getId());
        verify(asistenciaRepository, times(1)).findById(1);
    }

    @Test
    void testObtenerAsistenciaPorId_NoExistente() {
        when(asistenciaRepository.findById(1)).thenReturn(Optional.empty());
        Asistencia resultado = asistenciaService.obtenerAsistenciaPorId(1);
        assertNull(resultado);
        verify(asistenciaRepository, times(1)).findById(1);
    }

    @Test
    void testGuardarAsistencia() {
        when(asistenciaRepository.save(asistencia)).thenReturn(asistencia);
        asistenciaService.guardarAsistencia(asistencia);
        verify(asistenciaRepository, times(1)).save(asistencia);
    }

    @Test
    void testObtenerTodasLasAsistencias() {
        Asistencia asistencia2 = new Asistencia();
        asistencia2.setId(2);
        when(asistenciaRepository.findAll()).thenReturn(Arrays.asList(asistencia, asistencia2));
        List<Asistencia> asistencias = asistenciaService.obtenerTodasLasAsistencias();
        assertNotNull(asistencias);
        assertEquals(2, asistencias.size());
        verify(asistenciaRepository, times(1)).findAll();
    }

    @Test
    void testObtenerAsistenciasPorEvento() {
        Asistencia asistencia2 = new Asistencia();
        asistencia2.setId(2);
        when(asistenciaRepository.findByEventoId(1)).thenReturn(Arrays.asList(asistencia, asistencia2));
        List<Asistencia> asistencias = asistenciaService.obtenerAsistenciasPorEvento(1);
        assertNotNull(asistencias);
        assertEquals(2, asistencias.size());
        verify(asistenciaRepository, times(1)).findByEventoId(1);
    }

    @Test
    void testObtenerAsistenciasPorMiembro() {
        Asistencia asistencia2 = new Asistencia();
        asistencia2.setId(2);
        when(asistenciaRepository.findByMiembroId(1)).thenReturn(Arrays.asList(asistencia, asistencia2));
        List<Asistencia> asistencias = asistenciaService.obtenerAsistenciasPorMiembro(1);
        assertNotNull(asistencias);
        assertEquals(2, asistencias.size());
        verify(asistenciaRepository, times(1)).findByMiembroId(1);
    }

    @Test
    void testEliminarAsistencia_Existente() {
        when(asistenciaRepository.existsById(1)).thenReturn(true);
        doNothing().when(asistenciaRepository).deleteById(1);
        boolean eliminado = asistenciaService.eliminarAsistencia(1);
        assertTrue(eliminado);
        verify(asistenciaRepository, times(1)).existsById(1);
        verify(asistenciaRepository, times(1)).deleteById(1);
    }

    @Test
    void testEliminarAsistencia_NoExistente() {
        when(asistenciaRepository.existsById(1)).thenReturn(false);
        boolean eliminado = asistenciaService.eliminarAsistencia(1);
        assertFalse(eliminado);
        verify(asistenciaRepository, times(1)).existsById(1);
        verify(asistenciaRepository, never()).deleteById(anyInt());
    }
}

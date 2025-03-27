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
    void testObtenerAsistenciaPorId_Existente() {
        when(asistenciaRepository.findByIdOrThrow(1)).thenReturn(asistencia);
        Asistencia resultado = asistenciaService.obtenerAsistenciaPorId(1);
        assertNotNull(resultado);
        assertEquals(asistencia.getId(), resultado.getId());
        verify(asistenciaRepository, times(1)).findByIdOrThrow(1);
    }

    @Test
    void testObtenerAsistenciaPorId_NoExistente() {
        when(asistenciaRepository.findByIdOrThrow(1))
                .thenThrow(new RuntimeException("Asistencia no encontrada"));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            asistenciaService.obtenerAsistenciaPorId(1);
        });
        assertEquals("Asistencia no encontrada", exception.getMessage());
        verify(asistenciaRepository, times(1)).findByIdOrThrow(1);
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
        when(asistenciaRepository.findByIdOrThrow(1)).thenReturn(asistencia);
        doNothing().when(asistenciaRepository).delete(asistencia);
        boolean eliminado = asistenciaService.eliminarAsistencia(1);
        assertTrue(eliminado);
        verify(asistenciaRepository, times(1)).existsById(1);
        verify(asistenciaRepository, times(1)).findByIdOrThrow(1);
        verify(asistenciaRepository, times(1)).delete(asistencia);
    }

    @Test
    void testEliminarAsistencia_NoExistente() {
        when(asistenciaRepository.existsById(1)).thenReturn(false);
        boolean eliminado = asistenciaService.eliminarAsistencia(1);
        assertFalse(eliminado);
        verify(asistenciaRepository, times(1)).existsById(1);
        verify(asistenciaRepository, never()).delete(any(Asistencia.class));
    }
}

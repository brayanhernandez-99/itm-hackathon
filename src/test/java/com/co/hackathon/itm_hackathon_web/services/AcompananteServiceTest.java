package com.co.hackathon.itm_hackathon_web.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.co.hackathon.itm_hackathon_web.models.Acompanante;
import com.co.hackathon.itm_hackathon_web.repositories.AcompananteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AcompananteServiceTest {

    @Mock
    private AcompananteRepository acompananteRepository;

    @InjectMocks
    private AcompananteService acompananteService;

    private Acompanante acompanante;

    @BeforeEach
    void setUp() {
        acompanante = new Acompanante();
        acompanante.setId(1);
        acompanante.setNombre("Juan Pérez");
    }

    @Test
    void testObtenerAcompanantePorId_Existente() {
        when(acompananteRepository.findByIdOrThrow(1)).thenReturn(acompanante);
        Acompanante resultado = acompananteService.obtenerAcompanantePorId(1);
        assertNotNull(resultado);
        assertEquals(acompanante.getNombre(), resultado.getNombre());
        verify(acompananteRepository, times(1)).findByIdOrThrow(1);
    }

    @Test
    void testObtenerAcompanantePorId_NoExistente() {
        when(acompananteRepository.findByIdOrThrow(1)).thenThrow(new RuntimeException("Acompañante no encontrado"));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            acompananteService.obtenerAcompanantePorId(1);
        });
        assertEquals("Acompañante no encontrado", exception.getMessage());
        verify(acompananteRepository, times(1)).findByIdOrThrow(1);
    }

    @Test
    void testGuardarAcompanante() {
        when(acompananteRepository.save(acompanante)).thenReturn(acompanante);
        acompananteService.guardarAcompanante(acompanante);
        verify(acompananteRepository, times(1)).save(acompanante);
    }

    @Test
    void testObtenerTodosLosAcompanantes() {
        Acompanante acompanante2 = new Acompanante();
        acompanante2.setId(2);
        acompanante2.setNombre("María González");
        when(acompananteRepository.findAll()).thenReturn(Arrays.asList(acompanante, acompanante2));
        List<Acompanante> acompanantes = acompananteService.obtenerTodosLosAcompanantes();
        assertNotNull(acompanantes);
        assertEquals(2, acompanantes.size());
        verify(acompananteRepository, times(1)).findAll();
    }

    @Test
    void testEliminarAcompanante_Existente() {
        when(acompananteRepository.existsById(1)).thenReturn(true);
        when(acompananteRepository.findByIdOrThrow(1)).thenReturn(acompanante);
        doNothing().when(acompananteRepository).delete(acompanante);
        boolean eliminado = acompananteService.eliminarAcompanante(1);
        assertTrue(eliminado);
        verify(acompananteRepository, times(1)).existsById(1);
        verify(acompananteRepository, times(1)).findByIdOrThrow(1);
        verify(acompananteRepository, times(1)).delete(acompanante);
    }

    @Test
    void testEliminarAcompanante_NoExistente() {
        when(acompananteRepository.existsById(1)).thenReturn(false);
        boolean eliminado = acompananteService.eliminarAcompanante(1);
        assertFalse(eliminado);
        verify(acompananteRepository, times(1)).existsById(1);
        verify(acompananteRepository, never()).delete(any(Acompanante.class));
    }
}

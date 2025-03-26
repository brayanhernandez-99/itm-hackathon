package com.co.hackathon.itm_hackathon_web.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.co.hackathon.itm_hackathon_web.models.Miembro;
import com.co.hackathon.itm_hackathon_web.repositories.MiembroRepository;
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
class MiembroServiceTest {

    @Mock
    private MiembroRepository miembroRepository;

    @InjectMocks
    private MiembroService miembroService;

    private Miembro miembro;

    @BeforeEach
    void setUp() {
        miembro = new Miembro();
        miembro.setId(1);
        miembro.setNombre("Ana López");
    }

    @Test
    void testObtenerMiembroPorId() {
        when(miembroRepository.findById(1)).thenReturn(Optional.of(miembro));
        Miembro resultado = miembroService.obtenerMiembroPorId(1);
        assertNotNull(resultado);
        assertEquals(miembro.getNombre(), resultado.getNombre());
        verify(miembroRepository, times(1)).findById(1);
    }

    @Test
    void testObtenerMiembroPorId_NoExistente() {
        when(miembroRepository.findById(1)).thenReturn(Optional.empty());
        Miembro resultado = miembroService.obtenerMiembroPorId(1);
        assertNull(resultado);
        verify(miembroRepository, times(1)).findById(1);
    }

    @Test
    void testGuardarMiembro() {
        when(miembroRepository.save(miembro)).thenReturn(miembro);
        miembroService.guardarMiembro(miembro);
        verify(miembroRepository, times(1)).save(miembro);
    }

    @Test
    void testObtenerTodosLosMiembros() {
        Miembro miembro2 = new Miembro();
        miembro2.setId(2);
        miembro2.setNombre("Carlos Pérez");
        when(miembroRepository.findAll()).thenReturn(Arrays.asList(miembro, miembro2));
        List<Miembro> miembros = miembroService.obtenerTodosLosMiembros();
        assertNotNull(miembros);
        assertEquals(2, miembros.size());
        verify(miembroRepository, times(1)).findAll();
    }

    @Test
    void testEliminarMiembro() {
        doNothing().when(miembroRepository).deleteById(1);
        miembroService.eliminarMiembro(1);
        verify(miembroRepository, times(1)).deleteById(1);
    }
}

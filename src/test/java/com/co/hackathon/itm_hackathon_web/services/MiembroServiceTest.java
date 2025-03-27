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
    void testObtenerMiembroPorId_Existente() {
        when(miembroRepository.findByIdOrThrow(1)).thenReturn(miembro);
        Miembro resultado = miembroService.obtenerMiembroPorId(1);
        assertNotNull(resultado);
        assertEquals(miembro.getNombre(), resultado.getNombre());
        verify(miembroRepository, times(1)).findByIdOrThrow(1);
    }

    @Test
    void testObtenerMiembroPorId_NoExistente() {
        when(miembroRepository.findByIdOrThrow(1))
                .thenThrow(new RuntimeException("Miembro no encontrado"));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            miembroService.obtenerMiembroPorId(1);
        });
        assertEquals("Miembro no encontrado", exception.getMessage());
        verify(miembroRepository, times(1)).findByIdOrThrow(1);
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
        when(miembroRepository.findByIdOrThrow(1)).thenReturn(miembro);
        doNothing().when(miembroRepository).delete(miembro);
        miembroService.eliminarMiembro(1);
        verify(miembroRepository, times(1)).findByIdOrThrow(1);
        verify(miembroRepository, times(1)).delete(miembro);
    }
}

package com.saudemais.service;

import com.saudemais.model.Consulta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ConsultaLookupServiceTest {

    @Mock
    private IConsultaService mockService;

    @InjectMocks
    private ConsultaLookupService lookup;

    @Test
    public void testBuscarPorId_encontrouConsulta() {
        // Simula o retorno do service.listar()
        Consulta c1 = new Consulta("Maria Silva", "2025-01-01", "14:00", "Dor no peito","cardiologista");
        Consulta c2 = new Consulta("João", "12/02/2025", "13:00","Fraqueza","nutricionista");


        when(mockService.listar()).thenReturn(List.of(c1, c2));

        // Searching for existing id (Consulta auto increments id)
        Consulta result = lookup.buscarPorId(c1.getId());

        assertNotNull(result);
        assertEquals(c1.getId(), result.getId());

        verify(mockService, times(1)).listar();
    }

    @Test
    public void testBuscarPorId_naoEncontrou() {
        when(mockService.listar()).thenReturn(List.of());

        Consulta result = lookup.buscarPorId(999);

        assertNull(result);
        verify(mockService, times(1)).listar();
    }
}

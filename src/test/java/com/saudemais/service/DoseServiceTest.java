package com.saudemais.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DoseServiceTest {

    @Mock
    private DoseRepository doseRepository;

    @InjectMocks
    private DoseService doseService;

    @Test
    public void testCalcularTotalComMockito() {
        // Arrange (configura comportamento do mock)
        when(doseRepository.getVezesAoDia("Dipirona")).thenReturn(3);

        // Act (executa o método real)
        int total = doseService.calcularTotal("Dipirona", 4);

        // Assert (verifica resultado)
        assertEquals(12, total);

        // Verifica se o repository foi realmente chamado
        verify(doseRepository, times(1)).getVezesAoDia("Dipirona");
    }

    @Test
    public void testDiasNegativosLancaErro() {
        assertThrows(IllegalArgumentException.class, () -> {
            doseService.calcularTotal("Dipirona", -5);
        });
    }
}

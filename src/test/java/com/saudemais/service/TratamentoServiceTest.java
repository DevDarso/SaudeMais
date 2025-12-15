package com.saudemais.service;
import com.saudemais.model.Consulta;
import com.saudemais.model.Tratamento;
import com.saudemais.model.TratamentoDTO;
import com.saudemais.repository.ConsultaRepository;
import com.saudemais.repository.TratamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TratamentoServiceTest {

    @InjectMocks
    private TratamentoService tratamentoService;

    @Mock
    private TratamentoRepository tratamentoRepository;

    @Mock
    private ConsultaRepository consultaRepository;

    @Test
    void deveSalvarTratamentoComDatasCalculadasCorretamente() {

        // ===== DADOS DE ENTRADA =====
        Long consultaId = 1L;

        TratamentoDTO dto = new TratamentoDTO();
        dto.setConsultaId(consultaId);
        dto.setMedicamento("Paracetamol");
        dto.setDosagem("500mg");
        dto.setVezesAoDia(3);
        dto.setDias(5);
        dto.setDataInicio("2025-12-10");

        Consulta consulta = new Consulta();
        consulta.setId(consultaId);

        // ===== MOCKS =====
        when(consultaRepository.findById(consultaId))
                .thenReturn(Optional.of(consulta));

        when(tratamentoRepository.save(any(Tratamento.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // ===== EXECUÇÃO =====
        Tratamento resultado = tratamentoService.salvar(dto);

        // ===== VERIFICAÇÕES =====
        assertNotNull(resultado);
        assertEquals("Paracetamol", resultado.getNomeMedicamento());
        assertEquals("500mg", resultado.getDosagem());
        assertEquals(3, resultado.getVezesAoDia());
        assertEquals(5, resultado.getQuantidadeDias());

        LocalDate inicioEsperado = LocalDate.of(2025, 12, 10);
        LocalDate fimEsperado = inicioEsperado.plusDays(5);

        assertEquals(inicioEsperado, resultado.getDataInicio());
        assertEquals(fimEsperado, resultado.getDataFim());
        assertEquals(consulta, resultado.getConsulta());

        // ===== VERIFICA INTERAÇÕES =====
        verify(consultaRepository, times(1)).findById(consultaId);
        verify(tratamentoRepository, times(1)).save(any(Tratamento.class));
    }
}
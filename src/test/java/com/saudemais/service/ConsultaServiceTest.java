package com.saudemais.service;

import com.saudemais.model.Consulta;
import com.saudemais.repository.ConsultaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultaServiceTest {

    @Mock
    private ConsultaRepository consultaRepository;

    @InjectMocks
    private ConsultaService consultaService;

    private Consulta consulta;

    @BeforeEach
    void setUp() {
        consulta = new Consulta();
        consulta.setId(1L);
        consulta.setEspecialidade("Cardiologia");
        consulta.setDescricao("Consulta de rotina");
        consulta.setData("2025-12-14");
    }

    // ======================================================
    // TESTE: criar consulta
    // ======================================================
    @Test
    void deveCriarConsultaComIdNulo() {
        when(consultaRepository.save(any(Consulta.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Consulta resultado = consultaService.criar(consulta);

        assertNotNull(resultado);
        assertNull(resultado.getId(), "O ID deve ser nulo antes de salvar");
        verify(consultaRepository, times(1)).save(consulta);
    }

    // ======================================================
    // TESTE: buscar por id
    // ======================================================
    @Test
    void deveBuscarConsultaPorId() {
        when(consultaRepository.findById(1L))
                .thenReturn(Optional.of(consulta));

        Consulta resultado = consultaService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Cardiologia", resultado.getEspecialidade());
        verify(consultaRepository).findById(1L);
    }

    // ======================================================
    // TESTE: excluir consulta
    // ======================================================
    @Test
    void deveExcluirConsultaExistente() {
        when(consultaRepository.findById(1L))
                .thenReturn(Optional.of(consulta));

        consultaService.excluir(1L);

        verify(consultaRepository).deleteById(1L);
    }

    // ======================================================
    // TESTE: atualizar consulta
    // ======================================================
    @Test
    void deveAtualizarConsulta() {
        Consulta atualizada = new Consulta();
        atualizada.setEspecialidade("Dermatologia");
        atualizada.setDescricao("Consulta de pele");

        when(consultaRepository.findById(1L))
                .thenReturn(Optional.of(consulta));

        when(consultaRepository.save(any(Consulta.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Consulta resultado = consultaService.atualizar(1L, atualizada);

        assertEquals("Dermatologia", resultado.getEspecialidade());
        assertEquals("Consulta de pele", resultado.getDescricao());
        verify(consultaRepository).save(consulta);
    }
}
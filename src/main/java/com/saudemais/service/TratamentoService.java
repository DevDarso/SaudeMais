package com.saudemais.service;

import com.saudemais.model.Consulta;
import com.saudemais.model.Tratamento;
import com.saudemais.model.TratamentoDTO;
import com.saudemais.repository.ConsultaRepository;
import com.saudemais.repository.TratamentoRepository;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TratamentoService {

    @Autowired
    TratamentoRepository repository;
    
    @Autowired
    private ConsultaRepository consultaRepository;


    public Tratamento salvar(TratamentoDTO dto) {

    Consulta consulta = consultaRepository.findById(dto.getConsultaId())
        .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

    Tratamento t = new Tratamento();

    t.setConsulta(consulta);
    t.setNomeMedicamento(dto.getMedicamento());
    t.setDosagem(dto.getDosagem());
    t.setVezesAoDia(dto.getVezesAoDia());
    t.setQuantidadeDias(dto.getDias());

    // Converte string para LocalDate
    LocalDate inicio = LocalDate.parse(dto.getDataInicio());
    t.setDataInicio(inicio);

    // Calcula data final = inicio + dias
    LocalDate fim = inicio.plusDays(dto.getDias());
    t.setDataFim(fim);

    return repository.save(t);
}


    public List<Tratamento> listarPorConsulta(Long consultaId) {
        return repository.findByConsulta_Id(consultaId);
    }
    
    public void deletar(Long id) {
    repository.deleteById(id);
}

}


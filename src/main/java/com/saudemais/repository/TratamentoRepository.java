package com.saudemais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.saudemais.model.Tratamento;
import java.util.List;

public interface TratamentoRepository extends JpaRepository<Tratamento, Long> {
    List<Tratamento> findByConsulta_Id(Long consultaId);
}

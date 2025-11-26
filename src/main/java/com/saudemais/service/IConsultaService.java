package com.saudemais.service;

import com.saudemais.model.Consulta;
import java.util.List;

public interface IConsultaService {
    void salvar(Consulta consulta);
    List<Consulta> listar();
}

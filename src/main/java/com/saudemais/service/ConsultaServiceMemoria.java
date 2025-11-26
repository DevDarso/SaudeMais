package com.saudemais.service;

import com.saudemais.model.Consulta;
import java.util.ArrayList;
import java.util.List;

public class ConsultaServiceMemoria implements IConsultaService {

    private final List<Consulta> consultas = new ArrayList<>();

    @Override
    public void salvar(Consulta consulta) {
        consultas.add(consulta);
    }

    @Override
    public List<Consulta> listar() {
        return consultas;
    }
}

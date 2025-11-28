package com.saudemais.service;

import com.saudemais.model.Consulta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultaService {

    private final List<Consulta> consultas = new ArrayList<>();

    public void salvar(Consulta c) {
        consultas.add(c);
    }

    public List<Consulta> listarTodos() {
        return consultas;
    }
}

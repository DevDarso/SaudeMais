package com.saudemais.service;

import com.saudemais.model.Consulta;
import java.util.List;

public class ConsultaLookupService {

    private final IConsultaService consultaService;

    public ConsultaLookupService(IConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    public Consulta buscarPorId(int id) {
        return consultaService.listar().stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }
}

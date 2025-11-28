package com.saudemais.repository;

import com.saudemais.model.Tratamento;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class TratamentoRepository {

    private final List<Tratamento> tratamentos = new ArrayList<>();

    // Gerador simples de IDs
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Tratamento salvar(Tratamento t) {
        if (t.getId() == null) {
            t.setId(idGenerator.getAndIncrement());
        }
        tratamentos.add(t);
        return t;
    }

    public List<Tratamento> listarPorConsulta(Long consultaId) {
        List<Tratamento> lista = new ArrayList<>();
        for (Tratamento t : tratamentos) {
            if (t.getConsultaId().equals(consultaId)) {
                lista.add(t);
            }
        }
        return lista;
    }
}

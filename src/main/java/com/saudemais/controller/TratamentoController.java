package com.saudemais.controller;

import com.saudemais.model.Tratamento;
import com.saudemais.repository.TratamentoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tratamentos")
public class TratamentoController {

    private final TratamentoRepository repo = new TratamentoRepository();

    @PostMapping("/salvar")
    public Tratamento salvar(@RequestBody Tratamento t) {
        return repo.salvar(t);
    }

    @GetMapping("/listar/{consultaId}")
    public List<Tratamento> listar(@PathVariable Long consultaId) {
        return repo.listarPorConsulta(consultaId);
    }
}

package com.saudemais.controller;

import com.saudemais.model.Tratamento;
import com.saudemais.model.TratamentoDTO;
import com.saudemais.service.TratamentoService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tratamento")
public class TratamentoAPIController {

    @Autowired
    private TratamentoService tratamentoService;

    @PostMapping("/salvar")
    public ResponseEntity<Tratamento> adicionar(@RequestBody TratamentoDTO dto) {
        Tratamento t = tratamentoService.salvar(dto);
        return ResponseEntity.ok(t);
    }

    @GetMapping("/pesquisa/{consultaId}")
    public ResponseEntity<List<Tratamento>> listar(@PathVariable Long consultaId) {
        List<Tratamento> lista = tratamentoService.listarPorConsulta(consultaId);
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tratamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}

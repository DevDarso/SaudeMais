package com.saudemais.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tratamento")
public class TratamentoPageController {

    @GetMapping("/lista")
    public String listarTratamentosPorConsulta(@RequestParam Long consultaId, Model model) {
        model.addAttribute("consultaId", consultaId);
        return "tratamentoLista"; // arquivo templates/tratamentoLista.html
    }
}

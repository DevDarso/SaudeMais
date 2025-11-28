package com.saudemais.controller;

import com.saudemais.model.Consulta;
import com.saudemais.service.ConsultaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/consulta")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    // --- FORM NOVA CONSULTA ---
    @GetMapping("/nova")
    public String novaConsulta() {
        return "consulta-form";
    }

    // --- SALVAR CONSULTA ---
@PostMapping("/salvar")
public String salvarConsulta(
        @RequestParam String paciente,
        @RequestParam String data,
        @RequestParam String horario,
        @RequestParam String descricao,
        @RequestParam String especialidade
) {

    Consulta nova = new Consulta(paciente, data, horario, descricao, especialidade);
    consultaService.salvar(nova);

    return "redirect:/consulta/listar";
}



    // --- LISTAR CONSULTAS ---
    @GetMapping("/listar")
    public String listarConsultas(Model model) {
        model.addAttribute("consultas", consultaService.listarTodos());
        return "consulta-lista";
    }
}

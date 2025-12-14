package com.saudemais.controller;

import com.saudemais.model.Consulta;
import com.saudemais.model.Tratamento;
import com.saudemais.repository.ConsultaRepository;
import com.saudemais.repository.TratamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private TratamentoRepository tratamentoRepository;

    // --- Telas básicas ---

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/inicio")
    public String inicio() {
        return "index";
    }

    // --- Tela de cadastro ---
    @GetMapping("/cadastro")
    public String formulario(Model model) {
        model.addAttribute("consulta", new Consulta());
        return "consulta-form";
    }

    // --- Gravar consulta ---
    @PostMapping("/gravar")
    public String processarFormulario(@ModelAttribute Consulta consulta){
        consultaRepository.save(consulta); // Agora grava no banco
        return "redirect:/consulta/listagem";
    }

    // --- Listagem de consultas ---
    @GetMapping("/listagem")
    public String listagem(Model model) {
        model.addAttribute("consultas", consultaRepository.findAll());
        return "consulta-lista";
    }

    // --- Exibir uma consulta e seus tratamentos ---
    @GetMapping("/exibir")
    public String detalhes(@RequestParam Long id, Model model) {

        Consulta consulta = consultaRepository.findById(id)
                .orElse(new Consulta());

        // Busca todos tratamentos onde consulta_id = id
        model.addAttribute("tratamentos",
                tratamentoRepository.findByConsulta_Id(id));

        model.addAttribute("consulta", consulta);
        model.addAttribute("tratamento", new Tratamento());

        return "exibir";
    }
}

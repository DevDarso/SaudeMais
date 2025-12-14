package com.saudemais.controller;
import com.saudemais.model.Consulta;
import com.saudemais.service.ConsultaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/consulta") // prefixo alterado para separar da MVC
public class ConsultaAPIController {
    
    @Autowired
    ConsultaService consultaService;

    @PostMapping("/adicionar")
    public ResponseEntity<Consulta> criar(@RequestBody Consulta consulta){
       Consulta novaConsulta = consultaService.criar(consulta);
       return new ResponseEntity<>(novaConsulta, HttpStatus.CREATED);
    }
    
    @GetMapping("/listarTodos")
    public ResponseEntity<List<Consulta>> listar(){
        List<Consulta> listarTodasConsultas = consultaService.buscarTodos();
        return new ResponseEntity<>(listarTodasConsultas, HttpStatus.OK);
    }
    
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Consulta> buscar(@PathVariable Long id){
        Consulta consultaEncontrada = consultaService.buscarPorId(id);
        return new ResponseEntity<>(consultaEncontrada, HttpStatus.OK);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Consulta> atualizar(@PathVariable Long id,
                                              @RequestBody Consulta consulta){
        Consulta consultaAtualizada = consultaService.atualizar(id, consulta);
        return new ResponseEntity<>(consultaAtualizada, HttpStatus.OK);
    }
    
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        consultaService.excluir(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
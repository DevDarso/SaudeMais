package com.saudemais.service;

import com.saudemais.model.Consulta;
import com.saudemais.repository.ConsultaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {
    //injetar classe repositorio
    @Autowired
    ConsultaRepository consultaRepository;
    
    //CRUD
    public Consulta criar(Consulta consulta) {
        consulta.setId(null);
        consultaRepository.save(consulta); //salvar no banco de dados
        return consulta; //retornar a entidade salva completa
    }
    
    public List<Consulta> buscarTodos(){
        return consultaRepository.findAll();
    }
    
    public void excluir(Long id){
       Consulta consultaEncontrada = buscarPorId(id);
       consultaRepository.deleteById(consultaEncontrada.getId());
    }
    
    public Consulta buscarPorId(Long id){
        return consultaRepository.findById(id).orElseThrow();
    }
    
    public Consulta atualizar(Long id, Consulta consulta){
        Consulta consultaEncontrada = buscarPorId(id);
               
        consultaEncontrada.setPaciente(consulta.getPaciente());
        consultaEncontrada.setData(consulta.getData());
        consultaEncontrada.setHorario(consulta.getHorario());
        consultaEncontrada.setDescricao(consulta.getDescricao());
        consultaEncontrada.setEspecialidade(consulta.getEspecialidade());
            
        consultaRepository.save(consultaEncontrada);
        return consultaEncontrada;        
    }
    
    
}
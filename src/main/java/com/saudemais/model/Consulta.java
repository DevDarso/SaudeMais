package com.saudemais.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name="Consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paciente;
    private String data;
    private String horario;
    private String descricao;
    private String especialidade;

    @OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("consulta")
    private List<Tratamento> tratamentos = new ArrayList<>();

}

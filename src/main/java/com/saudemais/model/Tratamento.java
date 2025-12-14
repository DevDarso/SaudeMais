package com.saudemais.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.saudemais.model.Consulta;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "tratamento")
public class Tratamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeMedicamento;
    private String dosagem;
    private Integer vezesAoDia;
    private Integer quantidadeDias;
    private LocalDate dataInicio;
    private LocalDate dataFim;


    @ManyToOne
    @JoinColumn(name = "consulta_id", nullable = false)
    @JsonIgnoreProperties("tratamentos") // evita loop no JSON
    private Consulta consulta;
    
}


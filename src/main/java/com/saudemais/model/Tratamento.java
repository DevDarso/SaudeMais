package com.saudemais.model;

import lombok.Data;

@Data
public class Tratamento {

    private Long id;
    private Long consultaId;

    private String nomeMedicamento;
    private String dosagem;
    private int vezesAoDia;
    private int quantidadeDias;
}


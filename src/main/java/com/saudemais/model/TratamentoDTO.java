package com.saudemais.model;

import lombok.Data;

@Data
public class TratamentoDTO {

    private Long consultaId;
    private String medicamento;
    private String dosagem;
    private Integer vezesAoDia;
    private Integer dias;
    private String dataInicio;

}

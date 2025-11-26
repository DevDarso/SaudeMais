package com.saudemais.model;

public class Consulta {

    private static int contador = 1;

    private int id;
    private String especialidade;
    private String data;
    private String observacao;
    private Medicacao medicacao;

    public Consulta(String especialidade, String data, String observacao) {
        this.id = contador++;
        this.especialidade = especialidade;
        this.data = data;
        this.observacao = observacao;
        this.medicacao = null; // inicia sem medicação
    }

    public int getId() { return id; }
    public String getEspecialidade() { return especialidade; }
    public String getData() { return data; }
    public String getObservacao() { return observacao; }
    public Medicacao getMedicacao() { return medicacao; }

    public void setObservacao(String o) { this.observacao = o; }
    public void setMedicacao(Medicacao m) { this.medicacao = m; }
}

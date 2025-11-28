package com.saudemais.model;

public class Consulta {

    private static int contador = 1;

    private int id;
    private String paciente;
    private String data;
    private String horario;
    private String descricao;
    private String especialidade;

    public Consulta() {
        // Construtor vazio necessário para Spring/Thymeleaf
    }

    public Consulta(String paciente, String data, String horario, String descricao, String especialidade) {
        this.id = contador++;
        this.paciente = paciente;
        this.data = data;
        this.horario = horario;
        this.descricao = descricao;
        this.especialidade = especialidade;
    }

    // Construtor reduzido (opcional)
    public Consulta(String paciente, String data, String descricao) {
        this(paciente, data, "", descricao, "");
    }

    public int getId() { return id; }
    public String getPaciente() { return paciente; }
    public String getData() { return data; }
    public String getHorario() { return horario; }
    public String getDescricao() { return descricao; }
    public String getEspecialidade() { return especialidade; }

    public void setPaciente(String paciente) { this.paciente = paciente; }
    public void setData(String data) { this.data = data; }
    public void setHorario(String horario) { this.horario = horario; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
}

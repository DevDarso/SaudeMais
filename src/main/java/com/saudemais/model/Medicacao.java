package com.saudemais.model;

public class Medicacao {

    private String nome;
    private String dose;
    private String vezesDia;
    private String dias;

    public Medicacao(String nome, String dose, String vezesDia, String dias) {
        this.nome = nome;
        this.dose = dose;
        this.vezesDia = vezesDia;
        this.dias = dias;
    }

    public String getNome() { return nome; }
    public String getDose() { return dose; }
    public String getVezesDia() { return vezesDia; }
    public String getDias() { return dias; }
}

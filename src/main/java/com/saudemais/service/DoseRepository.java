package com.saudemais.service;

public interface DoseRepository {
    /**
     * Retorna quantas vezes ao dia a medicação deve ser tomada.
     */
    int getVezesAoDia(String medicacaoNome);
}

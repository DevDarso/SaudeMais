package com.saudemais.service;

/**
 * Calcula o total de doses com base em:
 * - vezes ao dia (pego do repository)
 * - dias informados pelo usuário
 */
public class DoseService {

    private final DoseRepository doseRepository;

    public DoseService(DoseRepository doseRepository) {
        this.doseRepository = doseRepository;
    }

    public int calcularTotal(String medicacaoNome, int dias) {
        int vezes = doseRepository.getVezesAoDia(medicacaoNome);

        if (dias < 0) {
            throw new IllegalArgumentException("Dias não pode ser negativo");
        }

        return vezes * dias;
    }
}

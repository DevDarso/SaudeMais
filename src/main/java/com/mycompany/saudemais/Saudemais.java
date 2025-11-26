package com.mycompany.saudemais;

import com.saudemais.view.TelaLogin;

public class Saudemais {

    public static void main(String[] args) {
        // Configuração recomendada para Swing
        javax.swing.SwingUtilities.invokeLater(() -> {
            new TelaLogin().setVisible(true);
        });
    }
}

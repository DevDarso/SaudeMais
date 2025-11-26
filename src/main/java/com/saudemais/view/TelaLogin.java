package com.saudemais.view;

import javax.swing.*;

public class TelaLogin extends JFrame {

    public TelaLogin() {
        setTitle("SaudeMais - Login");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblUser = new JLabel("Usuário:");
        lblUser.setBounds(30, 30, 80, 20);
        JTextField txtUser = new JTextField();
        txtUser.setBounds(120, 30, 150, 20);

        JLabel lblPass = new JLabel("Senha:");
        lblPass.setBounds(30, 70, 80, 20);
        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(120, 70, 150, 20);

        JButton btnLogin = new JButton("Entrar");
        btnLogin.setBounds(120, 110, 150, 30);

        btnLogin.addActionListener(e -> {
            if (txtUser.getText().equals("admin") &&
                new String(txtPass.getPassword()).equals("123")) {

                new TelaMenu().setVisible(true);
                dispose();

            } else {
                JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos.");
            }
        });

        add(lblUser); add(txtUser);
        add(lblPass); add(txtPass);
        add(btnLogin);
    }
}

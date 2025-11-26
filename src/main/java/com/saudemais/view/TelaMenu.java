package com.saudemais.view;

import com.saudemais.model.Consulta;
import com.saudemais.service.IConsultaService;
import com.saudemais.service.ConsultaServiceMemoria;
import com.saudemais.ui.components.ButtonEditor;
import com.saudemais.ui.components.ButtonRenderer;

import javax.swing.*;

public class TelaMenu extends JFrame {

    private final IConsultaService service; // interface (SOLID)

    private JButton btnNovaConsulta, btnListarConsultas;
    private JTextField txtEspecialidade, txtData;
    private JTextArea txtObservacao;         // removi areaLista daqui
    private JButton btnSalvar;
    private JTable tabela;
    private JScrollPane scrollTabela;
    private JLabel lblEsp, lblData, lblObs; // Labels como atributos

    public TelaMenu() {
        this.service = new ConsultaServiceMemoria();

        setTitle("SaudeMais - Menu");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        criarMenuPrincipal();
        criarFormulario();
        criarLista();

        // ocultar formulário e garantir que a tabela inicie oculta
        ocultarFormulario();
        if (scrollTabela != null) {
            scrollTabela.setVisible(false);
        }
    }

    private void criarMenuPrincipal() {
        btnNovaConsulta = new JButton("Nova consulta");
        btnNovaConsulta.setBounds(50, 30, 150, 40);
        btnNovaConsulta.addActionListener(e -> mostrarFormulario());

        btnListarConsultas = new JButton("Consultas");
        btnListarConsultas.setBounds(250, 30, 150, 40);
        btnListarConsultas.addActionListener(e -> mostrarLista());

        add(btnNovaConsulta);
        add(btnListarConsultas);
    }

    private void criarFormulario() {
        lblEsp = new JLabel("Especialidade:");
        lblData = new JLabel("Data:");
        lblObs = new JLabel("Observações:");

        txtEspecialidade = new JTextField();
        txtData = new JTextField();
        txtObservacao = new JTextArea();
        btnSalvar = new JButton("Salvar");

        lblEsp.setBounds(50, 100, 120, 20);
        txtEspecialidade.setBounds(180, 100, 220, 25);

        lblData.setBounds(50, 140, 120, 20);
        txtData.setBounds(180, 140, 220, 25);

        lblObs.setBounds(50, 180, 120, 20);
        txtObservacao.setBounds(180, 180, 220, 90);

        btnSalvar.setBounds(180, 280, 220, 35);

        btnSalvar.addActionListener(e -> salvarConsulta());

        // adiciona os componentes à tela
        add(lblEsp);
        add(txtEspecialidade);
        add(lblData);
        add(txtData);
        add(lblObs);
        add(txtObservacao);
        add(btnSalvar);
    }

    private void criarLista() {

        String[] colunas = { "ID", "Especialidade", "Data", "Observações", "Medicação" };

        tabela = new JTable(new Object[0][5], colunas) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return col == 3 || col == 4; // apenas as colunas dos botões são “editáveis”
            }
        };

        tabela.getColumn("Observações").setCellRenderer(new ButtonRenderer());
        tabela.getColumn("Observações").setCellEditor(new ButtonEditor("observacoes", service, this));

        tabela.getColumn("Medicação").setCellRenderer(new ButtonRenderer());
        tabela.getColumn("Medicação").setCellEditor(new ButtonEditor("medicacao", service, this));

        scrollTabela = new JScrollPane(tabela);
        scrollTabela.setBounds(40, 100, 520, 300);
        scrollTabela.setVisible(false);

        add(scrollTabela);
    }

    private void ocultarFormulario() {
        lblEsp.setVisible(false);
        lblData.setVisible(false);
        lblObs.setVisible(false);

        txtEspecialidade.setVisible(false);
        txtData.setVisible(false);
        txtObservacao.setVisible(false);
        btnSalvar.setVisible(false);
    }

    private void mostrarFormulario() {
        // esconder a tabela (substitui uso de areaLista)
        if (scrollTabela != null) {
            scrollTabela.setVisible(false);
        }

        lblEsp.setVisible(true);
        lblData.setVisible(true);
        lblObs.setVisible(true);

        txtEspecialidade.setText("");
        txtData.setText("");
        txtObservacao.setText("");

        txtEspecialidade.setVisible(true);
        txtData.setVisible(true);
        txtObservacao.setVisible(true);
        btnSalvar.setVisible(true);
    }

    private void mostrarLista() {
        ocultarFormulario();

        if (scrollTabela != null) {
            scrollTabela.setVisible(true);
        }

        var lista = service.listar();

        Object[][] dados = new Object[lista.size()][5];

        for (int i = 0; i < lista.size(); i++) {
            Consulta c = lista.get(i);
            dados[i][0] = c.getId();
            dados[i][1] = c.getEspecialidade();
            dados[i][2] = c.getData();
            dados[i][3] = "Abrir";
            dados[i][4] = "Abrir";
        }

        tabela.setModel(new javax.swing.table.DefaultTableModel(
                dados,
                new String[]{"ID", "Especialidade", "Data", "Observações", "Medicação"}
        ));

        // Reaplicar renderizadores e editores
        tabela.getColumn("Observações").setCellRenderer(new ButtonRenderer());
        tabela.getColumn("Observações").setCellEditor(new ButtonEditor("observacoes", service, this));

        tabela.getColumn("Medicação").setCellRenderer(new ButtonRenderer());
        tabela.getColumn("Medicação").setCellEditor(new ButtonEditor("medicacao", service, this));
    }

    private void salvarConsulta() {
        Consulta nova = new Consulta(
                txtEspecialidade.getText(),
                txtData.getText(),
                txtObservacao.getText()
        );

        service.salvar(nova);

        JOptionPane.showMessageDialog(this, "Consulta salva com sucesso!");

        ocultarFormulario();
    }
}

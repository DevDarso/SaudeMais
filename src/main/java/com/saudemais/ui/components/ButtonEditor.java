package com.saudemais.ui.components;

import com.saudemais.model.*;
import com.saudemais.service.IConsultaService;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.*;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

    private JButton btn;
    private String tipo;
    private IConsultaService service;
    private JFrame tela;

    private int idSelecionado;

    public ButtonEditor(String tipo, IConsultaService service, JFrame tela) {
        this.tipo = tipo;
        this.service = service;
        this.tela = tela;

        btn = new JButton("Abrir");
        btn.addActionListener(this);
    }

    @Override
    public Object getCellEditorValue() { return ""; }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {

        idSelecionado = (int) table.getValueAt(row, 0);
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Consulta consulta = service.listar().stream()
                .filter(c -> c.getId() == idSelecionado)
                .findFirst().orElse(null);

        if (tipo.equals("observacoes")) {
            abrirObservacoes(consulta);
        } else {
            abrirMedicacao(consulta);
        }

        fireEditingStopped();
    }

    // ---------- OBSERVAÇÕES ----------
    private void abrirObservacoes(Consulta consulta) {

        if (consulta.getObservacao() == null || consulta.getObservacao().isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Nenhuma observação cadastrada.");
            return;
        }

        JTextArea area = new JTextArea(consulta.getObservacao(), 10, 30);
        int resp = JOptionPane.showConfirmDialog(tela, new JScrollPane(area),
                "Editar Observações", JOptionPane.OK_CANCEL_OPTION);

        if (resp == JOptionPane.OK_OPTION) {
            consulta.setObservacao(area.getText());
            JOptionPane.showMessageDialog(tela, "Observações atualizadas!");
        }
    }

    // ---------- MEDICAÇÃO ----------

    private void abrirMedicacao(Consulta consulta) {

        if (consulta.getMedicacao() == null) {

            JTextField nome = new JTextField();
            JTextField dose = new JTextField();
            JTextField vezes = new JTextField();
            JTextField dias = new JTextField();

            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(new JLabel("Nome do remédio:"));
            panel.add(nome);
            panel.add(new JLabel("Dose:"));
            panel.add(dose);
            panel.add(new JLabel("Vezes ao dia:"));
            panel.add(vezes);
            panel.add(new JLabel("Quantidade de dias:"));
            panel.add(dias);

            int resp = JOptionPane.showConfirmDialog(tela, panel,
                    "Cadastrar Medicação", JOptionPane.OK_CANCEL_OPTION);

            if (resp == JOptionPane.OK_OPTION) {
                consulta.setMedicacao(new Medicacao(
                        nome.getText(),
                        dose.getText(),
                        vezes.getText(),
                        dias.getText()
                ));
                JOptionPane.showMessageDialog(tela, "Medicação cadastrada!");
            }

            return;
        }

        // Caso já tenha medicação → apenas exibe
        Medicacao m = consulta.getMedicacao();

        JOptionPane.showMessageDialog(tela,
                "Remédio: " + m.getNome() +
                "\nDose: " + m.getDose() +
                "\nVezes ao dia: " + m.getVezesDia() +
                "\nDias: " + m.getDias(),
                "Medicação Cadastrada",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

}

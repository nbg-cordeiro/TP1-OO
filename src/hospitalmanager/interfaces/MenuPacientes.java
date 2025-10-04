package hospitalmanager.interfaces;

import hospitalmanager.dominio.Paciente;
import hospitalmanager.interfaces.ModelosTabela.TabelaPacientes;
import hospitalmanager.interfaces.elementos.BotaoFechar;
import hospitalmanager.interfaces.elementos.BotaoVoltar;
import hospitalmanager.interfaces.elementos.PainelTitulo;
import hospitalmanager.interfaces.elementos.PainelInferior;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class MenuPacientes extends JFrame {
    public MenuPacientes(MenuInicial principal){
        setSize(500,500);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.gray);

        JButton botaoAdicionar = getJButton(principal);
        BotaoFechar botaoFechar = new BotaoFechar(this);
        BotaoVoltar botaoVoltar = new BotaoVoltar(this,principal);
        PainelInferior painelInferior = new PainelInferior(this,botaoFechar,botaoVoltar, botaoAdicionar);
        painelInferior.setBackground(Color.DARK_GRAY);
        PainelTitulo titulo = new PainelTitulo(this,"Hospital Manager - Menu Pacientes");
        titulo.setVisible(true);

        TabelaPacientes modeloPaciente = new TabelaPacientes(principal.getSistema().getPacientes());
        JTable tabela = new JTable(modeloPaciente);
        JScrollPane scrollPane = new JScrollPane(tabela);
        this.add(scrollPane,BorderLayout.CENTER);

        javax.swing.table.DefaultTableCellRenderer centerRenderer = new javax.swing.table.DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        javax.swing.table.TableColumnModel columnModel = tabela.getColumnModel();

        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(2).setCellRenderer(centerRenderer);
        columnModel.getColumn(3).setCellRenderer(centerRenderer);
        tabela.getColumnModel().getColumn(0).setMaxWidth(100);
        tabela.getColumnModel().getColumn(0).setMinWidth(100);
        tabela.getColumnModel().getColumn(2).setMaxWidth(50);
        tabela.getColumnModel().getColumn(3).setMaxWidth(60);
    }

    private static JButton getJButton(MenuInicial principal) {
        JButton botaoAdicionar = new JButton("Adicionar");
        botaoAdicionar.setPreferredSize(new Dimension(120, 40));
        botaoAdicionar.setMaximumSize(new Dimension(120, 40));
        botaoAdicionar.setMinimumSize(new Dimension(120, 40));

        botaoAdicionar.addActionListener(_ -> {
            MaskFormatter formatoCpf;
            try {
                formatoCpf = new MaskFormatter("###.###.###-##");
                formatoCpf.setPlaceholderCharacter('_');
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null,"Um erro ocorreu: "+e.getMessage());
                throw new RuntimeException(e);
            }
            JFormattedTextField campoCpf = new JFormattedTextField(formatoCpf);
            JTextField campoNome = new JTextField();

            SpinnerNumberModel modeloIdade = new SpinnerNumberModel(0, 0, 150, 1);
            JSpinner campoIdade = new JSpinner(modeloIdade);
            JSpinner.NumberEditor editor = (JSpinner.NumberEditor)campoIdade.getEditor();
            editor.getFormat().setGroupingUsed(false);

            JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
            panel.add(new JLabel("CPF:"));
            panel.add(campoCpf);
            panel.add(new JLabel("Nome:"));
            panel.add(campoNome);
            panel.add(new JLabel("Idade:"));
            panel.add(campoIdade);

            int result = JOptionPane.showConfirmDialog(null, panel, "Cadastro de Paciente",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION){
                String cpf = campoCpf.getText();
                String nome = campoNome.getText();
                String idade = campoIdade.getValue().toString();
                if(idade.isEmpty() || cpf.isEmpty() || nome.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Você deve preencher todos os campos!\n Cadastro cancelado!");
                }
                else{
                    Paciente paciente = new Paciente(cpf, nome, Integer.parseInt(idade));
                    try{
                        principal.getSistema().addPaciente(paciente);
                        JOptionPane.showMessageDialog(null,
                                "Paciente Cadastrado com Sucesso!\n\n" + "CPF: " + cpf + "\n" + "Nome: " + nome + "\n" + "Idade: " + idade, "Dados do Paciente", JOptionPane.INFORMATION_MESSAGE);
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null,"Um erro ocorreu: "+e.getMessage());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            }
        });
        return botaoAdicionar;
    }
}

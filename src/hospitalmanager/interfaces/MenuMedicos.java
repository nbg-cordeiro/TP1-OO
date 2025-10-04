package hospitalmanager.interfaces;

import hospitalmanager.dominio.Medico;
import hospitalmanager.interfaces.ModelosTabela.TabelaMedicos;
import hospitalmanager.interfaces.elementos.BotaoFechar;
import hospitalmanager.interfaces.elementos.BotaoVoltar;
import hospitalmanager.interfaces.elementos.PainelTitulo;
import hospitalmanager.interfaces.elementos.PainelInferior;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class MenuMedicos extends JFrame {
    public MenuMedicos(MenuInicial principal){
        setSize(500,500);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.gray);
        BotaoFechar botaoFechar = new BotaoFechar(this);
        BotaoVoltar botaoVoltar = new BotaoVoltar(this,principal);
        JButton botaoAdicionar = getJButton(principal);

        PainelInferior painelInferior = new PainelInferior(this,botaoFechar,botaoVoltar,botaoAdicionar);
        painelInferior.setBackground(Color.DARK_GRAY);
        PainelTitulo titulo = new PainelTitulo(this,"Hospital Manager - Menu Medicos");
        titulo.setVisible(true);
        TabelaMedicos modeloMedico = new TabelaMedicos(principal.getSistema().getMedicos());
        JTable tabela = new JTable(modeloMedico);
        JScrollPane scrollPane = new JScrollPane(tabela);
        this.add(scrollPane,BorderLayout.CENTER);

        javax.swing.table.DefaultTableCellRenderer centerRenderer = new javax.swing.table.DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        javax.swing.table.TableColumnModel columnModel = tabela.getColumnModel();

        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);
        columnModel.getColumn(3).setCellRenderer(centerRenderer);
        columnModel.getColumn(4).setCellRenderer(centerRenderer);
        tabela.getColumnModel().getColumn(0).setMaxWidth(60);
        tabela.getColumnModel().getColumn(1).setMaxWidth(100);
        tabela.getColumnModel().getColumn(1).setMinWidth(100);
        tabela.getColumnModel().getColumn(3).setMaxWidth(50);
        tabela.getColumnModel().getColumn(4).setMaxWidth(60);
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
                JOptionPane.showMessageDialog(null,"Um erro ocorreu: "+e.getMessage(),"Aviso",JOptionPane.INFORMATION_MESSAGE);
                throw new RuntimeException(e);
            }

            JTextField campoCrm = new JTextField();
            JFormattedTextField campoCpf = new JFormattedTextField(formatoCpf);
            JTextField campoNome = new JTextField();

            SpinnerNumberModel modeloIdade = new SpinnerNumberModel(0, 0, 150, 1);
            JSpinner campoIdade = new JSpinner(modeloIdade);
            JSpinner.NumberEditor editor = (JSpinner.NumberEditor)campoIdade.getEditor();
            editor.getFormat().setGroupingUsed(false);

            JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

            panel.add(new JLabel("CRM:"));
            panel.add(campoCrm);
            panel.add(new JLabel("CPF:"));
            panel.add(campoCpf);
            panel.add(new JLabel("Nome:"));
            panel.add(campoNome);
            panel.add(new JLabel("Idade:"));
            panel.add(campoIdade);

            int result = JOptionPane.showConfirmDialog(null, panel, "Cadastro de Medico",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION){
                String crm = campoCrm.getText();
                String idade = campoIdade.getValue().toString();
                String nome = campoNome.getText();
                String cpf = campoCpf.getText();
                if(idade.isEmpty() || cpf.isEmpty() || nome.isEmpty() || crm.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Você deve preencher todos os campos!\n Cadastro cancelado!","Aviso",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    Medico medico = new Medico(crm, cpf, nome, Integer.parseInt(idade));
                    try{
                        principal.getSistema().addMedico(medico);
                        JOptionPane.showMessageDialog(null,
                                "Medico Cadastrado com Sucesso!\n\n" + "CPF: " + cpf + "\n" + "Nome: " + nome + "\n" + "Idade: " + idade, "Dados do Medico", JOptionPane.INFORMATION_MESSAGE);
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null,"Um erro ocorreu: "+e.getMessage(),"Aviso",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            }
        });
        return botaoAdicionar;
    }
}

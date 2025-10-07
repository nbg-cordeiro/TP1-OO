package hospitalmanager.interfaces;

import hospitalmanager.dominio.Paciente;
import hospitalmanager.dominio.PacienteEspecial;
import hospitalmanager.dominio.PlanoDeSaude;
import hospitalmanager.interfaces.ModelosTabela.TabelaPacientes;
import hospitalmanager.interfaces.elementos.*;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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
        tabela.setBackground(Color.lightGray);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBackground(Color.gray);
        scrollPane.getViewport().setBackground(Color.GRAY);
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
        botaoAdicionar.setBackground(Color.LIGHT_GRAY);
        botaoAdicionar.setPreferredSize(new Dimension(120, 40));
        botaoAdicionar.setMaximumSize(new Dimension(120, 40));
        botaoAdicionar.setMinimumSize(new Dimension(120, 40));

        botaoAdicionar.addActionListener(_ -> {
            MaskFormatter formatoCpf;
            MaskFormatter formatoNascimento;
            try {
                formatoCpf = new MaskFormatter("###.###.###-##");
                formatoCpf.setPlaceholderCharacter('_');
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null,"Um erro ocorreu: "+e.getMessage());
                throw new RuntimeException(e);
            }
            try {
                formatoNascimento = new MaskFormatter("##/##/####");
                formatoNascimento.setPlaceholderCharacter('_');
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null,"Um erro ocorreu: "+e.getMessage());
                throw new RuntimeException(e);
            }
            String[] planosArray = new String[principal.getSistema().getPlanos().size() + 1];
            planosArray[0] = "Nenhum";
            int index = 1;
            for (PlanoDeSaude plano : principal.getSistema().getPlanos()){
                planosArray[index++] = plano.getCodigo() + " ("+plano.getNome()+")";
            }

            JComboBox<String> comboBoxPlanos = new JComboBox<>(planosArray);

            JFormattedTextField campoCpf = new JFormattedTextField(formatoCpf);
            JTextField campoNome = new JTextField();

            JFormattedTextField campoNascimento = new JFormattedTextField(formatoNascimento);

            JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
            panel.add(new JLabel("CPF:"));
            panel.add(campoCpf);
            panel.add(new JLabel("Nome:"));
            panel.add(campoNome);
            panel.add(new JLabel("Data de Nascimento (DD/MM/YYYY):"));
            panel.add(campoNascimento);
            panel.add(new JLabel("Plano de Saúde:"));
            panel.add(comboBoxPlanos);

            int result = JOptionPane.showConfirmDialog(null, panel, "Cadastro de Paciente",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION){
                PlanoDeSaude plano = principal.getSistema().getPlanos().get(comboBoxPlanos.getSelectedIndex());
                String cpf = campoCpf.getText();
                String nome = campoNome.getText();
                String dataNascimentoStr = campoNascimento.getText();
                int idade;
                if (nome.isEmpty() || cpf.contains("_") || dataNascimentoStr.contains("_")){
                    JOptionPane.showMessageDialog(null, "Você deve preencher todos os campos!\n Cadastro cancelado!");
                }
                else{
                    try{
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr, formatter);
                        if(comboBoxPlanos.getSelectedIndex()==0){
                            Paciente paciente = new Paciente(cpf, nome, dataNascimento);
                            idade = paciente.getIdade();
                            principal.getSistema().addPaciente(paciente);
                        }
                        else{
                            PacienteEspecial pacienteEspecial = new PacienteEspecial(cpf, nome, dataNascimento,plano);
                            principal.getSistema().addPacienteEspecial(pacienteEspecial);
                            idade = pacienteEspecial.getIdade();
                        }
                        JOptionPane.showMessageDialog(null,
                                "Paciente Cadastrado com Sucesso!\n\n" + "CPF: " + cpf + "\n" + "Nome: " + nome + "\nIdade:"+ idade +"\nIdade"+"\nCódigo Plano de Saúde:"+planosArray[comboBoxPlanos.getSelectedIndex()], "Dados do Paciente", JOptionPane.INFORMATION_MESSAGE);
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

package hospitalmanager.interfaces;

import hospitalmanager.dominio.Consulta;
import hospitalmanager.dominio.Medico;
import hospitalmanager.interfaces.ModelosTabela.TabelaConsultas;
import hospitalmanager.interfaces.ModelosTabela.TabelaMedicos;
import hospitalmanager.interfaces.elementos.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class MenuMedicos extends JFrame {
    public MenuMedicos(MenuInicial principal){
        setSize(500,500);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.gray);

        TabelaMedicos modeloMedico = new TabelaMedicos(principal.getSistema().getMedicos());

        JButton botaoAdicionar = getJButton(principal,modeloMedico);
        BotaoFechar botaoFechar = new BotaoFechar(this);
        BotaoVoltar botaoVoltar = new BotaoVoltar(this,principal);

        JTable tabela = new JTable(modeloMedico);

        Function<Integer, Medico> medicoProvider = modeloMedico::getMedicoAt;

        Consumer<Medico> verDetalhes = medico ->{
            try{
                JPanel painel = new JPanel(new BorderLayout(5, 5));
                JPanel painelInfo = new JPanel(new GridLayout(0, 1));
                painelInfo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                painelInfo.add(new JLabel("CRM: " + medico.getCrm()));
                painelInfo.add(new JLabel("Nome: " + medico.getNome()));
                painelInfo.add(new JLabel("Especialidade: " + medico.getEspecialidade()));
                int consultasConcluidas=0;
                int consultasAgendadas=0;
                for(Consulta c:medico.getAgenda()) {
                    if(Objects.equals(c.getStatus(), "Concluída")){
                        consultasConcluidas++;
                    }
                }
                for(Consulta c:medico.getAgenda()) {
                    if(Objects.equals(c.getStatus(), "Agendada")){
                        consultasAgendadas++;
                    }
                }
                painelInfo.add(new JLabel("Consultas Concluídas: "+consultasConcluidas));
                painelInfo.add(new JLabel("Consultas Agendadas: "+consultasAgendadas));
                painel.add(painelInfo, BorderLayout.NORTH);
                TabelaConsultas modelo = new TabelaConsultas(medico.getAgenda());
                JTable tabelaConsultas = new JTable(modelo);
                JScrollPane scrollPane = new JScrollPane(tabelaConsultas);
                painel.add(scrollPane,BorderLayout.CENTER);
                JOptionPane.showMessageDialog(
                        this,
                        painel,
                        "Relatório de Consultas:",
                        JOptionPane.PLAIN_MESSAGE
                );}catch(RuntimeException a){
                JOptionPane.showMessageDialog(null, "Visualização Cancelada.");
                System.err.println("Visualização Cancelada.");
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Erro ao abrir visualização: "+e.getMessage());
                System.err.println("Erro ao abrir visualização: "+e.getMessage());
            }
        };
        new BotaoColuna<>(tabela, 5, "Ver", verDetalhes, medicoProvider);

        PainelInferior painelInferior = new PainelInferior(this,botaoFechar,botaoVoltar,botaoAdicionar);
        painelInferior.setBackground(Color.DARK_GRAY);
        PainelTitulo titulo = new PainelTitulo(this,"Hospital Manager - Menu Medicos");
        titulo.setVisible(true);

        tabela.setBackground(Color.lightGray);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBackground(Color.gray);
        scrollPane.getViewport().setBackground(Color.GRAY);
        this.add(scrollPane,BorderLayout.CENTER);

        DefaultTableCellRenderer centerRenderer = new javax.swing.table.DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        TableColumnModel columnModel = tabela.getColumnModel();

        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);
        columnModel.getColumn(3).setCellRenderer(centerRenderer);
        columnModel.getColumn(4).setCellRenderer(centerRenderer);
        columnModel.getColumn(5).setCellRenderer(centerRenderer);
        tabela.getColumnModel().getColumn(0).setMaxWidth(60);
        tabela.getColumnModel().getColumn(1).setMaxWidth(100);
        tabela.getColumnModel().getColumn(1).setMinWidth(100);
        tabela.getColumnModel().getColumn(3).setMaxWidth(50);
        tabela.getColumnModel().getColumn(5).setMaxWidth(60);
    }
    private static JButton getJButton(MenuInicial principal,TabelaMedicos modeloMedico) {
        JButton botaoAdicionar = new JButton("Adicionar");
        botaoAdicionar.setBackground(Color.LIGHT_GRAY);
        botaoAdicionar.setPreferredSize(new Dimension(120, 40));
        botaoAdicionar.setMaximumSize(new Dimension(120, 40));
        botaoAdicionar.setMinimumSize(new Dimension(120, 40));

        botaoAdicionar.addActionListener(_ -> {
            JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
            String[] opcoes = {"Especialidade", "Médico", "Cancelar"};
            int escolha = JOptionPane.showOptionDialog( panel,null,"Opções de Cadastro",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,opcoes,opcoes[2]);
            if (escolha == 0)
            {
                JTextField campoEspecialidade = new JTextField();
                panel.add(new JLabel("Especialidade:"));
                panel.add(campoEspecialidade);
                int result = JOptionPane.showConfirmDialog(null, panel, "Cadastro de Especialidade",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION){
                    principal.getSistema().getEspecialidades().add(campoEspecialidade.getText());
                    JOptionPane.showMessageDialog(null,"Especialidade Cadastrada com Sucesso!\n\n");
                }
                else if (result == JOptionPane.CANCEL_OPTION){
                    JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                }
            }
            else if( escolha == 1)
            {
                MaskFormatter formatoCpf;
                MaskFormatter formatoNascimento;
                try {
                    formatoCpf = new MaskFormatter("###.###.###-##");
                    formatoCpf.setPlaceholderCharacter('_');
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(null,"Um erro ocorreu: "+e.getMessage(),"Aviso",JOptionPane.INFORMATION_MESSAGE);
                    throw new RuntimeException(e);
                }
                try{
                    formatoNascimento = new MaskFormatter("##/##/####");
                    formatoNascimento.setPlaceholderCharacter('_');
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(null,"Um erro ocorreu: "+e.getMessage(),"Aviso",JOptionPane.INFORMATION_MESSAGE);
                    throw new RuntimeException(e);
                }
                JTextField campoCrm = new JTextField();
                JFormattedTextField campoCpf = new JFormattedTextField(formatoCpf);
                JFormattedTextField campoDataNascimento = new JFormattedTextField(formatoNascimento);
                JTextField campoNome = new JTextField();
                JTextField campoCusto = new JTextField();

                String[] especialidadesArray = new String[principal.getSistema().getEspecialidades().size() + 1];
                especialidadesArray[0] = "Nenhum";
                int index = 1;
                for (String especialidade : principal.getSistema().getEspecialidades()){
                    especialidadesArray[index] = especialidade;
                    index++;
                }

                JComboBox<String> comboBoxEspecialidades = new JComboBox<>(especialidadesArray);
                panel.add(new JLabel("CRM:"));
                panel.add(campoCrm);
                panel.add(new JLabel("CPF:"));
                panel.add(campoCpf);
                panel.add(new JLabel("Nome:"));
                panel.add(campoNome);
                panel.add(new JLabel("Data de Nascimento (DD/MM/YYYY):"));
                panel.add(campoDataNascimento);
                panel.add(new JLabel("Especialidades:"));
                panel.add(comboBoxEspecialidades);
                panel.add(new JLabel("Custo:"));
                panel.add(campoCusto);
                int result = JOptionPane.showConfirmDialog(null, panel, "Cadastro de Medico",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION){
                    String crm = campoCrm.getText();
                    String dataNascimentoStr = campoDataNascimento.getText();
                    String nome = campoNome.getText();
                    String cpf = campoCpf.getText();
                    String especialidade = Objects.requireNonNull(comboBoxEspecialidades.getSelectedItem()).toString();
                    if(campoCusto.getText().isEmpty() || cpf.contains("_") || dataNascimentoStr.contains("_") || nome.isEmpty() || crm.isEmpty() || especialidade.isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Você deve preencher todos os campos!\n Cadastro cancelado!","Aviso",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        Double custoConsulta =  Double.parseDouble(campoCusto.getText());
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr, formatter);
                        Medico medico = new Medico(crm, cpf, nome, dataNascimento,especialidade,custoConsulta);
                        try{
                            if(dataNascimento.isAfter(LocalDate.now())){
                                throw new Exception("Data de nascimento invalida!");
                            }
                            principal.getSistema().addMedico(medico);
                            JOptionPane.showMessageDialog(null,
                                    "Medico Cadastrado com Sucesso!\n" + "\nCPF: " + cpf + "\nNome: " + nome + "\nIdade: " + medico.getIdade() + "\nEspecialidade: " + medico.getEspecialidade() + "Custo da Consulta: "+custoConsulta, "Dados do Medico", JOptionPane.INFORMATION_MESSAGE);
                            modeloMedico.fireTableDataChanged();
                        }catch(Exception e){
                            JOptionPane.showMessageDialog(null,"Um erro ocorreu: "+e.getMessage(),"Aviso",JOptionPane.INFORMATION_MESSAGE);
                            JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            }
        });
        return botaoAdicionar;
    }
}

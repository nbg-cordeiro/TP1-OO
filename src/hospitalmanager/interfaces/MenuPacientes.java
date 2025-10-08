package hospitalmanager.interfaces;

import hospitalmanager.dominio.*;
import hospitalmanager.interfaces.ModelosTabela.*;
import hospitalmanager.interfaces.elementos.*;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

import static hospitalmanager.persistencia.RegistroCSV.escreverRelatorio;

public class MenuPacientes extends JFrame {
    public MenuPacientes(MenuInicial principal){
        setSize(500,500);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.gray);
        TabelaPacientes modeloPaciente = new TabelaPacientes(principal.getSistema().getPacientes());
        TabelaPacientesEspeciais modeloEspecial = new TabelaPacientesEspeciais(principal.getSistema().getPacientesEspeciais());
        JTable tabela = new JTable(modeloPaciente);

        Function<Integer, Paciente> pacienteProvider = modeloPaciente::getPacienteAt;

        Consumer<Paciente> verDetalhes = paciente ->{
            try{
            JPanel painel = new JPanel(new BorderLayout(5, 5));
            String[] opcoes = {"Consultas", "Internações", "Cancelar"};
            int escolha = JOptionPane.showOptionDialog( painel,null,"Opções de Cadastro",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,opcoes,opcoes[2]);
            JPanel painelInfo = new JPanel(new GridLayout(0, 1));
            painelInfo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            painelInfo.add(new JLabel("CPF: " + paciente.getCpf()));
            painelInfo.add(new JLabel("Nome: " + paciente.getNome()));
            painelInfo.add(new JLabel("Idade: " + paciente.getIdade() + " anos"));
            painel.add(painelInfo, BorderLayout.NORTH);
            JScrollPane scrollPane;
            String title = "Relatório de Consultas";
            if(escolha == 1){
                TabelaInternacoes modeloInternacoes = new TabelaInternacoes(paciente.getInternacoes());

                JTable tabelaInternacoes = new JTable(modeloInternacoes);
                tabelaInternacoes.setPreferredScrollableViewportSize(new Dimension(500, 250));
                Function<Integer, Internacao> internacaoProvider = modeloInternacoes::getInternacaoAt;

                Consumer<Internacao> verDetalhes2 = internacao ->{
                    try{
                        JPanel panel2 = new JPanel(new BorderLayout(5, 5));
                        String[] opcoes2 = {"Dar Alta", "Mostrar Relatório","Deletar Internação","Cancelar"};
                        int escolha2 = JOptionPane.showOptionDialog( panel2,null,"Opções",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,opcoes2,opcoes2[3]);
                        switch(escolha2){
                            case 0:
                                if(internacao.getCheckOut()==null){
                                    internacao.fazerCheckOut();
                                    JOptionPane.showMessageDialog(null, "Alta realizada com sucesso!");
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Internação já finalizada!");
                                }
                                break;
                            case 1:
                                String plano;
                                if(internacao.getPaciente() instanceof PacienteEspecial){
                                    plano = ((PacienteEspecial) internacao.getPaciente()).getPlanoDeSaude().getCodigo();
                                }
                                else{
                                    plano = "Sem";
                                }
                                String[]opcoes3= {"Ok", "Exportar para .csv"};
                                String texto = "Relatório da Internação:\n" +
                                        "\nLeito: " + internacao.getLeito() +
                                        "\nCheck In: " + internacao.getCheckIn() +
                                        "\nCheck Out: " + internacao.getCheckOut() +
                                        "\nObservações:" + internacao.getObservacoes() +
                                        "\n\n- Dados do Médico Responsável" +
                                        "\nCRM:" + internacao.getMedico().getCrm() +
                                        "\nNome: " + internacao.getMedico().getNome() +
                                        "\nEspecialidade: " + internacao.getMedico().getEspecialidade() +
                                        "\n\n- Dados do Paciente:" +
                                        "\nCPF: " + internacao.getCpf() +
                                        "\n Nome: "+ internacao.getPaciente().getNome()+
                                        "\n Idade: "+ internacao.getPaciente().getIdade()+
                                        "\n Plano de Saúde: " + plano;
                                int escolha3 =JOptionPane.showOptionDialog(painel, texto
                                        ,
                                        "Relatório da Internação:", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,opcoes3,opcoes3[0]);
                                if(escolha3==1) {
                                    String caminho = escreverRelatorio("Internacao_"+internacao.getCpf()+"_"+internacao.getLeito(),texto);
                                    JOptionPane.showMessageDialog(null, "Relatório Salvo em:\n"+caminho);
                                }
                                break;
                            case 2:
                                principal.getSistema().getInternacoes().remove(internacao);
                                internacao.getPaciente().getInternacoes().remove(internacao);
                                JOptionPane.showMessageDialog(null, "Internação deletada com sucesso!");
                                break;
                            default:JOptionPane.showMessageDialog(null, "Nenhuma alteração foi feita!");
                        }
                        modeloInternacoes.fireTableDataChanged();
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null, "Um erro ocorreu: \n" + e.getMessage());
                        JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                    }
                };
                new BotaoColuna<>(tabelaInternacoes, 6, "Ver", verDetalhes2, internacaoProvider);

                scrollPane = new JScrollPane(tabelaInternacoes);
                title="Relatório de Internações";
                painel.add(scrollPane, BorderLayout.CENTER);
            }
            else if(escolha== 0){
                TabelaConsultas modeloConsultas = new TabelaConsultas(paciente.getConsultas());
                JTable tabelaConsultas = new JTable(modeloConsultas);

                Function<Integer, Consulta> consultaProvider = modeloConsultas::getConsultaAt;
                Consumer<Consulta> verDetalhes100 = consulta ->{
                    try{
                        JPanel painel100 = new JPanel(new BorderLayout(5, 5));
                        String[] opcoes100 = {"Concluir Consulta","Cancelar Consulta", "Mostrar Relatório","Cancelar"};
                        int escolha100 = JOptionPane.showOptionDialog( painel100,null,"Opções",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,opcoes100,opcoes100[3]);
                        switch(escolha100){
                            case 0:
                                JPanel panelPrincipal100 = new JPanel();
                                panelPrincipal100.setLayout(new BorderLayout(5, 5));
                                JTextArea campoObservacoes = new JTextArea(4, 20);
                                campoObservacoes.setLineWrap(true);
                                campoObservacoes.setWrapStyleWord(true);
                                JPanel panel100 = new JPanel(new GridLayout(0, 1, 5, 5));
                                JPanel panelObservacoes = new JPanel(new BorderLayout (5,5));
                                panel100.setLayout(new BorderLayout(5, 5));
                                panelObservacoes.add(campoObservacoes, BorderLayout.NORTH);
                                JScrollPane scrollObservacoes = new JScrollPane(campoObservacoes);
                                panelObservacoes.add(scrollObservacoes, BorderLayout.CENTER);
                                panel100.add(new JLabel("Observações adicionais (Diagnósticos, Receitas...):"));
                                panelPrincipal100.add(panel100, BorderLayout.NORTH);
                                panelPrincipal100.add(scrollObservacoes, BorderLayout.CENTER);
                                int resultado200000 = JOptionPane.showConfirmDialog(null, panelPrincipal100, "Conclusão de Consulta",
                                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                                if(resultado200000 == JOptionPane.OK_OPTION){
                                    consulta.concluirConsulta(" Observações adicionais (Diagnósticos, Receitas...): "+campoObservacoes.getText());
                                    JOptionPane.showMessageDialog(null, "Consulta concluída com sucesso!");
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Operação cancelada!");
                                }

                                break;
                            case 1:
                                if(!Objects.equals(consulta.getStatus(), "Cancelada")){
                                    consulta.cancelarConsulta();
                                    JOptionPane.showMessageDialog(null, "Consulta cancelada com sucesso!");

                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Consulta já cancelada!");
                                }
                                break;
                            case 2:
                                String plano;
                                if(consulta.getPaciente() instanceof PacienteEspecial){
                                    plano = ((PacienteEspecial) consulta.getPaciente()).getPlanoDeSaude().getCodigo();
                                }
                                else{
                                    plano = "Sem";
                                }
                                String[]opcoes20000= {"Ok", "Exportar para .csv"};
                                String texto = "Relatório da Consulta:\n" +
                                        "\nSala: " + consulta.getSala() +
                                        "\nData e Horário: " + consulta.getDataHora() +
                                        "\nStatus: " + consulta.getStatus() +
                                        "\nObservações:" + consulta.getObservacoes() +
                                        "\n\n- Dados do Médico Responsável" +
                                        "\nCRM:" + consulta.getMedico().getCrm() +
                                        "\nNome: " + consulta.getMedico().getNome() +
                                        "\nEspecialidade: " + consulta.getMedico().getEspecialidade() +
                                        "\n\n- Dados do Paciente:" +
                                        "\nCPF: " + consulta.getCpf() +
                                        "\n Nome: "+ consulta.getPaciente().getNome()+
                                        "\n Idade: "+ consulta.getPaciente().getIdade()+
                                        "\n Plano de Saúde: " + plano;
                                int escolha2000 =JOptionPane.showOptionDialog(painel100, texto
                                        ,
                                        "Relatório da Consulta:", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,opcoes20000,opcoes100[0]);
                                if(escolha2000==1) {
                                    String caminho = escreverRelatorio("consultas/Consulta_cpf"+consulta.getCpf()+"_crm"+consulta.getMedico().getCrm()+consulta.getSala(),texto);
                                    JOptionPane.showMessageDialog(null, "Relatório Salvo em:\n"+caminho);
                                }
                                break;
                            default:JOptionPane.showMessageDialog(null, "Nenhuma alteração foi feita!");
                        }
                        modeloConsultas.fireTableDataChanged();
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null, "Um erro ocorreu: \n" + e.getMessage());
                        JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                    }
                };
                new BotaoColuna<>(tabelaConsultas, 5, "Ver", verDetalhes100, consultaProvider);

                tabelaConsultas.setPreferredScrollableViewportSize(new Dimension(500, 250));
                scrollPane = new JScrollPane(tabelaConsultas);
                painel.add(scrollPane, BorderLayout.CENTER);
            }
            else{
                throw new RuntimeException();
            }
            JOptionPane.showMessageDialog(
                    this,
                    painel,
                    title,
                    JOptionPane.PLAIN_MESSAGE
            );}catch(RuntimeException a){
                JOptionPane.showMessageDialog(null, "Visualização Cancelada.");
                System.err.println("Visualização Cancelada.");
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Erro ao abrir visualização: "+e.getMessage());
                System.err.println("Erro ao abrir visualização: "+e.getMessage());
            }
        };
        new BotaoColuna<>(tabela, 3, "Ver", verDetalhes, pacienteProvider);

        JTable tabelaEspecial = new JTable(modeloEspecial);
        JButton botaoAdicionar = getJButton(principal, modeloEspecial,modeloPaciente);
        BotaoFechar botaoFechar = new BotaoFechar(this);
        BotaoVoltar botaoVoltar = new BotaoVoltar(this,principal);

        PainelTitulo titulo = new PainelTitulo(this,"Hospital Manager - Menu Pacientes");
        titulo.setVisible(true);



        tabela.setBackground(Color.lightGray);
        tabelaEspecial.setBackground(Color.lightGray);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBackground(Color.gray);
        scrollPane.getViewport().setBackground(Color.GRAY);
        this.add(scrollPane,BorderLayout.CENTER);

        DefaultTableCellRenderer centerRenderer = new javax.swing.table.DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        TableColumnModel columnModel = tabela.getColumnModel();
        TableColumnModel columnModeloEsp = tabelaEspecial.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        for(int i=0;i<4;i++)
        {
            columnModel.getColumn(i).setCellRenderer(centerRenderer);
            columnModeloEsp.getColumn(i).setCellRenderer(centerRenderer);
            columnModeloEsp.getColumn(i+1).setCellRenderer(centerRenderer);
        }
        tabela.getColumnModel().getColumn(0).setMaxWidth(100);
        tabelaEspecial.getColumnModel().getColumn(0).setMaxWidth(100);
        tabela.getColumnModel().getColumn(0).setMinWidth(100);
        tabelaEspecial.getColumnModel().getColumn(0).setMinWidth(100);
        tabela.getColumnModel().getColumn(2).setMaxWidth(50);
        tabelaEspecial.getColumnModel().getColumn(2).setMaxWidth(50);
        tabela.getColumnModel().getColumn(3).setMaxWidth(60);
        tabelaEspecial.getColumnModel().getColumn(4).setMaxWidth(60);

        JButton botaoAlternar = new JButton("Alternar");
        botaoAlternar.setBackground(Color.lightGray);
        botaoAlternar.addActionListener(_ -> {
            if(scrollPane.getViewport().getView() == tabela)
            {
                scrollPane.setViewportView(tabelaEspecial);
                tabelaEspecial.setVisible(true);
                modeloEspecial.fireTableDataChanged();
                tabela.setVisible(false);
            }
            else{
                scrollPane.setViewportView(tabela);
                tabela.setVisible(true);
                modeloPaciente.fireTableDataChanged();
                tabelaEspecial.setVisible(false);

            }

        });

        PainelInferior painelInferior = new PainelInferior(this,botaoFechar,botaoVoltar, botaoAdicionar, botaoAlternar);
        painelInferior.setBackground(Color.DARK_GRAY);
    }

    private static JButton getJButton(MenuInicial principal, TabelaPacientesEspeciais modeloEspecial, TabelaPacientes modeloPacientes) {
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
            JComboBox<String> comboBoxPlanos = new JComboBox<>();
            comboBoxPlanos.addItem("Nenhum");

            for (PlanoDeSaude plano : principal.getSistema().getPlanos()){
                comboBoxPlanos.addItem(plano.getCodigo());
            }

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
                        if(dataNascimento.isAfter(LocalDate.now())){
                            throw new Exception("Data de nascimento invalida!");
                        }
                        if(comboBoxPlanos.getSelectedItem()=="Nenhum"){
                            Paciente paciente = new Paciente(cpf, nome, dataNascimento);
                            idade = paciente.getIdade();
                            principal.getSistema().addPaciente(paciente);
                        }
                        else{
                            PlanoDeSaude plano = principal.getSistema().getPlanos().get(comboBoxPlanos.getSelectedIndex()-1);
                            PacienteEspecial pacienteEspecial = new PacienteEspecial(cpf, nome, dataNascimento,plano);
                            principal.getSistema().addPacienteEspecial(pacienteEspecial);
                            idade = pacienteEspecial.getIdade();
                        }
                        JOptionPane.showMessageDialog(null,
                                "Paciente Cadastrado com Sucesso!\n\n" + "CPF: " + cpf + "\n" + "Nome: " + nome + "\nIdade:"+ idade +"\nIdade"+"\nCódigo Plano de Saúde:"+comboBoxPlanos.getSelectedItem(), "Dados do Paciente", JOptionPane.INFORMATION_MESSAGE);
                        modeloPacientes.fireTableDataChanged();
                        modeloEspecial.fireTableDataChanged();
                    }catch(Exception e){
                        System.out.println("Erro ao cadastrar Paciente:"+e.getMessage());
                        JOptionPane.showMessageDialog(null,"Um erro ocorreu: \n"+e.getMessage());
                        JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            }
        });
        return botaoAdicionar;
    }
}

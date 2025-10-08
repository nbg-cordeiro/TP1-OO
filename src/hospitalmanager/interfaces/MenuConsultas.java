package hospitalmanager.interfaces;

import hospitalmanager.dominio.*;
import hospitalmanager.interfaces.ModelosTabela.TabelaConsultas;
import hospitalmanager.interfaces.elementos.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

import static hospitalmanager.persistencia.RegistroCSV.*;

public class MenuConsultas extends JFrame{

    public MenuConsultas(MenuInicial principal) {
        setSize(500,500);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        TabelaConsultas modeloConsultas = new TabelaConsultas(principal.getSistema().getConsultas());
        JTable tabela = new JTable(modeloConsultas);





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
        new BotaoColuna<>(tabela, 5, "Ver", verDetalhes100, consultaProvider);





        tabela.setBackground(Color.lightGray);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBackground(Color.gray);
        scrollPane.getViewport().setBackground(Color.GRAY);
        this.add(scrollPane,BorderLayout.CENTER);
        tabela.getColumnModel().getColumn(5).setMaxWidth(55);
        tabela.getColumnModel().getColumn(5).setMinWidth(55);
        tabela.getColumnModel().getColumn(4).setMaxWidth(100);
        tabela.getColumnModel().getColumn(4).setMinWidth(100);
        tabela.getColumnModel().getColumn(3).setMaxWidth(70);
        tabela.getColumnModel().getColumn(3).setMinWidth(70);
        tabela.getColumnModel().getColumn(2).setMaxWidth(60);
        tabela.getColumnModel().getColumn(2).setMinWidth(60);
        tabela.getColumnModel().getColumn(1).setMaxWidth(80);
        tabela.getColumnModel().getColumn(1).setMinWidth(80);
        JButton botaoAdicionar = getJButton(principal,modeloConsultas);
        DefaultTableCellRenderer centerRenderer = new javax.swing.table.DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        PainelTitulo titulo = new PainelTitulo(this,"Hospital Manager - Menu Consultas");
        titulo.setVisible(true);
        BotaoFechar botaoFechar = new BotaoFechar(this);
        BotaoVoltar botaoVoltar = new BotaoVoltar(this,principal);
        PainelInferior painelInferior = new PainelInferior(this,botaoFechar,botaoVoltar,botaoAdicionar);
        painelInferior.setVisible(true);
    }

    private static JButton getJButton(MenuInicial principal, TabelaConsultas modelo) {
        JButton botaoAdicionar = new JButton("Adicionar");
        botaoAdicionar.setBackground(Color.LIGHT_GRAY);
        botaoAdicionar.setPreferredSize(new Dimension(120, 40));
        botaoAdicionar.setMaximumSize(new Dimension(120, 40));
        botaoAdicionar.setMinimumSize(new Dimension(120, 40));

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        JPanel panelObservacoes = new JPanel(new BorderLayout (5,5));

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(5, 5));

        JComboBox<String> campoCpf = new JComboBox<>();
        for(PacienteEspecial paciente: principal.getSistema().getPacientesEspeciais()){
            campoCpf.addItem(paciente.getCpf());
        }
        for(Paciente paciente: principal.getSistema().getPacientes()){
            campoCpf.addItem(paciente.getCpf());
        }
        JComboBox<String> campoCrm = new JComboBox<>();
        for(Medico medico: principal.getSistema().getMedicos()){
            campoCrm.addItem(medico.getCrm());
        }
        MaskFormatter formatoData;
        MaskFormatter formatoHora;
        try {
            formatoData = new MaskFormatter("##/##/####");
            formatoData.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null,"Um erro ocorreu: "+e.getMessage());
            throw new RuntimeException(e);
        }
        try {
            formatoHora = new MaskFormatter("##:##");
            formatoHora.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null,"Um erro ocorreu: "+e.getMessage());
            throw new RuntimeException(e);
        }
        JFormattedTextField campoData = new JFormattedTextField(formatoData);
        JFormattedTextField campoHora = new JFormattedTextField(formatoHora);
        JTextField campoSala = new JTextField();
        JTextArea campoObservacoes = new JTextArea(4, 20);
        campoObservacoes.setLineWrap(true);
        campoObservacoes.setWrapStyleWord(true);
        JScrollPane scrollObservacoes = new JScrollPane(campoObservacoes);

        panel.add(new JLabel("CPF do Paciente:"));
        panel.add(campoCpf);
        panel.add(new JLabel("CRM do Médico:"));
        panel.add(campoCrm);
        panel.add(new JLabel("Dia:"));
        panel.add(campoData);
        panel.add(new JLabel("Hora:"));
        panel.add(campoHora);
        panel.add(new JLabel("Sala:"));
        panel.add(campoSala);
        panel.add(new JLabel("Motivo:"));
        panelObservacoes.add(scrollObservacoes, BorderLayout.CENTER);

        panelPrincipal.add(panel, BorderLayout.NORTH);
        panelPrincipal.add(panelObservacoes, BorderLayout.CENTER);


        botaoAdicionar.addActionListener(_ -> {
            int result = JOptionPane.showConfirmDialog(null, panelPrincipal, "Cadastro de Consulta",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result == JOptionPane.OK_OPTION){
                    try {
                        String cpf = Objects.requireNonNull(campoCpf.getSelectedItem()).toString();
                        String crm = Objects.requireNonNull(campoCrm.getSelectedItem()).toString();
                        String sala = campoSala.getText();
                        String dataStr = Objects.requireNonNull(campoData.getText());
                        String horaStr = Objects.requireNonNull(campoHora.getText());
                        String observacoes = campoObservacoes.getText();
                        if (cpf.isEmpty() || crm.isEmpty() || sala.isEmpty() || dataStr.contains("_") || horaStr.contains("_")|| observacoes.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Você deve preencher todos os campos!\n Cadastro cancelado!");
                        } else {
                            String dataHoraStr = dataStr + " " + horaStr;
                            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                            LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, formatador);
                            if(acharPaciente(cpf,principal.getSistema().getPacientes())==null)
                            {
                                PacienteEspecial pacienteEspecial = acharPacienteEspecial(cpf,principal.getSistema().getPacientesEspeciais());
                                Consulta consulta = new Consulta(pacienteEspecial,acharMedico(crm, principal.getSistema().getMedicos()),"Agendada", sala, dataHora, observacoes);
                                principal.getSistema().addConsulta(consulta);
                            }
                            else{
                                Paciente paciente = acharPaciente(cpf,principal.getSistema().getPacientes());
                                assert paciente != null;
                                Consulta consulta = new Consulta(paciente, acharMedico(crm, principal.getSistema().getMedicos()), "Agendada", sala, dataHora, observacoes);
                                principal.getSistema().addConsulta(consulta);
                            }

                            JOptionPane.showMessageDialog(null,
                                    "Consulta Cadastrada com Sucesso!\n\n" + "Data: " + dataStr + "\nHora: " + horaStr + "\nSala: "+ sala +"\nCpf: "+cpf+"\nCrm: "+crm+"\nMotivo: "+observacoes, "Dados do Paciente", JOptionPane.INFORMATION_MESSAGE);
                            modelo.fireTableDataChanged();
                        }
                    } catch (NullPointerException a) {
                        JOptionPane.showMessageDialog(null, "Você deve preencher todos os campos!\n Cadastro cancelado!");
                    }
                    catch(Exception e){
                        JOptionPane.showMessageDialog(null, "Um erro ocorreu: \n" + e.getMessage());
                        JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
                    }
                }
            else if(result == JOptionPane.CANCEL_OPTION){
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            }
        });
        return botaoAdicionar;
    }

}

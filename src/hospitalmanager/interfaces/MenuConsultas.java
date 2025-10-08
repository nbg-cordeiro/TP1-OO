package hospitalmanager.interfaces;

import hospitalmanager.dominio.*;
import hospitalmanager.interfaces.ModelosTabela.TabelaConsultas;
import hospitalmanager.interfaces.elementos.*;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static hospitalmanager.persistencia.RegistroCSV.*;

public class MenuConsultas extends JFrame{

    public MenuConsultas(MenuInicial principal) {
        setSize(500,500);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        TabelaConsultas modeloConsultas = new TabelaConsultas(principal.getSistema().getConsultas());
        JTable tabela = new JTable(modeloConsultas);
        tabela.setBackground(Color.lightGray);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBackground(Color.gray);
        scrollPane.getViewport().setBackground(Color.GRAY);
        this.add(scrollPane,BorderLayout.CENTER);
        tabela.getColumnModel().getColumn(5).setMaxWidth(50);
        tabela.getColumnModel().getColumn(5).setMinWidth(50);
        tabela.getColumnModel().getColumn(4).setMaxWidth(100);
        tabela.getColumnModel().getColumn(4).setMinWidth(100);
        tabela.getColumnModel().getColumn(3).setMaxWidth(70);
        tabela.getColumnModel().getColumn(3).setMinWidth(70);
        tabela.getColumnModel().getColumn(2).setMaxWidth(60);
        tabela.getColumnModel().getColumn(2).setMinWidth(60);
        tabela.getColumnModel().getColumn(1).setMaxWidth(80);
        tabela.getColumnModel().getColumn(1).setMinWidth(80);
        JButton botaoAdicionar = getJButton(principal,modeloConsultas);

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
        JPanel panelMotivo = new JPanel(new BorderLayout (5,5));

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(5, 5));


        JComboBox<String> campoCpf = new JComboBox<>();
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
        JTextArea campoMotivo = new JTextArea(4, 20);
        campoMotivo.setLineWrap(true);
        campoMotivo.setWrapStyleWord(true);
        JScrollPane scrollMotivo = new JScrollPane(campoMotivo);

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
        panelMotivo.add(scrollMotivo, BorderLayout.CENTER);

        panelPrincipal.add(panel, BorderLayout.NORTH);
        panelPrincipal.add(panelMotivo, BorderLayout.CENTER);


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
                        String motivo = campoMotivo.getText();
                        if (cpf.isEmpty() || crm.isEmpty() || sala.isEmpty() || dataStr.contains("_") || horaStr.contains("_")|| motivo.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Você deve preencher todos os campos!\n Cadastro cancelado!");
                        } else {
                            String dataHoraStr = dataStr + " " + horaStr;
                            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                            LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, formatador);
                            if(acharPaciente(cpf,principal.getSistema().getPacientes())==null)
                            {
                                PacienteEspecial pacienteEspecial = acharPacienteEspecial(cpf,principal.getSistema().getPacientesEspeciais());
                                assert pacienteEspecial != null;
                                Consulta consulta = new Consulta(pacienteEspecial,acharMedico(crm, principal.getSistema().getMedicos()),"Agendada", sala, dataHora, motivo);
                                principal.getSistema().addConsulta(consulta);
                            }
                            else{
                                Paciente paciente = acharPaciente(cpf,principal.getSistema().getPacientes());
                                assert paciente != null;
                                Consulta consulta = new Consulta(paciente, acharMedico(crm, principal.getSistema().getMedicos()), "Agendada", sala, dataHora, motivo);
                                principal.getSistema().addConsulta(consulta);
                            }

                            JOptionPane.showMessageDialog(null,
                                    "Consulta Cadastrada com Sucesso!\n\n" + "Data: " + dataStr + "\nHora: " + horaStr + "\nSala: "+ sala +"\nCpf: "+cpf+"\nCrm: "+crm+"\nMotivo: "+motivo, "Dados do Paciente", JOptionPane.INFORMATION_MESSAGE);
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

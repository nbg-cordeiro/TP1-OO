package hospitalmanager.interfaces;

import hospitalmanager.dominio.*;
import hospitalmanager.interfaces.ModelosTabela.TabelaInternacoes;
import hospitalmanager.interfaces.elementos.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

import static hospitalmanager.persistencia.RegistroCSV.*;
import static hospitalmanager.persistencia.RegistroCSV.acharMedico;
import static hospitalmanager.persistencia.RegistroCSV.acharPaciente;

public class MenuInternacoes extends JFrame{

    public MenuInternacoes(MenuInicial principal) {
        setSize(500,500);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        TabelaInternacoes modeloInternacoes = new TabelaInternacoes(principal.getSistema().getInternacoes());
        JTable tabela = new JTable(modeloInternacoes);
        tabela.setBackground(Color.lightGray);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBackground(Color.gray);
        scrollPane.getViewport().setBackground(Color.GRAY);
        this.add(scrollPane,BorderLayout.CENTER);

        tabela.getColumnModel().getColumn(0).setMaxWidth(60);
        tabela.getColumnModel().getColumn(0).setMinWidth(60);
        tabela.getColumnModel().getColumn(1).setMaxWidth(100);
        tabela.getColumnModel().getColumn(1).setMinWidth(100);
        tabela.getColumnModel().getColumn(2).setMaxWidth(70);
        tabela.getColumnModel().getColumn(2).setMinWidth(70);
        tabela.getColumnModel().getColumn(5).setMaxWidth(60);
        tabela.getColumnModel().getColumn(5).setMinWidth(60);
        tabela.getColumnModel().getColumn(6).setMaxWidth(55);
        tabela.getColumnModel().getColumn(6).setMinWidth(55);
        JButton botaoAdicionar = getJButton(principal,modeloInternacoes);
        DefaultTableCellRenderer centerRenderer = new javax.swing.table.DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        TableColumnModel columnModel = tabela.getColumnModel();

        for(int i = 0; i < tabela.getColumnModel().getColumnCount(); i++){
            if(i==5){continue;}
            columnModel.getColumn(i).setCellRenderer(centerRenderer);
        }
        Function<Integer, Internacao> internacaoProvider = modeloInternacoes::getInternacaoAt;

        Consumer<Internacao> verDetalhes = internacao ->{
            try{
                JPanel painel = new JPanel(new BorderLayout(5, 5));
                String[] opcoes = {"Dar Alta", "Mostrar Relatório","Deletar Internação","Cancelar"};
                int escolha = JOptionPane.showOptionDialog( painel,null,"Opções",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,opcoes,opcoes[3]);
                switch(escolha){
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
                        String[]opcoes2= {"Ok", "Exportar para .csv"};
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
                        int escolha2 =JOptionPane.showOptionDialog(painel, texto
                                ,
                                "Relatório da Internação:", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,opcoes2,opcoes[0]);
                                if(escolha2==1) {
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
        new BotaoColuna<>(tabela, 6, "Ver", verDetalhes, internacaoProvider);

        PainelTitulo titulo = new PainelTitulo(this,"Hospital Manager - Menu Internações");
        titulo.setVisible(true);
        BotaoFechar botaoFechar = new BotaoFechar(this);
        BotaoVoltar botaoVoltar = new BotaoVoltar(this,principal);
        PainelInferior painelInferior = new PainelInferior(this,botaoFechar,botaoVoltar,botaoAdicionar);
        painelInferior.setVisible(true);
    }

    private static JButton getJButton(MenuInicial principal, TabelaInternacoes modelo) {

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        JPanel panelObservacoes = new JPanel(new BorderLayout (5,5));

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(5, 5));

        JButton botaoAdicionar = new JButton("Adicionar");
        botaoAdicionar.setBackground(Color.LIGHT_GRAY);
        botaoAdicionar.setPreferredSize(new Dimension(120, 40));
        botaoAdicionar.setMaximumSize(new Dimension(120, 40));
        botaoAdicionar.setMinimumSize(new Dimension(120, 40));

        JComboBox<String> campoCrm = new JComboBox<>();
        for(Medico medico: principal.getSistema().getMedicos()){
            campoCrm.addItem(medico.getCrm());
        }
        JComboBox<String> campoCpf = new JComboBox<>();
        for(PacienteEspecial paciente: principal.getSistema().getPacientesEspeciais()){
            campoCpf.addItem(paciente.getCpf());
        }
        for(Paciente paciente: principal.getSistema().getPacientes()){
            campoCpf.addItem(paciente.getCpf());
        }

        JTextField campoLeito = new JTextField();
        JTextArea campoObservacoes = new JTextArea(4, 20);
        campoObservacoes.setLineWrap(true);
        campoObservacoes.setWrapStyleWord(true);
        JScrollPane scrollObservacoes = new JScrollPane(campoObservacoes);

        panel.add(new JLabel("CPF do Paciente:"));
        panel.add(campoCpf);
        panel.add(new JLabel("CRM do Médico:"));
        panel.add(campoCrm);
        panel.add(new JLabel("Leito:"));
        panel.add(campoLeito);

        panel.add(new JLabel("Observações:"));
        panelObservacoes.add(scrollObservacoes, BorderLayout.CENTER);

        panelPrincipal.add(panel, BorderLayout.NORTH);
        panelPrincipal.add(panelObservacoes, BorderLayout.CENTER);

        botaoAdicionar.addActionListener(_ -> {
            int result = JOptionPane.showConfirmDialog(null, panelPrincipal, "Cadastro de Consulta",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result == JOptionPane.OK_OPTION){
                try {
                    if(campoCpf.getSelectedItem()==(null)||campoCrm.getSelectedItem()==null|| campoLeito.getText().isEmpty() || campoObservacoes.getText().isEmpty()){
                        throw new NullPointerException();
                    }
                    String cpf = Objects.requireNonNull(campoCpf.getSelectedItem()).toString();
                    String crm = Objects.requireNonNull(campoCrm.getSelectedItem()).toString();
                    String leito =  campoLeito.getText();
                    String observacoes = campoObservacoes.getText();
                    LocalDate data = LocalDate.now();
                    if(acharPaciente(cpf,principal.getSistema().getPacientes())==null) {
                        PacienteEspecial pacienteEspecial = acharPacienteEspecial(cpf,principal.getSistema().getPacientesEspeciais());
                        Internacao internacao = new Internacao(pacienteEspecial,acharMedico(crm, principal.getSistema().getMedicos()),leito,data, observacoes);
                        principal.getSistema().addInternacao(internacao);
                    }
                    else{
                        Paciente paciente = acharPaciente(cpf,principal.getSistema().getPacientes());
                        assert paciente != null;
                        Internacao internacao = new Internacao(paciente,acharMedico(crm, principal.getSistema().getMedicos()),leito,data, observacoes);
                        principal.getSistema().addInternacao(internacao);
                    }
                    JOptionPane.showMessageDialog(null,
                            "Internação Cadastrada com Sucesso!\n" + "\nData: " + data + "\nLeito: " + leito + "\nCPF: " + cpf + "\nCrm: "+crm, "Dados do Medico", JOptionPane.INFORMATION_MESSAGE);
                    modelo.fireTableDataChanged();
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

package hospitalmanager.interfaces;

import hospitalmanager.interfaces.ModelosTabela.TabelaPlanos;
import hospitalmanager.interfaces.elementos.BotaoFechar;
import hospitalmanager.interfaces.elementos.BotaoVoltar;
import hospitalmanager.interfaces.elementos.PainelTitulo;
import hospitalmanager.interfaces.elementos.PainelInferior;

import javax.swing.*;
import java.awt.*;

public class MenuPlanos extends JFrame {
    public MenuPlanos(MenuInicial principal){
        setSize(500,500);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.GRAY);
        BotaoFechar botaoFechar = new BotaoFechar(this);
        BotaoVoltar botaoVoltar = new BotaoVoltar(this,principal);
        JButton botaoAdicionar = getJButton(principal);
        setBackground(Color.GRAY);
        PainelInferior painelInferior = new PainelInferior(this,botaoFechar,botaoVoltar,botaoAdicionar);
        painelInferior.setBackground(Color.DARK_GRAY);
        PainelTitulo titulo = new PainelTitulo(this,"Hospital Manager - Menu Planos");
        titulo.setVisible(true);

        TabelaPlanos modeloPlanos = new TabelaPlanos(principal.getSistema().getPlanos());
        JTable tabela = new JTable(modeloPlanos);
        tabela.setBackground(Color.lightGray);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBackground(Color.gray);
        scrollPane.getViewport().setBackground(Color.GRAY);
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
        botaoAdicionar.setBackground(Color.LIGHT_GRAY);
        botaoAdicionar.setPreferredSize(new Dimension(120, 40));
        botaoAdicionar.setMaximumSize(new Dimension(120, 40));
        botaoAdicionar.setMinimumSize(new Dimension(120, 40));

        botaoAdicionar.addActionListener(_ -> {
            JTextField campoNome = new JTextField();
            JTextField campoCodigo = new JTextField();
            JTextField campoTelefone = new JTextField();
            JTextField campoDescInter = new JTextField();
            JTextField campoDescCons = new JTextField();

            JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

            panel.add(new JLabel("Codigo:"));
            panel.add(campoCodigo);
            panel.add(new JLabel("Nome:"));
            panel.add(campoNome);
            panel.add(new JLabel("Telefone:"));
            panel.add(campoTelefone);
            panel.add(new JLabel("Desconto Internações (%):"));
            panel.add(campoDescInter);
            panel.add(new JLabel("Desconto Consultas (%):"));
            panel.add(campoDescCons);

            int result = JOptionPane.showConfirmDialog(null, panel, "Cadastro de Plano de Saúde",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION){
                // colocar coisas aqui depois
            } else {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            }
        });
        return botaoAdicionar;
    }
}

package hospitalmanager.interfaces;

import hospitalmanager.interfaces.ModelosTabela.TabelaPacientes;
import hospitalmanager.interfaces.elementos.BotaoFechar;
import hospitalmanager.interfaces.elementos.BotaoVoltar;
import hospitalmanager.interfaces.elementos.PainelTitulo;
import hospitalmanager.interfaces.elementos.PainelInferior;

import javax.swing.*;
import java.awt.*;

public class MenuPacientes extends JFrame {
    public MenuPacientes(MenuInicial passada){
        super("                 Hospital Manager - Menu Pacientes");
        setSize(500,500);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.gray);
        
        JButton botaoAdicionar = new JButton("Adicionar");
        botaoAdicionar.setPreferredSize(new Dimension(120, 40));
        botaoAdicionar.setMaximumSize(new Dimension(120, 40));
        botaoAdicionar.setMinimumSize(new Dimension(120, 40));
        botaoAdicionar.addActionListener(_ -> {

        });
        
        BotaoFechar botaoFechar = new BotaoFechar(this);
        BotaoVoltar botaoVoltar = new BotaoVoltar(this,passada);
        PainelInferior painelInferior = new PainelInferior(this,botaoFechar,botaoVoltar, botaoAdicionar);
        painelInferior.setBackground(Color.DARK_GRAY);
        PainelTitulo titulo = new PainelTitulo(this,"Hospital Manager - Menu Pacientes");
        titulo.setVisible(true);

        TabelaPacientes modeloPaciente = new TabelaPacientes(passada.getSistema().getPacientes());
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
}

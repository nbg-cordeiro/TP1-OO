package hospitalmanager.interfaces;

import hospitalmanager.interfaces.ModelosTabela.TabelaMedicos;
import hospitalmanager.interfaces.elementos.BotaoFechar;
import hospitalmanager.interfaces.elementos.BotaoVoltar;
import hospitalmanager.interfaces.elementos.PainelTitulo;
import hospitalmanager.interfaces.elementos.PainelInferior;

import javax.swing.*;
import java.awt.*;

public class MenuMedicos extends JFrame {
    public MenuMedicos(MenuInicial passada){
        super("               Hospital Manager - Menu Medicos");
        setSize(500,500);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.gray);
        BotaoFechar botaoFechar = new BotaoFechar(this);
        BotaoVoltar botaoVoltar = new BotaoVoltar(this,passada);
        PainelInferior painelInferior = new PainelInferior(this,botaoFechar,botaoVoltar);
        painelInferior.setBackground(Color.gray);
        PainelTitulo titulo = new PainelTitulo(this,"Hospital Manager - Menu Medicos");
        titulo.setVisible(true);
        TabelaMedicos modeloMedico = new TabelaMedicos(passada.getSistema().getMedicos());
        JTable tabela = new JTable(modeloMedico);
        JScrollPane scrollPane = new JScrollPane(tabela);
        this.add(scrollPane,BorderLayout.CENTER);

        setVisible(true);
    }
}

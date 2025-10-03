package hospitalmanager.interfaces;

import hospitalmanager.interfaces.ModelosTabela.TabelaMedicos;
import hospitalmanager.interfaces.elementos.BotaoFechar;
import hospitalmanager.interfaces.elementos.BotaoVoltar;
import hospitalmanager.interfaces.elementos.PainelTitulo;
import hospitalmanager.interfaces.elementos.Painelnferior;

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
        Painelnferior painelnferior = new Painelnferior(this,botaoFechar,botaoVoltar);
        PainelTitulo titulo = new PainelTitulo(this,"Hospital Manager - Menu Medicos");

        TabelaMedicos modeloMedico = new TabelaMedicos(passada.getSistema().getMedicos());
        JTable tabela = new JTable(modeloMedico);
        JScrollPane scrollPane = new JScrollPane(tabela);
        this.add(scrollPane,BorderLayout.CENTER);

        setVisible(true);
    }
}

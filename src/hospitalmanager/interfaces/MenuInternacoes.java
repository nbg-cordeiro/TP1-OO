package hospitalmanager.interfaces;

import hospitalmanager.interfaces.elementos.*;

import javax.swing.*;
import java.awt.*;

public class MenuInternacoes extends JFrame {
    public MenuInternacoes(JFrame passada){
        setSize(500,500);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.gray);


        BotaoFechar botaoFechar = new BotaoFechar(this);
        BotaoVoltar botaoVoltar = new BotaoVoltar(this,passada);
        PainelInferior painelInferior = new PainelInferior(this,botaoFechar,botaoVoltar);
        painelInferior.setVisible(true);
        PainelTitulo titulo = new PainelTitulo(this,"Hospital Manager - Menu Internações");
        titulo.setVisible(true);
    }
}

package hospitalmanager.interfaces;

import hospitalmanager.interfaces.elementos.BotaoFechar;
import hospitalmanager.interfaces.elementos.BotaoVoltar;
import hospitalmanager.interfaces.elementos.PainelTitulo;
import hospitalmanager.interfaces.elementos.Painelnferior;

import javax.swing.*;
import java.awt.*;

public class MenuGeral extends JFrame {
    public MenuGeral(JFrame passada){
        setSize(500,500);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.gray);

        BotaoFechar botaoFechar = new BotaoFechar(this);
        BotaoVoltar botaoVoltar = new BotaoVoltar(this,passada);
        Painelnferior painelnferior = new Painelnferior(this,botaoFechar,botaoVoltar);
        PainelTitulo titulo = new PainelTitulo(this,"Hospital Manager - Menu Geral");

        setVisible(true);
    }
}

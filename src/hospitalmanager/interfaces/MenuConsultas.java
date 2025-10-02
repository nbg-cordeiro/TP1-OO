package hospitalmanager.interfaces;

import hospitalmanager.interfaces.elementos.BotaoFechar;
import hospitalmanager.interfaces.elementos.BotaoVoltar;
import hospitalmanager.interfaces.elementos.PainelTitulo;
import hospitalmanager.interfaces.elementos.Painelnferior;

import javax.swing.*;
import java.awt.*;

public class MenuConsultas extends JFrame {
    public MenuConsultas(JFrame passada) {
        setSize(500,500);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.gray);

        PainelTitulo titulo = new PainelTitulo(this,"Hospital Manager - Menu Consultas");
        BotaoFechar botaoFechar = new BotaoFechar(this);
        BotaoVoltar botaoVoltar = new BotaoVoltar(this,passada);
        Painelnferior painelnferior = new Painelnferior(this,botaoFechar,botaoVoltar);
        setVisible(true);
    }


}

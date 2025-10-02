package hospitalmanager.interfaces.elementos;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Painelnferior extends JPanel {
    public Painelnferior(JFrame principal,BotaoFechar botaoFechar, BotaoVoltar botaoVoltar) {
        add(botaoVoltar);
        add(botaoFechar);
        setLayout(new GridLayout(1, 2, 250,250));
        setBackground(Color.darkGray);
        principal.add(this, BorderLayout.SOUTH);
        setVisible(true);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }
    public Painelnferior(JFrame principal,BotaoVoltar botaoVoltar){
        add(botaoVoltar);
        setBackground(Color.darkGray);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        principal.add(this, BorderLayout.SOUTH);
        setVisible(true);
    }
    public Painelnferior(JFrame principal,BotaoFechar botaoFechar) {
        add(botaoFechar);
        setBackground(Color.darkGray);
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        principal.add(this, BorderLayout.SOUTH);
        setVisible(true);
    }

}

package hospitalmanager.interfaces.elementos;

import javax.swing.*;
import java.awt.*;

public class PainelInferior extends JPanel {
    public PainelInferior(JFrame principal, BotaoFechar botaoFechar, BotaoVoltar botaoVoltar) {
        add(botaoVoltar);
        add(botaoFechar);
        setLayout(new GridLayout(1, 2, 250,250));
        setBackground(Color.darkGray);
        principal.add(this, BorderLayout.SOUTH);
        setVisible(true);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }
    public PainelInferior(JFrame principal, BotaoFechar botaoFechar) {
        add(botaoFechar);
        setBackground(Color.darkGray);
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        principal.add(this, BorderLayout.SOUTH);
        setVisible(true);
    }

}

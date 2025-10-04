package hospitalmanager.interfaces.elementos;

import javax.swing.*;
import java.awt.*;

public class BotaoVoltar extends JButton {
    public BotaoVoltar(JFrame principal, JFrame passada) {

        super("Voltar");
        setPreferredSize(new Dimension(120, 40));
        setMaximumSize(new Dimension(120, 40));
        setMinimumSize(new Dimension(120, 40));
        setBackground(new Color(30,70,240));
        setForeground(Color.white);
        addActionListener(_ -> {
            principal.setVisible(false);
            passada.setVisible(true);
        });
    }
}




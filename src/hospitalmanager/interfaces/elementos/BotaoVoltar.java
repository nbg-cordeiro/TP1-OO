package hospitalmanager.interfaces.elementos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotaoVoltar extends JButton {
    public BotaoVoltar(JFrame principal, JFrame passada) {

        super("Voltar");
        setPreferredSize(new Dimension(120, 40));
        setBackground(new Color(30,70,240));
        setForeground(Color.white);
        addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                principal.setVisible(false);
                passada.setVisible(true);
            }
        });
    }
}

package hospitalmanager.interfaces.elementos;

import hospitalmanager.dominio.Sistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BotaoFechar extends JButton {
    public BotaoFechar(JFrame principal) {
        super("Salvar e Sair");
        setForeground(Color.white);
        setPreferredSize(new Dimension(120, 40));
        setMaximumSize(new Dimension(120, 40));
        setMinimumSize(new Dimension(120, 40));
        setBackground(new Color(200,40,50));
        addActionListener(_ -> {
            Sistema.salvarTudo();
            System.out.println("Finalizando...");
            System.exit(1);
        });
        principal.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosed(WindowEvent e){
                Sistema.salvarTudo();
                System.out.println("Finalizando...");
                System.exit(1);
            }
        });
    }

}

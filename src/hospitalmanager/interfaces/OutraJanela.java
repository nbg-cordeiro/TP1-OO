package hospitalmanager.interfaces;

import hospitalmanager.dominio.Sistema;

import javax.swing.*;

public class OutraJanela extends JFrame{
    public OutraJanela() {
        super("Outra Janela do Hospital Manager");
        setSize(1000,500);
        setLocationRelativeTo(null);
        setVisible(true);
        JLabel label = new JLabel("Testando Labels");
        getContentPane().add(label);
    }
}

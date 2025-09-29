package hospitalmanager.interfaces;

import hospitalmanager.dominio.Sistema;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JanelaComum extends JFrame{
    public JanelaComum() {
        super("Hospital Manager");
        setSize(1000,500);
        setLocationRelativeTo(null);
        JLabel label = new JLabel("Texto qualquer");
        getContentPane().add(label);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                Sistema.salvarTudo();
                System.out.println("Finalizando...");
                System.exit(1);
            }
        });
    }
}

package hospitalmanager.interfaces.elementos;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PainelTitulo extends JPanel {
    public PainelTitulo(JFrame principal,String titulo) {
        setBackground(Color.darkGray);

        JLabel labelTitulo = new JLabel(titulo);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        labelTitulo.setForeground(Color.white);
        add(labelTitulo, BorderLayout.CENTER);
        Border bordaInferior = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY);
        Border margemInterna = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(bordaInferior, margemInterna));
        principal.add(this,BorderLayout.NORTH);
    }
}

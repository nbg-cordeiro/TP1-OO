package hospitalmanager.interfaces;

import hospitalmanager.dominio.Sistema;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class JanelaComum extends JFrame{
    public JanelaComum() {

        super("Hospital Manager");
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setUndecorated(true);
        setLayout(new BorderLayout());

        JPanel painelTitulo = getPainelTitulo();
        add(painelTitulo, BorderLayout.NORTH);

        JButton botaoFechar = new JButton("Sair");
        botaoFechar.setPreferredSize(new Dimension(100,50));
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(botaoFechar);
        add(painelBotoes, BorderLayout.SOUTH);
        painelBotoes.setLayout(new FlowLayout(FlowLayout.RIGHT));
        botaoFechar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Sistema.salvarTudo();
                System.out.println("Finalizando...");
                System.exit(1);
            }
        });
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosed(WindowEvent e){
                Sistema.salvarTudo();
                System.out.println("Finalizando...");
                System.exit(1);
            }
        });
    }

    private static JPanel getPainelTitulo() {
        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBackground(new Color(240, 240, 240));
        JLabel labelTitulo = new JLabel("  Hospital Manager");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        painelTitulo.add(labelTitulo, BorderLayout.WEST);

        Border bordaInferior = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY);
        Border margemInterna = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        painelTitulo.setBorder(BorderFactory.createCompoundBorder(bordaInferior, margemInterna));
        return painelTitulo;
    }
}

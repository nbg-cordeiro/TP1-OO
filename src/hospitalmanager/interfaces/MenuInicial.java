package hospitalmanager.interfaces;

import hospitalmanager.dominio.Sistema;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

import static hospitalmanager.app.App.executarOpcao;


public class MenuInicial extends JFrame{

    public MenuInicial(){
        super("Hospital Manager");
        setSize(500,500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel painelTitulo = getPainelTitulo();
        add(painelTitulo, BorderLayout.NORTH);
        JButton botaoPacientes = new JButton("Pacientes");
        botaoPacientes.setPreferredSize(new Dimension(200,100));
        botaoPacientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                executarOpcao(1);
                setVisible(false);
            }
        });
        JButton botaoConsultas = new JButton("Consultas");
        botaoConsultas.setPreferredSize(new Dimension(200,100));
        botaoConsultas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent b) {
                executarOpcao(2);
                setVisible(false);
            }
        });
        JButton botaoInternacoes = new JButton("Internações");
        botaoInternacoes.setPreferredSize(new Dimension(200,100));
        botaoInternacoes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent c) {
                executarOpcao(3);
                setVisible(false);
            }
        });
        JButton botaoMedicos = new JButton("Medicos");
        botaoMedicos.setPreferredSize(new Dimension(200,100));
        botaoMedicos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent d) {
                executarOpcao(4);
                setVisible(false);
            }
        });
        JButton botaoPlanos = new JButton("Planos de Saúde");
        botaoPlanos.setPreferredSize(new Dimension(200,100));
        botaoPlanos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                executarOpcao(5);
                setVisible(false);
            }
        });
        JButton botaoGeral = new JButton("Geral");
        botaoGeral.setPreferredSize(new Dimension(200,100));
        botaoGeral.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent f) {
                executarOpcao(6);
                setVisible(false);
            }
        });
        JPanel painelBotoes = new JPanel(new GridLayout(3, 2, 20, 20));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(painelBotoes, BorderLayout.CENTER);
        painelBotoes.add(botaoPacientes);
        painelBotoes.add(botaoConsultas);
        painelBotoes.add(botaoInternacoes);
        painelBotoes.add(botaoMedicos);
        painelBotoes.add(botaoPlanos);
        painelBotoes.add(botaoGeral);

        JButton botaoFechar = new JButton("Salvar e Sair");
        botaoFechar.setPreferredSize(new Dimension(120,40));
        JPanel painelBotaoSair = new JPanel();
        painelBotaoSair.add(botaoFechar);
        add(painelBotaoSair, BorderLayout.SOUTH);
        painelBotaoSair.setLayout(new FlowLayout(FlowLayout.RIGHT));

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
        setVisible(true);
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

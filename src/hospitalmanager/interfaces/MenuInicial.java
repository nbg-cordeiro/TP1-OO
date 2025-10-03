package hospitalmanager.interfaces;

import hospitalmanager.dominio.Sistema;
import hospitalmanager.interfaces.elementos.BotaoFechar;
import hospitalmanager.interfaces.elementos.PainelTitulo;
import hospitalmanager.interfaces.elementos.Painelnferior;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MenuInicial extends JFrame{
    private Sistema sistema = new Sistema();
    public MenuInicial(Sistema sistema){
        this.sistema = sistema;
        setSize(800,450);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.gray);

        PainelTitulo titulo = new PainelTitulo(this,"Hospital Manager - Menu Inicial");

        JButton botaoPacientes = new JButton("Pacientes");
        botaoPacientes.setBackground(Color.lightGray);
        botaoPacientes.setPreferredSize(new Dimension(200,100));
        botaoPacientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                MenuPacientes mPacie = new MenuPacientes(MenuInicial.this);
                setVisible(false);
            }
        });
        JButton botaoConsultas = new JButton("Consultas");
        botaoConsultas.setBackground(Color.lightGray);
        botaoConsultas.setPreferredSize(new Dimension(200,100));
        botaoConsultas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent b) {
                MenuConsultas mCons = new MenuConsultas(MenuInicial.this);
                setVisible(false);
            }
        });
        JButton botaoInternacoes = new JButton("Internações");
        botaoInternacoes.setBackground(Color.lightGray);
        botaoInternacoes.setPreferredSize(new Dimension(200,100));
        botaoInternacoes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent c) {
                MenuInternacoes mInters = new MenuInternacoes(MenuInicial.this);
                setVisible(false);
            }
        });
        JButton botaoMedicos = new JButton("Medicos");
        botaoMedicos.setBackground(Color.lightGray);
        botaoMedicos.setPreferredSize(new Dimension(200,100));
        botaoMedicos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent d) {
                MenuMedicos mMedics = new MenuMedicos(MenuInicial.this);
                setVisible(false);
            }
        });
        JButton botaoPlanos = new JButton("Planos de Saúde");
        botaoPlanos.setBackground(Color.lightGray);
        botaoPlanos.setPreferredSize(new Dimension(200,100));
        botaoPlanos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuPlanos mPlanos = new MenuPlanos(MenuInicial.this);
                setVisible(false);
            }
        });
        JButton botaoGeral = new JButton("Geral");
        botaoGeral.setBackground(Color.lightGray);
        botaoGeral.setPreferredSize(new Dimension(200,100));
        botaoGeral.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent f) {
                MenuGeral mGeral = new MenuGeral(MenuInicial.this);
                setVisible(false);
            }
        });
        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(Color.gray);
        painelBotoes.setLayout(new GridLayout(3, 2, 10,10));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        painelBotoes.add(botaoGeral);
        painelBotoes.add(botaoPlanos);
        painelBotoes.add(botaoConsultas);
        painelBotoes.add(botaoInternacoes);
        painelBotoes.add(botaoPacientes);
        painelBotoes.add(botaoMedicos);
        add(painelBotoes, BorderLayout.CENTER);

        BotaoFechar botaoFechar = new BotaoFechar(this);
        JPanel painelInferior = new Painelnferior(this,botaoFechar);
        setVisible(true);
    }
    public Sistema getSistema()
    {
        return sistema;
    }
}

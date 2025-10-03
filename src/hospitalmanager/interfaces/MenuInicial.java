package hospitalmanager.interfaces;

import hospitalmanager.dominio.Sistema;
import hospitalmanager.interfaces.elementos.BotaoFechar;
import hospitalmanager.interfaces.elementos.PainelTitulo;
import hospitalmanager.interfaces.elementos.PainelInferior;

import javax.swing.*;
import java.awt.*;


public class MenuInicial extends JFrame{
    private final Sistema sistema;
    public MenuInicial(Sistema sistema){
        this.sistema = sistema;
        setSize(800,450);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.gray);

        PainelTitulo titulo = new PainelTitulo(this,"Hospital Manager - Menu Inicial");
        titulo.setVisible(true);

        JButton botaoPacientes = new JButton("Pacientes");
        botaoPacientes.setBackground(Color.lightGray);
        botaoPacientes.setPreferredSize(new Dimension(200,100));
        botaoPacientes.addActionListener(_ -> {
            MenuPacientes mPacie = new MenuPacientes(MenuInicial.this);
            mPacie.setVisible(true);
            setVisible(false);
        });
        JButton botaoConsultas = new JButton("Consultas");
        botaoConsultas.setBackground(Color.lightGray);
        botaoConsultas.setPreferredSize(new Dimension(200,100));
        botaoConsultas.addActionListener(_ -> {
            MenuConsultas mCons = new MenuConsultas(MenuInicial.this);
            mCons.setVisible(true);
            setVisible(false);
        });
        JButton botaoInternacoes = new JButton("Internações");
        botaoInternacoes.setBackground(Color.lightGray);
        botaoInternacoes.setPreferredSize(new Dimension(200,100));
        botaoInternacoes.addActionListener(_ -> {
            MenuInternacoes mInters = new MenuInternacoes(MenuInicial.this);
            mInters.setVisible(true);
            setVisible(false);
        });
        JButton botaoMedicos = new JButton("Medicos");
        botaoMedicos.setBackground(Color.lightGray);
        botaoMedicos.setPreferredSize(new Dimension(200,100));
        botaoMedicos.addActionListener(_ -> {
            MenuMedicos mMedics = new MenuMedicos(MenuInicial.this);
            mMedics.setVisible(true);
            setVisible(false);
        });
        JButton botaoPlanos = new JButton("Planos de Saúde");
        botaoPlanos.setBackground(Color.lightGray);
        botaoPlanos.setPreferredSize(new Dimension(200,100));
        botaoPlanos.addActionListener(_ -> {
            MenuPlanos mPlanos = new MenuPlanos(MenuInicial.this);
            mPlanos.setVisible(true);
            setVisible(false);
        });
        JButton botaoGeral = new JButton("Geral");
        botaoGeral.setBackground(Color.lightGray);
        botaoGeral.setPreferredSize(new Dimension(200,100));
        botaoGeral.addActionListener(_ -> {
            MenuGeral mGeral = new MenuGeral(MenuInicial.this);
            mGeral.setVisible(true);
            setVisible(false);
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
        JPanel painelInferior = new PainelInferior(this,botaoFechar);
        painelInferior.setVisible(true);
        setVisible(true);
    }
    public Sistema getSistema()
    {
        return sistema;
    }
}

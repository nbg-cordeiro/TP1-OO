package hospitalmanager.interfaces;

import hospitalmanager.interfaces.elementos.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static hospitalmanager.persistencia.RegistroCSV.*;

public class MenuGeral extends JFrame {
    public MenuGeral(MenuInicial principal){
        setSize(500,500);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.gray);
        setBackground(Color.gray);
        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(Color.gray);
        painelBotoes.setLayout(new GridLayout(4, 2, 25,25));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton botaoCasosDeTeste = new JButton("Adicionar objetos de teste");
        botaoCasosDeTeste.setBackground(Color.lightGray);
        JButton botaoDelPacientes = new JButton("Deletar Pacientes");
        botaoDelPacientes.setBackground(Color.pink);
        botaoDelPacientes.setForeground(Color.RED);JButton botaoDelPacientesEspeciais = new JButton("Deletar Especiais");
        botaoDelPacientes.setBackground(Color.pink);
        botaoDelPacientes.setForeground(Color.RED);
        JButton botaoDelConsultas = new JButton("Deletar Consultas");
        botaoDelConsultas.setBackground(Color.pink);
        botaoDelConsultas.setForeground(Color.RED);
        JButton botaoDelMedicos = new JButton("Deletar Medicos");
        botaoDelMedicos.setBackground(Color.pink);
        botaoDelMedicos.setForeground(Color.RED);
        JButton botaoDelInternacoes = new JButton("Deletar Internações");
        botaoDelInternacoes.setBackground(Color.pink);
        botaoDelInternacoes.setForeground(Color.RED);
        JButton botaoDelPlanos = new JButton("Deletar Planos de Saúde");
        botaoDelPlanos.setBackground(Color.pink);
        botaoDelPlanos.setForeground(Color.RED);
        JButton botaoDelEspecialidades = new JButton("Deletar Planos de Saúde");
        botaoDelEspecialidades.setBackground(Color.pink);
        botaoDelEspecialidades.setForeground(Color.RED);

        painelBotoes.add(botaoCasosDeTeste);
        painelBotoes.add(botaoDelPacientes);
        painelBotoes.add(botaoDelMedicos);
        painelBotoes.add(botaoDelPacientesEspeciais);
        painelBotoes.add(botaoDelPlanos);
        painelBotoes.add(botaoDelConsultas);
        painelBotoes.add(botaoDelInternacoes);
        painelBotoes.add(botaoDelEspecialidades);
        add(painelBotoes);
        botaoCasosDeTeste.addActionListener(_ -> adicionarObjetosTeste(principal));
        botaoDelPlanos.addActionListener(_ -> {
            try {
                deletarPlanos(principal.getSistema());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        botaoDelConsultas.addActionListener(_ -> {
            try {
                deletarConsultas(principal.getSistema());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        botaoDelInternacoes.addActionListener(_ -> {
            try {
                deletarInternacoes(principal.getSistema());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        botaoDelPacientes.addActionListener(_ -> {
            try {
                deletarPacientes(principal.getSistema());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        botaoDelPacientesEspeciais.addActionListener(_ -> {
            try {
                deletarPacientesEspeciais(principal.getSistema());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        botaoDelMedicos.addActionListener(_ -> {
            try {
                deletarMedicos(principal.getSistema());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        botaoDelEspecialidades.addActionListener(_ -> {
            try {
                deletarEspecialidades(principal.getSistema());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        BotaoFechar botaoFechar = new BotaoFechar(this);
        BotaoVoltar botaoVoltar = new BotaoVoltar(this,principal);
        PainelInferior painelInferior = new PainelInferior(this,botaoFechar,botaoVoltar);
        painelInferior.setVisible(true);
        PainelTitulo titulo = new PainelTitulo(this,"Hospital Manager - Menu Geral");
        titulo.setVisible(true);
    }
    public void adicionarObjetosTeste(MenuInicial principal)
    {

    }
}

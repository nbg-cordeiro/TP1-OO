package hospitalmanager.interfaces;

import hospitalmanager.dominio.Medico;
import hospitalmanager.dominio.Paciente;
import hospitalmanager.interfaces.elementos.BotaoFechar;
import hospitalmanager.interfaces.elementos.BotaoVoltar;
import hospitalmanager.interfaces.elementos.PainelTitulo;
import hospitalmanager.interfaces.elementos.PainelInferior;
import hospitalmanager.persistencia.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;

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
        painelBotoes.setLayout(new GridLayout(3, 2, 40,40));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton botaoCasosDeTeste = new JButton("Adicionar objetos de teste");
        botaoCasosDeTeste.setBackground(Color.lightGray);
        JButton botaoDelPacientes = new JButton("Deletar Pacientes");
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


        painelBotoes.add(botaoCasosDeTeste);
        painelBotoes.add(botaoDelPacientes);
        painelBotoes.add(botaoDelMedicos);
        painelBotoes.add(botaoDelPlanos);
        painelBotoes.add(botaoDelConsultas);
        painelBotoes.add(botaoDelInternacoes);
        add(painelBotoes);
        botaoCasosDeTeste.addActionListener(_ -> adicionarObjetosTeste(principal));
        botaoDelPlanos.addActionListener(_ -> {
            try {
                RegistroPlanos.deletarRegistro(principal.getSistema());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        botaoDelConsultas.addActionListener(_ -> {
            try {
                RegistroConsulta.deletarRegistro(principal.getSistema());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        botaoDelInternacoes.addActionListener(_ -> {
            try {
                RegistroInternacoes.deletarRegistro(principal.getSistema());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        botaoDelPacientes.addActionListener(_ -> {
            try {
                RegistroPaciente.deletarRegistro(principal.getSistema());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        botaoDelMedicos.addActionListener(_ -> {
            try {
                RegistroMedico.deletarRegistro(principal.getSistema());
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
        Paciente paciente1 = new Paciente("111.222.333-44", "Ana Clara Sousa", LocalDate.of(1997, 1, 1));
        principal.getSistema().addPaciente(paciente1);
        Paciente paciente2 = new Paciente("222.333.444-55", "Bruno Costa Oliveira", LocalDate.of(1980, 1, 1));
        principal.getSistema().addPaciente(paciente2);
        Paciente paciente3 = new Paciente("333.444.555-66", "Carla Dias Ferreira", LocalDate.of(2006, 1, 1));
        principal.getSistema().addPaciente(paciente3);
        Paciente paciente4 = new Paciente("444.555.666-77", "Davi Martins Rodrigues", LocalDate.of(2017, 1, 1));
        principal.getSistema().addPaciente(paciente4);
        Paciente paciente5 = new Paciente("555.666.777-88", "Eduarda Gomes Almeida", LocalDate.of(1991, 1, 1));
        principal.getSistema().addPaciente(paciente5);
        Paciente paciente6 = new Paciente("666.777.888-99", "Felipe Azevedo Barros", LocalDate.of(1973, 1, 1));
        principal.getSistema().addPaciente(paciente6);
        Paciente paciente7 = new Paciente("777.888.999-00", "Gabriela Lima Santos", LocalDate.of(2003, 1, 1));
        principal.getSistema().addPaciente(paciente7);
        Paciente paciente8 = new Paciente("888.999.000-11", "Heitor Pereira Cardoso", LocalDate.of(1949, 1, 1));
        principal.getSistema().addPaciente(paciente8);
        Paciente paciente9 = new Paciente("999.000.111-22", "Isabela Ribeiro Correia", LocalDate.of(1994, 1, 1));
        principal.getSistema().addPaciente(paciente9);
        Paciente paciente10 = new Paciente("000.111.222-33", "João Vitor Monteiro", LocalDate.of(1964, 1, 1));
        principal.getSistema().addPaciente(paciente10);
        Paciente paciente11 = new Paciente("123.456.789-01", "Larissa Mendes Nunes", LocalDate.of(2000, 1, 1));
        principal.getSistema().addPaciente(paciente11);
        Paciente paciente12 = new Paciente("234.567.890-12", "Marcos Vinícius Cunha", LocalDate.of(1985, 1, 1));
        principal.getSistema().addPaciente(paciente12);
        Paciente paciente13 = new Paciente("345.678.901-23", "Natália Fernandes Pinto", LocalDate.of(2013, 1, 1));
        principal.getSistema().addPaciente(paciente13);
        Paciente paciente14 = new Paciente("456.789.012-34", "Otávio Castro Ramos", LocalDate.of(1944, 1, 1));
        principal.getSistema().addPaciente(paciente14);
        Paciente paciente15 = new Paciente("567.890.123-45", "Patrícia Teixeira Sales", LocalDate.of(1996, 1, 1));
        principal.getSistema().addPaciente(paciente15);
        Paciente paciente16 = new Paciente("678.901.234-56", "Quintino Rocha Borges", LocalDate.of(1970, 1, 1));
        principal.getSistema().addPaciente(paciente16);
        Paciente paciente17 = new Paciente("789.012.345-67", "Rafael Silveira Farias", LocalDate.of(1987, 1, 1));
        principal.getSistema().addPaciente(paciente17);
        Paciente paciente18 = new Paciente("890.123.456-78", "Sofia Gonçalves Mota", LocalDate.of(2004, 1, 1));
        principal.getSistema().addPaciente(paciente18);
        Paciente paciente19 = new Paciente("901.234.567-89", "Thiago Nogueira Viana", LocalDate.of(1995, 1, 1));
        principal.getSistema().addPaciente(paciente19);
        Paciente paciente20 = new Paciente("012.345.678-90", "Úrsula Freitas Pires", LocalDate.of(1983, 1, 1));
        principal.getSistema().addPaciente(paciente20);
        Paciente paciente21 = new Paciente("112.233.445-56", "Valentina Rezende Macedo", LocalDate.of(2020, 1, 1));
        principal.getSistema().addPaciente(paciente21);
        Paciente paciente22 = new Paciente("223.344.556-67", "Wagner Barbosa Duarte", LocalDate.of(1958, 1, 1));
        principal.getSistema().addPaciente(paciente22);
        Paciente paciente23 = new Paciente("334.455.667-78", "Xavier Arantes Peixoto", LocalDate.of(1953, 1, 1));
        principal.getSistema().addPaciente(paciente23);
        Paciente paciente24 = new Paciente("445.566.778-89", "Yasmin Costela Santana", LocalDate.of(2007, 1, 1));
        principal.getSistema().addPaciente(paciente24);
        Paciente paciente25 = new Paciente("556.677.889-90", "Zélia Campos Melo", LocalDate.of(1936, 1, 1));
        principal.getSistema().addPaciente(paciente25);
        Paciente paciente26 = new Paciente("667.788.990-01", "Alexandre Moreira Lopes", LocalDate.of(1977, 1, 1));
        principal.getSistema().addPaciente(paciente26);
        Paciente paciente27 = new Paciente("778.899.001-12", "Beatriz Fogaça Neves", LocalDate.of(1999, 1, 1));
        principal.getSistema().addPaciente(paciente27);
        Paciente paciente28 = new Paciente("889.900.112-23", "Caio Vasconcelos Garcia", LocalDate.of(1992, 1, 1));
        principal.getSistema().addPaciente(paciente28);
        Paciente paciente29 = new Paciente("990.011.223-34", "Débora Matos Brandão", LocalDate.of(1975, 1, 1));
        principal.getSistema().addPaciente(paciente29);
        Paciente paciente30 = new Paciente("001.122.334-45", "Erick Siqueira Reis", LocalDate.of(2002, 1, 1));
        principal.getSistema().addPaciente(paciente30);
        Paciente paciente31 = new Paciente("113.322.441-15", "Fernanda Tavares Justo", LocalDate.of(1989, 1, 1));
        principal.getSistema().addPaciente(paciente31);
        Paciente paciente32 = new Paciente("224.433.552-26", "Guilherme Drummond Morais", LocalDate.of(2010, 1, 1));
        principal.getSistema().addPaciente(paciente32);
        Paciente paciente33 = new Paciente("335.544.663-37", "Helena Beltrão Chaves", LocalDate.of(1961, 1, 1));
        principal.getSistema().addPaciente(paciente33);
        Paciente paciente34 = new Paciente("446.655.774-48", "Igor Bernardes Gusmão", LocalDate.of(1996, 1, 1));
        principal.getSistema().addPaciente(paciente34);
        Paciente paciente35 = new Paciente("557.766.885-59", "Júlia Padrão Queiroz", LocalDate.of(1984, 1, 1));
        principal.getSistema().addPaciente(paciente35);
        Medico medico1 = new Medico("123456", "101.202.303-01", "Dr. Carlos Andrade", LocalDate.of(1980, 1, 1));
        principal.getSistema().addMedico(medico1);
        Medico medico2 = new Medico("234567", "202.303.404-02", "Dra. Beatriz Martins", LocalDate.of(1987, 1, 1));
        principal.getSistema().addMedico(medico2);
        Medico medico3 = new Medico("345678", "303.404.505-03", "Dr. Leonardo Schmidt", LocalDate.of(1973, 1, 1));
        principal.getSistema().addMedico(medico3);
        Medico medico4 = new Medico("456789", "404.505.606-04", "Dra. Juliana Ribeiro", LocalDate.of(1994, 1, 1));
        principal.getSistema().addMedico(medico4);
        Medico medico5 = new Medico("567890", "505.606.707-05", "Dr. Fernando Guimarães", LocalDate.of(1965, 1, 1));
        principal.getSistema().addMedico(medico5);
        Medico medico6 = new Medico("678901", "606.707.808-06", "Dra. Patrícia da Mata", LocalDate.of(1984, 1, 1));
        principal.getSistema().addMedico(medico6);
        Medico medico7 = new Medico("789012", "707.808.909-07", "Dr. Roberto Vasconcelos", LocalDate.of(1986, 1, 1));
        principal.getSistema().addMedico(medico7);
        Medico medico8 = new Medico("890123", "808.909.010-08", "Dra. Vanessa Aragão", LocalDate.of(1996, 1, 1));
        principal.getSistema().addMedico(medico8);
        Medico medico9 = new Medico("901234", "909.010.111-09", "Dr. Gustavo Nogueira", LocalDate.of(1977, 1, 1));
        principal.getSistema().addMedico(medico9);
        Medico medico10 = new Medico("012345", "010.111.212-10", "Dra. Sandra Tavares", LocalDate.of(1970, 1, 1));
        principal.getSistema().addMedico(medico10);
        Medico medico11 = new Medico("112233", "111.212.313-11", "Dr. André Bastos", LocalDate.of(1992, 1, 1));
        principal.getSistema().addMedico(medico11);
        Medico medico12 = new Medico("223344", "212.313.414-12", "Dra. Flávia Drummond", LocalDate.of(1982, 1, 1));
        principal.getSistema().addMedico(medico12);
        Medico medico13 = new Medico("334455", "313.414.515-13", "Dr. Marcelo Peixoto", LocalDate.of(1967, 1, 1));
        principal.getSistema().addMedico(medico13);
        Medico medico14 = new Medico("445566", "414.515.616-14", "Dra. Cláudia Lins", LocalDate.of(1978, 1, 1));
        principal.getSistema().addMedico(medico14);
        Medico medico15 = new Medico("556677", "515.616.717-15", "Dr. Eduardo Macedo", LocalDate.of(1989, 1, 1));
        principal.getSistema().addMedico(medico15);
        Medico medico16 = new Medico("667788", "616.717.818-16", "Dra. Mônica Brandão", LocalDate.of(1974, 1, 1));
        principal.getSistema().addMedico(medico16);
        Medico medico17 = new Medico("778899", "717.818.919-17", "Dr. Diogo Cintra", LocalDate.of(1995, 1, 1));
        principal.getSistema().addMedico(medico17);
        Medico medico18 = new Medico("889900", "818.919.020-18", "Dra. Renata Guedes", LocalDate.of(1981, 1, 1));
        principal.getSistema().addMedico(medico18);
        Medico medico19 = new Medico("990011", "919.020.121-19", "Dr. Tiago Bernardes", LocalDate.of(1993, 1, 1));
        principal.getSistema().addMedico(medico19);
        Medico medico20 = new Medico("001122", "020.121.222-20", "Dra. Aline Sampaio", LocalDate.of(1988, 1, 1));
        principal.getSistema().addMedico(medico20);
        Medico medico21 = new Medico("102030", "121.222.323-21", "Dr. Vinícius Camargo", LocalDate.of(1976, 1, 1));
        principal.getSistema().addMedico(medico21);
        Medico medico22 = new Medico("203040", "222.323.424-22", "Dra. Cíntia Dantas", LocalDate.of(1972, 1, 1));
        principal.getSistema().addMedico(medico22);
        Medico medico23 = new Medico("304050", "323.424.525-23", "Dr. Rogério Esteves", LocalDate.of(1963, 1, 1));
        principal.getSistema().addMedico(medico23);
        Medico medico24 = new Medico("405060", "424.525.626-24", "Dra. Thaís Valente", LocalDate.of(1997, 1, 1));
        principal.getSistema().addMedico(medico24);
        Medico medico25 = new Medico("506070", "525.626.727-25", "Dr. Fábio Goulart", LocalDate.of(1979, 1, 1));
        principal.getSistema().addMedico(medico25);
        Medico medico26 = new Medico("607080", "626.727.828-26", "Dra. Daniela Pires", LocalDate.of(1990, 1, 1));
        principal.getSistema().addMedico(medico26);
        Medico medico27 = new Medico("708090", "727.828.929-27", "Dr. Anderson Frota", LocalDate.of(1975, 1, 1));
        principal.getSistema().addMedico(medico27);
        Medico medico28 = new Medico("809000", "828.929.030-28", "Dra. Paula Quintela", LocalDate.of(1968, 1, 1));
        principal.getSistema().addMedico(medico28);
        Medico medico29 = new Medico("900010", "929.030.131-29", "Dr. Igor Castelo", LocalDate.of(1991, 1, 1));
        principal.getSistema().addMedico(medico29);
        Medico medico30 = new Medico("000102", "030.131.232-30", "Dra. Rafaela Neves", LocalDate.of(1983, 1, 1));
        principal.getSistema().addMedico(medico30);
        Medico medico31 = new Medico("111023", "131.232.333-31", "Dr. Benício Torres", LocalDate.of(1960, 1, 1));
        principal.getSistema().addMedico(medico31);
        Medico medico32 = new Medico("222134", "232.333.434-32", "Dra. Lorena Franco", LocalDate.of(1994, 1, 1));
        principal.getSistema().addMedico(medico32);
        Medico medico33 = new Medico("333245", "333.434.535-33", "Dr. Samuel Ramalho", LocalDate.of(1971, 1, 1));
        principal.getSistema().addMedico(medico33);
        Medico medico34 = new Medico("444356", "434.535.636-34", "Dra. Estela Matos", LocalDate.of(1985, 1, 1));
        principal.getSistema().addMedico(medico34);
        Medico medico35 = new Medico("555467", "535.636.737-35", "Dr. Davi Lucca Mourão", LocalDate.of(1996, 1, 1));
        principal.getSistema().addMedico(medico35);
    }
}

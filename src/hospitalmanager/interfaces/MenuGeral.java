package hospitalmanager.interfaces;

import hospitalmanager.dominio.*;
import hospitalmanager.interfaces.elementos.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

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
        botaoDelPacientes.setForeground(Color.RED);
        JButton botaoDelPacientesEspeciais = new JButton("Deletar Pacientes Especiais");
        botaoDelPacientesEspeciais.setBackground(Color.pink);
        botaoDelPacientesEspeciais.setForeground(Color.RED);
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
        JButton botaoDelEspecialidades = new JButton("Especialidades");
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
        Sistema sistema = principal.getSistema();
        String[] especialidades={"Cardiologia", "Dermatologia", "Ortopedia", "Pediatria", "Neurologia", "Ginecologia", "Clínico Geral"};
        sistema.getEspecialidades().addAll(Arrays.asList(especialidades));

        PlanoDeSaude plano1 = new PlanoDeSaude("PLN001", "Saúde Total Basic", 10.0, 15.0);
        PlanoDeSaude plano2 = new PlanoDeSaude("PLN002", "Vida Premium", 20.0, 25.0);
        PlanoDeSaude plano3 = new PlanoDeSaude("PLN003", "Bem-Estar Gold", 30.0, 35.0);
        PlanoDeSaude plano4 = new PlanoDeSaude("PLN004", "Total Care Plus", 15.0, 20.0);
        PlanoDeSaude plano5 = new PlanoDeSaude("PLN005", "Seguro Viver Essencial", 25.0, 30.0);
        PlanoDeSaude plano6 = new PlanoDeSaude("PLN006", "Medial Silver", 12.0, 18.0);
        PlanoDeSaude plano7 = new PlanoDeSaude("PLN007", "Conforto Familiar", 22.0, 28.0);
        PlanoDeSaude plano8 = new PlanoDeSaude("PLN008", "Vitality Executivo", 35.0, 40.0);
        PlanoDeSaude plano9 = new PlanoDeSaude("PLN009", "Cuidado Pleno", 18.0, 22.0);
        PlanoDeSaude plano10 = new PlanoDeSaude("PLN010", "Plano Master", 40.0, 50.0);
        PlanoDeSaude plano11 = new PlanoDeSaude("PLN011", "Saúde em Dia", 10.0, 15.0);
        PlanoDeSaude plano12 = new PlanoDeSaude("PLN012", "Família Protegida", 20.0, 25.0);
        PlanoDeSaude plano13 = new PlanoDeSaude("PLN013", "Advanced Care", 30.0, 35.0);
        PlanoDeSaude plano14 = new PlanoDeSaude("PLN014", "Light Basic", 15.0, 20.0);
        PlanoDeSaude plano15 = new PlanoDeSaude("PLN015", "Life Standard", 25.0, 30.0);
        PlanoDeSaude plano16 = new PlanoDeSaude("PLN016", "Med Senior", 12.0, 18.0);
        PlanoDeSaude plano17 = new PlanoDeSaude("PLN017", "Global Health", 22.0, 28.0);
        PlanoDeSaude plano18 = new PlanoDeSaude("PLN018", "Infinity Plus", 35.0, 40.0);
        PlanoDeSaude plano19 = new PlanoDeSaude("PLN019", "Cuidado Integral", 18.0, 22.0);
        PlanoDeSaude plano20 = new PlanoDeSaude("PLN020", "Plano Executivo Top", 40.0, 50.0);
        PlanoDeSaude plano21 = new PlanoDeSaude("PLN021", "Essencial Care", 10.0, 15.0);
        PlanoDeSaude plano22 = new PlanoDeSaude("PLN022", "Vida e Saúde", 20.0, 25.0);
        PlanoDeSaude plano23 = new PlanoDeSaude("PLN023", "Bem-Estar Silver", 30.0, 35.0);
        PlanoDeSaude plano24 = new PlanoDeSaude("PLN024", "Total Protection", 15.0, 20.0);
        PlanoDeSaude plano25 = new PlanoDeSaude("PLN025", "Seguro Viver Master", 25.0, 30.0);

        sistema.addPlano(plano1); sistema.addPlano(plano2); sistema.addPlano(plano3); sistema.addPlano(plano4);
        sistema.addPlano(plano5); sistema.addPlano(plano6); sistema.addPlano(plano7); sistema.addPlano(plano8);
        sistema.addPlano(plano9); sistema.addPlano(plano10); sistema.addPlano(plano11); sistema.addPlano(plano12);
        sistema.addPlano(plano13); sistema.addPlano(plano14); sistema.addPlano(plano15); sistema.addPlano(plano16);
        sistema.addPlano(plano17); sistema.addPlano(plano18); sistema.addPlano(plano19); sistema.addPlano(plano20);
        sistema.addPlano(plano21); sistema.addPlano(plano22); sistema.addPlano(plano23); sistema.addPlano(plano24);
        sistema.addPlano(plano25);

        Paciente paciente1 = new Paciente("111.111.111-11", "João da Silva", LocalDate.of(1985, 5, 15));
        Paciente paciente2 = new Paciente("222.222.222-22", "Maria Oliveira", LocalDate.of(1990, 8, 25));
        Paciente paciente3 = new Paciente("333.333.333-33", "Pedro Souza", LocalDate.of(1978, 2, 10));
        Paciente paciente4 = new Paciente("444.444.444-44", "Ana Pereira", LocalDate.of(2001, 11, 30));
        Paciente paciente5 = new Paciente("555.555.555-55", "Carlos Ferreira", LocalDate.of(1965, 7, 22));
        Paciente paciente6 = new Paciente("121.212.121-21", "Mariana Alves", LocalDate.of(1995, 1, 18));
        Paciente paciente7 = new Paciente("343.434.343-43", "Lucas Gomes", LocalDate.of(1988, 6, 3));
        Paciente paciente8 = new Paciente("565.656.565-65", "Beatriz Ribeiro", LocalDate.of(2005, 9, 12));
        Paciente paciente9 = new Paciente("787.878.787-87", "Rafael Martins", LocalDate.of(1972, 4, 28));
        Paciente paciente10 = new Paciente("909.090.909-09", "Júlia Carvalho", LocalDate.of(1999, 12, 5));
        Paciente paciente11 = new Paciente("101.101.101-01", "Gabriel Lima", LocalDate.of(1983, 3, 14));
        Paciente paciente12 = new Paciente("202.202.202-02", "Larissa Almeida", LocalDate.of(1992, 10, 21));
        Paciente paciente13 = new Paciente("303.303.303-03", "Matheus Lopes", LocalDate.of(1975, 8, 1));
        Paciente paciente14 = new Paciente("404.404.404-04", "Isabela Fernandes", LocalDate.of(2003, 5, 19));
        Paciente paciente15 = new Paciente("505.505.505-05", "Thiago Rodrigues", LocalDate.of(1968, 2, 27));
        Paciente paciente16 = new Paciente("606.606.606-06", "Camila Santos", LocalDate.of(1997, 6, 9));
        Paciente paciente17 = new Paciente("707.707.707-07", "Felipe Oliveira", LocalDate.of(1981, 9, 16));
        Paciente paciente18 = new Paciente("808.808.808-08", "Amanda Souza", LocalDate.of(2000, 7, 24));
        Paciente paciente19 = new Paciente("909.909.909-09", "Rodrigo Pereira", LocalDate.of(1970, 1, 31));
        Paciente paciente20 = new Paciente("010.101.010-10", "Vanessa Ferreira", LocalDate.of(1994, 4, 11));
        Paciente paciente21 = new Paciente("112.223.334-45", "Bruno Alves", LocalDate.of(1987, 11, 7));
        Paciente paciente22 = new Paciente("224.335.446-56", "Clara Gomes", LocalDate.of(1991, 3, 23));
        Paciente paciente23 = new Paciente("336.447.558-67", "Vinicius Ribeiro", LocalDate.of(1979, 12, 13));
        Paciente paciente24 = new Paciente("448.559.660-78", "Letícia Martins", LocalDate.of(2002, 8, 29));
        Paciente paciente25 = new Paciente("551.662.773-89", "Gustavo Carvalho", LocalDate.of(1963, 5, 4));

        sistema.addPaciente(paciente1); sistema.addPaciente(paciente2); sistema.addPaciente(paciente3);
        sistema.addPaciente(paciente4); sistema.addPaciente(paciente5); sistema.addPaciente(paciente6);
        sistema.addPaciente(paciente7); sistema.addPaciente(paciente8); sistema.addPaciente(paciente9);
        sistema.addPaciente(paciente10); sistema.addPaciente(paciente11); sistema.addPaciente(paciente12);
        sistema.addPaciente(paciente13); sistema.addPaciente(paciente14); sistema.addPaciente(paciente15);
        sistema.addPaciente(paciente16); sistema.addPaciente(paciente17); sistema.addPaciente(paciente18);
        sistema.addPaciente(paciente19); sistema.addPaciente(paciente20); sistema.addPaciente(paciente21);
        sistema.addPaciente(paciente22); sistema.addPaciente(paciente23); sistema.addPaciente(paciente24);
        sistema.addPaciente(paciente25);

        Medico medico1 = new Medico("CRM001", "123.456.789-01", "Dr. Ricardo Abreu", LocalDate.of(1975, 3, 20), "Cardiologia", 350.0);
        Medico medico2 = new Medico("CRM002", "234.567.890-12", "Dra. Fernanda Costa", LocalDate.of(1980, 6, 25), "Dermatologia", 300.0);
        Medico medico3 = new Medico("CRM003", "345.678.901-23", "Dr. André Santos", LocalDate.of(1972, 1, 10), "Ortopedia", 320.0);
        Medico medico4 = new Medico("CRM004", "456.789.012-34", "Dra. Carolina Lima", LocalDate.of(1985, 9, 5), "Pediatria", 280.0);
        Medico medico5 = new Medico("CRM005", "567.890.123-45", "Dr. Roberto Dias", LocalDate.of(1968, 12, 15), "Neurologia", 400.0);
        Medico medico6 = new Medico("CRM006", "678.901.234-56", "Dra. Patrícia Melo", LocalDate.of(1979, 4, 30), "Ginecologia", 290.0);
        Medico medico7 = new Medico("CRM007", "789.012.345-67", "Dr. Marcos Rocha", LocalDate.of(1982, 7, 18), "Clínico Geral", 250.0);
        Medico medico8 = new Medico("CRM008", "890.123.456-78", "Dra. Sofia Andrade", LocalDate.of(1988, 10, 22), "Cardiologia", 360.0);
        Medico medico9 = new Medico("CRM009", "901.234.567-89", "Dr. Leonardo Barros", LocalDate.of(1970, 2, 8), "Dermatologia", 310.0);
        Medico medico10 = new Medico("CRM010", "012.345.678-90", "Dra. Gabriela Nunes", LocalDate.of(1990, 5, 12), "Ortopedia", 330.0);
        Medico medico11 = new Medico("CRM011", "112.233.445-56", "Dr. Fábio Azevedo", LocalDate.of(1976, 8, 28), "Pediatria", 285.0);
        Medico medico12 = new Medico("CRM012", "223.344.556-67", "Dra. Renata Farias", LocalDate.of(1983, 11, 3), "Neurologia", 410.0);
        Medico medico13 = new Medico("CRM013", "334.455.667-78", "Dr. César Pires", LocalDate.of(1965, 6, 17), "Ginecologia", 295.0);
        Medico medico14 = new Medico("CRM014", "445.566.778-89", "Dra. Juliana Teixeira", LocalDate.of(1989, 1, 24), "Clínico Geral", 255.0);
        Medico medico15 = new Medico("CRM015", "556.677.889-90", "Dr. Otávio Moreira", LocalDate.of(1974, 9, 9), "Cardiologia", 355.0);
        Medico medico16 = new Medico("CRM016", "667.788.990-01", "Dra. Vanessa Jorge", LocalDate.of(1981, 3, 19), "Dermatologia", 305.0);
        Medico medico17 = new Medico("CRM017", "778.899.001-12", "Dr. Igor Vasconcelos", LocalDate.of(1978, 12, 1), "Ortopedia", 325.0);
        Medico medico18 = new Medico("CRM018", "889.900.112-23", "Dra. Luana Rezende", LocalDate.of(1986, 7, 27), "Pediatria", 275.0);
        Medico medico19 = new Medico("CRM019", "990.011.223-34", "Dr. Samuel Dantas", LocalDate.of(1969, 5, 14), "Neurologia", 405.0);
        Medico medico20 = new Medico("CRM020", "001.122.334-45", "Dra. Bruna Guimarães", LocalDate.of(1992, 2, 26), "Ginecologia", 300.0);
        Medico medico21 = new Medico("CRM021", "113.322.441-15", "Dr. Heitor Neves", LocalDate.of(1977, 10, 8), "Clínico Geral", 260.0);
        Medico medico22 = new Medico("CRM022", "221.133.445-54", "Dra. Elisa Pinto", LocalDate.of(1984, 8, 13), "Cardiologia", 365.0);
        Medico medico23 = new Medico("CRM023", "331.122.554-45", "Dr. Benjamin Silveira", LocalDate.of(1971, 4, 6), "Dermatologia", 315.0);
        Medico medico24 = new Medico("CRM024", "442.233.551-15", "Dra. Alícia Tavares", LocalDate.of(1991, 1, 11), "Ortopedia", 335.0);
        Medico medico25 = new Medico("CRM025", "553.344.112-21", "Dr. Caio Viana", LocalDate.of(1987, 6, 29), "Pediatria", 290.0);

        sistema.addMedico(medico1); sistema.addMedico(medico2); sistema.addMedico(medico3); sistema.addMedico(medico4);
        sistema.addMedico(medico5); sistema.addMedico(medico6); sistema.addMedico(medico7); sistema.addMedico(medico8);
        sistema.addMedico(medico9); sistema.addMedico(medico10); sistema.addMedico(medico11); sistema.addMedico(medico12);
        sistema.addMedico(medico13); sistema.addMedico(medico14); sistema.addMedico(medico15); sistema.addMedico(medico16);
        sistema.addMedico(medico17); sistema.addMedico(medico18); sistema.addMedico(medico19); sistema.addMedico(medico20);
        sistema.addMedico(medico21); sistema.addMedico(medico22); sistema.addMedico(medico23); sistema.addMedico(medico24);
        sistema.addMedico(medico25);

        PacienteEspecial pe1 = new PacienteEspecial("191.817.161-51", "Laura Mendes", LocalDate.of(1992, 4, 12), plano2);
        PacienteEspecial pe2 = new PacienteEspecial("282.726.252-42", "Fernando Almeida", LocalDate.of(1987, 9, 21), plano3);
        PacienteEspecial pe3 = new PacienteEspecial("373.635.343-33", "Sandra Costa", LocalDate.of(1960, 3, 7), plano1);
        PacienteEspecial pe4 = new PacienteEspecial("464.544.434-24", "Ricardo Rocha", LocalDate.of(2000, 1, 25), plano5);
        PacienteEspecial pe5 = new PacienteEspecial("555.453.525-15", "Tatiana Dias", LocalDate.of(1975, 10, 18), plano4);
        PacienteEspecial pe6 = new PacienteEspecial("646.362.616-96", "Sérgio Barros", LocalDate.of(1998, 7, 30), plano6);
        PacienteEspecial pe7 = new PacienteEspecial("737.271.797-87", "Débora Nunes", LocalDate.of(1981, 12, 2), plano8);
        PacienteEspecial pe8 = new PacienteEspecial("828.189.888-78", "Marcelo Azevedo", LocalDate.of(1969, 5, 9), plano7);
        PacienteEspecial pe9 = new PacienteEspecial("919.998.979-69", "Cristina Farias", LocalDate.of(1993, 2, 14), plano10);
        PacienteEspecial pe10 = new PacienteEspecial("098.897.060-50", "Alexandre Pires", LocalDate.of(1973, 8, 22), plano9);
        PacienteEspecial pe11 = new PacienteEspecial("111.222.333-44", "Eliane Teixeira", LocalDate.of(1984, 6, 1), plano11);
        PacienteEspecial pe12 = new PacienteEspecial("222.333.444-55", "Rogério Moreira", LocalDate.of(1996, 11, 19), plano13);
        PacienteEspecial pe13 = new PacienteEspecial("333.444.555-66", "Valéria Jorge", LocalDate.of(1963, 7, 27), plano12);
        PacienteEspecial pe14 = new PacienteEspecial("444.555.666-77", "Diego Vasconcelos", LocalDate.of(2004, 3, 15), plano15);
        PacienteEspecial pe15 = new PacienteEspecial("555.666.777-88", "Silvia Rezende", LocalDate.of(1971, 9, 3), plano14);
        PacienteEspecial pe16 = new PacienteEspecial("666.777.888-99", "Daniel Dantas", LocalDate.of(1989, 4, 28), plano17);
        PacienteEspecial pe17 = new PacienteEspecial("777.888.999-00", "Paula Guimarães", LocalDate.of(1982, 1, 10), plano16);
        PacienteEspecial pe18 = new PacienteEspecial("888.999.000-11", "Eduardo Neves", LocalDate.of(1995, 8, 5), plano19);
        PacienteEspecial pe19 = new PacienteEspecial("999.000.111-22", "Andreza Pinto", LocalDate.of(1967, 2, 20), plano18);
        PacienteEspecial pe20 = new PacienteEspecial("000.111.222-33", "Leandro Silveira", LocalDate.of(1978, 10, 12), plano20);
        PacienteEspecial pe21 = new PacienteEspecial("123.123.123-12", "Ester Tavares", LocalDate.of(1999, 5, 24), plano21);
        PacienteEspecial pe22 = new PacienteEspecial("234.234.234-23", "Márcio Viana", LocalDate.of(1980, 7, 16), plano23);
        PacienteEspecial pe23 = new PacienteEspecial("345.345.345-34", "Heloísa Mendes", LocalDate.of(1962, 11, 8), plano22);
        PacienteEspecial pe24 = new PacienteEspecial("456.456.456-45", "Davi Almeida", LocalDate.of(2001, 6, 26), plano25);
        PacienteEspecial pe25 = new PacienteEspecial("567.567.567-56", "Rebeca Costa", LocalDate.of(1974, 12, 31), plano24);

        sistema.addPacienteEspecial(pe1); sistema.addPacienteEspecial(pe2); sistema.addPacienteEspecial(pe3);
        sistema.addPacienteEspecial(pe4); sistema.addPacienteEspecial(pe5); sistema.addPacienteEspecial(pe6);
        sistema.addPacienteEspecial(pe7); sistema.addPacienteEspecial(pe8); sistema.addPacienteEspecial(pe9);
        sistema.addPacienteEspecial(pe10); sistema.addPacienteEspecial(pe11); sistema.addPacienteEspecial(pe12);
        sistema.addPacienteEspecial(pe13); sistema.addPacienteEspecial(pe14); sistema.addPacienteEspecial(pe15);
        sistema.addPacienteEspecial(pe16); sistema.addPacienteEspecial(pe17); sistema.addPacienteEspecial(pe18);
        sistema.addPacienteEspecial(pe19); sistema.addPacienteEspecial(pe20); sistema.addPacienteEspecial(pe21);
        sistema.addPacienteEspecial(pe22); sistema.addPacienteEspecial(pe23); sistema.addPacienteEspecial(pe24);
        sistema.addPacienteEspecial(pe25);

        Consulta con1 = new Consulta(paciente1, medico1, "Agendada", "Sala 101", LocalDateTime.of(2025, 10, 20, 10, 0), "Check-up anual");
        Consulta con2 = new Consulta(pe2, medico2, "Agendada", "Sala 202", LocalDateTime.of(2025, 10, 21, 14, 30), "Manchas na pele");
        Consulta con3 = new Consulta(paciente3, medico3, "Concluída", "Sala 301", LocalDateTime.of(2025, 9, 5, 9, 0), "Dor no joelho");
        Consulta con4 = new Consulta(pe4, medico4, "Agendada", "Sala 105", LocalDateTime.of(2025, 11, 10, 11, 0), "Acompanhamento pediátrico");
        Consulta con5 = new Consulta(paciente5, medico5, "Agendada", "Sala 401", LocalDateTime.of(2025, 11, 15, 16, 0), "Enxaqueca crônica");
        Consulta con6 = new Consulta(pe6, medico7, "Cancelada", "Sala 102", LocalDateTime.of(2025, 10, 1, 8, 30), "Febre e mal-estar");
        Consulta con7 = new Consulta(paciente7, medico8, "Agendada", "Sala 101", LocalDateTime.of(2025, 12, 1, 15, 0), "Avaliação cardíaca");
        Consulta con8 = new Consulta(pe8, medico9, "Concluída", "Sala 203", LocalDateTime.of(2025, 8, 20, 13, 30), "Remoção de sinal");
        Consulta con9 = new Consulta(paciente9, medico10, "Agendada", "Sala 302", LocalDateTime.of(2025, 10, 25, 10, 30), "Dor no ombro");
        Consulta con10 = new Consulta(pe10, medico11, "Agendada", "Sala 106", LocalDateTime.of(2025, 11, 12, 9, 30), "Vacinação infantil");
        Consulta con11 = new Consulta(paciente11, medico12, "Agendada", "Sala 402", LocalDateTime.of(2025, 11, 22, 17, 0), "Dores de cabeça recorrentes");
        Consulta con12 = new Consulta(pe12, medico6, "Concluída", "Sala 205", LocalDateTime.of(2025, 9, 15, 11, 30), "Rotina ginecológica");
        Consulta con13 = new Consulta(paciente13, medico14, "Agendada", "Sala 103", LocalDateTime.of(2025, 10, 30, 14, 0), "Exames de rotina");
        Consulta con14 = new Consulta(pe14, medico1, "Cancelada", "Sala 101", LocalDateTime.of(2025, 10, 10, 11, 0), "Palpitações");
        Consulta con15 = new Consulta(paciente15, medico2, "Agendada", "Sala 202", LocalDateTime.of(2025, 11, 5, 15, 30), "Verificação de alergia");
        Consulta con16 = new Consulta(pe16, medico3, "Agendada", "Sala 301", LocalDateTime.of(2025, 12, 5, 8, 0), "Torção no tornozelo");
        Consulta con17 = new Consulta(paciente17, medico4, "Concluída", "Sala 105", LocalDateTime.of(2025, 8, 1, 10, 0), "Consulta de desenvolvimento");
        Consulta con18 = new Consulta(pe18, medico5, "Agendada", "Sala 401", LocalDateTime.of(2025, 11, 28, 14, 30), "Acompanhamento neurológico");
        Consulta con19 = new Consulta(paciente19, medico7, "Agendada", "Sala 102", LocalDateTime.of(2025, 11, 3, 9, 0), "Pressão alta");
        Consulta con20 = new Consulta(pe20, medico8, "Agendada", "Sala 101", LocalDateTime.of(2025, 12, 10, 16, 30), "Dor no peito");
        Consulta con21 = new Consulta(paciente21, medico9, "Concluída", "Sala 203", LocalDateTime.of(2025, 9, 25, 14, 0), "Acne");
        Consulta con22 = new Consulta(pe22, medico10, "Agendada", "Sala 302", LocalDateTime.of(2025, 11, 18, 11, 30), "Fisioterapia");
        Consulta con23 = new Consulta(paciente23, medico11, "Cancelada", "Sala 106", LocalDateTime.of(2025, 10, 5, 10, 30), "Acompanhamento");
        Consulta con24 = new Consulta(pe24, medico12, "Agendada", "Sala 402", LocalDateTime.of(2025, 12, 15, 15, 0), "Tontura");
        Consulta con25 = new Consulta(paciente25, medico6, "Agendada", "Sala 205", LocalDateTime.of(2025, 11, 20, 8, 30), "Exame preventivo");

        sistema.addConsulta(con1); sistema.addConsulta(con2); sistema.addConsulta(con3); sistema.addConsulta(con4);
        sistema.addConsulta(con5); sistema.addConsulta(con6); sistema.addConsulta(con7); sistema.addConsulta(con8);
        sistema.addConsulta(con9); sistema.addConsulta(con10); sistema.addConsulta(con11); sistema.addConsulta(con12);
        sistema.addConsulta(con13); sistema.addConsulta(con14); sistema.addConsulta(con15); sistema.addConsulta(con16);
        sistema.addConsulta(con17); sistema.addConsulta(con18); sistema.addConsulta(con19); sistema.addConsulta(con20);
        sistema.addConsulta(con21); sistema.addConsulta(con22); sistema.addConsulta(con23); sistema.addConsulta(con24);
        sistema.addConsulta(con25);

        Internacao in1 = new Internacao(paciente3, medico3, "Leito 201A", LocalDate.of(2025, 9, 6), LocalDate.of(2025, 9, 10), "Pós-operatório de joelho");
        Internacao in2 = new Internacao(pe1, medico1, "Leito 305B", LocalDate.of(2025, 10, 1), "Observação por arritmia");
        Internacao in3 = new Internacao(paciente5, medico5, "Leito 410C", LocalDate.of(2025, 7, 15), LocalDate.of(2025, 7, 20), "Investigação de AVC");
        Internacao in4 = new Internacao(pe7, medico7, "Leito 112A", LocalDate.of(2025, 8, 5), "Pneumonia");
        Internacao in5 = new Internacao(paciente10, medico4, "Leito 501P", LocalDate.of(2025, 6, 20), LocalDate.of(2025, 6, 25), "Asma infantil severa");
        Internacao in6 = new Internacao(pe13, medico6, "Leito 215B", LocalDate.of(2025, 9, 1), "Recuperação de histerectomia");
        Internacao in7 = new Internacao(paciente14, medico10, "Leito 202A", LocalDate.of(2025, 10, 5), "Fratura de braço");
        Internacao in8 = new Internacao(pe15, medico2, "Leito 310C", LocalDate.of(2025, 5, 10), LocalDate.of(2025, 5, 15), "Reação alérgica grave");
        Internacao in9 = new Internacao(paciente16, medico8, "Leito 306B", LocalDate.of(2025, 8, 12), "Monitoramento cardíaco");
        Internacao in10 = new Internacao(pe17, medico5, "Leito 411C", LocalDate.of(2025, 9, 22), "Crise convulsiva");
        Internacao in11 = new Internacao(paciente2, medico7, "Leito 113A", LocalDate.of(2025, 10, 8), "Dengue hemorrágica");
        Internacao in12 = new Internacao(pe3, medico1, "Leito 307B", LocalDate.of(2025, 7, 30), LocalDate.of(2025, 8, 5), "Angioplastia");
        Internacao in13 = new Internacao(paciente4, medico11, "Leito 502P", LocalDate.of(2025, 9, 18), "Bronquiolite");
        Internacao in14 = new Internacao(pe5, medico3, "Leito 203A", LocalDate.of(2025, 10, 3), "Cirurgia de quadril");
        Internacao in15 = new Internacao(paciente6, medico9, "Leito 311C", LocalDate.of(2025, 6, 5), LocalDate.of(2025, 6, 8), "Infecção de pele");
        Internacao in16 = new Internacao(pe8, medico12, "Leito 412C", LocalDate.of(2025, 8, 25), "Observação pós-trauma craniano");
        Internacao in17 = new Internacao(paciente8, medico13, "Leito 216B", LocalDate.of(2025, 9, 28), "Complicações de parto");
        Internacao in18 = new Internacao(pe9, medico14, "Leito 114A", LocalDate.of(2025, 10, 6), "Gastroenterite aguda");
        Internacao in19 = new Internacao(paciente12, medico1, "Leito 308B", LocalDate.of(2025, 7, 1), LocalDate.of(2025, 7, 4), "Cateterismo");
        Internacao in20 = new Internacao(pe11, medico2, "Leito 312C", LocalDate.of(2025, 9, 11), "Queimaduras de segundo grau");
        Internacao in21 = new Internacao(paciente18, medico3, "Leito 204A", LocalDate.of(2025, 10, 2), "Lesão de ligamento");
        Internacao in22 = new Internacao(pe19, medico4, "Leito 503P", LocalDate.of(2025, 8, 8), LocalDate.of(2025, 8, 14), "Infecção respiratória");
        Internacao in23 = new Internacao(paciente20, medico5, "Leito 413C", LocalDate.of(2025, 9, 30), "Investigação de tumor cerebral");
        Internacao in24 = new Internacao(pe21, medico7, "Leito 115A", LocalDate.of(2025, 7, 22), "Desidratação severa");
        Internacao in25 = new Internacao(paciente24, medico8, "Leito 309B", LocalDate.of(2025, 10, 7), "Crise hipertensiva");

        sistema.addInternacao(in1); sistema.addInternacao(in2); sistema.addInternacao(in3); sistema.addInternacao(in4);
        sistema.addInternacao(in5); sistema.addInternacao(in6); sistema.addInternacao(in7); sistema.addInternacao(in8);
        sistema.addInternacao(in9); sistema.addInternacao(in10); sistema.addInternacao(in11); sistema.addInternacao(in12);
        sistema.addInternacao(in13); sistema.addInternacao(in14); sistema.addInternacao(in15); sistema.addInternacao(in16);
        sistema.addInternacao(in17); sistema.addInternacao(in18); sistema.addInternacao(in19); sistema.addInternacao(in20);
        sistema.addInternacao(in21); sistema.addInternacao(in22); sistema.addInternacao(in23); sistema.addInternacao(in24);
        sistema.addInternacao(in25);

    }
}

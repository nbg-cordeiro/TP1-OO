package hospitalmanager.persistencia;

import hospitalmanager.dominio.*;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.*;
import java.util.*;

import static java.nio.file.Files.deleteIfExists;

public class RegistroCSV {
    private static final String pasta = "src/hospitalmanager/dados/";
    private static String caminho;

    public static void escreverEspecialidades(List<String> especialidades) {
        caminho = pasta + "Especialidades.csv";
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(caminho), StandardCharsets.UTF_8)) {
            for (String espe : especialidades) {
                writer.write(espe);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever em " + caminho + ": " + e.getMessage());
        }
    }

    public static void escreverPlanos(List<PlanoDeSaude> planos) {
        caminho = pasta + "PlanosDeSaude.csv";
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(caminho), StandardCharsets.UTF_8)) {
            for (PlanoDeSaude plan : planos) {
                writer.write(plan.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever em " + caminho + ": " + e.getMessage());
        }
    }

    public static void escreverPacientes(List<Paciente> pacientes) {
        caminho = pasta + "Pacientes.csv";
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(caminho), StandardCharsets.UTF_8)) {
            for (Paciente paci : pacientes) {
                writer.write(paci.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever em " + caminho + ": " + e.getMessage());
        }
    }

    public static void escreverPacientesEspeciais(List<PacienteEspecial> pacientesEspeciais) {
        caminho = pasta + "PacientesEspeciais.csv";
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(caminho), StandardCharsets.UTF_8)) {
            for (PacienteEspecial paci : pacientesEspeciais) {
                writer.write(paci.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever em " + caminho + ": " + e.getMessage());
        }
    }

    public static void escreverMedicos(List<Medico> medicos) {
        caminho = pasta + "Medicos.csv";
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(caminho), StandardCharsets.UTF_8)) {
            for (Medico medi : medicos) {
                writer.write(medi.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever em " + caminho + ": " + e.getMessage());
        }
    }

    public static void escreverConsultas(List<Consulta> consultas) {
        caminho = pasta + "Consultas.csv";
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(caminho), StandardCharsets.UTF_8)) {
            for (Consulta cons : consultas) {
                writer.write(cons.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever em " + caminho + ": " + e.getMessage());
        }
    }

    public static void escreverInternacoes(List<Internacao> internacoes) {
        caminho = pasta + "Internacoes.csv";
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(caminho), StandardCharsets.UTF_8)) {
            for (Internacao Inte : internacoes) {
                writer.write(Inte.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever em " + caminho + ": " + e.getMessage());
        }
    }

    public static List<String> lerEspecialidades() {
        caminho = pasta + "Especialidades.csv";
        Path path = Path.of(caminho);
        List<String> especialidades = new ArrayList<>();

        if (!Files.exists(path)) {
            System.out.println("Registros não encontrados, inicializando especialidades vazias: " + caminho);
            return especialidades;
        }
        try (BufferedReader leitor = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                especialidades.add(linha);
            }
            System.out.println("Especialidades lidas com sucesso!");
        } catch (IOException e) {

            System.err.println("Erro ao ler Especialidades: " + e.getMessage());
        }
        return especialidades;
    }

    public static List<PlanoDeSaude> lerPlanosDeSaude() {
        caminho = pasta + "PlanosDeSaude.csv";
        Path path = Path.of(caminho);
        List<PlanoDeSaude> planos = new ArrayList<>();
        if (!Files.exists(path)) {
            System.out.println("Registros não encontrados, inicializando Planos de Saúde vazios: " + caminho);
            return planos;
        }
        try (BufferedReader leitor = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] colunas = linha.split(",");
                String codigo = colunas[0].trim();
                String nome = colunas[1].trim();
                Double desConsultas = Double.parseDouble(colunas[2].trim());
                Double desInternacoes = Double.parseDouble(colunas[3].trim());
                PlanoDeSaude plano = new PlanoDeSaude(codigo, nome, desConsultas, desInternacoes);
                planos.add(plano);
            }
            System.out.println("Planos de Saúde lidos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao ler Planos de Saúde: " + e.getMessage());
        }
        return planos;
    }

    public static List<Paciente> lerPacientes() {
        caminho = pasta + "Pacientes.csv";
        Path path = Path.of(caminho);
        List<Paciente> pacientes = new ArrayList<>();

        if (!Files.exists(path)) {
            System.out.println("Registros não encontrados, inicializando Pacientes vazios: " + caminho);
            return pacientes;
        }
        try (BufferedReader leitor = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] colunas = linha.split(",");
                String cpf = colunas[0].trim();
                String nome = colunas[1].trim();
                LocalDate nascimento = LocalDate.parse(colunas[2].trim());
                Paciente paciente = new Paciente(cpf, nome, nascimento);
                pacientes.add(paciente);
            }
            System.out.println("Pacientes lidos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao ler Pacientes: " + e.getMessage());
        }
        return pacientes;
    }

    public static List<PacienteEspecial> lerPacientesEspeciais(Sistema sistema) {
        caminho = pasta + "PacientesEspeciais.csv";
        Path path = Path.of(caminho);
        List<PacienteEspecial> pacientesEspeciais = new ArrayList<>();

        if (!Files.exists(path)) {
            System.out.println("Registros não encontrados, inicializando Pacientes Especiais vazios: " + caminho);
            return pacientesEspeciais;
        }
        try (BufferedReader leitor = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] colunas = linha.split(",");
                String cpf = colunas[0].trim();
                String nome = colunas[1].trim();
                LocalDate nascimento = LocalDate.parse(colunas[2].trim());
                String codigo = colunas[3].trim();
                PacienteEspecial pacienteEspecial = new PacienteEspecial(cpf, nome, nascimento,acharPlano(codigo,sistema.getPlanos()));
                pacientesEspeciais.add(pacienteEspecial);
            }
            System.out.println("Pacientes lidos com sucesso!");
        } catch (IOException e){
            System.err.println("Erro ao ler Pacientes: " + e.getMessage());
        }
        return pacientesEspeciais;
    }

    public static List<Medico> lerMedicos() {
        caminho = pasta + "Medicos.csv";
        Path path = Path.of(caminho);
        List<Medico> medicos = new ArrayList<>();

        if (!Files.exists(path)) {
            System.out.println("Registros não encontrados, inicializando Medicos vazios: " + caminho);

            return medicos;
        }

        try (BufferedReader leitor = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] colunas = linha.split(",");
                String crm = colunas[0].trim();
                String cpf = colunas[1].trim();
                String nome = colunas[2].trim();
                LocalDate nascimento = LocalDate.parse(colunas[3].trim());
                String especialidade = colunas[4].trim();
                double custoConsulta = Double.parseDouble(colunas[5].trim());
                Medico medico = new Medico(crm, cpf, nome, nascimento, especialidade,custoConsulta);
                medicos.add(medico);
            }
            System.out.println("Medicos lidos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao ler Medicos: " + e.getMessage());
        }
        return medicos;
    }

    public static List<Consulta> lerConsultas(Sistema sistema) {
        caminho = pasta + "Consultas.csv";
        Path path = Path.of(caminho);
        List<Consulta> consultas = new ArrayList<>();

        if (!Files.exists(path)) {
            System.out.println("Registros não encontrados, inicializando Consultas vazias: " + caminho);
            return consultas;
        }

        try (BufferedReader leitor = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] colunas = linha.split(",");
                String cpf = colunas[0].trim();
                String crm = colunas[1].trim();
                String status = colunas[2].trim();
                LocalDateTime dataEHora = LocalDateTime.parse(colunas[3].trim());
                String local = colunas[4].trim();
                String motivo = colunas[5].trim();
                if(acharPaciente(cpf,sistema.getPacientes())==null)
                {
                    PacienteEspecial pacienteEspecial = acharPacienteEspecial(cpf,sistema.getPacientesEspeciais());
                    Consulta consulta = new Consulta(pacienteEspecial,acharMedico(crm, sistema.getMedicos()),status, local, dataEHora, motivo);
                    consultas.add(consulta);
                }
                else{
                    Paciente paciente = acharPaciente(cpf,sistema.getPacientes());
                    assert paciente != null;
                    Consulta consulta = new Consulta(paciente, acharMedico(crm, sistema.getMedicos()), "Agendada", local, dataEHora, motivo);
                    consultas.add(consulta);
                }
            }
            System.out.println("Consultas lidos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao ler Consultas: " + e.getMessage());
        }
        return consultas;
    }

    public static List<Internacao> lerInternacoes(Sistema sistema) {
        caminho = pasta + "Internacoes.csv";
        Path path = Path.of(caminho);
        List<Internacao> internacoes = new ArrayList<>();

        if (!Files.exists(path)) {
            System.out.println("Registros não encontrados, inicializando Internações vazias: " + caminho);
            return internacoes;
        }

        try (BufferedReader leitor = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] colunas = linha.split(",");
                String cpf = colunas[0].trim();
                String crm = colunas[1].trim();
                String leito = colunas[2].trim();
                LocalDate dataCheckIn = LocalDate.parse(colunas[3].trim());
                LocalDate dataCheckOut;
                if(colunas[4].trim().equals("Não"))
                {
                     dataCheckOut = null;
                }
                else{
                    dataCheckOut = LocalDate.parse(colunas[4].trim());
                }
                String observacoes =  colunas[5].trim();
                if(acharPaciente(cpf,sistema.getPacientes())==null)
                {
                    Internacao internacao = new Internacao(acharPacienteEspecial(cpf, sistema.getPacientesEspeciais()),acharMedico(crm,sistema.getMedicos()), leito, dataCheckIn, dataCheckOut,observacoes);
                    internacoes.add(internacao);
                }
                else{
                    Paciente paciente = acharPaciente(cpf,sistema.getPacientes());
                    assert paciente != null;
                    Internacao internacao = new Internacao(Objects.requireNonNull(acharPaciente(cpf, sistema.getPacientes())),acharMedico(crm,sistema.getMedicos()), leito, dataCheckIn, dataCheckOut,observacoes);
                    internacoes.add(internacao);
                }
            }
            System.out.println("Internações lidos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao ler Internações: " + e.getMessage());
        }
        return internacoes;
    }

    public static void deletarPlanos(Sistema sistema) throws IOException {
        int escolha = JOptionPane.showConfirmDialog(null, "Você está prestes a deletar TODOS os registros de Planos de Saúde.\n Deseja Continuar?", "Aviso",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (escolha == JOptionPane.OK_OPTION) {
            deleteIfExists(Path.of("src/hospitalmanager/dados/PlanosDeSaude.csv"));
            sistema.getPlanos().clear();
            System.err.println("Planos de Saúde Deletados!");
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada.\n Nenhum registro foi deletado!");
        }
    }

    public static void deletarEspecialidades(Sistema sistema) throws IOException {
        int escolha = JOptionPane.showConfirmDialog(null, "Você está prestes a deletar TODOS os registros de Especialidades.\n Deseja Continuar?", "Aviso",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (escolha == JOptionPane.OK_OPTION) {
            deleteIfExists(Path.of("src/hospitalmanager/dados/Especialidades.csv"));
            sistema.getEspecialidades().clear();
            System.err.println("Especialidades Deletadas!");
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada.\n Nenhum registro foi deletado!");
        }
    }

    public static void deletarPacientes(Sistema sistema) throws IOException {
        int escolha = JOptionPane.showConfirmDialog(null, "Você está prestes a deletar TODOS os registros de Pacientes.\n Deseja Continuar?", "Aviso",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (escolha == JOptionPane.OK_OPTION) {
            deleteIfExists(Path.of("src/hospitalmanager/dados/Pacientes.csv"));
            sistema.getPacientes().clear();
            System.err.println("Pacientes Deletados!");
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada.\n Nenhum registro foi deletado!");
        }
    }

    public static void deletarPacientesEspeciais(Sistema sistema) throws IOException {
        int escolha = JOptionPane.showConfirmDialog(null, "Você está prestes a deletar TODOS os registros de Pacientes Especiais.\n Deseja Continuar?", "Aviso",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (escolha == JOptionPane.OK_OPTION) {
            deleteIfExists(Path.of("src/hospitalmanager/dados/PacientesEspeciais.csv"));
            sistema.getPacientesEspeciais().clear();
            System.err.println("Pacientes Especiais Deletados!");
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada.\n Nenhum registro foi deletado!");
        }
    }

    public static void deletarMedicos(Sistema sistema) throws IOException {
        int escolha = JOptionPane.showConfirmDialog(null, "Você está prestes a deletar TODOS os registros de Medicos.\n Deseja Continuar?", "Aviso",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (escolha == JOptionPane.OK_OPTION) {
            deleteIfExists(Path.of("src/hospitalmanager/dados/Medicos.csv"));
            sistema.getMedicos().clear();
            System.err.println("Medicos Deletados!");
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada.\n Nenhum registro foi deletado!");
        }
    }

    public static void deletarInternacoes(Sistema sistema) throws IOException {
        int escolha = JOptionPane.showConfirmDialog(null, "Você está prestes a deletar TODOS os registros de Internações.\n Deseja Continuar?", "Aviso",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (escolha == JOptionPane.OK_OPTION) {
            deleteIfExists(Path.of("src/hospitalmanager/dados/Internacoes.csv"));
            sistema.getInternacoes().clear();
            System.err.println("Internações Deletadas!");
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada.\n Nenhum registro foi deletado!");
        }
    }

    public static void deletarConsultas(Sistema sistema) throws IOException {
        int escolha = JOptionPane.showConfirmDialog(null, "Você está prestes a deletar TODOS os registros de Consultas.\n Deseja Continuar?", "Aviso",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (escolha == JOptionPane.OK_OPTION) {
            deleteIfExists(Path.of("src/hospitalmanager/dados/Consultas.csv"));
            sistema.getConsultas().clear();
            System.err.println("Consultas Deletadas!");
        } else {
            JOptionPane.showMessageDialog(null, "Operação cancelada.\n Nenhum registro foi deletado!");
        }
    }

    public static Paciente acharPaciente(String cpf, List<Paciente> pacientes) {
        for (Paciente p : pacientes) {
            if (p.getCpf().equals(cpf)) {
                return p;
            }
        }
        return null;
    }
    public static PacienteEspecial acharPacienteEspecial(String cpf, List<PacienteEspecial> pacientesEspeciais) {
        for(PacienteEspecial p : pacientesEspeciais) {
            if(p.getCpf().equals(cpf)) {
                return p;
            }
        }
        return new PacienteEspecial(null,null,null,null);
    }
    public static PlanoDeSaude acharPlano(String codigo, List<PlanoDeSaude> planos) {
        for (PlanoDeSaude p : planos) {
            if (p.getCodigo().equals(codigo)){
                return p;
            }
        }
        return new PlanoDeSaude(null, null, null,null);
    }
    public static Medico acharMedico(String crm, List<Medico> medicos){
        for (Medico m : medicos) {
            if (m.getCrm().equals(crm)) {
                return m;
            }
        }
        return new Medico(null, null, null, null, null,0d);
    }
}
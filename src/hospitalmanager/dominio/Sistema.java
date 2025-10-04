package hospitalmanager.dominio;

import hospitalmanager.persistencia.RegistroConsulta;
import hospitalmanager.persistencia.RegistroMedico;
import hospitalmanager.persistencia.RegistroPaciente;

import java.util.*;

public class Sistema {
    private static List<Paciente> pacientes =  new ArrayList<>();
    private static List<Medico> medicos = new ArrayList<>();
    private static List<Consulta> consultas= new ArrayList<>();

    public Sistema()
    {
        pacientes = RegistroPaciente.ler();
        medicos = RegistroMedico.ler();
        consultas = RegistroConsulta.ler();
    }

    public static void salvarTudo()
    {
        try{
            RegistroMedico.escrever(medicos);
            RegistroPaciente.escrever(pacientes);
            RegistroConsulta.escrever(consultas);
            System.out.println("Dados salvos com sucesso!");
        }catch(Exception e){
            System.out.println("Erro no salvamento"+e.getMessage());
        }
    }
    public List<Consulta> getConsultas() {
        return consultas;
    }
    public List<Paciente> getPacientes(){
        return pacientes;
    }
    public List<Medico> getMedicos() {
        return medicos;
    }
    public void addMedico(Medico medico)
    {
        if(crmJaExiste(medico.getCrm()))
        {
            System.out.println("Já existe um médico cadastrado com este CRM!");
        }
        else {
            medicos.add(medico);
            System.out.println("Medico cadastrado com sucesso!");
        }
    }
    public void addConsulta(Consulta consulta)
    {
        consultas.add(consulta);
    }
    public void addPaciente(Paciente paciente)
    {
        if(cpfJaExiste(paciente.getCpf()))
        {
            System.out.println("Cpf já existe!");
            throw new RuntimeException("Já existe um paciente cadastrado com este CPF!");
        }
        else{
            pacientes.add(paciente);
            System.out.println("Paciente cadastrado com sucesso!");
        }
    }
    public static boolean cpfJaExiste(String cpf) {
        for (Paciente paciente : pacientes){
            if (paciente.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }
    public static boolean crmJaExiste(String crm){
        for (Medico medico : medicos){
            if (medico.getCrm().equals(crm)){
                return true;
            }
        }
        return false;
    }
}

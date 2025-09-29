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
    public List<Paciente> getPacientes() {
        return pacientes;
    }
    public List<Medico> getMedicos() {
        return medicos;
    }
    public void addMedico(Medico medico)
    {
        medicos.add(medico);
    }
    public void addConsulta(Consulta consulta)
    {
        consultas.add(consulta);
    }
    public void addPaciente(Paciente paciente)
    {
        pacientes.add(paciente);
    }

    public void listarConsultas()
    {
        for(Consulta consulta : consultas)
        {
            System.out.println(consulta.toString());
        }
    }
}

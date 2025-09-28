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
        RegistroMedico.escrever(medicos);
        RegistroPaciente.escrever(pacientes);
        RegistroConsulta.escrever(consultas);
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
        this.medicos.add(medico);
    }
    public void addConsulta(Consulta consulta)
    {
        this.consultas.add(consulta);
    }
    public void addPaciente(Paciente paciente)
    {
        this.pacientes.add(paciente);
    }

    public void listarConsultas()
    {
        for(Consulta consulta : consultas)
        {
            System.out.println(consulta.toString());
        }
    }
}

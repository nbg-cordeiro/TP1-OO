package hospitalmanager.dominio;

import java.util.*;

import static hospitalmanager.persistencia.RegistroCSV.*;

public class Sistema {
    private static List<Paciente> pacientes =  new ArrayList<>();
    private static List<PacienteEspecial> pacientesEspeciais = new ArrayList<>();
    private static List<Medico> medicos = new ArrayList<>();
    private static List<Consulta> consultas= new ArrayList<>();
    private static List<PlanoDeSaude> planos = new ArrayList<>();
    private static List<Internacao> internacoes = new ArrayList<>();
    private static List<String> especialidades =  new ArrayList<>();
    public Sistema()
    {
        especialidades = lerEspecialidades();
        planos = lerPlanosDeSaude();
        pacientes = lerPacientes();
        pacientesEspeciais = lerPacientesEspeciais(this);
        medicos = lerMedicos();
        internacoes = lerInternacoes(this);
        consultas = lerConsultas(this);
    }

    public static void salvarTudo()
    {
        try{
            escreverEspecialidades(especialidades);
            escreverPlanos(planos);
            escreverPacientes(pacientes);
            escreverPacientesEspeciais(pacientesEspeciais);
            escreverMedicos(medicos);
            escreverInternacoes(internacoes);
            escreverConsultas(consultas);
            System.out.println("Dados salvos com sucesso!");
        }catch(Exception e){
            System.out.println("Erro no salvamento"+e.getMessage());
        }
    }
    public List<PlanoDeSaude> getPlanos() {
        return planos;
    }
    public List<Consulta> getConsultas() {
        return consultas;
    }
    public List<Internacao> getInternacoes() {
        return internacoes;
    }
    public List<Paciente> getPacientes(){
        return pacientes;
    }
    public List<PacienteEspecial> getPacientesEspeciais(){
        return pacientesEspeciais;
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
        if(salaEstaOcupada(consulta)){
            throw new RuntimeException("Sala ocupada nesse horário!");
        }
        else{
            consultas.add(consulta);
        }
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
    public void addInternacao(Internacao internacao) {
        if(salaEstaOcupada(internacao)){
            throw new RuntimeException("Este leito está ocupado no momento!");
        }
        else{
            internacoes.add(internacao);
        }
    }
    public void addPacienteEspecial(PacienteEspecial pacienteEspecial)
    {
        if(cpfJaExiste(pacienteEspecial.getCpf()))
        {
            System.out.println("Cpf já existe!");
            throw new RuntimeException("Já existe um paciente cadastrado com este CPF!");
        }
        else{
            pacientesEspeciais.add(pacienteEspecial);
            System.out.println("Paciente cadastrado com sucesso!");
        }
    }
    public void addPlano(PlanoDeSaude plano)
    {
        planos.add(plano);
    }
    private boolean cpfJaExiste(String cpf) {
        for (Paciente paciente : pacientes){
            if (paciente.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    public List<String> getEspecialidades() {
        return especialidades;
    }

    private boolean crmJaExiste(String crm){
        for (Medico medico : medicos){
            if (medico.getCrm().equals(crm)){
                return true;
            }
        }
        return false;
    }
    private boolean salaEstaOcupada(Consulta consulta){
        for(Consulta cons: consultas){
            if(cons.getSala().equals(consulta.getSala())){
                if(cons.getDataHora().equals(consulta.getDataHora())){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean salaEstaOcupada(Internacao internacao){
        for(Internacao intern: internacoes){
            if(intern.getCheckOut()==null && internacao.getCheckOut()==null && intern.getLeito().equals(internacao.getLeito())){
                return true;
            }
        }
        return false;
    }

}

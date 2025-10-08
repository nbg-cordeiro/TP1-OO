package hospitalmanager.dominio;

import java.time.LocalDate;
import java.time.Period;

public class Internacao{
    private final Paciente paciente;
    private final Medico medico;
    private String leito;
    private final LocalDate inicio;
    private LocalDate fim=null;
    private Double descPlano = 1d;
    private Double descIdade = 1d;
    private final String observacoes;
    public Internacao(Paciente paciente,Medico medico,String leito, LocalDate inicio, String observacoes){
        this.paciente = paciente;
        this.leito = leito;
        this.inicio = inicio;
        this.medico = medico;
        this.observacoes = observacoes;
        if(paciente instanceof PacienteEspecial){
            if(((PacienteEspecial) paciente).getPlanoDeSaude()!=null){
                descPlano = 1-(((PacienteEspecial) paciente).getPlanoDeSaude().getDesInternacoes()/100);
            }
            else{
                descPlano = 1d;
            }
        }
        paciente.adicionarInternacao(this);
    }
    public Internacao(Paciente paciente,Medico medico,String leito, LocalDate inicio, LocalDate fim,String observacoes){
        this.paciente = paciente;
        this.medico = medico;
        this.leito = leito;
        this.inicio = inicio;
        this.fim = fim;
        this.observacoes = observacoes;
        if(paciente instanceof PacienteEspecial){
            if(((PacienteEspecial) paciente).getPlanoDeSaude()!=null){
                descPlano = 1-(((PacienteEspecial) paciente).getPlanoDeSaude().getDesInternacoes()/100);
            }
            else{
                descPlano = 1d;
            }
        }
        paciente.adicionarInternacao(this);
    }
    public void setLeito(String leito){
        this.leito = leito;
    }
    public String getObservacoes(){
        return observacoes;
    }
    public String getCpf()
    {
        return paciente.getCpf();
    }
    public String getLeito(){
        return leito;
    }
    public LocalDate getCheckIn(){
        return inicio;
    }
    public LocalDate getCheckOut(){
        return fim;
    }
    public Paciente getPaciente(){
       return paciente;
    }
    public Medico getMedico(){
        return medico;
    }
    public Double getPreco(){
        Period duracao;
        if(paciente.getIdade()>=60)
        {
            descIdade = 0.9d;
        }
        if(getCheckOut()==null)
        {
            duracao = Period.between(getCheckIn(),LocalDate.now());
        }
        else{
            duracao = Period.between(getCheckIn(),getCheckOut());
        }
        Double preco = (double) 500 + (100 *  (1+duracao.getDays()));
        preco *= (descIdade);
        preco *= (descPlano);
        return preco;
    }
    public void fazerCheckOut(){
        fim =  LocalDate.now();
    }
    @Override
    public String toString(){
        if(getCheckOut()==null)
        {
            return String.join(",",getPaciente().getCpf(),getMedico().getCrm(),getLeito(),getCheckIn().toString(),"Não",getObservacoes());
        }
        return String.join(",",getPaciente().getCpf(),getMedico().getCrm(),getLeito(),getCheckIn().toString(),getCheckOut().toString(),getObservacoes());
    }
}

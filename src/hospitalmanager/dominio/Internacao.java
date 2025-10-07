package hospitalmanager.dominio;

import java.time.LocalDate;
import java.time.Period;

public class Internacao{
    private Paciente paciente;
    private PacienteEspecial pacienteEspecial;
    private String leito;
    private LocalDate inicio;
    private LocalDate fim=null;
    private Double preco;
    private Double descPlano = 1d;
    private Double descIdade = 1d;
    public Internacao(Paciente paciente,String leito, LocalDate inicio){
        this.paciente = paciente;
        this.leito = leito;
        this.inicio = inicio;
    }
    public Internacao(Paciente paciente,String leito, LocalDate inicio, LocalDate fim){
        this.paciente = paciente;
        this.leito = leito;
        this.inicio = inicio;
        this.fim = fim;
    }
    public Internacao(PacienteEspecial paciente,String leito, LocalDate inicio){
        this.pacienteEspecial = paciente;
        this.leito = leito;
        this.inicio = inicio;
        this.descPlano = paciente.getPlanoDeSaude().getDesInternacoes();
    }
    public Internacao(PacienteEspecial paciente,String leito, LocalDate inicio, LocalDate fim){
        this.pacienteEspecial = paciente;
        this.leito = leito;
        this.inicio = inicio;
        this.fim = fim;
        this.descPlano = (paciente.getPlanoDeSaude().getDesInternacoes())/100d;
    }
    public void setPaciente(Paciente paciente){
        this.paciente = paciente;
    }
    public void setLeito(String leito){
        this.leito = leito;
    }
    public void setInicio(LocalDate inicio){
        this.inicio = inicio;
    }
    public void setFim(LocalDate fim){
        this.fim = fim;
    }
    public void setPreco(Double preco){
        this.preco = preco;
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
    public Double getPreco(){
        Period duracao;
        if(getPaciente().getIdade()>=60){
            descIdade = 0.9d;
        }
        if(getCheckOut()==null)
        {
            duracao = Period.between(getCheckIn(),LocalDate.now());
        }
        else{
            duracao = Period.between(getCheckIn(),getCheckOut());
        }
        preco= 500d + (100d * (double)duracao.getDays());
        preco *= (descIdade);
        preco *= (descPlano);
        return preco;
    }
    public void finalizar(){
        fim =  LocalDate.now();
    }
    @Override
    public String toString(){
        return String.join(",",getPaciente().getNome(),getLeito(),getPreco().toString(),getCheckIn().toString(),getCheckOut().toString());
    }
}

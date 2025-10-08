package hospitalmanager.dominio;

import java.time.LocalDate;
import java.util.*;

public class Paciente extends Pessoa{
    List<Consulta> historicoConsultas =  new ArrayList<>();
    List<Internacao> historicoInternacoes =  new ArrayList<>();

    public Paciente(){
        super();
    }
    public Paciente(String cpf, String nome, LocalDate dataNascimento) {
        super(cpf ,nome ,dataNascimento);
    }
    public void adicionarConsulta(Consulta consulta) {
        this.historicoConsultas.add(consulta);
    }
    public void adicionarInternacao(Internacao internacao){
        this.historicoInternacoes.add(internacao);
    }
    public List<Consulta> getConsultas()
        {return historicoConsultas;}
    public List<Internacao> getInternacoes() {
        return historicoInternacoes;
    }
    @Override
    public String toString()
    {
        return String.join(",",getCpf(),getNome(),getDataNascimento().toString());
    }
}

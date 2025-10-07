package hospitalmanager.dominio;

import java.time.LocalDate;
import java.util.*;

public class Medico extends Pessoa{
    private String crm;
    private final String especialidade;
    List<Consulta> agenda =  new ArrayList<>();

    public Medico(String crm, String cpf, String nome, LocalDate dataNascimento,String  especialidade)
    {
        super(cpf,nome,dataNascimento);
        this.crm = crm;
        this.especialidade = especialidade;
    }
    public String getEspecialidade(){
        return especialidade;
    }
    public void setCrm(String crm)
        {this.crm = crm;}
    public void adicionarConsulta(Consulta consulta)
        {this.agenda.add(consulta);}
    public void removerConsulta(Consulta consulta)
        {this.agenda.remove(consulta);}

    public List<Consulta> getConsultas()
        {return agenda;}
    public String getCrm()
        {return crm;}

    @Override
    public String toString()
    {
        return String.join(",",getCrm(),getCpf(),getNome(),getDataNascimento().toString(),getEspecialidade());
    }

}

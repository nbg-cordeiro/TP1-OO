package hospitalmanager.dominio;

import java.time.LocalDate;
import java.util.*;

public class Medico extends Pessoa{
    private String crm;
    private final String especialidade;
    private final double custoConsulta;
    List<Consulta> agenda =  new ArrayList<>();

    public Medico(String crm, String cpf, String nome, LocalDate dataNascimento,String  especialidade,Double custoConsulta)
    {
        super(cpf,nome,dataNascimento);
        this.crm = crm;
        this.especialidade = especialidade;
        this.custoConsulta = custoConsulta;
    }
    public String getEspecialidade(){
        return especialidade;
    }
    public void setCrm(String crm)
        {this.crm = crm;}
    public Double getCustoConsulta(){
        return custoConsulta;
    }
    public List<Consulta> getAgenda()
        {return agenda;}
    public void addAgenda(Consulta consulta){
        if(existeHorario(consulta))
        {
            throw new RuntimeException("A agenda do médico está cheia nesse horário!");
        }
        else{
            agenda.add(consulta);
        }
    }
    public String getCrm()
        {return crm;}

    @Override
    public String toString()
    {
        return String.join(",", getCrm(), getCpf(),getNome(),getDataNascimento().toString(),getEspecialidade(),getCustoConsulta().toString());
    }
    private boolean existeHorario(Consulta consulta){
        for(Consulta con : agenda){
            if(con.getDataHora()==consulta.getDataHora())
            {
                return true;
            }
        }
        return false;
    }

}

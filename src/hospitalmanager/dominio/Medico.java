package hospitalmanager.dominio;

import java.util.ArrayList;
import java.util.List;

public class Medico extends Pessoa {

    String crm;
    List<Consulta> consultas =  new ArrayList<>();

    public Medico(String crm, String cpf, String nome, int idade)
    {
        super(cpf,nome,idade);
        this.crm = crm;
    }
    public Medico(String crm, String cpf, String nome, int idade, List<Consulta> consultas)
    {
        super(cpf,nome,idade);
        this.crm = crm;
        this.consultas = consultas;
    }

    public void setCrm(String crm)
        {this.crm = crm;}
    public void adicionarConsulta(Consulta consulta)
        {this.consultas.add(consulta);}
    public void removerConsulta(Consulta consulta)
        {this.consultas.remove(consulta);}

    public List<Consulta> getConsultas()
        {return consultas;}
    public String getCrm()
        {return crm;}

    public String toString()
    {
        return "vou colocar o formato depois :P";
    }
}

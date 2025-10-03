package hospitalmanager.dominio;

import java.io.Serial;
import java.util.*;

public class Medico extends Pessoa implements java.io.Serializable{
    @Serial
    private static final long serialVersionUID = 1L;
    private String crm;
    List<Consulta> agenda =  new ArrayList<>();

    public Medico(String crm, String cpf, String nome, int idade)
    {
        super(cpf,nome,idade);
        this.crm = crm;
    }
    public Medico(String crm, String cpf, String nome, int idade, List<Consulta> consultas)
    {
        super(cpf,nome,idade);
        this.crm = crm;
        this.agenda = consultas;
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

    public void listarConsultas()
    {
        for(Consulta consulta : agenda)
        {
            System.out.println(consulta.toString());
        }
    }
    public String toString()
    {
        return "-Nome: "+getNome()+"\n-CRM: "+getCrm()+"\n-Idade: "+getIdade()+"\n-CPF: "+getCpf();
    }

}

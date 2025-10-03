package hospitalmanager.dominio;

import java.io.Serial;
import java.util.*;

public class Paciente extends Pessoa implements java.io.Serializable{
    @Serial
    private static final long serialVersionUID = 1L;
    List<Consulta> historicoConsultas =  new ArrayList<>();

    public Paciente(String cpf, String nome, int idade,List<Consulta> consultas)
    {
        super(cpf ,nome ,idade);
        this.historicoConsultas = consultas;
    }
    public Paciente(String cpf, String nome, int idade)
    {
        super(cpf ,nome ,idade);
    }

    public void adicionarConsulta(Consulta consulta)
    {
        this.historicoConsultas.add(consulta);
    }
    public void removerConsulta(Consulta consulta)
    {
        this.historicoConsultas.remove(consulta);
    }

    public List<Consulta> getConsultas()
        {return historicoConsultas;}

    public void listarConsultas()
    {
        for(Consulta consulta : historicoConsultas)
        {
            System.out.println(consulta.toString());
        }
    }

    public String toString()
    {
        return "-Nome: "+getNome()+"\n-Idade: "+getIdade()+"\n-CPF: "+getCpf();
    }
}

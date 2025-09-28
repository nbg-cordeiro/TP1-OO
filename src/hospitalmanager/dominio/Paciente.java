package hospitalmanager.dominio;

import java.util.ArrayList;
import java.util.List;

public class Paciente extends Pessoa{

    List<Consulta> consultas =  new ArrayList<>();
    public Paciente(String cpf, String nome, int idade,List<Consulta> consultas)
    {
        super(cpf ,nome ,idade);
        this.consultas = consultas;
    }
    public Paciente(String cpf, String nome, int idade)
    {
        super(cpf ,nome ,idade);
    }
    public void adicionarConsulta(Consulta consulta)
    {
        this.consultas.add(consulta);
    }
    public List<Consulta> getConsultas() {
        return consultas;
    }
}

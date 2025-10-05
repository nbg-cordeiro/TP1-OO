package hospitalmanager.dominio;

import java.io.Serial;

public class PlanoDeSaude implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String nome;
    private String codigo;
    private String telefone;
    private double descontoConsultas;
    private double descontoInternacoes;

    public PlanoDeSaude(String nome, String codigo, double descontoConsultas, double descontoInternacoes) {
        this.nome = nome;
        this.codigo = codigo;
        this.descontoConsultas = descontoConsultas;
        this.descontoInternacoes = descontoInternacoes;
    }

    public String getNome()
        {return nome;}
    public void setNome(String nome)
        {this.nome = nome;}
    public String getCodigo()
        {return codigo;}
    public void setCodigo(String codigo)
        {this.codigo = codigo;}
    public String getTelefone()
        {return telefone;}
    public void setTelefone(String telefone)
        {this.telefone = telefone;}
    public double getDesConsultas()
        {return descontoConsultas;}
    public void setDesConsultas(double descontoConsultas)
        {this.descontoConsultas = descontoConsultas;}
    public double getDesInternacoes()
        {return descontoInternacoes;}
    public void setDesInternacoes(double descontoInternacoes)
        {this.descontoInternacoes = descontoInternacoes;}
}
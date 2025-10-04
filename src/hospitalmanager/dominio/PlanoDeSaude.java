package hospitalmanager.dominio;

import java.io.Serial;

public class PlanoDeSaude implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String nome;
    private String codigo;
    private String tipo;
    private double descontoConsultas;
    private double descontoInternacoes;

    public PlanoDeSaude(String nome, String codigo, String tipo, double descontoConsultas, double descontoInternacoes) {
        this.nome = nome;
        this.codigo = codigo;
        this.descontoConsultas = descontoConsultas;
        this.descontoInternacoes = descontoInternacoes;
        this.tipo = tipo;
    }

    public String getNome()
        {return nome;}
    public void setNome(String nome)
        {this.nome = nome;}

    public String getCodigo()
        {return codigo;}
    public void setCodigo(String codigo)
        {this.codigo = codigo;}

    public String getTipo()
        {return tipo;}
    public void setTipo(String tipo)
        {this.tipo = tipo;}

    public double getDesConsultas()
        {return descontoConsultas;}
    public void setDesConsultas(double descontoConsultas)
        {this.descontoConsultas = descontoConsultas;}

    public double getDesInternacoes()
        {return descontoInternacoes;}
    public void setDesInternacoes(double descontoInternacoes)
        {this.descontoInternacoes = descontoInternacoes;}
}
package hospitalmanager.dominio;

public class PlanoDeSaude{

    private String nome;
    private String codigo;
    private Double descontoConsultas;
    private Double descontoInternacoes;

    public PlanoDeSaude(String codigo, String nome, Double descontoConsultas, Double descontoInternacoes) {
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
    public Double getDesConsultas()
        {return descontoConsultas;}
    public void setDesConsultas(Double descontoConsultas)
        {this.descontoConsultas = descontoConsultas;}
    public Double getDesInternacoes()
        {return descontoInternacoes;}
    public void setDesInternacoes(Double descontoInternacoes)
        {this.descontoInternacoes = descontoInternacoes;}
    @Override
    public String toString(){
        return String.join(",",getCodigo(),getNome(),getDesConsultas().toString(),getDesInternacoes().toString());
    }
}
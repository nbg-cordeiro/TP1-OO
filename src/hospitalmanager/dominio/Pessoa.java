package hospitalmanager.dominio;

import java.io.Serial;

public class Pessoa implements java.io.Serializable{
    @Serial
    private static final long serialVersionUID = 1L;
    private String cpf;
    private String nome;
    private int idade;

    public Pessoa(String cpf, String nome,int idade)
    {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
    }

    public void setCpf(String cpf)
        {this.cpf = cpf;}
    public void setNome(String nome)
        {this.nome = nome;}
    public void setIdade(int idade)
        {this.idade = idade;}
    public int getIdade()
        {return idade;}
    public String getCpf()
        {return cpf;}
    public String getNome()
        {return nome;}

}

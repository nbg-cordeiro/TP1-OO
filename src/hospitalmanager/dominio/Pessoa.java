package hospitalmanager.dominio;

import java.time.LocalDate;
import java.time.Period;


public class Pessoa{
    private String cpf;
    private String nome;
    private LocalDate dataNascimento;

    public Pessoa(String cpf, String nome,LocalDate dataNascimento)
    {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public Pessoa() {
    }

    public void setCpf(String cpf)
        {this.cpf = cpf;}
    public void setNome(String nome)
        {this.nome = nome;}
    public int getIdade() {
            if(dataNascimento==null)
            {
                return 0;

            }
                Period periodo = Period.between(dataNascimento, LocalDate.now());
                return periodo.getYears();
            }
    public String getCpf()
        {return cpf;}
    public String getNome()
        {return nome;}

}

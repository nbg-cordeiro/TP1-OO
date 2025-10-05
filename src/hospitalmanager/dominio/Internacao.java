package hospitalmanager.dominio;

import java.time.LocalDateTime;

public class Internacao{
    private Paciente paciente;
    private String leito;
    private LocalDateTime inicio;
    private double preco;
    public Internacao(Paciente paciente,String leito, LocalDateTime inicio){
        this.paciente = paciente;
        this.leito = leito;
        this.inicio = inicio;
    }
    public double getPreco(){

        return preco;
    }
}

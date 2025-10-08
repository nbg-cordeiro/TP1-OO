package hospitalmanager.dominio;

import java.time.LocalDateTime;
import java.util.Objects;

public class Consulta{

    private final Paciente paciente;
    private final Medico medico;
    private String status;
    private final LocalDateTime dataHora;
    private String sala;
    private String observacoes;
    private double descPlano= 1d;
    private double descIdade = 1d;
    public Consulta(Paciente paciente, Medico medico,String status,String sala, LocalDateTime dataHora, String observacoes)
    {
        this.paciente = paciente;
        this.medico = medico;
        this.dataHora = dataHora;
        this.sala = sala;
        this.observacoes = observacoes;
        this.status = status;
        if (paciente instanceof PacienteEspecial){
            if(((PacienteEspecial) paciente).getPlanoDeSaude()!=null){
                descPlano = (double) 1 - (((PacienteEspecial) paciente).getPlanoDeSaude().getDesConsultas() / 100d);
            }
        }
        paciente.adicionarConsulta(this);
        medico.addAgenda(this);
    }
    public String getCpf()
    {
        return paciente.getCpf();
    }
    public void setSala(String sala)
        {this.sala = sala;}
    public void setStatus(String status)
        {this.status = status;}
        public double getPreco(){
                if(paciente.getIdade()>=60)
                {
                    descIdade = 0.9d;
                }
            return (medico.getCustoConsulta()*descIdade)*descPlano;
        }
    public void concluirConsulta(String observacoesExtras){
        observacoes += observacoesExtras;
        status = "Concluída";
    }
    public String getObservacoes()
        {return observacoes;}
    public Paciente getPaciente()
        {return paciente;}
    public Medico getMedico()
        {return medico;}
    public String getStatus()
        {
            if(!Objects.equals(status, "Cancelada") && LocalDateTime.now().isAfter(dataHora))
            {
                status = "Concluída";
            }
            return status;}
    public void cancelarConsulta(){
        status="Cancelada";
    }
    public String getSala()
        {return sala;}
    public LocalDateTime getDataHora()
        {return dataHora;}
    @Override
    public String toString() {
        return String.join(",",getPaciente().getCpf(),getMedico().getCrm(),getStatus(),getDataHora().toString(),getSala(), getObservacoes());
    }

}

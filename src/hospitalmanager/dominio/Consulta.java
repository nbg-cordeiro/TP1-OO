package hospitalmanager.dominio;

import java.time.LocalDateTime;

public class Consulta{

    private Paciente paciente;
    private PacienteEspecial pacienteEspecial;
    private Medico medico;
    private String status = "Marcada";
    private LocalDateTime dataHora;
    private String local;
    private String motivo;
    private double descPlano = 1d;
    private double descIdade = 1d;
    public Consulta(Paciente paciente, Medico medico,String status,String local, LocalDateTime dataHora, String motivo)
    {
        this.paciente = paciente;
        this.medico = medico;
        this.dataHora = dataHora;
        this.local = local;
        this.motivo = motivo;
        this.status = status;
        paciente.adicionarConsulta(this);
        medico.adicionarConsulta(this);
    }
    public Consulta(PacienteEspecial paciente, Medico medico,String status,String local, LocalDateTime dataHora, String motivo)
    {
        this.pacienteEspecial = paciente;
        this.medico = medico;
        this.dataHora = dataHora;
        this.local = local;
        this.motivo = motivo;
        this.status = status;
        descPlano = (paciente.getPlanoDeSaude().getDesConsultas())/100d;
        paciente.adicionarConsulta(this);
        medico.adicionarConsulta(this);
    }
    public void setMotivo(String motivo)
        {this.motivo = motivo;}
    public void setDataHora(LocalDateTime dataHora)
        {this.dataHora = dataHora;}
    public void setLocal(String local)
        {this.local = local;}
    public void setPaciente(Paciente paciente)
        {this.paciente = paciente;}
    public void setMedico(Medico medico)
        {this.medico = medico;}
    public void concluirConsulta()
        {status = "Concluída";}
    public void cancelarConsulta()
        {
            medico.removerConsulta(this);
            paciente.removerConsulta(this);
            this.status = "Cancelada";
        }
        public double getPreco(){
            if(paciente.getIdade()>=60){
                descIdade = 0.9d;
            }
            return (200*descIdade)*descPlano;
        }

    public String getMotivo()
        {return motivo;}
    public Paciente getPaciente()
        {return paciente;}
    public Medico getMedico()
        {return medico;}
    public String getStatus()
        {return status;}
    public String getLocal()
        {return local;}
    public LocalDateTime getDataHora()
        {return dataHora;}
    @Override
    public String toString() {
        return String.join(",",getPaciente().getCpf(),getMedico().getCrm(),getStatus(),getDataHora().toString(),getLocal(),getMotivo());
    }
}

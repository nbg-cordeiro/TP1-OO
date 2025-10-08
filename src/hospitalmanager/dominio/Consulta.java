package hospitalmanager.dominio;

import java.time.LocalDateTime;
import java.util.Objects;

public class Consulta{

    private Paciente paciente= new Paciente(null,null,null);
    private PacienteEspecial pacienteEspecial = new PacienteEspecial(null,null,null,null);
    private final Medico medico;
    private String status;
    private final LocalDateTime dataHora;
    private String sala;
    private final String motivo;
    private double descPlano = 1d;
    private double descIdade = 1d;
    public Consulta(Paciente paciente, Medico medico,String status,String sala, LocalDateTime dataHora, String motivo)
    {
        this.paciente = paciente;
        this.medico = medico;
        this.dataHora = dataHora;
        this.sala = sala;
        this.motivo = motivo;
        this.status = status;
        paciente.adicionarConsulta(this);
        medico.adicionarConsulta(this);
    }
    public Consulta(PacienteEspecial paciente, Medico medico,String status,String sala, LocalDateTime dataHora, String motivo)
    {
        this.pacienteEspecial = paciente;
        this.medico = medico;
        this.dataHora = dataHora;
        this.sala = sala;
        this.motivo = motivo;
        this.status = status;
        descPlano = 1-(paciente.getPlanoDeSaude().getDesConsultas())/100d;
        paciente.adicionarConsulta(this);
        medico.adicionarConsulta(this);
    }
    public String getCpf()
    {
        if(pacienteEspecial.getCpf()==null)
        {
            return paciente.getCpf();
        }
        return pacienteEspecial.getCpf();
    }
    public void setSala(String sala)
        {this.sala = sala;}
    public void setStatus(String status)
        {this.status = status;}
        public double getPreco(){
                if(pacienteEspecial.getIdade()>=60 || paciente.getIdade()>=60)
                {
                    descIdade = 0.9d;
                }
            return medico.getCustoConsulta()*descIdade*descPlano;
        }
    public String getMotivo()
        {return motivo;}
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
    public String getSala()
        {return sala;}
    public LocalDateTime getDataHora()
        {return dataHora;}
    @Override
    public String toString() {
        return String.join(",",getPaciente().getCpf(),getMedico().getCrm(),getStatus(),getDataHora().toString(),getSala(),getMotivo());
    }

}

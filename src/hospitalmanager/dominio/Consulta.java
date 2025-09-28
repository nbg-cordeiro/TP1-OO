package hospitalmanager.dominio;
import java.time.LocalDateTime;

public class Consulta {
    private Paciente paciente;
    private Medico medico;
    private String status = "Agendada";
    private LocalDateTime dataHora;
    private String local;

    public Consulta(Paciente paciente, Medico medico, String local, LocalDateTime dataHora)
    {
        this.paciente = paciente;
        this.medico = medico;
        this.dataHora = dataHora;
        this.local = local;
        paciente.adicionarConsulta(this);
        medico.adicionarConsulta(this);
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
    public void setLocal(String local) {
        this.local = local;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public void setMedico(Medico medico){
        this.medico = medico;
    }

    public void concluirConsulta()
    {
        status = "Concluída";
    }
    public void cancelarConsulta()
    {
        status = "Cancelado";
    }

    public Paciente getPaciente() {
        return paciente;
    }
    public Medico getMedico() {
        return medico;
    }
    public String getStatus() {
        return status;
    }
    public String getLocal() {
        return local;
    }
    public LocalDateTime getDataHora() {
        return dataHora;
    }


}

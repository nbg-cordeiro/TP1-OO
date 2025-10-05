package hospitalmanager.dominio;

import java.time.LocalDate;

public class PacienteEspecial extends Paciente{
    private final PlanoDeSaude planoDeSaude;
    public PacienteEspecial (String cpf, String Nome, LocalDate dataNascimento, PlanoDeSaude planoDeSaude){
        super(cpf,Nome,dataNascimento);
        this.planoDeSaude = planoDeSaude;
    }
    public PlanoDeSaude getPlanoDeSaude(){
        return planoDeSaude;
    }
}

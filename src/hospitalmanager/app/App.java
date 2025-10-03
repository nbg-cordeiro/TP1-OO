package hospitalmanager.app;

import hospitalmanager.dominio.Medico;
import hospitalmanager.dominio.Paciente;
import hospitalmanager.dominio.Sistema;
import hospitalmanager.interfaces.MenuInicial;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        Sistema sistema = new Sistema();
        System.out.println("Pacientes:"+sistema.getPacientes().size());
        System.out.println("Medicos:"+sistema.getMedicos().size());
        Sistema.salvarTudo();
        MenuInicial janela = new MenuInicial(sistema);
    }
}

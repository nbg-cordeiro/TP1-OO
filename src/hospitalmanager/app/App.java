package hospitalmanager.app;

import hospitalmanager.dominio.Sistema;
import hospitalmanager.interfaces.*;

import java.awt.*;

public class App {
    public static void main(String[] args) throws InterruptedException {
        int opcao;
        Sistema sistema = new Sistema();
        MenuInicial janela = new MenuInicial();
    }
    public static void executarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                MenuPacientes menuPacie = new MenuPacientes();
                break;
            case 2:
                MenuConsultas menuConsu = new MenuConsultas();
                break;
            case 3:
                MenuInternacoes menuInter = new MenuInternacoes();
                break;
            case 4:
                MenuMedicos menuMedi = new MenuMedicos();
                break;
            case 5:
                MenuPlanos menuPlano = new MenuPlanos();
                break;
            case 6:
                MenuGeral menuGeral = new MenuGeral();
                break;
        }
    }
}

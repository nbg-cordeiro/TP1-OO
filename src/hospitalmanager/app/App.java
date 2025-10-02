package hospitalmanager.app;

import hospitalmanager.dominio.PlanoDeSaude;
import hospitalmanager.dominio.Sistema;
import hospitalmanager.interfaces.*;


public class App {
    public static void main(String[] args){
        Sistema sistema = new Sistema();
        MenuInicial janela = new MenuInicial();
    }
}

package hospitalmanager.app;

import hospitalmanager.dominio.Sistema;
import hospitalmanager.interfaces.JanelaTeste;
import hospitalmanager.interfaces.OutraJanela;

public class App {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        JanelaTeste janela1 = new JanelaTeste();
        OutraJanela outraJanela = new OutraJanela();
    }
}

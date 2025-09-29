package hospitalmanager.app;

import hospitalmanager.dominio.Sistema;
import hospitalmanager.interfaces.JanelaComum;

public class App {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        JanelaComum janela = new JanelaComum();
        janela.setVisible(true);

    }
}

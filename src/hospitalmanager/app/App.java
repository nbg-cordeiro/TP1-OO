package hospitalmanager.app;

import hospitalmanager.dominio.Sistema;

public class App {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        Sistema.salvarTudo();
    }
}

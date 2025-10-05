package hospitalmanager.app;
import hospitalmanager.dominio.Sistema;
import hospitalmanager.interfaces.MenuInicial;



public class App{
    public static void main(String[] args){
        Sistema sistema = new Sistema();
        MenuInicial janela = new MenuInicial(sistema);
        janela.setVisible(true);
    }
}

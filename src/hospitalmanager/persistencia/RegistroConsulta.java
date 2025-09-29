package hospitalmanager.persistencia;

import hospitalmanager.dominio.Consulta;
import java.io.*;
import java.util.*;

public class RegistroConsulta{
    public static void escrever(List<Consulta> consultas)
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream("src/hospitalmanager/dados/Consultas.dat")))
        {
            oos.writeObject(consultas);
        } catch (IOException e) {
            System.out.println("Erro ao escrever as consultas: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Consulta> ler()
    {
        try (ObjectInputStream ois = new ObjectInputStream(
            new FileInputStream("src/hospitalmanager/dados/Consultas.dat"))){
            return (List<Consulta>) ois.readObject();
        } catch (ClassNotFoundException | IOException e){
            System.out.println("Sem registros cadastrados, inicializando Consultas vazias");
            return new ArrayList<>();
        }
    }
}
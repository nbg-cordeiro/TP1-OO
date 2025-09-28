package hospitalmanager.persistencia;

import hospitalmanager.dominio.Medico;
import java.io.*;
import java.util.*;

public class RegistroMedico{
    public static void escrever(List<Medico> medicos)
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("src/hospitalmanager/dados/Medicos.dat")))
        {
            oos.writeObject(medicos);
        } catch (IOException e) {
            System.out.println("Erro ao escrever os médicos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Medico> ler()
    {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("src/hospitalmanager/dados/Medicos.dat"))){
            return (List<Medico>) ois.readObject();
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Erro ao ler os medicos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
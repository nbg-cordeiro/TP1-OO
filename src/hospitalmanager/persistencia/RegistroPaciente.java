package hospitalmanager.persistencia;

import hospitalmanager.dominio.Paciente;
import java.io.*;
import java.util.*;

public class RegistroPaciente{
    public static void escrever(List<Paciente> medicos)
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("src/hospitalmanager/dados/Pacientes.dat")))
        {
            oos.writeObject(medicos);
        } catch (IOException e) {
            System.out.println("Erro ao escrever os pacientes: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Paciente> ler()
    {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("src/hospitalmanager/dados/Pacientes.dat"))){
            return (List<Paciente>) ois.readObject();
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Erro ao ler os pacientes: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
package hospitalmanager.persistencia;

import hospitalmanager.dominio.Medico;
import java.io.*;
import java.nio.file.Path;
import java.util.*;

import static java.nio.file.Files.deleteIfExists;

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
            System.out.println("Registro de Medicos lidos com sucesso!");
            return (List<Medico>) ois.readObject();
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Sem registros cadastrados, inicializando Medicos vazios");
            return new ArrayList<>();
        }
    }
    public static void deletarRegistro() throws IOException {
        deleteIfExists(Path.of("src/hospitalmanager/dados/Medicos.dat"));
    }
}
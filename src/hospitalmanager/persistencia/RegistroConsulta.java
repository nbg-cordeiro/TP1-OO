package hospitalmanager.persistencia;

import hospitalmanager.dominio.Consulta;
import java.io.*;
import java.nio.file.Path;
import java.util.*;

import static java.nio.file.Files.deleteIfExists;

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
            System.out.println("Registro de Consultas lidos com sucesso!");
            return (List<Consulta>) ois.readObject();
        } catch (ClassNotFoundException | IOException e){
            System.out.println("Sem registros cadastrados, inicializando Consultas vazias");
            return new ArrayList<>();
        }
    }
    public static void deletarRegistro() throws IOException {
        deleteIfExists(Path.of("src/hospitalmanager/dados/Consultas.dat"));
    }
}
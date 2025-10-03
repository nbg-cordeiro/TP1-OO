package hospitalmanager.persistencia;

import hospitalmanager.dominio.Paciente;
import java.io.*;
import java.nio.file.Path;
import java.util.*;

import static java.nio.file.Files.deleteIfExists;

public class RegistroPaciente{
    public static void escrever(List<Paciente> pacientes)
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream("src/hospitalmanager/dados/Pacientes.dat")))
        {
            oos.writeObject(pacientes);
        } catch (IOException e) {
            System.out.println("Erro ao escrever os pacientes: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Paciente> ler()
    {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("src/hospitalmanager/dados/Pacientes.dat"))){
            System.out.println("Registro de Pacientes lidos com sucesso!");
            return (List<Paciente>) ois.readObject();
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Sem registros cadastrados, inicializando Pacientes vazios"+ e.getMessage());
            return new ArrayList<>();
        }
    }
    public static void deletarRegistro() throws IOException {
        deleteIfExists(Path.of("src/hospitalmanager/dados/Pacientes.dat"));
    }
}
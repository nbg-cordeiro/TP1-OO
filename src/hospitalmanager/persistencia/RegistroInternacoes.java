package hospitalmanager.persistencia;

import hospitalmanager.dominio.Internacao;
import hospitalmanager.dominio.Sistema;

import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.util.*;

import static java.nio.file.Files.deleteIfExists;

public class RegistroInternacoes{
    public static void escrever(List<Internacao> internacoes)
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("src/hospitalmanager/dados/Internacoes.dat")))
        {
            oos.writeObject(internacoes);
        } catch (IOException e) {
            System.out.println("Erro ao escrever as internações: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Internacao> ler()
    {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("src/hospitalmanager/dados/Internacoes.dat"))){
            System.out.println("Registro de Pacientes lidos com sucesso!");
            return (List<Internacao>) ois.readObject();
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Sem registros cadastrados, inicializando Internações vazias"+ e.getMessage());
            return new ArrayList<>();
        }
    }
    public static void deletarRegistro(Sistema sistema) throws IOException {
        int escolha =  JOptionPane.showConfirmDialog(null,"Você está prestes a deletar TODOS os registros de Internações.\n Deseja Continuar?", "Aviso",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if(escolha == JOptionPane.OK_OPTION){
            deleteIfExists(Path.of("src/hospitalmanager/dados/Internacoes.dat"));
            sistema.getInternacoes().clear();
        }
        else{
            JOptionPane.showMessageDialog(null,"Operação cancelada.\n Nenhum registro foi deletado!");
        }
    }
}
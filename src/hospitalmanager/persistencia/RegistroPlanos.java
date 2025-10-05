package hospitalmanager.persistencia;

import hospitalmanager.dominio.PlanoDeSaude;
import hospitalmanager.dominio.Sistema;

import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.util.*;

import static java.nio.file.Files.deleteIfExists;

public class RegistroPlanos{
    public static void escrever(List<PlanoDeSaude> planos)
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("src/hospitalmanager/dados/Planos.dat")))
        {
            oos.writeObject(planos);
        } catch (IOException e) {
            System.out.println("Erro ao escrever os Planos de Saúde: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<PlanoDeSaude> ler()
    {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("src/hospitalmanager/dados/Planos.dat"))){
            System.out.println("Registro de Planos de Saúde lidos com sucesso!");
            return (List<PlanoDeSaude>) ois.readObject();
        } catch (IOException | ClassNotFoundException e){
            System.out.println("Sem registros cadastrados, inicializando Planos de Saúde vazios"+ e.getMessage());
            return new ArrayList<>();
        }
    }
    public static void deletarRegistro(Sistema sistema) throws IOException {
        int escolha =  JOptionPane.showConfirmDialog(null,"Você está prestes a deletar TODOS os registros de Planos de Saúde.\n Deseja Continuar?", "Aviso",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if(escolha == JOptionPane.OK_OPTION){
            deleteIfExists(Path.of("src/hospitalmanager/dados/Planos.dat"));
            sistema.getPlanos().clear();
        }
        else{
            JOptionPane.showMessageDialog(null,"Operação cancelada.\n Nenhum registro foi deletado!");
        }
    }
}
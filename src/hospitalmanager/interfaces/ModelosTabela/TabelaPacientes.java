package hospitalmanager.interfaces.ModelosTabela;

import hospitalmanager.dominio.Paciente;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TabelaPacientes extends AbstractTableModel {

    private final List<Paciente> pacientes;
    private final String[] colunas = {"Nome", "CPF", "Idade", "Telefone"};

    public TabelaPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    @Override
    public int getRowCount() {
        return pacientes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Paciente paciente = pacientes.get(rowIndex);
        return switch (columnIndex){
            case 0 -> paciente.getNome();
            case 1 -> paciente.getCpf();
            case 2 -> paciente.getIdade();
            default -> null;
        };
    }
}
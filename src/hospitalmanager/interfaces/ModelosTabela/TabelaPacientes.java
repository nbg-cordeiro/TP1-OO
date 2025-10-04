package hospitalmanager.interfaces.ModelosTabela;

import hospitalmanager.dominio.Paciente;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TabelaPacientes extends AbstractTableModel {

    private final List<Paciente> pacientes;
    private final String[] colunas = {"CPF", "Nome", "Idade", "Histórico"};

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
            case 0 -> paciente.getCpf();
            case 1 -> "    "+paciente.getNome();
            case 2 -> paciente.getIdade();
            case 3 -> "Ver";
            default -> null;
        };
    }
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Paciente paciente = pacientes.get(rowIndex);
        switch (columnIndex){
            case 0:
                paciente.setCpf( (String) aValue );
                break;
            case 1:
                paciente.setNome( ((String) aValue).trim() );
                break;
            case 2:
                try {
                    paciente.setIdade( Integer.parseInt(aValue.toString()) );
                } catch (NumberFormatException e) {
                    System.err.println("Formato invalido.");
                }
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0 || columnIndex == 1 || columnIndex == 2;
    }
}


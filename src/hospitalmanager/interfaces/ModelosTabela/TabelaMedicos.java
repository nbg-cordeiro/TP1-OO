package hospitalmanager.interfaces.ModelosTabela;

import hospitalmanager.dominio.Medico;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TabelaMedicos extends AbstractTableModel {
    private final List<Medico> medicos;
    private final String[] colunas = {"CRM", "CPF","Nome", "Idade","Especialidade","Agenda"};

    public TabelaMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }

    @Override
    public int getRowCount() {
        return medicos.size();
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
        Medico medico = medicos.get(rowIndex);
        return switch (columnIndex){
            case 0 -> medico.getCrm();
            case 1 -> medico.getCpf();
            case 2 -> "    "+medico.getNome();
            case 3 -> medico.getIdade();
            case 4 -> medico.getEspecialidade();
            case 5 -> "Ver";
            default -> null;
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Medico medico = medicos.get(rowIndex);
        switch (columnIndex){
            case 0:
                medico.setCrm( (String) aValue );
                break;
            case 1:
                medico.setCpf( ((String) aValue).trim() );
                break;
            case 2:
                medico.setNome( ((String) aValue).trim() );
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0 || columnIndex == 1 || columnIndex == 2;
    }
}

package hospitalmanager.interfaces.ModelosTabela;

import hospitalmanager.dominio.PacienteEspecial;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TabelaPacientesEspeciais extends AbstractTableModel {

    private final List<PacienteEspecial> pacientesEspeciais;
    private final String[] colunas = {"CPF", "Nome", "Idade","Plano de Saúde", "Histórico"};

    public TabelaPacientesEspeciais(List<PacienteEspecial> pacientesEspeciais) {
        this.pacientesEspeciais = pacientesEspeciais;
    }

    @Override
    public int getRowCount() {
        return pacientesEspeciais.size();
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
        PacienteEspecial pacienteEspecial = pacientesEspeciais.get(rowIndex);
        return switch (columnIndex){
            case 0 -> pacienteEspecial.getCpf();
            case 1 -> pacienteEspecial.getNome();
            case 2 -> pacienteEspecial.getIdade();
            case 3 ->  pacienteEspecial.getPlanoDeSaude().getCodigo();
            case 4 -> "Ver";
            default -> null;
        };
    }
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        PacienteEspecial pacienteEspecial = pacientesEspeciais.get(rowIndex);
        switch (columnIndex){
            case 0:
                pacienteEspecial.setCpf( (String) aValue );
                break;
            case 1:
                pacienteEspecial.setNome( ((String) aValue).trim() );
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0 || columnIndex == 1;
    }
}


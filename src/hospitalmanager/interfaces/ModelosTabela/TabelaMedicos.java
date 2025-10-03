package hospitalmanager.interfaces.ModelosTabela;

import hospitalmanager.dominio.Medico;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TabelaMedicos extends AbstractTableModel {
    private final List<Medico> medicos;
    private final String[] colunas = {"CRM", "CPF","Nome", "Idade"};

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
            case 2 -> medico.getNome();
            case 3 -> medico.getIdade();
            default -> null;
        };
    }
}

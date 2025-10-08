package hospitalmanager.interfaces.ModelosTabela;

import hospitalmanager.dominio.Consulta;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TabelaConsultas extends AbstractTableModel {

    private final List<Consulta> consultas;
    private final String[] colunas = {"Status","Preço", "Sala", "CRM", "CPF","Opções"};

    public TabelaConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    @Override
    public int getRowCount() {
        return consultas.size();
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
        Consulta consulta = consultas.get(rowIndex);
        return switch (columnIndex){
            case 0 -> consulta.getStatus();
            case 1 -> consulta.getPreco();
            case 2 -> consulta.getSala();
            case 3 -> consulta.getMedico().getCrm();
            case 4 -> consulta.getCpf();
            case 5 -> "Ver";
            default -> null;
        };
    }
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Consulta consulta = consultas.get(rowIndex);
        switch (columnIndex){
            case 0:
                consulta.setStatus((String) aValue);
                break;
            case 2:
                consulta.setSala(((String) aValue).trim());
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0 || columnIndex == 2 || columnIndex == 5;
    }
}


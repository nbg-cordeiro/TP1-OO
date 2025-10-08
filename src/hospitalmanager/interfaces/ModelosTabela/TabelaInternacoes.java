package hospitalmanager.interfaces.ModelosTabela;

import hospitalmanager.dominio.Internacao;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TabelaInternacoes extends AbstractTableModel {

    private final List<Internacao> internacoes;
    private final String[] colunas = {"Leito","CPF", "CRM", "Check In","Check Out", "Preço","Opções"};

    public TabelaInternacoes(List<Internacao> internacoes) {
        this.internacoes = internacoes;
    }

    @Override
    public int getRowCount() {
        return internacoes.size();
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
        Internacao internacao = internacoes.get(rowIndex);
        return switch (columnIndex){
            case 0 -> internacao.getLeito();
            case 1 -> internacao.getCpf();
            case 2 -> internacao.getMedico().getCrm();
            case 3 -> internacao.getCheckIn();
            case 4 -> internacao.getCheckOut();
            case 5 -> internacao.getPreco();
            case 6 -> "Opções";
            default -> null;
        };
    }
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Internacao internacao = internacoes.get(rowIndex);
        if (columnIndex == 0){
            internacao.setLeito(((String) aValue).trim());
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0 || columnIndex == 6;
    }
}


package hospitalmanager.interfaces.ModelosTabela;

import hospitalmanager.dominio.PlanoDeSaude;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TabelaPlanos extends AbstractTableModel {

    private final List<PlanoDeSaude> planos;
    private final String[] colunas = {"Codigo", "Nome", "Desconto em \n Consultas (%)","Desconto em \n Internações (%)"};

    public TabelaPlanos(List<PlanoDeSaude> planos) {
        this.planos = planos;
    }

    @Override
    public int getRowCount() {
        return planos.size();
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
        PlanoDeSaude plano = planos.get(rowIndex);
        return switch (columnIndex){
            case 0 -> plano.getCodigo();
            case 1 -> "    "+plano.getNome();
            case 2 -> plano.getDesConsultas();
            case 3 -> plano.getDesInternacoes();
            default -> null;
        };
    }
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        PlanoDeSaude plano = planos.get(rowIndex);
        switch (columnIndex){
            case 0:
                plano.setCodigo( (String) aValue );
                break;
            case 1:
                plano.setNome( ((String) aValue).trim() );
                break;
            case 2:
                try {
                    plano.setDesConsultas( Double.parseDouble(aValue.toString()) );
                } catch (NumberFormatException e) {
                    System.err.println("Formato invalido.");
                }
                break;
            case 3:
                try {
                    plano.setDesInternacoes( Double.parseDouble(aValue.toString()) );
                } catch (NumberFormatException e) {
                    System.err.println("Formato invalido.");
                }
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0 || columnIndex == 1 || columnIndex == 2 || columnIndex == 3;
    }
}


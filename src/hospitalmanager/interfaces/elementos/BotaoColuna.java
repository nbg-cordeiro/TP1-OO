package hospitalmanager.interfaces.elementos;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;
import java.util.function.Function;

public class BotaoColuna<T> extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
    private final JTable table;
    private final JButton renderButton;
    private final JButton editButton;
    private int currentRow;

    private final Consumer<T> action;

    private final Function<Integer, T> objectProvider;

    public BotaoColuna(JTable tabela, int column, String titulo, Consumer<T> acao, Function<Integer, T> objectProvider) {
        this.table = tabela;
        this.action = acao;
        this.objectProvider = objectProvider;
        this.renderButton = new JButton(titulo);
        this.editButton = new JButton(titulo);
        this.editButton.setFocusPainted(false);
        this.editButton.addActionListener(this);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer(this);
        columnModel.getColumn(column).setCellEditor(this);
    }

    @Override
    public Component getTableCellEditorComponent(JTable tabela, Object value, boolean isSelected, int row, int column) {
        this.currentRow = row;
        return editButton;
    }

    @Override
    public Component getTableCellRendererComponent(JTable tabela, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return renderButton;
    }

    @Override
    public Object getCellEditorValue() {
        return editButton.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
        int modelRow = table.convertRowIndexToModel(this.currentRow);
        T targetObject = this.objectProvider.apply(modelRow);
        if (this.action != null) {
            this.action.accept(targetObject);
        }
    }
}
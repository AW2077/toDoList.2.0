import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.EventObject;

class MyRendererAndEditor implements TableCellRenderer, TableCellEditor {
    private final JButton btn;
    private int row;

    MyRendererAndEditor(JTable table) {
        btn = new JButton("Usuń");
        btn.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.removeRow(row);
        });
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object
            value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        return btn;
    }
    @Override
    public Component getTableCellEditorComponent(JTable table, Object
            value, boolean isSelected, int row, int column)
    {
        this.row = row;
        return btn;
    }
    @Override
    public Object getCellEditorValue() { return true; }
    @Override
    public boolean isCellEditable(EventObject anEvent) { return true; }
    @Override
    public boolean shouldSelectCell(EventObject anEvent) { return true; }
    @Override
    public boolean stopCellEditing() { return true; }
    @Override
    public void cancelCellEditing() {}
    @Override
    public void addCellEditorListener(CellEditorListener l) {}
    @Override
    public void removeCellEditorListener(CellEditorListener l) {}
}
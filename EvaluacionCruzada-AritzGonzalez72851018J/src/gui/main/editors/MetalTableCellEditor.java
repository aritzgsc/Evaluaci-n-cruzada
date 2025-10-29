package gui.main.editors;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import domain.Medal.Metal;

// GUI.14 Edición de la tabla de medallas

public class MetalTableCellEditor extends AbstractCellEditor implements TableCellEditor {
	
	private static final long serialVersionUID = 1L;
	
	JComboBox<Metal> metalComboBox = new JComboBox<Metal>(Metal.values());
	
	@Override
	public Object getCellEditorValue() {
		return metalComboBox.getSelectedItem();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		
		return metalComboBox;
		
	}
	
}

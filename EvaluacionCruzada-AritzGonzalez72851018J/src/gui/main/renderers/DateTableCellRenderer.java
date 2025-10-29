package gui.main.renderers;

import java.awt.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

//GUI.13 Renderer personalizado de un JTable

public class DateTableCellRenderer extends DefaultTableCellRenderer {
	
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		
		JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
		label.setText(formatter.format((LocalDate) value));
		
		return label;
		
	}
	
}

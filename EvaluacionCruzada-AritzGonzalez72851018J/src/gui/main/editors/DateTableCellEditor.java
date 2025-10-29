package gui.main.editors;

import java.awt.Component;
import java.time.LocalDate;
import java.util.GregorianCalendar;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import org.jdatepicker.JDatePicker;

//GUI.14 Edición de la tabla de medallas

public class DateTableCellEditor extends AbstractCellEditor implements TableCellEditor {

	private static final long serialVersionUID = 1L;

	JDatePicker selectorFecha = new JDatePicker();
	
	@Override
	public Object getCellEditorValue() {
		GregorianCalendar calendario = (GregorianCalendar) selectorFecha.getModel().getValue();
		return calendario.toZonedDateTime().toLocalDate();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

		LocalDate currentDate = (LocalDate) value;
		selectorFecha.getModel().setDate(currentDate.getYear(), currentDate.getMonthValue() - 1, currentDate.getDayOfMonth());
		selectorFecha.getModel().setSelected(true);
		
		selectorFecha.addActionListener((e) -> fireEditingStopped());
		
		return selectorFecha;
	}
	
}
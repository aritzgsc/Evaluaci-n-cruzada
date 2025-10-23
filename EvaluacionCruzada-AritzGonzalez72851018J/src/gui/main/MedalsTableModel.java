package gui.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.Medal;
import domain.Medal.Metal;

public class MedalsTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;

	private List<Medal> medallasMostradas = new ArrayList<Medal>();
	private String[] cabeceras = {"Metal", "Fecha", "Disciplina"};
	
	public void updateMedals(List<Medal> nuevasMedallasMostradas) {
		medallasMostradas = nuevasMedallasMostradas;
		fireTableDataChanged();
	}
	
	@Override
	public int getRowCount() {
		return medallasMostradas.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(int index) {
		return cabeceras[index];
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		switch (column) {
		case 0: return medallasMostradas.get(row).getMetal();
		case 1: return medallasMostradas.get(row).getMedalDate();
		case 2: return medallasMostradas.get(row).getDiscipline();

		default: return null; 
		}
	}

	@Override
	public Class<?> getColumnClass(int column) {
		switch (column) {
		case 0: return Metal.class;
		case 1: return LocalDate.class;
		case 2: return String.class;

		default: return null;
		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return true;
	}

	// GUI.14 Editor de la tabla
	
	@Override
	public void setValueAt(Object value, int row, int column) {
		Medal medalla = medallasMostradas.get(row);
		
		if (column == 0) {
			
			medalla.setMetal((Metal) value);
			
		} else if (column == 1) {
			
			medalla.setDate((LocalDate) value);
			
		} else if (column == 2) {
			
			medalla.setDiscipline((String) value);
			
		}
		
	}
	
}

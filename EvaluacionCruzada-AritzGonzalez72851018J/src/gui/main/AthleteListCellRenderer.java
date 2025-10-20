package gui.main;

import java.awt.*;
import javax.swing.*;

import domain.Athlete;

// GUI.6 Creamos la clase AthleteListCellRenderer 

public class AthleteListCellRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		
		JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		
		// Añadimos el atributo apellido a los atletas para poder tenerlo
		
		label.setText(((Athlete) value).getName());
		
		return label;
		
	}

	
	
}

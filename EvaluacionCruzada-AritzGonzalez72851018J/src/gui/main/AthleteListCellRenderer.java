package gui.main;

import java.awt.*;
import javax.swing.*;

import domain.Athlete;
import gui.util.CountryCodes;
import gui.util.IconLoader;
import gui.util.IconLoadingException;

// GUI.6 Creamos la clase AthleteListCellRenderer 

public class AthleteListCellRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 1L;

	String textoFiltro = "";
	
	public void setTextoFiltro(String textoFiltro) {
		this.textoFiltro = textoFiltro;
	}
	
	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		
		JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		
		// Añadimos el atributo apellido a los atletas para poder tenerlo
		
		label.setText(setHighLightedText(((Athlete) value).getName()));			// GUI.16 Llamamos a setHighlightedText cada vez que se actualice algún valor
		
		try {
			label.setIcon(IconLoader.getIcon("/resources/images/flags/" + CountryCodes.COUNTRY_ISO_CODES.get(((Athlete) value).getCountry()).toLowerCase() + ".png"));
		} catch (IconLoadingException e) {
			label.setIcon(new ImageIcon("/resources/images/error.png"));	// Ponemos imagen de error (descargada aparte)
		}
		
		return label;
		
	}

	// GUI.16 Subrayar texto
	
	public String setHighLightedText(String nombreAtleta) {
		
		if (!textoFiltro.isBlank() && nombreAtleta.toLowerCase().contains(textoFiltro.toLowerCase())) {
			
//			String resultado = nombreAtleta.replaceAll("(?i)(" + textoFiltro + ")", "<span style='background-color: yellow;'>$1</span>");		// Se podría hacer con REGEX de esta forma (?i) para decir que sea case-insensitive y el $1 para hacer referencia a lo que capture el primer grupo
            
			// Forma de hacerlo sin utilizar REGEX
			
			String resultado = "";
			String[] partes = nombreAtleta.toLowerCase().split(textoFiltro.toLowerCase());
			
			int contadorLetras = 0;
			
			for (int i = 0; i < partes.length ; i++) {

				String parte = partes[i];
				
				resultado += nombreAtleta.substring(contadorLetras, contadorLetras + parte.length());
				
				contadorLetras += parte.length();
				
				if (contadorLetras + textoFiltro.length() <= nombreAtleta.length()) {
					
					resultado += "<span style='background-color: yellow;'>" + nombreAtleta.substring(contadorLetras, contadorLetras + textoFiltro.length()) + "</span>";
					contadorLetras += textoFiltro.length();
					
				}
				
			}
			
			
            return "<html>" + resultado + "</html>";
            
        } else {
            
        	return nombreAtleta;
        	
        }
		
	}
	
}

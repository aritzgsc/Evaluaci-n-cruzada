package gui.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.text.*;
import java.time.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;

import domain.Athlete;
import domain.Athlete.Genre;


public class AthleteFormPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JFormattedTextField codigoFTF;
	private JTextField nombreTF;
	private ButtonGroup generoBG;
	private JRadioButton botonHombre;
	private JRadioButton botonMujer;
	private JFormattedTextField fechaFTF;
	private JComboBox<String> paisComboBox;
	
	private boolean isEditable = true;
	
	public AthleteFormPanel(List<String> paisesList /*GUI.8 Mejora del formulario*/) {
		
		// GUI.7 Creación inicial de formulario
		
		// Elegimos el layout para el panel principal
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Dibujamos el borde
		
		setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY), "Atleta"));
		
		// Creamos el panel para el código y lo añadimos al panel principal
		
		JPanel panelCodigo = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
		
		add(panelCodigo);
		
		// Creamos y añadimos el código y el textfield formateado
		
		JLabel codigoLabel = new JLabel("Código");
		
		// Creamos el formatter para permitir solamente que los números (normales sin . ni , entre medio) sean escritos
		
		NumberFormat nf0 = NumberFormat.getIntegerInstance();
		nf0.setGroupingUsed(false);
		
		NumberFormatter nf1 = new NumberFormatter(nf0);
		nf1.setValueClass(Integer.class);
		
		codigoFTF = new JFormattedTextField(nf1);
		codigoFTF.setColumns(7);
		
		panelCodigo.add(codigoLabel);
		panelCodigo.add(codigoFTF);
		
		// Creamos el panel para el código y lo añadimos al panel principal
		
		JPanel panelNombre = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
				
		add(panelNombre);
				
		// Creamos y añadimos el código y el textfield formateado
				
		JLabel nombreLabel = new JLabel("Nombre");
		nombreTF = new JTextField(18);
		
		panelNombre.add(nombreLabel);
		panelNombre.add(nombreTF);
		
		// Creamos el panel para los JRadioButtons
		
		JPanel panelGenero = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		
		add(panelGenero);
		
		// Creamos los botones y los metemos a un grupo y al panel
		
		botonHombre = new JRadioButton("Hombre");
		botonMujer = new JRadioButton("Mujer");
		
		panelGenero.add(botonHombre);
		panelGenero.add(botonMujer);
		
		generoBG = new ButtonGroup();
		
		generoBG.add(botonHombre);
		generoBG.add(botonMujer);
		
		// Creamos el panel para el código y lo añadimos al panel principal
		
		JPanel panelFecha = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
				
		add(panelFecha);
				
		// Creamos y añadimos el código y el textfield formateado
				
		JLabel fechaLabel = new JLabel("Nacimiento");
		
		fechaFTF = new JFormattedTextField(new DateFormatter(new SimpleDateFormat("dd/MM/yyyy"))); // No hacemos como antes porque si no no se puede escribir nada
		fechaFTF.setColumns(8);
		
		panelFecha.add(fechaLabel);
		panelFecha.add(fechaFTF);
		
		// Creamos el panel para el código y lo añadimos al panel principal
		
		JPanel panelPais = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
					
		add(panelPais);
						
		// Creamos y añadimos el pais y el combobox
		
//		List<String> paisesList = new ArrayList<String>();							// Comentado por GUI.8
//		
//		paisesList.add("Pais 1");													// Comentado por GUI.8
//		paisesList.add("Pais 2");													// Comentado por GUI.8
//		paisesList.add("Pais 3");													// Comentado por GUI.8
		
		JLabel paisLabel = new JLabel("País");
		paisComboBox = new JComboBox<String>(paisesList.toArray(new String[0]));	// A partir de GUI.8 recibe otra lista (con el mismo nombre)
					
		panelPais.add(paisLabel);
		panelPais.add(paisComboBox);
		
	}
	
	// GUI.8 Creamos metodo que rellena los datos del formulario a partir de un Atleta
	
	public void setAthlete(Athlete atleta) {
		
		codigoFTF.setValue(atleta.getCode());
		
		nombreTF.setText(atleta.getName());
		
		generoBG.clearSelection();
		if (atleta.getGenre() == Genre.MALE) {
			botonHombre.setSelected(true);
		} else {
			botonMujer.setSelected(true);
		}
		
		fechaFTF.setValue(Date.from(atleta.getBirthdate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
		paisComboBox.setSelectedItem(atleta.getCountry());
		
	}
	
	// Creamos el nuevo tipo de excepción
	
	public class FormDataNotValid extends Exception {

		private static final long serialVersionUID = 1L;
		
		public FormDataNotValid(String mensaje) {
			super(mensaje);
		}
		
	}
	
	// Creamos la función getAthlete contemplando todas las excepciones posibles
	
	public Athlete getAthlete() throws FormDataNotValid {
		
		int codigo = 0;
		String nombre = "";
		Genre genero = Genre.FEMALE;
		LocalDate fecha = LocalDate.now();
		String pais = (String) paisComboBox.getSelectedItem();		// No se contemplan posibles excepciones
		
		boolean errores = false;
		
		// Condiciones de funcionamiento correcto (código)
		
		try {
			
			if (!codigoFTF.getText().isBlank()) {
				
				if (codigoFTF.isValid()) {
				
					codigo = (Integer) codigoFTF.getValue();
					
				
				} else {
					
					throw new FormDataNotValid("Se esperaba un código numérico");
					
				}
				
			} else {
				
				throw new FormDataNotValid("El código no puede ser vacío");
				
			}
			
		} catch (FormDataNotValid e) {
			
			System.err.println(e.getMessage());
			errores = true;
			
		}
		
		// Condiciones de funcionamiento correcto (nombre)
		
		try {
			
			if (!nombreTF.getText().isBlank()) {
				
				nombre = nombreTF.getText();
				
			} else {
				
				throw new FormDataNotValid("El nombre no puede ser vacío");
				
			}
			
		} catch (FormDataNotValid e) {
			
			System.err.println(e.getMessage());
			errores = true;
			
		}
		
		// Condiciones de funcionamiento correcto (genero)
		
		try {
			
			if (botonHombre.isSelected() || botonMujer.isSelected()) {
				
				genero = botonHombre.isSelected()? Genre.MALE : Genre.FEMALE;
				
			} else {
				
				throw new FormDataNotValid("Se debe seleccionar un género");
				
			}
			
		} catch (FormDataNotValid e) {
			
			System.err.println(e.getMessage());
			errores = true;
			
		}
		
		// Condiciones de funcionamiento correcto (fecha)
		
		try {
			
			if (!fechaFTF.getText().isBlank()) {
				
				if (fechaFTF.isValid()) {
				
					fecha = LocalDate.ofInstant(((Date) fechaFTF.getValue()).toInstant(), ZoneId.systemDefault());
				
				} else {
					
					throw new FormDataNotValid("La fecha no tiene el formato esperado (dd/MM/yyyy)");
					
				}
				
			} else {
				
				throw new FormDataNotValid("La fecha no puede ser vacía");
				
			}
			
		} catch (FormDataNotValid e) {

			System.err.println(e.getMessage());
			errores = true;
			
		}
		
		if (errores) {
			
			return null;
			
		} else {
			
			return new Athlete(codigo, nombre, genero, pais, fecha);
			
		}
		
	}
	
	public void setEditable(boolean editable) {
		
		isEditable = editable;
		
		codigoFTF.setEditable(editable);
		nombreTF.setEditable(editable);
		botonHombre.setEnabled(editable);
		botonMujer.setEnabled(editable);
		fechaFTF.setEditable(editable);
		paisComboBox.setEnabled(editable);
		
	}
	
	public boolean isEditable() {
		return isEditable;
	}
	
	// Prueba de panel y funcionalidades
	
	public static void main(String[] args) {
		
		JFrame ventanaPrueba = new JFrame();
		
		ventanaPrueba.setTitle("Ventana de prueba");
		ventanaPrueba.setSize(480, 300);
		ventanaPrueba.setLocationRelativeTo(null);
		
		List<String> paisesList = new ArrayList<String>();							
	
		paisesList.add("Pais 1");													
		paisesList.add("Pais 2");													
		paisesList.add("Pais 3");													
		
		AthleteFormPanel panelFormulario = new AthleteFormPanel(paisesList);
		
		ventanaPrueba.add(panelFormulario);
		
		JPanel panelBotones = new JPanel();
		
		JButton obtenerDatos = new JButton("Obtener datos");
		obtenerDatos.addActionListener((e) -> {
			try {
				System.out.println(panelFormulario.getAthlete());
			} catch (FormDataNotValid e1) {
				e1.printStackTrace();
			}
		});
		
		JButton desHabilitar = new JButton("Habilitar/Deshabilitar");
		desHabilitar.addActionListener((e) -> panelFormulario.setEditable(!panelFormulario.isEditable()));
		
		panelBotones.add(obtenerDatos);
		panelBotones.add(desHabilitar);
		
		ventanaPrueba.add(panelBotones, BorderLayout.SOUTH);
		
		ventanaPrueba.setVisible(true);
		
	}
	
}

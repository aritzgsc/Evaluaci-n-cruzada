package gui.main.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.text.*;
import java.time.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;

import domain.Athlete;
import domain.Athlete.Genre;
import gui.util.CountryCodes;
import gui.util.IconLoader;
import gui.util.IconLoadingException;


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
	
	private ArrayList<String> errores;
	
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
		paisComboBox.setSelectedIndex(-1);		
		
		paisComboBox.setRenderer(new DefaultListCellRenderer() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				
				JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				
				if (value == null) {
					return label;
				}
				
				String paisCodigoISO = "";
				
				if (CountryCodes.COUNTRY_ISO_CODES.containsKey((String) value)) {
					paisCodigoISO = CountryCodes.COUNTRY_ISO_CODES.get((String) value);
				} else {
					try {
						label.setIcon(IconLoader.getIcon("/images/error.png"));
					} catch (IconLoadingException e) {
						e.printStackTrace();
					}
					return label;
				}
				
				try {
					label.setIcon(IconLoader.getIcon("/images/flags/" + paisCodigoISO.toLowerCase() + ".png"));
				} catch (IconLoadingException e) {
					e.printStackTrace();
				}
				
				return label;
				
			}
			
		});
		
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
	
	// Creamos la función getAthlete contemplando todas las excepciones posibles
	// GUI.11 NO modifico esto aqui porque rompería la forma en que funciona el NewAthleteDialog, si introdujera un atleta mal se borraría todo, cosa que no quiero que ocurra
	// 		  por ello modificaré el ListSelectionListener anterior para que no de error y haré un nuevo método clearSelection al que llamaré tras borrar atletas
	
	public Athlete getAthlete() throws FormDataNotValid {
		
		int codigo = 0;
		String nombre = "";
		Genre genero = Genre.FEMALE;
		LocalDate fecha = LocalDate.now();
		String pais = "";
		
		errores = new ArrayList<String>();
		
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
			
//			System.err.println(e.getMessage());		// Muestra por consola
			errores.add(e.getMessage());		// Actualiza el mensaje de error
			
		}
		
		// Condiciones de funcionamiento correcto (nombre)
		
		try {
			
			if (!nombreTF.getText().isBlank()) {
				
				nombre = nombreTF.getText();
				
			} else {
				
				throw new FormDataNotValid("El nombre no puede ser vacío");
				
			}
			
		} catch (FormDataNotValid e) {
			
//			System.err.println(e.getMessage());		// Muestra por consola
			errores.add(e.getMessage());		// Actualiza el mensaje de error
			
		}
		
		// Condiciones de funcionamiento correcto (genero)
		
		try {
			
			if (botonHombre.isSelected() || botonMujer.isSelected()) {
				
				genero = botonHombre.isSelected()? Genre.MALE : Genre.FEMALE;
				
			} else {
				
				throw new FormDataNotValid("Se debe seleccionar un género");
				
			}
			
		} catch (FormDataNotValid e) {
			
//			System.err.println(e.getMessage());		// Muestra por consola
			errores.add(e.getMessage());		// Actualiza el mensaje de error
			
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

//			System.err.println(e.getMessage());		// Muestra por consola
			errores.add(e.getMessage());		// Actualiza el mensaje de error
			
		}
		
		try {
			
			if (paisComboBox.getSelectedIndex() != -1) {
				
				pais = (String) paisComboBox.getSelectedItem();
				
			} else {
				
				throw new FormDataNotValid("Debe seleccionar un país");
				
			}
			
		} catch (Exception e) {

//			System.err.println(e.getMessage());		// Muestra por consola
			errores.add(e.getMessage());		// Actualiza el mensaje de error
			
		}
		
		if (!errores.isEmpty()) {
			
			throw new FormDataNotValid("");
			
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
	
	public ArrayList<String> getErrores() {
		return errores;
	}
	
	// GUI.11 Para evitar romper algo de NewAthleteDialog creamos esta función y la llamamos desde la MainWindow tras eliminar atletas
	
	public void clearSelection() {
		codigoFTF.setText("");
		nombreTF.setText("");
		generoBG.clearSelection();
		fechaFTF.setText("");
		paisComboBox.setSelectedIndex(-1);
	}
	
	// Prueba de panel y funcionalidades
	
	public static void main(String[] args) {
		
		JFrame ventanaPrueba = new JFrame();
		
		ventanaPrueba.setTitle("Ventana de prueba");
		ventanaPrueba.setSize(480, 320);
		ventanaPrueba.setLocationRelativeTo(null);
		
		List<String> paisesList = new ArrayList<String>();							
	
		paisesList.add("Pais 1");													
		paisesList.add("Pais 2");													
		paisesList.add("Pais 3");													
		paisesList.add("Pais 4");													
		paisesList.add("Pais 5");													
		
		AthleteFormPanel panelFormulario = new AthleteFormPanel(paisesList);
		
		ventanaPrueba.add(panelFormulario);
		
		JPanel panelBotones = new JPanel();
		
		JButton obtenerDatos = new JButton("Obtener datos");
		obtenerDatos.addActionListener((e) -> {
			try {
				System.out.println(panelFormulario.getAthlete());
			} catch (FormDataNotValid e1) {
				JPanel panelErrores = new JPanel();
				panelErrores.setLayout(new BoxLayout(panelErrores, BoxLayout.Y_AXIS));
				for (String error : panelFormulario.getErrores()) {
					JLabel errorL = new JLabel("- " + error);
					errorL.setBorder(new EmptyBorder(5, 5, 5, 5));
					panelErrores.add(errorL);
				}
				JOptionPane.showMessageDialog(ventanaPrueba, panelErrores, "Error al crear atleta", JOptionPane.ERROR_MESSAGE);
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

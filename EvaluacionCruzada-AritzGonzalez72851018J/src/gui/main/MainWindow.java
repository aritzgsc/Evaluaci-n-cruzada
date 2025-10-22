package gui.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;

import java.time.*;
import java.time.format.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import org.jdatepicker.JDatePicker;

import domain.Athlete;
import domain.Athlete.Genre;
import domain.Medal;
import domain.Medal.Metal;

// GUI.1 Creación de la ventana principal

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private static MainWindow mainWindow;
	
	private static List<Athlete> listaAtletas = new ArrayList<Athlete>();					// Sacamos la listaAtletas para tener acceso desde fuera del constructor
	private static List<String> listaPaises = new ArrayList<String>();						// Sacamos la listaPaises para tener acceso desde fuera del constructor
	
	public MainWindow() {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("JJ.OO París 2024");
		setSize(640, 480);
		setLocationRelativeTo(null);
		
		// GUI.2 Creación de JList y JTabbedPane y añadir a la ventana
		
		// Creación de atletas
		
//		String[] arrayAtletas = new String[30];								// COMENTADO POR GUI.5
//																			// COMENTADO POR GUI.5
//		for (int i = 0; i < 30; i++) {										// COMENTADO POR GUI.5
//			arrayAtletas[i] = "Atleta " + i;								// COMENTADO POR GUI.5
//		}
		
		// Creación de JList
		
//		JList<String> listaAtletas = new JList<String>(arrayAtletas);		// COMENTADO POR GUI.5
		
		// Creación del JScrollPane donde añadiremos la JList
		
//		JScrollPane panelLista = new JScrollPane(listaAtletas);				// COMENTADO POR GUI.5
//		panelLista.setPreferredSize(new Dimension(200, getHeight()));		// COMENTADO POR GUI.5
		
		// Creación del JTabbedPane donde añadiremos los posteriores paneles
		
		JTabbedPane panelTabs = new JTabbedPane();
		
		// Creación de los paneles a añadir
		
		JPanel panelDatos = new JPanel(new BorderLayout());					// Ponemos BorderLayout por el GUI.9
		JPanel panelMedallas = new JPanel(new BorderLayout());
		
		// Añadimos los paneles al JTabbedPane
		
		panelTabs.addTab("Datos", panelDatos);
		panelTabs.addTab("Medallas", panelMedallas);
		
//		add(panelLista, BorderLayout.WEST);									// COMENTADO POR GUI.5
		add(panelTabs, BorderLayout.CENTER);
		
		// GUI.3 Creación de menú de aplicación
		
		// Creamos la barra de menú de aplicación y la añadimos a la ventana
		
		JMenuBar barraMenu = new JMenuBar();
		setJMenuBar(barraMenu);
		
		// Creamos el menú fichero y lo añadimos a la barra menú
		
		JMenu menuFichero = new JMenu("Fichero");
		menuFichero.setMnemonic('F');
		
		barraMenu.add(menuFichero);
		
		// Creamos los items del menú fichero y los añadimos al menú
		
		JMenuItem nuevoAtleta = new JMenuItem("Nuevo Atleta");
		JMenuItem importar = new JMenuItem("Importar");
		JMenuItem exportar = new JMenuItem("Exportar");
		JMenuItem salir = new JMenuItem("Salir");
		
		nuevoAtleta.setMnemonic('N');
		importar.setMnemonic('I');
		exportar.setMnemonic('E');
		salir.setMnemonic('S');
		
		menuFichero.add(nuevoAtleta);
		menuFichero.add(importar);
		menuFichero.add(exportar);
		menuFichero.add(salir);
		
		// GUI.4 Gestión cierre de ventana
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		// Añadimos el WindowListener que implemente el JOptionPane para realizar la pregunta
		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				
				int respuesta = JOptionPane.showConfirmDialog(null, new JLabel("¿Seguro que desea salir?"), "Salir", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (respuesta == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
				
			}
		
		});
		
		// Añadimos la funcionalidad del botón salir
		
		salir.addActionListener((e) -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
		
		// GUI.5 Mejorando el modelo de datos JList
		
		// Creamos la lista de atletas y la rellenamos
		
		Athlete atleta1 = new Athlete(1111111, "Apellido 1, Atleta 1", Genre.FEMALE, "Pais 1", LocalDate.of(2006, 1, 11));
		Athlete atleta2 = new Athlete(2222222, "Apellido 2, Atleta 2", Genre.MALE, "Pais 2", LocalDate.of(2000, 1, 1));
		Athlete atleta3 = new Athlete(3333333, "Apellido 3, Atleta 3", Genre.MALE, "Pais 2", LocalDate.of(2000, 1, 1));
		Athlete atleta4 = new Athlete(4444444, "Apellido 4, Atleta 4", Genre.MALE, "Pais 3", LocalDate.of(2000, 1, 1));
		Athlete atleta5 = new Athlete(5555555, "Apellido 5, Atleta 5", Genre.MALE, "Pais 3", LocalDate.of(2000, 1, 1));
		
		listaAtletas.add(atleta1);
		listaAtletas.add(atleta2);
		listaAtletas.add(atleta3);
		listaAtletas.add(atleta4);
		listaAtletas.add(atleta5);
		
		// Creamos el modelo para la JList
		
		DefaultListModel<Athlete> modeloListaAtletas = new DefaultListModel<Athlete>();
		
		for (Athlete athlete : listaAtletas) {
			modeloListaAtletas.addElement(athlete);
		}
		
		// Creamos JList y le pasamos la lista creada
		
		JList<Athlete> jListAtletas = new JList<Athlete>(modeloListaAtletas);
		
		// Creamos el contenedor JScrollPane y metemos la lista de atletas (swing) y metemos el panel a la izquierda de la ventana
		
		JScrollPane sPanelAtletas = new JScrollPane(jListAtletas);
		sPanelAtletas.setPreferredSize(new Dimension(200, getHeight()));
		
		// Añadimos el ScrollPane a la ventana principal de nuevo porque hemos comentado toda esta parte del ejercicio GUI.2
		
//		add(panelAtletas, BorderLayout.WEST);				// Comentado por GUI.11
		
		// GUI.6 Creación de renderer para la JList
		
		jListAtletas.setCellRenderer(new AthleteListCellRenderer());
		
		// GUI.7 y 8 en AthleteFormPanel (prueba de funcionamiento en esa misma clase)
		
		// GUI.9 Añadir el formulario creado a la ventana principal
		
		for (Athlete atleta : listaAtletas) {
			if (!listaPaises.contains(atleta.getCountry())) {
				listaPaises.add(atleta.getCountry());
			}
		}
		
		AthleteFormPanel formulario = new AthleteFormPanel(listaPaises);
		formulario.setEditable(false);
		
		panelDatos.add(formulario);
		
		// Añadir datos al formulario dependiendo del atleta seleccionado
		
		jListAtletas.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (jListAtletas.getSelectedValue() != null) {
					formulario.setAthlete(jListAtletas.getSelectedValue());
				}
			}
			
		});
		
		// GUI.10 Añadir funcionalidad a item de menú nuevo atleta
		
		ActionListener anadirAtleta = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int respuesta = NewAthleteDialog.showDialog(mainWindow);
				
				if (respuesta == JOptionPane.OK_OPTION) {
					modeloListaAtletas.addElement(NewAthleteDialog.getAthlete());		// Se añade el último atleta creado correctamente
				}
			}
		};
		
		nuevoAtleta.addActionListener(anadirAtleta);
		
		// GUI.11 Añadir funcionalidades añadir y eliminar atletas
		
		// Reorganizamos la parte izquierda de la ventana y creamos los nuevos botones
		
		JPanel panelAtletasFunc = new JPanel(new BorderLayout());
		
		JPanel panelBotones = new JPanel();
		
		JButton botonAnadir = new JButton("Añadir");
		botonAnadir.addActionListener(anadirAtleta);
		
		JButton botonEliminar = new JButton("Eliminar");
		botonEliminar.setEnabled(false);
		botonEliminar.addActionListener((e) -> {
			List<Athlete> atletasSeleccionados = jListAtletas.getSelectedValuesList();
			
			int respuesta = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres eliminar " + atletasSeleccionados.size() + " atletas seleccionados?", "Eliminar atletas", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			
			if (respuesta == JOptionPane.YES_OPTION) {
				for (Athlete atleta : atletasSeleccionados) {
					modeloListaAtletas.removeElement(atleta);
				}
			}
			formulario.clearSelection();
		});
		
		jListAtletas.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (jListAtletas.getSelectedValuesList().size() > 0) {
					botonEliminar.setEnabled(true);
				} else {
					botonEliminar.setEnabled(false);
				}
			}
			
		});
		
		panelBotones.add(botonAnadir);
		panelBotones.add(botonEliminar);		
		
		panelAtletasFunc.add(sPanelAtletas, BorderLayout.CENTER);
		panelAtletasFunc.add(panelBotones, BorderLayout.SOUTH);
		
		add(panelAtletasFunc, BorderLayout.WEST);
		
		// GUI.12 JTable de medallas ganadas por cada atleta seleccionado
		
		Map<Athlete, List<Medal>> medallasPorAtleta = new HashMap<Athlete, List<Medal>>();
		
		List<Medal> medallasAtleta1 = new ArrayList<Medal>(Arrays.asList(
				new Medal(Metal.BRONZE, LocalDate.of(2024, 5, 20), atleta1, "Disciplina1"),
				new Medal(Metal.SILVER, LocalDate.of(2023, 5, 20), atleta1, "Disciplina1"),
				new Medal(Metal.BRONZE, LocalDate.of(2022, 5, 20), atleta1, "Disciplina1")
				));
		
		List<Medal> medallasAtleta2 = new ArrayList<Medal>(Arrays.asList(
				new Medal(Metal.GOLD, LocalDate.of(2024, 5, 20), atleta2, "Disciplina1"),
				new Medal(Metal.GOLD, LocalDate.of(2023, 5, 20), atleta2, "Disciplina1"),
				new Medal(Metal.SILVER, LocalDate.of(2022, 5, 20), atleta2, "Disciplina1")
			)) ;
		
		List<Medal> medallasAtleta3 = new ArrayList<Medal>(Arrays.asList(
				new Medal(Metal.SILVER, LocalDate.of(2024, 5, 20), atleta3, "Disciplina1"),
				new Medal(Metal.SILVER, LocalDate.of(2023, 5, 20), atleta3, "Disciplina2"),
				new Medal(Metal.BRONZE, LocalDate.of(2022, 5, 20), atleta3, "Disciplina2")
			)) ;
		
		List<Medal> medallasAtleta4 = new ArrayList<Medal>(Arrays.asList(
				new Medal(Metal.BRONZE, LocalDate.of(2024, 5, 20), atleta1, "Disciplina2"),
				new Medal(Metal.SILVER, LocalDate.of(2023, 5, 20), atleta1, "Disciplina3")
			)) ;
		
		List<Medal> medallasAtleta5 = new ArrayList<Medal>();
		
		medallasPorAtleta.put(atleta1, medallasAtleta1);
		medallasPorAtleta.put(atleta2, medallasAtleta2);
		medallasPorAtleta.put(atleta3, medallasAtleta3);
		medallasPorAtleta.put(atleta4, medallasAtleta4);
		medallasPorAtleta.put(atleta5, medallasAtleta5);
		
		class MedalsTableModel extends AbstractTableModel {
			
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
		
		MedalsTableModel modeloTablaMedallas = new MedalsTableModel();
		
		JTable tablaMedallas = new JTable(modeloTablaMedallas);
		
		tablaMedallas.getColumn("Metal").setMaxWidth(75);
		tablaMedallas.getColumn("Fecha").setMaxWidth(75);
		
		jListAtletas.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (jListAtletas.getSelectedValue() != null) {
					if (medallasPorAtleta.get(jListAtletas.getSelectedValue()) != null) {
						modeloTablaMedallas.updateMedals(medallasPorAtleta.get(jListAtletas.getSelectedValue()));
					}
				}
			}
			
		});
		
		JScrollPane panelTablaMedallas = new JScrollPane(tablaMedallas);
		
		panelMedallas.add(panelTablaMedallas);
		
		// GUI.13 Renderer personalizado de un JTable
		
		class MedalTableCellRenderer extends DefaultTableCellRenderer {

			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				
				JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				
				if ((Metal) value == Metal.GOLD) {
					label.setForeground(new Color(0xFFD700));
				} else if ((Metal) value == Metal.SILVER) {
					label.setForeground(new Color(0xC0C0C0));
				} else if ((Metal) value == Metal.BRONZE){
					label.setForeground(new Color(0xCD7F32));
				} else {
					label.setForeground(Color.RED);
				}
				
				label.setFont(getFont().deriveFont(Font.BOLD));
				
				return label;
				
			}
			
		}
		
		class DateTableCellRenderer extends DefaultTableCellRenderer {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				
				JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
				label.setText(formatter.format((LocalDate) value));
				
				return label;
				
			}
			
		}
		
		tablaMedallas.getColumn("Metal").setCellRenderer(new MedalTableCellRenderer());
		tablaMedallas.setDefaultRenderer(LocalDate.class, new DateTableCellRenderer());
		
		// GUI.14 Edición de la tabla de medallas
		
		class MetalTableCellEditor extends AbstractCellEditor implements TableCellEditor {
			
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
		
		class DateTableCellEditor extends AbstractCellEditor implements TableCellEditor {

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
		
		tablaMedallas.getColumn("Metal").setCellEditor(new MetalTableCellEditor());
		tablaMedallas.getColumn("Fecha").setCellEditor(new DateTableCellEditor());
		
		setVisible(true);
		
	}
	
	public static List<Athlete> getListaAtletas() {
		return listaAtletas;
	}
	
	public static List<String> getListaPaises() {
		return listaPaises;
	}
	
	public static MainWindow getMainWindow() {
		return mainWindow;
	}
	
}

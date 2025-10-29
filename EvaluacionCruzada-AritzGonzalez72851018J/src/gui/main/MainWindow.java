package gui.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;

import java.time.*;
import java.util.*;
import java.util.function.Predicate;

import javax.swing.*;
import javax.swing.event.*;

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
//		JPanel panelMedallas = new JPanel(new BorderLayout());				// COMENTADO POR GUI.17
		
		// Añadimos los paneles al JTabbedPane
		
		panelTabs.addTab("Datos", panelDatos);
//		panelTabs.addTab("Medallas", panelMedallas);						// COMENTADO POR GUI.17
		
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
		menuFichero.addSeparator();		
		menuFichero.add(importar);
		menuFichero.add(exportar);
		menuFichero.addSeparator();
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
		Athlete atleta3 = new Athlete(3333333, "Apellido 3, Atleta 3", Genre.MALE, "Pais 3", LocalDate.of(2000, 1, 1));
		Athlete atleta4 = new Athlete(4444444, "Apellido 4, Atleta 4", Genre.MALE, "Pais 4", LocalDate.of(2000, 1, 1));
		Athlete atleta5 = new Athlete(5555555, "Apellido 5, Atleta 5", Genre.MALE, "Pais 5", LocalDate.of(2000, 1, 1));
		
		listaAtletas.add(atleta1);
		listaAtletas.add(atleta2);
		listaAtletas.add(atleta3);
		listaAtletas.add(atleta4);
		listaAtletas.add(atleta5);
		
		// Creamos el modelo para la JList
		
//		DefaultListModel<Athlete> modeloListaAtletas = new DefaultListModel<Athlete>();						// Comentado por GUI.16 (Cambiamos el modelo)
		
		FilterListModel<Athlete> modeloListaAtletas = new FilterListModel<Athlete>();						// Nuevo Modelo GUI.16 - Le ponemos el mismo nombre para no tener que modificar los listeners	
		
		for (Athlete athlete : listaAtletas) {																// Comentado por GUI.16 (Cambiamos el modelo)
			modeloListaAtletas.addElement(athlete);															// Comentado por GUI.16 (Cambiamos el modelo)
		}																									// Comentado por GUI.16 (Cambiamos el modelo)
			
//		FilterListModel<Athlete> modeloListaAtletas = new FilterListModel<Athlete>(listaAtletas);	// Nuevo Modelo GUI.16 - Le ponemos el mismo nombre para no tener que modificar los listeners
		
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
				} else {
					formulario.clearSelection();
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
			
			int respuesta = JOptionPane.showConfirmDialog(mainWindow, "¿Estás seguro de que quieres eliminar " + atletasSeleccionados.size() + " atletas seleccionados?", "Eliminar atletas", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if (respuesta == JOptionPane.YES_OPTION) {
				for (Athlete atleta : atletasSeleccionados) {
					modeloListaAtletas.removeElement(atleta);
				}
			}
			
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
				new Medal(Metal.BRONZE, LocalDate.of(2024, 5, 20), atleta1, "Disciplina 1"),
				new Medal(Metal.SILVER, LocalDate.of(2023, 5, 20), atleta1, "Disciplina 1"),
				new Medal(Metal.BRONZE, LocalDate.of(2022, 5, 20), atleta1, "Disciplina 1")
				));
		
		List<Medal> medallasAtleta2 = new ArrayList<Medal>(Arrays.asList(
				new Medal(Metal.GOLD, LocalDate.of(2024, 5, 20), atleta2, "Disciplina 1"),
				new Medal(Metal.GOLD, LocalDate.of(2023, 5, 20), atleta2, "Disciplina 1"),
				new Medal(Metal.SILVER, LocalDate.of(2022, 5, 20), atleta2, "Disciplina 1")
			)) ;
		
		List<Medal> medallasAtleta3 = new ArrayList<Medal>(Arrays.asList(
				new Medal(Metal.SILVER, LocalDate.of(2024, 5, 20), atleta3, "Disciplina 1"),
				new Medal(Metal.SILVER, LocalDate.of(2023, 5, 20), atleta3, "Disciplina 2"),
				new Medal(Metal.BRONZE, LocalDate.of(2022, 5, 20), atleta3, "Disciplina 2")
			)) ;
		
		List<Medal> medallasAtleta4 = new ArrayList<Medal>(Arrays.asList(
				new Medal(Metal.BRONZE, LocalDate.of(2024, 5, 20), atleta1, "Disciplina 2"),
				new Medal(Metal.SILVER, LocalDate.of(2023, 5, 20), atleta1, "Disciplina 3")
			)) ;
		
		List<Medal> medallasAtleta5 = new ArrayList<Medal>();
		
		medallasPorAtleta.put(atleta1, medallasAtleta1);
		medallasPorAtleta.put(atleta2, medallasAtleta2);
		medallasPorAtleta.put(atleta3, medallasAtleta3);
		medallasPorAtleta.put(atleta4, medallasAtleta4);
		medallasPorAtleta.put(atleta5, medallasAtleta5);
		
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
					} else {
						medallasPorAtleta.put(jListAtletas.getSelectedValue(), new ArrayList<Medal>());
						modeloTablaMedallas.updateMedals(medallasPorAtleta.get(jListAtletas.getSelectedValue()));
					}
				} else {
					modeloTablaMedallas.updateMedals(new ArrayList<Medal>());
				}
			}
			
		});
		
		JScrollPane panelTablaMedallas = new JScrollPane(tablaMedallas);
		
//		panelMedallas.add(panelTablaMedallas);							// COMENTADO POR GUI.17
	
		// GUI.13 Añadir TableCellRenderers a la tabla
		
		tablaMedallas.getColumn("Metal").setCellRenderer(new MedalTableCellRenderer());
		tablaMedallas.setDefaultRenderer(LocalDate.class, new DateTableCellRenderer());
		
		// GUI.14 Añadir editores a tabla de medallas
		
		tablaMedallas.getColumn("Metal").setCellEditor(new MetalTableCellEditor());
		tablaMedallas.getColumn("Fecha").setCellEditor(new DateTableCellEditor());
		
		// GUI.15 Añadiendo algunos eventos de teclado
		
		jListAtletas.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_DELETE && jListAtletas.getSelectedIndices().length > 0) {
					
					List<Athlete> atletasSeleccionados = jListAtletas.getSelectedValuesList();
					
					int respuesta = JOptionPane.showConfirmDialog(mainWindow, "¿Estás seguro de que quieres eliminar " + atletasSeleccionados.size() + " atletas seleccionados?", "Eliminar atletas", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					
					if (respuesta == JOptionPane.YES_OPTION) {
						for (Athlete atleta : atletasSeleccionados) {
							modeloListaAtletas.removeElement(atleta);
						}
					}
					
				}
				
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_M && jListAtletas.getSelectedValue() != null) {
					
					panelTabs.setSelectedIndex(1);
					
					List<Medal> medallasAtletaSeleccionado = medallasPorAtleta.get(jListAtletas.getSelectedValue());
					medallasAtletaSeleccionado.add(new Medal(Metal.BRONZE, LocalDate.now(), jListAtletas.getSelectedValue(), "Default discipline"));		// Añadimos una medalla por defecto
					
					modeloTablaMedallas.updateMedals(medallasAtletaSeleccionado);
					
					tablaMedallas.setRowSelectionInterval(medallasAtletaSeleccionado.size() - 1, medallasAtletaSeleccionado.size() - 1);
					tablaMedallas.editCellAt(medallasAtletaSeleccionado.size() - 1, 0);
					
				}
				
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_A) {
					
					int respuesta = NewAthleteDialog.showDialog(mainWindow);
					
					if (respuesta == JOptionPane.OK_OPTION) {
						modeloListaAtletas.addElement(NewAthleteDialog.getAthlete());		// Se añade el último atleta creado correctamente
					}
					
				}
				
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					
					jListAtletas.clearSelection();
					
				}
				
			}
			
		});
		
		// GUI.16 Aplicando un filtro para la lista
		
		JTextField filtro = new JTextField();
		
		panelAtletasFunc.add(filtro, BorderLayout.NORTH);
		
		// Comentamos arriba el modelo viejo y creamos el modelo nuevo arriba también para no tener que reescribir todos los listeners para el nuevo modelo (no podemos modificarlos con jListAtletas.getModel()... porque no todos los ListModels tienen los mismos métodos)
		
		filtro.getDocument().addDocumentListener(new DocumentListener() {
			
			public void filtrar() {
				String textoFiltro = filtro.getText().toLowerCase();
				Predicate<Athlete> predicado = atleta -> atleta.getName().toLowerCase().contains(textoFiltro);			// Test
//				Predicate<Athlete> predicado = new Predicate<Athlete>() {
//					
//					@Override
//					public boolean test(Athlete atleta) {
//						return atleta.getName().toLowerCase().contains(textoFiltro);
//					}
//					
//				};
				modeloListaAtletas.setFilter(predicado);
				((AthleteListCellRenderer) jListAtletas.getCellRenderer()).setTextoFiltro(textoFiltro);
			}
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				filtrar();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				filtrar();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				filtrar();
			}
			
		});
		
		// GUI.17 Eventos de ratón para la tabla
		
		// Comentamos arriba toda la configuración del panel medallas y creamos un nuevo JSplitPane para sustituirlo
		
		JSplitPane panelMedallasDividido = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		panelMedallasDividido.setDividerSize(1);
		panelMedallasDividido.setDividerLocation((int) (getHeight() / 2.25));
		
		// Creamos un nuevo JEditorPane que pondremos en la parte de abajo del nuevo panel (que recibe texto en formato HTML)
		
		JEditorPane panelTextoDisciplina = new JEditorPane("text/html", "");
		
		// Creamos un mapa provisional de las disciplinas y sus descripciones
		
		Map<String, String> textoPorDisciplina = new HashMap<String, String>();
		
		textoPorDisciplina.put("Disciplina 1", "<html><h1>Descripción de Disciplina 1\n</h1>Descripción de disciplina 1</html>");
		textoPorDisciplina.put("Disciplina 2", "<html><h1>Descripción de Disciplina 2\n</h1>Descripción de disciplina 2</html>");
		textoPorDisciplina.put("Disciplina 3", "<html><h1>Descripción de Disciplina 3\n</h1>Descripción de disciplina 3</html>");
		
		tablaMedallas.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if (tablaMedallas.getSelectedColumn() == tablaMedallas.getColumn("Disciplina").getModelIndex()) {
					
					if (textoPorDisciplina.get(tablaMedallas.getValueAt(tablaMedallas.getSelectedRow(), tablaMedallas.getSelectedColumn())) != null) {
					
						panelTextoDisciplina.setText(textoPorDisciplina.get(tablaMedallas.getValueAt(tablaMedallas.getSelectedRow(), tablaMedallas.getSelectedColumn())));
					} else {
						
						panelTextoDisciplina.setText("<html><h1>No se ha podido encontrar el contenido</h1></html>");
						
					}
				} else {
					
					panelTextoDisciplina.setText("");
					
				}
				
			}
			
		});
		
		panelMedallasDividido.setTopComponent(panelTablaMedallas);
		panelMedallasDividido.setBottomComponent(panelTextoDisciplina);
		
		panelTabs.addTab("Medallas", panelMedallasDividido);
		
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

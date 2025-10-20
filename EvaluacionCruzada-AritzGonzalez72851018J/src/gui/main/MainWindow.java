package gui.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;

import javax.swing.*;

import domain.Athlete;
import domain.Athlete.Genre;

// GUI.1 Creación de la ventana principal

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

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
		
		JPanel panelDatos = new JPanel();
		JPanel panelMedallas = new JPanel();
		
		// Añadimos los paneles al JTabbedPane
		
		panelTabs.addTab("Datos", panelDatos);
		panelTabs.addTab("Medallas", panelMedallas);
		
//		add(panelLista, BorderLayout.WEST);									// COMENTADO POR GUI.5
		add(panelTabs);
		
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
		
		List<Athlete> listaAtletas = new ArrayList<Athlete>();
		
		listaAtletas.add(new Athlete(1111111, "Atleta 1", Genre.MALE, "País 1", LocalDate.of(2000, 1, 1)));
		listaAtletas.add(new Athlete(2222222, "Atleta 2", Genre.MALE, "País 2", LocalDate.of(2000, 1, 1)));
		listaAtletas.add(new Athlete(3333333, "Atleta 3", Genre.MALE, "País 2", LocalDate.of(2000, 1, 1)));
		listaAtletas.add(new Athlete(4444444, "Atleta 4", Genre.MALE, "País 3", LocalDate.of(2000, 1, 1)));
		listaAtletas.add(new Athlete(5555555, "Atleta 5", Genre.MALE, "País 3", LocalDate.of(2000, 1, 1)));
		
		// Creamos el modelo para la JList
		
		DefaultListModel<Athlete> modeloListaAtletas = new DefaultListModel<Athlete>();
		
		for (Athlete athlete : listaAtletas) {
			modeloListaAtletas.addElement(athlete);
		}
		
		// Creamos JList y le pasamos la lista creada
		
		JList<Athlete> jListAtletas = new JList<Athlete>(modeloListaAtletas);
		
		// Creamos el contenedor JScrollPane y metemos la lista de atletas (swing) y metemos el panel a la izquierda de la ventana
		
		JScrollPane panelAtletas = new JScrollPane(jListAtletas);
		panelAtletas.setPreferredSize(new Dimension(200, getHeight()));
		
		// Añadimos el ScrollPane a la ventana principal de nuevo porque hemos comentado toda esta parte del ejercicio GUI.2
	
		add(panelAtletas, BorderLayout.WEST);
		
		setVisible(true);
		
	}
	
}

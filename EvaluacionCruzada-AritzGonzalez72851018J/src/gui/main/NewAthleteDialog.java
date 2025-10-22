package gui.main;

import java.awt.BorderLayout;
import java.awt.Window;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import domain.Athlete;
import gui.main.AthleteFormPanel.FormDataNotValid;

// GUI.10 Creamos la nueva ventana emergente que nos permitirá añadir atletas a la ventana principal

public class NewAthleteDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private int respuesta = -1;
	private static Athlete nuevoAtleta;
	
	public NewAthleteDialog(List<String> listaPaises, Window parent) {
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(450, 320);
		setModal(true);
		setLocationRelativeTo(parent);
		
		AthleteFormPanel formularioAtleta = new AthleteFormPanel(MainWindow.getListaPaises());
		formularioAtleta.setEditable(true);
		
		JPanel panelBotones = new JPanel();
		
		JButton botonGuardar = new JButton("Guardar");
		botonGuardar.addActionListener((e) -> {
			try {
				nuevoAtleta = formularioAtleta.getAthlete();
				respuesta = JOptionPane.OK_OPTION;
				dispose();
			} catch (FormDataNotValid e1) {
				JPanel panelErrores = new JPanel();
				panelErrores.setLayout(new BoxLayout(panelErrores, BoxLayout.Y_AXIS));
				for (String error : formularioAtleta.getErrores()) {
					JLabel errorL = new JLabel("- " + error);
					errorL.setBorder(new EmptyBorder(5, 5, 5, 5));
					panelErrores.add(errorL);
				}
				JOptionPane.showMessageDialog(this, panelErrores, "Error al crear atleta", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener((e) -> {
			respuesta = JOptionPane.CANCEL_OPTION;
			dispose();
		});
		
		// Añadimos los botones al panel
		
		panelBotones.add(botonGuardar);
		panelBotones.add(botonCancelar);
		
		// Añadimos los componentes a la ventana
		
		add(formularioAtleta, BorderLayout.CENTER);
		add(panelBotones, BorderLayout.SOUTH);
		
		setVisible(true);
		
	}
	
	public static int showDialog(Window parent) {
		
		NewAthleteDialog dialogoAtleta = new NewAthleteDialog(MainWindow.getListaPaises(), parent);
		
		return dialogoAtleta.respuesta;
		
	}
	
	public static Athlete getAthlete() {
		return nuevoAtleta;
	}

}
		

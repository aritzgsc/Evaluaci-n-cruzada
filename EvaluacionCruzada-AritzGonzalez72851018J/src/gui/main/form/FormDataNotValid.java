package gui.main.form;

//Creamos el nuevo tipo de excepción

public class FormDataNotValid extends Exception {

	private static final long serialVersionUID = 1L;
	
	public FormDataNotValid(String mensaje) {
		super(mensaje);
	}
	
}

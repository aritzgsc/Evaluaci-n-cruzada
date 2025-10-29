package gui.main.models;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javax.swing.AbstractListModel;

// Creación del modelo que permite filtrar una lista por un Predicate que se le pasa

public class FilterListModel<E> extends AbstractListModel<E> {

	private static final long serialVersionUID = 1L;
	
	List<E> objetos;
	List<E> objetosFiltrados;
	
	public FilterListModel() {
		this.objetos = new ArrayList<E>();
		this.objetosFiltrados = new ArrayList<E>();
	}
	
	@Override
	public int getSize() {
		return objetosFiltrados.size();
	}

	@Override
	public E getElementAt(int index) {
		return objetosFiltrados.get(index);
	}
	
	public void addElement(E e) {
		objetos.add(e);
		objetosFiltrados.add(e);
		fireIntervalAdded(this, getSize() - 1, getSize() - 1);
	}
	
	public void remove(int index) {
		objetos.remove(index);
		fireIntervalRemoved(this, index, index);
	}
	
	public void removeElement(E e) {
		int index = objetos.indexOf(e);
		objetosFiltrados.remove(e);
		objetos.remove(e);
		fireIntervalRemoved(this, index, index);
	}
	
	public void setFilter(Predicate<E> p) {
		
		objetosFiltrados = new ArrayList<E>();
		
		for (E objeto : objetos) {
			if (p.test(objeto)) {
				objetosFiltrados.add(objeto);
			}
		}
		
		fireContentsChanged(this, 0, getSize() - 1);
	}
	
}

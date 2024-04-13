package Negocio;

import java.util.ArrayList;

public class Ejemplo {
	
	private ArrayList<String> valores;
	private String clase;
	
	public Ejemplo(ArrayList<String> fila) {
		setValores(fila);
		
		int i = valores.size() - 1;
		setClase(valores.get(i));
		valores.remove(i);
	}

	public ArrayList<String> getValores() {
		return valores;
	}

	public void setValores(ArrayList<String> valores) {
		this.valores = valores;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}
}

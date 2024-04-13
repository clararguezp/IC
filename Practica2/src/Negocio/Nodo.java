package Negocio;

import java.util.ArrayList;

public class Nodo {
	
	private String atributo;
	private String valor;
	private String clase;
	private ArrayList<Nodo> hijos;
	private boolean esHoja;
	private boolean esAtributo;
	
	public Nodo() {
		setAtributo("raiz");
		setHijos(new ArrayList<Nodo>());
	}
	
	public Nodo(Atributo a) {
		setAtributo(a.getNombre());
		setEsAtributo(true);
		setHijos(new ArrayList<Nodo>());
	}
	
	public Nodo(String clase) {
		setClase(clase);
		setEsHoja(true);
	}
	
	public Nodo(String atributo, String valor) {
		setAtributo(atributo);
		setValor(valor);
		setHijos(new ArrayList<Nodo>());
	}

	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public ArrayList<Nodo> getHijos() {
		return hijos;
	}

	public void setHijos(ArrayList<Nodo> hijos) {
		this.hijos = hijos;
	}
	
	public void setHijo(Nodo hijo) {
		hijos.add(hijo);
	}

	public boolean isEsHoja() {
		return esHoja;
	}

	public void setEsHoja(boolean esHoja) {
		this.esHoja = esHoja;
	}

	public boolean isEsAtributo() {
		return esAtributo;
	}

	public void setEsAtributo(boolean esAtributo) {
		this.esAtributo = esAtributo;
	}
	
	public boolean esNull() {
		boolean esNull = false;
		int i = 0;
		
		while(i < hijos.size() || !esNull) {
			if (hijos.get(i) == null) {
				esNull = true;
			}
			i++;
		}
		
		return esNull;
	}
}

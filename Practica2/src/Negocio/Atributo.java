package Negocio;

import java.util.ArrayList;

public class Atributo {

	private String nombre;
	private Integer posicion;
	private ArrayList<String> valoresPosibles;
	
	public Atributo(String nombre, Integer posicion) {
		this.setNombre(nombre);
		this.setPosicion(posicion);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getPosicion() {
		return posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

	public ArrayList<String> getValoresPosibles() {
		return valoresPosibles;
	}

	public void setValoresPosibles(ArrayList<String> valoresPosibles) {
		this.valoresPosibles = valoresPosibles;
	}
	
	public double calcularEntropiaValor (ArrayList<Ejemplo> ejemplos, String valor, Atributo clase) {
		ArrayList<String> clasesPosibles = clase.getValoresPosibles();
		int[] contadorClases = new int[clase.getValoresPosibles().size()];
		
		for(int i = 0; i < contadorClases.length; i++) {
			contadorClases[i] = 0;
		}
		
		int contador = 0;
		
		for(Ejemplo ejemplo : ejemplos) {
			ArrayList<String> valores = ejemplo.getValores();
			
			if(valores.size() > posicion && valores.get(posicion).equals(valor)) {
				contador++;
				
				for (int j = 0; j < clasesPosibles.size(); j++) {
					if(ejemplo.getClase().equals(clasesPosibles.get(j))) {
						contadorClases[j] += 1;
					}
				}
			}
		}
		
		double entropia = 0;
		
		for (int j = 0; j < clasesPosibles.size(); j++) {
			if (contadorClases[j] != 0) {
				double division = (double) (contadorClases[j] / contador);
				double logaritmo = Math.log(division) / Math.log(2);
				
				entropia += contadorClases[j] * logaritmo;
			}
		}
		
		return entropia;
	}
	
	public Double calcularEntropiaAtributo(ArrayList<Ejemplo> ejemplos, Atributo clase) {
		double entropia = 0.0;
		
		for(String valor : valoresPosibles) {
			double entropiaAux = calcularEntropiaValor(ejemplos, valor, clase);
			entropia += entropiaAux;
		}
		
		return -(entropia / ejemplos.size());
	}
}

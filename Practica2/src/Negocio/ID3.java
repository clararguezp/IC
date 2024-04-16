package Negocio;

import java.util.ArrayList;

public class ID3 {
	private ArrayList<Atributo> atributos;
	private ArrayList<Ejemplo> ejemplos;
	private ArrayList<String> valoresPosibles;
	
	public ID3() {
		this.atributos = new ArrayList<Atributo>();
		this.ejemplos = new ArrayList<Ejemplo>();
	}

	public ArrayList<Atributo> getAtributos() {
		return atributos;
	}

	public void setAtributos(ArrayList<String> atributos) {
		for (String atributo : atributos) {
			this.atributos.add(new Atributo(atributo, atributos.indexOf(atributo)));
		}
	}

	public ArrayList<Ejemplo> getEjemplos() {
		return ejemplos;
	}

	public void setEjemplos(ArrayList<ArrayList<String>> ejemplos) {
		for (ArrayList<String> ejemplo : ejemplos) {
			this.ejemplos.add(new Ejemplo(ejemplo));
		}
	}

	public ArrayList<String> getValoresPosibles() {
		return valoresPosibles;
	}

	public void setValoresPosibles() {
		this.valoresPosibles = calcularPosiblesValoresClase(atributos.get(atributos.size()-1));;
	}
	
	public Nodo algoritmoID3 (ArrayList<Atributo> atributos, ArrayList<Ejemplo> ejemplos) {
		if(ejemplos.isEmpty()) {
			return null;
		}
		
		if (atributos.isEmpty() || mismaClase(ejemplos)){
			return new Nodo(calcularClase(ejemplos));
		}

		Atributo atributo = calcularMaximaGanancia(atributos, ejemplos);
		Nodo nodo = new Nodo(atributo);
			
		for (int i = 0; i < atributo.getValoresPosibles().size();i++){
			ArrayList<Ejemplo> ejRestantes = ejemplosRestantes(atributo.getValoresPosibles().get(i), ejemplos);
			ArrayList<Atributo> atribRestantes = atributos;
			atribRestantes.remove(atributo);
				
			Nodo hijo = new Nodo(atributo.getNombre(), atributo.getValoresPosibles().get(i));
			Nodo aux = algoritmoID3(atribRestantes, ejRestantes);
				
			if(aux != null) {
				hijo.setHijo(aux);
			}
					
			nodo.setHijo(hijo);
		}
			
		return nodo;
	}
	
	public boolean mismaClase(ArrayList<Ejemplo> ejemplos) {
		String clase = ejemplos.get(0).getClase();
		boolean igual = true;
		int i = 1;
		
		while(i < ejemplos.size() || igual) {
			if(!ejemplos.get(i).getClase().equals(clase)) {
				igual = false;
			}
			
			i++;
		}
		
		return igual;
	}
	
	public ArrayList<Ejemplo> ejemplosRestantes(String valor, ArrayList<Ejemplo> ejemplos){
		ArrayList<Ejemplo> restantes = new ArrayList<Ejemplo>();
		
		for(Ejemplo e : ejemplos){
			if(e.getValores().contains(valor)) {
				restantes.add(e);
			}
		}
		
		return restantes;
	}
	
	public ArrayList<String> calcularPosiblesValoresClase(Atributo atributo) {
		ArrayList<String> valores = new ArrayList<String>();
		
		for(int i = 0; i < ejemplos.size(); i++) {
			if(valores.isEmpty()) {
				valores.add(ejemplos.get(i).getClase());
			}
			else if(!valores.contains(ejemplos.get(i).getClase())) {
				valores.add(ejemplos.get(i).getClase());
			}
		}
		
		atributo.setValoresPosibles(valores);
		
		return valores;
	}
	
	public void calcularPosiblesValores(Atributo atributo) {
		ArrayList<String> valores = new ArrayList<String>();
		
		for(int i = 0; i < ejemplos.size(); i++) {
			if(valores.isEmpty()) {
				valores.add(ejemplos.get(i).getValores().get(atributo.getPosicion()));
			}
			else if(!valores.contains(ejemplos.get(i).getValores().get(atributo.getPosicion()))) {
				valores.add(ejemplos.get(i).getValores().get(atributo.getPosicion()));
			}
		}
		
		atributo.setValoresPosibles(valores);
	}
	
	public String calcularClase(ArrayList<Ejemplo> ejemplos) {
		int[] contador = new int[this.valoresPosibles.size()];
		int max = 0;
		int pos = 0;
		
		for(int i = 0; i < contador.length; i++) {
			contador[i] = 0;
		}
		
		for(int i = 0; i < ejemplos.size(); i++) {
			contador[this.valoresPosibles.indexOf(ejemplos.get(i).getClase())]++;
		}
		
		for(int i = 0; i < contador.length; i++) {
			if(contador[i] > max) {
				max = contador[i];
				pos = i;
			}
		}
		
		return this.valoresPosibles.get(pos);
	}
	
	public Atributo calcularMaximaGanancia(ArrayList<Atributo> atributos, ArrayList<Ejemplo> ejemplos){
		ArrayList<Double> entropias = new ArrayList<Double>();
		int indice = 0;
		double min = 1;
		
		for (int i = 0; i < atributos.size()-1; i++){
			if(atributos.get(i).getValoresPosibles() == null) {
				calcularPosiblesValores(atributos.get(i));
			}
			
			entropias.add(atributos.get(i).calcularEntropiaAtributo(ejemplos, atributos.get(atributos.size()-1)));
			
			if(entropias.get(i) < min){
				indice = i;
				min = entropias.get(i);
			} 
		}
		
		return atributos.get(indice);
	}
}

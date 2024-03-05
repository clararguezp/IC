package Negocio;

import java.util.ArrayList;
import java.util.List;

import Negocio.Nodo;

public class AEstrella {
	
	private Tablero tab;
	private Nodo NAct;
	
	public List<Nodo> abierta;
	public List<Nodo> cerrada;
	
	private boolean INmeta = false;
	private boolean ImpMeta = false;
	private int numWayPoints;
	
	
	public AEstrella(Tablero tablero) {
		abierta = new ArrayList<Nodo>();
		cerrada = new ArrayList<Nodo>();
		this.tab = tablero;
		this.numWayPoints = this.tab.getWaypoints().size();
	}
	
	public void move(Nodo Nact) {
		Nodo next;
		for(int i = Nact.getX()-1; i <= Nact.getX()+1; i++) {
			for(int j = Nact.getY()-1; j <= Nact.getY()+1; j++) {
				if(tab.estaDentro(i,j) && (Nact.getX() != i || Nact.getY() != j)) {
					next = tab.getNodoTablero(i,j);
					if(next.getTipo() != "forbidden") {
						
						if(estaEnAbierta(next)) {
							if((Nact.getG() + calcularDistancia(Nact,next)) < next.getG()) {
								if(next.getTipo() == "dangerous") next.setG(Nact.getG() + calcularDistancia(Nact,next)+ 1);
								else next.setG(Nact.getG() + calcularDistancia(Nact,next));
								next.setH(calcularMeta(next,tab.getMeta()));
								next.setF(next.getG()+next.getH());
								next.setAnterior(Nact);
							}
						}
						else {
							if(!estaEnCerrada(next)) {
								abierta.add(next);
								if(next.getTipo() == "dangerous") next.setG(Nact.getG() + calcularDistancia(Nact,next)+ 1);
								else next.setG(Nact.getG() + calcularDistancia(Nact,next));
								next.setH(calcularMeta(next,tab.getMeta()));
								next.setF(next.getG()+next.getH());
								next.setAnterior(Nact);
							}
							
						}
						if(next.getH()  == 0 && (next.isEqual(tab.getMeta().getX(), tab.getMeta().getY()))) {
							INmeta = true;
							next.setAnterior(Nact);
							cerrada.add(next);
							abierta.remove(next);
							NAct = next;
						}
					}
					
				}
				
			}
		}
		if(!INmeta) {
			if(abierta.size() ==0 ) {
				ImpMeta = true;
			}
			else {
				next = getMejorF();
				if(!estaEnCerrada(next)) {
					cerrada.add(next);
				}
				NAct = next;
				abierta.remove(next);
			}
			
		}
		else if(numWayPoints > 0 ){
			if(!INmeta) {
				if(abierta.size() == 0) {
					ImpMeta = true;
				}
				else {
					next = getMejorF();
					NAct = next;
					if(!estaEnCerrada(next)) {
						next.setAnterior(Nact);
						cerrada.add(next);
					}
					abierta.remove(next);
				}
				
			}
			
		}
		
		
		
	}

	public List<Nodo> AEstrella(){
		Nodo ini = tab.getSalida();
		Nodo meta = tab.getMeta();
		
		ini.setG(0);
		ini.setH(calcularMeta(ini, meta));
		ini.getF();
		NAct = ini;
		cerrada.add(NAct);
		while(!INmeta && !ImpMeta) {
			move(NAct);
		}
		
		List<Nodo>sol = new ArrayList<Nodo>();
		if(INmeta) {
			Nodo aux = cogerMeta(meta.getX(),meta.getY());
			sol.add(aux);
			while(aux.getAnterior() != null) {
				sol.add(aux.getAnterior());
				aux = aux.getAnterior();
			}
		}
		if(ImpMeta) System.out.println("META NO ALCANZABLE");
		return sol;
	}
	
	
	public List<Nodo>  AEstrellaConWP() {
		List<Nodo> sol = new ArrayList<Nodo>();
		Nodo metaFinal = tab.getMeta();
		Nodo ini = tab.getSalida();
		double min = Double.MAX_VALUE;
		int indice = 0;
		Nodo meta = tab.getWaypoints().get(0);
		NAct = ini;
		boolean wpAlcanzado = false;
		
		while(numWayPoints > 0 && !ImpMeta) {
			if(!wpAlcanzado) {
				for(int i = 0; i < tab.getWaypoints().size(); i++) {
					if(calcularDistancia(ini, tab.getWaypoints().get(i)) < min){
						min = calcularDistancia(ini, tab.getWaypoints().get(i));
						indice = i;
					}
				}
				wpAlcanzado = true;
				min = Double.MAX_VALUE;
				meta = tab.getWaypoints().get(indice);
				tab.getWaypoints().remove(indice);
				tab.setMeta(meta.getX(), meta.getY());
			}
			move(NAct);
			
			if(NAct.isEqual(meta.getX(), meta.getY())) {
				wpAlcanzado = false;
				numWayPoints--;
				INmeta = false;
				actualizarFunciones();
				NAct.setG(0);
				
			}
			
		}
		INmeta = false;
		Nodo aux1 = cogerMeta(meta.getX(), meta.getY());
		sol.add(aux1);
		while(aux1.getAnterior() != null) {
			sol.add(aux1.getAnterior());
			aux1 = aux1.getAnterior();
		}
		meta = metaFinal;
		tab.setMeta(meta.getX(), meta.getY());
		abierta.clear();
		System.out.println(sol);
		actualizarFunciones();
		NAct.setG(0);
		cerrada.clear();
		while(!INmeta && !ImpMeta) {
			move(NAct);
		}
		if(INmeta) {
			System.out.println(cerrada);
			System.out.println(sol);
			Nodo aux = cogerMeta(metaFinal.getX(), metaFinal.getY());
			sol.add(aux);
			int z = cerrada.size()-1;
			while(aux.getAnterior() != null && z > 0 ) {
				sol.add(aux.getAnterior());
				aux = aux.getAnterior(); 
				z--;
			}
		}
		if(ImpMeta) {
			System.out.println("META NO ALCANZABLE");
			sol.clear();
		} 
		return sol;
	}
	
	public void actualizarFunciones() {
		for(int i = 0; i < tab.getN(); i++) {
			for(int j = 0; j < tab.getM();j++) {
				if(!estaEnCerrada(tab.getNodoTablero(i, j))) {
					tab.getNodoTablero(i, j).setG(Double.MAX_VALUE);
				}
			}
		}
	}
	
	public boolean estaEnAbierta(Nodo vecino) {
		return abierta.contains(vecino);
	}
	
	public Nodo getMejorF() {
		double mejor = abierta.get(0).getF();
		Nodo mejorC = abierta.get(0);
		for(int i = 1; i < abierta.size(); i++) {
			if(abierta.get(i).getF() < mejor) {
				mejorC = abierta.get(i);
				mejor = abierta.get(i).getF();
			}
		}
		return mejorC;
	}
	
	public boolean estaEnCerrada(Nodo aux) {
		boolean encontrado = false;
		for (Nodo a : cerrada) {
			if(a.isEqual(aux.getX(), aux.getY()))encontrado = true;
		}
		return encontrado;
	}
	
	public Nodo cogerMeta(int x,int y) {
		Nodo metaSol = null;
		for(int i = 0; i < cerrada.size();i++) {
			if(cerrada.get(i).isEqual(x, y)) metaSol = cerrada.get(i); 
		}
		return metaSol;
	}
	
	public double calcularMeta(Nodo ini, Nodo meta) {
		return Math.sqrt(Math.pow((meta.getX() - ini.getX()),2) + Math.pow((meta.getY() - ini.getY()),2));
	}
	
	public double calcularDistancia (Nodo celdaActual, Nodo celdaSiguiente) {
		return Math.sqrt(Math.pow((celdaSiguiente.getX() - celdaActual.getX()),2) + Math.pow((celdaSiguiente.getY() - celdaActual.getY()),2));
	}

}

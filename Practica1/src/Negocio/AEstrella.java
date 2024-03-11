package Negocio;

import java.util.ArrayList;
import java.util.List;

public class AEstrella {
	
	private Cuadricula cuadricula;
	private Nodo posicionActual;
	
	public List<Nodo> abierta;
	public List<Nodo> cerrada;
	
	private boolean llegadaMeta = false;
	private boolean inalcanzableMeta = false;
	private int numWayPoints;
	
	
	public AEstrella(Cuadricula cuadricula) {
		abierta = new ArrayList<Nodo>();
		cerrada = new ArrayList<Nodo>();
		
		this.cuadricula = cuadricula;
		this.numWayPoints = this.cuadricula.getWaypoints().size();
	}
	
	public void move(Nodo nodoActual) {
		Nodo next;
		
		for(int i = nodoActual.getX()-1; i <= nodoActual.getX()+1; i++) {
			for(int j = nodoActual.getY()-1; j <= nodoActual.getY()+1; j++) {
				if(cuadricula.estaDentro(i,j) && (nodoActual.getX() != i || nodoActual.getY() != j)) {
					next = cuadricula.getNodoCuadricula(i,j);
					
					if(next.getTipo() != TiposNodos.PROHIBIDO) {
						if(estaEnAbierta(next)) {
							if((nodoActual.getG() + calcularDistancia(nodoActual,next)) < next.getG()) {
								if(next.getTipo() == TiposNodos.PELIGROSO) {
									next.setG(nodoActual.getG() + calcularDistancia(nodoActual,next)+ 1);
								}
								else {
									next.setG(nodoActual.getG() + calcularDistancia(nodoActual,next));
								}
								
								next.setH(calcularMeta(next,cuadricula.getMeta()));
								next.setF(next.getG()+next.getH());
								next.setAnterior(nodoActual);
							}
						}
						else {
							if(!estaEnCerrada(next)) {
								abierta.add(next);
								
								if(next.getTipo() == TiposNodos.PELIGROSO) {
									next.setG(nodoActual.getG() + calcularDistancia(nodoActual,next)+ 1);
								}
								else {
									next.setG(nodoActual.getG() + calcularDistancia(nodoActual,next));
								}
								
								next.setH(calcularMeta(next,cuadricula.getMeta()));
								next.setF(next.getG()+next.getH());
								next.setAnterior(nodoActual);
							}
							
						}
						
						if(next.getH()  == 0 && (next.isEqual(cuadricula.getMeta().getX(), cuadricula.getMeta().getY()))) {
							llegadaMeta = true;
							next.setAnterior(nodoActual);
							cerrada.add(next);
							abierta.remove(next);
							posicionActual = next;
						}
					}
				}
			}
		}
		
		if(!llegadaMeta) {
			if(abierta.size() ==0 ) {
				inalcanzableMeta = true;
			}
			else {
				next = getMejorF();
				
				if(!estaEnCerrada(next)) {
					cerrada.add(next);
				}
				
				posicionActual = next;
				abierta.remove(next);
			}
		}
		else if(numWayPoints > 0 ){
			if(!llegadaMeta) {
				if(abierta.size() == 0) {
					inalcanzableMeta = true;
				}
				else {
					next = getMejorF();
					posicionActual = next;
					
					if(!estaEnCerrada(next)) {
						next.setAnterior(nodoActual);
						cerrada.add(next);
					}
					
					abierta.remove(next);
				}
			}
		}
	}

	public List<Nodo> algoritmoAEstrella(){
		Nodo salida = cuadricula.getSalida();
		Nodo meta = cuadricula.getMeta();
		
		salida.setG(0);
		salida.setH(calcularMeta(salida, meta));
		salida.getF();
		
		posicionActual = salida;
		cerrada.add(posicionActual);
		
		while(!llegadaMeta && !inalcanzableMeta) {
			move(posicionActual);
		}
		
		List<Nodo>sol = new ArrayList<Nodo>();
		
		if(llegadaMeta) {
			Nodo aux = cogerMeta(meta.getX(),meta.getY());
			sol.add(aux);
			
			while(aux.getAnterior() != null) {
				sol.add(aux.getAnterior());
				aux = aux.getAnterior();
			}
		}
		
		if(inalcanzableMeta) {
			System.out.println("META NO ALCANZABLE");
			sol = null;
		}
		
		return sol;
	}
	
	
	public List<Nodo>  algoritmoAEstrellaWayPoints() {
		List<Nodo> sol = new ArrayList<Nodo>();
		Nodo metaFinal = cuadricula.getMeta();
		Nodo ini = cuadricula.getSalida();
		double min = Double.MAX_VALUE;
		int indice = 0;
		Nodo meta = cuadricula.getWaypoints().get(0);
		posicionActual = ini;
		boolean wpAlcanzado = false;
		
		while(numWayPoints > 0 && !inalcanzableMeta) {
			if(!wpAlcanzado) {
				for(int i = 0; i < cuadricula.getWaypoints().size(); i++) {
					if(calcularDistancia(ini, cuadricula.getWaypoints().get(i)) < min){
						min = calcularDistancia(ini, cuadricula.getWaypoints().get(i));
						indice = i;
					}
				}
				
				wpAlcanzado = true;
				min = Double.MAX_VALUE;
				meta = cuadricula.getWaypoints().get(indice);
				cuadricula.getWaypoints().remove(indice);
				cuadricula.setMeta(meta.getX(), meta.getY());
			}
			
			move(posicionActual);
			
			if(posicionActual.isEqual(meta.getX(), meta.getY())) {
				wpAlcanzado = false;
				numWayPoints--;
				llegadaMeta = false;
				actualizarFunciones();
				posicionActual.setG(0);
				
			}
		}
		
		llegadaMeta = false;
		Nodo aux1 = cogerMeta(meta.getX(), meta.getY());
		sol.add(aux1);
		
		while(aux1.getAnterior() != null) {
			sol.add(aux1.getAnterior());
			aux1 = aux1.getAnterior();
		}
		
		meta = metaFinal;
		cuadricula.setMeta(meta.getX(), meta.getY());
		abierta.clear();
		System.out.println(sol);
		
		actualizarFunciones();
		posicionActual.setG(0);
		cerrada.clear();
		
		while(!llegadaMeta && !inalcanzableMeta) {
			move(posicionActual);
		}
		
		if(llegadaMeta) {
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
		
		if(inalcanzableMeta) {
			System.out.println("META NO ALCANZABLE");
			sol.clear();
		} 
		
		return sol;
	}
	
	public void actualizarFunciones() {
		for(int i = 0; i < cuadricula.getN(); i++) {
			for(int j = 0; j < cuadricula.getM();j++) {
				if(!estaEnCerrada(cuadricula.getNodoCuadricula(i, j))) {
					cuadricula.getNodoCuadricula(i, j).setG(Double.MAX_VALUE);
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

package Negocio;

import java.util.ArrayList;
import java.util.List;

public class Cuadricula {
	
	private int N;
	private int M;
	private Nodo cuadricula[][];
	private Nodo meta;
	private Nodo salida;
	private List<Nodo> waypoints;
	
	public Cuadricula(int N, int M) {
		this.N = N;
		this.M = M;
		
		waypoints = new ArrayList<>();
		crearCuadricula(this.N, this.M);
	}
	
	private void crearCuadricula(int N, int M) {
		cuadricula = new Nodo[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				cuadricula[i][j] = new Nodo(i, j, TiposNodos.VACIO);
			}
		}
	}

	public int getN() {
		return N;
	}
	
	public void setN(int n) {
		N = n;
	}
	
	public int getM() {
		return M;
	}
	
	public void setM(int m) {
		M = m;
	}
	
	public Nodo[][] getCuadricula() {
		return cuadricula;
	}
	
	public void setCuadricula(Nodo[][] tablero) {
		this.cuadricula = tablero;
	}
	
	public Nodo getNodoCuadricula(int i, int j) {
		return cuadricula[i][j];
	}
	
	public void setNodoCuadricula(int i, int j, TiposNodos tipo) {
		cuadricula[i][j].setTipo(tipo);
	}
	
	public TiposNodos getTipo(int x, int y) {
		return cuadricula[x][y].getTipo();
	}
	public Nodo getMeta() {
		return meta;
	}
	
	public void setMeta(int i, int j) {
		meta = new Nodo(i, j, TiposNodos.META);
	}
	
	public Nodo getSalida() {
		return salida;
	}
	
	public void setSalida(int i, int j) {
		salida = new Nodo(i, j, TiposNodos.SALIDA);
	}
	
	public List<Nodo> getWaypoints() {
		return waypoints;
	}
	
	public void setWaypoints(int i, int j) {
		waypoints.add(cuadricula[i][j]);
	}
	
	@SuppressWarnings("null")
	public List<Nodo> getForbidden() {
		List<Nodo> nodosProhibidos = null;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M;j++) {
				if(cuadricula[i][j].getTipo() == TiposNodos.PROHIBIDO) {
					nodosProhibidos.add(cuadricula[i][j]);
				}
			}
		}
		
		return nodosProhibidos;
	}
	
	public double calcularDistancia (Nodo celdaActual, Nodo celdaSiguiente) {
		return Math.sqrt(Math.pow((celdaSiguiente.getX() - celdaActual.getX()),2) + Math.pow((celdaSiguiente.getY() - celdaActual.getY()),2));

	}

	public boolean estaDentro(int i, int j) {
		return ( i >= 0 && i < N ) && (j >= 0 && j < M);
	}
}
package Negocio;

import java.util.ArrayList;
import java.util.List;

public class Tablero {

	private int N;
	private int M;
	private Nodo tablero[][];
	private Nodo meta;
	private Nodo salida;
	private List<Nodo> waypoints;
	
	public Tablero(int N, int M) {
		this.N = N;
		this.M = M;
		
		waypoints = new ArrayList<>();
		crearTablero(this.N, this.M);
	}
	
	private void crearTablero(int N, int M) {
		tablero = new Nodo[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				tablero[i][j] = new Nodo(i, j, "vacio");
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
	
	public Nodo[][] getTablero() {
		return tablero;
	}
	
	public void setTablero(Nodo[][] tablero) {
		this.tablero = tablero;
	}
	
	public Nodo getNodoTablero(int i, int j) {
		return tablero[i][j];
	}
	
	public void setNodoTablero(int i, int j, String tipo) {
		tablero[i][j].setTipo(tipo);
	}
	
	public Nodo getMeta() {
		return meta;
	}
	
	public void setMeta(int i, int j) {
		meta = new Nodo(i, j, "meta");
	}
	
	public Nodo getSalida() {
		return salida;
	}
	
	public void setSalida(int i, int j) {
		salida = new Nodo(i, j, "salida");
	}
	
	public List<Nodo> getWaypoints() {
		return waypoints;
	}
	
	public void setWaypoints(int i, int j) {
		waypoints.add(tablero[i][j]);
	}
	
	@SuppressWarnings("null")
	public List<Nodo> getForbidden() {
		List<Nodo> nodosProhibidos = null;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M;j++) {
				if(tablero[i][j].getTipo() == "prohibido") {
					nodosProhibidos.add(tablero[i][j]);
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

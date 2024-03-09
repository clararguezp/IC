package Negocio;

import java.util.List;

import Presentacion.PrincipalView;

public class Controlador {

	private static Controlador instance = null;
	private TiposNodos tipo;
	private Cuadricula cuadricula;
	public static boolean CheckMeta = false;
	public static boolean CheckSalida = false;
	
	public Controlador(int filas, int columnas) {
		tipo = TiposNodos.VACIO;
		cuadricula = new Cuadricula(filas, columnas);
	}

	public static Controlador getInstance() {
		if (instance == null) {
			instance = new Controlador(5, 5);
		}

		return instance;
	}
	
	public void setInstance() {
		instance = this;
	}
	
	public Cuadricula getCuadricula() {
		return cuadricula;
	}
	
	public void setCuadricula(Cuadricula cuadricula) {
		this.cuadricula = cuadricula;
	}
	
	public TiposNodos getTipo() {
		return tipo;
	}
	
	public void setTipo(TiposNodos tipo) {
		 this.tipo = tipo;
	}
	
	public void resetCuadricula() {
		PrincipalView.getInstance().reset();
	}


	public void pintarResultado(List<Nodo> sol) {
		PrincipalView.getInstance().pintarCaminoSolucion(sol);
	}
}

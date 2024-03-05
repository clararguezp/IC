package Negocio;

import java.util.List;

import Presentacion.Principal;


public class Controller {
	
	private static Controller instance = null;
	private String tipo;
	private Tablero tablero;
	public static boolean CheckMeta = false;
	public static boolean CheckInicio = false;
	
	public Controller(int filas, int cols) {
		this.tipo = "normal";
		tablero = new Tablero(filas, cols);
	}
	

	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller(4, 4);
		}

		return instance;
	}
	
	public void setInstance() {
		instance = this;
	}
	
	public Tablero getTablero() {
		return tablero;
	}
	
	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		 this.tipo = tipo;
	}
	
	public void resetTablero() {
		Principal.getInstance().reset();
	}


	public void paintResult(List<Nodo> sol) {
		Principal.getInstance().paintResult(sol);
		
	}

}

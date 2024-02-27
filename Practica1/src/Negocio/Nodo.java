package Negocio;

public class Nodo {
	
	private String tipo;
	private int x;
	private int y;
	private double f;
	private double g;
	private double h;
	private Nodo anterior;
	
	public Nodo(int x, int y, String tipo) {
		this.x = x;
		this.y = y;
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public double getF() {
		return f;
	}
	
	public void setF(double f) {
		this.f = f;
	}
	
	public double getG() {
		return g;
	}
	
	public void setG(double g) {
		this.g = g;
	}
	
	public double getH() {
		return h;
	}
	
	public void setH(double h) {
		this.h = h;
	}
	
	public Nodo getAnterior() {
		return anterior;
	}
	
	public void setAnterior(Nodo anterior) {
		this.anterior = anterior;
	}
	
	public boolean isEqual (int x, int y) {
		return this.x == x && this.y == y;
	}
	
	public String toString() {
		return "["+ x + ", " + y +"]";
	}
}

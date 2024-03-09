package Presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import Negocio.Controlador;
import Negocio.Cuadricula;
import Negocio.Nodo;
import Negocio.TiposNodos;

public class CuadriculaView extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private JButton[][] cuadriculaVista;
	private int N;
	private int M;
	private int xSalida;
	private int ySalida;
	private int xMeta;
	private int yMeta;
	
	public CuadriculaView() {
		this.setPreferredSize(new Dimension(900,650));
		initGUI();
	}
	
	// Función para iniciar el tablero
	public void initGUI() {
		Cuadricula cuadricula = Controlador.getInstance().getCuadricula();
		N = cuadricula.getN();
		M = cuadricula.getM();
		
		setBackground(Color.WHITE);
		setLayout(new GridLayout(N, M));
		
		cuadriculaVista = new JButton[N][M];
		
		for(int x = 0 ; x < N; x++) {
			for(int y = 0; y < M;y++) {
				cuadriculaVista[x][y] = new JButton();
				cuadriculaVista[x][y].setBackground(Color.WHITE);
				cuadriculaVista[x][y].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				cuadriculaVista[x][y].addActionListener(new ActionListenerImp(x,y));
				this.add(cuadriculaVista[x][y]);
			}
		}
	}

	// Función para resetear el tablero
	public void reset() {
		Controlador.CheckSalida = false;
		Controlador.CheckMeta = false;
		
		for(int x = 0 ; x < N; x++) {
			for(int y = 0; y < M;y++) {
				Controlador.getInstance().getCuadricula().setNodoCuadricula(x, y, TiposNodos.VACIO);
				cuadriculaVista[x][y].setBackground(Color.WHITE);
				cuadriculaVista[x][y].setText("");
			}
		}
	}
	
	// Función para pintar la solución en el tablero
	public void pintarCamino(List<Nodo> sol) {
		System.out.println(sol);
		for(Nodo a : sol) {
			if(a.getX() != xSalida || a.getY() != ySalida) {
				if(a.getX() != xMeta || a.getY() != yMeta) {
					cuadriculaVista[a.getX()][a.getY()].setBackground(Color.PINK);
				}
			}
		}
	}
	
	// Función para pintar un nodo
	public void colorearNodo(int x, int y) {
		switch(Controlador.getInstance().getCuadricula().getTipo(x, y)) {
			case VACIO:
				cuadriculaVista[x][y].setBackground(Color.WHITE);
				cuadriculaVista[x][y].setText("");
			break;
			
			case SALIDA:
				cuadriculaVista[x][y].setBackground(Color.BLUE);
				cuadriculaVista[x][y].setText(TiposNodos.SALIDA.toString());
			break;
			
			case META:
				cuadriculaVista[x][y].setBackground(Color.GREEN);
				cuadriculaVista[x][y].setText(TiposNodos.META.toString());
			break;
			
			case PROHIBIDO: 
				cuadriculaVista[x][y].setBackground(Color.RED);
				cuadriculaVista[x][y].setText("");
			break;
			
			case PELIGROSO:
				cuadriculaVista[x][y].setBackground(Color.ORANGE);
				cuadriculaVista[x][y].setText("");
			break;
			
			case WAYPOINT:
				cuadriculaVista[x][y].setBackground(Color.YELLOW);
				cuadriculaVista[x][y].setText("");
			break;
			case SOLUCION:
				cuadriculaVista[x][y].setBackground(Color.PINK);
				cuadriculaVista[x][y].setText("");
			break;
		}
	}
	
	// Clase auxiliar para poder pintar los elementos necesarios
	public class ActionListenerImp implements ActionListener{
		
		private int x, y;

		public ActionListenerImp(int x,int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			switch(Controlador.getInstance().getTipo()) {
				case VACIO:
					if(Controlador.getInstance().getCuadricula().getNodoCuadricula(x, y).getTipo() == TiposNodos.SALIDA) {
						Controlador.CheckSalida = false;
					}
					else if(Controlador.getInstance().getCuadricula().getNodoCuadricula(x, y).getTipo() == TiposNodos.META) {
						Controlador.CheckMeta = false;
					}
					Controlador.getInstance().getCuadricula().setNodoCuadricula(x, y, TiposNodos.VACIO);
				break;
					
				case PROHIBIDO: 
					Controlador.getInstance().getCuadricula().setNodoCuadricula(x, y, TiposNodos.PROHIBIDO);
				break;
					
				case SALIDA:
					if(!Controlador.CheckSalida) {
						Controlador.getInstance().getCuadricula().setNodoCuadricula(x, y, TiposNodos.SALIDA);
						Controlador.CheckSalida = true;
						xSalida = x;
						ySalida = y;
					}
					
					else {
						Controlador.getInstance().getCuadricula().setNodoCuadricula(x, y, TiposNodos.SALIDA);
						Controlador.getInstance().getCuadricula().setNodoCuadricula(xSalida, ySalida, TiposNodos.VACIO);
						colorearNodo(xSalida,ySalida);
						xSalida = x;
						ySalida = y;
					}
					
					Controlador.getInstance().getCuadricula().setSalida(x, y);
				break;
					
				case META:
					if(!Controlador.CheckMeta) {
						Controlador.getInstance().getCuadricula().setNodoCuadricula(x, y, TiposNodos.META);
						Controlador.CheckMeta = true;
						xMeta = x;
						yMeta = y;
					}
					
					else {
						Controlador.getInstance().getCuadricula().setNodoCuadricula(x, y, TiposNodos.META);
						Controlador.getInstance().getCuadricula().setNodoCuadricula(xMeta, yMeta, TiposNodos.VACIO);
						colorearNodo(xMeta,yMeta);
						xMeta = x;
						yMeta = y;
					}
					
					Controlador.getInstance().getCuadricula().setMeta(x, y);
				break;
					
				case PELIGROSO:
					Controlador.getInstance().getCuadricula().setNodoCuadricula(x, y, TiposNodos.PELIGROSO);
				break;
					
				case WAYPOINT:
					Controlador.getInstance().getCuadricula().setNodoCuadricula(x, y, TiposNodos.WAYPOINT);
					Controlador.getInstance().getCuadricula().getWaypoints().add(Controlador.getInstance().getCuadricula().getNodoCuadricula(x, y));
					
				break;
			}
			
			colorearNodo(x,y);
		}
	}
}
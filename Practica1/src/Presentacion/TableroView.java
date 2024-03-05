package Presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import Negocio.Casilla;
import Negocio.Controller;
import Negocio.Nodo;



public class TableroView extends JPanel{
private static final long serialVersionUID = 1L;
	
	private JButton[][] celdas;
	private int filas, columnas, coordXMeta, coordYMeta, coorddXSalida, coordYSalida;
	
	public TableroView() {
		this.setPreferredSize(new Dimension(900,650));
		initGUI();
	}
	
	public void initGUI() {
		this.setBackground(Color.WHITE);
		filas = Controller.getInstance().getTablero().getN();
		columnas = Controller.getInstance().getTablero().getM();
		setLayout(new GridLayout(filas, columnas));
		celdas = new JButton[filas][columnas];
		
		for(int x = 0 ; x < filas; x++) {
			for(int y = 0; y < columnas;y++) {
				celdas[x][y] = new JButton();
				celdas[x][y].setBackground(Color.WHITE);
				celdas[x][y].setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
				celdas[x][y].addActionListener(new Casilla(x,y));
				this.add(celdas[x][y]);
			}
		}
	}

	
	public void reset() {
		Controller.CheckInicio = false;
		Controller.CheckMeta = false;
		for(int x = 0 ; x < filas; x++) {
			for(int y = 0; y < columnas;y++) {
				Controller.getInstance().getTablero().setNodoTablero(x, y, "normal");
				celdas[x][y].setBackground(Color.WHITE);
				celdas[x][y].setText("");
			}
		}
	}
	public void paintResult(List<Nodo> sol) {
		System.out.println(sol);
		for(Nodo a : sol) {
			if(a.getX() != coorddXSalida || a.getY() != coordYSalida) {
				if(a.getX() != coordXMeta || a.getY() != coordYMeta) {
					celdas[a.getX()][a.getY()].setBackground(Color.MAGENTA);
				}
			}
		}
	}
	
	public void colorNode(int x, int y) {
		switch(Controller.getInstance().getTablero().getTipo(x, y)) {
			case "normal":
				celdas[x][y].setBackground(Color.WHITE);
			break;
			
			case "forbidden": 
				celdas[x][y].setBackground(Color.RED);
			break;
			
			case "finish":
				celdas[x][y].setBackground(Color.BLACK);	
			break;
			
			case "start":
				celdas[x][y].setBackground(Color.GREEN);
			break;
			
			case "dangerous":
				celdas[x][y].setBackground(Color.YELLOW);
			break;
			
			case "waypoint":
				celdas[x][y].setBackground(Color.CYAN);
			break;
			case "sol":
				celdas[x][y].setBackground(Color.MAGENTA);
			break;
		}
	}
	
	
}

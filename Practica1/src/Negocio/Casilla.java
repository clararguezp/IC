package Negocio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Presentacion.TableroView;

public class Casilla implements ActionListener{
	
	private int x, y;
	private int coordXMeta, coordYMeta, coordXSalida, coordYSalida;
	TableroView tab;

	public Casilla(int x,int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		switch(Controller.getInstance().getTipo()) {
			case "normal":
				if(Controller.getInstance().getTablero().getNodoTablero(x, y).getTipo() == "start") Controller.CheckInicio = false;
				else if(Controller.getInstance().getTablero().getNodoTablero(x, y).getTipo() == "finish") Controller.CheckMeta = false;
				Controller.getInstance().getTablero().setNodoTablero(x, y, "normal");
			break;
				
			case "forbidden": 
				Controller.getInstance().getTablero().setNodoTablero(x, y, "forbidden");
			break;
				
			case "start":
				if(!Controller.CheckInicio) {
					Controller.getInstance().getTablero().setNodoTablero(x, y, "start");
					Controller.CheckInicio = true;
					coordXSalida = x;
					coordYSalida = y;
				}
				else {
					Controller.getInstance().getTablero().setNodoTablero(x, y, "start");
					Controller.getInstance().getTablero().setNodoTablero(coordXSalida, coordYSalida, "normal");
					tab.colorNode(coordXSalida,coordYSalida);
					coordXSalida = x;
					coordYSalida = y;
				}
				Controller.getInstance().getTablero().setSalida(x, y);
			break;
				
			case "finish":
				if(!Controller.CheckMeta) {
					Controller.getInstance().getTablero().setNodoTablero(x, y, "finish");
					Controller.CheckMeta = true;
					coordXMeta = x;
					coordYMeta = y;
				}
				else {
					Controller.getInstance().getTablero().setNodoTablero(x, y, "finish");
					Controller.getInstance().getTablero().setNodoTablero(coordXMeta, coordYMeta, "normal");
					tab.colorNode(coordXMeta,coordYMeta);
					coordXMeta = x;
					coordYMeta = y;
				}
				Controller.getInstance().getTablero().setMeta(x, y);
			break;
				
			case "dangerous":
				Controller.getInstance().getTablero().setNodoTablero(x, y, "dangerous");
			break;
				
			case "waypoint":
				Controller.getInstance().getTablero().setNodoTablero(x, y, "waypoint");
				Controller.getInstance().getTablero().getWaypoints().add(Controller.getInstance().getTablero().getNodoTablero(x, y));
				
			break;
		}
		tab.colorNode(x,y);

	}
	
}

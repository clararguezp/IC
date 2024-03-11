package Presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Negocio.AEstrella;
import Negocio.Nodo;
import Negocio.TiposNodos;
import Negocio.Controlador;

public class ConfiguracionView extends JPanel{
		
	private static final long serialVersionUID = 1L;

	private JButton anyadirSalida;
	private JButton anyadirMeta;
	private JButton anyadirProhibido;
	private JButton anyadirPeligroso;
	private JButton anyadirWaypoint;
	private JButton borrar;
	
	private JButton comenzar;
	private JButton reiniciar;
	
	public ConfiguracionView() {
		initGUI();
	}

	public void initGUI() {
		anyadirSalida = new JButton("Añadir Salida");
		anyadirSalida.setBackground(Color.BLUE);
		anyadirSalida.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstance().setTipo(TiposNodos.SALIDA);
			}
			
		});

		anyadirMeta = new JButton("Añadir Meta");
		anyadirMeta.setBackground(Color.GREEN);
		anyadirMeta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstance().setTipo(TiposNodos.META);
			}
			
		});

		anyadirProhibido = new JButton("Añadir Prohibido");
		anyadirProhibido.setBackground(Color.RED);
		anyadirProhibido.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstance().setTipo(TiposNodos.PROHIBIDO);
			}
			
		});

		anyadirPeligroso = new JButton("Añadir Peligroso");
		anyadirPeligroso.setBackground(Color.ORANGE);
		anyadirPeligroso.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstance().setTipo(TiposNodos.PELIGROSO);
			}
			
		});

		anyadirWaypoint = new JButton("Añadir Waypoint");
		anyadirWaypoint.setBackground(Color.YELLOW);
		anyadirWaypoint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstance().setTipo(TiposNodos.WAYPOINT);
			}
			
		});

		borrar = new JButton("Borrar Casilla");
		borrar.setBackground(Color.GRAY);
		borrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstance().setTipo(TiposNodos.VACIO);
			}
			
		});
		
		comenzar = new JButton("Comenzar");
		comenzar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!Controlador.CheckSalida || !Controlador.CheckMeta) {
					JOptionPane.showMessageDialog(null, "Debe haber una casilla de salida y una casilla de meta", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					AEstrella algoritmo = new AEstrella(Controlador.getInstance().getCuadricula());
					List<Nodo> caminoSolucion;
					
					if(Controlador.getInstance().getCuadricula().getWaypoints().size() > 0) {
						caminoSolucion = algoritmo.algoritmoAEstrellaWayPoints();
					}
					else {
						caminoSolucion = algoritmo.algoritmoAEstrella();
					}
					
					if(caminoSolucion == null) {
						JOptionPane.showMessageDialog(null, "La meta es inalcanzable", "Error", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						Controlador.getInstance().pintarResultado(caminoSolucion);
					}
				}
			}
		});
		
		reiniciar = new JButton("Reiniciar cuadrícula");
		reiniciar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.getInstance().resetCuadricula();
			}
		});

		add(anyadirSalida);
		add(anyadirMeta);
		add(anyadirProhibido);
		add(anyadirPeligroso);
		add(anyadirWaypoint);
		add(borrar);
		add(comenzar);
		add(reiniciar);
	}
}



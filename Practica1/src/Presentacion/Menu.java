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
import Negocio.Controller;

public class Menu extends JPanel{
		
	private static final long serialVersionUID = 1L;

	private JButton start, finish, waypoint, dangerous, forbidden, reset, play, normal;

	public Menu() {
		this.setPreferredSize(new Dimension(900,150));
		initGUI();
	}

	public void initGUI() {
		this.setLayout(new GridLayout(2,7));

		start = new JButton("Start");
		start.setBackground(Color.GREEN);

		finish = new JButton("Finish");
		finish.setBackground(Color.BLACK);
		finish.setForeground(Color.WHITE);

		forbidden = new JButton("Forbidden");
		forbidden.setBackground(Color.RED);
		forbidden.setForeground(Color.WHITE);

		waypoint = new JButton("Waypoint");
		waypoint.setBackground(Color.CYAN);

		dangerous = new JButton("Dangerous");
		dangerous.setBackground(Color.YELLOW);

		reset = new JButton("Reset");
		reset.setBackground(Color.WHITE);

		play = new JButton("Play");
		play.setBackground(Color.MAGENTA);

		normal = new JButton("Borrar");
		normal.setBackground(Color.ORANGE);

		eventos();

		this.add(start);
		this.add(finish);
		this.add(forbidden);
		this.add(waypoint);
		this.add(dangerous);
		this.add(reset);
		this.add(play);		
		this.add(normal);
	}

	public void eventos() {
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().setTipo("start");
			}

		});

		finish.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().setTipo("finish");
			}

		});

		forbidden.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().setTipo("forbidden");
			}

		});

		waypoint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().setTipo("waypoint");
			}

		});

		dangerous.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().setTipo("dangerous");
			}

		});

		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().resetTablero();
			}

		});

		play.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(Controller.CheckInicio) {
					if(Controller.CheckMeta) {
						AEstrella algorithm = new AEstrella(Controller.getInstance().getTablero());
						List <Nodo> result;
						if(Controller.getInstance().getTablero().getWaypoints().size() > 0) result = algorithm.AEstrellaConWP();
						else result = algorithm.AEstrella();
						Controller.getInstance().paintResult(result);
					}
					else {
						JOptionPane.showMessageDialog(new JPanel(), "Falta indicar una meta!", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(new JPanel(), "Falta indicar un punto de salida!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		normal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.getInstance().setTipo("normal");
			}

		});
	}
}



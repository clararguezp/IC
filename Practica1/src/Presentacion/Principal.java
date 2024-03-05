package Presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Negocio.Nodo;



public class Principal extends JFrame{

	private static final long serialVersionUID = 1L;
	private  JPanel contentPanel;
	private static Principal instance;
	private TableroView tablero;
	private Menu menu;
	
	public Principal(){
		instance = this;
	    initGUI();
	}
	
	public static Principal getInstance() {
		if (instance == null)
			instance = new Principal();
		return instance;
	}
	
	public void initGUI() {
		Dialogo dimensiones = new Dialogo();
		while(!Dialogo.done) {}
		tablero = new TableroView();
		menu = new Menu();
		contentPanel = new JPanel(new BorderLayout());
		contentPanel.add(tablero,BorderLayout.CENTER);
		contentPanel.add(menu,BorderLayout.SOUTH);
		this.setTitle("A_Estrella");
		this.setResizable(false);
		this.setMinimumSize(new Dimension(900,800));
		this.setLocationRelativeTo(null);
		this.add(contentPanel);
		this.setVisible(true);
		this.pack();
	}
	
	public void reset() {
		tablero.reset();
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void paintResult(List<Nodo> sol) {
			tablero.paintResult(sol);
	}
	

}
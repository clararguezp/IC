package Presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Negocio.Nodo;



public class PrincipalView extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private static PrincipalView instance;
	private JPanel principalPanel;
	private CuadriculaView cuadriculaPanel;
	private ConfiguracionView configuracionPanel;
	
	public PrincipalView(){
		instance = this;
	    initGUI();
	}
	
	public static PrincipalView getInstance() {
		if (instance == null)
			instance = new PrincipalView();
		return instance;
	}
	
	public void initGUI() {
		DimensionesTableroView dimensiones = new DimensionesTableroView();
		while(!DimensionesTableroView.done) {}
		
		cuadriculaPanel = new CuadriculaView();
		configuracionPanel = new ConfiguracionView();
		
		principalPanel = new JPanel(new BorderLayout());
		principalPanel.add(cuadriculaPanel,BorderLayout.CENTER);
		principalPanel.add(configuracionPanel,BorderLayout.NORTH);
		
		setTitle("Algoritmo A*");
		setResizable(false);
		setMinimumSize(new Dimension(900,800));
		setLocationRelativeTo(null);
		
		add(principalPanel);
		setVisible(true);
		pack();
	}
	
	public void reset() {
		cuadriculaPanel.reset();
	}

	public void pintarCaminoSolucion(List<Nodo> sol) {
			cuadriculaPanel.pintarCamino(sol);
	}
}
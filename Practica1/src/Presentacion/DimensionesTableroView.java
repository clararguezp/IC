package Presentacion;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;


import Negocio.Controlador;

public class DimensionesTableroView extends JDialog{
	
	private static final long serialVersionUID = 1L;
	
	private JSpinner nFilas;
	private JSpinner nColumnas;
	private JButton confirmar;
	private JLabel aux;
	private JLabel description;
	
	private Controlador ctrl;
	public static boolean done = false;
	
	public DimensionesTableroView() {
		initGUI();
	}
	
	// Funcion para iniciar el dialogo
	public void initGUI() {
		JPanel mainPanel = new JPanel();
		description = new JLabel();
		nFilas = new JSpinner();
		nColumnas = new JSpinner();
		confirmar = new JButton();
		aux = new JLabel();
		
		nFilas.setModel(new SpinnerNumberModel(5, 1, 20, 1));
		nFilas.setPreferredSize(new Dimension(50, 20));
		
		nColumnas.setModel(new SpinnerNumberModel(5, 1, 20, 1));
		nColumnas.setPreferredSize(new Dimension(50, 20));
		
		aux.setText(" X ");
		
		description.setText("Seleccione las dimensiones del tablero: ");
		description.setAlignmentX(CENTER_ALIGNMENT);
		
		confirmar.setText("CONFIRMAR");
		confirmar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int nf = (Integer) nFilas.getValue();
				int nc = (Integer) nColumnas.getValue(); 
				
				ctrl = new Controlador(nf, nc);
				ctrl.setInstance();
				done = true;
				dispose();
			}
		});
		
		setTitle("Dimensiones");
		
		JPanel espacio = new JPanel();
		espacio.setSize(300, 50);
		
		JPanel p1 = new JPanel();
		p1.add(nFilas);
		p1.add(aux);
		p1.add(nColumnas);
		add(p1);
		
		JPanel p2 = new JPanel();
		p2.add(confirmar);
		add(p2);
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.add(espacio);
		mainPanel.add(description);
		mainPanel.add(p1);
		mainPanel.add(p2);
		
		setPreferredSize(new Dimension(300, 200));
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
	}
}

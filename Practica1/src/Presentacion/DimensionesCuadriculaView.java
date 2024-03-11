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

public class DimensionesCuadriculaView extends JDialog{
	
	private static final long serialVersionUID = 1L;
	
	private JSpinner nFilas;
	private JSpinner nColumnas;
	private JButton aceptar;
	private JLabel filasLabel;
	private JLabel columnasLabel;
	private JLabel mensajeLabel;
	
	private Controlador ctrl;
	public static boolean done = false;
	
	public DimensionesCuadriculaView() {
		initGUI();
	}
	
	// Funcion para iniciar el dialogo
	public void initGUI() {
		JPanel mainPanel = new JPanel();
		
		mensajeLabel = new JLabel();
		mensajeLabel.setText("Seleccione las dimensiones de la cuadrícula");
		mensajeLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		filasLabel = new JLabel();	
		filasLabel.setText("FILAS: ");
		
		nFilas = new JSpinner();
		nFilas.setModel(new SpinnerNumberModel(5, 1, 20, 1));
		nFilas.setPreferredSize(new Dimension(50, 20));
		
		columnasLabel = new JLabel();
		columnasLabel.setText("COLUMNAS: ");
		
		nColumnas = new JSpinner();
		nColumnas.setModel(new SpinnerNumberModel(5, 1, 20, 1));
		nColumnas.setPreferredSize(new Dimension(50, 20));
		
		aceptar = new JButton();
		aceptar.setText("ACEPTAR");
		aceptar.addActionListener(new ActionListener() {

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
		
		setTitle("Dimensiones de la cuadrícula");
		
		JPanel espacio = new JPanel();
		espacio.setSize(300, 50);
		
		JPanel panelSpinners = new JPanel();
		panelSpinners.add(filasLabel);
		panelSpinners.add(nFilas);
		panelSpinners.add(columnasLabel);
		panelSpinners.add(nColumnas);
		
		JPanel panelBoton = new JPanel();
		panelBoton.add(aceptar);
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		setContentPane(mainPanel);
		
		mainPanel.add(espacio);
		mainPanel.add(mensajeLabel);
		mainPanel.add(panelSpinners);
		mainPanel.add(panelBoton);
		
		setPreferredSize(new Dimension(300, 200));
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setModal(true);
		setVisible(true);
	}
}

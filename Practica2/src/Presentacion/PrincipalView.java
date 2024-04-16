package Presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.ID3;
import Negocio.LecturaFicheros;
import Negocio.Nodo;

public class PrincipalView extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private JPanel principalPanel;
	private JPanel configuracionPanel;
	
	private JButton cargarAtributosButton;
	private JButton cargarEjemplosButton;
	private JButton calcularSolucionButton;
	
	private ID3 algoritmoID3;
	private SolucionView solucionPanel;
	
	public PrincipalView(){
	    initGUI();
	}
	
	public void initGUI() {
		configuracionPanel = new JPanel();
		algoritmoID3 = new ID3();
		
		cargarAtributosButton = new JButton("Cargar Atributos");
		cargarAtributosButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser();
				filechooser.setCurrentDirectory(new File(new File("./resources/examples/").getAbsolutePath()));
				
				int option = filechooser.showOpenDialog(filechooser);
				if(option == filechooser.APPROVE_OPTION) {
					try {
						File fichero = filechooser.getSelectedFile();
						ArrayList<String> atributos = LecturaFicheros.leerAtributos(fichero);
						algoritmoID3.setAtributos(atributos);
						
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "ERROR: No se han podido cargar los atributos del juego", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
		});

		cargarEjemplosButton = new JButton("Cargar Ejemplos");
		cargarEjemplosButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser();
				filechooser.setCurrentDirectory(new File(new File("./resources/examples/").getAbsolutePath()));
				
				int option = filechooser.showOpenDialog(filechooser);
				if(option == filechooser.APPROVE_OPTION) {
					try {
						File fichero = filechooser.getSelectedFile();
						ArrayList<ArrayList<String>> ejemplos = LecturaFicheros.leerJuego(fichero);
						algoritmoID3.setEjemplos(ejemplos);
						
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "ERROR: No se ha podido cargar el juego", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
		});

		calcularSolucionButton = new JButton("Calcular Solución");
		calcularSolucionButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Nodo solucion;
				algoritmoID3.setValoresPosibles();
				solucion = algoritmoID3.algoritmoID3(algoritmoID3.getAtributos(), algoritmoID3.getEjemplos());
				solucionPanel = new SolucionView(solucion);
				principalPanel.add(solucionPanel, BorderLayout.CENTER);
			}
			
		});

		configuracionPanel.add(cargarAtributosButton);
		configuracionPanel.add(cargarEjemplosButton);
		configuracionPanel.add(calcularSolucionButton);
		
		principalPanel = new JPanel(new BorderLayout());
		principalPanel.add(configuracionPanel, BorderLayout.NORTH);
		
		setTitle("Algoritmo ID3");
		setResizable(false);
		setMinimumSize(new Dimension(900,800));
		setLocationRelativeTo(null);
		
		add(principalPanel);
		setVisible(true);
		pack();
	}
}

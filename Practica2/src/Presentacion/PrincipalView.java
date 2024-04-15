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
	private ID3 ID3Alg;
	private SolutionView solution;
	
	public PrincipalView(){
	    initGUI();
	}
	
	public void initGUI() {
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel buttonsPanel = new JPanel(new BorderLayout());
		JPanel solPanel = new JPanel(new BorderLayout());
		
		JButton cargarAtributos = new JButton("Cargar Atributos");
		JButton cargarEjemplos = new JButton("Cargar Ejemplos");
		JButton CalcularButton = new JButton("Calcular");
		
		ID3Alg = new ID3();
		
		cargarAtributos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser();
				filechooser.setCurrentDirectory(new File(new File("./resources/examples/").getAbsolutePath()));
				
				int option = filechooser.showOpenDialog(filechooser);
				if(option == filechooser.APPROVE_OPTION) {
					try {
						File fichero = filechooser.getSelectedFile();
						ArrayList<String> atributos = LecturaFicheros.leerAtributos(fichero);
						ID3Alg.setAtributos(atributos);
						
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "ERROR AL CARGAR LOS ATRIBUTOS", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		cargarEjemplos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new File(new File("./resources/examples/").getAbsolutePath()));
				
				int op = fc.showOpenDialog(fc);
				if(op == fc.APPROVE_OPTION) {
					try {
						File fichero = fc.getSelectedFile();
						ArrayList<String> ejemplos = LecturaFicheros.leerJuego(fichero);
						ID3Alg.setEjemplos(ejemplos);
						
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "ERROR AL CARGAR LOS EJEMPLOS", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		CalcularButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Nodo solucion;
				ID3Alg.setValoresPosibles();
				solucion = ID3Alg.algoritmoID3(ID3Alg.getAtributos(), ID3Alg.getEjemplos());
				solution = new SolutionView(solucion);
				solPanel.add(solution);
			}
		});
		
		buttonsPanel.add(cargarAtributos);
		buttonsPanel.add(cargarEjemplos);
		buttonsPanel.add(CalcularButton);
		
		mainPanel.add(buttonsPanel,BorderLayout.NORTH);
		mainPanel.add(solPanel,BorderLayout.CENTER);
		
		setTitle("ID3");
		setResizable(false);
		setMinimumSize(new Dimension(900,800));
		setLocationRelativeTo(null);
		add(mainPanel);
		setVisible(true);
		pack();
	}

}

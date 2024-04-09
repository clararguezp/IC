package Negocio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LecturaFicheros {
	
	public static ArrayList<String> leerAtributos(File fichero) throws IOException {
		FileReader reader = new FileReader(fichero);
		try (BufferedReader buffer = new BufferedReader(reader)) {
			ArrayList<String> atributos = new ArrayList<String>();
			
			String linea = buffer.readLine();
			String[] aux = linea.split(",");
			    
			for (String palabra : aux) {
				atributos.add(palabra.trim());
			}
			
			return atributos;
		}
	}
	
	public static ArrayList<String> leerJuego(File fichero) throws IOException {
		FileReader reader = new FileReader(fichero);
		try (BufferedReader buffer = new BufferedReader(reader)) {
			ArrayList<String> juego = new ArrayList<String>();
			
			String linea;
			while((linea = buffer.readLine()) != null) {
				String[] aux = linea.split(",");
				
				for (String palabra : aux) {
					juego.add(palabra.trim());
				}
			}
			
			return juego;
		}
	}
}

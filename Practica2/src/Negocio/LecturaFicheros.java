package Negocio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
			
			buffer.close();
			reader.close();
			
			return atributos;
		}
	}
	
	public static ArrayList<ArrayList<String>> leerJuego(File fichero) throws IOException {
		FileReader reader = new FileReader(fichero);
		try (BufferedReader buffer = new BufferedReader(reader)) {
			ArrayList<ArrayList<String>> juego = new ArrayList<ArrayList<String>>();
			
			String linea;
			while((linea = buffer.readLine()) != null) {
				String[] auxArray = linea.split(",");
				List<String> auxList = Arrays.asList(auxArray);
				ArrayList<String> elemento = new ArrayList<String>(auxList);
				juego.add(elemento);
			}
			
			buffer.close();
			reader.close();
			
			return juego;
		}
	}
}

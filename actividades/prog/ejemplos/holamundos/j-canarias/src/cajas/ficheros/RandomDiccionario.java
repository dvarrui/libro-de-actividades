package cajas.ficheros;

import java.io.*;
import java.util.*;

public class RandomDiccionario {
	// Atributos
	private String fichero;

	private String palabras[];

	private Random azar;

	public RandomDiccionario(String pFichero) {
		String str;
		int i = 0;

		// Inicializaci�n de atributos
		fichero = pFichero;
		palabras = new String[this.contarPalabras()];
		azar = new Random();

		try {
			BufferedReader br = new BufferedReader(new FileReader(fichero));
			str = br.readLine();
			while (str != null) {
				palabras[i++] = str;
				str = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public String getNombreFichero() {
		return fichero;
	}

	public String getPalabra() {
		return palabras[azar.nextInt(palabras.length)];
	}

	public void mostrarFichero() {
		String str;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fichero));
			str = br.readLine();
			while (str != null) {
				System.out.println(str);
				str = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public void crearFichero(String pFichero, int numLineas) {
		try {
			PrintWriter pw = new PrintWriter(new File(pFichero));
			for (int i = 0; i < numLineas; i++) {
				pw.println(this.getPalabra());
			}
			pw.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public void crearFichero(String pFichero) {
		crearFichero(pFichero,azar.nextInt(19)+1);
	}

	private int contarPalabras() {
		String str;
		int cont = 0;

		try {
			BufferedReader br = new BufferedReader(new FileReader(fichero));
			str = br.readLine();
			while (str != null) {
				cont++;
				str = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			System.err.println(e);
		}
		return cont;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RandomDiccionario d = new RandomDiccionario("datos/diccionario.txt");
		System.out.println("getNombreFichero()=" + d.getNombreFichero());
		System.out.println("getPalabra()=" + d.getPalabra());
		d.mostrarFichero();
		d.crearFichero("datos/prueba.txt", 10);
		d.crearFichero("datos/prueba2.txt");
	}

}

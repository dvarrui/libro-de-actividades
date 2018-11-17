package cajas.ficheros;

import java.io.*;

//import  java.util.*;

/**
 * Se pasan dos argumentos: (1) Fichero origen (2) Fichero destino (3) Tipo de
 * coloreado (opcional)
 * 
 * Se lee el fichero origen y se transforma para poder visualizarlo corectamente
 * en un navegador.
 * 
 * @author David Vargas Ruiz
 * @version 1.0.0
 */
public class ConvierteFichero {
	/**
	 * Método main
	 */
	public static void main(String args[]) {
		if (args.length<2)
			System.err.println("Faltan argumentos!");
		else {
			ConvierteFichero conv = new ConvierteFichero();
			conv.setNombreOrigen(args[0]);
			conv.setNombreDestino(args[1]);
			conv.setColor(args[2].toCharArray()[0]);
			conv.ejecutar();
		}
	}

	char color = 'n'; // n=ninguno, j=java, b=basic

	BufferedReader entrada;

	int fin;

	String nombreDestino; // Nombre del fichero Destino

	String nombreOrigen; // Nombre del fichero Origen

	PrintWriter salida;

	/**
	 * Constructor sin argumentos
	 */
	public ConvierteFichero() {
	}

	/**
	 * Abrir: Abrir el fichero origen y crear el fichero destino
	 * 
	 * @return Devuelve <b>false</b> si ha ocurrido algn problema
	 */
	private boolean abrir() {
		try {
			entrada = new BufferedReader(new FileReader(nombreOrigen));
			salida = new PrintWriter(new BufferedWriter(new FileWriter(
					nombreDestino)));
			fin = 0;
			return true;
		} catch (Exception e) {
			System.err.println("ERROR(abrir): " + e);
			return false;
		}
	}

	/**
	 * Cierra los ficheros origen y destino
	 * 
	 * @return Devuelve <b>false</b> si ha ocurrido algn problema
	 */
	private boolean cerrar() {
		try {
			entrada.close();
			salida.close();
			return true;
		} catch (Exception e) {
			System.err.println("ERROR(cerrar): " + e);
			return false;
		}
	}

	private String colorearLinea(StringBuffer pLinea) {
		StringBuffer salida = new StringBuffer(500);
		String linea = new String(pLinea);

		if (color == 'j') {
			if (linea.trim().startsWith("//"))
				salida.append("<div class='comentario'>" + linea + "</div>");
			else
				salida.append(linea);
			return new String(salida);
		}
		return linea;
	}

	/**
	 * Convierte una entrada de String TXT en String HTML
	 * 
	 * @return Devuelve un tipo <b>String</b>
	 */
	private String convierteTextoToHtml(String entrada) {
		StringBuffer salida = new StringBuffer(500); // entrada.length());
		char c;

		for (int i = 0; i < entrada.length(); i++) {
			c = entrada.charAt(i);
			if (c == '<') {
				salida.append("<b>&lt;");
			} else if (c == '>') {
				salida.append("&gt;</b>");
			} else if (c == '"') {
				salida.append("&quot;");
			} else if (c == '&') {
				salida.append("&amp;");
			} else {
				salida.append(c);
			}
		}
		salida.append("<br>");
		return (colorearLinea(salida));
	}

	public void ejecutar() {
		String str;
		abrir();
		while (!getFin()) {
			str = getSiguiente();
			System.out.println(str);
			setSiguiente(str);
		}
		cerrar();
	}

	public char getColor() {
		return color;
	}

	private boolean getFin() {
		if (fin == 1)
			return true;
		return false;
	}

	public String getNombreDestino() {
		return nombreDestino;
	}

	public String getNombreOrigen() {
		return nombreOrigen;
	}

	/**
	 * Lee una línea del fichero origen convertido de TXT a HTML
	 * 
	 * @return Devuelve un tipo <b>String</b>
	 */
	private String getSiguiente() {
		try {
			if (fin == 0) {
				String str;
				str = new String(entrada.readLine());
				if (str != null) {
					/* Convierte la fila de HTML a TXT */
					return (convierteTextoToHtml(str));
				}
				fin = 1;
				return " ";
			}
		} catch (Exception e) {
			fin = 1;
			return "";
		}
		return "";
	}

	public void setColor(char pColor) {
		color = pColor;
		if (pColor != 'j' && pColor != 'b' && pColor != 'n')
			pColor = 'n';
	}

	public void setNombreDestino(String pNombre) {
		nombreDestino = new String(pNombre);
	}

	/**
	 * Procedimientos SET y GET
	 */
	public void setNombreOrigen(String pNombre) {
		nombreOrigen = new String(pNombre);
	}

	/**
	 * Graba una línea en el fichero destino
	 * 
	 * @return Devuelve <b>false</b> si ah ocurrido algn problema
	 */
	public boolean setSiguiente(String pSiguiente) {
		try {
			salida.println(pSiguiente);
			return true;
		} catch (Exception e) {
			System.err.println("ERROR(setSiguiente): " + e);
		}
		return false;
	}
}

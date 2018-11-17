package david.tenerife.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Utilidad para tratar con ficheros y directorios:
 * (a) Hacer copias de ficheros
 * (b) Copiar un programa java cambiando el paquete
 * (c) Crear una ruta si no existe
 * (d) Generar un fichero fich.println desde plantilla
 * 
 * @author David Vargas Ruiz
 * @version 1.0.0 - 20051130
 *
 */
public class Ficheros {

	public static void copiarFichero(String origen, String destino) {
		String str;
		try {
			BufferedReader entrada = new BufferedReader(new FileReader(origen));
			PrintWriter fich = new PrintWriter(destino);
			str = entrada.readLine();
			while (str != null) {
				fich.println(str);
				str = entrada.readLine();
			}
			entrada.close();
			fich.close();
		} catch (Exception e) {
			System.err.println("Error:" + e);
		}
	}

	public static void copiarFicheroJava(String origen, String destino,
			String paquete) {
		String str;
		try {
			BufferedReader entrada = new BufferedReader(new FileReader(origen));
			PrintWriter fich = new PrintWriter(destino);
			str = entrada.readLine();
			while (str != null) {
				if (str.startsWith("package "))
					fich.println("package " + paquete + ";");
				else if (str.startsWith("import david.tenerife")) {
					// TODO: Modificar
					fich.println("import david.proyecto"
							+ str.substring("import david.tenerife".length()));
				} else
					fich.println(str);
				str = entrada.readLine();
			}
			entrada.close();
			fich.close();
		} catch (Exception e) {
			System.err.println("Error:" + e);
		}
	}

	public static void crearRuta(String path) {
		File f = new File(path);
		boolean flag = true;

		if (!f.exists()) {
			StringBuilder sb = new StringBuilder(path.length());
			StringTokenizer st = new StringTokenizer(path, "/", false);
			while (st.hasMoreElements()) {
				if (flag) {
					sb.append(st.nextElement());
					flag = false;
				} else
					sb.append("/" + st.nextElement());
				f = new File(sb.toString());
				if (!f.exists()) {
					f.mkdir();
				}
			}
		}
	}

	
	public static void generarFicheroPrintln(String origen, String destino) {
		String linea;
		try {
			BufferedReader entrada = new BufferedReader(new FileReader(origen));
			PrintWriter salida = new PrintWriter(destino);
			linea = entrada.readLine();
			while (linea != null) {
				
				if (linea.trim().startsWith("//")) {
					salida.println("fich.println(\""+linea+"\");");
				} else if (linea.equals("")) {
					salida.println("fich.println();");
				} else {
					linea = linea.replaceAll("\"","\\\"");
					salida.println("fich.println(\""+linea+"\");");
				}
				linea = entrada.readLine();
			}
			entrada.close();
			salida.close();
		} catch (Exception e) {
			System.err.println("Error:" + e);
		}
	}

	
	public static void main(String[] args) {
		Ficheros.generarFicheroPrintln("src/david/tenerife/swing/frmNuevo.java","tmp/frmNuevo.java");
	}
}

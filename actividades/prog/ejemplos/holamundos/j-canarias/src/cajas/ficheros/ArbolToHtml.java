package cajas.ficheros;

import java.io.*;

public class ArbolToHtml {
	public final static int MULTIPLE = 2;

	public final static int RECURSIVO = 1;

	public final static int SIMPLE = 0;

	public static void ejecutar(String directorio, String ficheroSalida,
			int modo) {
		File dir = new File(directorio);
		PrintWriter salida;

		try {
			if (!dir.isDirectory())
				dir = File.listRoots()[0];

			salida = new PrintWriter(new BufferedWriter(new FileWriter(
					ficheroSalida)));

			if (modo == ArbolToHtml.SIMPLE)
				ejecutarSimple(dir, salida);
			else if (modo == ArbolToHtml.RECURSIVO)
				ejecutarRecursivo(dir, salida);

		} catch (Exception e) {
			System.err.println("Error:" + e);
		}
	}

	public static void ejecutarRecursivo(File dir, PrintWriter salida) {
		ejecutarRecursivo(dir, salida, 0);
	}

	private static int ejecutarRecursivo(File dir, PrintWriter salida,
			int level) {
		// Declaración de variables
		File[] archivos = dir.listFiles();
		int contador = 0;

		try {
			// Mostrar contenido del directorio
			if (level == 0) {
				salida.println("<html>");
				salida.println("<head><title>Indice Recursivo</title></head>");
				salida.println("<body>");
				salida.println("<b>"+dir.getAbsolutePath() + "</b>/<br>");
			}
			else salida.println(dir.getName()+"/");
			salida.println("<ul>");

			for (int i = 0; i < archivos.length; i++) {

				if (!archivos[i].isHidden()) {
					contador++;

					salida.println("<li>");

					if (archivos[i].isDirectory()) {
						contador = contador + ejecutarRecursivo(archivos[i], salida, level + 1);
					} else {
						salida.println("<a href=\""+ archivos[i].getAbsolutePath()+"\">");
						salida.println(archivos[i].getName());
						salida.println("</a>");
					}
					salida.println("</li>");
				}

			}
			salida.println("</ul>");
			if (level == 0) {
				salida.println("total " + contador + "<br>");
				salida.println("<body>");
				salida.close();
			}
		} catch (Exception e) {
			System.err.println("ERROR: " + e);
		}
		return contador;
	}

	public static void ejecutarSimple(File dir, PrintWriter salida) {
		// Declaración de variables
		File[] archivos = dir.listFiles();
		int contador = 0;

		try {
			// Mostrar contenido del directorio
			salida.println("<html>");
			salida.println("<head><title>Indice Simple</title></head>");
			salida.println("<body>");
			salida.println(dir.getAbsolutePath() + "/<br>");
			salida.println("<ul>");

			for (int i = 0; i < archivos.length; i++) {
				if (!archivos[i].isHidden()) {
					contador++;
					salida.println("<li>");
					if (archivos[i].isDirectory()) {
						salida.print("<a href=\"\">" + archivos[i].getName());
						salida.println("/</a>");
					} else {
						salida.print(archivos[i].getName());
						salida.println("");
					}
					salida.println("</li>");
				}
			}
			salida.println("</ul>");
			salida.println("total " + contador + "<br>");
			salida.println("<body>");
			salida.close();
		} catch (Exception e) {
			System.err.println("ERROR: " + e);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// ArbolToHtml d = new ArbolToHtml("c:/temp");
		// d.ls();
		ArbolToHtml.ejecutar("/home/david", "tmp/indiceSimple.html",
				ArbolToHtml.SIMPLE);
		ArbolToHtml.ejecutar("/home/david/workspace/j-canarias.p/src", "tmp/indiceRecursivo.html",
				ArbolToHtml.RECURSIVO);
	}
}

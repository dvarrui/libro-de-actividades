package david.orotava.lib;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Esta clase representa lo elementos dentro de un contenedor.
 * Por ejemplo: archivos y/o ficheros dentro de un directorio.
 * @author David Vargas Ruiz
 * @version 1.0.0 - 20051213
 * 
 */
public class Elemento {
	public static int MOSTRAR_HTML = 1;

	public static int MOSTRAR_TXT = 2;

	private File archivo;

	private TreeMap enlacesEntrada;

	private TreeMap enlacesSalida;

	private boolean error;

	private StringBuffer log;

	private String tipo;

	public Elemento(File pArchivo) {
		archivo = pArchivo;
		error = false;
		log = new StringBuffer();
		tipo = establecerTipo();
		if (tipo.equals("err")) {
			error = true;
			log.append("Tipo fichero incorrecto.\n");
		}
		enlacesEntrada = new TreeMap();
		enlacesSalida = new TreeMap();
	}

	private String establecerTipo() {
		String tipo;
		if (archivo.isDirectory())
			tipo = "dir";
		else if (archivo.getName().toLowerCase().endsWith(".css"))
			tipo = "css";
		else if (archivo.getName().toLowerCase().endsWith(".htm")
				|| archivo.getName().toLowerCase().endsWith(".html")
				|| archivo.getName().toLowerCase().endsWith(".shtml"))
			tipo = "html";
		else if (archivo.getName().toLowerCase().endsWith(".gif")
				|| archivo.getName().toLowerCase().endsWith(".jpg")
				|| archivo.getName().toLowerCase().endsWith(".jpeg")
				|| archivo.getName().toLowerCase().endsWith(".png")
				|| archivo.getName().toLowerCase().endsWith(".bmp"))
			tipo = "img";
		else if (archivo.getName().toLowerCase().endsWith(".jsp"))
			tipo = "jsp";
		else if (archivo.getName().toLowerCase().endsWith(".java"))
			tipo = "java";
		else if (archivo.getName().toLowerCase().endsWith(".php"))
			tipo = "php";
		else if (archivo.getName().toLowerCase().endsWith(".sql"))
			tipo = "sql";
		else if (archivo.getName().toLowerCase().endsWith(".swf"))
			tipo = "fla";
		else if (archivo.getName().toLowerCase().endsWith(".txt"))
			tipo = "txt";
		else if (archivo.getName().toLowerCase().endsWith(".vb"))
			tipo = "vb";
		else if (archivo.getName().toLowerCase().endsWith(".xml"))
			tipo = "xml";
		else
			tipo = "err";
		return tipo;
	}

	public TreeMap getEnlacesEntrada() {
		return enlacesEntrada;
	}

	public TreeMap getEnlacesSalida() {
		return enlacesSalida;
	}

	public File getFile() {
		return archivo;
	}

	public String getLog() {
		return log.toString();
	}

	public String getParent() {
		return archivo.getParent();
	}
	
	public String getNombre() {
		return archivo.getName();
	}

	public String getNombreAbsoluto() {
		try {
			return archivo.getCanonicalPath();
		} catch (Exception e) {
			System.err.println(e);
		}
		return null;
	}

	public String getTipo() {
		return tipo;
	}

	public boolean isDirectorio() {
		if (tipo.equals("dir"))
			return true;
		return false;
	}

	public boolean isError() {
		if (error)
			return true;
		try {
			Enlace e;
			Iterator i = enlacesEntrada.values().iterator();

			while (i.hasNext()) {
				e = (Enlace) i.next();
				if (e.isError()) {
					error = true;
				}
			}

			i = enlacesSalida.values().iterator();

			while (i.hasNext()) {
				e = (Enlace) i.next();
				if (e.isError()) {
					error = true;
				}
			}

		} catch (Exception e) {
			System.err.println(e);
		}
		return error;
	}

	public void leerEnlaces(Contenedor c) {
		String linea = null;
		String str = null;
		int i = -1;

		if (tipo.equals("html")) {
			// Se revisan páginas HTML
			try {
				BufferedReader br = new BufferedReader(new FileReader(archivo
						.getCanonicalFile()));
				linea = br.readLine();

				// Patrones y emparejadores
				Pattern p1 = Pattern.compile("href=\"*\"");
				Pattern p2 = Pattern.compile("src=\"*\"");
				Matcher m1;
				Matcher m2;

				while (linea != null) {
					// Buscar HREF
					m1 = p1.matcher(linea.toLowerCase());
					if (m1.find()) {
						str = linea.substring(m1.end());
						i = str.indexOf("\"");
						if (i > 0)
							str = str.substring(0, i);

						Enlace l = new Enlace(str, archivo.getParent(), true);
						if (i < 0) {
							l.setError(true);
							l.setLog("Dirección mal formada.");
						}

						enlacesSalida.put(l.getNombreAbsoluto(), l);

						if (!l.isAncla() && !l.isError() && l.isLocal()) {
							// Si el enlace de salida no es un ancla y no tiene
							// errores y es local
							// Esto es existe el archivo dentro del directorio
							// raiz
							if (c.containsElemento(l.getNombreAbsoluto())) {
								// Buscamos si existe en elemento dentro del
								// contendor
								Elemento e2 = c.getElemento(l
										.getNombreAbsoluto());
								Enlace l2 = new Enlace(this.getNombre(),
										archivo.getParent(), true);
								e2.getEnlacesEntrada().put(
										l2.getNombreAbsoluto(), l2);
							} else {
								this.error = true;
								this.setLog("Salida: No existe el elemento '"
										+ l.getNombreAbsoluto()
										+ "' dentro del contenedor.\n");
							}
						}
					}
					// Buscar SRC
					m2 = p2.matcher(linea.toLowerCase());
					if (m2.find()) {
						str = linea.substring(m2.end());
						i = str.indexOf("\"");
						if (i > 0)
							str = str.substring(0, i);

						Enlace l = new Enlace(str, archivo.getParent(), false);
						if (i < 0) {
							l.setError(true);
							l.setLog("Dirección mal formada.");
						}

						enlacesSalida.put(l.getNombreAbsoluto(), l);

						if (!l.isAncla() && !l.isError() && l.isLocal()) {
							// Si el enlace de salida no es un ancla y no tiene
							// errores y es local
							// Esto es existe el archivo dentro del directorio
							// raiz
							if (c.containsElemento(l.getNombreAbsoluto())) {
								// Buscamos si existe en elemento dentro del
								// contendor
								Elemento e2 = c.getElemento(l
										.getNombreAbsoluto());
								Enlace l2 = new Enlace(this.getNombre(),
										archivo.getParent(), false);
								e2.getEnlacesEntrada().put(
										l2.getNombreAbsoluto(), l2);
							} else {
								this.error = true;
								this.setLog("Salida: No existe el elemento '"
										+ l.getNombreAbsoluto()
										+ "' dentro del contenedor.\n");
							}
						}
					}

					// Continuar la búsqueda
					linea = br.readLine();
				}
				br.close();
			} catch (Exception e) {
				System.err.println("Elemento.leerEnlaces():" + e + "," + linea
						+ "," + str + "," + i);
			}

		}
	}

	public void mostrar() {
		mostrarTxt(System.out);
	}

	public void mostrar(int tipoSalida) {
		mostrar(System.out, tipoSalida);
	}

	public void mostrar(PrintStream out) {
		mostrarTxt(out);
	}

	public void mostrar(PrintStream out, int tipoSalida) {
		if (tipoSalida == Elemento.MOSTRAR_TXT)
			mostrarTxt(out);
		else if (tipoSalida == Elemento.MOSTRAR_HTML)
			mostrarHtml(out);
	}

	private void mostrarHtml(PrintStream out) {
		try {
			out.println("<hr>");
			out.println("<h3>Elemento  : " + this.getNombreAbsoluto() + "("
					+ getTipo() + ")</h3>");

			if (error) {
				out.println("<h4>Hay Errores</h4>");
				out.println("<p>" + this.getLog() + "</p>");
			}

			out.println("<ul>");
			Enlace l;
			Iterator i;

			i = enlacesEntrada.values().iterator();
			while (i.hasNext()) {
				l = (Enlace) i.next();
				if (!l.isElemento())
					out.println("<li><b>- Componente de :</b>"
							+ l.getNombreAbsoluto() + "</li>");
			}

			i = enlacesEntrada.values().iterator();
			while (i.hasNext()) {
				l = (Enlace) i.next();
				if (l.isElemento())
					out.println("<li><b>< Entrada    :</b>"
							+ l.getNombreAbsoluto() + "</li>");
			}

			i = enlacesSalida.values().iterator();
			while (i.hasNext()) {
				l = (Enlace) i.next();
				if (l.isElemento())
					out.println("<li><b>> Salida     :</b>"
							+ l.getNombreAbsoluto() + "</li>");
			}

			i = enlacesSalida.values().iterator();
			while (i.hasNext()) {
				l = (Enlace) i.next();
				if (!l.isElemento())
					out.println("<li><b>+ Incluye componente :</b>"
							+ l.getNombreAbsoluto() + "</li>");
			}
			out.println("</ul>");

		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private void mostrarTxt(PrintStream out) {
		try {
			out.println();
			out.println("----------------------------------------");
			out.println("Elemento  : " + this.getNombreAbsoluto() + "("
					+ getTipo() + ")");

			if (error) {
				out.println(" ! Errores   :");
				out.println(this.getLog());
			}

			Enlace l;
			Iterator i;

			i = enlacesEntrada.values().iterator();
			while (i.hasNext()) {
				l = (Enlace) i.next();
				if (!l.isElemento())
					out.println(" - Componente de : " + l.getNombreAbsoluto());
			}

			i = enlacesEntrada.values().iterator();
			while (i.hasNext()) {
				l = (Enlace) i.next();
				if (l.isElemento())
					out.println(" < Entrada    : " + l.getNombreAbsoluto());
			}

			i = enlacesSalida.values().iterator();
			while (i.hasNext()) {
				l = (Enlace) i.next();
				if (l.isElemento())
					out.println(" > Salida     : " + l.getNombreAbsoluto());
			}

			i = enlacesSalida.values().iterator();
			while (i.hasNext()) {
				l = (Enlace) i.next();
				if (!l.isElemento())
					out.println(" + Incluye componente : "
							+ l.getNombreAbsoluto());
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public void setLog(String texto) {
		log.append(texto);
	}

	public String toCadena() {
		return getNombreAbsoluto() + "  (" + getTipo() + ")";
	}

}

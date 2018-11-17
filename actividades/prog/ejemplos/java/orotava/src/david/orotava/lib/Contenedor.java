package david.orotava.lib;

import java.io.File;
import java.io.PrintStream;
import java.util.TreeMap;
import java.util.Iterator;

/**
 * Esta clase representa un objeto que contiene elementos:
 *    (a) web: Contiene elementos de una web.
 *    (b) dir: Contiene elementos de un sistema de ficheros.
 * 
 * @author David Vargas Ruiz
 * @version 1.0.0 - 20051213
 * 
 */
public class Contenedor {
	public static final int TIPO_WEB=1;
	public static final int TIPO_DIR=2;
	
	public static void main(String[] args) {
		try {
			Contenedor c = new Contenedor();
			c.setDirRaiz("/home/david/web/cesarmanrique/9.tomcat");
			c.setSalida(System.out);
			c.setTipo(Contenedor.TIPO_DIR);
			c.leer();
			//c.mostrar(new PrintStream("prueba.html"), Elemento.MOSTRAR_HTML);
			c.mostrar();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private File dirRaiz;
	private boolean error;
	private TreeMap lista;
	private PrintStream salida;
	private int tipo;

	public Contenedor() {
		lista = new TreeMap();
		error = false;
		tipo = TIPO_WEB;
	}

	public boolean containsElemento(String key) {
		return lista.containsKey(key);
	}

	public File getDirRaiz() {
		return dirRaiz;
	}

	public Elemento getElemento(String key) {
		return (Elemento) lista.get(key);
	}

	public TreeMap getLista() {
		return lista;
	}

	public PrintStream getSalida() {
		return salida;
	}

	public boolean isError() {
		if (error)
			return true;
		try {
			Elemento e;
			Iterator i = lista.values().iterator();

			while (i.hasNext()) {
				e = (Elemento) i.next();
				if (e.isError()) {
					error = true;
					return error;
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		return error;
	}

	public void leer() {
		this.leerElementos();
		if (this.tipo==TIPO_WEB) {
		   this.leerEnlaces();
		   this.revisar();
		}
	}

	private void leerElementos() {
		File ficheros[];
		Elemento we;

		try {
			ficheros = dirRaiz.listFiles();
			for (int i = 0; i < ficheros.length; i++) {
				we = new Elemento(ficheros[i]);

				if (we.getNombreAbsoluto().startsWith(
						dirRaiz.getCanonicalPath())) {
					// key,value
					lista.put(we.getNombreAbsoluto(), we);
					if (we.isDirectorio()) {
						Contenedor wc = new Contenedor();
						wc.setDirRaiz(we.getNombreAbsoluto());
						wc.setLista(lista);
						wc.leerElementos();
						lista = wc.getLista();
					}
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private void leerEnlaces() {
		try {
			Elemento we;
			Iterator i = lista.values().iterator();

			while (i.hasNext()) {
				we = (Elemento) i.next();
				we.leerEnlaces(this);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public void mostrar() {
		mostrar(Elemento.MOSTRAR_TXT);
	}

	public void mostrar(int tipoSalida) {
		PrintStream out = this.getSalida();
		if (this.getSalida() == null)
			out = System.out;
		mostrar(out, tipoSalida);
	}

	public void mostrar(PrintStream out, int tipoSalida) {
		if (this.tipo==TIPO_WEB) {
			mostrarContenedorWEB(out,tipoSalida);
		} else {
			//Salida de otro tipo
			mostrarContenedorDIR(out,tipoSalida);
		}
	}
	
	
	private void mostrarContenedorDIR(PrintStream out, int tipoSalida) {
		try {
			Elemento e;
			Iterator i;

			// Cabecera HTML
			if (tipoSalida == Elemento.MOSTRAR_HTML) {
				out.println("<html><head></head><body>");
			}

			//Listado
			i = lista.values().iterator();
			while (i.hasNext()) {
				e = (Elemento) i.next();
				if (tipoSalida == Elemento.MOSTRAR_HTML) {
					out.println("<li>" + e.getNombreAbsoluto() + "</li>");
				} else {
					out.println(e.getParent()+"/"+e.getNombre());
				}
			}

			//Cerrar HTML
			if (tipoSalida == Elemento.MOSTRAR_HTML) {
				out.println("</body></html>");
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	
    private void mostrarContenedorWEB(PrintStream out, int tipoSalida) {
		try {
			Elemento e;
			Iterator i;

			// Cebacera
			if (tipoSalida == Elemento.MOSTRAR_HTML) {
				out.println("<html><head></head><body>");
			}

			// Resumen
			if (tipoSalida == Elemento.MOSTRAR_HTML) {
				out.println("<h2>Resumen</h2>");
				out.println("Número de elementos:" + this.lista.size());
			} else if (tipoSalida == Elemento.MOSTRAR_TXT) {
				out.println("=======");
				out.println("RESUMEN");
				out.println("=======");
				out.println("Número de elementos:" + this.lista.size());
			}

			if (this.isError()) {
				if (tipoSalida == Elemento.MOSTRAR_HTML) {
					out.println("<h3>Existen Errores</h3><ul>");
				} else
					out.println("Existen errores");
			}

			i = lista.values().iterator();
			while (i.hasNext()) {
				e = (Elemento) i.next();
				if (!e.isDirectorio() && e.isError()) {
					if (tipoSalida == Elemento.MOSTRAR_HTML) {
						out.println("<li>" + e.getNombreAbsoluto() + "<br>");
						out.println(e.getLog() + "</li>");
					} else {
						out.println(e.getNombreAbsoluto());
						out.println(e.getLog());
					}
				}
			}

			if (this.isError()) {
				if (tipoSalida == Elemento.MOSTRAR_HTML) {
					out.println("</ul>");
				}
			}

			// Informe detallado
			if (tipoSalida == Elemento.MOSTRAR_HTML) {
				out.println("<h2>Informe detallado</h2>");
			} else if (tipoSalida == Elemento.MOSTRAR_TXT) {
				out.println("=================");
				out.println("INFORME DETALLADO");
				out.println("=================");
			}

			i = lista.values().iterator();
			while (i.hasNext()) {
				e = (Elemento) i.next();
				if (!e.isDirectorio()) {
					e.mostrar(out, tipoSalida);
				}
			}

			if (tipoSalida == Elemento.MOSTRAR_HTML) {
				out.println("</body></html>");
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

    
	private void revisar() {
		try {
			Elemento e;
			Iterator i;

			i = lista.values().iterator();
			while (i.hasNext()) {
				e = (Elemento) i.next();
				if (e.getEnlacesEntrada().size() == 0
						&& !e.getNombreAbsoluto().equals(
								dirRaiz.getCanonicalPath() + "index.html")) {
					e.setError(true);
					e.setLog("No tiene entradas.\n");
				}
			}
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

	public void setDirRaiz(File dirRaiz) {
		this.dirRaiz = dirRaiz;
	}

	public void setDirRaiz(String dirRaiz) {
		this.dirRaiz = new File(dirRaiz);
	}

	public void setLista(TreeMap lista) {
		this.lista = lista;
	}

	public void setSalida(PrintStream salida) {
		this.salida = salida;
	}

	public void setSalida(String salida) {
		try {
			this.salida = new PrintStream(salida);
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
}

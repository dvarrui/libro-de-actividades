package web.navegador.v02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.TreeMap;
import java.util.StringTokenizer;

public class Navegador {
	private TreeMap lista;
	private File fichero;
	private boolean leido=false;

	public void setFichero(String fichero) {
		this.fichero = new File(fichero);
	}
	
	public void crearFichero(String fichero) {
		PrintStream ps;
		try {
			ps=new PrintStream(new FileOutputStream(fichero));
			ps.println("Probando suerte!");
			ps.close();
		} catch(Exception e) {
			System.err.println(" [ERROR] crearFichero:"+e);
		}
		
	}
	public String getTextoNavegador(String clave) {
		if (!leido) leerFichero();
		
		String str = (String) lista.get(clave);
		if (str==null) str="("+lista.size()+")";
		return str;
	}
	
	private void leerFichero() {
		leido = true;
		lista = new TreeMap();
		
		try {
			String linea;
			BufferedReader entrada = new BufferedReader(new FileReader(fichero));
			linea = entrada.readLine();

			while (linea != null) {
				if (!linea.trim().startsWith("#")) {
					StringTokenizer st = new StringTokenizer(linea,":");
					lista.put(st.nextToken(),st.nextToken());
				}
				linea = entrada.readLine();
			}
			entrada.close();
		} catch (Exception e) {
			System.err.println(" [ERROR] leerFichero():" + e);
		}
	}
	
	
	public String generarSalida(String ficheroSalida, File dirRaiz) {
		//TODO: FATAL cambiar completamente!!!! 
		File ficheros[],dir;
		boolean fin = false;
		//PrintStream fich;
		String salida="";
		String tmp="";

		try {
			//fich = new PrintStream(ficheroSalida);
			dir=dirRaiz;
			ficheros = dir.listFiles();
			for (int i = 0; i < ficheros.length; i++) {
				File f = ficheros[i];

				// Añadir al texto de navegación y salir del bucle
				if (f.getName().equals("index.html")||
						f.getName().equals("index.jsp")) {

					if (dir.getCanonicalPath() == dirRaiz.getCanonicalPath()) {
						//Poner la página actual en el navegador
						//salida = Notacion.getPascal(f.getParentFile().getName());
						salida = getDescripcion(f);
					} else {
						//Poner página index en elnavegador
						salida = "<a href=\"" + "prefijo"+f.getName() + "\">"
								+ getDescripcion(f)
								+ "</a>";
					}
					break;
				}
			}
			if (!fin) {
				// Si no encuentro el final...
				// sigo de forma recursiva directorio arriba
				//tmp = buscarModoRelativo(dir.getParentFile(),"../"+"prefijo");
				if (tmp.length()!=0 && salida.length()!=0)
					salida =  tmp+" > "+salida;
				else if (tmp.length()!=0)
					salida = tmp;
			}
		} catch (Exception e) {
			System.err.println(e);
		}

		return salida;
	}

	
	private String getDescripcion(File fichero) {
		//String salida=fichero.getName();
		String salida=fichero.getParentFile().getName();
		try {
			String linea;
			BufferedReader entrada = new BufferedReader(new FileReader(fichero));
			linea = entrada.readLine();

			while (linea != null) {
				
				if (linea.trim().startsWith("String vg_desc=")) {
					//Línea localizada
					int i=linea.indexOf("\"");
					int j=linea.lastIndexOf("\"");
					salida=linea.substring(i+1,j);
				}
				linea = entrada.readLine();
			}
			entrada.close();
		} catch (Exception e) {
			System.err.println("Error:" + e);
		}
		return salida;
	}

	
	public static void main(String[] args) {
		Navegador n = new Navegador();
		n.setFichero("datos/navegador.txt");
		System.out.println(n.getTextoNavegador("/index.jsp"));
		System.out.println(n.getTextoNavegador("/html/alumnos/index.jsp"));
		System.out.println(n.getTextoNavegador("/html/padres/index.jsp"));
		System.out.println(n.getTextoNavegador("error"));
	}

}

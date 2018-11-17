package web.navegador.v01;

import java.io.*;

public class Navegador {
	public static final int MODO_ABS=1;
	public static final int MODO_REL=2;
	
	private File dirRaiz=null;
	private String txtNavegador="";
	private String parada="index.html";
	private int modo=MODO_REL;

	public void setDirRaiz(String dir) {
		this.dirRaiz = new File(dir);
	}
	
	public File getDirRaiz() {
		return this.dirRaiz;
	}

	public void setParada(String parada) {
		this.parada = parada;
	}

	public String getParada() {
		return this.parada;
	}
	
	public void setModo(int modo) {
		this.modo=modo;
	}
	
	public int getModo() {
		return this.modo;
	}
	
	public String getTxtNavegador() {
		if (modo==MODO_ABS) txtNavegador=buscarModoAbsoluto(this.dirRaiz);
		else if (modo==MODO_REL) txtNavegador=buscarModoRelativo(this.dirRaiz,"");
		else txtNavegador="*";

		return this.txtNavegador;
	}

	private String buscarModoAbsoluto(File dir) {
		File ficheros[];
		boolean fin = false;
		String salida = "";
		String tmp="";

		try {
			ficheros = dir.listFiles();
			for (int i = 0; i < ficheros.length; i++) {
				File f = ficheros[i];

				if (f.getName().startsWith(parada))
					fin = true;

				// Añadir al texto de navegación y salir del bucle
				if (f.getName().equals("index.html")||
						f.getName().equals("index.jsp")) {

					if (dir.getCanonicalPath() == dirRaiz.getCanonicalPath()) {
						
					} else {
						salida = "<a href=\"" + f.getCanonicalPath() + "\">"
								+ f.getParentFile().getName().toLowerCase()
								+ "</a>";
					}
					break;
				}
			}
			if (!fin) {
				// Si no encuentro el final...
				// sigo de forma recursiva directorio arriba
				tmp = buscarModoAbsoluto(dir.getParentFile());
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

	
	private String buscarModoRelativo(File dir, String prefijo) {
		File ficheros[];
		boolean fin = false;
		String salida = "";
		String tmp="";

		try {
			ficheros = dir.listFiles();
			for (int i = 0; i < ficheros.length; i++) {
				File f = ficheros[i];

				if (f.getName().startsWith(parada))
					fin = true;

				// Añadir al texto de navegación y salir del bucle
				if (f.getName().equals("index.html")||
						f.getName().equals("index.jsp")) {

					if (dir.getCanonicalPath() == dirRaiz.getCanonicalPath()) {
						//Poner la página actual en el navegador
						//salida = Notacion.getPascal(f.getParentFile().getName());
						salida = getDescripcion(f);
					} else {
						//Poner página index en elnavegador
						salida = "<a href=\"" + prefijo+f.getName() + "\">"
								+ getDescripcion(f)
								+ "</a>";
					}
					break;
				}
			}
			if (!fin) {
				// Si no encuentro el final...
				// sigo de forma recursiva directorio arriba
				tmp = buscarModoRelativo(dir.getParentFile(),"../"+prefijo);
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
		n.setParada("david");
		n.setDirRaiz("/home/david/web/cesarmanrique/9.tomcat/html/alumnos");
		n.setModo(Navegador.MODO_REL);
		System.out.println(n.getTxtNavegador());
	}

}

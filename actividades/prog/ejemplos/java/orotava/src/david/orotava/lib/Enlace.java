package david.orotava.lib;

import java.io.File;

/**
 * Esta clase define un enlace dentro de una página web: 
 *    (a) Ancla 
 *    (b) Elemento: No forma parte de otra página (HREF) 
 *    (c) Local: Es un enlace a un archivo local
 *    (d) Salida: Es un enlace de tipo salida
 * 
 * @author David Vargas Ruiz
 * @version 1.0.0 - 20051205
 * 
 */
public class Enlace {

	public boolean ancla;

	private boolean elemento;

	private boolean error;

	private boolean local;

	private StringBuffer log;

	private String nombreAbsoluto;

	private String nombreOrigen;

	public Enlace(String pNombre, String pRuta, boolean elemento) {
		try {
			File f = new File(pRuta + "/" + pNombre);
			log = new StringBuffer();
			nombreOrigen = pNombre;

			if (f.exists()) {
				nombreAbsoluto = f.getCanonicalPath();
				ancla = false;
				error = false;
				local = true;
			} else if (pNombre.startsWith("http://")
					|| pNombre.startsWith("shttp://")
					|| pNombre.startsWith("ftp://")
					|| pNombre.startsWith("sftp://")) {
				nombreAbsoluto = pNombre;
				ancla = false;
				error = false;
				local = false;
			} else if (pNombre.startsWith("#")) {
				nombreAbsoluto = pNombre;
				ancla = true;
				error = false;
				local = true;
				elemento = false;
			} else {
				nombreAbsoluto = pRuta + "/" + pNombre;
				ancla = false;
				error = true;
				local = false;
				log.append("Enlace cortado.");
			}
			this.elemento = elemento;
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public String getLog() {
		return log.toString();
	}

	public String getNombreAbsoluto() {
		return nombreAbsoluto;
	}

	public String getNombreOrigen() {
		return nombreOrigen;
	}

	public boolean isAncla() {
		return ancla;
	}

	public boolean isElemento() {
		return elemento;
	}

	public boolean isError() {
		return error;
	}

	public boolean isLocal() {
		return local;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public void setLog(String texto) {
		log.append(texto);
	}
}

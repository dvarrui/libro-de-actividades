package david.proyecto.util;

import java.util.*;

/**
 * Esta clase sirve para facilitar el acceso a la informaci�n contenida en un
 * fichero del tipo <b>properties</b>.
 * 
 * Se crea sin constructor para ser usado como JavaBean en JSP.
 * 
 * @author David Vargas Ruiz
 * @version 1.0.0 - 20051116
 */

public class Configuracion {
	public static void main(String args[]) {
		Configuracion c = new Configuracion();
		c.setFichero("david.tenerife.rec.inicio");
		System.out.println(c.getLength("entornos"));
		System.out.println(c.getStringAt("entornos", 0));
		System.out.println(c.getStringAt("entornos", 1));
		System.out.println(c.getStringAt("entornos", 2));

	}

	String fichero;

	ResourceBundle resources;

	public String getFichero() {
		return fichero;
	}

	/**
	 * @param str
	 *            Es el nombre del parámetro que queremos buscar en el fichero
	 * @return Se devuelve el valor <b>int</b> de dicho par�metro
	 */
	public int getInt(String str) {
		return Integer.parseInt(resources.getString(str));
	}

	public int getLength(String str) {
		String[] s = getStringArray(str);
		return s.length;
	}

	/**
	 * @param str
	 *            Es el nombre del parámetro que queremos buscar en el fichero
	 * @return Se devuelve el valor <b>String</b> de dicho par�metro
	 */
	public String getString(String str) {
		return resources.getString(str);
	}

	public String[] getStringArray(String str) {
		String[] s = tokenize(resources.getString(str));
		return s;
	}

	public String getStringAt(String str, int i) {
		String[] s = getStringArray(str);
		return s[i];
	}

	/**
	 * Constructor de la clase
	 * 
	 * @param <b>fichero
	 *            </b> Nombre del fichero tipo properties
	 */
	public void setFichero(String pFichero) {
		try {
			fichero = pFichero;
			resources = ResourceBundle.getBundle(fichero, Locale.getDefault());
		} catch (MissingResourceException mre) {
			System.err.println("ERROR Configuracion(): No se encuentra el "
					+ fichero + ".properties :" + mre);
		}
	}

	private String[] tokenize(String input) {
		Vector v = new Vector();
		StringTokenizer t = new StringTokenizer(input, ",");
		String cmd[];

		while (t.hasMoreTokens())
			v.addElement(t.nextToken());
		cmd = new String[v.size()];
		for (int i = 0; i < cmd.length; i++)
			cmd[i] = (String) v.elementAt(i);
		return cmd;
	}
}

package web.navegador.v04;

import java.util.ResourceBundle;

public class Navegador {
	private ResourceBundle fichero;

	public void setFichero(String fichero) {
		try {
			this.fichero =  ResourceBundle.getBundle(fichero);
		} catch(Exception e) {
			System.err.println(" [ERROR] setFichero(): "+e);
		}
	}
	
	public String getTextoNavegador(String clave) {
		
		String str = fichero.getString(clave);
		if (str==null) str="(Clave no definida)=("+clave+")";
		return str;
	}
	
	public static void main(String[] args) {
		Navegador n = new Navegador();
		n.setFichero("/home/david/workspace/canarias/datos/navegador.properties");
		
		System.out.println(n.getTextoNavegador("index.jsp"));
		System.out.println(n.getTextoNavegador("html.alumnos.index.jsp"));
		System.out.println(n.getTextoNavegador("html.padres.index.jsp"));
		System.out.println(n.getTextoNavegador("error"));
	}

}

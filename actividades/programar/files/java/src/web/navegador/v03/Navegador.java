package web.navegador.v03;

import java.util.TreeMap;

public class Navegador {
	private TreeMap lista;
	private boolean leido=false;

	public String getTextoNavegador(String clave) {
		if (!leido) leerFichero();
		
		String str = (String) lista.get(clave);
		if (str==null) str="("+lista.size()+")";

		return str;
	}
	
	private void leerFichero() {
		leido = true;
		lista = new TreeMap();

		lista.put("/index.jsp","");
		
		lista.put("/html/alumnos/index.jsp","<a href=\"../../index.jsp\">Principal</a> » ");
		lista.put("/html/alumnos/descargarsoftware.jsp","<a href=\"../../index.jsp\">Principal</a> » <a href=\"index.jsp\">Alumn@s</a> » ");
		lista.put("/html/alumnos/enlacesdeinteres.jsp","<a href=\"../../index.jsp\">Principal</a> » <a href=\"index.jsp\">Alumn@s</a> » ");
		
		lista.put("/html/centro/index.jsp","<a href=\"../../index.jsp\">Principal</a> » ");
		
		lista.put("/html/direccion/index.jsp","<a href=\"../../index.jsp\">Principal</a> » ");
		
		lista.put("/html/eventos/index.jsp","<a href=\"../../index.jsp\">Principal</a> | ");
		
		lista.put("/html/noticias/index.jsp","<a href=\"../../index.jsp\">Principal</a> | ");
		
		lista.put("/html/padres/index.jsp","<a href=\"../../index.jsp\">Principal</a> » ");
		
		lista.put("/html/piedepagina/condicionesdeuso.jsp","<a href=\"../../index.jsp\">Principal</a> | ");
		lista.put("/html/piedepagina/contacto.jsp","<a href=\"../../index.jsp\">Principal</a> | ");
		lista.put("/html/piedepagina/creditos.jsp","<a href=\"../../index.jsp\">Principal</a> | ");
		lista.put("/html/piedepagina/mapaweb.jsp","<a href=\"../../index.jsp\">Principal</a> | ");
		
		lista.put("/html/profesorado/index.jsp","<a href=\"../../index.jsp\">Principal</a> » ");
		lista.put("/html/profesorado/departamentos/index.jsp","<a href=\"../../../index.jsp\">Principal</a> » <a href=\"../index.jsp\">Profesorado</a> » ");
		lista.put("/html/profesorado/departamentos/administracion/index.jsp","<a href=\"../../../../index.jsp\">Principal</a> » <a href=\"../../index.jsp\">Profesorado</a> » <a href=\"../index.jsp\">Departamentos</a> » ");
		lista.put("/html/profesorado/departamentos/comercio/index.jsp","<a href=\"../../../../index.jsp\">Principal</a> » <a href=\"../../index.jsp\">Profesorado</a> » <a href=\"../index.jsp\">Departamentos</a> » ");
		lista.put("/html/profesorado/departamentos/comercio/ciclocomercio.jsp","<a href=\"../../../../index.jsp\">Principal</a> » <a href=\"../../index.jsp\">Profesorado</a> » <a href=\"../index.jsp\">Departamentos</a> » <a href=\"index.jsp\">Comercio y Marketing</a> » ");
		lista.put("/html/profesorado/departamentos/comercio/ciclomarketing.jsp","<a href=\"../../../../index.jsp\">Principal</a> » <a href=\"../../index.jsp\">Profesorado</a> » <a href=\"../index.jsp\">Departamentos</a> » <a href=\"index.jsp\">Comercio y Marketing</a> » ");
		lista.put("/html/profesorado/departamentos/comercio/ciclointernacional.jsp","<a href=\"../../../../index.jsp\">Principal</a> » <a href=\"../../index.jsp\">Profesorado</a> » <a href=\"../index.jsp\">Departamentos</a> » <a href=\"index.jsp\">Comercio y Marketing</a> » ");
		lista.put("/html/profesorado/departamentos/comunicacion/index.jsp","<a href=\"../../../../index.jsp\">Principal</a> » <a href=\"../../index.jsp\">Profesorado</a> » <a href=\"../index.jsp\">Departamentos</a> » ");
		lista.put("/html/profesorado/departamentos/electricidad/index.jsp","<a href=\"../../../../index.jsp\">Principal</a> » <a href=\"../../index.jsp\">Profesorado</a> » <a href=\"../index.jsp\">Departamentos</a> » ");
		lista.put("/html/profesorado/departamentos/formacion/index.jsp","<a href=\"../../../../index.jsp\">Principal</a> » <a href=\"../../index.jsp\">Profesorado</a> » <a href=\"../index.jsp\">Departamentos</a> » ");
		lista.put("/html/profesorado/departamentos/informatica/index.jsp","<a href=\"../../../../index.jsp\">Principal</a> » <a href=\"../../index.jsp\">Profesorado</a> » <a href=\"../index.jsp\">Departamentos</a> » ");
		
		lista.put("/html/secretaria/index.jsp","<a href=\"../../index.jsp\">Principal</a> » ");
		lista.put("/html/secretaria/matricula/index.jsp","<a href=\"../../../index.jsp\">Principal</a> » <a href=\"../index.jsp\">Secretar&iacute;a</a> » ");
		lista.put("/html/secretaria/oferta/index.jsp","<a href=\"../../../index.jsp\">Principal</a> » <a href=\"../index.jsp\">Secretar&iacute;a</a> » ");
		lista.put("/html/secretaria/preinscripcion/index.jsp","<a href=\"../../../index.jsp\">Principal</a> » <a href=\"../index.jsp\">Secretar&iacute;a</a> » ");
	}
	
	
	public static void main(String[] args) {
		Navegador n = new Navegador();
		System.out.println(n.getTextoNavegador("/index.jsp"));
		System.out.println(n.getTextoNavegador("/html/alumnos/index.jsp"));
		System.out.println(n.getTextoNavegador("/html/padres/index.jsp"));
		System.out.println(n.getTextoNavegador("error"));
	}

}

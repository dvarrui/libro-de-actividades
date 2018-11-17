package david.lalaguna.prueba;
/*
 * Creado el 17-oct-2004
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */

import java.util.regex.*;

/**
 * @author david
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
public class ProbarMatcher 
{
	public static void main(String[] args) 
	{
		String entrada = new String("(david es (un (maquina, 567 y, (que mas da) eh))) )");
		String patron = new String("\\([^\\(\\)|.]*\\)");
		//String s;
		 	 
		//Patrones y emparejadores
		Pattern p = Pattern.compile(patron);
		Matcher m = p.matcher(entrada);

		while(m.find() && entrada.length()>2)
		{ 
			System.out.println("OK: El patr�n <"+patron+"> se empareja con la entrada\n\t<"+entrada+">");
			System.out.println("Selecci�n = " + entrada.substring(m.start(),+m.end()));
			System.out.println("Coordenadas = "+m.start()+","+m.end());
			if (m.start()>0 && m.end()<entrada.length())
			{entrada = entrada.substring(0,m.start()-1)+" $ "+entrada.substring(m.end()+1);}
			else if (m.start()==0 && m.end()<entrada.length())
			{entrada = " $ "+entrada.substring(m.end()+1);}
			else if (m.start()>0 && m.end()==entrada.length())
			{entrada = entrada.substring(0,m.start()-1)+" $ ";}
			else
			{ entrada = new String("$");}
			 
			System.out.println("Resultado = "+entrada);
			m = p.matcher(entrada);
		}
		System.err.println("ERR: El patr�n <"+patron+"> NO se empareja con la entrada <"+entrada+">");
			
	}
}

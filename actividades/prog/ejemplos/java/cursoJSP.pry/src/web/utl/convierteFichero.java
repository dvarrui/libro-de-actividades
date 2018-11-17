package web.utl;

import  java.io.*;
import  java.util.*;

/**
 * @author David Vargas Ruiz
 * @version 1.0.0
 */
public class convierteFichero
{
	String 			nombreOrigen;	//Nombre del fichero Origen
	String			nombreDestino;	//Nombre del fichero Destino
	BufferedReader entrada;
   PrintWriter		salida;	
	int 				fin;
	
	/**
	 * Constructor vacío
	 */
	public convierteFichero() {}

	/**
	 * Procedimientos SET y GET 
	 */
	public void 	setNombreOrigen(String pNombre)	{ nombreOrigen = new String(pNombre);	}
	public void 	setNombreDestino(String pNombre)	{ nombreDestino = new String(pNombre);	}
	
	public String 	getNombreOrigen()		{  return nombreOrigen; 	}
	public String 	getNombreDestino()	{  return nombreDestino;	}
	public boolean	getFin()					{  if (fin==1) return true; return false;	}
	
	/**
	 * Abrir: Abrir el fichero origen y crear el fichero destino
	 * @return Devuelve <b>false</b> si ha ocurrido algún problema
	 */
   public boolean abrir()
   {
      try
      {
			entrada = new BufferedReader (new FileReader(nombreOrigen));
			salida  = new PrintWriter(new BufferedWriter(new FileWriter(nombreDestino)));
			fin = 0;	
			return true;
      }
      catch (Exception e)
      {	System.err.println("ERROR(abrir): "+e); return false;}
   }
  
	/**
	 * Cierra los ficheros origen y destino
	 * @return Devuelve <b>false</b> si ha ocurrido algún problema
	 */
   public boolean cerrar()
   {
      try
      {
         entrada.close();
			salida.close();
			return true;	
      }
      catch (Exception e)
      {	System.err.println("ERROR(cerrar): "+e); return false;}
   }


	/**
	 * Lee una línea del fichero origen convertido de TXT a HTML
	 * @return Devuelve un tipo <b>String</b>
	 */
   public String getSiguiente()
   {
      try
      {
     		if (fin==0)
			{
				String str;
				str = new String (entrada.readLine());
           	if (str != null)
    			{	
					/*Convierte la fila de HTML a TXT*/
       			return(convierteStringHtmlAtxt(str));
      		}
				fin=1;
				return " ";
	    	}
      }
      catch (Exception e)
		{ fin=1;	return "";	}
		return "";
   }

 	
	/**
	 * Graba una línea en el fichero destino
	 * @return Devuelve <b>false</b> si ah ocurrido algún problema
	 */
   public boolean setSiguiente(String pSiguiente)
   {
      try
      {	salida.println(pSiguiente); return true;
      }
      catch (Exception e)
      { 	System.err.println("ERROR(setSiguiente): "+e); }
		return false;
   }


	/**
	 * Convierte una entrada de String TXT en String HTML
	 * @return Devuelve un tipo <b>String</b>
	 */
	private String convierteStringHtmlAtxt(String entrada)
	{
		StringBuffer salida = new StringBuffer(500); //entrada.length());
		char c;
		
		for(int i=0; i<entrada.length(); i++)
		{
		   c = entrada.charAt(i);
			if (c=='<')      	{salida.append("<b>&lt;");}
			else if (c=='>') 	{salida.append("&gt;</b>");}
			else if (c=='"') 	{salida.append("&quot;");}
			else if (c=='&') 	{salida.append("&amp;");}
			else 					{salida.append(c);}
		}
		return (salida.toString()+"<br>");
	}

	
	/**
	 * Método main
	 */
	public static void main(String args[])
	{
		String str;
		convierteFichero conv = new convierteFichero();
		conv.setNombreOrigen(args[0]); 
		conv.setNombreDestino(args[1]);
		conv.abrir();
		while(!conv.getFin())
		{ 
			str=conv.getSiguiente(); System.out.println(str);
		  	conv.setSiguiente(str);	
		}
		conv.cerrar();
	}
}


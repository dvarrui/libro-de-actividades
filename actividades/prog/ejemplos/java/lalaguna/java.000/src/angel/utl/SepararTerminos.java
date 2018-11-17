/*
 * Fichero : utlSepararTerminos.java
 * Fecha UM: 20030902
*/

package angel.utl;

import java.io.*;
import java.lang.*;

/**
 * Esta clase se encarga de ir dividiendo un String de entrada en varios substrings. 
 * Los caractéres empleados como separadores son los paréntesis y los espacios.
 * Además los espacios no se devuelven como substrings válidos.
 * 
 * @author David Vargas Ruiz
 * @version 0.1.0
*/
public class SepararTerminos extends Object
{
   private String Cadena;
	
   //===========
   //Constructor
   //===========
   public SepararTerminos(String str)
   { Cadena = new String(str.trim()); }
	
   /**
    * @return Devuelve el siguiente substring
    */
   public String siguiente()
   {
      
      int i1=0;
      int i2=0;
      int a1,a2,a3;
      String str = new String("");;

      //Quitar espacios del principio
      while (Cadena.substring(0,1).equals(" "))	Cadena=new String(Cadena.substring(1));
			
      if (Cadena.length()>0)
      {
         //Detectar paréntesis al comienzo de la cadena
	 if (Cadena.substring(0,1).equals("(") || Cadena.substring(0,1).equals(")"))
	 { i1=0;i2=1; }
	 else 
	 {
	    //Detectar término entre paréntesis o espacios
	    a1=Cadena.indexOf(" ");	if (a1<0) a1=Cadena.length();
	    a2=Cadena.indexOf("("); if (a2<0) a2=Cadena.length();
	    a3=Cadena.indexOf(")"); if (a3<0) a3=Cadena.length();

	    if(a1<a2 && a1<a3) i2=a1;
	    else if (a2<a1 && a2<a3) i2=a2;
	    else if (a3<a1 && a3<a2) i2=a3;
	    else i2=Cadena.length();
	 }
	 //Devuelvo el token en str
	 str = new String(Cadena.substring(i1,i2));	
	 //Elimino el token encontrado de Cadena
	 Cadena = new String(Cadena.substring(i2));
      }
      return(str);
   }

   
   /**
    * @return devuelve falso si quedan términos pendientes de extraer
    */
   public boolean fin()
   {
      if (Cadena.length()>0) {return false;}
      else	{return true;}
   }
}

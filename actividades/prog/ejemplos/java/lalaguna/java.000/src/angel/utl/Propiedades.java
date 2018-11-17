/*
  Fichero utlPropiedades.java<br>
  Fecha creación: 20-03-2003<br>
  Fecha UM      : 29-05-2003<br>
 */
package angel.utl;

import java.util.*;
import java.io.*;

/**
 * 
 * Esta clase sirve para facilitar el acceso a la información 
 * contenida en un fichero del tipo <b>properties</b>.
 * 
 * 
 * @author David Vargas Ruiz
 * @version 0.2.0
 * 
 */

public class Propiedades //extends Object
{
   ResourceBundle resources;

   /**
    * Constructor de la clase 
    * @param <b>fichero</b> Nombre del fichero tipo properties
    */
   //public utlPropiedades() {}
   
   public void setNombreFichero(String fichero)
   {
      try
      {  resources = ResourceBundle.getBundle(fichero, Locale.getDefault()); }
      catch (MissingResourceException mre)
      {	 System.err.println("No se encuentra el "+fichero+".properties :"+mre);}
   }

   /**
    * @param str Es el nombre del parámetro que queremos buscar en el fichero
    * @return Se devuelve el valor <b>int</b> de dicho parámetro
    */
   public int    getInt(String str)     { return Integer.parseInt(resources.getString(str));}
   
   /**
    * @param str Es el nombre del parámetro que queremos buscar en el fichero
    * @return Se devuelve el valor <b>String</b> de dicho parámetro
    */
   public String getString(String str)   { return resources.getString(str);}
   
   //----------------------------------------
   public String getStringElement(String str, int i)
   {
      String[] s = getStringArray(str);
      return s[i];
   }  

   private String[] getStringArray(String str)
   {  
      String[] s = tokenize(resources.getString(str));
      return s;
   }

   private String[] tokenize(String input) 
   {
      Vector v = new Vector();
      StringTokenizer t = new StringTokenizer(input);
      String cmd[];

      while (t.hasMoreTokens())	 v.addElement(t.nextToken());
      cmd = new String[v.size()];
      for (int i = 0; i < cmd.length; i++)  cmd[i] = (String) v.elementAt(i);
      return cmd;
   }
}

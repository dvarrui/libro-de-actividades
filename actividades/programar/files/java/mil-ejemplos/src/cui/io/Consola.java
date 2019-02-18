package cui.io;

import java.io.*;

/*
 * @author David Vargas Ruiz
 * @version 1.0.0
 * Esta clase hace de interfaz con la consola
 */

public class Consola
{
   /**
    * Método readLine()
    */
   public static String readLine()
   {
      BufferedReader br;   //Necesario para la lectura readLine
      br = new BufferedReader(new InputStreamReader(System.in));

      try
      {
         return br.readLine();
      }
      catch(Exception e)
      { System.err.println("ERROR Consola.readLine():"+e);}
      return null;
   }

   /**
    * Método println()
    */
   public static void println(String pTexto)
   {
      System.out.println(pTexto);
   }
}

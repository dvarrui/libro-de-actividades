/*-----------------------------------------------
   Programa :  WFRegistrarConexion.java
   Fecha    :  28-08-2003
   Estado   :  OK
-----------------------------------------------*/
package totem.lib;

import  java.io.*;
import  java.util.*;
import  java.sql.*;


/**
 * Guarda un registro en el almacén de LOG
 *
 * @author David Vargas Ruiz
 * @version 0.8.2
 */
public class WFRegistrarConexion extends Object
{
   private Connection      con;
   private String	   tipo, usuario, pc, modulo, descripcion;

   
   //=======================
   //Constructor de la clase
   //=======================
   public WFRegistrarConexion(Connection p_con)
   {
      con=p_con;
      usuario=new String("RegistrarConexion"); pc=null; modulo=new String("*");
   }

   
   //====================
   //Métodos PON públicos
   //====================
   public void ponModulo(String p_modulo)   {   modulo=p_modulo;  }
   public boolean registrarERR(String p_descripcion)   { return registrar("ERR",p_descripcion);}
   public boolean registrarERR(String p_modulo, String p_descripcion)  {return registrar("ERR",p_modulo,p_descripcion);}
   public boolean registrarLOG(String p_descripcion)   {return registrar("LOG",p_descripcion);}
   public boolean registrarLOG(String p_modulo, String p_descripcion) {return registrar("LOG",p_modulo,p_descripcion);}
   public boolean registrarWRN(String p_descripcion)  {   return registrar("WRN",p_descripcion);  }
   public boolean registrarWRN(String p_modulo, String p_descripcion) {return registrar("WRN",p_modulo,p_descripcion);}
   

   //====================
   //Métodos PON privados 
   //====================
   private boolean registrar(String p_tipo, String p_descripcion)
   {  return registrar(p_tipo, modulo, p_descripcion);}
   
   private boolean registrar(String p_tipo, String p_modulo, String p_descripcion)
   {   
      Statement st1;
      String str = new String(p_descripcion.replace('\'','.'));
      try
      {
	 st1 = con.createStatement();
	 st1.executeUpdate("INSERT INTO LOG_D_AVISOS (TIPO,USUARIO,PC,MODULO,DESCRIPCION) VALUES('"
	    +p_tipo+"','"+usuario+"','"+pc+"','"+modulo+"','"+str+"')");
         st1.close();
         return true;
      }
      catch(Exception e)
      {  System.err.println("ERROR en WFRegistrarConexion.registrar(): "+e+",<"+str+">"); }
      return false;
   }
}
//Fin de la clase WFLoggerConexion.java

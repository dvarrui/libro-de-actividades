/*-----------------------------------------------
   Programa :  WFRegistrarSesion.java
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
 * @version 0.8.0
 */
public class WFRegistrarSesion extends Object
{
   private WFSesion	   sesion;
   private String	   tipo, usuario, pc, modulo, descripcion;

   
   //=======================
   //Constructor de la clase
   //=======================
   public WFRegistrarSesion(WFSesion p_sesion)
   {
      sesion=p_sesion;
      if (sesion.estaLogin()) 
      { usuario=sesion.leeCodUsuario(); pc=sesion.leePC(); modulo=new String("*"); }
      else
      { System.err.println("WFRegistrarSesion(): ERROR Sesión no establecida");}
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
      if (!sesion.estaLogin()) return false;
      Connection con = sesion.leeConnection(); Statement st1;
      try
      {
	 st1 = con.createStatement();
	 st1.executeUpdate("INSERT INTO LOG_D_AVISOS (TIPO,USUARIO,PC,MODULO,DESCRIPCION) VALUES('"
	    +p_tipo+"','"+usuario+"','"+pc+"','"+modulo+"','"+p_descripcion+"')");
         st1.close();
         return true;
      }
      catch(Exception e)
      {  System.err.println("ERROR en WFRegistrarSesion.registrar(): "+e); }
      return false;
   }
}
//Fin de la clase WFRegistrarSesion.java

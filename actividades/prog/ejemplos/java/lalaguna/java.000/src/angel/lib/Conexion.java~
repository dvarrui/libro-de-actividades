/*-----------------------------------------------
   Programa :  BDConexion.java
   Fecha    :  24-08-2003
   Estado   :  OK
-----------------------------------------------*/
package angel.lib;

import  java.io.*;
import  java.util.*;
import  java.sql.*;


/**
 * Mantener una conexión permanente con una base de datos
 * 
 * @author David Vargas Ruiz
 * @version 0.1.0
 */
public class BDConexion extends Object
{
   final int    ERROR=-1, DESCONECTADO=0, CONECTADO=1;
   
   private Connection  con;
   private String      usuario, clave, url, entorno, des_estado="DESCONECTADO";
   private int	       cod_estado=DESCONECTADO;
   
   
   //=======================
   //Constructor de la clase
   //=======================
   /*
   public WFBD(String p_url, String p_usuario, String p_clave)
   {
      // Conectar con la BBDD de WF
      cod_estado=DESCONECTADO;  des_estado=new String("DESCONECTADO");
      try
      {
	 //Cargar el driver JDBC
	 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	 //Conectar a la base de datos
	 usuario=new String(p_usuario); clave=new String(p_clave); url=new String(p_url);
	 con=DriverManager.getConnection(url, usuario, clave);
	 cod_estado= CONECTADO; des_estado=new String("CONECTADO");
      } 
      catch(Exception e)
      {	 
	 cod_estado=ERROR; des_estado=new String("ERROR Conexión BBDD");
	 System.err.println("WFBD ERROR 1 Conexión BD:"+e);
      }
   }*/

   //==========================================================================
   /**
    * Constructor externo de la clase WFBD para cumplir especificaciones de EJB. 
    */
   public void inicializar(String p_url, String p_usuario, String p_clave, String p_entorno)
   {
      // Conectar con la BBDD de WF
      //cod_estado=DESCONECTADO;  des_estado=new String("DESCONECTADO");
      try
      {
         //Cargar el driver JDBC
         Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
         //Conectar a la base de datos
         usuario=new String(p_usuario); clave=new String(p_clave); url=new String(p_url); entorno=new String(p_entorno);
         con=DriverManager.getConnection(url, usuario, clave);
         cod_estado=CONECTADO; des_estado=new String("CONECTADO");
	 //¿Registrar que se ha establecido la conexión?
	 //
      } 
      catch(Exception e)
      { 	 
         cod_estado=ERROR; des_estado=new String("ERROR Conexión BBDD");
         System.err.println("BDConexion ERROR 1 Conexión BD:"+e);
      }
   }

   //===================================================================
   /**
    * Crear una nueva sesión que acceda a la base de datos conectada.
    * @return Un objeto del tipo <b>BDSesion</b> que representa la sesión.
    */
   public BDSesion crearSesion()
   {
      if (cod_estado==CONECTADO)
      {
         //return new WFSesion(this);
	 BDSesion s = new BDSesion(); s.inicializar(this);
	 return s;
      }
      return null;
   }


   //===================================================================
   /**
    * Realiza la desconexión con la base de datos.
    * @return Devuelve <b>verdadero</b> si se realiza la desconexión con éxito.
    */
   public boolean desconectar()
   {
      try   { con.close(); return true;}
      catch (Exception e)  { System.err.println("Exception en BDConexion.desconectar(): " + e);}
      return false;
   }
   
   
   //===================================================================
   /**
    * Devuelve <b>verdadero</b> si hay una conexión establecida.
    * @return Devuelve <b>verdadero</b> si hay una conexión establecida.
    */
   public boolean estaConectado()
   {  
      if (cod_estado==CONECTADO) return true;
      return false;
   }

   //===================================================================   
   /**
    *Si hay una conexión establecida se devuelve el objeto <b>Connection</b> que la representa.
    * @return Devuelve el objeto <b>Connection</b> que representa la conexión con la BBDD.
    */
   public Connection leeConnection()
   {  
      if (cod_estado==CONECTADO) return con;
      return null;
   }
   
   public String     leeEntorno() { if (cod_estado==CONECTADO) return entorno;     return null;   }
}
//Fin de la clase BDConexion.java

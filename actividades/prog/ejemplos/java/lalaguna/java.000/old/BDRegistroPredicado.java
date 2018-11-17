/*
 * Archivo. BDRegistroPredicado.java
 * */

package angel.lib;

import  java.io.*;
import 	java.lang.*;
import  java.util.*;
import  java.sql.*;


/**
 *
 * @author David Vargas Ruiz
 * @version 0.1.0
 */
public class BDRegistroPredicado extends Object
{
   final int          ERROR=-1, VACIO=0, NUEVO=1, OK=2, OK_BUSCADO=3, OK_INSERTADO=4, OK_BORRADO=5;
   private int        cod_estado, bd_cod_predicado, bd_cod_tipo_predicado;
   private String     des_estado, bd_contexto;
   private BDSesion   sesion;
   private PredicadoEstructura  predicado;
   private java.util.Date       fec_ult_mod;
	
   //================================
   // Constructor de la clase
   //================================ 
   public BDRegistroPredicado()
   {
      cod_estado=VACIO; des_estado=new String("VACIO");
      sesion=null;  predicado=null;
   }

   public void inicializar(BDSesion p_sesion)
   {
      cod_estado=VACIO; des_estado=new String("VACIO");
      sesion=p_sesion;  predicado = null;
   }

   
   //============
   // Métodos PON
   //============
   public void ponPredicadoEstructura(PredicadoEstructura p_pred)
   {
      if (p_pred.estaCorrecto())
      {
         cod_estado=NUEVO; des_estado=new String("NUEVO");    predicado = p_pred;
         bd_cod_predicado=-1; bd_cod_tipo_predicado=1; bd_contexto = new String("*");
	 fec_ult_mod=new java.util.Date();
      }
      else
      {  cod_estado=ERROR; des_estado=new String("ERR 01: Predicado incorrecto");     }
   }
   

   //============
   // Métodos LEE
   //============
   public int                   leeBDCodPredicado()   { return bd_cod_predicado; }
   public int                   leeBDTipoPredicado()  { return bd_cod_tipo_predicado;}
   public int                   leeCodEstado()        { return cod_estado; }
   public String                leeDesEstado()        { return des_estado; }
   public PredicadoEstructura   leePredicado()        { return predicado;}


   
   public boolean borrar()
   {
      if (bd_cod_predicado<0) return false;
      return true;
   }

   public boolean buscar()
   {
      //Buscar el registro tipo predicado en la BBDD y localizar su código ID
      if (cod_estado==VACIO||cod_estado==ERROR) return false;
      
      Statement st1;
      ResultSet rs1;
      
      try
      {
	 st1 = sesion.leeConnection().createStatement();
	 
	 //Insertar Predicado Cabecera
	 /*
	 rs1 = st1.executeQuery("SELECT COD_PREDICADO as ID FROM TRA_M_PREDICADOS_CABECERA;");
	 rs1.next();
         bd_cod_predicado=rs1.getInt("ID")+1;
	 rs1.close();

         st1.executeUpdate("INSERT INTO TRA_M_PREDICADOS_CABECERA (COD_PREDICADO,CONTEXTO,DES_PREDICADO,"
			 +"COD_TIPO_PREDICADO,COD_USUARIO) VALUES('"
		         +bd_cod_predicado+"','"+bd_contexto+"','"+predicado.leeDesPredicado()+"','"
			 +bd_cod_tipo_predicado+"','"+sesion.leeCodUsuario()+"');");

	 
         for(int i=0;i<predicado.leeNumeroPredicados();i++)
         {
            st1.executeUpdate("INSERT INTO TRA_D_PREDICADOS_DETALLE (COD_PREDICADO,NUM_ORDEN,"
			 +"ARG1,ARG2,ARG3,ARG4,ARG5) VALUES('"
		         +bd_cod_predicado+"','"+i+"','"
			 +predicado.leeTermino(i,0)+"','"
			 +predicado.leeTermino(i,1)+"','"
			 +predicado.leeTermino(i,2)+"','"
			 +predicado.leeTermino(i,3)+"','"
			 +predicado.leeTermino(i,4)+"');");
         }
	 
         st1.close();*/
         return true;
      }
      catch(Exception e)
      { 
	cod_estado=ERROR; des_estado=new String("ERROR buscar()");
        System.err.println("Exception en buscar(): " + e);
      }
      return false;
   }
   
   public boolean insertar()
   {
      if (cod_estado!=NUEVO) return false;
      
      Statement st1;
      ResultSet rs1;
      
      try
      {
	 st1 = sesion.leeConnection().createStatement();
	 
	 //Insertar Predicado Cabecera
	 rs1 = st1.executeQuery("SELECT MAX(COD_PREDICADO) as ID FROM TRA_M_PREDICADOS_CABECERA;");
	 rs1.next();
         bd_cod_predicado=rs1.getInt("ID")+1;
	 rs1.close();

         st1.executeUpdate("INSERT INTO TRA_M_PREDICADOS_CABECERA (COD_PREDICADO,CONTEXTO,DES_PREDICADO,"
			 +"COD_TIPO_PREDICADO,COD_USUARIO) VALUES('"
		         +bd_cod_predicado+"','"+bd_contexto+"','"+predicado.leeDesPredicado()+"','"
			 +bd_cod_tipo_predicado+"','"+sesion.leeCodUsuario()+"');");

	 
         for(int i=0;i<predicado.leeNumeroPredicados();i++)
         {
            st1.executeUpdate("INSERT INTO TRA_D_PREDICADOS_DETALLE (COD_PREDICADO,NUM_ORDEN,"
			 +"ARG1,ARG2,ARG3,ARG4,ARG5) VALUES('"
		         +bd_cod_predicado+"','"+i+"','"
			 +predicado.leeTermino(i,0)+"','"
			 +predicado.leeTermino(i,1)+"','"
			 +predicado.leeTermino(i,2)+"','"
			 +predicado.leeTermino(i,3)+"','"
			 +predicado.leeTermino(i,4)+"');");
         }
         st1.close();
         return true;
      }
      catch(Exception e)
      { 
	cod_estado=ERROR; des_estado=new String("ERROR insertar()");
        System.err.println("Exception en insertar(): " + e);
      }
      return false;
   }
   
} //Fin de BDRegistroPredicado.java

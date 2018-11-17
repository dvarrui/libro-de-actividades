/*-----------------------------------------------
   Programa :  WFTransicion.java
   Fecha    :  05-06-2003
-----------------------------------------------*/
package totem.lib;

import  java.io.*;
import  java.util.*;
import  java.sql.*;


/**
 * @author David Vargas Ruiz
 * @version 0.7.0
 */
public class WFTransicion extends Object
{
   private int		   cod_transicion;
   private String	   cod_usuario, cod_tipo_transicion, des_tipo_transicion;
   private java.util.Date  fec_ult_mod;
   private WFTarea         tarea1, tarea2;
   
   
   //=======================
   //Constructor de la clase
   //=======================
   public WFTransicion(Connection p_con, int p_cod_transicion)
   {
      cod_transicion=0;cod_usuario=null;cod_tipo_transicion=null;des_tipo_transicion=null;tarea1=null;tarea2=null;
      
      Statement st1;
      ResultSet rs1;
      try
      {
	 st1 = p_con.createStatement();
	 rs1 = st1.executeQuery("SELECT tra.cod_transicion,tra.cod_tarea1,tra.cod_tarea2,tra.cod_tipo_transicion,"
	       +"tip.des_tipo_transicion,tra.fec_ult_mod,tra.cod_usuario FROM DEF_D_TRANSICIONES tra, "
	       +"SYS_M_TIPOS_TRANSICION tip WHERE "
	       +"tra.cod_tipo_transicion=tip.cod_tipo_transicion AND cod_transicion="+p_cod_transicion+";");
	 if (rs1.next())
	 { 
	    //Cargar las variables de la tabla TRANSICIONES en el objeto java
	    cod_transicion=p_cod_transicion;
            tarea1= new WFTarea(p_con,rs1.getInt("COD_TAREA1"));
            tarea2= new WFTarea(p_con,rs1.getInt("COD_TAREA2"));
            cod_tipo_transicion=rs1.getString("COD_TIPO_TRANSICION");
	    des_tipo_transicion=rs1.getString("DES_TIPO_TRANSICION");
	    fec_ult_mod=rs1.getDate("FEC_ULT_MOD");
	    cod_usuario=rs1.getString("COD_USUARIO");
	 }
	 rs1.close();
	 st1.close();
      }
      catch(Exception e)
      {  System.err.println("ERROR WFTransicion() REG_NOTFOUND: " + e);}
   }

   
   //===========
   //Métodos LEE
   //===========
   public int		   leeCodTarea1()	   { return tarea1.leeCodTarea();}
   public int		   leeCodTarea2()	   { return tarea2.leeCodTarea();}
   public String	   leeCodTipoTransicion()  { return cod_tipo_transicion;}
   public int		   leeCodTransicion()	   { return cod_transicion;}
   public String	   leeCodUsuario()	   { return cod_usuario;}
   public String	   leeDesTarea1()	   { return tarea1.leeDesTarea();}
   public String	   leeDesTarea2()	   { return tarea2.leeDesTarea();}
   public String	   leeDesTipoTransicion()  { return des_tipo_transicion;}
   public java.util.Date   leeFecUltMod()	   { return fec_ult_mod;}
}
//Fin de la clase WFTransicion.java

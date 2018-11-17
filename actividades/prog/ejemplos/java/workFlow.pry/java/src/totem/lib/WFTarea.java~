/*-----------------------------------------------
   Programa :  WFTarea.java
   Fecha    :  05-05-2003
-----------------------------------------------*/
package totem.lib;

import java.io.*;
import java.util.*;
import java.sql.*;


/**
 * Objeto entidad tarea de la BBDD
 * 
 * @author David Vargas Ruiz
 * @version 0.8.0
 */
public class WFTarea extends Object
{
   
   private int		   cod_tarea;
   private String	   cod_usuario, des_tarea, formulario_web, formulario_java;
   private java.util.Date  fec_ult_mod;
   
   //=======================
   //Constructor de la clase
   //=======================
   public WFTarea(Connection p_con, int p_cod_tarea)
   {
      cod_tarea=-1; cod_usuario=null; des_tarea=null; fec_ult_mod=null;
      
      Statement st1;
      ResultSet rs1;
      try
      {
	 st1 = p_con.createStatement();
	 rs1 = st1.executeQuery("SELECT * FROM DEF_M_TAREAS WHERE cod_tarea="+p_cod_tarea+";");
	 if (rs1.next())
	 { 
	    //Cargar las variables de la tabla TAREA en el objeto java
	    cod_tarea=p_cod_tarea;
            des_tarea=rs1.getString("DES_TAREA");
	    fec_ult_mod=rs1.getDate("FEC_ULT_MOD");
	    formulario_web=rs1.getString("FORMULARIO_WEB");
	    formulario_java=rs1.getString("FORMULARIO_JAVA");
	 }
	 rs1.close();
	 st1.close();
      }
      catch(Exception e)
      {  System.err.println("Exception WFTarea() REG_NOTFOUND: " + e);}
   }

   
   //===========
   //Métodos GET
   //===========
   public int		   leeCodTarea()   { return cod_tarea;}
   public String	   leeCodUsuario()   { return cod_usuario;}
   public String	   leeDesTarea()   { return des_tarea;}
   public java.util.Date   leeFecUltMod()   { return fec_ult_mod;}


   
   //=========
   //verInfo()
   public void verInfo()
   {
      System.out.println("WFTarea.verInfo():");
      System.out.println("des_tarea       = "+des_tarea+" ["+cod_tarea+"]");
      System.out.println("fec_ult_mod     = "+fec_ult_mod+" ["+cod_usuario+"]");
      System.out.println();
   }
}
//Fin de la clase WFTarea.java

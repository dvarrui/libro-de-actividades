/*-----------------------------------------------
   Programa :  WFTipoCampo.java
   Fecha    :  03-06-2003
   Estado   :  Desarrollo
-----------------------------------------------*/
package totem.lib;

import  java.io.*;
import  java.util.*;
import  java.sql.*;


/**
 * @author David Vargas Ruiz
 * @version 0.7.1
 */
class WFTipoCampo extends Object
{
   private String cod_tipo_campo, des_tipo_campo, cod_mascara;
   
   
   WFTipoCampo(Connection p_con, String p_cod_tipo_campo)
   {
      cod_tipo_campo=null; des_tipo_campo=null; cod_mascara=null;
      
      Statement st1;
      ResultSet rs1;
      try
      {
	 st1 = p_con.createStatement();
	 rs1 = st1.executeQuery("SELECT * FROM SYS_M_TIPOS_CAMPO WHERE cod_tipo_campo='"+p_cod_tipo_campo+"';");
	 if (rs1.next())
	 { 
	    //Cargar las variables de la tabla CAMPO en el objeto java
	    cod_tipo_campo=p_cod_tipo_campo;des_tipo_campo=rs1.getString("DES_TIPO_CAMPO");
	    cod_mascara=rs1.getString("COD_MASCARA");
	 }
	 rs1.close();
	 st1.close();
      }
      catch(Exception e)
      {  System.err.println("Exception WFTipoCampo("+p_cod_tipo_campo+") REG_NOTFOUND: " + e);}
   }

   
   //===========
   //Métodos GET
   //===========
   public String   leeCodMascaraTipoCampo()   { return cod_mascara;}
   public String   leeCodTipoCampo()          { return cod_tipo_campo;}
   public String   leeDesTipoCampo()          { return des_tipo_campo;}

   
}
//Fin de la clase WFTipoCampo.java

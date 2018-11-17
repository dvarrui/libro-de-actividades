/*-----------------------------------------------
   Programa :  WFTipoTicket.java
   Versión  :  0.5.0
   Fecha    :  06-05-2003
   Estado   :  OK
-----------------------------------------------*/
package totem.lib;

import  java.io.*;
import  java.util.*;
import  java.sql.*;


public class WFTipoTicket extends Object
{
   private int 		   cod_tipo_ticket;
   private String	   des_tipo_ticket, cod_usuario;
   private java.util.Date  fec_ult_mod;
   
   
   //=======================
   //Constructor de la clase
   //=======================
   public WFTipoTicket(Connection p_con, int p_cod_tipo_ticket)
   {
      cod_tipo_ticket=-1; des_tipo_ticket=null; cod_usuario=null; fec_ult_mod=null;
      
      Statement st1;
      ResultSet rs1;
      try
      {
	 st1 = p_con.createStatement();
	 rs1 = st1.executeQuery("SELECT * FROM DEF_M_TIPOS_TICKET WHERE cod_tipo_ticket="+p_cod_tipo_ticket+";");
	 if (rs1.next())
	 { 
	    //Cargar las variables de la tabla TIPO_TICKET en el objeto java
	    cod_tipo_ticket=p_cod_tipo_ticket;des_tipo_ticket=rs1.getString("DES_TIPO_TICKET");
            cod_usuario=rs1.getString("COD_USUARIO");fec_ult_mod=rs1.getDate("FEC_ULT_MOD");
	 }
	 rs1.close();
	 st1.close();
      }
      catch(Exception e)
      {  System.err.println("Exception WFTipoTicket("+p_cod_tipo_ticket+") REG_NOTFOUND: " + e);}
   }

   
   //===========
   //Métodos GET
   //===========
   public int	         leeCodTipoTicket()   { return cod_tipo_ticket;}
   public String         leeCodUsuario()      { return cod_usuario;}
   public String         leeDesTipoTicket()   { return des_tipo_ticket;}
   public java.util.Date leeFecUltMod()       { return fec_ult_mod;}
   
   
   //=========
   //verInfo()
   //=========
   public void verInfo()
   {
      System.out.println("WFTipoTicket.verInfo():");
      System.out.println("tipo ticket = "+des_tipo_ticket+" ("+cod_tipo_ticket+")");
   }
}
//Fin de la clase WFTipoTicket.java

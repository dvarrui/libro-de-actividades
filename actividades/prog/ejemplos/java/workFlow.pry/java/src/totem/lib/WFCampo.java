/*-----------------------------------------------
   Programa :  WFCampo.java
   Versión  :  0.5.0
   Fecha    :  05-05-2003
   Estado   :  Desarrollo
-----------------------------------------------*/
package totem.lib;

import  java.io.*;
import  java.util.*;
import  java.sql.*;


class WFCampo extends Object
{
   
   private int		   cod_campo;
   private String	   cod_usuario, des_campo, cod_mascara, des_mascara;
   private java.util.Date  fec_ult_mod;
   private WFTipoCampo     tipo_campo;
   
   
   //=======================
   //Constructor de la clase
   //=======================
   WFCampo(Connection p_con, int p_cod_campo)
   {
      cod_campo=-1;des_campo=null;tipo_campo=null;cod_mascara=null;des_mascara=null;cod_usuario=null;fec_ult_mod=null;
      
      Statement st1;
      ResultSet rs1;
      try
      {
	 st1 = p_con.createStatement();
	 rs1 = st1.executeQuery("SELECT * FROM DEF_M_CAMPOS WHERE cod_campo="+p_cod_campo+";");
	 if (rs1.next())
	 { 
	    //Cargar las variables de la tabla CAMPO en el objeto java
	    cod_campo=p_cod_campo;
            des_campo=rs1.getString("DES_CAMPO"); 
	    tipo_campo=new WFTipoCampo(p_con,rs1.getString("COD_TIPO_CAMPO"));
            cod_mascara=rs1.getString("COD_MASCARA");des_mascara=rs1.getString("DES_MASCARA");
	    fec_ult_mod=rs1.getDate("FEC_ULT_MOD");cod_usuario=rs1.getString("COD_USUARIO");
	 }
	 rs1.close();
	 st1.close();
      }
      catch(Exception e)
      {  System.err.println("Exception WFCampo() REG_NOTFOUND: " + e);}
   }

   
   //=============================================================================
   //Métodos GET
   //=============================================================================
   public int		   leeCodCampo()      { return cod_campo;}
   
   public String leeCodMascara()    
   { 
      //Devuelve la máscara del campo o del tipo de campo
      if (cod_mascara!=null && cod_mascara.length()>0)  return cod_mascara;
      if (tipo_campo.leeCodMascaraTipoCampo()!=null && tipo_campo.leeCodMascaraTipoCampo().length()>0) 
	 return tipo_campo.leeCodMascaraTipoCampo();
      return "*";
   }

   public String	   leeCodTipoCampo()  { return tipo_campo.leeCodTipoCampo();}
   public String	   leeCodUsuario()    { return cod_usuario;}
   public String	   leeDesCampo()      { return des_campo;}
   
   public String leeDesMascara()    
   { 
      //Devuelve la máscara del campo o del tipo de campo
      if (des_mascara!=null && des_mascara.length()>0)  return des_mascara;
      if (tipo_campo.leeDesTipoCampo()!=null && tipo_campo.leeDesTipoCampo().length()>0) 
	 return tipo_campo.leeDesTipoCampo();
      return "(Sin especificar)";
   }
   
   public String	   leeDesTipoCampo()  { return tipo_campo.leeDesTipoCampo();}
   public java.util.Date   leeFecUltMod()     { return fec_ult_mod;}   
   
   
   //=========
   //verInfo()
   //=========
   public void verInfo()
   {
      System.out.println("WFCampo.verInfo():");
      System.out.println("des_campo       = "+des_campo+" ["+cod_campo+"]");
      System.out.println("tipo_campo      = "+tipo_campo.leeDesTipoCampo()+" ["+tipo_campo.leeCodTipoCampo()+"]");
      System.out.println("fec_ult_mod     = "+fec_ult_mod+" ["+cod_usuario+"]");
      System.out.println();
   }
}
//Fin de la clase WFCampo.java

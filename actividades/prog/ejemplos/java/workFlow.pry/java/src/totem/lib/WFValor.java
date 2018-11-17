/*-----------------------------------------------
   Programa :  WFValor.java
   Fecha    :  11-06-2003
   Estado   :  Desarrollo
-----------------------------------------------*/
package totem.lib;

import  java.io.*;
import  java.util.*;
import  java.sql.*;
import  java.util.regex.*;

/**
 * 
 * @author David Vargas Ruiz
 * @version 0.7.2
 */
public class WFValor extends Object
{
   final int OK=0, UPDATED=1, NUM_HISTORICO=20;
   
   private WFLinea	   linea;
   private WFCampo         campo;
   private String	   cod_usuario, valor;
   private java.util.Date  fec_ult_mod;
   private int		   cod_estado;
   private boolean	   chk_modificable, chk_notnull;
   private Vector	   historico;

   
   //=======================
   //Constructor de la clase
   //=======================
   public WFValor(WFLinea p_linea, int p_cod_campo)
   {
      linea=null;campo=null;valor=null;fec_ult_mod=null;cod_usuario=null;cod_estado=OK;
      chk_modificable=false;chk_notnull=false; historico = new Vector(NUM_HISTORICO);
      
      Statement st1; ResultSet rs1;
      try
      {
	 st1 = p_linea.leeConnection().createStatement();
	 //Localizar los campos del registro valor-tarea-campo
	 rs1 = st1.executeQuery("SELECT val.* "
		  +"FROM JOB_D_LINEA_VALORES val "
		  +" WHERE val.cod_linea="+p_linea.leeCodLinea()
		  +" AND   val.cod_campo="+p_cod_campo+";");

	 if (rs1.next())
	 { 
	    //Cargar las variables de la tabla LINEA_VALORES en el objeto java
	    linea=p_linea;
            campo= new WFCampo(p_linea.leeConnection(),p_cod_campo); valor=rs1.getString("VALOR");
	    fec_ult_mod=rs1.getDate("FEC_ULT_MOD");cod_usuario=rs1.getString("COD_USUARIO");
	    //chk_modificable= rs1.getBoolean("CHK_MODIFICABLE");chk_notnull=rs1.getBoolean("CHK_NOTNULL");

	    //Cargar las variables de la tabla LINEA_HIST_VALORES en el objeto java
	    rs1 = st1.executeQuery("SELECT his.* FROM JOB_D_LINEA_HIST_VALORES his "
		  +" WHERE his.cod_linea="+linea.leeCodLinea()
		  +" AND   his.cod_campo="+p_cod_campo+" ORDER BY FECHA;");
	    
	    for(int i=0;i<NUM_HISTORICO;i++)
	    {
	       if (rs1.next()) 
	       {
		  String s1,s2;
		  java.util.Date f;
		  s1=rs1.getString("VALOR");s2=rs1.getString("COD_USUARIO");f=rs1.getDate("FECHA");
	          historico.addElement(new WFHistValor(linea.leeCodLinea(),p_cod_campo,s1,f,s2));
	       }
	       else i=NUM_HISTORICO;
	    }
	 }
	 rs1.close();
	 st1.close();
      }
      catch(Exception e)
      {  
         System.err.println("Exception WFValor() REG_NOTFOUND: " + e);
         System.err.println("Ticket="+p_linea.leeCodTicket()+", linea="+p_linea.leeCodLinea()+", campo="+p_cod_campo);
      }
   }

   
   
   //============================================================================
   //Métodos LEE
   //============================================================================
   public int		   leeCodCampo()     { return campo.leeCodCampo();}
   public String	   leeCodUsuario()   { return cod_usuario;}
   public String	   leeCodTipoCampo() { return campo.leeCodTipoCampo();}
   public String	   leeDesCampo()     { return campo.leeDesCampo();}
   public String	   leeDesTipoCampo() { return campo.leeDesTipoCampo();}
   public java.util.Date   leeFecUltMod()    { return fec_ult_mod;}
   public WFHistValor      leeHistValor(int i)
   {
      if (i<historico.size()) return (WFHistValor) historico.get(i);
      return null;
   }
   public int   	   leeNumHistValores() { return historico.size();}
   public String	   leeValor()	     { return valor;}
   
   
   //============================================================================
   //Métodos PON
   //============================================================================
   
   //==========
   //setValor()
   //==========
   public boolean setValor(String s)
   { 
      if (valor.equals(s)&&cod_estado!=UPDATED) return false;

      Pattern patron = Pattern.compile(campo.leeCodMascara());
      Matcher encaja = patron.matcher(s);
      
      if (encaja.matches())
      {  //Chequear que <s> es del tipo adecuado según el tipo de campo
	 valor=new String(s); fec_ult_mod=new java.util.Date(); cod_usuario=new String(linea.leeCodUsuarioSesion());
	 cod_estado=UPDATED;
	 return true;
      }
      return false;
   }

   
   //========
   //commit()
   //========
   public boolean commit()
   { 
      if (cod_estado==UPDATED)
      {
	 Statement st1,st2;
	 ResultSet rs1;
	 try
	 {
	    st1 = linea.leeConnection().createStatement();
	    st1.executeUpdate("INSERT INTO JOB_D_TICKET_HIST_VALORES (cod_ticket,cod_linea,cod_campo,valor,cod_usuario)"
		  +" VALUES("+linea.leeCodTicket()+","+linea.leeCodLinea()+","
		  +campo.leeCodCampo()+",'"+valor+"','"+cod_usuario+"')");
	    st1.executeUpdate("UPDATE JOB_D_LINEA_VALORES SET valor='"+valor+"', cod_usuario='"+cod_usuario+"' "
		  +" WHERE cod_ticket="+linea.leeCodTicket()+" AND cod_linea="+linea.leeCodLinea()
		  +" AND cod_campo="+campo.leeCodCampo());
	    st1.close();
	    cod_estado=OK;
	    return true;
	 }
	 catch(Exception e)
	 { System.err.println("Exception WFValor().commit("+linea.leeCodTicket()+","+campo.leeCodCampo()+"):"+e);}
      }
      return true;
   }
}
//Fin de la clase WFValor.java

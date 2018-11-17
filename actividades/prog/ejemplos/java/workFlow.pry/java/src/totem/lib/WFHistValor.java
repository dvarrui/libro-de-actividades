/*-----------------------------------------------
   Programa :  WFHistValor.java
   Fecha    :  11-06-2003
-----------------------------------------------*/
package totem.lib;

import java.io.*;
import java.util.*;
import java.sql.*;

/**
 * Representa la entrada en el histórico de cada valor.
 * El valor se identifica por la tupla (Línea, campo).
 *
 * @author David Vargas Ruiz
 * @version 0.7.3
 */
public class WFHistValor extends Object
{
   
   private int		   cod_linea;
   private int		   cod_campo;
   private String	   cod_usuario, valor;
   private java.util.Date  fecha;
   private int		   cod_estado;

   
   //=======================
   //Constructor de la clase
   //=======================
   public WFHistValor(int p_cod_linea,int p_cod_campo,String p_valor,java.util.Date p_fecha,String p_cod_usuario)
   {
      cod_linea   =p_cod_linea;
      cod_campo	  =p_cod_campo;
      valor	  =new String(p_valor);
      fecha	  =p_fecha;
      cod_usuario =new String(p_cod_usuario);
   }

   //===========
   //Métodos LEE
   //===========
   public int		   leeCodLinea()     { return cod_linea;}
   public int		   leeCodCampo()     { return cod_campo;}
   public String	   leeValor()	     { return valor;}
   public java.util.Date   leeFecha()	     { return fecha;}
   public String	   leeCodUsuario()   { return cod_usuario;}

}
//Fin de la clase WFHistValor.java

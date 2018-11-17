/*
 Capa1Predicado.java
 creacion    09-10-2003
 fec_ult_mod 27-10-2003
*/

package angel.lib;

import  java.io.*;
import 	java.lang.*;
import  java.util.*;
import  java.sql.*;


/**
 * @author David Vargas Ruiz
 * @version 0.1.0
 */
public class Capa1Predicado extends Object
{
   public final int         ERROR=-1, VACIO=0, NUEVO=1, OK=2, OK_BUSCADO=3, OK_INSERTADO=4, OK_BORRADO=5;
   final int                MODO_TEXTO=10, MODO_REGISTRO=11;
   private int              cod_estado, modo_busqueda, cod_registro, bd_cod_tipo_predicado;
   private String           des_estado, bd_contexto;
   private Sesion           sesion;
   private Predicado        predicado;
   private java.util.Date   fec_ult_mod;

   
   //================================
   // Constructor de la clase
   //================================ 
   /*public Capa1Predicado()
   { cod_estado=VACIO; des_estado=new String("VACIO"); sesion=null; predicado=null; 
     modo_busqueda=MODO_TEXTO;cod_registro=-1;
   }*/

   public void inicializar(Sesion p_sesion)
   { cod_estado=VACIO; des_estado=new String("VACIO"); sesion=p_sesion; predicado=null; 
     modo_busqueda=MODO_TEXTO;cod_registro=-1;
   }

   
   //============
   // Métodos PON
   //============
   public void ponCodigoRegistro(int p_cod_registro)
   {
      if (p_cod_registro>0)
      {
	 modo_busqueda=MODO_REGISTRO; predicado=null; cod_registro=p_cod_registro; 
	 cod_estado=NUEVO; des_estado=new String("NUEVO");
	 bd_cod_tipo_predicado=1; bd_contexto=new String("*"); fec_ult_mod=new java.util.Date();
      }
      else
      {  cod_estado=ERROR; des_estado=new String("ERROR 01: ponCodigoRegistro()"); }
   }


   public void ponPredicado(Predicado p_pred)
   {
      if (p_pred.estaCorrecto())
      {
         modo_busqueda=MODO_TEXTO; predicado=p_pred; cod_registro=-1; 
	 cod_estado=NUEVO; des_estado=new String("NUEVO");
	 bd_cod_tipo_predicado=1; bd_contexto = new String("*"); fec_ult_mod=new java.util.Date();
      }
      else
      {  cod_estado=ERROR; des_estado=new String("ERROR 02: ponPredicado()"); }
   }

   
   //============
   // Métodos LEE
   //============
   public int         leeCodRegistro()    { return cod_registro; }
   public String      leeContexto()       { return bd_contexto; }
   public int         leeTipoPredicado()  { return bd_cod_tipo_predicado;}
   public int         leeCodEstado()      { return cod_estado; }
   public String      leeDesEstado()      { return des_estado; }
   public Predicado   leePredicado()      { return predicado;}


   //========================================================================================
   /**
    * @return Devuelve <b>verdadero</b> si se borró el predicado de la BD.
    */
   public boolean borrar()
   {
      //Es necesario tener el código de registro que almacena el predicado en la BD para poder borrarlo
      if (cod_registro<0||cod_estado!=OK) return false;
      Statement st1, st2; ResultSet rs1; StringBuffer sb; int i;
      
      try
      {
	 st1 = sesion.leeConnection().createStatement();
	 st2 = sesion.leeConnection().createStatement();
         //---------------------
	 //Limpiar las búsquedas
         rs1=st1.executeQuery("SELECT COD_BUSQUEDA FROM TRA_D_BUSQUEDA_DETALLE WHERE COD_PREDICADO="+cod_registro
			 +" ORDER BY COD_BUSQUEDA;");
	 while(rs1.next())
	 {
	    i=rs1.getInt("COD_BUSQUEDA");
            st2.executeUpdate("UPDATE TRA_M_BUSQUEDA_CABECERA SET REFRESCAR='true' WHERE COD_BUSQUEDA="+i+";");
            st2.executeUpdate("DELETE * FROM TRA_D_BUSQUEDA_DETALLE WHERE COD_PREDICADO="+cod_registro+";");
	 }
	 rs1.close();
         //-------------------------
         //Primero borrar el detalle
	 sb= new StringBuffer("DELETE * FROM TRA_D_PREDICADOS_DETALLE WHERE COD_PREDICADO="+cod_registro+";");
         st1.executeUpdate(sb.toString());	 
	 //------------------------
         //Luego borrar la cabecera
	 sb= new StringBuffer("DELETE * FROM TRA_M_PREDICADOS_CABECERA WHERE COD_PREDICADO="+cod_registro+";");
         st1.executeUpdate(sb.toString());	 
         return true;
      }
      catch(Exception e)
      { 
         cod_estado=ERROR; des_estado=new String("ERROR: Capa1Registro.borrar()"); 
	 System.err.println("Exception en Capa1Registro.borrar(): " + e); 
      }
      return false;
   }

   
   //========================================================================================
   /**
    * @return Devuelve <b>verdadero</b> si localizó el predicado en la BD.
    */
   public boolean buscar()
   {
      //Buscar el registro tipo predicado en la BBDD y localizar su código ID
      //Si el estado es NUEVO u OK entonces si se realiza la busqueda.
      if (cod_estado!=NUEVO&&cod_estado!=OK) return false;
      
      Statement st1; ResultSet rs1; StringBuffer sb;
      try
      {
	 st1 = sesion.leeConnection().createStatement();
	 if (modo_busqueda==MODO_TEXTO)
         {
	    //----------------------------------------------------
	    //Localizar código del registro a partir del Predicado
	    //----------------------------------------------------
            //Crear 'SELECT * FROM <tablas>'
	    sb = new StringBuffer("SELECT MIN(cab.COD_PREDICADO) as ID, MIN(cab.CONTEXTO) as CONTEXTO0 "
			    +"FROM TRA_M_PREDICADOS_CABECERA cab,TRA_D_PREDICADOS_DETALLE det0 ");
	    if (predicado.leeNumeroPredicados()>=2) sb.append(", TRA_D_PREDICADOS_DETALLE det1 ");
	    if (predicado.leeNumeroPredicados()>=3) sb.append(", TRA_D_PREDICADOS_DETALLE det2 ");
	    if (predicado.leeNumeroPredicados()>=4) sb.append(", TRA_D_PREDICADOS_DETALLE det3 ");
	    if (predicado.leeNumeroPredicados()==5) sb.append(", TRA_D_PREDICADOS_DETALLE det4 ");
	    if (predicado.leeNumeroPredicados()>5)
            {		    
	       cod_estado=ERROR; des_estado=new String("ERROR 03: buscar()");
               System.err.println("ERROR 03: buscar()");
               return false;
            }
            //Crear cláusulas 'WHERE * AND'
	    sb.append(" WHERE cab.PROFUNDIDAD="+predicado.leeProfundidad()
		     +"  AND  cab.NUM_PREDICADOS="+predicado.leeNumeroPredicados()
		     +"  AND  cab.NUM_TERMINOS="+predicado.leeNumeroTerminos());
	    
            for(int i=0;i<predicado.leeNumeroPredicados();i++)
            {
	       sb.append(" AND det"+i+".COD_PREDICADO=cab.COD_PREDICADO AND det"+i+".COD_SUBPREDICADO="+i+" ");
	       
	       for(int j=0;j<predicado.leeNumeroTerminosPredicado(i);j++)
	       { sb.append(" AND det"+i+".ARG"+j+"='"+predicado.leeTermino(i,j)+"' ");}
	       for(int j=predicado.leeNumeroTerminosPredicado(i);j<Predicado.NUM_MAX_COLUMNAS;j++)
	       { sb.append(" AND det"+i+".ARG"+j+" IS NULL ");}
            }
	    sb.append(";"); rs1=st1.executeQuery(sb.toString());
	    rs1.next(); cod_registro=rs1.getInt("ID"); bd_contexto=rs1.getString("CONTEXTO0"); 
	    rs1.close();
            st1.close();
	 }
	 else if (modo_busqueda==MODO_REGISTRO)
         {
	    //------------------------------------------------------
	    //Localizar el predicado a partir del código de registro

            //Crear 'SELECT * FORM <tablas>'
	    sb = new StringBuffer("SELECT MIN(cab.COD_PREDICADO) as ID, MIN(cab.CONTEXTO) as CONTEXTO0 "
			    +"FROM TRA_M_PREDICADOS_CABECERA cab,TRA_D_PREDICADOS_DETALLE det0 ");
	    if (predicado.leeNumeroPredicados()>=2) sb.append(", TRA_D_PREDICADOS_DETALLE det1 ");
	    if (predicado.leeNumeroPredicados()>=3) sb.append(", TRA_D_PREDICADOS_DETALLE det2 ");
	    if (predicado.leeNumeroPredicados()>=4) sb.append(", TRA_D_PREDICADOS_DETALLE det3 ");
	    if (predicado.leeNumeroPredicados()==5) sb.append(", TRA_D_PREDICADOS_DETALLE det4 ");
	    if (predicado.leeNumeroPredicados()>5)
            {		    
	       cod_estado=ERROR; des_estado=new String("ERROR 03: buscar()");
               System.err.println("ERROR 03: buscar()");
               return false;
            }
            //Crear cláusulas 'WHERE * AND'
	    sb.append(" WHERE cab.PROFUNDIDAD="+predicado.leeProfundidad()
		     +"  AND  cab.NUM_PREDICADOS="+predicado.leeNumeroPredicados()
		     +"  AND  cab.NUM_TERMINOS="+predicado.leeNumeroTerminos());
	    
            for(int i=0;i<predicado.leeNumeroPredicados();i++)
            {
	       sb.append(" AND det"+i+".COD_PREDICADO=cab.COD_PREDICADO AND det"+i+".COD_SUBPREDICADO="+i+" ");
	       
	       for(int j=0;j<predicado.leeNumeroTerminosPredicado(i);j++)
	       { sb.append(" AND det"+i+".ARG"+j+"='"+predicado.leeTermino(i,j)+"' ");}
	       for(int j=predicado.leeNumeroTerminosPredicado(i);j<Predicado.NUM_MAX_COLUMNAS;j++)
	       { sb.append(" AND det"+i+".ARG"+j+" IS NULL ");}
            }
	    sb.append(";"); //System.out.println(sb.toString()); 
	    rs1=st1.executeQuery(sb.toString());
	    rs1.next(); cod_registro=rs1.getInt("ID"); bd_contexto=rs1.getString("CONTEXTO0"); 
	    rs1.close();
            st1.close();
	 }
	 else 
         { 
	    cod_estado=ERROR; des_estado=new String("ERROR buscar()"); 
	    System.err.println("ERROR buscar() "); return false;
	 }
         return true;
      }
      catch(Exception e)
      { cod_estado=ERROR; des_estado=new String("ERROR buscar()"); System.err.println("Exception en buscar(): " + e); }
      return false;
   }

   
   //========================================================================================
   /**
    * @return Devuelve <b>verdadero</b> si se realizó la inserción del predicado. 
    */
   public boolean insertar()
   {
      //Si el predicado ya existe en la capa1 entonces no lo insertamos
      if (cod_estado!=NUEVO||modo_busqueda!=MODO_TEXTO||this.buscar()) return false;
      
      Statement st1; ResultSet rs1; StringBuffer sb;
      try
      {
	 st1 = sesion.leeConnection().createStatement();
	 //-------------------------------------
	 //Localizar un código de registro libre
	 rs1 = st1.executeQuery("SELECT MAX(COD_PREDICADO) as ID FROM TRA_M_PREDICADOS_CABECERA;");
	 rs1.next(); cod_registro=rs1.getInt("ID")+1; rs1.close();

         //----------------------------------
	 //Insertar la cabecera del predicado
         st1.executeUpdate("INSERT INTO TRA_M_PREDICADOS_CABECERA "
			 +"(COD_PREDICADO,CONTEXTO,DES_PREDICADO,COD_TIPO_PREDICADO,"
			 +"PROFUNDIDAD,NUM_PREDICADOS,NUM_TERMINOS,COD_USUARIO) VALUES('"
		         +cod_registro+"','"+bd_contexto+"','"+predicado.leeDesPredicado()+"','"
			 +bd_cod_tipo_predicado+"','"+predicado.leeProfundidad()+"','"
			 +predicado.leeNumeroPredicados()+"','"+predicado.leeNumeroTerminos()+"','"
			 +sesion.leeCodUsuario()+"');");
	 
	 //--------------------------
	 //Insertar Predicado Detalle
         for(int i=0;i<predicado.leeNumeroPredicados();i++)
         {
            sb=new StringBuffer("INSERT INTO TRA_D_PREDICADOS_DETALLE (COD_PREDICADO,COD_SUBPREDICADO");
	    for(int j=0;j<predicado.leeNumeroTerminosPredicado(i);j++) sb.append(",ARG"+j);
	    sb.append(") VALUES('"+cod_registro+"','"+i+"'");
	    for(int j=0;j<predicado.leeNumeroTerminosPredicado(i);j++) sb.append(",'"+predicado.leeTermino(i,j)+"'");
	       
	    /*for(int j=predicado.leeNumeroTerminosPredicado(i);j<Predicado.NUM_MAX_COLUMNAS;j++)
	       sb.append(",");*/
	    sb.append(");"); //System.out.println("sql_insert="+sb.toString());
	    st1.executeUpdate(sb.toString());
         }
         st1.close(); cod_estado=OK; des_estado=new String("OK");
         return true;
      }
      catch(Exception e)
      { 
	cod_estado=ERROR; des_estado=new String("ERROR Capa1Predicado.insertar()");
        System.err.println("Exception en insertar(): " + e);
      }
      return false;
   }
   
} //Fin de Capa1Predicado.java

/*-----------------------------------------------
   Programa :  WFBatchSQL.java
   Fecha    :  03-07-2003
   Estado   :  Desarrollo
-----------------------------------------------*/
package totem.bd;

import  totem.lib.*;
import  totem.utl.*;
import  java.io.*;
import  java.util.*;
import  java.sql.*;


/**
 * Ejecuta una serie de scripts SQL sobre la base de datos conectada.
 * 
 * @author David Vargas Ruiz
 * @version 0.7.4
 */
public class WFBatchSQL extends Object
{
   private Connection          con;
   private utlPropiedades      prop;
   private int                 tipo_elegido;
   private String              path, secuencia;
   private Vector              tipo, path2, fichero;
   private WFRegistrarConexion logger;

   
   //=======================
   //Constructor de la clase
   //=======================
   public WFBatchSQL(Connection p_con)
   {
      con=p_con;
      prop = new utlPropiedades();
      prop.setNombreFichero("totem.rec.WFBatchSQL");
      tipo_elegido=-1; path=null; secuencia=new String("A");
      
      tipo=new Vector(prop.getInt("Numero_de_TiposBD"));
      for(int i=0;i<prop.getInt("Numero_de_TiposBD");i++) { tipo.add(prop.getStringElement("TipoBD",i));}
      path2=new Vector(prop.getInt("Numero_de_TiposBD"));
      for(int i=0;i<prop.getInt("Numero_de_TiposBD");i++) { path2.add(prop.getStringElement("PATH2",i));}
      fichero=new Vector(prop.getInt("Numero_de_Ficheros_"+secuencia));
      for(int i=0;i<prop.getInt("Numero_de_Ficheros_"+secuencia);i++)   { fichero.add(prop.getString(secuencia+(i+1)));}
      logger=new WFRegistrarConexion(con);  logger.ponModulo("WFBatchSQL");
   }
   
   
   //===================================================================
   /**
    * Ejecuta una secuencia de script's SQL.
    * @return Devuelve <b>verdadero</b> si tiene éxito.
    */
   public boolean ejecutarSecuencia()
   {
      if (tipo_elegido<0) return false;
      boolean b = true;
      for(int i=0;i<fichero.size();i++)
      {
         b=b&&ejecutarFichero(i);
         if (b) logger.registrarLOG("Resultado ["+leeFichero(i)+"] correcto? "+b);
	 else   logger.registrarWRN("Resultado ["+leeFichero(i)+"] correcto? "+b);
      }
      if (b) logger.registrarLOG("Todo correcto? "+b);
      else   logger.registrarERR("Todo correcto? "+b);
      return b;
   }

   //===================================================================
   /**
    * Ejecuta el script's SQL i-ésimo.
    * @return Devuelve <b>verdadero</b> si tiene éxito.
    */
   public boolean ejecutarFichero(int i)
   {
      if (tipo_elegido<0) return false;
      BufferedReader br;  String str, str1; StringBuffer strb = new StringBuffer(); 
      Statement st1;
      try
      {
         File f = new File(path+(String)fichero.get(i)); 
         if (f.canRead()) 
         {
            //El fichero A1 no se registra en el LOG porque éste todavía no existe
            if (i>1) logger.registrarLOG("Ejecutando...="+path+(String)fichero.get(i));
	       
	    br = new BufferedReader (new FileReader(f));
            while ((str=br.readLine()) != null)
	    {
	       if (str.startsWith("#")||str.startsWith("--")||str.startsWith("//"))
	       { /*Tenemos una línea de comentarios*/  }
	       else if (str.indexOf(";")>0)
	       { 
		  /*Tenemos una línea completa para ejecutar*/
	          strb.append(str.substring(0,str.indexOf(";")+1));
		  
		  //Registramos la acción en la tabla de LOG
		  //El fichero A1 no se registra en el LOG porque éste todavía no existe
		  if (i>1)
	          {  
		     if (!logger.registrarLOG(strb.substring(0,(strb.length()>90?90:strb.length())))) return false;
                  }
                  //Ejecutamos la acción sobre la instancia de la BBDD
		  st1 = con.createStatement();
                  st1.execute(new String(strb));
		  
		  /*if (!st1.execute(new String(strb)))
		  { logger.registrarERR(strb.substring(0,(strb.length()>90?90:strb.length())));return false;}
		  */
		  //st1.executeUpdate(new String(strb));
                  //st1.close();
		  
		  if (str.length()>str.indexOf(";")+1) strb=new StringBuffer(str.substring(str.indexOf(";")+1));
		  else strb=new StringBuffer();
	       }
	       else if (str.length()>0)
	       {  /*Tenemos una línea incompleta*/
		  //str1 = new String(str.replaceAll("\s\s"," "));  str = new String(str1);
		  //str1 = new String(str.replaceAll("\ ,\ ",","));  str = new String(str1);
		  strb.append(str);
	       }
            }
            if (i>1) //(i==0) 
	    {
	       logger.registrarLOG("Ejecutado="+(String)fichero.get(i));
	    }
	    return true;	 
         }
      }
      catch (Exception e)
      { System.err.println("ERROR WFBatchSQL:"+e+",["+secuencia+(i+1)+"]"); return false;}
      return false;
   }
   

   //===================================================================
   /**
    * @return Devuelve el nombre del fichero i-ésimo.
    */
   public String leeFichero(int i)
   {
      if (i<fichero.size()) return (String)fichero.get(i);
      return null;
   }
   

   //===================================================================
   /**
    * @return Devuelve el nombre del tipo i-ésimo.
    */
   public String leeRuta(int i) {if (i<path2.size()) return (String)path2.get(i);return null;}
   
   //==================================================
   /**
    * @return Devuelve el identificador de la secuencia
    */
   public String leeSecuencia()  {return secuencia;}

   //===================================================================
   /**
    * @return Devuelve el nombre del tipo i-ésimo.
    */
   public String leeTipo(int i)  {if (i<tipo.size()) return (String)tipo.get(i); return null;   }


   //===================================================================
   /**
    * @return Devuelve el nombre del tipo i-ésimo.
    */
   public String leeTipoElegido() {if (tipo_elegido<0) return "(Ninguno)";return (String)tipo.get(tipo_elegido);}
   
   
   //===================================================================
   /**
    * @return Devuelve el número de ficheros.
    */
   public int leeNumFicheros()   { return fichero.size();}

   
   //===================================================================
   /**
    * @return Devuelve el número de tipos.
    */
   public int leeNumTipos()   { return tipo.size();}

   
   //===================================================================
   public boolean ponTipoElegido(int i)   
   {
      if (i>=0&&i<tipo.size())
      {tipo_elegido=i; path=new String(prop.getString("PATH1")+(String)path2.get(i));return true;}
      return false;
   }
}
//Fin de la clase WFBatchSQL.java

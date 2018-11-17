/*-----------------------------------------------
   Programa :  WFTicket.java
   Fecha    :  16-06-2003
   Estado   :  Desarrollo
   Futuro   :  Ampliar el tema de los permisos, ejecutar transición TT
-----------------------------------------------*/
package totem.lib;

import  java.io.*;
import  java.util.*;
import  java.sql.*;


/**
 * 
 * Clase para tratar los tiquets.
 * 
 * @author David Vargas Ruiz
 * @version 0.7.5
 * 
 */
public class WFTicket extends Object
{
   final int   NUM_TRANSICIONES=10, NUM_LINEAS=10, NUM_HIST_TRANSICIONES=20;
   final int   ERROR=-1, NOTFOUND=-2;
   final int   VACIO=0, NUEVO=1, OK=2, OK_CARGADO=3, MODIF=4, BLOQUEADO=5, MODO_LECTURA=6, MODO_ESCRITURA=7;
   
   private WFFiltro	    filtro;
   private String	    des_estado, cod_usuario, cod_usuario_bloq, apunte;
   private int		    cod_estado, modo, cod_ticket,cod_ticket_padre;
   private java.util.Date   fecha_creacion, fec_ult_mod;
   private Vector	    transicion, linea, hist_transicion;
   private WFTarea	    tarea;
   private WFTipoTicket	    tipo;

   
   //=============================================================================
   //Constructor de la clase (se crea a partir de un filtro y un código de ticket)
   //El ticket ya existe en la BBDD
   //=============================================================================
   public WFTicket(WFFiltro p_f, int p_cod_ticket)
   {
      cod_estado=VACIO; des_estado=new String("VACIO"); 
      filtro = p_f; 
      cod_ticket=-1;cod_ticket_padre=-1; modo=MODO_LECTURA;
      cod_usuario=null; apunte=null; fecha_creacion=null; fec_ult_mod=null; tarea=null; tipo=null;
      transicion = new Vector(NUM_TRANSICIONES); linea=new Vector(NUM_LINEAS); 
      hist_transicion= new Vector(NUM_HIST_TRANSICIONES);
	 
      Statement st1;
      ResultSet rs1;
      try
      {
	 st1 = filtro.leeConnection().createStatement();
	 rs1 = st1.executeQuery("SELECT * FROM JOB_M_TICKETS WHERE cod_ticket="+p_cod_ticket+";");
	 if (rs1.next())
	 { 
	    //----------------------------------------------------------
	    //Cargar las variables de la tabla TICKETS en el objeto java
	    cod_ticket=rs1.getInt("COD_TICKET"); cod_ticket_padre=rs1.getInt("COD_TICKET_PADRE");
            tarea=new WFTarea(filtro.leeConnection(),rs1.getInt("COD_TAREA"));
            tipo=new WFTipoTicket(filtro.leeConnection(),rs1.getInt("COD_TIPO_TICKET"));
            fecha_creacion=rs1.getDate("FECHA_CREACION");
	    fec_ult_mod=rs1.getDate("FEC_ULT_MOD"); cod_usuario=rs1.getString("COD_USUARIO");
	    cod_estado=OK; des_estado=new String("OK");
	 }
	 else
	 { cod_estado=NOTFOUND; des_estado= new String("NOTFOUND"); }
	 rs1.close();
	 st1.close();
      }
      catch(Exception e)
      { 
         cod_estado=NOTFOUND; des_estado= new String("NOTFOUND_ERROR");
         System.err.println("Exception WFTicket() NOTFOUND: " + e);
      }
   }

   
   //=============================================================================
   //Constructor de la clase (se crea a partir de un filtro y una transición)
   //    Se insertan registros en la BBDD. 
   //    Para usar con las transiciones 0 (Inicio, nuevo TT) y F,G (Fork)
   //=============================================================================
   public WFTicket(WFFiltro p_f, int i, String p_apunte)
   {  
      WFTransicion ti = p_f.leeTransicion(i);
      cod_estado=NUEVO; des_estado=new String("NUEVO"); 
      cod_ticket=-1;cod_ticket_padre=-1; 
      apunte=new String(p_apunte); filtro = p_f; cod_usuario=filtro.leeCodUsuario();
      tarea= new WFTarea(filtro.leeConnection(),ti.leeCodTarea2()); 
      fecha_creacion=new java.util.Date(); fec_ult_mod=new java.util.Date();
      transicion = new Vector(NUM_TRANSICIONES); linea=new Vector(NUM_LINEAS);
      
      modo=MODO_LECTURA;
      Statement st1;
      ResultSet rs1;
      try
      {
	 st1 = filtro.leeConnection().createStatement();
	 //-----------------------------
	 //Localizar el siguiente TIQUET
	 rs1 = st1.executeQuery("SELECT MAX(cod_ticket) as MAX_COD_TICKET FROM JOB_M_TICKETS;");
	 rs1.next(); cod_ticket=rs1.getInt("MAX_COD_TICKET")+1;
	 rs1.close();
	 //-------------------------------------
	 //Insertar registro en la tabla TICKETS
	 if (ponModoEscritura())
	 {
	    st1.executeUpdate("INSERT INTO JOB_M_TICKETS (cod_ticket,cod_tarea,cod_usuario) "
	       +"VALUES ("+cod_ticket+","+ti.leeCodTarea2()+",'"+cod_usuario+"')");
	    //ejecutarTansicion()????
	    //-----------------------------------------------------------
	    //Insertar registro en HIST_TRANSICIONES para el nuevo estado	
	    st1.executeUpdate("INSERT INTO JOB_D_TICKET_HIST_TRANSICIONES (cod_ticket,cod_ticket_padre,cod_tarea,"+
	       "cod_transicion,apunte,cod_usuario) VALUES ("
	       +cod_ticket+",null,"+tarea.leeCodTarea()+","+ti.leeCodTransicion()+","
	       +"'"+apunte+"','"+cod_usuario+"')");
	    //-----------------------------
	    //Insertar registros en VALORES
	    //crearValores(filtro);
	    st1.close();
	    ponModoLectura();
	    cod_estado=OK; des_estado=new String("OK");
	 }
      }
      catch(Exception e)
      { 
         cod_estado=ERROR; des_estado= new String("CREAR_ERROR");
         System.err.println("ERROR WFTicket() Crear nuevo: " + e);
      }
   }
   
   
   //=============
   //cargarDatos()
   //=============
   private boolean cargarDatos()
   {
      transicion=new Vector(NUM_TRANSICIONES);linea=new Vector(NUM_LINEAS);
      hist_transicion=new Vector(NUM_HIST_TRANSICIONES); Statement st1; ResultSet rs1;
      try
      {
	 st1 = filtro.leeConnection().createStatement();
	 //---------------------------------------------------------------
	 //Cargar las variables de la tabla TRANSICIONES en el objeto java
	 rs1 = st1.executeQuery("SELECT tra.* FROM DEF_D_TRANSICIONES tra, DEF_D_PERFIL_TRANSICIONES per WHERE "
	    +"tra.cod_transicion=per.cod_transicion AND per.cod_perfil="+filtro.leeCodPerfil()
	    +" AND cod_tarea1="+tarea.leeCodTarea()+";");
	 for(int i=0;i<NUM_TRANSICIONES;i++)
	 {
	    if (rs1.next()) transicion.addElement(new WFTransicion(filtro.leeConnection(),rs1.getInt("COD_TRANSICION")));
	    else i=NUM_TRANSICIONES;
	 }
	 //-------------------------------------------------------------
	 //Cargar las líneas de la tabla TICKET_LINEAS en el objeto java
	 //Sólo se cargan las líneas-concepto visibles para la tarea actual del ticket
	 rs1.close();
	 rs1 = st1.executeQuery("SELECT lin.* FROM JOB_D_TICKET_LINEAS lin, JOB_M_TICKETS tic, "
	    +"DEF_D_TAREA_CONCEPTOS con WHERE tic.cod_ticket="+cod_ticket+" AND tic.cod_ticket=lin.cod_ticket "
	    +"AND tic.cod_tarea=con.cod_tarea AND lin.cod_concepto=con.cod_concepto "
	    +"ORDER BY lin.COD_CONCEPTO;");
	 for(int i=0;i<NUM_LINEAS;i++)
	 {
	    if (rs1.next()) linea.addElement(new WFLinea(this,rs1.getInt("COD_LINEA")));
	    else i=NUM_LINEAS;
	 }
	 //-----------------------------------------
	 //Leer histórico de transiciones del ticket
	 rs1.close();
	 rs1 = st1.executeQuery("SELECT * FROM JOB_D_TICKET_HIST_TRANSICIONES his "
	    +"WHERE his.cod_ticket="+cod_ticket+" ORDER BY his.FECHA;");
	 for(int i=0;i<NUM_HIST_TRANSICIONES;i++)
	 {
	    if (rs1.next())
	    {
	       WFHistTransicion ht =new WFHistTransicion(filtro.leeConnection());
               ht.ponApunte(rs1.getString("APUNTE"));
	       ht.ponCodTicket(rs1.getInt("COD_TICKET"));         ht.ponCodTicketPadre(rs1.getInt("COD_TICKET_PADRE"));
	       ht.ponCodUsuario(rs1.getString("COD_USUARIO"));    ht.ponCodTarea(rs1.getInt("COD_TAREA"));
	       ht.ponCodTransicion(rs1.getInt("COD_TRANSICION")); ht.ponFecha(rs1.getDate("FECHA"));
	       hist_transicion.addElement(ht);
	    }
	    else i=NUM_HIST_TRANSICIONES;
	 }

	 //----------------------------------------------------------
         cod_estado=OK_CARGADO; des_estado= new String("OK_CARGADO");
	 rs1.close(); st1.close();
	 return true;
      }
      catch(Exception e)
      { 
         cod_estado=NOTFOUND; des_estado= new String("NOTFOUND_ERROR");
         System.err.println("Exception WFTicket.cargarDatos() TT_NOTFOUND: " + e);
      }
      return false;
   }
   
   
   //====================
   //Métodos LEE públicos
   //====================
   public String	   leeApunte()		   { return apunte;}
   public int		   leeCodTarea()           { return tarea.leeCodTarea();}
   public int		   leeCodTicket()	   { return cod_ticket;}
   public int		   leeCodTicketPadre()	   { return cod_ticket_padre;}
   public String	   leeCodUsuario()	   { return cod_usuario;}
   public String	   leeCodUsuarioSesion()   { return filtro.leeCodUsuario();}
   public Connection	   leeConnection()	   { return filtro.leeConnection();}
   public String	   leeDesTarea()	   { return tarea.leeDesTarea();}
   public String	   leeDesTipoTicket()	   { return tipo.leeDesTipoTicket();}
   public java.util.Date   leeFechaCreacion()      { return fecha_creacion;}
   public java.util.Date   leeFecUltMod()	   { return fec_ult_mod;}
   public WFHistTransicion leeHistTransicion(int i) 
   {
      if(cod_estado==OK) cargarDatos();
      if (i<hist_transicion.size()) return (WFHistTransicion)hist_transicion.get(i);
      else return null;
   }
   public WFLinea	   leeLinea(int i)	   
   {
      if(cod_estado==OK) cargarDatos();
      if (i<linea.size()) return ((WFLinea) linea.get(i));
      return null;
   }
   public int		   leeNumHistTransiciones(){ if(cod_estado==OK) cargarDatos(); return hist_transicion.size();}     
   public int		   leeNumLineas()	   { if(cod_estado==OK) cargarDatos(); return linea.size();}  
   public int		   leeNumTransiciones()	   { if(cod_estado==OK) cargarDatos(); return transicion.size();}  
   public WFTransicion     leeTransicion(int i) 
   {
      if(cod_estado==OK) cargarDatos();
      if (i<transicion.size()) return (WFTransicion)transicion.get(i);
      else return null;
   }



   //========================================================================
   //Métodos SET
   //========================================================================
   
   //===========
   //ponApunte()
   //===========
   public boolean ponApunte(String p_apunte)	 
   { 
      //Actualizar el campo apunte del Object WFTicket
      //Esta variable sólo se almacena en la tabla HIST_TRANSICIONES a modo diario de trabajo
      if (modo==MODO_ESCRITURA) { apunte=new String(p_apunte); return true; }
      return false;
   }
   
   
   //================
   //ponModoLectura()
   //================
   public boolean ponModoLectura()
   {
      if (modo==MODO_LECTURA) return true;
      if (modo==MODO_ESCRITURA)
      {  
	 //PASO 01:Actualizar las lineas-valores en la BBDD antes de cambiar el modo del TT
	 if (cod_estado==MODIF) { if (!commitLineas()) return false; }
	 else if (cod_estado==ERROR) return false;
	 
	 //PASO 02:Quitar el registro de la tabla JOB_M_TICKETS_ACTIVOS
	 Statement st1,st2;
	 ResultSet rs1;
	 try
	 {
	    st1 = filtro.leeConnection().createStatement();
	    st1.executeUpdate("DELETE * FROM JOB_M_TICKETS_ACTIVOS WHERE cod_ticket="+cod_ticket+
	          "AND cod_usuario='"+filtro.leeCodUsuario()+"'");
	    rs1=st1.executeQuery("SELECT * FROM JOB_M_TICKETS_ACTIVOS WHERE cod_ticket="+cod_ticket+";");
	    if (rs1.next())
	    { 
	       //Registrar el error en las tablas de LOG de la BBDD
               st2 = filtro.leeConnection().createStatement();
	       st2.executeUpdate("INSERT INTO LOG_D_AVISOS (TIPO,USUARIO,PC,MODULO,DESCRIPCION) VALUES('ERR','"
		     +filtro.leeCodUsuario()+"','"+filtro.leePC()+"','TT_setModoLectura','Registro no borrado de la BBDD TT["
		     +cod_ticket+"]')");
               st2.close();
	       cod_estado=ERROR; des_estado=new String("ERROR");
	       return false;
	    }
	    rs1.close(); st1.close(); modo=MODO_LECTURA;
	    return true;
	 }
	 catch(Exception e)
	 { 
	    cod_estado=ERROR; des_estado= new String("ERROR");
	    System.err.println("Exception WFTicket().setModoLectura: " + e);
	 }
      }
      return false;
   }
   
   
   //==================
   //ponModoEscritura()
   //==================
   public boolean ponModoEscritura()
   {
      if (modo==MODO_ESCRITURA) return true;
      if (modo==MODO_LECTURA && (cod_estado==OK||cod_estado==OK_CARGADO||cod_estado==NUEVO))
      {
	 //Actualizar BBDD antes de cambiar el modo del TT
	 //PASO 01:Añadir el registro de la tabla JOB_M_TICKETS_ACTIVOS
	 Statement st1,st2;
	 ResultSet rs1;
	 try
	 {
	    st1 = filtro.leeConnection().createStatement();
	    st1.executeUpdate("INSERT INTO JOB_M_TICKETS_ACTIVOS (COD_TICKET,COD_USUARIO) VALUES("+cod_ticket+
	          ",'"+filtro.leeCodUsuario()+"')");
	    rs1=st1.executeQuery("SELECT * FROM JOB_M_TICKETS_ACTIVOS WHERE cod_ticket="+cod_ticket+
		  " AND cod_usuario='"+filtro.leeCodUsuario()+"';");
	    if (!rs1.next())
	    { 
	       //Registrar el error en las tablas de LOG de la BBDD
               st2 = filtro.leeConnection().createStatement();
	       st2.executeUpdate("INSERT INTO LOG_D_AVISOS (TIPO,USUARIO,PC,MODULO,DESCRIPCION) VALUES('ERR','"
		     +filtro.leeCodUsuario()+"','"+filtro.leePC()+"','TT_setModoEscritura','Registro no guardado en BBDD TT["
		     +cod_ticket+"]')");
               st2.close();
	       cod_estado=ERROR; des_estado=new String("ERROR");
	       return false;
	    }
	    rs1.close(); st1.close(); modo=MODO_ESCRITURA;
	    return true;
	 }
	 catch(Exception e)
	 { 
	    cod_estado=ERROR; des_estado= new String("ERROR");
	    System.err.println("Exception WFTicket().setModoEscritura: " + e);
	 }
      }
      return false;
   }
   

   //====================
   //ejecutarTransicion()
   //====================
   public boolean ejecutarTransicion(int i)
   {
      Statement st1,st2;
      try
      {
	 if (modo==MODO_ESCRITURA && i<transicion.size())
	 {
	    //Crear una nueva transición para el Ticket
	    //tarea = new (WFtarea) transicion.get(i).getCodTarea2();

	    st1 = filtro.leeConnection().createStatement();
	    //Modificar los campos del TICKET
	    st1.executeUpdate("UPDATE JOB_M_TICKETS WHERE cod_ticket="+cod_ticket+" SET "
	          +" apunte='"+apunte+"', fec_ult_mod='"+new java.util.Date()+"', cod_usuario='"
		  +filtro.leeCodUsuario()+"', cod_tarea="+tarea.leeCodTarea());
	    //Insertar registro en HIST_TRANSICIONES para el nuevo estado	 
	    st1.executeUpdate("INSERT INTO JOB_D_TICKET_HIST_TRANSICIONES (cod_ticket,cod_tarea,apunte,cod_usuario) "
		  +"VALUES ("+cod_ticket+","+tarea.leeCodTarea()+",'"+apunte+"','"+filtro.leeCodUsuario()+"'");
	    st1.close();
	    //¿Realizar llamada a crearLinea?
	    modo=MODO_LECTURA; //Poner filtro en estado UPDATED.*****
	    cod_estado=OK;des_estado=new String("OK");
	    return true;
	 }
	 
	 //Registrar el error en las tablas de LOG de la BBDD
	 st2 = filtro.leeConnection().createStatement();
	 st2.executeUpdate("INSERT INTO LOG_D_AVISOS (TIPO,USUARIO,PC,MODULO,DESCRIPCION) VALUES('ERR','"
	    +filtro.leeCodUsuario()+"','"+filtro.leePC()+"','TT_commitTransicion','Transición incorrecta ["+i+"] en TT["
	    +cod_ticket+"]')");
	 st2.close();
	 cod_estado=ERROR; des_estado=new String("ERROR");
      }
      catch(Exception e)
      { 
	 cod_estado=ERROR; des_estado= new String("ERROR");
	 System.err.println("Exception WFTicket().commitTransicion: " + e);
      }
      return false;
   }
   
   
   //===============
   //commitLineas()
   //===============
   public boolean commitLineas()
   {
      //Actualiza en BBDD todas las líneas en estado UPDATED
      boolean s=true;
      if (cod_estado==MODIF)
      {
         //Actualiza los valores de los campos del ticket y el histórico de valores
         for (int i=0;i<linea.size();i++) { s=s && ((WFLinea)linea.get(i)).commit(); }
      }
      return s;
   }

   
   //=============
   //crearLineas()
   //=============
   private boolean crearLineas(WFFiltro filtro)
   {
      //Crear registros en TICKET_VALORES la primera vez que se entra en la tarea
      if (cod_estado==NUEVO || modo==MODO_ESCRITURA)
      {
	 Statement st1,st2;
	 ResultSet rs1, rs2;
	 try
	 {
	    st1 = filtro.leeConnection().createStatement();
	    st2 = filtro.leeConnection().createStatement();
	    //-------------------------------------
	    //Insertar registro en la tabla TICKETS
	    rs1 = st1.executeQuery("SELECT * FROM DEF_D_TAREA_CAMPOS WHERE COD_TAREA="+tarea.leeCodTarea()
		  +" ORDER BY COD_CAMPO;");
	    while(rs1.next())
	    {
	       int j,k;
	       j=rs1.getInt("COD_CAMPO");;
	       rs2 = st2.executeQuery("SELECT count(*) as TOTAL FROM JOB_D_TICKET_VALORES WHERE COD_TICKET="+cod_ticket
		  +" AND COD_CAMPO="+j+";");
	       rs2.next();
	       k=rs2.getInt("TOTAL");
	       if(k==0)
	       {
		  //Crear el registro con el valor del ticket
		  st2.executeUpdate("INSERT INTO JOB_D_TICKET_VALORES (cod_ticket,cod_campo,valor,cod_usuario)"
			+" VALUES("+cod_ticket+","+j+",'-','"+cod_usuario+"')");
		  //¿Crear registro también el el histórico de valores del concepto?
	       }
	       //rs2.close();
	    }
	    st2.close();
	    rs1.close();
	    st1.close();
	    return true;
	 }
	 catch(Exception e)
	 { 
	    cod_estado=ERROR; des_estado= new String("CREAR_VALORES_ERROR");
	    System.err.println("ERROR WFTicket() CREAR_VALORES: " + e);
	 }
      }
      return false;
   }
   
   

   //==========
   //finalize() (Revisar si funciona correctamente)
   //==========
   protected void finalize() throws Throwable 
   {
      ponModoLectura();
      super.finalize();
   }
}
//Fin de la clase WFTicket.java

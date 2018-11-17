/*-----------------------------------------------------
   Programa :  WFFiltro.java
   Fecha    :  25-08-2003
   Estado   :  OK
   Futuro   :  criterios del filtro
-----------------------------------------------------*/
package totem.lib;

import  java.io.*;
import  java.util.*;
import  java.sql.*;

/**
 * Clase para trabajar con un conjunto de tareas (tiquets).
 * 
 * @author David Vargas Ruiz
 * @version 0.8.0
 */
public class WFFiltro extends Object
{
   final int   ERROR=-1, VACIO=0, DEF=1, ACTIVO=2, OFF=-2, MODIFICADO=-3;
   final int   NUM_REGISTROS=10, NUM_TRANSICIONES=10,NUM_TAREAS=10, NUM_TIPOS=10;
   final int   APLICADO=4, PENDIENTE=5;
   
   private WFSesion  sesion;
   private Vector    registro;   //Vector con los tiquets seleccionados
   private Vector    transicion; //Vector con las transiciones iniciales del usario
   private Vector    tarea;      //Vector con las tareas accesibles por el usuario
   private Vector    tipo;       //Vector con todos los tipos de tiquets
   
   private String    des_estado,des_estado_criterios,buscarDesPH,buscarDesTipo,buscarDesTarea;
   private int	     cod_estado,cod_estado_criterios,buscarCodTicket,buscarCodTicketPadre;
   private int       numPagina; //Numero de la página de selección actual del filtro
   private int       numRegistrosTotal; //Número de registros totales accesibles por el usuario
   java.util.Date    buscarFecDesde, buscarFecHasta;
   
   
   //=======================
   //Constructor de la clase
   //=======================
   /*public WFFiltro(WFSesion p_s)
   {
      cod_estado=VACIO; des_estado=new String("VACIO");numPagina=0;numRegistrosTotal=0;
      cod_estado_criterios=PENDIENTE; des_estado_criterios=new String("PENDIENTE");
      sesion = p_s;
      registro = new Vector(NUM_REGISTROS); transicion = new Vector(NUM_TRANSICIONES);
      tarea = new Vector(NUM_TAREAS);       tipo = new Vector(NUM_TIPOS);
      limpiaBuscar();
   }*/

   public void inicializar(WFSesion p_s)
   {
      cod_estado=VACIO; des_estado=new String("VACIO");numPagina=0;numRegistrosTotal=0;
      cod_estado_criterios=PENDIENTE; des_estado_criterios=new String("PENDIENTE");
      sesion = p_s;
      registro = new Vector(NUM_REGISTROS); transicion = new Vector(NUM_TRANSICIONES);
      tarea = new Vector(NUM_TAREAS);       tipo = new Vector(NUM_TIPOS);
      limpiaBuscar();
   }

   //======================================================================
   //Métodos SET()
   //======================================================================

   //=========
   //activar()
   //=========
   /**
    * Lee el conjunto de tiquets asignados al usuario y los carga en el objeto.
    * 
    * @return Devuelve <b>verdadero</b> si el filtro se ha activado correctamente.
    */
   public boolean activar()
   {
      desactivar(); //Me aseguro de desactivar primero el filtro antes de activarlo
      try
      {
	 String str1, str3;
	 StringBuffer str2;
	 Statement st1;
         ResultSet rs1;
	 st1 = sesion.leeConnection().createStatement();
	 
	 //Alimentar el Vector de registros
	 str1 = new String("SELECT distinct(tic.COD_TICKET) FROM JOB_M_TICKETS tic, DEF_D_TRANSICIONES tra, "
	    +"DEF_D_PERFIL_TRANSICIONES per, DEF_M_TAREAS tar "
	    +"WHERE tic.cod_tarea=tra.cod_tarea1 "
	    +"AND tra.cod_transicion=per.cod_transicion "
	    +"AND per.cod_perfil="+sesion.leeCodPerfil()+" "
	    +"AND tar.cod_tarea=tic.cod_tarea");
	 str2 = new StringBuffer("");//Aquí van los criterios de búsqueda
	 str3 = new String(";");
	 if (buscarCodTicket>0)       { str2.append(" AND tic.cod_ticket="+buscarCodTicket);}
	 if (buscarCodTicketPadre>0)  { str2.append(" AND tic.cod_ticket_padre="+buscarCodTicketPadre);}
         //if (buscarDesTipo!=null)     { str2.append(" AND tar.des_tarea='"+buscarDesTarea+"'");}
         if (buscarDesTarea!=null)    { str2.append(" AND tar.des_tarea='"+buscarDesTarea+"'");}
	 //str3 = new String(";");
	 
	 rs1 = st1.executeQuery(str1+str2+str3);
	 //rs1.relative(numPagina*NUM_REG_POR_PAGINA);
	 int j=0;
	 while(j<numPagina*NUM_REGISTROS) {j++;if (!rs1.next()) j=numPagina*NUM_REGISTROS;}
	 
	 for(int i=0;i<NUM_REGISTROS;i++)
         {
	    if (rs1.next()) registro.addElement(new WFTicket(this, rs1.getInt("COD_TICKET")));
	    else i=NUM_REGISTROS;
	 }
	 //---------------------------------------------
	 //Alimentar el Vector de transiciones iniciales
	 str1 = new String("SELECT tra.* FROM DEF_D_TRANSICIONES tra, DEF_D_PERFIL_TRANSICIONES per "
	    +"WHERE tra.cod_transicion=per.cod_transicion AND per.cod_perfil="+sesion.leeCodPerfil())
	    +" AND tra.cod_tipo_transicion='0';";
	 rs1 = st1.executeQuery(str1);
	 for(int i=0;i<NUM_TRANSICIONES;i++)
         {
	    if (rs1.next()) transicion.addElement(new WFTransicion(sesion.leeConnection(),rs1.getInt("COD_TRANSICION")));
	    else i=NUM_TRANSICIONES;
	 }
	 //----------------------------------------
	 //Alimentar el Vector de tareas accesibles
	 str1 = new String("SELECT tra.cod_tarea1 "
	    +"FROM DEF_D_TRANSICIONES tra, DEF_D_PERFIL_TRANSICIONES per "
	    +"WHERE tra.cod_transicion=per.cod_transicion AND per.cod_perfil="+sesion.leeCodPerfil()
	    +" AND  tra.cod_tipo_transicion<>'0' "
	    +" GROUP BY tra.cod_tarea1;");
	 rs1 = st1.executeQuery(str1);
	 for(int i=0;i<NUM_TAREAS;i++)
         {
	    if (rs1.next()) tarea.addElement(new WFTarea(sesion.leeConnection(),rs1.getInt("COD_TAREA1")));
	    else i=NUM_TAREAS;
	 }
         //----------------------------
	 //Alimentar el Vector de tipos
	 str1 = new String("SELECT * "
	    +"FROM DEF_M_TIPOS_TICKET tic ORDER BY COD_TIPO_TICKET;");
	 rs1 = st1.executeQuery(str1);
	 for(int i=0;i<NUM_TIPOS;i++)
         {
	    if (rs1.next()) tipo.addElement(new WFTipoTicket(sesion.leeConnection(),rs1.getInt("COD_TIPO_TICKET")));
	    else i=NUM_TIPOS;
	 }
         //------------------------------------------------	 
	 //Número total de registros accesibles sin filtrar
	 str1 = new String("SELECT count(tic.COD_TICKET) as TOTAL "
	    +"FROM JOB_M_TICKETS tic, DEF_D_TRANSICIONES tra, "
	    +"DEF_D_PERFIL_TRANSICIONES per WHERE tic.cod_tarea=tra.cod_tarea1 "
	    +"AND tra.cod_transicion=per.cod_transicion "
	    +"AND per.cod_perfil="+sesion.leeCodPerfil()+";");
         rs1 = st1.executeQuery(str1);
	 if (rs1.next()) numRegistrosTotal=rs1.getInt("TOTAL");
         //--------------
	 //Cerrar rs y st	 
	 rs1.close(); st1.close();
         cod_estado=ACTIVO;des_estado=new String("ACTIVO");
         cod_estado_criterios=APLICADO; des_estado_criterios=new String("APLICADO");
	 return true;
      }
      catch(Exception e)
      { 
	cod_estado=ERROR; des_estado= new String("ERROR");
        System.err.println("ERROR WFFiltro.activar(): " + e);
      }
      return false;
   }


   //============
   //desactivar()
   //============
   /**
    * @return Devuelve <b>verdadero</b> si se ha realizado correctamente.
    */
   public boolean desactivar()
   {  
      registro.removeAllElements();transicion.removeAllElements();tarea.removeAllElements();tipo.removeAllElements();
      cod_estado=OFF;des_estado=new String("OFF");
      return true;
   }

   
   //=============
   //crearTicket()
   //=============
   public int crearNuevoTiquet(int i, String p_apunte)
   {
      if (i<transicion.size())
      {
	 //Crear nuevo ticket en BBDD
	 WFTicket nuevoTicket = new WFTicket(this, i, p_apunte); //((WFTransicion)transicion.get(i)), p_apunte);
	 //Incluir TT en el Vector registro???
         registro.addElement(nuevoTicket);
	 return nuevoTicket.leeCodTicket();
      }
      return -1;
   }


   //==============
   //limpiaBuscar()
   //==============
   public void limpiaBuscar()
   {
      buscarCodTicket=-1;buscarCodTicketPadre=-1;
      buscarFecDesde=null;buscarFecHasta=null;
      buscarDesPH=null;buscarDesTipo=null;buscarDesTarea=null;
   }


   //===========
   //ponBuscar() 
   //===========
   /**
    * Define criterios de búsqueda sobre los registros.
    * @param p_campo Nombre del campo, s valor seleccionable
    * @return Devuelve <b>verdadero</b> si se ha realizado correctamente
    */
   public boolean ponBuscar(String p_campo, String s)
   {
      if (p_campo.equals("COD_TICKET"))            { buscarCodTicket=Integer.parseInt("0"+s); }
      else if (p_campo.equals("COD_TICKET_PADRE")) { buscarCodTicketPadre=Integer.parseInt("0"+s); }
      else if (p_campo.equals("PADRE_HIJO")) 
      {
         if (s.equals("(Ver todo)")||s.equals("*")) buscarDesPH=null;
         else  buscarDesPH =new String(s);
      }
      else if (p_campo.equals("DES_TIPO")) 
      {
         if (s.equals("(Ver todo)")||s.equals("*")) buscarDesTipo=null;
         else  buscarDesTipo =new String(s);
      }
      else if (p_campo.equals("DES_TAREA")) 
      {
         if (s.equals("(Ver todo)")||s.equals("*")) buscarDesTarea=null;
         else  buscarDesTarea =new String(s);
      }
      //public void ponBuscarFecDesde(java.util.Date p) {buscarFecDesde=new Date(p);}
      //public void ponBuscarFecHasta(java.util.Date p) {buscarFecHasta=new java.util.Date(p);} 
      cod_estado_criterios=PENDIENTE; des_estado_criterios=new String("PENDIENTE");
      return true;
   }

   
   public boolean ponNumPagina(int i)         
   {
      if (cod_estado==ACTIVO)
      { 
	 i--;
         if (i>=0 && i*NUM_REGISTROS<numRegistrosTotal)  numPagina=i;
	 else numPagina=0;
         desactivar();activar();
      }
      return true;
   } 
   

   //======================================================================
   //Métodos GET()
   //======================================================================
   public boolean       estaActivo()
   {
      if(cod_estado==ACTIVO) return true;
      return false;
   }

   public int            leeBuscarCodTicket()      { return buscarCodTicket;}
   public int            leeBuscarCodTicketPadre() { return buscarCodTicketPadre;} 
   public java.util.Date leeBuscarFecDesde()       { return buscarFecDesde;}
   public java.util.Date leeBuscarFecHasta()       { return buscarFecHasta;} 
   public String         leeBuscarDesPH()          { return buscarDesPH;}
   public String         leeBuscarDesTipo()        { return buscarDesTipo;}
   public String         leeBuscarDesTarea()       { return buscarDesTarea;} 
   public int		 leeCodPerfil()	           {  return sesion.leeCodPerfil();  }
   public String 	 leeCodUsuario()	   {  return sesion.leeCodUsuario();  }
   public Connection	 leeConnection()	   {  return sesion.leeConnection();  }
   public String	 leeDesEstado()	           {  return des_estado;  }
   public String	 leeDesEstadoCriterios()   {  return des_estado_criterios;  }
   public int 		 leeNumPaginaActual()      {  return numPagina+1;  }
   public int 		 leeNumPaginaTotal()       {  return (numRegistrosTotal/NUM_REGISTROS)+1;  }
   public int 		 leeNumRegistros()         {  return registro.size();  }
   public int 		 leeNumRegistrosTotal()    {  return numRegistrosTotal;  }
   public int 		 leeNumTareas()            {  return tarea.size();  }
   public int 		 leeNumTipos()             {  return tipo.size();  }
   public int 		 leeNumTransiciones()      {  return transicion.size();  }   
   public String 	 leePC()		   {  return sesion.leePC();  }
   public WFTicket 	 leeTicket(int i)     
   {  
      WFTicket tt = (WFTicket)registro.get(i);  
      return tt;  
   }
   public WFTicket 	leeTicketConCodigo(int j)     
   {  
      for(int i=0;i<registro.size();i++)
      { WFTicket tt = (WFTicket)registro.get(i); if (tt.leeCodTicket()==j) return tt; }
      return null;
   }
   public WFTransicion 	leeTransicion(int i) { return (WFTransicion)transicion.get(i);  }
   public WFTarea 	leeTarea(int i)      { return (WFTarea)tarea.get(i);  }
   public WFTipoTicket 	leeTipoTicket(int i) { return (WFTipoTicket) tipo.get(i);  }
}
//Fin de la clase WFFiltro.java

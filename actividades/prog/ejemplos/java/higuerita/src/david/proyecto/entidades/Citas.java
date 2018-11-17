//Creado por Proyecto TENERIFE
//Autor: David Vargas Ruiz
//Fecha:Tue Feb 07 22:55:25 WET 2006
package david.proyecto.entidades;

import david.proyecto.util.Fechas;
import java.util.Date;
import java.util.StringTokenizer;

public class Citas implements IEntidad, Comparable {
   private final static String SEPARADOR="|";
   private int codCita;
   private int codAgenda;
   private int codPersona;
   private Date fecha;
   private int codCatalogo;
   private boolean realizado;

   public int getCodCita()	{ return codCita;}
   public void setCodCita(int pCodCita)	{ this.codCita = pCodCita;}
   public int getCodAgenda()	{ return codAgenda;}
   public void setCodAgenda(int pCodAgenda)	{ this.codAgenda = pCodAgenda;}
   public int getCodPersona()	{ return codPersona;}
   public void setCodPersona(int pCodPersona)	{ this.codPersona = pCodPersona;}
   public Date getFecha()	{ return fecha;}
   public void setFecha(Date pFecha)	{ this.fecha = pFecha;}
   public int getCodCatalogo()	{ return codCatalogo;}
   public void setCodCatalogo(int pCodCatalogo)	{ this.codCatalogo = pCodCatalogo;}
   public boolean getRealizado()	{ return realizado;}
   public void setRealizado(boolean pRealizado)	{ this.realizado = pRealizado;}

   public String toCadena() {
      StringBuffer sb = new StringBuffer(200);
      sb.append(codCita+SEPARADOR);
      sb.append(codAgenda+SEPARADOR);
      sb.append(codPersona+SEPARADOR);
      sb.append(Fechas.toCadena(fecha)+SEPARADOR);
      sb.append(codCatalogo+SEPARADOR);
      sb.append(realizado);
      return sb.toString();
   }
   public void fromCadena(String pTexto) {
      StringTokenizer st = new StringTokenizer(pTexto,SEPARADOR,false);
      setCodCita(Integer.parseInt(st.nextToken()));
      setCodAgenda(Integer.parseInt(st.nextToken()));
      setCodPersona(Integer.parseInt(st.nextToken()));
      setFecha(Fechas.fromCadena(st.nextToken()));
      setCodCatalogo(Integer.parseInt(st.nextToken()));
      setRealizado(Boolean.parseBoolean(st.nextToken()));
   }

   public boolean equals(IEntidad registro) {
      //No usar el método compareTo en lugar de equals
      //equals detecta igualdad,compareTo es para ordenar
      Citas e = (Citas) registro;
      if (this.toCadena().equals(e.toCadena())) return true;
      return false;
   }

   public int compareTo(Object registro) {
      // -1: Si x <= y
      //  0: Si x == y
      // +1: Si x >= y
      Citas e = (Citas) registro;
      if (this.toCadena().equals(e.toCadena())) return 0;
      return this.toCadena().compareTo(e.toCadena());
   }

   public String getDescripcion() {
      StringBuffer sb = new StringBuffer(200);
      return sb.toString().trim();
   }

   public String getClave() {
      StringBuffer sb = new StringBuffer(200);
      sb.append(codCita+"-");
      return sb.toString().trim();
   }

   public int getNumeroCampos() {
      return 6;
   }

   public Object getCampo(int index) {
      if(index==0) 
         return new Integer(getCodCita());
      if(index==1) 
         return new Integer(getCodAgenda());
      if(index==2) 
         return new Integer(getCodPersona());
      if(index==3) 
         return getFecha();
      if(index==4) 
         return new Integer(getCodCatalogo());
      if(index==5) 
         return new Boolean(getRealizado());
      return null;
   }

   public static String getNombreCampo(int index) {
      if(index==0) 
         return "cod_cita";
      if(index==1) 
         return "cod_agenda";
      if(index==2) 
         return "cod_persona";
      if(index==3) 
         return "fecha";
      if(index==4) 
         return "cod_catalogo";
      if(index==5) 
         return "realizado";
      return null;
   }

}

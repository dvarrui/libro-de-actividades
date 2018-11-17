//Creado por Proyecto TENERIFE
//Autor: David Vargas Ruiz
//Fecha:Tue Feb 07 22:55:25 WET 2006
package david.proyecto.entidades;

import java.util.StringTokenizer;

public class Agendas implements IEntidad, Comparable {
   private final static String SEPARADOR="|";
   private int codAgenda;
   private String desAgenda;

   public int getCodAgenda()	{ return codAgenda;}
   public void setCodAgenda(int pCodAgenda)	{ this.codAgenda = pCodAgenda;}
   public String getDesAgenda()	{ return desAgenda;}
   public void setDesAgenda(String pDesAgenda)	{ this.desAgenda = pDesAgenda;}

   public String toCadena() {
      StringBuffer sb = new StringBuffer(200);
      sb.append(codAgenda+SEPARADOR);
      sb.append(desAgenda.toString());
      return sb.toString();
   }
   public void fromCadena(String pTexto) {
      StringTokenizer st = new StringTokenizer(pTexto,SEPARADOR,false);
      setCodAgenda(Integer.parseInt(st.nextToken()));
      setDesAgenda(st.nextToken());
   }

   public boolean equals(IEntidad registro) {
      //No usar el método compareTo en lugar de equals
      //equals detecta igualdad,compareTo es para ordenar
      Agendas e = (Agendas) registro;
      if (this.toCadena().equals(e.toCadena())) return true;
      return false;
   }

   public int compareTo(Object registro) {
      // -1: Si x <= y
      //  0: Si x == y
      // +1: Si x >= y
      Agendas e = (Agendas) registro;
      if (this.toCadena().equals(e.toCadena())) return 0;
      return this.toCadena().compareTo(e.toCadena());
   }

   public String getDescripcion() {
      StringBuffer sb = new StringBuffer(200);
      sb.append(desAgenda.toString()+" ");
      return sb.toString().trim();
   }

   public String getClave() {
      StringBuffer sb = new StringBuffer(200);
      sb.append(codAgenda+"-");
      return sb.toString().trim();
   }

   public int getNumeroCampos() {
      return 2;
   }

   public Object getCampo(int index) {
      if(index==0) 
         return new Integer(getCodAgenda());
      if(index==1) 
         return getDesAgenda();
      return null;
   }

   public static String getNombreCampo(int index) {
      if(index==0) 
         return "cod_agenda";
      if(index==1) 
         return "des_agenda";
      return null;
   }

}

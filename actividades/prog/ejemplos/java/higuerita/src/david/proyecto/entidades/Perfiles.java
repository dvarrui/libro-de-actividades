//Creado por Proyecto TENERIFE
//Autor: David Vargas Ruiz
//Fecha:Tue Feb 07 22:55:25 WET 2006
package david.proyecto.entidades;

import java.util.StringTokenizer;

public class Perfiles implements IEntidad, Comparable {
   private final static String SEPARADOR="|";
   private int codPerfil;
   private String desPerfil;

   public int getCodPerfil()	{ return codPerfil;}
   public void setCodPerfil(int pCodPerfil)	{ this.codPerfil = pCodPerfil;}
   public String getDesPerfil()	{ return desPerfil;}
   public void setDesPerfil(String pDesPerfil)	{ this.desPerfil = pDesPerfil;}

   public String toCadena() {
      StringBuffer sb = new StringBuffer(200);
      sb.append(codPerfil+SEPARADOR);
      sb.append(desPerfil.toString());
      return sb.toString();
   }
   public void fromCadena(String pTexto) {
      StringTokenizer st = new StringTokenizer(pTexto,SEPARADOR,false);
      setCodPerfil(Integer.parseInt(st.nextToken()));
      setDesPerfil(st.nextToken());
   }

   public boolean equals(IEntidad registro) {
      //No usar el método compareTo en lugar de equals
      //equals detecta igualdad,compareTo es para ordenar
      Perfiles e = (Perfiles) registro;
      if (this.toCadena().equals(e.toCadena())) return true;
      return false;
   }

   public int compareTo(Object registro) {
      // -1: Si x <= y
      //  0: Si x == y
      // +1: Si x >= y
      Perfiles e = (Perfiles) registro;
      if (this.toCadena().equals(e.toCadena())) return 0;
      return this.toCadena().compareTo(e.toCadena());
   }

   public String getDescripcion() {
      StringBuffer sb = new StringBuffer(200);
      sb.append(desPerfil.toString()+" ");
      return sb.toString().trim();
   }

   public String getClave() {
      StringBuffer sb = new StringBuffer(200);
      sb.append(codPerfil+"-");
      return sb.toString().trim();
   }

   public int getNumeroCampos() {
      return 2;
   }

   public Object getCampo(int index) {
      if(index==0) 
         return new Integer(getCodPerfil());
      if(index==1) 
         return getDesPerfil();
      return null;
   }

   public static String getNombreCampo(int index) {
      if(index==0) 
         return "cod_perfil";
      if(index==1) 
         return "des_perfil";
      return null;
   }

}

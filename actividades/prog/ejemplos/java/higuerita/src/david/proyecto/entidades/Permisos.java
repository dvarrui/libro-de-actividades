//Creado por Proyecto TENERIFE
//Autor: David Vargas Ruiz
//Fecha:Tue Feb 07 22:55:25 WET 2006
package david.proyecto.entidades;

import java.util.StringTokenizer;

public class Permisos implements IEntidad, Comparable {
   private final static String SEPARADOR="|";
   private int codPermiso;
   private String desPermiso;

   public int getCodPermiso()	{ return codPermiso;}
   public void setCodPermiso(int pCodPermiso)	{ this.codPermiso = pCodPermiso;}
   public String getDesPermiso()	{ return desPermiso;}
   public void setDesPermiso(String pDesPermiso)	{ this.desPermiso = pDesPermiso;}

   public String toCadena() {
      StringBuffer sb = new StringBuffer(200);
      sb.append(codPermiso+SEPARADOR);
      sb.append(desPermiso.toString());
      return sb.toString();
   }
   public void fromCadena(String pTexto) {
      StringTokenizer st = new StringTokenizer(pTexto,SEPARADOR,false);
      setCodPermiso(Integer.parseInt(st.nextToken()));
      setDesPermiso(st.nextToken());
   }

   public boolean equals(IEntidad registro) {
      //No usar el método compareTo en lugar de equals
      //equals detecta igualdad,compareTo es para ordenar
      Permisos e = (Permisos) registro;
      if (this.toCadena().equals(e.toCadena())) return true;
      return false;
   }

   public int compareTo(Object registro) {
      // -1: Si x <= y
      //  0: Si x == y
      // +1: Si x >= y
      Permisos e = (Permisos) registro;
      if (this.toCadena().equals(e.toCadena())) return 0;
      return this.toCadena().compareTo(e.toCadena());
   }

   public String getDescripcion() {
      StringBuffer sb = new StringBuffer(200);
      sb.append(desPermiso.toString()+" ");
      return sb.toString().trim();
   }

   public String getClave() {
      StringBuffer sb = new StringBuffer(200);
      sb.append(codPermiso+"-");
      return sb.toString().trim();
   }

   public int getNumeroCampos() {
      return 2;
   }

   public Object getCampo(int index) {
      if(index==0) 
         return new Integer(getCodPermiso());
      if(index==1) 
         return getDesPermiso();
      return null;
   }

   public static String getNombreCampo(int index) {
      if(index==0) 
         return "cod_permiso";
      if(index==1) 
         return "des_permiso";
      return null;
   }

}

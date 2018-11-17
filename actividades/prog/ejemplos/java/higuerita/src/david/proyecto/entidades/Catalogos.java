//Creado por Proyecto TENERIFE
//Autor: David Vargas Ruiz
//Fecha:Tue Feb 07 22:55:25 WET 2006
package david.proyecto.entidades;

import java.util.StringTokenizer;

public class Catalogos implements IEntidad, Comparable {
   private final static String SEPARADOR="|";
   private int codCatalogo;
   private String desCatalogo;
   private int codCatalogoPadre;
   private int peso;

   public int getCodCatalogo()	{ return codCatalogo;}
   public void setCodCatalogo(int pCodCatalogo)	{ this.codCatalogo = pCodCatalogo;}
   public String getDesCatalogo()	{ return desCatalogo;}
   public void setDesCatalogo(String pDesCatalogo)	{ this.desCatalogo = pDesCatalogo;}
   public int getCodCatalogoPadre()	{ return codCatalogoPadre;}
   public void setCodCatalogoPadre(int pCodCatalogoPadre)	{ this.codCatalogoPadre = pCodCatalogoPadre;}
   public int getPeso()	{ return peso;}
   public void setPeso(int pPeso)	{ this.peso = pPeso;}

   public String toCadena() {
      StringBuffer sb = new StringBuffer(200);
      sb.append(codCatalogo+SEPARADOR);
      sb.append(desCatalogo.toString()+SEPARADOR);
      sb.append(codCatalogoPadre+SEPARADOR);
      sb.append(peso);
      return sb.toString();
   }
   public void fromCadena(String pTexto) {
      StringTokenizer st = new StringTokenizer(pTexto,SEPARADOR,false);
      setCodCatalogo(Integer.parseInt(st.nextToken()));
      setDesCatalogo(st.nextToken());
      setCodCatalogoPadre(Integer.parseInt(st.nextToken()));
      setPeso(Integer.parseInt(st.nextToken()));
   }

   public boolean equals(IEntidad registro) {
      //No usar el método compareTo en lugar de equals
      //equals detecta igualdad,compareTo es para ordenar
      Catalogos e = (Catalogos) registro;
      if (this.toCadena().equals(e.toCadena())) return true;
      return false;
   }

   public int compareTo(Object registro) {
      // -1: Si x <= y
      //  0: Si x == y
      // +1: Si x >= y
      Catalogos e = (Catalogos) registro;
      if (this.toCadena().equals(e.toCadena())) return 0;
      return this.toCadena().compareTo(e.toCadena());
   }

   public String getDescripcion() {
      StringBuffer sb = new StringBuffer(200);
      sb.append(desCatalogo.toString()+" ");
      return sb.toString().trim();
   }

   public String getClave() {
      StringBuffer sb = new StringBuffer(200);
      sb.append(codCatalogo+"-");
      return sb.toString().trim();
   }

   public int getNumeroCampos() {
      return 4;
   }

   public Object getCampo(int index) {
      if(index==0) 
         return new Integer(getCodCatalogo());
      if(index==1) 
         return getDesCatalogo();
      if(index==2) 
         return new Integer(getCodCatalogoPadre());
      if(index==3) 
         return new Integer(getPeso());
      return null;
   }

   public static String getNombreCampo(int index) {
      if(index==0) 
         return "cod_catalogo";
      if(index==1) 
         return "des_catalogo";
      if(index==2) 
         return "cod_catalogo_padre";
      if(index==3) 
         return "peso";
      return null;
   }

}

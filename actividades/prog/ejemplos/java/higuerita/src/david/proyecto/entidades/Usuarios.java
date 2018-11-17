//Creado por Proyecto TENERIFE
//Autor: David Vargas Ruiz
//Fecha:Tue Feb 07 22:55:25 WET 2006
package david.proyecto.entidades;

import david.proyecto.util.Fechas;
import java.util.Date;
import java.util.StringTokenizer;

public class Usuarios implements IEntidad, Comparable {
   private final static String SEPARADOR="|";
   private int codUsuario;
   private String desUsuario;
   private String password;
   private int codPerfil;
   private Date fecDesde;
   private Date fecHasta;
   private boolean activo;

   public int getCodUsuario()	{ return codUsuario;}
   public void setCodUsuario(int pCodUsuario)	{ this.codUsuario = pCodUsuario;}
   public String getDesUsuario()	{ return desUsuario;}
   public void setDesUsuario(String pDesUsuario)	{ this.desUsuario = pDesUsuario;}
   public String getPassword()	{ return password;}
   public void setPassword(String pPassword)	{ this.password = pPassword;}
   public int getCodPerfil()	{ return codPerfil;}
   public void setCodPerfil(int pCodPerfil)	{ this.codPerfil = pCodPerfil;}
   public Date getFecDesde()	{ return fecDesde;}
   public void setFecDesde(Date pFecDesde)	{ this.fecDesde = pFecDesde;}
   public Date getFecHasta()	{ return fecHasta;}
   public void setFecHasta(Date pFecHasta)	{ this.fecHasta = pFecHasta;}
   public boolean getActivo()	{ return activo;}
   public void setActivo(boolean pActivo)	{ this.activo = pActivo;}

   public String toCadena() {
      StringBuffer sb = new StringBuffer(200);
      sb.append(codUsuario+SEPARADOR);
      sb.append(desUsuario.toString()+SEPARADOR);
      sb.append(password.toString()+SEPARADOR);
      sb.append(codPerfil+SEPARADOR);
      sb.append(Fechas.toCadena(fecDesde)+SEPARADOR);
      sb.append(Fechas.toCadena(fecHasta)+SEPARADOR);
      sb.append(activo);
      return sb.toString();
   }
   public void fromCadena(String pTexto) {
      StringTokenizer st = new StringTokenizer(pTexto,SEPARADOR,false);
      setCodUsuario(Integer.parseInt(st.nextToken()));
      setDesUsuario(st.nextToken());
      setPassword(st.nextToken());
      setCodPerfil(Integer.parseInt(st.nextToken()));
      setFecDesde(Fechas.fromCadena(st.nextToken()));
      setFecHasta(Fechas.fromCadena(st.nextToken()));
      setActivo(Boolean.parseBoolean(st.nextToken()));
   }

   public boolean equals(IEntidad registro) {
      //No usar el método compareTo en lugar de equals
      //equals detecta igualdad,compareTo es para ordenar
      Usuarios e = (Usuarios) registro;
      if (this.toCadena().equals(e.toCadena())) return true;
      return false;
   }

   public int compareTo(Object registro) {
      // -1: Si x <= y
      //  0: Si x == y
      // +1: Si x >= y
      Usuarios e = (Usuarios) registro;
      if (this.toCadena().equals(e.toCadena())) return 0;
      return this.toCadena().compareTo(e.toCadena());
   }

   public String getDescripcion() {
      StringBuffer sb = new StringBuffer(200);
      sb.append(desUsuario.toString()+" ");
      return sb.toString().trim();
   }

   public String getClave() {
      StringBuffer sb = new StringBuffer(200);
      sb.append(codUsuario+"-");
      return sb.toString().trim();
   }

   public int getNumeroCampos() {
      return 7;
   }

   public Object getCampo(int index) {
      if(index==0) 
         return new Integer(getCodUsuario());
      if(index==1) 
         return getDesUsuario();
      if(index==2) 
         return getPassword();
      if(index==3) 
         return new Integer(getCodPerfil());
      if(index==4) 
         return getFecDesde();
      if(index==5) 
         return getFecHasta();
      if(index==6) 
         return new Boolean(getActivo());
      return null;
   }

   public static String getNombreCampo(int index) {
      if(index==0) 
         return "cod_usuario";
      if(index==1) 
         return "des_usuario";
      if(index==2) 
         return "password";
      if(index==3) 
         return "cod_perfil";
      if(index==4) 
         return "fec_desde";
      if(index==5) 
         return "fec_hasta";
      if(index==6) 
         return "activo";
      return null;
   }

}

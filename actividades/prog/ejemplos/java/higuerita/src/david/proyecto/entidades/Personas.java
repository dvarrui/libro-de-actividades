//Creado por Proyecto TENERIFE
//Autor: David Vargas Ruiz
//Fecha:Tue Feb 07 22:55:25 WET 2006
package david.proyecto.entidades;

import david.proyecto.util.Fechas;
import java.util.Date;
import java.util.StringTokenizer;

public class Personas implements IEntidad, Comparable {
   private final static String SEPARADOR="|";
   private int codPersona;
   private String nombre;
   private String apellido1;
   private String apellido2;
   private Date fechaNacimiento;
   private String dni;
   private String telefono1;
   private String telefono2;
   private String direccion;

   public int getCodPersona()	{ return codPersona;}
   public void setCodPersona(int pCodPersona)	{ this.codPersona = pCodPersona;}
   public String getNombre()	{ return nombre;}
   public void setNombre(String pNombre)	{ this.nombre = pNombre;}
   public String getApellido1()	{ return apellido1;}
   public void setApellido1(String pApellido1)	{ this.apellido1 = pApellido1;}
   public String getApellido2()	{ return apellido2;}
   public void setApellido2(String pApellido2)	{ this.apellido2 = pApellido2;}
   public Date getFechaNacimiento()	{ return fechaNacimiento;}
   public void setFechaNacimiento(Date pFechaNacimiento)	{ this.fechaNacimiento = pFechaNacimiento;}
   public String getDni()	{ return dni;}
   public void setDni(String pDni)	{ this.dni = pDni;}
   public String getTelefono1()	{ return telefono1;}
   public void setTelefono1(String pTelefono1)	{ this.telefono1 = pTelefono1;}
   public String getTelefono2()	{ return telefono2;}
   public void setTelefono2(String pTelefono2)	{ this.telefono2 = pTelefono2;}
   public String getDireccion()	{ return direccion;}
   public void setDireccion(String pDireccion)	{ this.direccion = pDireccion;}

   public String toCadena() {
      StringBuffer sb = new StringBuffer(200);
      sb.append(codPersona+SEPARADOR);
      sb.append(nombre.toString()+SEPARADOR);
      sb.append(apellido1.toString()+SEPARADOR);
      sb.append(apellido2.toString()+SEPARADOR);
      sb.append(Fechas.toCadena(fechaNacimiento)+SEPARADOR);
      sb.append(dni.toString()+SEPARADOR);
      sb.append(telefono1.toString()+SEPARADOR);
      sb.append(telefono2.toString()+SEPARADOR);
      sb.append(direccion.toString());
      return sb.toString();
   }
   public void fromCadena(String pTexto) {
      StringTokenizer st = new StringTokenizer(pTexto,SEPARADOR,false);
      setCodPersona(Integer.parseInt(st.nextToken()));
      setNombre(st.nextToken());
      setApellido1(st.nextToken());
      setApellido2(st.nextToken());
      setFechaNacimiento(Fechas.fromCadena(st.nextToken()));
      setDni(st.nextToken());
      setTelefono1(st.nextToken());
      setTelefono2(st.nextToken());
      setDireccion(st.nextToken());
   }

   public boolean equals(IEntidad registro) {
      //No usar el método compareTo en lugar de equals
      //equals detecta igualdad,compareTo es para ordenar
      Personas e = (Personas) registro;
      if (this.toCadena().equals(e.toCadena())) return true;
      return false;
   }

   public int compareTo(Object registro) {
      // -1: Si x <= y
      //  0: Si x == y
      // +1: Si x >= y
      Personas e = (Personas) registro;
      if (this.toCadena().equals(e.toCadena())) return 0;
      return this.toCadena().compareTo(e.toCadena());
   }

   public String getDescripcion() {
      StringBuffer sb = new StringBuffer(200);
      sb.append(nombre.toString()+" ");
      sb.append(apellido1.toString()+" ");
      sb.append(apellido2.toString()+" ");
      return sb.toString().trim();
   }

   public String getClave() {
      StringBuffer sb = new StringBuffer(200);
      sb.append(codPersona+"-");
      return sb.toString().trim();
   }

   public int getNumeroCampos() {
      return 9;
   }

   public Object getCampo(int index) {
      if(index==0) 
         return new Integer(getCodPersona());
      if(index==1) 
         return getNombre();
      if(index==2) 
         return getApellido1();
      if(index==3) 
         return getApellido2();
      if(index==4) 
         return getFechaNacimiento();
      if(index==5) 
         return getDni();
      if(index==6) 
         return getTelefono1();
      if(index==7) 
         return getTelefono2();
      if(index==8) 
         return getDireccion();
      return null;
   }

   public static String getNombreCampo(int index) {
      if(index==0) 
         return "cod_persona";
      if(index==1) 
         return "nombre";
      if(index==2) 
         return "apellido1";
      if(index==3) 
         return "apellido2";
      if(index==4) 
         return "fecha_nacimiento";
      if(index==5) 
         return "dni";
      if(index==6) 
         return "telefono1";
      if(index==7) 
         return "telefono2";
      if(index==8) 
         return "direccion";
      return null;
   }

}

//Creado por Proyecto TENERIFE
//Autor: David Vargas Ruiz
//Fecha:Tue Feb 07 22:55:25 WET 2006
package david.proyecto.almacenes.sql;

import david.proyecto.ejb.conexiones.Conexion;
import david.proyecto.entidades.IEntidad;
import david.proyecto.entidades.Personas;
import david.proyecto.almacenes.IAlmacen;
import java.util.ArrayList;
import java.sql.*;

public class AlmacenSQLPersonas implements IAlmacen {
   public static final String NOMBRETABLA="personas";
   private Conexion conexion;

   public void setConexion(Conexion pConexion) {
      conexion=pConexion;
   }

   public boolean open() {
      return true;
   }

   public boolean close() {
      return true;
   }

   public ArrayList getAll() {
      Statement st=conexion.getStatement();
      ResultSet rs;
      ArrayList lista=new ArrayList();
      Personas reg;

      try {
         rs = st.executeQuery("SELECT * FROM "+NOMBRETABLA);
         while(rs.next()) {
            reg = new Personas();
            reg.setCodPersona(rs.getInt(0));
            reg.setNombre(rs.getString(1));
            reg.setApellido1(rs.getString(2));
            reg.setApellido2(rs.getString(3));
            reg.setFechaNacimiento(rs.getDate(4));
            reg.setDni(rs.getString(5));
            reg.setTelefono1(rs.getString(6));
            reg.setTelefono2(rs.getString(7));
            reg.setDireccion(rs.getString(8));
            lista.add(reg);
         }
      }  catch(Exception e) {
         System.err.println("Error:"+e);
      }
      return lista;
   }

   public IEntidad getById(int id) {
      Statement st=conexion.getStatement();
      ResultSet rs;
      Personas reg = new Personas();
      try {
         rs = st.executeQuery("SELECT * FROM "+NOMBRETABLA+" WHERE cod_persona = "+id+";");         while(rs.next()) {
         reg.setCodPersona (rs.getInt(0));
         reg.setNombre (rs.getString(1));
         reg.setApellido1 (rs.getString(2));
         reg.setApellido2 (rs.getString(3));
         reg.setFechaNacimiento (rs.getDate(4));
         reg.setDni (rs.getString(5));
         reg.setTelefono1 (rs.getString(6));
         reg.setTelefono2 (rs.getString(7));
         reg.setDireccion (rs.getString(8));
         }
      }  catch(Exception e) {
         System.err.println("Error:"+e);
      }
      return (IEntidad) reg;
   }

   public ArrayList getFind(IEntidad desde, IEntidad hasta) {
      Statement st=conexion.getStatement();
      ResultSet rs;
      ArrayList lista=new ArrayList();
      Personas reg;

      try {
         rs = st.executeQuery("SELECT * FROM "+NOMBRETABLA);
         while(rs.next()) {
            reg = new Personas();
            reg.setCodPersona(rs.getInt(0));
            reg.setNombre(rs.getString(1));
            reg.setApellido1(rs.getString(2));
            reg.setApellido2(rs.getString(3));
            reg.setFechaNacimiento(rs.getDate(4));
            reg.setDni(rs.getString(5));
            reg.setTelefono1(rs.getString(6));
            reg.setTelefono2(rs.getString(7));
            reg.setDireccion(rs.getString(8));
            lista.add(reg);
         }
      }  catch(Exception e) {
         System.err.println("Error:"+e);
      }
      return lista;
   }

   public boolean add(IEntidad pRegistro) {
      Personas registro = (Personas) pRegistro;
      Statement st=conexion.getStatement();
      try {
         st.executeUpdate("INSERT INTO "+NOMBRETABLA+
         " (cod_persona,nombre,apellido1,apellido2,fecha_nacimiento,dni,telefono1,telefono2,direccion) "+
         "VALUES ('"+registro.getCodPersona()+"'"
         +",'"+registro.getNombre()+"'"
         +",'"+registro.getApellido1()+"'"
         +",'"+registro.getApellido2()+"'"
         +",'"+registro.getFechaNacimiento()+"'"
         +",'"+registro.getDni()+"'"
         +",'"+registro.getTelefono1()+"'"
         +",'"+registro.getTelefono2()+"'"
         +",'"+registro.getDireccion()+"'"
            +");");
         st.close();
      } catch(Exception e) {
         System.err.println("Error:"+e);
      }
      return true;
   }

   public boolean delete(IEntidad pRegistro) {
      Personas registro = (Personas) pRegistro;
      Statement st=conexion.getStatement();
      try {
         st.executeUpdate("DELETE "+NOMBRETABLA+" WHERE "+
         "cod_persona='"+registro.getCodPersona()+"'"
         +" AND "+"nombre='"+registro.getNombre()+"'"
         +" AND "+"apellido1='"+registro.getApellido1()+"'"
         +" AND "+"apellido2='"+registro.getApellido2()+"'"
         +" AND "+"fecha_nacimiento='"+registro.getFechaNacimiento()+"'"
         +" AND "+"dni='"+registro.getDni()+"'"
         +" AND "+"telefono1='"+registro.getTelefono1()+"'"
         +" AND "+"telefono2='"+registro.getTelefono2()+"'"
         +" AND "+"direccion='"+registro.getDireccion()+"'"
            +";");
         st.close();
      }  catch(Exception e) {
         System.err.println("Error:"+e);
      }
      return true;
   }

}


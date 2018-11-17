//Creado por Proyecto TENERIFE
//Autor: David Vargas Ruiz
//Fecha:Tue Feb 07 22:55:25 WET 2006
package david.proyecto.almacenes.sql;

import david.proyecto.ejb.conexiones.Conexion;
import david.proyecto.entidades.IEntidad;
import david.proyecto.entidades.Perfiles;
import david.proyecto.almacenes.IAlmacen;
import java.util.ArrayList;
import java.sql.*;

public class AlmacenSQLPerfiles implements IAlmacen {
   public static final String NOMBRETABLA="perfiles";
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
      Perfiles reg;

      try {
         rs = st.executeQuery("SELECT * FROM "+NOMBRETABLA);
         while(rs.next()) {
            reg = new Perfiles();
            reg.setCodPerfil(rs.getInt(0));
            reg.setDesPerfil(rs.getString(1));
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
      Perfiles reg = new Perfiles();
      try {
         rs = st.executeQuery("SELECT * FROM "+NOMBRETABLA+" WHERE cod_perfil = "+id+";");         while(rs.next()) {
         reg.setCodPerfil (rs.getInt(0));
         reg.setDesPerfil (rs.getString(1));
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
      Perfiles reg;

      try {
         rs = st.executeQuery("SELECT * FROM "+NOMBRETABLA);
         while(rs.next()) {
            reg = new Perfiles();
            reg.setCodPerfil(rs.getInt(0));
            reg.setDesPerfil(rs.getString(1));
            lista.add(reg);
         }
      }  catch(Exception e) {
         System.err.println("Error:"+e);
      }
      return lista;
   }

   public boolean add(IEntidad pRegistro) {
      Perfiles registro = (Perfiles) pRegistro;
      Statement st=conexion.getStatement();
      try {
         st.executeUpdate("INSERT INTO "+NOMBRETABLA+
         " (cod_perfil,des_perfil) "+
         "VALUES ('"+registro.getCodPerfil()+"'"
         +",'"+registro.getDesPerfil()+"'"
            +");");
         st.close();
      } catch(Exception e) {
         System.err.println("Error:"+e);
      }
      return true;
   }

   public boolean delete(IEntidad pRegistro) {
      Perfiles registro = (Perfiles) pRegistro;
      Statement st=conexion.getStatement();
      try {
         st.executeUpdate("DELETE "+NOMBRETABLA+" WHERE "+
         "cod_perfil='"+registro.getCodPerfil()+"'"
         +" AND "+"des_perfil='"+registro.getDesPerfil()+"'"
            +";");
         st.close();
      }  catch(Exception e) {
         System.err.println("Error:"+e);
      }
      return true;
   }

}


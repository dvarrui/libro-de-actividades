//Creado por Proyecto TENERIFE
//Autor: David Vargas Ruiz
//Fecha:Tue Feb 07 22:55:25 WET 2006
package david.proyecto.almacenes.sql;

import david.proyecto.ejb.conexiones.Conexion;
import david.proyecto.entidades.IEntidad;
import david.proyecto.entidades.Usuarios;
import david.proyecto.almacenes.IAlmacen;
import java.util.ArrayList;
import java.sql.*;

public class AlmacenSQLUsuarios implements IAlmacen {
   public static final String NOMBRETABLA="usuarios";
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
      Usuarios reg;

      try {
         rs = st.executeQuery("SELECT * FROM "+NOMBRETABLA);
         while(rs.next()) {
            reg = new Usuarios();
            reg.setCodUsuario(rs.getInt(0));
            reg.setDesUsuario(rs.getString(1));
            reg.setPassword(rs.getString(2));
            reg.setCodPerfil(rs.getInt(3));
            reg.setFecDesde(rs.getDate(4));
            reg.setFecHasta(rs.getDate(5));
            reg.setActivo(rs.getBoolean(6));
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
      Usuarios reg = new Usuarios();
      try {
         rs = st.executeQuery("SELECT * FROM "+NOMBRETABLA+" WHERE cod_usuario = "+id+";");         while(rs.next()) {
         reg.setCodUsuario (rs.getInt(0));
         reg.setDesUsuario (rs.getString(1));
         reg.setPassword (rs.getString(2));
         reg.setCodPerfil (rs.getInt(3));
         reg.setFecDesde (rs.getDate(4));
         reg.setFecHasta (rs.getDate(5));
         reg.setActivo (rs.getBoolean(6));
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
      Usuarios reg;

      try {
         rs = st.executeQuery("SELECT * FROM "+NOMBRETABLA);
         while(rs.next()) {
            reg = new Usuarios();
            reg.setCodUsuario(rs.getInt(0));
            reg.setDesUsuario(rs.getString(1));
            reg.setPassword(rs.getString(2));
            reg.setCodPerfil(rs.getInt(3));
            reg.setFecDesde(rs.getDate(4));
            reg.setFecHasta(rs.getDate(5));
            reg.setActivo(rs.getBoolean(6));
            lista.add(reg);
         }
      }  catch(Exception e) {
         System.err.println("Error:"+e);
      }
      return lista;
   }

   public boolean add(IEntidad pRegistro) {
      Usuarios registro = (Usuarios) pRegistro;
      Statement st=conexion.getStatement();
      try {
         st.executeUpdate("INSERT INTO "+NOMBRETABLA+
         " (cod_usuario,des_usuario,password,cod_perfil,fec_desde,fec_hasta,activo) "+
         "VALUES ('"+registro.getCodUsuario()+"'"
         +",'"+registro.getDesUsuario()+"'"
         +",'"+registro.getPassword()+"'"
         +",'"+registro.getCodPerfil()+"'"
         +",'"+registro.getFecDesde()+"'"
         +",'"+registro.getFecHasta()+"'"
         +",'"+registro.getActivo()+"'"
            +");");
         st.close();
      } catch(Exception e) {
         System.err.println("Error:"+e);
      }
      return true;
   }

   public boolean delete(IEntidad pRegistro) {
      Usuarios registro = (Usuarios) pRegistro;
      Statement st=conexion.getStatement();
      try {
         st.executeUpdate("DELETE "+NOMBRETABLA+" WHERE "+
         "cod_usuario='"+registro.getCodUsuario()+"'"
         +" AND "+"des_usuario='"+registro.getDesUsuario()+"'"
         +" AND "+"password='"+registro.getPassword()+"'"
         +" AND "+"cod_perfil='"+registro.getCodPerfil()+"'"
         +" AND "+"fec_desde='"+registro.getFecDesde()+"'"
         +" AND "+"fec_hasta='"+registro.getFecHasta()+"'"
         +" AND "+"activo='"+registro.getActivo()+"'"
            +";");
         st.close();
      }  catch(Exception e) {
         System.err.println("Error:"+e);
      }
      return true;
   }

}


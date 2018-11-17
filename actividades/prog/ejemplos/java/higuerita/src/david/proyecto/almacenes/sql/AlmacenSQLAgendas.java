//Creado por Proyecto TENERIFE
//Autor: David Vargas Ruiz
//Fecha:Tue Feb 07 22:55:25 WET 2006
package david.proyecto.almacenes.sql;

import david.proyecto.ejb.conexiones.Conexion;
import david.proyecto.entidades.IEntidad;
import david.proyecto.entidades.Agendas;
import david.proyecto.almacenes.IAlmacen;
import java.util.ArrayList;
import java.sql.*;

public class AlmacenSQLAgendas implements IAlmacen {
   public static final String NOMBRETABLA="agendas";
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
      Agendas reg;

      try {
         rs = st.executeQuery("SELECT * FROM "+NOMBRETABLA);
         while(rs.next()) {
            reg = new Agendas();
            reg.setCodAgenda(rs.getInt(0));
            reg.setDesAgenda(rs.getString(1));
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
      Agendas reg = new Agendas();
      try {
         rs = st.executeQuery("SELECT * FROM "+NOMBRETABLA+" WHERE cod_agenda = "+id+";");         while(rs.next()) {
         reg.setCodAgenda (rs.getInt(0));
         reg.setDesAgenda (rs.getString(1));
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
      Agendas reg;

      try {
         rs = st.executeQuery("SELECT * FROM "+NOMBRETABLA);
         while(rs.next()) {
            reg = new Agendas();
            reg.setCodAgenda(rs.getInt(0));
            reg.setDesAgenda(rs.getString(1));
            lista.add(reg);
         }
      }  catch(Exception e) {
         System.err.println("Error:"+e);
      }
      return lista;
   }

   public boolean add(IEntidad pRegistro) {
      Agendas registro = (Agendas) pRegistro;
      Statement st=conexion.getStatement();
      try {
         st.executeUpdate("INSERT INTO "+NOMBRETABLA+
         " (cod_agenda,des_agenda) "+
         "VALUES ('"+registro.getCodAgenda()+"'"
         +",'"+registro.getDesAgenda()+"'"
            +");");
         st.close();
      } catch(Exception e) {
         System.err.println("Error:"+e);
      }
      return true;
   }

   public boolean delete(IEntidad pRegistro) {
      Agendas registro = (Agendas) pRegistro;
      Statement st=conexion.getStatement();
      try {
         st.executeUpdate("DELETE "+NOMBRETABLA+" WHERE "+
         "cod_agenda='"+registro.getCodAgenda()+"'"
         +" AND "+"des_agenda='"+registro.getDesAgenda()+"'"
            +";");
         st.close();
      }  catch(Exception e) {
         System.err.println("Error:"+e);
      }
      return true;
   }

}


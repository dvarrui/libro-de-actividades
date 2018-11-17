//Creado por Proyecto TENERIFE
//Autor: David Vargas Ruiz
//Fecha:Tue Feb 07 22:55:25 WET 2006
package david.proyecto.almacenes.sql;

import david.proyecto.ejb.conexiones.Conexion;
import david.proyecto.entidades.IEntidad;
import david.proyecto.entidades.Citas;
import david.proyecto.almacenes.IAlmacen;
import java.util.ArrayList;
import java.sql.*;

public class AlmacenSQLCitas implements IAlmacen {
   public static final String NOMBRETABLA="citas";
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
      Citas reg;

      try {
         rs = st.executeQuery("SELECT * FROM "+NOMBRETABLA);
         while(rs.next()) {
            reg = new Citas();
            reg.setCodCita(rs.getInt(0));
            reg.setCodAgenda(rs.getInt(1));
            reg.setCodPersona(rs.getInt(2));
            reg.setFecha(rs.getDate(3));
            reg.setCodCatalogo(rs.getInt(4));
            reg.setRealizado(rs.getBoolean(5));
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
      Citas reg = new Citas();
      try {
         rs = st.executeQuery("SELECT * FROM "+NOMBRETABLA+" WHERE cod_cita = "+id+";");         while(rs.next()) {
         reg.setCodCita (rs.getInt(0));
         reg.setCodAgenda (rs.getInt(1));
         reg.setCodPersona (rs.getInt(2));
         reg.setFecha (rs.getDate(3));
         reg.setCodCatalogo (rs.getInt(4));
         reg.setRealizado (rs.getBoolean(5));
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
      Citas reg;

      try {
         rs = st.executeQuery("SELECT * FROM "+NOMBRETABLA);
         while(rs.next()) {
            reg = new Citas();
            reg.setCodCita(rs.getInt(0));
            reg.setCodAgenda(rs.getInt(1));
            reg.setCodPersona(rs.getInt(2));
            reg.setFecha(rs.getDate(3));
            reg.setCodCatalogo(rs.getInt(4));
            reg.setRealizado(rs.getBoolean(5));
            lista.add(reg);
         }
      }  catch(Exception e) {
         System.err.println("Error:"+e);
      }
      return lista;
   }

   public boolean add(IEntidad pRegistro) {
      Citas registro = (Citas) pRegistro;
      Statement st=conexion.getStatement();
      try {
         st.executeUpdate("INSERT INTO "+NOMBRETABLA+
         " (cod_cita,cod_agenda,cod_persona,fecha,cod_catalogo,realizado) "+
         "VALUES ('"+registro.getCodCita()+"'"
         +",'"+registro.getCodAgenda()+"'"
         +",'"+registro.getCodPersona()+"'"
         +",'"+registro.getFecha()+"'"
         +",'"+registro.getCodCatalogo()+"'"
         +",'"+registro.getRealizado()+"'"
            +");");
         st.close();
      } catch(Exception e) {
         System.err.println("Error:"+e);
      }
      return true;
   }

   public boolean delete(IEntidad pRegistro) {
      Citas registro = (Citas) pRegistro;
      Statement st=conexion.getStatement();
      try {
         st.executeUpdate("DELETE "+NOMBRETABLA+" WHERE "+
         "cod_cita='"+registro.getCodCita()+"'"
         +" AND "+"cod_agenda='"+registro.getCodAgenda()+"'"
         +" AND "+"cod_persona='"+registro.getCodPersona()+"'"
         +" AND "+"fecha='"+registro.getFecha()+"'"
         +" AND "+"cod_catalogo='"+registro.getCodCatalogo()+"'"
         +" AND "+"realizado='"+registro.getRealizado()+"'"
            +";");
         st.close();
      }  catch(Exception e) {
         System.err.println("Error:"+e);
      }
      return true;
   }

}


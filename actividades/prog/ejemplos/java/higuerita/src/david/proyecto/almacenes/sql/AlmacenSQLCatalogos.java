//Creado por Proyecto TENERIFE
//Autor: David Vargas Ruiz
//Fecha:Tue Feb 07 22:55:25 WET 2006
package david.proyecto.almacenes.sql;

import david.proyecto.ejb.conexiones.Conexion;
import david.proyecto.entidades.IEntidad;
import david.proyecto.entidades.Catalogos;
import david.proyecto.almacenes.IAlmacen;
import java.util.ArrayList;
import java.sql.*;

public class AlmacenSQLCatalogos implements IAlmacen {
   public static final String NOMBRETABLA="catalogos";
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
      Catalogos reg;

      try {
         rs = st.executeQuery("SELECT * FROM "+NOMBRETABLA);
         while(rs.next()) {
            reg = new Catalogos();
            reg.setCodCatalogo(rs.getInt(0));
            reg.setDesCatalogo(rs.getString(1));
            reg.setCodCatalogoPadre(rs.getInt(2));
            reg.setPeso(rs.getInt(3));
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
      Catalogos reg = new Catalogos();
      try {
         rs = st.executeQuery("SELECT * FROM "+NOMBRETABLA+" WHERE cod_catalogo = "+id+";");         while(rs.next()) {
         reg.setCodCatalogo (rs.getInt(0));
         reg.setDesCatalogo (rs.getString(1));
         reg.setCodCatalogoPadre (rs.getInt(2));
         reg.setPeso (rs.getInt(3));
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
      Catalogos reg;

      try {
         rs = st.executeQuery("SELECT * FROM "+NOMBRETABLA);
         while(rs.next()) {
            reg = new Catalogos();
            reg.setCodCatalogo(rs.getInt(0));
            reg.setDesCatalogo(rs.getString(1));
            reg.setCodCatalogoPadre(rs.getInt(2));
            reg.setPeso(rs.getInt(3));
            lista.add(reg);
         }
      }  catch(Exception e) {
         System.err.println("Error:"+e);
      }
      return lista;
   }

   public boolean add(IEntidad pRegistro) {
      Catalogos registro = (Catalogos) pRegistro;
      Statement st=conexion.getStatement();
      try {
         st.executeUpdate("INSERT INTO "+NOMBRETABLA+
         " (cod_catalogo,des_catalogo,cod_catalogo_padre,peso) "+
         "VALUES ('"+registro.getCodCatalogo()+"'"
         +",'"+registro.getDesCatalogo()+"'"
         +",'"+registro.getCodCatalogoPadre()+"'"
         +",'"+registro.getPeso()+"'"
            +");");
         st.close();
      } catch(Exception e) {
         System.err.println("Error:"+e);
      }
      return true;
   }

   public boolean delete(IEntidad pRegistro) {
      Catalogos registro = (Catalogos) pRegistro;
      Statement st=conexion.getStatement();
      try {
         st.executeUpdate("DELETE "+NOMBRETABLA+" WHERE "+
         "cod_catalogo='"+registro.getCodCatalogo()+"'"
         +" AND "+"des_catalogo='"+registro.getDesCatalogo()+"'"
         +" AND "+"cod_catalogo_padre='"+registro.getCodCatalogoPadre()+"'"
         +" AND "+"peso='"+registro.getPeso()+"'"
            +";");
         st.close();
      }  catch(Exception e) {
         System.err.println("Error:"+e);
      }
      return true;
   }

}


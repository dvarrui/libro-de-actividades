//Creado por Proyecto TENERIFE
//Autor David Vargas Ruiz
//Fecha:Tue Feb 07 22:55:25 WET 2006
package david.proyecto.almacenes;

import david.proyecto.almacenes.sql.*;
import david.proyecto.almacenes.txt.*;
import david.proyecto.almacenes.IAlmacen;

public class FabricaAlmacenes {
   public static final String PERSONAS="personas";
   public static final String AGENDAS="agendas";
   public static final String CATALOGOS="catalogos";
   public static final String CITAS="citas";
   public static final String USUARIOS="usuarios";
   public static final String PERFILES="perfiles";
   public static final String PERMISOS="permisos";
   public static final String TIPO_TXT="TXT";
   public static final String TIPO_SQL="SQL";

   public static IAlmacen createAlmacen(String pTabla, String pTipo) {
      if (pTabla.equals(FabricaAlmacenes.PERSONAS)) {
         if (pTipo.equals(FabricaAlmacenes.TIPO_TXT))
            return new AlmacenTXTPersonas();
         if (pTipo.equals(FabricaAlmacenes.TIPO_SQL))
            return new AlmacenSQLPersonas();
         return null;
      }
      if (pTabla.equals(FabricaAlmacenes.AGENDAS)) {
         if (pTipo.equals(FabricaAlmacenes.TIPO_TXT))
            return new AlmacenTXTAgendas();
         if (pTipo.equals(FabricaAlmacenes.TIPO_SQL))
            return new AlmacenSQLAgendas();
         return null;
      }
      if (pTabla.equals(FabricaAlmacenes.CATALOGOS)) {
         if (pTipo.equals(FabricaAlmacenes.TIPO_TXT))
            return new AlmacenTXTCatalogos();
         if (pTipo.equals(FabricaAlmacenes.TIPO_SQL))
            return new AlmacenSQLCatalogos();
         return null;
      }
      if (pTabla.equals(FabricaAlmacenes.CITAS)) {
         if (pTipo.equals(FabricaAlmacenes.TIPO_TXT))
            return new AlmacenTXTCitas();
         if (pTipo.equals(FabricaAlmacenes.TIPO_SQL))
            return new AlmacenSQLCitas();
         return null;
      }
      if (pTabla.equals(FabricaAlmacenes.USUARIOS)) {
         if (pTipo.equals(FabricaAlmacenes.TIPO_TXT))
            return new AlmacenTXTUsuarios();
         if (pTipo.equals(FabricaAlmacenes.TIPO_SQL))
            return new AlmacenSQLUsuarios();
         return null;
      }
      if (pTabla.equals(FabricaAlmacenes.PERFILES)) {
         if (pTipo.equals(FabricaAlmacenes.TIPO_TXT))
            return new AlmacenTXTPerfiles();
         if (pTipo.equals(FabricaAlmacenes.TIPO_SQL))
            return new AlmacenSQLPerfiles();
         return null;
      }
      if (pTabla.equals(FabricaAlmacenes.PERMISOS)) {
         if (pTipo.equals(FabricaAlmacenes.TIPO_TXT))
            return new AlmacenTXTPermisos();
         if (pTipo.equals(FabricaAlmacenes.TIPO_SQL))
            return new AlmacenSQLPermisos();
         return null;
      }
      return null;
   }

}


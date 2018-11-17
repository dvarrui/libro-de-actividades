//Creado por Proyecto TENERIFE
//Autor David Vargas Ruiz
//Fecha:Tue Nov 22 20:13:40 WET 2005
package david.tenerife.ejb.sesiones;


public class FabricaSesiones {
   public static final String PRUEBA="prueba";
   public static final String TXT="txt";
   public static final String SQL="sql";
   
   public ISesion createSesion(String pTipo) {
      if (pTipo.equals(FabricaSesiones.PRUEBA)) {
         return new SesionPrueba();
      }
      if (pTipo.equals(FabricaSesiones.TXT)) {
         return new SesionTXT();
      }
      if (pTipo.equals(FabricaSesiones.SQL)) {
          return new SesionSQL();
       }
      return null;
   }

}


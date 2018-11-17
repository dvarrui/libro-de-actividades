
// Creado por el proyecto TENERIFE
// Autor: David Vargas Ruiz
// Fecha: Tue Feb 07 22:55:25 WET 2006

package david.proyecto.almacenes.txt;

// import
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

import david.proyecto.entidades.IEntidad;
import david.proyecto.entidades.Perfiles;
import david.proyecto.almacenes.IAlmacen;

// Comienzo de la clase
public class AlmacenTXTPerfiles implements IAlmacen {
   public static final String NOMBRETABLA="perfiles";
   
   private ArrayList datos;
   private boolean opened=false;

   // --------------------------------------------------
   // Constructor: No debe haber para EJB en páginas JSP

   // ----
   // open
   // ----
   public boolean open() {
      if (opened) return true;

      //Declaración de variables
      BufferedReader entrada;
      String str;
      Perfiles reg;
      datos = new ArrayList();
      
      try {
         //Abrir fichero;
         entrada = new BufferedReader(new FileReader("datos/"+NOMBRETABLA+".txt"));
         //Cargar datos en ArrayList;
         str = entrada.readLine();
         while(str!=null) {
            reg = new Perfiles();
			reg.fromCadena(str);
			datos.add(reg);
			str = entrada.readLine();
         }
         entrada.close();
         opened = true;
         return true;
      } catch(Exception e) {
         System.err.println("Error:"+e);
      }
      return false;
   }

   // -----
   // close
   // -----
   public boolean close() {
      if (!opened) return true;
      
      //Declaración de variables
      PrintWriter salida;
      Perfiles reg;
      
      try {
         //Abrir fichero
         salida = new PrintWriter("datos/"+NOMBRETABLA+".txt");
         //Escribir desde el ArrayList
         for(int i=0;i<datos.size();i++) {
            reg = (Perfiles) datos.get(i);
            salida.println(reg.toCadena());
         }
         salida.close();
         opened = false;
         return true;
      } catch(Exception e) {
         System.err.println("Error:"+e);
      }
      return false;
   }
   
   // -------------
   // Método getAll
   // -------------
   public ArrayList getAll() {
      return datos;
   }
   
   // ---------------
   // Método getById
   // ---------------
   public IEntidad getById(int id) {
      Perfiles reg = new Perfiles();
      return (IEntidad) reg;
   }
   
   // ---------------
   // Método getFind
   // ---------------
   public ArrayList getFind(IEntidad desde, IEntidad hasta) {
      ArrayList lista=new ArrayList();
      //Perfiles reg;
      //reg = new Perfiles();
      return lista;
   }

   // ----------
   // Método add
   // ----------
   public boolean add(IEntidad pRegistro) {
      Perfiles registro = (Perfiles) pRegistro;
      datos.add(registro);
      return true;
   }
   
   // -------------
   // Método delete
   // -------------
   public boolean delete(IEntidad pRegistro) {
      Perfiles registro = (Perfiles) pRegistro;
      Perfiles r;
      
      //Buscar elemento y remove();
      for(int i=0;i<datos.size();i++) {
         r = (Perfiles) datos.get(i);
         if (r.equals(registro)) datos.remove(i);
      }
      
      return true;
   }
}

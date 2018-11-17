import totem.lib.*;
import totem.bd.*;

import java.io.*;
import java.util.*;
import java.util.regex.*;

class prueba extends Object
{

   public static void main(String args[])
   {
      //Properties p = System.getProperties();
      //p.list(System.out);
      
      //=======================================================================
      /*Pattern patron2 = Pattern.compile("[0-9]{3}-[0-9]{2}-[0-9]{2}-[0-9]{2}");
      Matcher encaja2 = patron2.matcher("922-12-31-23");
      System.out.println(encaja2.matches());*/
      

      //=======================================================================
      //Definiciones de variables
      //WFBD wf = new WFBD();  wf.inicializar("jdbc:odbc:WFBDS","","","prueba");
      WFBD wf = new WFBD();  wf.inicializar("jdbc:postgresql:bdTotem","","","prueba");
      //WFBD wf = new WFBD("jdbc:odbc:WFBDS","","");
      
      WFSesion s1,s2;
      WFBatchSQL fsql = new WFBatchSQL(wf.leeConnection());
      
      //fsql.ponTipoElegido(0);//MS-ACCESS
      fsql.ponTipoElegido(5);//PGSQL

      fsql.ejecutarSecuencia();
      //System.out.println("fsql.ejecutarTodo()"+fsql.ejecutarTodo());
      //WFFiltro f1;
      //WFTicket t1;
      //WFLinea  l1;
      //wf.verInfo();
      //s1 = wf.crearSesion(); s1.login("USSII","123456");
      //s1.verInfo();
     //s1.logout();
      wf.desconectar();
   }
}

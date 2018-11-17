package david.tenerife.old;

//import des.lib.*;
import david.tenerife.ejb.conexiones.Conexion;
import david.tenerife.util.*;
import java.sql.*;

class prueba extends Object
{

   public static void main(String args[])
   {
      //=======================================================================
      //Definiciones de variables

      System.out.println("\nPrueba 01.......");
      Configuracion conf = new Configuracion();
      conf.setFichero("david.rec.login");

      Conexion conexion = new Conexion();

      conexion.inicializar(conf.getString("driver"),conf.getString("basededatos"),
    		  				conf.getString("usuario"),conf.getString("clave"),
    		  				conf.getString("entorno"));

      System.out.println("Conexion.estaConectado="+conexion.isConectado());
      System.out.println("Conexion.leeEntorno="+conexion.leeEntorno());

      //Diario d = new Diario(conexion);
      //d.registrarLOG(10,"probando...");

      System.out.println("\nPrueba 02...");
      try
      {
      	DatabaseMetaData md = conexion.leeConnection().getMetaData();
			System.out.println("DatabaseProductName    = "+md.getDatabaseProductName());
			System.out.println("DatabaseProductVersion = "+md.getDatabaseProductVersion());
      	System.out.println("DriverName             = "+md.getDriverName());
      	System.out.println("DriverVersion          = "+md.getDriverVersion());
     		System.out.println("ExtraNameCharacters    = "+md.getExtraNameCharacters());
     		System.out.println("IdentifierQuoteString  = "+md.getIdentifierQuoteString());
     		System.out.println("MaxColumnsInSelect     = "+md.getMaxColumnsInSelect());
     		System.out.println("ProcedureTerm          = "+md.getProcedureTerm());
     		System.out.println("StringFunctions        = "+md.getStringFunctions());
			System.out.println("URL                    = "+md.getURL());
			System.out.println("UserName               = "+md.getUserName());

			System.out.println("\nPrueba 03...");
   		ResultSet rs1;int i;

			//String nombreTablas="log%";
			//String tipos[]= new String[1];	tipos[0]="TABLES";
   		//String tipos[]= {"TABLE"};

			rs1=md.getTables(null,null,"%",new String [] {"TABLE"});i=0;
			while(rs1.next())
			{ System.out.println("Tables["+i+"]=<"+rs1.getString("TABLE_NAME"));i++;}
			rs1.close();

			System.out.println("\nPrueba 04...");
			Statement st1 = conexion.leeConnection().createStatement();
			rs1=st1.executeQuery("SELECT * FROM log_avisos");
			ResultSetMetaData rsmd = rs1.getMetaData();
			for (i=1; i<=rsmd.getColumnCount();i++)
			{
				System.out.println("Nombre["+i+"]="+rsmd.getColumnLabel(i)+" ancho="+rsmd.getColumnDisplaySize(i));
			}

	   		conexion.desconectar();
		}
		catch(Exception e) {System.err.println(e);}

      //=======================================================================
      System.out.println("\nFin de las pruebas.");
   }
}

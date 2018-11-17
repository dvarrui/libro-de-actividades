package david.tenerife.old;

import david.tenerife.ejb.conexiones.Conexion;
import david.tenerife.util.*;
//import des.apl.*;

//import java.io.*;
import java.sql.*;
import java.util.*;
//import java.util.regex.*;

public class ConsultarMetaDatos extends Object
{
	//public ConsultarMetaDatos () {System.out.println(".");}

   public static void main(String args[])
   {
	   //Atributos
		int 					i;
		String 				nombreTablas, str;
	   Configuracion 		conf;
		Conexion      		conexion;
		Statement 			st1;;
		ResultSet 			rs1;
		ResultSetMetaData rsmd;
		ArrayList			tablas; //, columnas, clavesP, clavesE, clavesI;

      try
      {
			/*Establecer Conexi�n con BBDD*/
      	conf = new Configuracion();
      	conf.setFichero("david.rec.login");

      	conexion = new Conexion();
      	conexion.inicializar(conf.getString("driver"),conf.getString("basededatos"),
      						conf.getString("usuario"),conf.getString("clave"),
      						conf.getString("entorno"));

			/*Crear MetaDatos*/
			DatabaseMetaData md = conexion.leeConnection().getMetaData();
			System.out.println("\nIniciando lectura de METADATOS...\n");

      	System.out.println("DriverName             = "+md.getDriverName());
      	System.out.println("DriverVersion          = "+md.getDriverVersion());
			System.out.println("URL                    = "+md.getURL());
			System.out.println("DatabaseProductName    = "+md.getDatabaseProductName());
			System.out.println("DatabaseProductVersion = "+md.getDatabaseProductVersion());
			System.out.println("UserName               = "+md.getUserName());

			/*Lectura de las tablas*/
			System.out.println("\nIniciando lectura de las tablas...\n");
			nombreTablas="%";
			//String tipos[]= {"TABLE"};
			//String tipos[]= new String[1];	tipos[0]="TABLES";

			rs1=md.getTables(null, null, nombreTablas, new String [] {"TABLE"}); i=0;
			tablas = new ArrayList();
			while(rs1.next())
			{
				str = rs1.getString("TABLE_NAME");
				tablas.add(str);
				System.out.println("Tabla["+i+"] = "+str);i++;
			}
			rs1.close();


			System.out.println("\nIniciando lectura de los campos...\n"); i=0;
			while(i<tablas.size())
			{

				st1 = conexion.leeConnection().createStatement();
				rs1=st1.executeQuery("SELECT * FROM "+tablas.get(i).toString());
				rsmd = rs1.getMetaData();
				/*Lectura de los campos de las tablas*/
				System.out.println("\nTabla "+tablas.get(i).toString()
										+" con = "+rsmd.getColumnCount()+" campos.\n");

				for(int j=1; j<=rsmd.getColumnCount();j++)
				{
					System.out.println("  \t"+tablas.get(i).toString()+"."+rsmd.getColumnLabel(j)
							+"\t{"+rsmd.getColumnTypeName(j)+"("+rsmd.getColumnDisplaySize(j)+")}"  );
				}

				rs1= md.getPrimaryKeys(null,null,tablas.get(i).toString());
				while(rs1.next())
				{ System.out.println("PK\t"+tablas.get(i).toString()+"."+rs1.getString("COLUMN_NAME"));}

				/*rs1= md.getExportedKeys(null,null,tablas.get(i).toString());
				while(rs1.next())
				{ System.out.println("EK\t"+tablas.get(i).toString()+"."+rs1.getString(8));}*/

				/*rs1= md.getImportedKeys(null,null,tablas.get(i).toString());
				while(rs1.next())
				{ System.out.println("IK\t"+tablas.get(i).toString()+"."+rs1.getString(8));}*/

				//Desconectar
				st1.close(); rs1.close();
				i++;
			}
    		conexion.desconectar();
		}
		catch(Exception e) {System.err.println(e);}

      System.out.println("\nFin de la consulta METADATOS.");
   }
}

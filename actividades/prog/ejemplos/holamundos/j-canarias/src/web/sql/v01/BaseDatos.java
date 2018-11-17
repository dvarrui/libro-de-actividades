package web.sql.v01;

import java.sql.*;

public class BaseDatos {
	
	public static void main(String[] args) {
		//String url="jdbc:odbc:Companies";
		String url="jdbc:postgresql:pruebas";
		
		try {
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			System.out.println(" [INFO] Cargando el driver...");
			Class.forName("org.postgresql.Driver");
			
			System.out.println(" [INFO] Conectando...");
			Connection con=DriverManager.getConnection(url,"david","david");
			
			SQLWarning warn=con.getWarnings();
			while(warn!=null) {
				System.out.println("SQLState : "+warn.getSQLState());
				System.out.println("Message  : "+warn.getMessage());
				System.out.println("Vendor   : "+warn.getErrorCode());
				System.out.println("");
				warn=warn.getNextWarning();
			}
			System.out.println(" [INFO] Cerrando conexión...");
			con.close();
			
		} catch(ClassNotFoundException e) {
			System.out.println(" [ERROR] Can't load driver "+e);				
		} catch(SQLException e) {
			System.out.println(" [ERROR] Database access failed "+e);
		}
	}
}

package web.sql.v04;

import java.sql.*;

public class BaseDatos {

	public static void main(String[] args) {
		BaseDatos bd = new BaseDatos();
		// String url="jdbc:odbc:Companies"; //ACCESS
		bd.setUrl("jdbc:postgresql:pruebas"); //Postgresql
		bd.abrir();
		bd.cerrar();
	}

	private Connection con;

	private String url;

	public BaseDatos() {
		url="jdbc:postgresql:pruebas";
	}

	public void abrir() {
		try {
			System.out.println(" [INFO] Cargando el driver...");
			Class.forName("org.postgresql.Driver"); //Postgresql
			// Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); //ACCESS
			
			System.out.println(" [INFO] Conectando...");
			con = DriverManager.getConnection(url, "david", "david");

		} catch (ClassNotFoundException e) {
			System.out.println(" [ERROR] Carga del Driver: " + e);
		} catch (SQLException e) {
			System.out.println(" [ERROR] Base de datos: " + e);
		}
	}

	public void cerrar() {
		try {
			System.out.println(" [INFO] Cerrando conexión...");
			con.close();
		} catch (Exception e) {
			System.out.println(" [ERROR] Cerrando BD: " + e);
		}
	}

	public Connection getConexion() {
		return con;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

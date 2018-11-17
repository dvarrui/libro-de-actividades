package david.lalaguna.almacenes;

import java.sql.*;

/**
 * Esta clase representa la conexión con el almacén de datos.
 * @author David Vargas Ruiz
 * @version 20060822
 *
 */
public class Conexion {

	public static void main(String[] args) {
		Conexion bd = new Conexion();
		// String url="jdbc:odbc:Companies";
		bd.setUrl("jdbc:postgresql:desiree");
		bd.open();
		bd.close();
	}

	private boolean abierto; //Indica si al alamcén está abierto (true=abierto, false=cerrado)

	private Connection con; //Objeto de conexión con la base de datos

	private String url; //url que identifica la base de datos a conectar

	/**
	 * Constructor de la clase Conexion.
	 */
	public Conexion() {
		con = null;
		url = "jdbc:postgresql:desiree";
		abierto = false;
	}

	/**
	 * Cierra el almacén de datos
	 * @return Devuelve true si todo es correcto
	 */
	public boolean close() {
		if (abierto) {
			try {
				System.out.println(" [INFO] Cerrando conexión...");
				con.close();
				abierto = false;
			} catch (Exception e) {
				System.out.println(" [ERROR] Cerrando BD: " + e);
				abierto = true;
			}
		}
		return abierto;
	}

	public Connection getConnection() {
		return con;
	}

	/**
	 * Abre el almacén de datos.
	 * @return Devuelve true si todo es correcto.
	 */
	public boolean open() {
		if (!abierto) {
			try {
				// Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				System.out.println(" [INFO] Cargando el driver...");
				Class.forName("org.postgresql.Driver");

				System.out.println(" [INFO] Conectando...");
				con = DriverManager.getConnection(url, "david", "david");

				SQLWarning warn = con.getWarnings();
				while (warn != null) {
					System.out.println("SQLState : " + warn.getSQLState());
					System.out.println("Message  : " + warn.getMessage());
					System.out.println("Vendor   : " + warn.getErrorCode());
					System.out.println("");
					warn = warn.getNextWarning();
				}
				abierto = true;
			} catch (ClassNotFoundException e) {
				System.out.println(" [ERROR] Carga del Driver: " + e);
				abierto = false;
			} catch (SQLException e) {
				System.out.println(" [ERROR] Base de datos: " + e);
				abierto = false;
			}
		}
		return abierto;
	}

	/**
	 * Establece un valor para el url de la base de datos.
	 * @param url
	 */
	public void setUrl(String url) {
		if (abierto)
			close();
		this.url = url;
	}
}

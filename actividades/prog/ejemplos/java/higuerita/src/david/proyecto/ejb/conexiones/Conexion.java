package david.proyecto.ejb.conexiones;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import david.proyecto.ejb.sesiones.ISesion;
import david.proyecto.ejb.sesiones.SesionSQL;


/**
 * Mantener una conexi�n permanente con una base de datos
 * 
 * @author David Vargas Ruiz
 * @version 1.1.0 - 20040618
 */
public class Conexion implements IConexion{
	public static final int ERROR = -1, DESCONECTADO = 0, CONECTADO = 1;

	private int cod_estado = DESCONECTADO;

	private Connection conexion;

	private String usuario, clave, url, entorno, des_estado = "DESCONECTADO";

	/**
	 * Crear una nueva sesi�n que acceda a la base de datos conectada.
	 * 
	 * @return Un objeto del tipo <b>WFSesion</b> que representa la sesi�n.
	 */
	public ISesion getSesion() {
		if (cod_estado == CONECTADO) {
			SesionSQL s = new SesionSQL();
			s.inicializar(this);
			return s;
		}
		return null;
	}

	/**
	 * Realiza la desconexi�n con la base de datos.
	 * 
	 * @return Devuelve <b>verdadero </b> si se realiza la desconexi�n con
	 *         �xito.
	 */
	public boolean desconectar() {
		try {
			conexion.close();
			return true;
		} catch (Exception e) {
			System.err.println("Conexion(). ERROR 2. Conexion.desconectar(): "
					+ e);
		}
		return false;
	}

	/**
	 * Devuelve <b>verdadero </b> si hay una conexi�n establecida.
	 * 
	 * @return Devuelve <b>Verdadero </b> si hay una conexi�n establecida.
	 */
	public boolean isConectado() {
		if (cod_estado == CONECTADO)
			return true;
		return false;
	}

	/**
	 * Si ha una conexi�n establecida se devuelve un objeto <b>Statement </b>
	 * 
	 * @return Devuelve un objeto <b>Statement </b> asociado a la conexi�n
	 */
	public Statement getStatement() {
		try {
			if (cod_estado == CONECTADO)
				return conexion.createStatement();
		} catch (Exception e) {
			System.err
					.println("Conexion(). ERROR 3. Problemas al devolver Statement");
		}
		return null;
	}

	/**
	 * Constructor externo de la clase para cumplir especificaciones de EJB.
	 */
	public void inicializar(String p_driver, String p_url, String p_usuario,
			String p_clave, String p_entorno) {
		// Conectar con la Base de Datos
		// cod_estado=DESCONECTADO; des_estado=new String("DESCONECTADO");
		try {

			// Se lee el driver JDBC que sea necesario
			Class.forName(p_driver);

			// Conectar a la base de datos
			usuario = new String(p_usuario);
			clave = new String(p_clave);
			url = new String(p_url);
			entorno = new String(p_entorno);
			conexion = DriverManager.getConnection(url, usuario, clave);
			cod_estado = CONECTADO;
			des_estado = new String("CONECTADO");
		} catch (Exception e) {
			cod_estado = ERROR;
			des_estado = new String("ERROR Conexi�n BBDD");
			System.err
					.println("Conexion(). ERROR 1. Problemas al establecer la conexi�n: "
							+ e);
		}
	}

	/**
	 * Devuelve el objeto connection ATENCION: ESTE METODO SE DEBE QUITAR DE LA
	 * SOLUCION FINAL
	 * 
	 * @return Devuelve el atributo <b>connection </b>
	 */
	public Connection leeConnection() {
		if (cod_estado == CONECTADO)
			return conexion;
		return null;
	}

	// ===================================================================
	/**
	 * Devuelve el valor del atributo <Entorno>
	 * 
	 * @return Devuelve el atributo <b>entorno </b>
	 */
	public String leeEntorno() {
		if (cod_estado == CONECTADO)
			return entorno;
		return null;
	}
}

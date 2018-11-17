package david.tenerife.ejb.sesiones;

import david.tenerife.ejb.conexiones.Conexion;

import java.util.*;
import java.sql.*;

/**
 * Clase que representa una sesi�n sobre una determinada instancia de la base de
 * datos (objeto del tipo <b>WFBD</b>)
 * 
 * @author David Vargas Ruiz
 * @version 0.8.0 - 20030824
 */
public class SesionSQL implements ISesion {
	public static final int ERROR = -1, READY = 0, LOGOUT = 1, LOGIN = 2;

	private int cod_perfil;

	private Conexion conexion;

	private String entorno;

	private java.util.Date fec_desde, fec_hasta;

	private java.util.Date fechaLogin, fecha_last_login;

	private int intentos_login, cod_estado = READY;

	private String pc, des_estado = "READY";

	private String usuario, clave, nombre, apellido1, apellido2;

	public String getEntorno() {
		return entorno;
	}

	public java.util.Date getFechaLogin() {
		return fechaLogin;
	}

	/**
	 * Constructor externo para cumplir especificaciones EJB
	 * 
	 * @param p_bd
	 *            Objeto conexi�n a base de datos.
	 */

	public void inicializar(Conexion pConexion) {
		// Crear sesion con la BBDD de WF
		usuario = null;
		clave = null;
		cod_perfil = -1;
		fec_desde = null;
		fec_hasta = null;
		fechaLogin = null;
		intentos_login = 0;
		cod_estado = READY;
		des_estado = new String("READY");
		conexion = pConexion;
		// Localizar informaci�n del PC
		Properties p = System.getProperties();
		pc = new String(p.getProperty("user.name") + "-"
				+ p.getProperty("os.name") + "-" + p.getProperty("os.version"));
	}

	// ===========
	// M�todos GET
	// ===========
	public boolean isLogin() {
		if (cod_estado == LOGIN)
			return true;
		return false;
	}

	public String leeApellido1() {
		if (cod_estado == LOGIN)
			return apellido1;
		return null;
	}

	public String leeApellido2() {
		if (cod_estado == LOGIN)
			return apellido2;
		return null;
	}

	public int leeCodPerfil() {
		if (cod_estado == LOGIN)
			return cod_perfil;
		return -1;
	}

	public String leeCodUsuario() {
		if (cod_estado == LOGIN)
			return usuario;
		return null;
	}

	public Connection leeConnection() {
		if (cod_estado == LOGIN)
			return conexion.leeConnection();
		return null;
	}

	public java.util.Date leeFechaLastLogin() {
		if (cod_estado == LOGIN)
			return fecha_last_login;
		return null;
	}

	public String leeNombre() {
		if (cod_estado == LOGIN)
			return nombre;
		return null;
	}

	public String leePC() {
		if (cod_estado == LOGIN)
			return pc;
		return null;
	}

	/**
	 * Lee el valor para una opci�n concreta del usuario.
	 * 
	 * @param p_opcion
	 *            Nombre de la opci�n.
	 * @return Devuelve un <b>String</b> con el valor de la opci�n.
	 */
	public String leeValorDeOpcion(String p_opcion) {
		if (cod_estado == LOGIN) {
			Connection con = conexion.leeConnection();
			Statement st1;
			ResultSet rs1;
			try {
				st1 = con.createStatement();
				rs1 = st1
						.executeQuery("SELECT * FROM DEF_D_USUARIO_OPCIONES opc "
								+ "WHERE opc.cod_usuario='"
								+ usuario
								+ "' AND opc.cod_opcion='" + p_opcion + "';");
				if (rs1.next()) {
					String s = new String(rs1.getString("VALOR"));
					rs1.close();
					st1.close();
					return s;
				}
				rs1.close();
				st1.close();
			} catch (Exception e) {
				cod_estado = ERROR;
				des_estado = new String("WFS ERROR WFSesion.leeValorDeOpcion()");
				System.err.println("Exception en WFSesion.leeValorDeOpcion(): "
						+ e);
			}
		}
		return "*";
	}

	/**
	 * Lee el valor de un par�metro del sistema.
	 * 
	 * @param p_parametro
	 *            Nombre del par�metro.
	 * @return Devuelve un <b>String</b> con el valor del par�metro.
	 */
	public String leeValorDeParametro(String p_parametro) {
		if (cod_estado == LOGIN) {
			Connection con = conexion.leeConnection();
			Statement st1;
			ResultSet rs1;
			try {
				st1 = con.createStatement();
				rs1 = st1.executeQuery("SELECT * FROM SYS_M_PARAMETROS par "
						+ "WHERE par.campo='" + p_parametro + "';");
				if (rs1.next()) {
					String s = new String(rs1.getString("VALOR"));
					rs1.close();
					st1.close();
					return s;
				}
				rs1.close();
				st1.close();
			} catch (Exception e) {
				cod_estado = ERROR;
				des_estado = new String(
						"WFS ERROR WFSesion.leeValorDeParametro()");
				System.err
						.println("Exception en WFSesion.leeValorDeParametro(): "
								+ e);
			}
		}
		return "*";
	}

	/**
	 * M�todo la realizar la operaci�n de LOGIN en la sesi�n.
	 * 
	 * @param p_usuario
	 *            Nombre del usuario, p_clave Clave del usuario
	 * @return Devuelve true si se ha realizado la operaci�n correctamente
	 */
	public boolean login(String p_usuario, String p_clave) {
		// Crear sesion con la BBDD de WF
		// Definici�n de variables
		Connection con = conexion.leeConnection();
		Statement st1, st2;
		ResultSet rs1, rs2;

		usuario = null;
		clave = null;
		cod_perfil = -1;
		fec_desde = null;
		fec_hasta = null;
		fechaLogin = null;
		fecha_last_login = null;
		nombre = null;
		apellido1 = null;
		apellido2 = null;

		if (++intentos_login > 3) {
			cod_estado = ERROR;
			des_estado = new String("WFS ERROR WFSesion.login()");
			return false;
		}

		try {
			st1 = con.createStatement();
			rs1 = st1
					.executeQuery("SELECT * FROM DEF_M_USUARIOS WHERE cod_usuario='"
							+ p_usuario + "' AND clave='" + p_clave + "';");
			if (rs1.next()) {
				usuario = new String(p_usuario);
				clave = new String(p_clave);
				cod_perfil = rs1.getInt("COD_PERFIL");
				fec_desde = rs1.getDate("FEC_DESDE");
				fec_hasta = rs1.getDate("FEC_HASTA");
				fechaLogin = new java.util.Date();
				nombre = rs1.getString("NOMBRE");
				apellido1 = rs1.getString("APELLIDO1");
				apellido2 = rs1.getString("APELLIDO2");

				if (fechaLogin.after(fec_desde)
						&& (fec_hasta == null || fechaLogin.before(fec_hasta))) {
					cod_estado = LOGIN;
					des_estado = new String("LOGIN");
					// Leer last login
					st2 = con.createStatement();
					rs2 = st2
							.executeQuery("SELECT MAX(FECHA) as LAST_LOGIN FROM LOG_D_ACCESOS WHERE TIPO='LOG' "
									+ "AND MODULO='WFS_LOGIN' AND USUARIO='"
									+ usuario + "'");
					if (rs2.next())
						fecha_last_login = rs2.getDate("LAST_LOGIN");
					rs2.close();

					// Registrar LOGIN en la BBDD
					st2
							.executeUpdate("INSERT INTO LOG_D_ACCESOS (TIPO,USUARIO,PC,MODULO,DESCRIPCION) VALUES('LOG','"
									+ usuario
									+ "','"
									+ pc
									+ "','WFS_LOGIN','Realizado login')");
					st2.close();

					return true;
				} else {
					cod_estado = READY;
					des_estado = new String("READY");
					// Registrar LOGIN en la BBDD
					st2 = con.createStatement();
					st2
							.executeUpdate("INSERT INTO LOG_D_ACCESOS (TIPO,USUARIO,PC,MODULO,DESCRIPCION) VALUES('WRN','"
									+ usuario
									+ "','"
									+ pc
									+ "','WFS_LOGIN','Intento "
									+ intentos_login
									+ " fallido (Usuario desactivado)')");
					st2.close();
				}
			} else {
				cod_estado = READY;
				des_estado = new String("READY");
				// Registrar LOGIN en la BBDD
				st2 = con.createStatement();
				st2
						.executeUpdate("INSERT INTO LOG_D_ACCESOS (TIPO,USUARIO,PC,MODULO,DESCRIPCION) VALUES('WRN','"
								+ p_usuario
								+ "','"
								+ pc
								+ "','WFS_LOGIN','Intento "
								+ intentos_login
								+ " fallido (Usuario/clave incorrecto)')");
				st2.close();
			}
			rs1.close();
			st1.close();
		} catch (Exception e) {
			cod_estado = ERROR;
			des_estado = new String("WFS ERROR WFSesion.login()");
			System.err.println("Exception en WFSesion.login(): " + e);
		}
		usuario = null;
		clave = null;
		cod_perfil = -1;
		fec_desde = null;
		fec_hasta = null;
		fechaLogin = null;
		nombre = null;
		apellido1 = null;
		apellido2 = null;
		return false;
	}

	/**
	 * M�todo la realizar la operaci�n de LOGOUT de la sesi�n.
	 * 
	 * @return Devuelve <b>verdadero</b> si se ha realizado la operaci�n
	 *         correctamente
	 */
	public boolean logout() {
		try {
			if (cod_estado == LOGOUT)
				return true;
			if (cod_estado == LOGIN) {
				// Registrar LOGOUT en la BBDD
				Connection con = conexion.leeConnection();
				Statement st1;
				st1 = con.createStatement();
				st1
						.executeUpdate("INSERT INTO LOG_D_ACCESOS (TIPO,USUARIO,PC,MODULO,DESCRIPCION) VALUES('LOG','"
								+ usuario
								+ "','"
								+ pc
								+ "','WFS_LOGOUT','Realizado logout')");
				st1.close();
				// Limpiar las variables
				usuario = null;
				clave = null;
				cod_perfil = -1;
				conexion = null;
				fec_desde = null;
				fec_hasta = null;
				pc = null;
				fechaLogin = null;
				fecha_last_login = null;
				nombre = null;
				apellido1 = null;
				apellido2 = null;
				cod_estado = LOGOUT;
				des_estado = new String("LOGOUT");
				return true;
			}
		} catch (Exception e) {
			cod_estado = ERROR;
			des_estado = new String("ERRORS WFSesion.logout()");
			System.err.println("WFS ERROR WFSesion.logout(): " + e);
		}
		return false;
	}

	// ===========================
	// WFSesion.ponValorDeOpcion()
	// ===========================
	/**
	 * Graba un valor para una opci�n concreta del usuario.
	 * 
	 * @param p_opcion
	 *            Nombre de la opci�n, p_valor Valor que debe tomar el campo
	 * @return Devuelve <b>verdadero</b> si se ha realizado correctamente.
	 */
	public boolean ponValorDeOpcion(String p_opcion, String p_valor) {
		if (cod_estado == LOGIN) {
			Connection con = conexion.leeConnection();
			Statement st1;
			try {
				String s = new String(leeValorDeOpcion(p_opcion));
				st1 = con.createStatement();
				if (s.equals("*")) {
					st1
							.executeUpdate("INSERT INTO DEF_D_USUARIO_OPCIONES (cod_usuario,cod_opcion,valor) "
									+ "VALUES ('"
									+ usuario
									+ "','"
									+ p_opcion
									+ "','" + p_valor + "');");
				} else if (!s.equals(p_valor) && !p_valor.equals("*")) {
					st1
							.executeUpdate("UPDATE DEF_D_USUARIO_OPCIONES set valor='"
									+ p_valor
									+ "' "
									+ "WHERE cod_usuario='"
									+ usuario
									+ "' AND cod_opcion='"
									+ p_opcion
									+ "';");
				}
				st1.close();
				return true;
			} catch (Exception e) {
				cod_estado = ERROR;
				des_estado = new String("WFS ERROR WFSesion.ponValorDeOpcion("
						+ p_opcion + "," + p_valor + ")");
				System.err.println("Exception en WFSesion.ponValorDeOpcion(): "
						+ e);
			}
		}
		return false;
	}

	// =======================
	// WFSesion.tienePermiso()
	// =======================
	/**
	 * @param Permiso
	 *            que se quiere consultar.
	 * @return Devuelve <b>verdadero</b> si el usuario dispone del permiso.
	 */
	public boolean tienePermiso(String p_permiso) {
		if (cod_estado == LOGIN) {
			Connection con = conexion.leeConnection();
			Statement st1;
			ResultSet rs1;
			try {
				st1 = con.createStatement();
				rs1 = st1
						.executeQuery("SELECT * FROM SYS_M_ROLES rol, DEF_D_PERFIL_ROLES per "
								+ "WHERE per.cod_perfil="
								+ cod_perfil
								+ " AND per.cod_rol=rol.cod_rol AND rol.cod_rol='"
								+ p_permiso + "';");
				if (rs1.next()) {
					rs1.close();
					st1.close();
					return true;
				}
				rs1.close();
				st1.close();
			} catch (Exception e) {
				cod_estado = ERROR;
				des_estado = new String("WFS ERROR WFSesion.tienePermiso()");
				System.err
						.println("Exception en WFSesion.tienePermiso(): " + e);
			}
		}
		return false;
	}
}

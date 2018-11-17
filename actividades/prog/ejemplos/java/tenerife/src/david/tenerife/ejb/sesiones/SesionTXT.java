package david.tenerife.ejb.sesiones;

import java.util.Date;

/**
 * @author David Vargas Ruiz
 * @version 1.0.0 - 20051121
 */
public class SesionTXT implements ISesion {

	private String clave;

	private boolean conectado;

	private String entorno;

	private Date fechaLogin;

	private String usuario;

	public Date getFechaLogin() {
		return fechaLogin;
	}

	public String getUsuario() {
		return usuario;
	}

	public void inicializar(String pFichero) {
		usuario = null;
		clave = null;
		conectado = false;
	}

	public boolean isLogin() {
		return conectado;
	}

	public boolean login(String pUsuario, String pClave) {
		usuario = pUsuario;
		clave = pClave;
		fechaLogin = new Date();
		conectado = true;
		return true;
	}
	
	public boolean login(String pUsuario, String pClave, String pEntorno) {
		usuario = pUsuario;
		clave = pClave;
		entorno = pEntorno;
		fechaLogin = new Date();
		conectado = true;
		return true;
	}

	public boolean logout() {
		conectado = false;
		return true;
	}

	public boolean tienePermiso(String pPermiso) {
		return true;
	}

	public String getEntorno() {
		return entorno;
	}

	public void setEntorno(String entorno) {
		this.entorno = entorno;
	}

}

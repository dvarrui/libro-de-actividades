package hilos.hosts;

import java.util.StringTokenizer;

/**
 * Esta clase representa a un HOST de la red.
 * 
 * @author David Vargas Ruiz
 * @version 1.0.0 - 20060128
 * 
 */
public class Host {

	public static final String SEPARADOR = ":";

	private String grupo; //Para agrupar los host

	private String descripcion; //Propietario del host

	private String ip; //Dirección IP del host

	private boolean ping; //Indicador de si el ping llega correctamente

	public void fromCadena(String pTexto) {
		StringTokenizer st = new StringTokenizer(pTexto, SEPARADOR);
		ip = st.nextToken();
		descripcion = st.nextToken();
		grupo = st.nextToken();
		ping = false;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getGrupo() {
		return grupo;
	}

	public String getIp() {
		return ip;
	}

	/**
	 * Devuelve verdadero si el HOST responde al comando PING
	 * 
	 * @return
	 */
	public boolean isPing() {
		return ping;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setPing(boolean ping) {
		this.ping = ping;
	}

	public String toCadena() {
		return ip + SEPARADOR + descripcion + SEPARADOR + grupo;
	}
}

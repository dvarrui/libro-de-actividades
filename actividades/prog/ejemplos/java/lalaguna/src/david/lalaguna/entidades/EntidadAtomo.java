package david.lalaguna.entidades;

import java.util.Date;
import java.util.StringTokenizer;

public class EntidadAtomo {

	public static final long CODIGONULO = 0;

	public static final String SEPARADOR = "|";

	String atomo;

	long codigo; // Código identificador de la base de datos

	String descripcion;

	Date fechaUM;

	String usuario;

	/**
	 * Constructor de prueba
	 * 
	 * @param codigo
	 * @param atomo
	 */
	public EntidadAtomo(long codigo, String atomo) {
		this.codigo = codigo;
		this.atomo = atomo;
		descripcion = "sin-definir";
		fechaUM = new java.util.Date();
		usuario = "sin-especificar";
	}

	/**
	 * Constructor recomendado
	 * 
	 * @param codigo
	 * @param atomo
	 * @param descripcion
	 * @param fechaUM
	 * @param usuario
	 */
	public EntidadAtomo(long codigo, String atomo, String descripcion,
			Date fechaUM, String usuario) {
		this.codigo = codigo;
		this.atomo = atomo;
		this.descripcion = "sin-definir";
		this.fechaUM = new java.util.Date();
		this.usuario = "sin-especificar";
	}

	public Object clone() {
		EntidadAtomo e = new EntidadAtomo(this.getCodigo(), this.getAtomo());
		return e;
	}

	/**
	 * Comprueba la igualdad de dos registros EntidadAtomo.
	 * 
	 * @param Un
	 *            objeto del tipo EntidadAtomo
	 * @return Devuelve verdadero si son iguales.
	 */
	public boolean equals(EntidadAtomo a) {
		if (atomo.equals(a.getAtomo()))
			return true;
		return false;
	}

	/**
	 * Apartir de un String crea el objeto EntidadAtomo
	 * 
	 * @param texto
	 */
	public void fromCadena(String texto) {
		StringTokenizer st = new StringTokenizer(texto, SEPARADOR);
		codigo = Long.parseLong(st.nextToken());
		atomo = st.nextToken();
		descripcion = st.nextToken();
		// TODO: fechaUM = Date.parse(st.nextToken());
		usuario = st.nextToken();
	}

	public String getAtomo() {
		return atomo;
	}

	public long getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public java.util.Date getFechaUM() {
		return fechaUM;
	}

	public String getUsuario() {
		return usuario;
	}

	/**
	 * Devuelve un objeto String con todos los datos del objeto EntidadAtomo
	 * 
	 * @return Un String con los datos
	 */
	public String toCadena() {
		return this.getCodigo() + SEPARADOR + this.getAtomo() + SEPARADOR
				+ this.getDescripcion() + SEPARADOR + this.getFechaUM()
				+ SEPARADOR + this.getUsuario();
	}
}

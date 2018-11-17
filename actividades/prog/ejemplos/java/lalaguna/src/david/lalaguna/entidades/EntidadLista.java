package david.lalaguna.entidades;

import david.lalaguna.estructuras.Lista;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * @author David Vargas Ruiz
 * @version 20060822
 * 
 * Esta clase encapsula la estructura Lista en una Entidad recuperable desde el
 * Alamcán de Listas
 */
public class EntidadLista {
	public static final String SEPARADOR = "|";

	private long codigo; // Identificador de la entidad en la base de datos

	private Date fechaUM; // Fecha de la última modificación del registro

	private Lista lista; // Objeto de tipo LISTA

	private String usuario; // Usuario responsable de la última modificación

	/**
	 * Constructor para hacer pruebas
	 * 
	 * @param lista
	 * @param codigo
	 */
	public EntidadLista(Lista lista, long codigo) {
		this.lista = lista;
		this.codigo = codigo;
		this.fechaUM = new java.util.Date();
		this.usuario = "sin-especificar";
	}

	/**
	 * Constructor recomendado
	 * 
	 * @param lista
	 * @param codigo
	 * @param fecha_um
	 * @param usuario
	 */
	public EntidadLista(Lista lista, long codigo, Date fechaUM, String usuario) {
		this.lista = lista;
		this.codigo = codigo;
		this.fechaUM = fechaUM;
		this.usuario = usuario;
	}

	/**
	 * Crea una copia del objeto
	 */
	public Object clone() {
		return new EntidadLista(lista, codigo, fechaUM, usuario);
	}

	/**
	 * Dos objetos EntidadLista se considerarán iguales si tienen el mismo
	 * código identificador en la base de datos.
	 * 
	 * @param Objeto
	 *            de tipo EntidadLista
	 * @return Devuelve verdadero si los objetos son iguales.
	 */
	public boolean equals(EntidadLista e) {
		if (codigo == e.getCodigo())
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
		lista = new Lista();
		lista.setLista(st.nextToken());
		// TODO: fechaUM = Date.parse(st.nextToken());
		usuario = st.nextToken();
	}

	/**
	 * Devuelve el código identificador del registro en la base de datos
	 * 
	 * @return código del registro
	 */
	public long getCodigo() {
		return codigo;
	}

	/**
	 * Devuelve la fecha de última modificación del registro en la base de datos
	 * 
	 * @return la fecha de última modificación
	 */
	public Date getFechaUM() {
		return fechaUM;
	}

	/**
	 * Devuelve un objeto de tipo Lista con la lista del registro
	 * 
	 * @return Objeto de tipo <b>Lista</b>
	 */
	public Lista getLista() {
		return lista;
	}

	/**
	 * Devuelve el usuario que realizó la última modificación del registro.
	 * 
	 * @return Nombre del usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Devuelve un objeto String con todos los datos del objeto EntidadAtomo
	 * 
	 * @return Un String con los datos
	 */
	public String toCadena() {
		return this.getCodigo() + SEPARADOR + this.getLista().getString()
				+ SEPARADOR + this.getFechaUM() + SEPARADOR + this.getUsuario();
	}
}

package david.lalaguna.estructuras;

import java.util.StringTokenizer;

/**
 * La clase Atomo implementa la estructura átomo típica de las listas
 * del lenguaje de programación LISP.
 * 
 * @author David Vargas Ruiz
 * @version 20060827
 * 
 */
public class Atomo implements IEstructura {

	public static final long CODIGONULO = 0;

	public static final String SEPARADOR = "|";

	String atomo;

	long cod_atomo; // Código identificador de la base de datos

	long cod_lista; // Código identificador de la base de datos

	String descripcion;

	boolean enlace;

	int orden; // Indicador del orden de los átomos???

	int sublista;

	public Atomo() {
		cod_atomo = CODIGONULO;
		cod_lista = CODIGONULO;
		sublista = -1;
		orden = -1;
		atomo = null;
		descripcion = null;
		enlace = false;
	}

	public Atomo(int sublista, int orden, String atomo) {
		this.setCodAtomo(CODIGONULO);
		this.setCodLista(CODIGONULO);
		this.setSublista(sublista);
		this.setOrden(orden);
		this.setAtomo(atomo);
		this.setEnlace(isEnlace(atomo));
	}

	public Object clone() {
		Atomo a = new Atomo();
		a.setAtomo(this.getAtomo());
		return a;
	}

	public boolean equals(IEstructura estructura) {
		Atomo a = (Atomo) estructura;
		if (atomo.equals(a.getAtomo()))
			return true;
		return false;
	}

	public void fromCadena(String texto) {
		StringTokenizer st = new StringTokenizer(texto, SEPARADOR);
		this.setCodAtomo(Long.parseLong(st.nextToken()));
		this.setAtomo(st.nextToken());
		this.setDescripcion(st.nextToken());
		this.setCodLista(Long.parseLong(st.nextToken()));
		this.setSublista(Integer.parseInt(st.nextToken()));
		this.setOrden(Integer.parseInt(st.nextToken()));
		this.setEnlace(Boolean.parseBoolean(st.nextToken()));
	}

	public String getAtomo() {
		return atomo;
	}

	public long getCodAtomo() {
		return cod_atomo;
	}

	public long getCodLista() {
		return cod_lista;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getOrden() {
		return orden;
	}

	public int getSublista() {
		return sublista;
	}

	public boolean isEnlace() {
		return enlace;
	}

	private boolean isEnlace(String atomo) {
		if (atomo.trim().startsWith(Lista.CARACTERESPECIAL)) {
			int i = Integer.parseInt(atomo.trim().substring(1));
			String s = new String("" + i);
			if (atomo.trim().substring(1).equals(s))
				return true;
			return false;
		}
		return false;
	}

	public void setAtomo(String atomo) {
		this.atomo = atomo;
	}

	public void setCodAtomo(long cod_atomo) {
		this.cod_atomo = cod_atomo;
	}

	public void setCodLista(long cod_lista) {
		this.cod_lista = cod_lista;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setEnlace(boolean enlace) {
		this.enlace = enlace;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public void setSublista(int sublista) {
		this.sublista = sublista;
	}

	public String toCadena() {
		return this.getCodAtomo() + SEPARADOR + this.getAtomo() + SEPARADOR
				+ this.getDescripcion() + SEPARADOR + this.getCodLista()
				+ SEPARADOR + this.getSublista() + SEPARADOR + this.getOrden()
				+ SEPARADOR + this.isEnlace();
	}
}

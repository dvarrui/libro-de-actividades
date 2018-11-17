package david.tenerife.lib;

import david.tenerife.util.Notacion;

/**
 * @author David Vargas Ruiz
 * @version 1.0.0 - 20060109
 */
public class Campo {
	private boolean clave;

	private boolean descripcion;

	private String etiqueta;

	private String expresionRegular;

	private String nombre;

	private String nombreOrigen;

	private String tablaFK;

	private String tipo;

	public Campo() {
		clave = false;
		descripcion = false;
		tablaFK = null;
	}

	public String getEtiqueta() {
		if (etiqueta == null)
			return this.nombre;
		return etiqueta;
	}

	public String getExpresionRegular() {
		return expresionRegular;
	}

	public String getNombre() {
		return nombre;
	}

	public String getNombreOrigen() {
		return nombreOrigen;
	}

	public String getTablaFK() {
		return tablaFK;
	}

	public String getTipo() {
		return tipo;
	}

	public boolean hayTablaFK() {
		if (tablaFK == null)
			return false;
		return true;
	}

	public boolean isClave() {
		return clave;
	}

	public boolean isDescripcion() {
		return descripcion;
	}

	public boolean isFecha() {
		if (Notacion.getTipoJava(tipo).toLowerCase().equals("date"))
			return true;
		return false;
	}

	public void reset() {
		nombre = null;
		tipo = null;
	}

	public void setClave(boolean pClave) {
		clave = pClave;
	}

	public void setDescripcion(boolean descripcion) {
		this.descripcion = descripcion;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public void setExpresionRegular(String expresionRegular) {
		this.expresionRegular = expresionRegular;
	}

	public void setNombre(String nombre) {
		this.nombreOrigen = nombre;
		this.nombre = Notacion.getSinGuiones(nombre);
	}

	public void setTablaFK(String tablaFK) {
		this.tablaFK = tablaFK;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String toCadena() {
		StringBuilder sb = new StringBuilder(200);
		sb.append(nombre + "|" + nombreOrigen + "|" + tipo + "|" + etiqueta
				+ "|" + tablaFK + "|" + clave + "|" + descripcion + "|"
				+ expresionRegular);
		return sb.toString();
	}
}
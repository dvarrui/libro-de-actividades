package david.tenerife.lib;

import java.util.*;
import david.tenerife.util.Notacion;

/**
 * Contiene la definición completa de un proyecto Base de Datos
 * 
 * @author David Vargas Ruiz
 * @version 1.0.0 - 20051230
 */
public class BaseDatos {
	private String nombre;

	private String nombreProyecto;

	private String paqueteJava;

	private ArrayList tablas;

	public BaseDatos() {
		tablas = new ArrayList();
	}

	public void addTabla(Tabla pTabla) {
		tablas.add(pTabla);
	}

	public String getNombre() {
		return nombre;
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public int getNumeroTablas() {
		return tablas.size();
	}

	public String getPaqueteJava() {
		return paqueteJava;
	}

	public Tabla getTabla(int index) {
		return (Tabla) tablas.get(index);
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public void setPaqueteJava(String nombre) {
		this.paqueteJava = Notacion.getSinGuiones(nombre).toLowerCase();
	}

	public String toCadena() {
		StringBuilder sb = new StringBuilder(300);
		sb.append(nombre + "|" + nombreProyecto + "|" + paqueteJava + "|"
				+ tablas.size());
		return sb.toString();
	}
}
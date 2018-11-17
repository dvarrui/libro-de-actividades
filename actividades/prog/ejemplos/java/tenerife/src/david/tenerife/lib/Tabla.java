package david.tenerife.lib;

import java.util.*;
import david.tenerife.util.*;

/**
 * @author David Vargas Ruiz
 * @version 1.0.3 - 20051104
 */
public class Tabla {
	public final static int MAESTRA = 0;

	public final static int PRINCIPAL = 1;

	public final static int SECUNDARIA = 2;

	private ArrayList campos; //Definición de loa campos

	private String nombre;

	private String nombreOrigen;

	private int tipo; //Tipo MAESTRA, PRINCIPAL, SECUNDARIA

	public Tabla() {
		campos = new ArrayList();
		nombre = null;
		nombreOrigen = null;
		tipo = Tabla.MAESTRA;
	}

	public void addCampo(Campo pCampo) {
		campos.add(pCampo);
	}

	public Campo getCampo(int index) {
		return (Campo) campos.get(index);
	}

	public Campo getCampoClave() {
		for (int i = 0; i < getNumeroCampos(); i++) {
			if (this.getCampo(i).isClave())
				return getCampo(i);
		}
		return null;
	}

	public String getNombre() {
		return nombre;
	}

	public String getNombreOrigen() {
		return nombreOrigen;
	}

	public int getNumeroCampos() {
		return campos.size();
	}

	public boolean hayClave() {
		for (int i = 0; i < this.getNumeroCampos(); i++) {
			if (this.getCampo(i).isClave())
				return true;
		}
		return false;
	}

	public boolean hayFecha() {
		for (int i = 0; i < this.getNumeroCampos(); i++) {
			if (this.getCampo(i).isFecha())
				return true;
		}
		return false;
	}

	public void reset() {
		nombre = null;
		nombreOrigen = null;
		campos.clear();
	}

	public void setNombre(String nombre) {
		this.nombreOrigen = nombre;
		this.nombre = Notacion.getSinGuiones(nombre);
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public String toCadena() {
		StringBuilder sb = new StringBuilder(200);
		sb.append(nombre+"|"+nombreOrigen+"|"+tipo+"|"+campos.size());
		return sb.toString();
	}
}
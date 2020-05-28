/**
 * Author: David Vargas Ruiz <dvargas@canarias.org>
 */
package gui.poo.polimorfismo.ordenar;

public class Persona implements IElementosOrdenados {

	private String nombre;

	private String apellido1;

	private String apellido2;

	private int edad;

	Persona(String n, String a1, String a2, int e) {
		this.nombre = n;
		this.apellido1 = a1;
		this.apellido2 = a2;
		this.edad = e;
	}

	public String toCadena() {
		return nombre + "|" + apellido1 + "|" + apellido2 + "|" + edad;
	}

	public void mostrar() {
		System.out.println(this.toCadena());
	}

	public boolean esMayorQue(Object obj) {
		Persona p = (Persona) obj;
		if (this.getApellido1().compareTo(p.getApellido1())>0) return true;
		return false;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}

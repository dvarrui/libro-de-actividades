/**
 * Author: David Vargas Ruiz <dvargas@canarias.org>
 */
package gui.poo.polimorfismo.ordenar;

public class Horoscopo implements IElementosOrdenados {

	private int mes;
	private String nombre;
	
	Horoscopo(int mes, String nombre) {
		this.mes=mes;
		this.nombre=nombre;
	}
	
	public int getMes() {
		return this.mes;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String toCadena() {
		return mes+"|"+nombre;
	}
	
	public void mostrar() {
		System.out.println(this.toCadena());
	}
	
	public boolean esMayorQue(Object obj) {
		Horoscopo h = (Horoscopo) obj;
		
		if (this.getMes()>h.getMes()) return true;
		
		return false;
	}
}

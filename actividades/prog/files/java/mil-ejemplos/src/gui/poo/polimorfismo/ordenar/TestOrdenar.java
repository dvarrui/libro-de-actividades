/**
 * Author: David Vargas Ruiz <dvargas@canarias.org>
 */
package gui.poo.polimorfismo.ordenar;

public class TestOrdenar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Horoscopo h[] = new Horoscopo[3];
		h[0] = new Horoscopo(12,"Sagitario");
		h[1] = new Horoscopo(11,"Escorpio");
		h[2] = new Horoscopo(1,"Capricornio");

		Horoscopo s[];
		
		s = (Horoscopo[]) Ordenar.metodoUno(h);
		
		Ordenar.mostrar(h);
		Ordenar.mostrar(s);
		
		Persona p[] = new Persona[3];
		p[0] = new Persona("A","CCC","",10);
		p[1] = new Persona("A","BBB","",20);
		p[2] = new Persona("A","AAA","",30);
		
		Persona q[];
		q = (Persona[]) Ordenar.metodoUno(p);
		
		Ordenar.mostrar(p);
		Ordenar.mostrar(q);
		
	}

}

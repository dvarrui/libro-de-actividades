/**
 * Author: David Vargas Ruiz <dvargas@canarias.org>
 */
package gui.poo.polimorfismo.figuras;


public class Circulo implements IDibujarFigura {

	private String tipo;
	
	Circulo() {
		tipo = "Circulo";
	}
	
	public void dibujar() {
		System.out.println(" *** ");
		System.out.println("*   *");
		System.out.println(" *** ");
	}
	
	public void mostrar() {
		System.out.println("Soy un "+tipo);
	}
}

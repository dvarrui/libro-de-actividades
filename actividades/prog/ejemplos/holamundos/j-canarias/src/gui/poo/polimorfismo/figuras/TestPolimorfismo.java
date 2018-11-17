/**
 * Author: David Vargas Ruiz <dvargas@canarias.org>
 */
package gui.poo.polimorfismo.figuras;


public class TestPolimorfismo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Circulo f1 = new Circulo();
		Cuadrado f2 = new Cuadrado();
		Triangulo f3 = new Triangulo();
		
		IDibujarFigura v[] = new IDibujarFigura[3];
		v[0]=f1;
		v[1]=f2;
		v[2]=f3;
		
		for(int i=0;i<v.length;i++){
			v[i].mostrar();
			v[i].dibujar();
		}
	}
}

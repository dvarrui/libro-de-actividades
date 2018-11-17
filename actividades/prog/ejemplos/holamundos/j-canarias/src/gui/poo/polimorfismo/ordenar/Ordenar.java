/**
 * Author: David Vargas Ruiz <dvargas@canarias.org>
 */
package gui.poo.polimorfismo.ordenar;

public class Ordenar {

	public static Object[] metodoUno(Object[] entrada) {
		IElementosOrdenados v[] = (IElementosOrdenados[]) entrada.clone();
		IElementosOrdenados swap;
		
		for(int i=0;i<v.length-1 ;i++) {
			for(int j=i+1;j<v.length;j++) {
				if (v[i].esMayorQue(v[j])) {
					swap=v[i];
					v[i]=v[j];
					v[j]=swap;
				}
			}
		}	
		return (Object[]) v;
	}
	
	public static void mostrar(Object[] e) {
		IElementosOrdenados[] v = (IElementosOrdenados[]) e.clone();
		System.out.println("Mostrando la lista...");
		for(int i=0;i<v.length;i++) v[i].mostrar();
	}
}

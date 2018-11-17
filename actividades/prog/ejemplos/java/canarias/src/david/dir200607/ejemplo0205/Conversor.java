package david.dir200607.ejemplo0205;

import java.util.ArrayList;

public class Conversor {

	private static final int REF=0;
	
	private ArrayList monedas;

	public Conversor() {
		int i=0;
		monedas=new ArrayList();
		monedas.add(i++,new Moneda("Euro",1));
		monedas.add(i++,new Moneda("Dolar",1.2));
		monedas.add(i++,new Moneda("Libra",0.8));
		monedas.add(i++,new Moneda("Yen",0.5));
	}
	
	public double convertir(int origen, int destino, double valor) {
		if (origen==destino) return valor;
		
		if (origen==REF) {
			double c = ((Moneda) monedas.get(destino)).getValorEnEuros();
			
			return redondear(valor * c);
		} if (destino==REF) {
			double c = ((Moneda) monedas.get(origen)).getValorEnEuros();
			return redondear(valor / c);
	    } else {
	    	double e = this.convertir(origen, REF, valor);
	    	return redondear(this.convertir(REF, destino, e));
		}
	}
	
	private double redondear(double valor) {
		double d = Math.round(valor*100);
		return d/100;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Conversor c = new Conversor();
		System.out.println("1   E -> E ="+c.convertir(0, 0, 1));
		System.out.println("1   E -> D ="+c.convertir(0, 1, 1));
		System.out.println("1   E -> L ="+c.convertir(0, 2, 1));
		System.out.println("1   E -> Y ="+c.convertir(0, 3, 1));
		System.out.println("1.2 D -> E ="+c.convertir(1, 0, 1.2));
		System.out.println("0.8 L -> E ="+c.convertir(2, 0, 0.8));
		System.out.println("0.5 Y -> E ="+c.convertir(3, 0, 0.5));
		System.out.println("1.0 D -> E ="+c.convertir(1, 0, 1));
		System.out.println("1.0 D -> L ="+c.convertir(1, 2, 1));
		System.out.println("1.0 D -> Y ="+c.convertir(1, 3, 1));
	}

}

package juegos.cinquillo.cartas;



public class Baraja extends Cartas {

	public Baraja() {
		super();
		inicializar();
	}
	
	protected void inicializar() {
		for (Palo palo : Palo.values()) {
			for (int numero = 1; numero <= 12; numero++) {
				if ((numero > 7) && (numero < 10)) continue;
				Carta carta = new Carta(numero, palo);
				getCartas().add(carta);
			}
		}
	}
	
	public final Cartas barajar() {
		return null;
	}
	
}

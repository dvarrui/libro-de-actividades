package juegos.cinquillo.cartas;

public class CartaNoValida extends Exception {
	private static final long serialVersionUID = 596372496221234479L;

	private Carta carta;
	
	public CartaNoValida(Carta carta) {
		super("Carta " + carta + " no es válida");
		this.carta = carta;
	}

	public Carta getCarta() {
		return carta;
	}

}

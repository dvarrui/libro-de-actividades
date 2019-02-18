package juegos.cinquillo.cartas;

import juegos.cinquillo.cinquillo.Mesa;


public abstract class Jugador {
	private String nombre;
	private TipoJugador tipo;
	private Cartas cartas;

	public Jugador(String nombre, TipoJugador tipo) {
		this.cartas = new Cartas();
		this.nombre = nombre;
		this.tipo = tipo;
	}
	
	public void addCarta(Carta carta) {
		this.cartas.addCartaOrdenada(carta);
	}
	
	public Cartas getCartas() {
		return cartas;
	}

	public String getNombre() {
		return nombre;
	}

	public TipoJugador getTipo() {
		return tipo;
	}

	public abstract Carta seleccionarCarta(final Mesa mesa);
	
	public Carta echarCarta(final Mesa mesa) {
		Carta carta = seleccionarCarta(mesa);
		if (carta != null) cartas.removeCarta(carta);
		return carta;
	}

	public String toString(String tabs) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(tabs + "Jugador {\n");
		buffer.append(tabs + "\tnombre=" + this.nombre + "\n");
		buffer.append(tabs + "\ttipo=" + this.tipo + "\n");
		buffer.append(tabs + "\tcartas{\n" + this.cartas.toString("\t\t" + tabs) + tabs + "\t}\n");
		buffer.append(tabs + "}");
		return buffer.toString();
	}
	
	public String toString() {
		return toString("");
	}
	
	public abstract void notificarJugada(String nombreJugador, Carta carta);
	
}

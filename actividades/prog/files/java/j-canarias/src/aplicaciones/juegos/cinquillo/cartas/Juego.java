package juegos.cinquillo.cartas;

import java.util.ArrayList;
import java.util.List;

public abstract class Juego {
	private final Baraja baraja;
	private List<Jugador> jugadores;
	private Jugador jugadorActual;
	
	public Juego() {
		this.baraja = new Baraja();
		this.jugadores = new ArrayList<Jugador>();
		this.jugadorActual = null;
	}
	
	public void addJugador(Jugador jugador) {
		if (this.jugadores.isEmpty()) {
			setJugadorActual(jugador);
		}
		this.jugadores.add(jugador);
	}

	public final List<Jugador> getJugadores() {
		return this.jugadores;
	}

	public Jugador getJugadorActual() {
		return jugadorActual;
	}

	public void setJugadorActual(Jugador jugadorActual) {
		this.jugadorActual = jugadorActual;
	}
	
	protected Baraja getBaraja() {
		return this.baraja;
	}
	
	public abstract void jugar();
	public abstract void repartir();
	public abstract void pasarTurno();
	
}

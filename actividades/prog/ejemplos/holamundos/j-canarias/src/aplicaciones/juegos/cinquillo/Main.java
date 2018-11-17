package juegos.cinquillo;

import juegos.cinquillo.cinquillo.Cinquillo;
import juegos.cinquillo.cinquillo.JugadorCinquillo;

public class Main {

	public static void main(String[] args) {
		Cinquillo cinquillo = new Cinquillo();
		cinquillo.addJugador(new JugadorCinquillo("jugador1"));
		cinquillo.addJugador(new JugadorCinquillo("jugador2"));
		cinquillo.addJugador(new JugadorCinquillo("jugador3"));
		cinquillo.addJugador(new JugadorCinquillo("jugador4"));
		cinquillo.jugar();
	}

}

package juegos.cinquillo.cinquillo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import juegos.cinquillo.cartas.Carta;
import juegos.cinquillo.cartas.Cartas;
import juegos.cinquillo.cartas.Jugador;
import juegos.cinquillo.cartas.TipoJugador;

public class JugadorCinquillo extends Jugador {

	public JugadorCinquillo(String nombre) {
		super(nombre, TipoJugador.CPU);
	}

	public List<Carta> cartasValidas(Mesa mesa) {
		List<Carta> validas = new ArrayList<Carta>(); 
		Cartas cartas = this.getCartas();
		for (Carta carta : cartas.getCartas()) {
			if (mesa.comprobarValidez(carta)) {
				validas.add(carta);
			}
		}
		return validas;
	}
	
	@Override
	public Carta seleccionarCarta(final Mesa mesa) {
		List<Carta> validas = cartasValidas(mesa);
		int totalCartas = validas.size();
		if (totalCartas == 0) return null;
		Random random = new Random();
		int cartaElegida = random.nextInt(totalCartas);
		return validas.get(cartaElegida);
	}

	@Override
	public void notificarJugada(String nombreJugador, Carta carta) {
//		System.out.println(getNombre() + " sabe que " + nombreJugador + " ha echado " + carta);
	}

}

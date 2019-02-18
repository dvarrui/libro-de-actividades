package juegos.cinquillo.cinquillo;

import java.util.HashMap;
import java.util.Map;

import juegos.cinquillo.cartas.Carta;
import juegos.cinquillo.cartas.CartaNoValida;
import juegos.cinquillo.cartas.Cartas;
import juegos.cinquillo.cartas.Palo;

public class Mesa {
	private Map<Palo, Cartas> columnas;
	
	public Mesa() {
		this.columnas = new HashMap<Palo, Cartas>();
		for (Palo palo : Palo.values()) {
			this.columnas.put(palo, new Cartas());
		}
	}

	public Cartas getColumna(Palo palo) {
		return columnas.get(palo);
	}

	public void ponerCarta(Carta carta) throws CartaNoValida {
		if (!comprobarValidez(carta)) {
			throw new CartaNoValida(carta);
		}
		if (carta == null) return;
		getColumna(carta.getPalo()).addCartaOrdenada(carta);
	}

	public Boolean comprobarValidez(Carta carta) {
		if (carta == null) {
			// TODO comprobar que el jugador no tiene cartas para echar
			return true;
		}
		
		if (carta.getNumero() == 5) {
			return true;
		}
		
		Palo palo = carta.getPalo();
		Cartas cartas = getColumna(palo);
		
		if (cartas.getCartas().isEmpty()) return false;
		
		if (cartas.getPrimera().esSiguiente(carta)) {
			return true;
		}
		
		if (cartas.getUltima().esAnterior(carta)) {
			return true;
		}
		
		return false;
	}	
}

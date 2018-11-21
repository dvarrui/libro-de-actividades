package juegos.cinquillo.cinquillo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import juegos.cinquillo.cartas.Carta;
import juegos.cinquillo.cartas.CartaNoValida;
import juegos.cinquillo.cartas.Cartas;
import juegos.cinquillo.cartas.Juego;
import juegos.cinquillo.cartas.Jugador;
import juegos.cinquillo.cartas.Palo;

public class Cinquillo extends Juego {
	private Mesa mesa;
	
	public Cinquillo() {
		super();
		this.mesa = new Mesa();
	}
	
	@Override
	public void repartir() {
		Random random = new Random();
		List<Carta> todas = new ArrayList<Carta>(getBaraja().getCartas());
		int j = 0;
		for (int i = todas.size(); i >= 1; i--) {
			int actual = random.nextInt(i);
			Carta carta = todas.get(actual);
			getJugadores().get(j).addCarta(carta);
			todas.remove(carta);
			j++;
			if (j > 3) j = 0;
		}
	}

	@Override
	public void pasarTurno() {
		Jugador jugadorActual = getJugadorActual();
		int pos = getJugadores().indexOf(jugadorActual) + 1;
		if (pos >= getJugadores().size()) pos = 0;
		setJugadorActual(getJugadores().get(pos));
	}
	
	public Boolean comprobarValidez(Carta carta) {
		if (carta.getNumero() == 5) {
			return true;
		}
		
		Palo palo = carta.getPalo();
		Integer numero = carta.getNumero();
		Cartas cartas = this.mesa.getColumna(palo);
		
		if (cartas.getCartas().isEmpty()) return false;
		
		Carta baja = cartas.getCartas().get(0);
		if (baja.getNumero() == numero + 1) {
			return true;
		}
		
		Carta alta = cartas.getCartas().get(cartas.getCartas().size() - 1);
		if (alta.getNumero() == numero - 1) {
			return true;
		}
		
		return false;
	}
	
	private void notificar(Jugador jugador, Carta carta) {
		for (Jugador j : getJugadores()) {
			j.notificarJugada(jugador.getNombre(), carta);
		}
	}

	public void jugar() {
		repartir();
		while (true) {
			Jugador jugadorActual = getJugadorActual();
			
			System.out.println("es el turno de " + jugadorActual.getNombre());

			Carta carta = null;
			
			while (true) {
			
				carta = jugadorActual.echarCarta(this.mesa);
				
				try { 
					this.mesa.ponerCarta(carta);
					
					if (carta == null) {
						System.out.println(jugadorActual.getNombre() + " se pasa");
					} else {
						System.out.println(jugadorActual.getNombre() + " echa " + carta);				
					}
					
					break;
				} catch (CartaNoValida e) {
					jugadorActual.addCarta(carta);
				}
				
			} 

			notificar(jugadorActual, carta);
			
			if (jugadorActual.getCartas().getCartas().isEmpty()) {
				System.out.println("ha ganado " + jugadorActual.getNombre());
				break;
			}
		
			
			pasarTurno();
		}
	}
	
}

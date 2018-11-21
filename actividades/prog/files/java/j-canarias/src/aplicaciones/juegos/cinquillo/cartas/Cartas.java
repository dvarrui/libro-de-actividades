package juegos.cinquillo.cartas;

import java.util.ArrayList;
import java.util.List;

public class Cartas {
	private List<Carta> cartas;

	public Cartas() {
		this.cartas = new ArrayList<Carta>();
	}
	
	public List<Carta> getCartas() {
		return cartas;
	}
	
	public void addCarta(Carta carta) {
		this.cartas.add(carta);
	}

	protected int buscarPosicion(Carta carta) {
		int pos = 0;
		for (; (pos < cartas.size()) && (cartas.get(pos).compareTo(carta) < 0); pos++);
		return pos;
	}
	
	public void addCartaOrdenada(Carta carta) {
		int pos = buscarPosicion(carta); 
		this.cartas.add(pos, carta);
	}
	
	public void removeCarta(Carta carta) {
		this.cartas.remove(carta);
	}
	
	public Carta getPrimera() {
		if (this.cartas.isEmpty()) return null;
		return this.cartas.get(0);
	}

	public Carta getUltima() {
		if (this.cartas.isEmpty()) return null;
		return this.cartas.get(this.cartas.size() - 1);
	}
	
	public String toString(String tabs) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(tabs + "Cartas {\n");
		for (Carta carta : this.cartas) {
			buffer.append(carta.toString(tabs + "\t") + "\n");
		}
		buffer.append(tabs + "}\n");
		return buffer.toString();
	}
	
        @Override
	public String toString() {
		return toString("");
	}
	
}

package juegos.cinquillo.cartas;

public class Carta implements Comparable<Carta> {
	private Integer numero;
	private Palo palo;
	
	public Carta(Integer numero, Palo palo) {
		this.numero = numero;
		this.palo = palo;
	}
	
	public Integer getNumero() {
		return numero;
	}
	
	public Palo getPalo() {
		return palo;
	}

	public int compareTo(Carta carta) {
		
		// compara los palos 
		int rPalos = this.getPalo().compareTo(carta.getPalo()); 
		if (rPalos != 0) return rPalos;
		
		// compara la numeraci�n
		int rNumeracion = this.getNumero().compareTo(carta.getNumero());
		if (rNumeracion != 0) return rNumeracion;
		
		return 0;
	}
	
	public String toString(String tabs) {
		switch (this.numero) {
			case 1: return tabs + "AS de " + this.palo;
			case 10: return tabs + "SOTA de " + this.palo;
			case 11: return tabs + "CABALLO de " + this.palo;
			case 12: return tabs + "REY de " + this.palo;
			default: return tabs + this.numero + " de " + this.palo;
		}
	}
	
	public Boolean esSiguiente(Carta carta) {
		return ((getPalo().equals(carta.getPalo()) && (getNumero() == carta.getNumero() + 1)));
	}

	public Boolean esAnterior(Carta carta) {
		return ((getPalo().equals(carta.getPalo()) && (getNumero() == carta.getNumero() - 1)));
	}
	
       @Override
	public String toString() {
		return toString("");
	}
	
	
}

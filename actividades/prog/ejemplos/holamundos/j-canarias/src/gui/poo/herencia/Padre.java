package gui.poo.herencia;

public class Padre extends Abuelo {
	
	Padre(String nombre) {
		super(nombre);
		this.tipo="Padre";
	}

	public void verMisGustos() {
		System.out.println(getNombre()+": me gustan los Rolling Stones");
	}

}

package gui.poo.herencia;

public class Hijo extends Padre {

	public Hijo(String nombre) {
		super(nombre);
		this.tipo="Hijo";
	}

	public void verMisGustos() {
		System.out.println(getNombre()+": me gusta Jet");
	}
}

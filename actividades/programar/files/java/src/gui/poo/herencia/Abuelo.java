package gui.poo.herencia;

public class Abuelo {
	
	private String nombre;
	protected String tipo;
	
	Abuelo(String nombre) {
		this.nombre = nombre;
		this.tipo = "Abuelo";
	}
	
	public String getTipo() {
		return "Abuelo";
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void mostrar() {
		System.out.println("Soy el "+tipo+" "+nombre);
	}
	
	public void verMisGustos() {
		System.out.println(getNombre()+": me gusta ABBA");
	}

}

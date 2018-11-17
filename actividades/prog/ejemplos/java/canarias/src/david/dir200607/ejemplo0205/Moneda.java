package david.dir200607.ejemplo0205;

public class Moneda {
	private String nombre;
	private double valorEnEuros;
	
	public Moneda(String nombre, double valorEnEuros) {
		this.nombre=nombre;
		this.valorEnEuros=valorEnEuros;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getValorEnEuros() {
		return valorEnEuros;
	}
	public void setValorEnEuros(double valorEnEuros) {
		this.valorEnEuros = valorEnEuros;
	}
	
}

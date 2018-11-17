package david.dir200607.ejemploUML;


public class Cafetera {
	Cafe cafe;
	Camarero camarero;
	
	public Cafetera() {
		cafe = new Cafe();
	}
	
	public Cafe preparado() {
		System.out.println(this.getClass().getName()+" preparado(); Se devuelve café");
		return this.cafe;
	}
	
	public void setCamarero(Camarero camarero) {
		this.camarero=camarero;
	}
}

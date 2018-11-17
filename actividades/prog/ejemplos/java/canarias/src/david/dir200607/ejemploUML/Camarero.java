package david.dir200607.ejemploUML;


public class Camarero {
	Cafetera cafetera;
	
	public Cafe pedido() {
		System.out.println(this.getClass().getName()+".pedido()");
		Cafe cafe = cafetera.preparado();
		System.out.println("Se devuelve café");
		return cafe;
	}
	
	public void setCafetera(Cafetera cafetera) {
		this.cafetera=cafetera;
	}
}

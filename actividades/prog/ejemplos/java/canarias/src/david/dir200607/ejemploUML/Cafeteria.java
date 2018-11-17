package david.dir200607.ejemploUML;


public class Cafeteria {
	Camarero camarero;
	Cafetera cafetera;
	
	public Cafeteria() {
		camarero = new Camarero();
		cafetera = new Cafetera();
		
		camarero.setCafetera(cafetera);
		cafetera.setCamarero(camarero);
	}
	
	public Camarero entro() {
		System.out.println(this.getClass().getName()+".entro();Se devuelve el camarero");
		return this.camarero;
	}
}

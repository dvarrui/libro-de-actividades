package david.dir200607.ejemploUML;


public class Cliente {
	Cafeteria cafeteria;
	Camarero camarero;
	
	public void entrar(Cafeteria cafeteria) {
		this.cafeteria=cafeteria;
		System.out.println(this.getClass().getName()+".entrar()");
		camarero=this.cafeteria.entro();
	}
	
	public void pedir() {
		System.out.println(this.getClass().getName()+".pedir()");
		camarero.pedido();
		this.beber();
	}
	
	public void beber() {
		System.out.println(this.getClass().getName()+".beber()");
	}
	
	public static void main(String args[]) {
		Cliente cliente = new Cliente();
		Cafeteria cafeteria = new Cafeteria();
		cliente.entrar(cafeteria);
		cliente.pedir();
		
	}
}

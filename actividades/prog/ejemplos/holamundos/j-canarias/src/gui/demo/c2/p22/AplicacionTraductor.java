package gui.demo.c2.p22;

//import gui.demo.nivel21.Traductor;

public class AplicacionTraductor {
	public AplicacionTraductor() {
		new VentanaTraductor(new Traductor());
	}
	
	public static void main(String[] args) {
		new AplicacionTraductor();
	}
}

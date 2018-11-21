package gui.demo.c2.p27;

import gui.demo.c2.p21.Traductor;

public class AplicacionTraductor {
	public AplicacionTraductor() {
		new VentanaTraductor(new Traductor());
	}
	
	public static void main(String[] args) {
		new AplicacionTraductor();
	}
}

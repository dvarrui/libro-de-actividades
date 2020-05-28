package gui.poo.herencia;

public class TestHerencia {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Abuelo a1 = new Abuelo("Floro");
		Padre p1 = new Padre("José");
		Hijo h1 = new Hijo("Jonathan");
		
		Abuelo v[] = new Abuelo[3];
		v[0]=a1;
		v[1]=p1;
		v[2]=h1;
		
		for(int i=0;i<v.length;i++) v[i].mostrar();
		for(int i=0;i<v.length;i++) v[i].verMisGustos();

	}

}

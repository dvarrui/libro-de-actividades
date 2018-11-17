package cui.holamundo;

public class HolaMundo5 {

	public void saludar(int n) {
		for(int i=0;i<n;i++) 
			System.out.println("Hola Mundo 5!");
	}
	
	public static void main(String[] args) {
		HolaMundo5 h;
		h= new HolaMundo5();
		h.saludar(5);
	}

}

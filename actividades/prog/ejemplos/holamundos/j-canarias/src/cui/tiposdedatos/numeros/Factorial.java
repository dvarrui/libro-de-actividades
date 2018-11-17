package cui.tiposdedatos.numeros;

public class Factorial {
	public static long calcularFactorial(int pNumero) {
		long solucion = 1;

		for (int i = 1; i <= pNumero; i++) {
			solucion = solucion * (long) i;
		}
		return solucion;
	}

	public static void main(String args[]) {
		System.out.print("Factorial de 3=");
		System.out.println(Factorial.calcularFactorial(3));
	}
}

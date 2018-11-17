package david.dir200607.ejemplo0201;

public class FuncionesMatematicas {

	public static long calcularFactorial(long numero) {
		long resultado = 1;
		
		if (numero==0) return 1;
		if (numero<0) return -1;
		
		for (long i=numero;i>1;i--) {
			resultado = resultado * i;
		}
		return resultado;
	}
}

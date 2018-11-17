package david.dir200607.ejemplo0202;

public class FuncionesDeCadenas {

	public static String invertirCadena(String entrada) {
		String salida = "";
		
		for (int i=0;i<entrada.length();i++) {
			salida = entrada.substring(i, i+1)+salida;
		}

		return salida;
	}
}

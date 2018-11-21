package cui.tiposdedatos.cadenas;
import cui.tiposdedatos.cadenas.Cadena;
/**
 * @author David Vargas Ruiz <dvargas@canarias.org>
 *
 */
public class TestCadena {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String str = new String("Hola Mundo!");
		Cadena cadena = new Cadena();
		
		System.out.println("Origen   : "+str);
		//Invertir cadena
		//System.out.println("Invertir : "+cadena.invertirCadena(str));
		System.out.println("César    : "+cadena.criptografiarCesar(str));
		
		String str2 = new String(cadena.rotarIzquierda(str.toCharArray()));
		System.out.println("RotarIzq.: "+str2);
		String str3 = new String(cadena.rotarIzquierda(str.toCharArray(),3));
		System.out.println("RotarIzq3: "+str3);
		
		System.out.println("César2   : "+cadena.criptografiarDesplazamiento2(str,3));
		
		cadena.banner("Probando el banner","*",1);
	}

}

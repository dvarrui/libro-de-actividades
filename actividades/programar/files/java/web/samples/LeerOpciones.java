/**
 * Author: David Vargas Ruiz <dvargas@canarias.org>
 */
package web.samples;

//import java.util.*;

/*
 * Un ejemplo de lectura e interpretación de parámetros al estilo script UNIX
 * Para usarlo copiar la clase y adaptarla según las necesidades.
 */
public class LeerOpciones {
	public static boolean isArgumento(String p) {
		if (p.startsWith("-"))
			return true;
		return false;
	}

	public static void main(String args[]) {
		try {
			String str;
			// Bucle para leer todas las opciones
			System.out.println(" [ OK ] Iniciando lectura de argumentos");
			for (int i = 0; i < args.length; i++) {
				str = args[i];

				if (!isArgumento(str))
					System.err.println(" [Error] Error argumento " + str);

				//Ejecutar la acción correspondiente a cada parámetro
				if (str.equals("-i")) {
					System.out.println(" * Opción = " + str);
					if (!isArgumento(args[i+1]))
							System.out.println("   Parámetro="+args[++i]);
					else 
						System.err.println(" [Error] Falta argumento en " + str);
				} else if (str.equals("-n")) {
					System.out.println(" * Opción = " + str+" sin parámetros");

				} else if (str.equals("-o")) {
					System.out.println(" * Opción = " + str);
					if (!isArgumento(args[i+1]))
						System.out.println("   Parámetro="+args[++i]);
				else 
					System.err.println(" [Error] Falta argumento en " + str);
				} else {
					System.out.println(" * Opción = " + str +" parámetro no controlado");
				}
			}
		} catch (Exception ex) {
			System.err.println(" [Error] " + ex);
		}
		//Se ejecuta la acción principal después de haber procesado todos los parámetros
		System.out.println(" [ OK ] Ejecutar programa");
	}
}

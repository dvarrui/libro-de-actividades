package david.tenerife.util;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Ejecuta una serie de acciones (comandos del SO) contenidas en un archivo en
 * texo plano
 * 
 * @author David Vargas Ruiz
 * @version 1.0.0 - 20060109
 * 
 */
public class EjecutarAcciones {

	private String fichero;

	public EjecutarAcciones(String pFichero) {
		fichero = pFichero;
	}

	public boolean ejecutar() {
		boolean resultado = true;
		String str;
		int i = 0;

		try {
			BufferedReader entrada = new BufferedReader(new FileReader(fichero));
			str = entrada.readLine();
			while (str != null) {
				if (!str.trim().startsWith("#") && str.trim().length()>0) {
					// Ejecutar acción
					System.out.println(" [INFO] Ejecutando..." + str);
					// i = ExecAndPrint.run(str.trim());
					Runtime r = Runtime.getRuntime();
					Process p = r.exec(str.trim());

					try {
						p.waitFor(); // wait for process to complete
					} catch (InterruptedException e) {
						resultado = false;
						;
					}
					i = p.exitValue();

					// System.in.read();
					// 0 = OK, 1 = error
					if (i != 0) {
						resultado = false;
					}
				}
				str = entrada.readLine();
			}
			System.out.println(" [INFO] Fin ejecución...");
			entrada.close();
		} catch (Exception e) {
			System.err.println("Error:" + e);
		}
		return resultado;
	}
}

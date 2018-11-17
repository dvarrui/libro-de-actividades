package david.tenerife.ui;

import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import david.tenerife.lib.BaseDatos;
import david.tenerife.lib.EntradaDefinicion;
import david.tenerife.lib.Generador;
import david.tenerife.lib.Parametros;
import david.tenerife.util.EjecutarAcciones;

/**
 * Copiado de : com.david.samples.LeerOpciones Descripción : Un ejemplo de
 * lectura e interpretación de parámetros al estilo script UNIX Para usarlo
 * copiar la clase y adaptarla según las necesidades.
 * 
 * @author David Vargas Ruiz
 * @version 1.0.0 - 20051230
 */
public class LeerOpciones {
	private static Logger logger = Logger.getLogger(LeerOpciones.class);

	public static boolean isArgumento(String p) {
		if (p.startsWith("-"))
			return true;
		return false;
	}

	public static void main(String args[]) {
		// Configuración del logger desde un fichero properties
		// El logger es del tipo FileRollerAppend
		String resource = "/david/tenerife/rec/logger.properties";
		URL configFileResource = LeerOpciones.class.getResource(resource);
		PropertyConfigurator.configure(configFileResource);

		// Add a bunch of logging statements ...
		// logger.debug("Hello, my name is Homer Simpson.");
		// logger.warn("Mmm...forbidden donut.");
		logger.info("----------------------------------------------------");
		logger.info("Se inicia una nueva ejecución");

		// Declaración de variables
		String str;
		Parametros param = new Parametros();

		// Se procesan todos los parámetros
		try {
			// Bucle para leer todas las opciones
			for (int i = 0; i < args.length; i++) {
				str = args[i];

				if (!isArgumento(str))
					System.err.println(" [Error] Error argumento " + str);
				else if (str.equals("-f")) {
					// Fichero de definiciones
					if (!isArgumento(args[i + 1]))
						param.setValor("file.config", args[i + 1]);
					else
						System.err
								.println(" [Error] Falta argumento en " + str);

				} else if (str.equals("-i")) {
					// Activar entrada desde fichero o base datos

					if (!isArgumento(args[i + 1])) {
						param.setValor("input.mode", args[i + 1]);
					} else
						System.err
								.println(" [Error] Falta argumento en " + str);
				} else if (str.equals("-d")) {
					// Directorio de salida del código
					if (!isArgumento(args[i + 1]))
						param.setValor("dir.salida", args[i + 1]);
					else
						System.err
								.println(" [Error] Falta argumento en " + str);
				} else {
					// Argumento no incluido
					System.out.println(" * Opción = " + str
							+ " parámetro no controlado");
				}
			}
		} catch (Exception ex) {
			logger.error(ex);
		}
		// Se ejecuta la acción principal después de
		// haber procesado todos los parámetros
		System.out.println(" [ OK ] Iniciar ejecución");

		// Se realiza la lectura de la definición de entrada
		BaseDatos bd;
		bd = EntradaDefinicion.ejecutar(param);

		// Se ejecutan los generadores de código
		Generador g = new Generador(bd, param);
		g.generarCodigo();

		// Ejecutar acciones al final del proyecto
		EjecutarAcciones e = new EjecutarAcciones(
				Parametros.DIR_BASE+"david/tenerife/rec/acciones.ejecutar");
		e.ejecutar();

		System.out.println(" [ OK ] Proceso finalizado");
	}
}

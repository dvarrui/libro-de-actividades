package david.gomera.lib;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author David Vargas Ruiz
 * @version 1.0.0 - 20051023
 */
public class Parametros {
	public final static String CODE_SALIDA = "code.salida";

	public final static String DIR_SALIDA = "dir.salida";

	public final static String FILE_CONFIG = "file.config";

	public final static String INPUT_MODE = "input.mode";
	
	public final static String JAVA = "java";
	
	public final static String PHP = "php";

	public final static String PROYECTO = "proyecto.name";

	public final static String valoresCS = "["+JAVA+"]["+PHP+"][all]";

	public final static String valoresIM = "[prueba][fichero][basedatos]";

	HashMap defecto; // Parámetros por defecto

	ArrayList errores; // Lista de errores encontrados en los parámetros

	HashMap param; // Parámetros definidos por el usuario

	public Parametros() {
		param = new HashMap();

		// Inicializar parámetros
		param.put(FILE_CONFIG, "david.tenerife.rec.inicio.properties");
		param.put(INPUT_MODE, "prueba");
		param.put(DIR_SALIDA, "tmp");
		param.put(CODE_SALIDA, "java");
		param.put(PROYECTO, "david.gomera.proyecto");

		defecto = (HashMap) param.clone();
		errores = new ArrayList();
	}

	public String getValor(String clave) {
		return (String) param.get(clave);
	}

	public boolean hayErrores() {
		if (errores.size() > 0)
			return true;
		return false;
	}

	public void mostrarErrores() {
		if (hayErrores())
			System.err.println("[ERROR] Errores de los PARAMETROS");
		for (int i = 0; i < errores.size(); i++)
			System.err.println("        * " + (String) errores.get(i));
	}

	public void setValor(String clave, String valor) {
		if (clave.equals(CODE_SALIDA))
			setValorCodeSalida(valor);
		else if (clave.equals(DIR_SALIDA))
			setValorDirSalida(valor);
		else if (clave.equals(FILE_CONFIG))
			setValorFileConfig(valor);
		else if (clave.equals(INPUT_MODE))
			setValorInputMode(valor);
		else if (clave.equals(PROYECTO))
			setValorProyecto(valor);
	}

	private void setValorCodeSalida(String valor) {
		if (valoresCS.indexOf("[" + valor + "]") < 0)
			errores.add(CODE_SALIDA + " = " + valor
					+ " (Asignación incorrecta)");
		else {
			param.remove(CODE_SALIDA);
			param.put(CODE_SALIDA, valor);
		}
	}

	private void setValorDirSalida(String valor) {
		// Comprobar y/o crear directorio de salida
		param.remove(DIR_SALIDA);
		param.put(DIR_SALIDA, valor);

	}

	/**
	 * Establecer el fichero de configuración
	 * 
	 * @param clave
	 * @param valor
	 */
	private void setValorFileConfig(String valor) {
		// Comprobar si existe el fichero antes de modificar valor
		param.remove(FILE_CONFIG);
		param.put(FILE_CONFIG, valor);

	}

	private void setValorInputMode(String valor) {
		if (valoresIM.indexOf("[" + valor + "]") < 0)
			errores
					.add(INPUT_MODE + " = " + valor
							+ " (Asignación incorrecta)");
		else {
			param.remove(INPUT_MODE);
			param.put(INPUT_MODE, valor);
		}
	}

	private void setValorProyecto(String valor) {
		// Comprobar si existe el fichero antes de modificar valor
		param.remove(PROYECTO);
		param.put(PROYECTO, valor);

	}

}

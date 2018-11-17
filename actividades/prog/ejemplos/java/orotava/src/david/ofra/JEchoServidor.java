package david.ofra;

import java.util.HashMap;
import java.util.Properties;

public class JEchoServidor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Properties propiedades = System.getProperties();
		propiedades.list(System.out);
		Runtime r = Runtime.getRuntime();
		System.out.println("FreeMemory  = "+r.freeMemory());
		System.out.println("MaxMemory   = "+r.maxMemory());
		System.out.println("TotalMemory = "+r.totalMemory());
		
		HashMap perfil = new HashMap(10);
		perfil.put("os.name",propiedades.get("os.name"));
		perfil.put("user.name",propiedades.get("user.name"));
	}

}

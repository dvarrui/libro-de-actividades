package hilos.hosts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
//import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Esta clase realiza el control de una serie de HOST.
 * 
 * @author David Vargas Ruiz
 * @version 1.0.0 - 20060128
 * 
 */
public class ControlHosts {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ControlHosts h = new ControlHosts("datos/hosts.txt");
		try {
			for (int i = 1; i < 10; i++) {
				System.out.println(" * Iteración:" + i + " de " + 9);
				h.comprobarHosts();
				h.mostrarEnConsola();
				// minutos * segundos * milisegundos
				// 1 * 10 * 1000 = 10 segundos
				// 1 * 60 * 1000 = 1 minuto
				// 10 * 60 * 1000 = 10 minutos
				Thread.sleep(1 * 2 * 1000);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private ResourceBundle configuracion;
	private String fichero; // Nombre del fichero que contiene la lista de host
	private ArrayList<Host> hosts; // Array con la lista de objetos Host

	public ControlHosts() {
		fichero = null;
		hosts = null;
		//configuracion = ResourceBundle.getBundle("ControlHost", Locale.getDefault());
	}

	public ControlHosts(String pFichero) {
		this.setFicheroHost(pFichero);
		//configuracion = ResourceBundle.getBundle("datos/ControlHost.properties", Locale.getDefault());
	}

	public void comprobarHosts() {
		Host h;
		int s;
		String str;

		try {
			for (int i = 0; i < hosts.size(); i++) {
				h = (Host) hosts.get(i);

				// Ejecutar PING
				// TODO: Ampliarlo para poder hacer ping en cualquier SO
				str = "ping " + h.getIp() + " -c 1";
				Runtime r = Runtime.getRuntime();
				Process p = r.exec(str.trim());

				try {
					p.waitFor(); // wait for process to complete
				} catch (InterruptedException e) {
					System.err.println(" [ERROR] " + str);
				}
				s = p.exitValue();

				// El valor devuelto por la ejecución del comando será:
				// 0 = OK, 1 = error
				if (s == 0) {
					h.setPing(true);
				} else {
					h.setPing(false);
				}
			}
		} catch (Exception e) {
			System.err.println("Error:" + e);
		}
	}

	public String getFicheroHost() {
		return fichero;
	}

	public Host getHost(int index) {
		return (Host) hosts.get(index);
	}

	public int getNumHosts() {
		return hosts.size();
	}

	private void leerDatos() {
		BufferedReader entrada;
		String str;
		hosts = new ArrayList<Host>();
		Host h;

		try {
			entrada = new BufferedReader(new FileReader(fichero));
			str = entrada.readLine();
			while (str != null) {
				if (!str.startsWith("#")) {
					h = new Host();
					h.fromCadena(str);
					hosts.add(h);
				}
				str = entrada.readLine();
			}
			entrada.close();
		} catch (Exception e) {
			System.err.println("[ERROR] " + e);
		}
	}

	public void mostrarEnConsola() {
		Host h;
		for (int i = 0; i < hosts.size(); i++) {
			h = (Host) hosts.get(i);
			System.out.println(h.toCadena() + "\t -> \t" + h.isPing());
		}
	}

	public void refrescar() {
		this.leerDatos();
	}

	public void setFicheroHost(String fichero) {
		this.fichero = fichero;
		this.leerDatos();
	}

	public int getTiempoMuestreo() {
		return Integer.parseInt(configuracion.getString("tiempoMuestreo"));
	}
}

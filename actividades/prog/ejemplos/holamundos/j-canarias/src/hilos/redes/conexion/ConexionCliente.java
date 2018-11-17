package hilos.redes.conexion;

import java.net.*;

/**
 * Esta clase sirve para probar la conexión a un {host:puerto} determinado.
 * De esta forma se puede comprobar si los servicios están activos.
 * 
 * @author David Vargas Ruiz
 *
 */
public class ConexionCliente {
	

	public static void probarConexion(String host, int puerto) {
		try {
			Socket sock = new Socket(host,puerto);
			System.out.println("\nConexión  ["+host+":"+puerto+"] establecida");
			
			sock.close();
		} catch(java.io.IOException e) {
			System.err.println(" Error conectando con ["+host+":"+puerto+"] : "+e);
		}
	}


	public static void main(String[] args) {
		ConexionCliente.probarConexion("localhost",10013);
		ConexionCliente.probarConexion("localhost",80);
		ConexionCliente.probarConexion("localhost",8080);
		ConexionCliente.probarConexion("localhost",9999);
	}

}

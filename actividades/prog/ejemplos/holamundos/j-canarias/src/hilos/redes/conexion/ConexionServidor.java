package hilos.redes.conexion;

import java.net.*;
import java.io.*;

public class ConexionServidor {

	public static final int PUERTO=9999;

	static void procesar (Socket s) throws IOException {
		System.out.println(" Conexión aceptada desde "+s.getInetAddress());
		//Conversación
		System.out.println(" Conexión cerrada con "+s.getInetAddress());
		s.close();
	}
	
	public static void main(String[] args) {
		ServerSocket servidor;
		Socket cliente;
		try {
			servidor = new ServerSocket(PUERTO);
			while ((cliente=servidor.accept())!=null) {
				procesar(cliente);
			} 
		} catch (IOException e) {
			System.err.println(" [ERROR] "+e);
		}
	}
}

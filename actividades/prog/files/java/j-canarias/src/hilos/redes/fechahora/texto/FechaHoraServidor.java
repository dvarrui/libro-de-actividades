package hilos.redes.fechahora.texto;

import java.net.*;
import java.io.*;

public class FechaHoraServidor {

	ServerSocket sock;
	public final static int PUERTO=10013;

	public FechaHoraServidor(int puerto) {
		try {
			sock = new ServerSocket(puerto);
		} catch (IOException e) {
			System.err.println(" [ERROR] FechaHoraServidor :"+e);
			System.exit(1);
		}
	}
	
	protected void runServicio() {
		Socket ios=null;
		DataOutputStream os = null;
		while(true) {
			try {
				System.out.println("Iniciado el Servicio FechaHora");
				System.out.println("Esperando conexiones en el puerto "+PUERTO);
				ios=sock.accept();
				
				System.err.println("Aceptada petición desde "+ios.getInetAddress().getHostName());
				os = new DataOutputStream(ios.getOutputStream());
				
				os.writeChars(new java.util.Date().toString());

				os.close();
			} catch(IOException e) {
				System.err.println(" [ERROR] "+e);
			}
		}
	}
	
	public static void main(String[] args) {
		FechaHoraServidor s = new FechaHoraServidor(PUERTO);
		s.runServicio();
		

	}

}

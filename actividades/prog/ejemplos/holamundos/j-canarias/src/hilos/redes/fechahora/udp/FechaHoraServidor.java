package hilos.redes.fechahora.udp;

import java.net.*;
import java.io.*;

public class FechaHoraServidor {

	ServerSocket sock;
	public final static int PUERTO=10013;
	public final static int PAQUETE_SIZE=100;

	public FechaHoraServidor(int puerto) {
		try {
			sock = new ServerSocket(puerto);
		} catch (IOException e) {
			System.err.println(" [ERROR] FechaHoraServidor :"+e);
			System.exit(1);
		}
	}
	
	protected void runServicio() {
		Socket remoto=null;

		while(true) {
			try {
				System.out.println("Iniciado el Servicio FechaHora");
				System.out.println("Esperando conexiones en el puerto "+PUERTO);
				remoto=sock.accept();
				
				System.err.println("Aceptada petición desde "+remoto.getInetAddress().getHostName());

				byte[] buffer = new byte[PAQUETE_SIZE];
				DatagramPacket packet = new DatagramPacket(
						buffer, PAQUETE_SIZE,remoto.getInetAddress(), PUERTO);
				
				packet.setLength(PAQUETE_SIZE-1);

				/*System.out.println(" [INFO] Enviado paquete de peticion...");
				sock.send(packet);
				System.out.println(" [INFO] Esperando paquete de respuesta...");
				sock.receive(packet);				
				System.out.println("FechaHora en "+host+" es "+new String(buffer,0,packet.getLength()));
				*/
			
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

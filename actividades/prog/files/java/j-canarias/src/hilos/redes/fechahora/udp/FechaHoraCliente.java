package hilos.redes.fechahora.udp;

import java.io.*;
import java.net.*;


public class FechaHoraCliente {

	public final static int PUERTO=10013;
	protected final static int PAQUETE_SIZE=100;
	
	public static void main(String[] args) throws IOException {
		String host = "localhost";
		
		if (args.length>0) {
			host=args[0];
		}
		
		
		InetAddress servAddr = InetAddress.getByName(host);
		DatagramSocket sock = new DatagramSocket();
		
		byte[] buffer = new byte[PAQUETE_SIZE];
		DatagramPacket packet = new DatagramPacket(
				buffer, PAQUETE_SIZE, servAddr, PUERTO);
		
		packet.setLength(PAQUETE_SIZE-1);
		System.out.println(" [INFO] Enviado paquete de peticion...");
		sock.send(packet);
		
		System.out.println(" [INFO] Esperando paquete de respuesta...");
		sock.receive(packet);
		
		System.out.println("FechaHora en "+host+" es "+new String(buffer,0,packet.getLength()));
	}

}

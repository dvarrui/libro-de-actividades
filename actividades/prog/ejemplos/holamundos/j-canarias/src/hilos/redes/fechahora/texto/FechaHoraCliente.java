package hilos.redes.fechahora.texto;

import java.net.*;
import java.io.*;

public class FechaHoraCliente {

	public static final int PUERTO=10013;

	public static void main(String[] args) {
		String host = "localhost";
		
		if (args.length==1) host=args[0];
		
		try {
			Socket sock = new Socket(host,PUERTO);
			BufferedReader is = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			String mensaje = is.readLine();
			System.out.println("FechaHora en "+host+" es "+mensaje);
		} catch (IOException e) {
			System.err.println(" [ERROR] "+e);
		}

	}

}

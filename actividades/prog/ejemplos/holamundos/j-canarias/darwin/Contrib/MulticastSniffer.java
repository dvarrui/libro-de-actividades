package Contrib;

import java.net.*;
import java.io.*;

/** Multicast Sniffer, from Elliotte Harold's book 
 * Java Network Programming, by O'Reilly & Associates.
 * Page 342, first edition.
 */

public class MulticastSniffer {
	public static void main(String[] args) {
		InetAddress ia = null;
		byte[] buffer = new byte[65535];
		DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
		int port = 0;

		// read the address from the command line
		try {
			try {
				ia = InetAddress.getByName(args[0]);
			} catch (UnknownHostException e) {
				System.err.println(e);
			}
			port = Integer.parseInt(args[1]);
		} catch (Exception e) {
			System.err.println(e);
			System.err.println("Usage: java MulticastSniffer mcast-addr port");
			System.exit(1);
		}
		System.out.println("About to join " + ia);

		try {
			MulticastSocket ms = new MulticastSocket(port);
			ms.joinGroup(ia);
			while (true) {
				System.out.println("About to receive...");
				ms.receive(dp);
				String s = new String(dp.getData(), 0, 0, dp.getLength());
				System.out.println(s);
			}
		} catch(SocketException se) {
			System.err.println(se);
		} catch(IOException ie) {
			System.err.println(ie);
		}
	}
}

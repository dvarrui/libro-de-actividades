package cajas.ficheros;

import java.io.*;

/**
 * CheckSum - Muestra el valor checksum de uno o varios ficheros
 * 
 * @author David Vargas Ruiz
 * @version 1.0.0 - 20051029
 */

public class CheckSum {
	public static void main(String[] av) {
		CheckSum c = new CheckSum();
		int sum = 0;
		if (av.length == 0)
			System.out.println("No se han pasado argumentos!");
		else {
			for (int i = 0; i < av.length; i++)
				try {
					sum += c
							.procesar(new BufferedReader(new FileReader(av[i])));
				} catch (FileNotFoundException e) {
					System.err.println(e);
				}
			System.out.println(sum);
		}

	}

	/**
	 * CheckSum one text file, given an open BufferedReader. Checksumm does not
	 * include line endings, so will give the same value for given text on any
	 * platform. Do not use on binary files!
	 */
	public int procesar(BufferedReader is) {
		int sum = 0;
		try {
			String inputLine;

			while ((inputLine = is.readLine()) != null) {
				int i;
				for (i = 0; i < inputLine.length(); i++) {
					sum += inputLine.charAt(i);
				}
			}
			is.close();
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}
		return sum;
	}

	public int procesar(String str) {
		int sum = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(str));
			sum = procesar(br);
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}
		return sum;
	}
}

package cui.tiposdedatos.numeros;
import java.io.*;

import cui.tiposdedatos.numeros.Factorial;

public class TestFactorial
{
	public static void main(String args[])
	{
		BufferedReader br;
		String str;
		int numero;

		try
		{
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Introduce un número:");
			str = br.readLine();
			numero = Integer.parseInt(str);
			System.out.println(Factorial.calcularFactorial(numero));
		}
		catch(Exception e)
		{ System.err.println(e);}
	
	}
	
}

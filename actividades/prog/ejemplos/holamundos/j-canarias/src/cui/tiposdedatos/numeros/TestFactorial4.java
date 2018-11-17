package cui.tiposdedatos.numeros;

import cui.tiposdedatos.numeros.Factorial;
import cui.io.Consola;

public class TestFactorial4
{
   public static void main(String args[])
   {
      int numero = Integer.parseInt(Consola.readLine());
      System.out.print("Factorial de "+numero+"=");
      long l = Factorial.calcularFactorial(numero);
      System.out.println(l);
   }
}

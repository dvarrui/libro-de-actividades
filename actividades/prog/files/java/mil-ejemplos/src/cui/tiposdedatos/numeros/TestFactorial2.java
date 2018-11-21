package cui.tiposdedatos.numeros;

//import java.io.*;

public class TestFactorial2
{
   public static void main(String args[])
   {
      try
      {
         int numero = Integer.parseInt(args[0]);
         long l = Factorial.calcularFactorial(numero);

         System.out.print("Factorial de "+numero+"=");
         System.out.println(l);
      }
      catch(Exception e) {System.err.println(e);}
   }
}

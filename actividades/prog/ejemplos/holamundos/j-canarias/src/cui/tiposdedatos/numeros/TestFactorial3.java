package cui.tiposdedatos.numeros;
import cui.tiposdedatos.numeros.Factorial;

//import java.io.*;

public class TestFactorial3
{
   public static void main(String args[])
   {
      try
      {
         int numero = System.in.read();
         System.out.print("Factorial de "+numero+"=");
         System.out.println(Factorial.calcularFactorial(numero));
      } 
      catch(Exception e) {System.err.println(e);}
   }
}

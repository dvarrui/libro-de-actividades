package cui.io;

import cui.io.Consola;
import cui.io.DibujarConsola;


public class TestDibujarConsola
{
   public static void main(String args[])
   {
      System.out.print("Introducir número:");
      int numero = Integer.parseInt(Consola.readLine());

      DibujarConsola.dibujarPiramide(numero,0);
      DibujarConsola.dibujarRombo(numero,10);
      DibujarConsola.dibujarRectangulo(5,12,3,false);
   }
}
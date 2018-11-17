package cui.io;

public class DibujarConsola
{

   //Dibujar pirámide en la consola
   public static void dibujarPiramide(int pNumFilas, int pDesplazamiento)
   {
      int numEspacios = pNumFilas-1 + pDesplazamiento;
      int numAsteriscos = 1;

      for(int i=0;i<pNumFilas;i++)
      {
         for(int j=0;j<numEspacios;j++)
            System.out.print(" ");
         for(int j=0;j<numAsteriscos;j++)
            System.out.print("*");
         System.out.println();
         numEspacios-=1;
         numAsteriscos+=2;
      }
   }

   //Dibujar pirámide invertida en consola
   public static void dibujarPiramideInvertida(int pNumFilas, int pDesplazamiento)
   {
      int numEspacios = 0 + pDesplazamiento;
      int numAsteriscos = (int) (pNumFilas*2-1);

      for(int i=0;i<pNumFilas;i++)
      {
         for(int j=0;j<numEspacios;j++)
            System.out.print(" ");
         for(int j=0;j<numAsteriscos;j++)
            System.out.print("*");
         System.out.println();
         numEspacios+=1;
         numAsteriscos-=2;
      }
   }

   //Dibujar rombo en consola
   public static void dibujarRombo(int pNumFilas, int pDesplazamiento)
   {
      DibujarConsola.dibujarPiramide(pNumFilas,pDesplazamiento);
      DibujarConsola.dibujarPiramideInvertida(pNumFilas-1,pDesplazamiento+1);
   }

   //Dibujar rectángulo en consola
   public static void dibujarRectangulo(int pAlto, int pLargo, 
                                        int pDesp, boolean pRelleno)
   {
      for(int i=0;i<pAlto;i++)
      {
         for(int j=0;j<pDesp;j++)
            System.out.print(" ");

         if (pRelleno || i==0 || i==pAlto-1)
         {
            for(int j=0;j<pLargo;j++)
               System.out.print("*");
         }
         else
         {
            System.out.print("*");
            for(int j=0;j<pLargo-2;j++)
               System.out.print(" ");
            System.out.print("*");
         }
         System.out.println();
      }
   }
}

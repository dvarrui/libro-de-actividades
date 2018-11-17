/* Paralelo.c */
/* Programa que manda por el puerto paralelo un dato para
   visiaulizar unos led externos, y tambi‚n acepta los datos
   de unos pulsadores externos

   Direcci¢n base de LPT1:    0x378
		     LPT2:    0x278

   Registros:   base + offset
		Datos:   base + 0
		Estado:	 base + 1
		Control: base + 2
	 El Registro de Datos tiene asignados los Pines
	    del 2 al 9 en ese orden.

 Los pulsadores ponen a 1 un bit del registro de estado.
 Pulsador 5:  Bit 7 = 1     Pin  11
	  4:      6              10
	  3:      5              12
	  2:	  4              13
	  1:      3              15
*/

#include <stdio.h>
#include <conio.h>
#include <dos.h>

#define LPT1 0x378    // Direcci¢n de LPT1
#define ESTADO 0x379  // Direcci¢n registro de estado

void main(void)
{
   int valor=0;
   int valor2=0;
   clrscr();
   while(!kbhit())
   {
       clrscr();
// Salida, env¡o de datos al puerto por el Registro de Datos

       outportb(LPT1, valor);
       printf(" Valor entero: %d , Binario: ", valor);
       printf(" %d ", (valor & 128)/128);
       printf(" %d ", (valor & 64)/64);
       printf(" %d ", (valor & 32)/32);
       printf(" %d ", (valor & 16)/16);
       printf(" %d ", (valor & 8)/8);
       printf(" %d ", (valor & 4)/4);
       printf(" %d ", (valor & 2)/2);
       printf(" %d ", (valor & 1)/1);
       valor=valor+1;

// Entrada de valores:  Lectura del puerto,  Pulsadores por el
//                      Registro de Estado

       valor2 = inportb(ESTADO);

       if((valor2 & 128) != 0)
	    printf("\n\t\tPulsador activado = 1");
       if((valor2 & 64) != 0)
	    printf("\n\t\tPulsador activado = 2");
       if((valor2 & 32) != 0)
	    printf("\n\t\tPulsador activado = 3");
       if((valor2 & 16) != 0)
	    printf("\n\t\tPulsador activado = 4");
       if((valor2 & 8) != 0)
	    printf("\n\t\tPulsador activado = 5");
       delay(2000);
   }
   valor=0;
   outportb(LPT1, valor);
}


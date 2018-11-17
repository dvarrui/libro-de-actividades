/***************************************************************
  Puerto3.c

 Prueba de comunicaci¢n serie entre 2 equipos.
 Si queremos probarlo con 2 equipos debemos hacer un cable serie
 de 9 pines cruzando los pines 2 y 3, a parte de poner el Bit de
 Bucle del registro MCR a 0.
 Est  hecho usando los comandos inportb y outportb que funcionan
 correctamente en MsDos y Windows,  pues el Bioscom falla con el
 Windows XP.
 Divide la pantalla en 2. La parte alta es la zona donde escribe
 el emisor,  y  la  baja es  la  zona  donde ve lo que recibe el
 receptor.
 Registros que usa:
   RBR: Registro de la UART de buffer del receptor
   THR: Buffer del transmisor
   LCR: Formato de datos
   LSR: Estado de l¡nea
   DLL y DLM: Velocidad de transmisi¢n.
 A diferencia de puerto2.c, en este programa usamos las asigna-
  ciones de valores a los registros usando campos de bits.
*****************************************************************/

#include <stdio.h>
#include <conio.h>
#include <dos.h>

// Funciona para COM1 y COM2

// Definimos las direcciones base de los puertos
#define COM1 0x3F8
#define COM2 0x2F8

// Definimos las variables para los registros de la UART
unsigned int RBR;
unsigned int THR;
unsigned int IER;
unsigned int IIR;
unsigned int LCR;
unsigned int MCR;
unsigned int LSR;
unsigned int MSR;
unsigned int DLL;
unsigned int DLM;

struct campo
{
   unsigned int cero: 1;
   unsigned int uno: 1;
   unsigned int dos: 1;
   unsigned int tres: 1;
   unsigned int cuatro: 1;
   unsigned int cinco: 1;
   unsigned int seis: 1;
   unsigned int siete: 1;
};

union campos
{
    unsigned int valor;
    struct campo bits;
};

union campos registro;



// Base apuntar  a la direcci¢n del puerto
unsigned int base;

// Funci¢n principal
void main(void)
{
    int ch, c, op;
    int fila1, fila2, col1, col2;
    fila1=1;
    fila2=12;
    col1=col2=1;
    registro.valor=0;
    clrscr();
    printf("\n\n  Introduzca COMM1 / COM2 (1 ¢ 2): ");
    scanf("%d", &op);
    if(op == 1)
       base = 0x3F8;
    else
       base = 0x2F8;

    RBR = base + 0x00;
    THR = base + 0x00;
    IER = base + 0x01;
    IIR = base + 0x02;
    LCR = base + 0x03;
    MCR = base + 0x04;
    LSR = base + 0x05;
    MSR = base + 0x06;
    DLL = base + 0x00;
    DLM = base + 0x01;

    // Inicializar el puerto
    registro.bits.siete=1;  // 0x80
    outportb(LCR, registro.valor);   // DLAB a 1 para acceder a DLL y DLM

    registro.bits.siete=0;    // 0x00
    outportb(DLL, registro.valor);

    registro.bits.uno=1;  // 0x02
    outportb(DLM, registro.valor);  // Velocidad a 57600 bps

    registro.bits.uno=1;
    registro.bits.cero=1;          // 0x03 | 0x00 | 0x00
    outportb(LCR, registro.valor);  // Datos, Stop, Paridad
    registro.bits.cero=0;
    registro.bits.uno=0;

     // Esta l¡nea s¢lo ser¡a necesaria si tuvi‚semos modem,
     // o si quisi‚ramos trabajar en modo bucle con un s¢lo
     // equipo.
     // outportb(MCR, 0x01 | 0x02);   //  DTR, RTS
      outportb(MCR, 0x01 | 0x02 | 0x10);  // DTR, RTS, Bucle.
     // Bucle necesario (0x10) si trabajamos con 1 s¢lo equipo
     // simulando 2.

    inportb(RBR);   // Limpiar buffer de entrada, lo leemos
		    // pero no lo guardamos, s¢lo vaciamos.

    clrscr();
    gotoxy(col2, fila2-2);
    printf("-----------------------------------------------");
    gotoxy(col1, fila1);

    do
    {
      c = inportb(LSR);
      // Si dato recibido  DLISTO bit 0 del registro LSR
      registro.bits.cero=1;  // 0x01
      if(c & registro.valor)
      {
	 ch = inportb(RBR);
	 if((ch == 10) || (ch == 13))  // Saltos de l¡nea
	 {
	   fila2++;
	   col2=1;
	 }
	 else if(ch != 27)   // Escape
	 {
	   gotoxy(col2,fila2);
	   printf("%c", ch);
	   col2++;
	   gotoxy(col1,fila1);
	 }
	 else
	 {
	   gotoxy(1, fila2+1);
	   printf("Fin de transmisi¢n en destino ");
	 }
      }
      if(kbhit())
      {
	 gotoxy(col1,fila1);
	 ch = getch();   // Para no ver el car cter 27 ESC
	 if((ch == 10) || (ch == 13))
	 {
	   fila1++;
	   col1=1;
	   gotoxy(col1,fila1);
	   outportb(THR, ch);
	 }
	 else if(ch != 27)
	 {
	   gotoxy(col1,fila1);
	   printf("%c",ch);
	   col1++;
	   outportb(THR, ch);
	 }
	 else
	 {
	    outportb(THR, ch);
	 }
      }
    } while(ch != 27);

    gotoxy(1, fila1+1);
    printf("Pulse una tecla para salir ");
    getch();
    clrscr();
}

/*
 Resultado de una ejecuci¢n

 hola que tal
 estamos
 hoy
 Pulse una tecla para salir

 --------------------------------------------------
 hola que tal
 estamos
 hoy
 Fin de transmisi¢n en destino


 En la parte alta de la pantalla vemos lo que nosotros
 escribimos, o sea lo que se env¡a al puerto de salida
 En la parte baja vemos lo que recibimos del puerto de
 entrada, se escribe desde el otro ordenador que tenga
 ejecutando este mismo programa.

*/

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

int main()
{
   setuid( 0 );
   system( "./setuid.sh" );

   return 0;
}
//gcc setuid.c -o setuid.exe


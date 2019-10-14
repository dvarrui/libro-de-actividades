
> ACLARACIÓN: Entiendo que C no entra bien en la categoría de lenguajes de scripting, ni lo prentendo. Sólo pongo material sobre cómo invocar comandos de shell desde un programa ejecutable a partir de código fuente C.

# Lenguage C

## Ejemplo myls1

Enlaces de interés:
* [Ejecutar comando de shell desde C](https://jgutierrez.wordpress.com/2008/01/30/ejecutar-comano-de-shell-desde-c/)
* [C library function - system](https://www.tutorialspoint.com/c_standard_library/c_function_system.htm)

Crear el fichero [myls1.c](files/myls1.c):
```
#include<stdio.h>

int main(){
  system("ls -l");
  return 0;
}
```

Para compilarlo hacemos: `gcc myls1.c -o myls1`. Obtenemos el ejecutable `myls1`.

---

## Ejemplo mysl2

Capturar el código de error que devuelve el comando. Si no es cero, entonces se muestra un mensaje en pantalla.

Crear el fichero [myls2.c](files/myls2.c):
```
#include<stdio.h>

int main(){
  int ret = system("ls -l");
  if (ret!=0) {
    printf("Error");
  }
  return 0;
}
```

---

## Ejemplo myls3

Compilación sin advertencias.

Crear el fichero [myls3.c](files/myls3.c):
```
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main () {
   char command[50];

   strcpy(command, "ls -l" );
   system(command);

   return(0);
}
```

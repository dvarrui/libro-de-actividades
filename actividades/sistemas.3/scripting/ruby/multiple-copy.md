```
Fecha cración : 20191019
Curso         : 201920
Materia       : Scripting
```
---

# Copiado múltiple

## Introducción

El comando `cp` (o `copy`) lo usamos para copiar archivos o directorios completos (modo recursivo). El comando require dos argumentos para funcionar:
* Origen: Fichero o directorio que queremos copiar.
* Destino: Fichero o directorio donde queremos dejar la copia.

Vamos a crear un script llamado `mulcopy` para realizar un copiado múltiple. Esto es, copiar un origen a varios destinos a la vez.

## Entrega

* Vamos a crear un script ruby llamado **mulcopy**.
* La entrega la realizaremos a través del repositorio Git.
* Al terminar etiquetaremos la entrega con *mulcopy*.

## Script

* Al comenzar el script se debe comprobar si el usuario que lo ejecuta es
`root` (if-then). En caso contrario se mostrara un mensaje y se finaliza el script con error (exit).
* Los datos de entrada al script estarán en los argumentos de entrada. Asegurarse de recibir como 2 argumentos o más en la entrada (Array ARGV).
* Nuestro script aceptará un número indeterminado de argumentos, donde:
    * 1er argumento: Fichero o directorio que queremos copiar.
    * Resto de argumentos: Ficheros o directorios donde queremos dejar la copia.
* El script debe actuar de forma NO interactiva. No debe hacer preguntas al usuario durante la ejecución.
* Al realizar cada acción comprobar si se ha ejecutado correctamente. En caso contrario, mostrar un mensaje y parar la ejecución del script.

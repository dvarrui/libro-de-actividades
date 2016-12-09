
# Instalar y desinstalar software

## Entrega

* Vamos a crear un script ruby llamado **install-and-remove.rb**.
* La entrega la realizaremos a través del repositorio Git.
* Al terminar etiquetaremos la entrega con *install-and-remove*.

## Script

* Al comenzar el script se debe comprobar si el usuario que lo ejecuta es
`root`. En caso contrario se mostrara un mensaje y se finaliza el script.
* Los datos de entrada al script estarán en un fichero *software-list.txt*,
con el formato `PACKAGENAME:ACTION`.
* El script deberá leer el contenido del fichero de entrada,
donde vendrán definidos los nombres de los paquetes. Deberá cargarlos en un array
para luego procesarlos.

> No incluir siguientes paquetes: `ruby` y `geany`.

* Para cada elemento del array deberemos ejecutar los comandos necesarios
para actuar sobre cada paquete en un sistema GNU/Linux.
    * Si `ACTION` es remove o r, entonces desinstalar el software.
    * Si `ACTION` es install o i, entonces instalar el software.
    * Si `ACTION` es status o s, entonces mostrar un mensaje (instalado o no instalado).

> PISTA para saber si tenemos un programa instalado o no...
>
> * `whereis PACKAGENAME |grep bin |wc -l`
> * `zypper se geany|grep 'i '|wc -l`
> * Si el resultado es 0 -> NO está instalado
> * Si el resultado es mayor que 0 -> SI está instalado 

* El script debe actuar de forma NO interactiva. No debe hacer preguntas al usuario
durante la ejecución.
* Antes de realizar cada acción comprobar:
    * Si la acción es "eliminar software" hay que comprobar si el paquete está instalado.
    * Si la acción es "instalar software" hay que comprobar si el paquete no está instalado.

## Enlaces de interés
* ¿Cómo crear [scripts que no requieran la intervencion del usuario]
 (https://es.opensuse.org/Scripts_sin_intervencion_del_usuario)?

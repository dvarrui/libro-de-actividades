
#Instalar y desinstalar software

##Entrega
* Vamos a crear un script ruby llamado **install-and-remove.rb**.
* La entrega la realizaremos a través del repositorio Git.
* Al terminar etiquetaremos la entrega con *install-and-remove*.

##Script
* Al comenzar el script se debe comprobar si el usuario que lo ejecuta es
`root`. En caso contrario se mostrara un mensaje y se finaliza el script.
* Los datos de entrada al script estarán en un fichero *software-list.txt*,
con el formato `package_name:action`.
* El script deberá leer el contenido del fichero de entrada, 
donde vendrán definidos los nombres de los paquetes. Deberá cargarlos en un array
para luego procesarlos.

> No elijan como los paquetes siguientes: geany, ruby

* Para cada elemento del array deberemos ejecutar los comandos necesarios
para actuar sobre cada paquete en un sistema GNU/Linux.
    * Si `action=remove o r` entonces se desinstala el software.
    * Si `action=install o i`, entonces se instala el software.
* El script debe actuar de forma NO interactiva. No debe preguntar decisiones
durante su ejecución.
* Antes de realizar cada acción comprobar:
    * Si la acción es eliminar software hay que comprobar si el paquete está instalado.
    * Si la acción es instalar software hay que comprobar si el paquete no está instalado.

##Enlaces de interés
* ¿Cómo crear [scripts que no requieran la intervencion del usuario]
 (https://es.opensuse.org/Scripts_sin_intervencion_del_usuario)?

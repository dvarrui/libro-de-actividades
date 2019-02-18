
# Crear y eliminar usuarios

## Entrega
* Vamos a crear un script ruby llamado **create-and-delete-users.rb**.
* La entrega la realizaremos a través del repositorio Git.
* Al terminar etiquetaremos la entrega con *create-and-delete-users*.

## Script
* Al comenzar el script se debe comprobar si el usuario que lo ejecuta es
`root`. En caso contrario se mostrara un mensaje y se finaliza el script.
* Los datos de los usuarios estarán en un fichero *userslist.txt*,
con el formato `username:lastname:email:birthday:action`.
* El script deberá leer el contenido de un fichero *userslist.txt*,
donde vendrán definidos los nombres de usuario, y deberá cargarlos en un array
para luego procesarlos.
* Para cada elemento del array deberemos ejecutar los comandos necesarios
para eliminar/crear dicho usuario en un sistema GNU/Linux.
    * Si `action=delete` entonces el usuario se elimina y
    * Si `action=add` el usuario se crea
    * Si el usuario no tiene email entonces se muestra un mensaje en pantalla
indicando el problema pero no se hace nada con él.

## Enlaces de interés
* ¿Cómo crear [scripts que no requieran la intervencion del usuario]
 (https://es.opensuse.org/Scripts_sin_intervencion_del_usuario)?

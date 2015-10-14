
#Crear y eliminar usuarios

##Entrega
* Vamos a crear un script ruby llamado **create-and-delete-users.rb**.
* La entrega la realizaremos a través del repositorio Git.
* Al terminar etiquetaremos la entrega con *create-and-delete-users*.

##Script
* Los datos de los usuarios estarán en un fichero *userslist.txt*,
con el siguiente formato:
```
username1:create
username2:delete
...
```
* El script deberá leer el contenido de un fichero *userslist.txt*,
donde vendrán definidos los nombres de usuario y la acción a realizar,
 y cargarlos en un array.
* Para cada elemento del array deberemos ejecutar los comandos necesarios
para eliminar/crear dicho usuario en un sistema GNU/Linux.
* Antes de ejecutar la acción (eliminar/crear usuario) realizaremos
una comprobación para asegurarnos que si existe o no dicho usuario.

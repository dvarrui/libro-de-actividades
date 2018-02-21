
# Eliminar usuarios

## Entrega
* Vamos a crear un script ruby llamado `delete-users.rb`.
* La entrega la realizaremos a través del repositorio Git.
* Al terminar etiquetaremos la entrega con *delete-users*.

## Script
* Crear un fichero de texto `userslist.txt`, donde vendrán definidos
los nombres de usuario.
* Se debe pasar como argumento el nombre del fichero con los datos.
* Comprobar que los argumentos son correctos.
* Leer el contenido del fichero pasado por argumentos y cargar los datos
en una variable (Los datos deben estar en formato Array).
* Para cada elemento del Array deberemos ejecutar los comandos necesarios
para eliminar dicho usuario en un sistema GNU/Linux.

Recordar:
* Usar paso de argumentos para indicar el nombre del ficheros al script.
* Cargar los datos en una variable de tipo Array (Usar split cuando sea necesario).
* Usar iteradores para el bucle.
* Al ejecutar un comando con `system("command")`, esto nos devuelve `true` o `false`,
si el comando se ha ejecutado correctamente o no.
* Si queremos ejecutar un comando pero no queremos ver los mensajes de salida y/o error,
haremos lo siguiente `comando 2>/dev/null 1>/dev/null`. Redirigimos la salida y/o error a 
la basura (dispositivo nulo).

## Incluir comprobaciones
* Antes de intentar eliminar el usuario, comprobar si existe.
* Antes de leer el archivo, comprobar si existe.

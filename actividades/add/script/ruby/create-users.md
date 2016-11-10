
#Crear usuarios y directorios

##Entrega
* Vamos a crear un script ruby llamado `create-users.rb`.
* La entrega la realizaremos a través del repositorio Git.
* Al terminar etiquetaremos la entrega con *create-users*.

##Script

Argumentos:
* Crear un fichero de texto `userslist.txt`, donde vendrán definidos
los nombres de usuario que vamos a procesar.
* Usar paso de argumentos para indicar el nombre del ficheros al script.

Comprobar:
* Comprobar que los argumentos son correctos.
* Al comenzar el script se debe comprobar si el usuario que lo ejecuta es
`root`. En caso contrario se mostrara un mensaje y se finaliza el script.

Proceso:
* Leer el contenido del fichero pasado por argumentos y cargar los datos
en una variable (Los datos deben estar en formato Array).
* Para cada nombre de usuario debemos ejecutar los comandos necesarios
para:
    * Crear el usuario `username` en un sistema GNU/Linux.
    * Crear el directorio `/home/username/private` con los permisos 700.
    * Crear el directorio `/home/username/group` con los permisos 750.
    * Crear el directorio `/home/username/public` con los permisos 755.

Recordar:
* Comprobar que los argumentos son correctos.
* Cargar los datos en una variable de tipo Array (Usar split cuando sea necesario).
* Usar iteradores para el bucle.
* Con las comillas francesas se ejecuta un comando y los mensajes de pantalla
se guardan en una variable. Por ejemplo: `user = \`whoami\``
* Con la orden system se ejecuta un comando pero no se lee el resultado.
Por ejemplo: `system("mkdir /home/username/private")`.

##Enlaces de interés
* ¿Cómo crear [scripts que no requieran la intervencion del usuario]
 (https://es.opensuse.org/Scripts_sin_intervencion_del_usuario)?

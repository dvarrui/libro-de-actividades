
#1. Introducción

* Esta práctica es individual.
* Vamos a usar una máquina virtual con SO GNU/Linux, para practicar el borrado de ficheros y su recuperación.

#2. Preparativos

* Añadiremos un segundo disco duro (sdb) virtual lo más pequeño posible a la máquina virtual (50MB).
* Crearemos una partición en el segundo disco y le daremos formato.
* Copiaremos en dicha partición varias ficheros: un fichero de texto, una imagen/foto, una canción y/o vídeo.
* A continuación borraremos todos los ficheros excepto dos.

> **NOTA**
>
> *Este borrado no es *total* y por tanto todavía estamos a tiempo de recuparar los archivos.
> * Para realizar un borrado seguro de archivos podemos usar herramientas como shred

#3. Clonación
* Demontamos el segundo disco.
* Creamos un tercer disco de igual tamaño que el disco 2.
* Clonamos el disco 2 en el disco 3.

> La recuperación se debe hacer siempre en una copia y nunca en el disco original.

#4. Recuperación

> Listado de algunas herramientas de recuperación:
> * PhotoRec/Testdisk: Se usa para recuperar archivos eliminados (Ejemplo: Recuperar archivos borrados con photorec). TestDisk también se puede usar para recuperar particiones.
> * Foremost. Ejemplo de uso: "foremost -v -i /dev/dispositivo -o salida-foremost"
> * Recuva (http://www.piriform.com/recuva)
> * Scalpel. Ejemplo de uso: "scalpel /dev/dispositivo -o salida-scalpel"

Proceso:

* Instalaremos una herramienta para la recuperación de archivos.
* Aplicaremos el proceso de recuperación sobre la partición del tercer disco. 
    * La carpeta con los archivos recuperado debe definirse en el primer disco. 
    * No es conveniente crear archivos en el mismo disco que contiene los archivos que queremos recuperar.


#ANEXO

Esto no hay que hacerlo. Es sólo informativo.
Soporte en disquete físico
* Coger un disquete del taller y formatearlo.
* Vamos a crear un sistema de ficheros tipo ext2 dentro del disquete. Comando: "mkfs.ext2 /dev/fd0".
* Crear el directorio "/mnt/dir-montaje".
* Montamos el disquete en un directorio. Comando: "mount /dev/fd0/mnt/dir-montaje".
* Comprobar que el sistema ficheros está montado. Comando: "df -hT".

Soporte en fichero
Crear fichero soporte
* Abrir consola como superusuario.
* Crear un fichero "file-soporte", de tamaño 1MB y lleno de ceros. Comando: "dd if=/dev/zero of=/mnt/file-soporte bs=512 count=2048".
* NOTA: El comando dd se utiliza para clonar (copiar a bajo nivel) dispositivos. Aunque nosotros lo hemos usado para crear un archivo de un tamaño determinado.

Formatear el fichero soporte
* Vamos a crear un sistema de ficheros tipo ext2 dentro del fichero file-soporte. Comando: "mkfs.ext2 /mnt/file-soporte".


Escribir en el soporte
Acceder al sistema de ficheros

    NOTA: Para poder acceder al sistema de ficheros recién creado debemos montarlo en un directorio.
    Crear el directorio "/mnt/dir-montaje".
    Montamos el dispositivo soporte.
    Comprobar que el sistema ficheros está montado. Comando: "df -hT".

Escribir en el sistema de ficheros

    Crear el fichero /mnt/dir-montaje/docs/README.
    Escribir dentro algunas frases en inglés.
    Crear el fichero /mnt/dir-montaje/docs/LEEME
    Escribir dentro nuestro nombre.
    Desmontar el sistema de ficheros. Comando: "umount /mnt/dir-montaje".
    Comando: "vdir /mnt/dir-montaje". La información no se ha perdido. Sólo que el sistema de ficheros no está montado.
    Montar sistema de ficheros y probar "vdir /mnt/dir-montaje".


Eliminar un fichero

    Borrar el fichero /mnt/dir-montaje/README
    Comando: "vdir /mnt/dir-montaje". El fichero ya no existe.
    Desmontar el sistema de ficheros.


Recuperar el archivo eliminado
Clonamos el disquete

    Por seguridad, vamos a clonar el dispositivo.
        dd if=/mnt/fd0 of=/mnt/soporte-clonado.alfa
        dd if=/mnt/fd0 of=/mnt/soporte-clonado.beta

Clonamos el fichero soporte

    Si hacemos "cat /mnt/file-soporte". Veremos en pantalla el contenido (en crudo) del sistema de ficheros. Vemos que el contenido del fichero eliminado se muestra por pantalla. Por lo tanto ¡no ha sido eliminado completamente del sistema de almacenamiento!
    Por seguridad, vamos a clonar el fichero soporte.
        dd if=/mnt/file-soporte of=/mnt/soporte-clonado.alfa
        dd if=/mnt/file-soporte of=/mnt/soporte-clonado.beta

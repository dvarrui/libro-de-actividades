*(Actividad realizada en los cursos 201415, 201314)*

#1. Introducción

En esta actividad vamos a practicar el borrado de ficheros y su recuperación.

* Realizaremos la práctica de forma individual.
* Vamos a usar una máquina virtual con SO GNU/Linux OpenSUSE13.2, configurada como:
    * IP: 172.19.XX.00
    * Enlace: 172.19.0.1
    * DNS: 8.8.4.4
    * Usuario: nombre-del-alumno
    * Nombre de la máquina: 1er-apellido-del-alumno
    * Nombre del dominio: 2do-apellido-del-alumno
    * Instalar SSH server.
    * Clave de root el DNI con letra en minúsculas.

#2. Preparar el disco

* Añadiremos un segundo disco duro (sdb) virtual de 10MB.
Cuanto más pequeño sea el disco más rápido se harán las clonaciones.
* Crearemos una partición primaria que coja todo el segundo disco y le daremos formato `FAT32`.
> Todos los pendrives vienen por defecto con formato FAT32.
* Copiaremos/descargaremos en dicha partición (sdb1) 3 ficheros:
    * FILE1: Un fichero de texto
    * FILE2: Una imagen/foto
    * FILE3: Una canción y/o vídeo.
* A continuación borraremos FILE1 y FILE2, usando los comandos habituales.
Si borramos por el entorno gráfico, además debemos vaciar la papelera.

> * Este borrado no es *total* y por tanto todavía estamos a tiempo de recuperar los archivos.
> * Para realizar un borrado seguro de archivos podemos usar herramientas como shred, dd, etc.

#3. Clonación

Antes de recuperar los archivos del disco (sdb) debemos hacer una clonación del mismo.
Lo llamaremos disco `alfa`. La recuperación la haremos siempre al disco `alfa`.

> La recuperación se debe hacer siempre en una copia y nunca en el disco original.

* Creamos un tercer disco de igual tamaño que el disco 2.
* Clonamos el disco 2 en el disco 3.
* Demontamos el segundo disco (sdb).

#4. Recuperación

Listado de algunas herramientas de recuperación:
* PhotoRec/Testdisk: Se usa para recuperar archivos eliminados.
    * Ejemplo de cómo [recuperar archivos borrados con photorec](http://blog.desdelinux.net/recuperar-archivos-borrados-facilmente-con-photorec-desde-la-consola/).
    * TestDisk también se puede usar para recuperar particiones.
* Foremost.
    * Ejemplo de uso: `foremost -v -i /dev/dispositivo -o salida-foremost`
* Recuva
    * [Recuva](http://www.piriform.com/recuva)
* Scalpel.
    * Ejemplo de uso: `scalpel /dev/dispositivo -o salida-scalpel`

Realizar los siguientes pasos:
* Instalaremos una herramienta para la recuperación de archivos en nuestro sistema o bien usar:
    * Distro Live Kali Linux (Descargar de Leela).
    * Distro Live Caine7 (Descargar de Leela).
* Aplicaremos el proceso de recuperación sobre la partición del disco `alfa`. 
    * No es conveniente crear archivos en el mismo disco que contiene los archivos que queremos recuperar.
    * La carpeta con los archivos recuperado debe definirse en el primer disco. 

#5. Borrado seguro

Hemos visto que aunque borremos un archivo todavía existen formas de recuperar dichos datos.
Ahora vamos a ver cómo realizar un borrado seguro.


> **Herramientas para borrado seguro**
>
> Enlaces de SHRED:
> * [shred](http://www.welivesecurity.com/la-es/2014/11/24/como-hacer-borrado-seguro-shred-linux/).
> * [Borrado seguro de archivos con Shred](http://www.linuxtotal.com.mx/index.php?cont=info_seyre_008)
>
> Ejemplo con `dd`:
> * `dd if=/dev/zero of=FILE2`: Llena el contenido del fichero FILE2 con ceros.

* Volvemos a crear/descargar 3 archivos para eliminar.
    * FILE1: Un fichero de texto
    * FILE2: Una imagen/foto
    * FILE3: Una canción y/o vídeo.
* A continuación
    * Borramos FILE1 con el comando habitual.
    * Borramos FILE2 con herramienta de borrado seguro (shred).
    * Borramos FILE3 con herramienta de borrado seguro (dd).
* Ahora ejecutamos el proceso de recuperación. ¿Se consigue recuperar algún archivo?

#ANEXO

Esto no hay que hacerlo. Es sólo informativo.

##A1. Soporte en disquete físico
* Coger un disquete del taller y formatearlo.
* Vamos a crear un sistema de ficheros tipo ext2 dentro del disquete. Comando: "mkfs.ext2 /dev/fd0".
* Crear el directorio "/mnt/dir-montaje".
* Montamos el disquete en un directorio. Comando: "mount /dev/fd0/mnt/dir-montaje".
* Comprobar que el sistema ficheros está montado. Comando: "df -hT".

##A2. Soporte en fichero
Vamos a crear fichero como soporte de datos:
* Abrir consola como superusuario.
* Crear un fichero "file-soporte", de tamaño 1MB y lleno de ceros. 
    * Comando: "dd if=/dev/zero of=/mnt/file-soporte bs=512 count=2048".
> NOTA
>
> El comando dd se utiliza para clonar (copiar a bajo nivel) dispositivos. 
>
> Aunque nosotros lo hemos usado para crear un archivo de un tamaño determinado.

##A3. Formatear el fichero soporte
* Vamos a crear un sistema de ficheros tipo ext2 dentro del fichero file-soporte. 
    * Comando: "mkfs.ext2 /mnt/file-soporte".

##A4. Escribir en el soporte
Acceder al sistema de ficheros

> NOTA: Para poder acceder al sistema de ficheros recién creado debemos montarlo en un directorio.
* Crear el directorio "/mnt/dir-montaje".
* Montamos el dispositivo soporte.
* Comprobar que el sistema ficheros está montado. Comando: "df -hT".

##A5. Escribir en el sistema de ficheros

* Crear el fichero /mnt/dir-montaje/docs/README.
* Escribir dentro algunas frases en inglés.
* Crear el fichero /mnt/dir-montaje/docs/LEEME
* Escribir dentro nuestro nombre.
* Desmontar el sistema de ficheros. Comando: "umount /mnt/dir-montaje".
* Comando: "vdir /mnt/dir-montaje". La información no se ha perdido. Sólo que el sistema de ficheros no está montado.
* Montar sistema de ficheros y probar "vdir /mnt/dir-montaje".

##A6. Eliminar un fichero

* Borrar el fichero /mnt/dir-montaje/README
* Comando: "vdir /mnt/dir-montaje". El fichero ya no existe.
* Desmontar el sistema de ficheros.

##A7. Recuperar el archivo eliminado
Clonamos el disquete

* Por seguridad, vamos a clonar el dispositivo.
    * dd if=/mnt/fd0 of=/mnt/soporte-clonado.alfa
    * dd if=/mnt/fd0 of=/mnt/soporte-clonado.beta

Clonamos el fichero soporte

* Si hacemos "cat /mnt/file-soporte". Veremos en pantalla el contenido (en crudo) del sistema de ficheros. Vemos que el contenido del fichero eliminado se muestra por pantalla. Por lo tanto ¡no ha sido eliminado completamente del sistema de almacenamiento!
* Por seguridad, vamos a clonar el fichero soporte.
    * dd if=/mnt/file-soporte of=/mnt/soporte-clonado.alfa
    * dd if=/mnt/file-soporte of=/mnt/soporte-clonado.beta

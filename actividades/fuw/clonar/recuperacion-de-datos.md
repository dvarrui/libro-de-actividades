
# 1. Introducción

* En esta actividad vamos a practicar el borrado de ficheros y su recuperación.
* Realizaremos la práctica de forma individual.
* Vamos a usar una MV OpenSUSE según [configuración](../../global/configuracion/opensuse.md).

---

# 2. Preparar el disco `roto`

* Añadiremos un segundo disco duro (sdb) a la MV VirtualBox de 10MB con el nombre de "roto".
Cuanto más pequeño sea el disco más rápido se harán las operaciones de clonado/ y recuperación.
* Iniciamos la MV y usamos la herramienta `Yast -> particionador de discos`,
para crear una partición primaria que coja todo el segundo disco y le daremos formato `ext2`.

> Para particionar/formatear se podrían usar otras herramientas como: gparted, fdisk, etc.
> En esta práctica podríamos usar el formato FAT32 que es el formato en el que vienen por defecto
todos los pendrives y discos duros externos, pero entonces el tamaño del disco debe ser mayor de 33 MB.

* Creamos el directorio `disco_roto` dentro de `/mnt`.

Montamos la partición del disco "roto"(`/dev/sdb1`) en la ruta `/mnt/disco_roto`.
* `id`, consultar uid del usuario actual (UID).
* `mount /dev/sdb1 /mnt/disco_roto -o defaults,uid=UID`, monta la partición en la ruta espacificada, y estableciendo los permisos para el usuario UID.   
* Feedback de comprobación: `df -hT`, `mount | grep disco_roto`.

> Un sistema de ficheros FAT32 no es capaz de guardar información de usuarios ni los permisos
de los ficheros/carpetas.

* Copiaremos/descargaremos en dicha partición (sdb1) 3 ficheros:
    * `FILE1`: Un fichero de texto
    * `FILE2`: Una imagen/foto
    * `FILE3`: Una canción y/o vídeo.
    * Feedback de comprobación `ls /mnt/disco_roto`.
* A continuación borraremos FILE1, FILE2 y FILE3, usando los comandos habituales de borrado.
Si borramos por el entorno gráfico, además debemos vaciar la papelera.
Feedback de comprobación `ls /mnt/disco_roto`.

> * Este borrado no es *total* y por tanto todavía estamos a tiempo de recuperar los archivos.
> * Para realizar un borrado seguro de archivos usaríamos otras herramientas.

* Desmontamos el disco "roto".
    * Feedback de comprobación: `df -hT`, `mount |grep roto`.
    * Si no podemos desmontar el disco, probablemente es que lo estamos usando.
    Con el comando `lsof |grep disco_roto`, podemos visualizar qué o quién está
    usando el disco.

---

# 3. Clonación alfa

Antes de recuperar los archivos del disco "roto" (sdb) vamos hacer una clonación
device-device del mismo. Al disco clonado lo llamaremos disco `alfa`. Apartir de
ahora los procesos de recuperación los haremos siempre con el disco `alfa`.

> La recuperación se debe hacer siempre en una copia y nunca en el disco original
para evitar que los procesos de recuperación afecten a la integridad del disco
"roto" (original).

* Creamos un tercer disco de igual tamaño que el disco "roto". A este disco lo
llamaremos `alfa` en VirtualBox.
* Iniciamos la MV. Deben estar los 3 discos. Feeback de comprobación: `fdisk -l`
* Los discos "roto" y "alfa" no deben estar montados. Feedback de comprobación: `df -hT`, `mount`
* `fdisk -l`,vemos que el disco B tiene una partición y el disco C no.

Ahora vamos a clonar el disco "roto" en el "alfa". Ya hemos usado alguna herramienta
de clonación (Clonezilla) pero en este caso vamos a usar el comando `dd`.
Este comando hace un clonado total de disco a disco incluyendo los sectores "vacíos".
Si no clonamos los sectores "vacíos" (supuestamente vaciós) no se incluirían
los ficheros eliminados.

* Usar el comando `dd` para clonar el disco `roto` en el disco `alfa`.
Ejemplo: `dd if=/dev/DISCO-ORIGEN of=/dev/DISCO-DESTINO`.
Feedback de comprobación: `diff /dev/sdb1 /dev/sdc1`.
* `fdisk -l`,vemos que el disco C ahora si tiene una partición y el mismo formato que el B.

Todas las pruebas las haremos en el disco `alfa` a partir de ahora.

> En una situación de trabajo real, quitaríamos el disco "roto" de la máquina y
lo guardaríamos en sitio seguro. No es necesario hacerlo en la práctica.

---

# 4. Recuperación

## 4.1 Herramientas de recuperación

Listado de algunas herramientas de recuperación:
* PhotoRec: Se usa para recuperar archivos eliminados.
    * Ejemplo de cómo [recuperar archivos borrados con photorec](http://blog.desdelinux.net/recuperar-archivos-borrados-facilmente-con-photorec-desde-la-consola/).
* TestDisk también se puede usar para recuperar particiones.
* Foremost.
    * Ejemplo de uso: `foremost -v -i /dev/dispositivo -o salida-foremost`
* Recuva
    * [Recuva](http://www.piriform.com/recuva)
* Scalpel.
    * Ejemplo de uso: `scalpel /dev/dispositivo -o salida-scalpel`

## 4.2 Instalando PhotoRec

Primero tenemos que conseguir la herramienta de recuperación PhotoRec.

Instalar el programa en nuestro sistema.
* `zypper in photorec qphotorec`, instalación de paquetes en OpenSUSE.
* Reiniciar la MV.
* Feedback de comprobación `zypper search nombre-programa`.

> También podríamos usar alguna distribución DVD-Live que venga con dicha herramienta, como por ejemplo:
> * Caine7 (Descargar de Leela).
> * Kali GNU/Linux (Descargar de leela).
> * Tails GNU/Linux (Descargar de la web).

## 4.3 Recuperando con PhotoRec

Vamos a iniciar el proceso de recuperación sobre la partición del disco `alfa`.
* Hay que demontar el disco `alfa`.
* Abrimos una consola como root.
* Ejecutamos `qphotorec`. De esta forma iniciamos el entorno gráfico de Photorec

Ejemplo de uso de qphotorec:

![](./images/rescue-qphotorec-01.png)

* Los archivos que se recuperen no deben escribirse en el disco `alfa`.

> La carpeta con los archivos recuperados NO deben estar en el disco `alfa` ni en el disco `roto`.

## 4.4 Recuperar ficheros de texto

Supongamos que no hemos podido recuperar el fichero de texto con las herramientas anteriores, entonces vamos a probar de otra forma.

* Creamos un archivo `/mnt/disco_alfa/secreto.txt` con el siguiente contenido:

```
===============
Fichero secreto
===============

Estos son las claves de acceso de las naves imperiales.
* Abracadabra
* Ábrete sésamo
* 123456

===============
```

* Borramos el archivo de texto con `rm`.
* Desmontamos la partición.
* `cat /dev/sdc1 | more `...¿qué estamos viendo?

---

# 5. Borrado seguro

Hemos visto que aunque borremos un archivo todavía existen formas de recuperar dichos datos.
Ahora vamos a ver cómo realizar un borrado seguro.

> **¿De verdad?**
>
> Las herramientas de borrado seguro deben ejecutarse un número de veces (35 normalmente)
para que podamos decir (¿seguro?) que hemos logrado un borrado efectivo. La explicación
de por qué pasa esto la tenemos en el siguiente
[artículo](http://www.eldiario.es/hojaderouter/tecnologia/hardware/archivos-eliminacion-recuperacion-disco_duro-papelera_de_reciclaje_0_495201286.html)  
>
> Ante la duda, y para segurarse, muchas empresas recurren a la destrucción física de los disco.
>

## 5.1 Herramientas de borrado seguro

Enlaces de SHRED:
* [Cómo hacer borrado seguro con shred](http://www.welivesecurity.com/la-es/2014/11/24/como-hacer-borrado-seguro-shred-linux/).
* [Borrado seguro de archivos con Shred](http://www.linuxtotal.com.mx/index.php?cont=info_seyre_008)

Ejemplo con `dd`:
* `dd if=/dev/zero of=FILE2`: Llena el contenido del fichero FILE2 con ceros.

## 5.2 Proceso de borrado seguro

* Creamos un disco nuevo VirtualBox de 10MB. A este disco lo llamaremos "limpio".
* Iniciamos la MV.
* Creamos la carpeta `disco_limpio` en `/mnt`.
* Montamos el disco `limpio` en la ruta `/mnt/disco_limpio`.
Feedback de comprobación: `df -hT`, `mount | grep disco`
* Volvemos a crear/descargar 3 archivos para eliminar en el disco `limpio`.
    * `FILE1`: Un fichero de texto (txt)
    * `FILE2`: Una imagen/foto (png)
    * `FILE3`: Una canción y/o vídeo.
    * Feedback de comprobación: `ls /mnt/disco_limpio`.
* A continuación
    * Borramos FILE1 con el comando habitual.
    * Borramos FILE2 con herramienta de borrado seguro (shred).
    * Borramos FILE3 con el comando habitual.
    * Feedback de comprobación: `ls /mnt/disco_limpio`.
* Ahora ejecutamos el proceso de recuperación. ¿Se consigue recuperar algún archivo?
 ¿Todos? ¿Cuáles no se han podido recuperar?

---

# 6. Recuperar esquema de particionado

OJO: Esta parte sólo funciona con particiones MBR. Estoy buscando la información
para particiones GPT.

Vamos a intentar recuperar un esquema de particionado dañado.

* `fdisk -l |grep sdc`, comprobamos que se detecta la partición.
* `dd if=/dev/zero of=/dev/sdc bs=512 count=1`, escribimos valores aleatorios
en el sector 0 del disco sdc. Destruyendo el esquema de particiones del disco.
* `fdisk -l |grep sdc`, comprobamos que ha desaparecido la partición.
* Ahora no se puede acceder a la partición sdc1.
* Usar TestDisk para recuperar el esquema de particionado.
* Ahora se debería poder acceder a la partición sdc1.

---

# ANEXO

Esto no hay que hacerlo. Es sólo informativo.

## A1. Soporte en disquete físico

* Coger un disquete del taller y formatearlo.
* Vamos a crear un sistema de ficheros tipo ext2 dentro del disquete. Comando: "mkfs.ext2 /dev/fd0".
* Crear el directorio "/mnt/dir-montaje".
* Montamos el disquete en un directorio. Comando: "mount /dev/fd0/mnt/dir-montaje".
* Comprobar que el sistema ficheros está montado. Comando: "df -hT".

## A2. Soporte en fichero

Vamos a crear fichero como soporte de datos:
* Abrir consola como superusuario.
* Crear un fichero "file-soporte", de tamaño 1MB y lleno de ceros.
    * Comando: "dd if=/dev/zero of=/mnt/file-soporte bs=512 count=2048".
> NOTA
>
> El comando dd se utiliza para clonar (copiar a bajo nivel) dispositivos.
>
> Aunque nosotros lo hemos usado para crear un archivo de un tamaño determinado.

## A3. Formatear el fichero soporte

* Vamos a crear un sistema de ficheros tipo ext2 dentro del fichero file-soporte.
    * Comando: "mkfs.ext2 /mnt/file-soporte".

## A4. Escribir en el soporte

Acceder al sistema de ficheros

> NOTA: Para poder acceder al sistema de ficheros recién creado debemos montarlo en un directorio.
* Crear el directorio "/mnt/dir-montaje".
* Montamos el dispositivo soporte.
* Comprobar que el sistema ficheros está montado. Comando: "df -hT".

## A5. Escribir en el sistema de ficheros

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

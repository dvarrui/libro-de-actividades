
*(Ejecutada en los cursos 201415, 201314)*

#1. Instalar SO en RAID-0 software

Vídeo de interés: 
* [RAID en Ubuntu](https://youtu.be/z84oBqOxsD0?list=PLtGnc4I6s8duPu8fzK6zoNzczrXogvYnx). Este
vídeo no es exactamente la actividad que vamos a realizar, pero muestra cómo se configuran
discos RAID-1 software durante la instalación del SO Ubuntu 12.

Vamos a instalar un sistema operativo sobre unos discos con RAID-0 software.

##1.1 Creación de la MV 

> Las máquinas (Y por tanto las MV de VirtualBox también), sólo aceptan 4 discos IDE, 
o 3 discos IDE y 1 unidad de cdrom.
> Como vamos a necesitar una mayor cantidad de discos, es mejor usar controladoras SATA y/o SCSI 
en nuestra máquina.

* Crear una máquina virtual nueva con 3 discos virtuales SATA:
    * (a) 100MB, 
    * (b) 4GB
    * (c) 4GB.
* Vamos a instalar GNU/Linux Debian.
* Los discos (b) y (c), van a formar un RAID-0. 

> Para hacer el RAID-0:
> * Elegimos formato de los discos (b) y (c), tipo RAID.
> * Luego debemos ir a `Configuración RAID software`, y elegimos que queremos hacer un raid0, con los discos (b) y (c).
> * Cuando veamos las siglas 'MD', se refieren a "MultiDisks". Esto es un conjunto de discos.

##1.2 Particionado e instalación

* Continuamos con el proceso de instalación, y por esta vez sin swap (Área de intercambio).
* La partición `/boot`, va en el disco (a). Los ficheros que inician el SO 
van en una partición aparte sin RAID, para evitar problemas en el boot del sistema.
* El sistema de arranque va en el disco (a).
* Crear una partición para instalar el sistema operativo dentro del dispositivo /dev/raid0.

Imagen de ejemplo, como resultado de realizar el particionado RAID0.

![raid0-particionado](./images/raid0-particionado.png)

> Por esta vez, tampoco vamos a crear una partición independiente para `/home`

* Seguimos la instalación como siempre.

##1.3 Comprobación

* Una vez instalado ejecutar los siguientes comandos, e incluir su salida en el informe:
```
    date
    hostname
    ip a
    route -n
    host www.google.es
    fdisk -l
    df -hT
    cat /proc/mdstat
    lsblk -fm
```

#2. RAID-1 software

> Consejo: Haz copia de seguridad de la MV (Exportar/importar de VBox).

Ahora vamos a añadir al sistema anterior, dos discos más para montar un RAID-1 software.

##2.1 Preparar la MV

> **NOTA**
> Las máquinas ( y las MV de VirtualBox también), sólo aceptan 4 discos IDE, o 3 discos IDE y 1 unidad de cdrom.
> Si necesitamos añadir más discos podemos hacerlo añadiendo controladores SATA/SCSI a nuestra máquina virtual.

Realizar las siguientes tareas:
* Crear 2 discos virtuales: (d) 500MB, (e) 500MB. Importante: (d) y (e) deben ser del mismo tamaño.
* Reiniciar la MV
* Usar `fdisk -l` para asegurarnos que los discos nuevos son /dev/sdd y /dev/sde

##2.2 Usar mdadm para crear RAID-1

* Instalar el paquete mdadm (Administración de dispositivos RAID). 
* Ahora si debe existir el fichero `/etc/mdadm/mdadm.conf`.
* Crear un RAID-1 (/dev/md1) con los discos (d) y (e) 
(Consultar URL: Comando `mdadm --create /dev/md1 --level=1 --raid-devices=2 /dev/sdd /dev/sde`).
* Para comprobar si se ha creado el raid1 correctamente:
```
    cat /proc/mdstat
    lsblk -fm
    mdadm --detail /dev/md1
```
* Formatear el RAID-1 con ext4: `mkfs -t ext4 /dev/md1`


##2.3 Escribir en el RAID-1

* Montar el dispositivo RAID-1 (/dev/md1) en /mnt/raid1: `mount /dev/md1 /mnt/raid1`.
* Comprobamos con `df hT`, `mount`.

> Ahora podemos escribir información en /mnt/raid1.

* Crea lo siguiente en /mnt/raid1
    * Directorio `/mnt/raid1/naboo`
    * Fichero `/mnt/raid/naboo/yoda.txt`
    * Directorio `/mnt/raid1/endor`
    * Fichero `/mnt/raid/endor/startrooper.txt`

##2.4 Configuración de RAID-1
    
* Consultar el fichero /etc/mdadm/mdadm.conf.
* El archivo de configuración sólo muestra una línea ARRAY correspondiente al RAID0.
* Para añadir una segunda línea ARRAY para el RAID1, nos ayudaremos de la salida del 
comando siguiente: `mdadm --examine --scan`. Esta información la tenemos que escribir
nosotros en el fichero de configuración.

> * Si usamos la redirección de comandos, es más fácil escribir la configuración anterior.
Por ejemplo si hacemos `echo "hola" >> /etc/mdadm/mdadm.conf`, estamos añadiendo la
salida de un comando al fichero de texto.
> * Con esto conseguimos que el disco RAID1 no pierda su configuración con cada reinicio del sistema.

##2.5 Montaje automático

> Además si queremos que se monte automáticamente el dispositivo en cada 
reinicio debemos añadir una línea en el fichero /etc/fstab, como la siguiente: 
`/dev/md1 /mnt/raid1 ext4 defaults 0 2`

* Configurar `/etc/fstab` para que el disco raid1 se monte automáticamente en caa reinicio.

#3. Quitar disco y probar

* Apagamos la MV.
* Quitar en VirtualBox uno de los discos del raid1 (`/dev/sdd`).
* Reiniciamos la MV y comprobamos que la información no se ha perdido.
* Volver a poner el disco en la MV, reiniciar.

> Para sincronizar los discos RAID1:
> * `mdadm --detail /dev/md1`
> * `mdadm /dev/md1 --manage --add /dev/sdd`

* Sincronizamos los discos y comprobar que todo está correcto.

**Salida de comprobación**

Una vez realizado lo anterior, ejecutar los siguientes comandos, y comprobar su salida:
```
    date
    hostname
    ip a
    route -n
    host www.google.es
    fdisk -l
    df -hT
    cat /proc/mdstat
    lsblk -fm
    cat /etc/mdadm/mdadm.conf
```

> NOTA: Para consultar el UUID de una partición podemos usar el comando "blkid" o hacer "vdir /dev/disk/by-uuid".

#4. Discos dinámicos en Windows

> En windows las particiones se llaman volúmenes básicos.
> 
> Para poder hacer RAID se convierten los volúmenes básicos en dinámicos.
> * Reflejo: RAID1
> * Seccionado: RAID0 con todos los discos de igual tamaño.
> * Distribuido: parecido a RAID0 usando discos de distinto tamaño.

##4.1 Volumen Seccionado (RAID0)
Vamos a crear un volumen *seccionado*:
* Crea un volumen seccionado con un tamaño total de 800MB,utilizando para ello 4 discos duros 
virtuales de 1GB cada uno.

> Un volumen Seccionado es similar a un RAID0, donde todos los discos de igual tamaño.

##4.2 Volumen Reflejado (RAID1)
Un volumen *Reflejado* es similar a un RAID1.
* Crea un par de volúmenes reflejados de 500MB cada uno, con los discos anteriormente utilizados. 
Introduce un fichero prueba-mirror.txt en el primero de ellos. Escribe tu nombre dentro. 
* Rompe los discos utilizando la opción adecuada.¿Qué ocurre?

##4.3 Pregunta RAID5
* Investiga acerca de cómo crear en Windows un Raid-5 por software y detalla la respuesta.

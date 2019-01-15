

# 1. Instalar OpenSUSE en disco RAID0 software

Ejemplo de rúbrica:

| Sección               | Muy bien (2) | Regular (1) | Poco adecuado (0) |
| --------------------- | ------------ | ----------- | ----------------- |
| (1.3) Comprobar RAID-0 | | | |
| (2.3) Comprobar RAID-1 | | | |
| (2.6) Montaje automático de RAID-1 | | | |
| (3) Quitar disco y probar RAID-1 | | |. |

Vamos a instalar un sistema operativo OpenSUSE sobre unos discos en RAID0 software.

> Con el comando `sha256sum -c opensuse-version.sha256` comprobamos si el fichero lo hemos descargado bien.

## 1.1 Creación de la MV

* Crear una máquina virtual nueva con 3 discos virtuales SATA:
    * (a) 200MB,
    * (b) 10GB
    * (c) 10GB.

> Vamos a instalar GNU/Linux OpenSUSE en un disco RAID0 formado por los discos físicos (b) y (c).

## 1.2 Particionado e instalación

* Empezamos el proceso de instalación.
* Elegimos particionado experto o manual.
* Preparamos los discos para el RAID:
    * Hacemos una partición sdb1 que coja todo el disco con formato `Volumen físico RAID`.
    * Hacemos una partición sdc1 que coja todo el disco con formato `Volumen físico RAID`.

| Dispositivo   | Size   | Tipo      | Formato |
| ------------- | ------ | --------- | ------- |
| /dev/sdb1     |  10 GB | Partición | RAID    |
| /dev/sdc2     |  10 GB | Partición | RAID    |

* Creamos el nuevo volumen RAID-0:
    * Luego debemos ir a `Particionador -> RAID`, y elegimos que queremos hacer un raid0, con las particiones RAID (sdb1 y sdc1). Le pondremos el nombre `raid0aXX`. Hemos conseguido lo siguiente:

| Dispositivo   | Size   | Tipo      | Formato | Montar    |
| ------------- | ------ | --------- | ------- | --------- |
| /dev/raid0aXX |  10 GB | Partición | btrfs   | /         |

* Crear las siguientes particiones:

| Dispositivo   | Size   | Tipo      | Formato | Montar    |
| ------------- | ------ | --------- | ------- | --------- |
| /dev/sda1     | 150 MB | Partición | ext3    | /boot     |
| /dev/sda2     |  50 MB | Partición | fat32   | /boot/efi |

* Instalar el SSOO
    * Por esta vez sin swap (Área de intercambio).
    * Tampoco vamos a crear una partición independiente para `/home`

| Dispositivo   | Formato | Montar    |
| ------------- | ------- | --------- |
| /dev/sda1     | ext3    | /boot     |
| /dev/sda2     | fat32   | /boot/efi |
| /dev/raid0aXX | btrfs   | /         |

> * El sistema de arranque irá en el disco (a). Los ficheros que inician el SO irán en una partición aparte sin RAID, para evitar problemas en el boot del sistema.
> * Hemos creado un dispositivo RAID0 llamado `/dev/raid0aXX`. Dentro de esta partición vamos a instalar el sistema operativo.

* Seguimos la instalación como siempre. Consultar la [configuración](../../global/configuracion/opensuse.md).

## 1.3 Comprobación

* Una vez instalado ejecutar los siguientes comandos, e incluir su salida en el informe:

```
date                 # Muestra la fecha/hora actual
hostname             # Nombre de la máquina
ip a                 # Muestra configuración interfaces de red
ip route             # Muestra información de enrutamiento
host www.nba.com     # Comprueba la resolución de nombres
fdisk -l             # Muestra particiones y discos
df -hT               # Muestra los puntos de montaje
cat /proc/mdstat     # Muestra la configuración RAID
lsblk -fm            # Muestra esquema de discos/particiones/montaje
```

---

# 2. RAID-1 software

> **IMPORTANTE**
> * Haz copia de seguridad de la MV VBox (snapshot/instantánea o clonarla).
> * Una vez que empiecen con los apartados 2.x,  NO apagar la MV. Sólo se puede apagar la MV cuando terminen el punto 2.4, se puede reiniciar la máquina sin perder los resultados.

Ahora vamos a añadir al sistema anterior, dos discos más para montar un RAID-1 software.

## 2.1 Preparar la MV

Realizar las siguientes tareas:
* Crear 2 discos virtuales: (d) 500MB, (e) 500MB. Importante: (d) y (e) deben ser del mismo tamaño.
* Reiniciar la MV
* Usar `fdisk -l` para asegurarnos que los discos nuevos son `/dev/sdd` y `/dev/sde`.

## 2.2 Crear RAID-1

Vamos a crear un RAID-1 (`/dev/md1`) con los discos (d) y (e)
(Consultar [URL wikipedia sobre mdadm](https://en.wikipedia.org/wiki/Mdadm):
* Podemos crear el RAID1 de varias formas:
    * Usar `Yast -> particionador` para crear el RAID-1.
    * `mdadm --create /dev/md1 --level=1 --raid-devices=2 /dev/sdd /dev/sde`

> * `mdadm` es una herramienta para gestionar los dispositivos RAID.
> * `--create /dev/md1`, indica que vamos a crear un nuevo dispositivo con el nombre que pongamos.
> * `--level=1` el dispositivo a crear será un RAID-1.
> * `--raid-devices=2`, vamos a usar dos dispositivos (particiones o discos) reales para crear el RAID.

## 2.3 Comprobar RAID-1

* Para comprobar si se ha creado el raid1 correctamente:

```
cat /proc/mdstat        # Muestra info de discos RAID
lsblk -fm               # Muestra info de los discos/particiones
mdadm --detail /dev/md1 # Muestra info del disposivo RAID md1
```
* Formatear el RAID-1 con ext4: `mkfs -t ext4 /dev/md1`

## 2.4 Escribir datos en el RAID-1

* Montar el dispositivo RAID-1 (/dev/md1) en /mnt/raid1: `mount /dev/md1 /mnt/raid1`.
* Con los comandos `df -hT` y `mount` podemos comprobar el paso anterior.

> Ahora podemos escribir información en /mnt/raid1.

* Crea lo siguiente en /mnt/raid1
    * Directorio `/mnt/raid1/naboo`
    * Fichero `/mnt/raid1/naboo/yoda.txt`
    * Directorio `/mnt/raid1/endor`
    * Fichero `/mnt/raid1/endor/sandtrooper.txt`

## 2.5 Configuración de RAID-1

Si reiniciamos la MV vamos a perder la configuración RAID1.
Vamos a configurar mdadm.conf para que RAID1 pierda su configuración con cada reinicio del sistema.

Podemos hacer la configuración permanente por `Yast -> paritcionador` o por comandos.

> A continuación ejemplo por comandos:
>
> * Hacer un snapshot de la MV por seguridad.
> * Hacer una copia de seguridad del archivo `/etc/mdadm/mdadm.conf`.
> * Consultar el fichero `/etc/mdadm/mdadm.conf`. Este archivo de configuración sólo tiene una línea ARRAY correspondiente al RAID0.
> * Para añadir una segunda línea ARRAY para el RAID1, nos ayudamos de la salida del comando siguiente: `mdadm --examine --scan`.
La información correspondiente al RAID1 la tenemos que incluir nosotros en el fichero de configuración.
> * `mdadm --examine --scan >> /etc/mdadm/mdadm.conf`, de esta forma estamos añadiendo la salida del comando al fichero de configuración.
> * Ahora hay que editar el fichero de configuración para dejer sólo 2 líneas ARRAY: una para RAID0 y otra para RAID1.

---

> **INFO: Redirección**
>
> * Si usamos la redirección de comandos, es más fácil escribir la configuración anterior.
Por ejemplo si hacemos `echo "hola" >> /etc/mdadm/mdadm.conf`, estamos añadiendo la salida de un comando al fichero de texto.

---

> * `sudo mkinitrd`, tenemos que actualizar el fichero initramfs, de modo que contenga las configuraciones de nuestro mdadm.conf durante el arranque.
* Ahora ya se puede reiniciar la MV sin que se pierda la configuración RAID1 que hemos hecho.

## 2.6 Montaje automático

> El fichero `/etc/fstab` guarda información de los dispositivos que deben montarse al iniciarse la máquina.

Vamos a configurar `/etc/fstab` para que el disco raid1 se monte automáticamente en cada reinicio. Podemos hacerlo por `Yast -> particionador` o por comandos:

A continuación ejemplo por comandos:
* Hacer una copia de seguridad del archivo `/etc/fstab`.
* Configurar `/etc/fstab` para que el disco raid1 se monte automáticamente en cada reinicio.
* Añadir la siguiente línea al fichero `/etc/fstab`:
```
/dev/md1 /mnt/raid1 ext4 defaults 0 2
```

---

# 3. Quitar disco y probar

* Apagamos la MV.
* Quitar en VirtualBox uno de los discos del raid1 (`/dev/sde`).
* Reiniciamos la MV y comprobamos que la información no se ha perdido.
* Volver a poner el disco en la MV, reiniciar.

Vamos a sincronizar los discos y comprobar que todo está correcto.

> Para sincronizar los discos RAID1:
> * [Enlace de interés para arreglar dispositivos RAID1](http://www.seavtec.com/en/content/soporte/documentacion/mdadm-raid-por-software-ensamblar-un-raid-no-activo).

* `mdadm --detail /dev/md1`, comprobamos que de los dos discos configurados, sólo hay uno.
* `mdadm /dev/md1 --manage --add /dev/sdX`, añadimos el disco que falta (sdd o sde, depende de cada caso).
* `mdadm --detail /dev/md1`, comprobamos que están los dos.

Una vez realizado lo anterior, ejecutar los siguientes comandos, y comprobar su salida:
```
date
fdisk -l
df -hT
cat /proc/mdstat
lsblk -fm
cat /etc/mdadm/mdadm.conf
```

> NOTA: Para consultar el UUID de una partición podemos usar el comando "blkid" o hacer "vdir /dev/disk/by-uuid".

---

# 4. Discos dinámicos en Windows

* Haremos la práctica con MV Windows Server, para asegurarnos de que tenga soporte
para implementar RAID5.

En windows las particiones se llaman volúmenes básicos.

Para poder hacer RAID se convierten los volúmenes básicos en dinámicos.
* Reflejo: RAID1
* Seccionado: RAID0 con todos los discos de igual tamaño.
* Distribuido: parecido a RAID0 usando discos de distinto tamaño.

## 4.1 Volumen Seccionado (RAID0)

Vamos a crear un volumen *seccionado*:
* Vídeo sobre la [Creacion de un volumen seccionado de Windows](https://www.youtube.com/watch?v=g0TF38JV1Xk)
* Vídeo sobre [RAID 0, 1 y 5 en Windows Server 2008](https://www.youtube.com/watch?v=qUNvCqWkeBA)

* Crea un volumen seccionado con un tamaño total de 800MB,utilizando para ello 4 discos duros virtuales de 200 MB cada uno.

> Un volumen Seccionado es similar a un RAID0, donde todos los discos de igual tamaño.

## 4.2 Volumen Reflejado (RAID1)

Un volumen *Reflejado* es similar a un RAID1.
* Vídeo sobre la [Creación de un volumen reflejado en Windows7](https://www.youtube.com/watch?v=UzIR9FHZyEQ).
* Vídeo sobre [RAID 0, 1 y 5 en Windows Server 2008](https://www.youtube.com/watch?v=qUNvCqWkeBA)
* Enlace sobre cómo [Configurar unas particiones reflejadas en Windows Server 2008](https://support.microsoft.com/es-es/kb/951985)

* Crea un par de volúmenes reflejados de 200 MB cada uno, con los discos anteriormente utilizados.
* Crear un fichero `prueba-mirror.txt` en el volumen reflejado. Escribe tu nombre dentro.
* Rompe los discos utilizando la opción adecuada. ¿Qué ocurre?

## 4.3 Pregunta RAID5

* Vídeo sobre [RAID 0, 1 y 5 en Windows Server 2008](https://www.youtube.com/watch?v=qUNvCqWkeBA)
* Investiga acerca de cómo crear en Windows un Raid-5 por software y detalla la respuesta.

---

# ANEXO

## A1: proceso para OpenSUSE

RAID0
* Crear MV con los discos
* Elegit idioma e instalar
* Particionador modo experto
* sda1 ext3 /boot
* sdb1 (volumen bruto) -> raid
* sdc1 (volumen bruto) -> raid
* Raid0 -> Añadir 2 particiones
* Siguiente -> Volumen para SO
* Elegir ext4 /
* Sin swap
* Escritorio XFce
RAID1
* Crear discos VBox
* Iniciar MV
* Yast -> Particionador
* crear particiones raid
* Crear md1 , montar y formatear
* usar dispositivio raid1

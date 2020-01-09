
```
Curso           : 201920, 201819
Software        : OpenSUSE Leap 15
Tiempo estimado :
```

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
    * (a) 300MB,
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
    * Luego debemos ir a `Particionador -> RAID`, y elegimos que queremos hacer un raid0, con las particiones RAID (sdb1 y sdc1). Le pondremos el nombre `r0_deviceXX`. Hemos conseguido lo siguiente:

| Dispositivo      | Size   | Tipo      | Formato | Montar    |
| ---------------- | ------ | --------- | ------- | --------- |
| /dev/r0_deviceXX |  20 GB | Partición | ext4    | /         |

> El sistema de arranque irá en el disco (a). Los ficheros que inician el SO irán en una partición aparte sin RAID, para evitar problemas en el boot del sistema.

* Crear las siguientes particiones:

| Dispositivo   | Size   | Tipo      | Formato | Montar    |
| ------------- | ------ | --------- | ------- | --------- |
| /dev/sda1     | 150 MB | Partición | ext3    | /boot     |
| /dev/sda2     |  50 MB | Partición | fat32   | /boot/efi |

* Seguimos la instalación como siempre. Consultar la [configuración](../../global/configuracion/opensuse.md).
    * Por esta vez sin swap (Área de intercambio).
    * Tampoco vamos a crear una partición independiente para `/home`.

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

**IMPORTANTE**
* Haz copia de seguridad de la MV VBox (snapshot/instantánea o clonarla).

## 2.1 Preparar la MV

Ahora vamos a añadir a la MV, varios discos para montar un RAID-1 software:
* Crear 2 discos virtuales (Importante: (d) y (e) deben ser del mismo tamaño):
    * (d) 500MB
    * (e) 500MB.
* Reiniciar la MV
* Usar `fdisk -l` para asegurarnos que los discos nuevos son `/dev/sdd` y `/dev/sde`.

## 2.2 Crear y montar RAID-1

> * Nombre del dispositivo DEVICE1=`r1_deviceXX`
> * Nombre del directorio de montaje MPOINT1=`r1_discoXX`

Vamos a crear un RAID-1 con los discos (d) y (e)
(Consultar [URL wikipedia sobre mdadm](https://en.wikipedia.org/wiki/Mdadm):
* Podemos crear el RAID1 de varias formas:
    * Usar `Yast -> particionador` para crear el RAID-1.
    * Formato `ext4`.
    * Montar en `/mnt/MPOINT1`

## 2.3 Comprobar RAID-1 y el montaje

* Para comprobar si se ha creado el raid1 correctamente:

```
cat /proc/mdstat               # Muestra info de discos RAID
lsblk -fm                      # Muestra info de los discos/particiones
mdadm --detail /dev/md/DEVICE1 # Muestra info del disposivo RAID1
```

* Consultar fichero `/etc/mdadm/mdadm.conf`, veremos que hay registradas dons confiuraciones RAID. ¿Sabes cuál es cada una?
* Comprobar si el dispositivo está correctamente montado:

```
df -hT | grep XX
mount | grep XX
```
* Consultar el fichero `/etc/fstab` para comprobar que el dispositivo se montará automáticamente al reiniciarse.

## 2.4 Escribir datos en el RAID-1

> Ahora podemos escribir información en /mnt/MPOINT1.

Crea lo siguiente en /mnt/MPOINT1
* Directorio `/mnt/MPOINT1/naboo`
* Fichero `/mnt/MPOINT1/naboo/yoda.txt`
* Directorio `/mnt/MPOINT1/endor`
* Fichero `/mnt/MPOINT1/endor/sandtrooper.txt`

Reiniciar la MV y comprobar que se mantienen los datos:
```
df -hT |grep XX
tree /mnt/MPOINT1
```
---

# 3. Quitar disco y probar

* Apagamos la MV.
* Quitar en VirtualBox uno de los discos del raid1 (`/dev/sde`).
* Reiniciamos la MV y comprobamos que la información no se ha perdido.
```
df -hT |grep XX
tree /mnt/MPOINT1
```

* Volver a poner el disco en la MV, reiniciar.

Vamos a sincronizar los discos y comprobar que todo está correcto.

> Para sincronizar los discos RAID1:
> * [Enlace de interés para arreglar dispositivos RAID1](http://www.seavtec.com/en/content/soporte/documentacion/mdadm-raid-por-software-ensamblar-un-raid-no-activo).

* `mdadm --detail /dev/DEVICE1`, comprobamos que de los dos discos configurados, sólo hay uno.
* `mdadm /dev/DEVICE1 --manage --add /dev/sdX`, añadimos el disco que falta (sdd o sde, depende de cada caso).
* `mdadm --detail /dev/DEVICE1`, comprobamos que están los dos.

Una vez realizado lo anterior, ejecutar los siguientes comandos, y comprobar su salida:

```
date
fdisk -l
df -hT
cat /proc/mdstat
lsblk -fm
cat /etc/mdadm.conf
```

> NOTA: Para consultar el UUID de una partición podemos usar el comando "blkid" o hacer "vdir /dev/disk/by-uuid".

---

# 4. Discos dinámicos en Windows

* Haremos la práctica con MV Windows Server, para asegurarnos de que tenga soporte para implementar RAID5.

En windows las particiones normales se llaman `volúmenes básicos`.

Para poder hacer RAID se convierten los volúmenes básicos en dinámicos.

| Nomenclatura Windows | Nomenclatura RAID   |
| -------------------- | ------------------- |
| Volumen básico       | Partición           |
| Volumen dinámico     | Partición tipo RAID |
| Reflejo              | RAID-1              |
| Seccionado           | RAID-0 (Todos los discos de igual tamaño) |
| Distribuido          | LVM (Parecido a RAID-0 pero usando discos de distinto tamaño |

## 4.1 Volumen Seccionado (RAID-0)

Vamos a crear un volumen *seccionado*:
* Vídeo sobre la [Creacion de un volumen seccionado de Windows](https://www.youtube.com/watch?v=g0TF38JV1Xk)
* Vídeo sobre [RAID 0, 1 y 5 en Windows Server 2008](https://www.youtube.com/watch?v=qUNvCqWkeBA)

* Añadir a la MV 4 discos duros virtuales de 200 MB cada uno.
* Crea un volumen seccionado con un tamaño total de 800 MB, utilizando los discos anteriores.

## 4.2 Volumen Reflejado (RAID-1)

Un volumen *Reflejado* es similar a un RAID1.
* Vídeo sobre la [Creación de un volumen reflejado en Windows7](https://www.youtube.com/watch?v=UzIR9FHZyEQ).
* Vídeo sobre [RAID 0, 1 y 5 en Windows Server 2008](https://www.youtube.com/watch?v=qUNvCqWkeBA)
* Enlace sobre cómo [Configurar unas particiones reflejadas en Windows Server 2008](https://support.microsoft.com/es-es/kb/951985)

* Usar los 4 discos de 200 MB anteriores para crear un volúmen reflejado de 200 MB.
* Crear un fichero `mirror-pruebaXX.txt` en el volumen reflejado. Escribe tu nombre dentro.
* Rompe los discos utilizando la opción adecuada. ¿Qué ocurre?

## 4.3 Pregunta RAID-5

* ¿Qué es RAID-5?
* Vídeo sobre [RAID 0, 1 y 5 en Windows Server 2008](https://www.youtube.com/watch?v=qUNvCqWkeBA)
* Investiga acerca de cómo crear en Windows un Raid-5 por software y detalla la respuesta.

---

# ANEXO (Esto NO hay que hacerlo)

## A 2.5 Configuración de RAID-1 por comandos

* Hacer un snapshot de la MV por seguridad.

Si reiniciamos la MV vamos a perder la configuración RAID1.
Para que la configuración de RAID-1 sea permanente hay que escribir los datos en el fichero mdadm.conf. De esta forma la configuración RAID-1 se mantiene en cada reinicio del sistema.

Podemos hacer la configuración permanente cambiando el fichero `mdadm.conf`directamente, o usando `Yast -> particionador`.

* Hacer una copia de seguridad del archivo `/etc/mdadm/mdadm.conf`.
* Consultar el fichero `/etc/mdadm/mdadm.conf`. Este archivo de configuración sólo tiene una línea ARRAY correspondiente al RAID0.
* Para añadir una segunda línea ARRAY para el RAID1, nos ayudamos de la salida del comando siguiente: `mdadm --examine --scan`.
La información correspondiente al RAID1 la tenemos que incluir nosotros en el fichero de configuración.
* `mdadm --examine --scan >> /etc/mdadm/mdadm.conf`, de esta forma estamos añadiendo la salida del comando al fichero de configuración.
* Ahora hay que editar el fichero de configuración para dejer sólo 2 líneas ARRAY: una para RAID0 y otra para RAID1.

**INFO: Redirección**

* Si usamos la redirección de comandos, es más fácil escribir la configuración anterior.
Por ejemplo si hacemos `echo "hola" >> /etc/mdadm/mdadm.conf`, estamos añadiendo la salida de un comando al fichero de texto.
* `sudo mkinitrd`, tenemos que actualizar el fichero initramfs, de modo que contenga las configuraciones de nuestro mdadm.conf durante el arranque.

**Comprobamos**
* Ahora ya se puede reiniciar la MV sin que se pierda la configuración RAID1 que hemos hecho.
* Ya hemos conseguido tener la configuración RAID-1 permanente.

---
# Crear y montar RAID1 por comandos
> **Aclaración**:
>
> Para hacerlo por comandos: `mdadm --create /dev/DEVICE1 --level=1 --raid-devices=2 /dev/sdd /dev/sde`.
>
> Explicación de los parámetros del comando anterior:
> * `mdadm` es una herramienta para gestionar los dispositivos RAID.
> * `--create /dev/DEVICE1`, indica que vamos a crear un nuevo dispositivo con el nombre que pongamos.
> * `--level=1` el dispositivo a crear será un RAID-1.
> * `--raid-devices=2`, vamos a usar dos dispositivos (particiones o discos) reales para crear el RAID.
>
> * Formatear el RAID-1 con ext4: `mkfs -t ext4 /dev/md/DEVICE1`
>
> * Montar el dispositivo RAID-1: `mount /dev/md/DEVICE1 /mnt/MPOINT1`.
---
## 2.5 Configuración de RAID-1

* Hacer un snapshot de la MV por seguridad.

Si reiniciamos la MV vamos a perder la configuración RAID1.
Para que la configuración de RAID-1 sea permanente hay que "grabar" los datos en el fichero "mdadm.conf". De esta forma la configuración RAID-1 se mantiene en cada reinicio del sistema.

* Consultar fichero `/etc/mdadm/mdadm.conf` antes de registrar el RAID1.
* Podemos hacer la configuración RAID-1 permanente usando `Yast -> particionador`.
* Consultar fichero `/etc/mdadm/mdadm.conf` después de registrar el RAID1.
* Ahora ya se puede reiniciar la MV sin que se pierda la configuración RAID1 que hemos hecho. Hemos conseguido tener la configuración RAID-1 permanente. Compruébalo.

---
## A 2.6 Montaje automático

> El fichero `/etc/fstab` guarda información de los dispositivos que deben montarse al iniciarse la máquina.

Vamos a configurar `/etc/fstab` para que el disco raid1 se monte automáticamente en cada reinicio. Podemos hacerlo por `Yast -> particionador` o por comandos:

A continuación ejemplo por comandos:
* Hacer un snapshot de la MV por seguridad.
* Hacer una copia de seguridad del archivo `/etc/fstab`.
* Configurar `/etc/fstab` para que el disco raid1 se monte automáticamente en cada reinicio.
* Añadir la siguiente línea al fichero `/etc/fstab`:
```
/dev/md1 /mnt/raid1 ext4 defaults 0 2
```

## 2.6 Montaje automático

> El fichero `/etc/fstab` guarda información de los dispositivos que deben montarse al iniciarse la máquina.

Vamos a configurar `/etc/fstab` para que el disco raid1aXX se monte automáticamente en cada reinicio. Podemos hacerlo por `Yast -> particionador` o por comandos:

* Hacer un snapshot de la MV por seguridad.
* Hacer una copia de seguridad del archivo `/etc/fstab`.
* Ir a `Yast -> Particionador`
* Configurar para que el disco /dev/raid1aXX se monte automáticamente en cada reinicio en /mnt/raid1discoXX.
* Consultar el fichero `/etc/fstab` resultante.

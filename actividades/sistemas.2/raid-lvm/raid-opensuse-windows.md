
```
Curso       : 201920, 201819
Area        : Sistemas operativos, fundamentos de hardware
Descripción : Practicar las configuraciones RAID0 y RAID1 por softwere.
Requisitos  : OpenSUSE Leap 15, Windows Server
Tiempo      : 8 sesiones
```

---
# 1. Instalar OpenSUSE en disco RAID0 software

Ejemplo de rúbrica:

| Sección               | Muy bien (2) | Regular (1) | Poco adecuado (0) |
| --------------------- | ------------ | ----------- | ----------------- |
| (1.3) Comprobar RAID-0 | | | |
| (2.3) Comprobar RAID-1 | | | |
| (3) Quitar disco y probar RAID-1 | | |. |

Vamos a instalar un sistema operativo OpenSUSE sobre unos discos en RAID0 software.

## 1.1 Creación de la MV

* Crear una máquina virtual sin discos.
* Añadir 3 discos virtuales SATA a la MV:
    * (a) 300MB,
    * (b) 10GB
    * (c) 10GB.
* **IMPORTANTE**: Configurar la MV con EFI activo (`Configuración -> Sistema -> EFI`).

## 1.2 Particionado e instalación

* Empezamos el proceso de instalación del SO.
* Elegimos particionado experto o manual.

**Partición de arranque**: El arranque del sistema operativo (boot) lo pondremos en una partición normal, fuera del RAID.
* Crear la siguiente partición en el disco sda:

| Dispositivo   | Size   | Tipo                      | Formato | Montar    |
| ------------- | ------ | ------------------------- | ------- | --------- |
| /dev/sda1     | 300 MB | Partición de Arranque EFI | fat     | /boot/efi |

> El sistema de arranque irá en el disco (a). Los ficheros que inician el SO irán en una partición aparte sin RAID, para evitar problemas en el boot del sistema.

**Dispositivo para el sistema operativo**: El sistema operativo lo vamos a instalar en un dispositivo virtual RAID0.
* Ir a `Particionador -> RAID`, y elegimos:
    * Hacer un `raid0`
    * Elegir los discos `sdb` y `sdc`.
    * Le pondremos el nombre `deviceXXr0` al dispositivo RAID0.
* Aceptar.
* Ya tenemos creado el nuevo dispositivo. Ahora crear una partición en él:

| Dispositivo        | Size   | Tipo                  | Formato | Montar |
| ------------------ | ------ | --------------------- | ------- | ------ |
| /dev/md/deviceXXr0 |  20 GB | Partición del sistema | ext4    | /      |

> En esta ocasión:
> * No crearemos área de intercambio(swap).
> * No crearemos una partición independiente para `/home`.

* Seguimos la instalación como siempre. Consultar la [configuración](../../global/configuracion/opensuse.md).

## 1.3 Comprobar RAID0

> Como resultado final obtenemos una instalación de SO GNU/Linux OpenSUSE en un disco RAID0 formado por la unión de dos discos físicos `sdb` y `sdc`.

* Una vez instalado ejecutar los siguientes comandos, e incluir su salida en el informe:

```
date              # Muestra la fecha/hora actual
hostname          # Nombre de la máquina
ip a              # Muestra configuración interfaces de red
ip route          # Muestra información de enrutamiento
host www.nba.com  # Comprueba la resolución de nombres
```

Información sobre los discos, particiones y dispositivos:
```
fdisk -l          # Muestra particiones y discos
df -hT            # Muestra los puntos de montaje
cat /proc/mdstat  # Muestra la configuración RAID
lsblk             # Muestra esquema de discos/particiones/montaje
```

---
# 2. RAID-1 software

Ahora vamos a practicar el RAID1 con nuestra MV anterior.

> **IMPORTANTE**: Haz copia de seguridad de la MV VBox (snapshot/instantánea o clonarla).

## 2.1 Preparar la MV

Vamos a añadir a la MV, varios discos para montar un RAID-1 software:
* Crear 2 discos virtuales (del mismo tamaño) a la MV:
    * (d) 500MB
    * (e) 500MB.
* Reiniciar la MV
* Usar `fdisk -l` para asegurarnos que los discos nuevos son `/dev/sdd` y `/dev/sde`.

## 2.2 Crear y montar RAID-1

> Enlace de interés:
> * [URL wikipedia sobre mdadm](https://en.wikipedia.org/wiki/Mdadm):

Vamos a crear RAID-1 con los discos `sdd` y `sde`:
* Ir a `Particionador -> RAID`, y elegimos:
    * Elegir tipo `raid1`
    * Elegir los discos `sdd` y `sde`.
    * Le pondremos el nombre `deviceXXr1`.
* Aceptar
* Crear directorio `/mnt/folderXXr1`. Este es el directorio que vamos a usar para montar el dispositivo.
* Crear una partición en el nuevo dispositivo `deviceXXr1`:
    * Formato `ext3`.
    * Tamaño: `Disco completo`.
    * Montar en `/mnt/folderXXr1`

## 2.3 Comprobar RAID-1 (configuración y montaje)

* Para comprobar si se ha creado el dispositivo RAID1 correctamente:

```
cat /proc/mdstat                  # Muestra info de discos RAID
lsblk                             # Muestra info de los discos/particiones
mdadm --detail /dev/md/deviceXXr1 # Muestra info del disposivo RAID1
```

> En el fichero `/etc/mdadm.conf`, se guardan todas las configuraciones relacionadas con los dispositivos RAID.

* `df -hT | grep XX`, comprobar si el dispositivo está correctamente montado.
* `cat /etc/fstab`, comando para consultar el fichero de configuración de los montajes automáticos. Esto es para que se monte el dispositivo automáticamente en cada reinicio de la máquina.
* Reiniciar equipo.
* Comprobar que el dispositivo RAID1 está montado en `/mnt/folderXXr1`.

## 2.4 Escribir datos en el RAID-1

Crea lo siguiente:
* Directorio `/mnt/folderXXr1/naboo`
* Fichero `/mnt/folderXXr1/naboo/yoda.txt`
* Directorio `/mnt/folderXXr1/endor`
* Fichero `/mnt/folderXXr1/endor/sandtrooper.txt`

Reiniciar la MV y comprobar que se mantienen los datos:
```
df -hT |grep XX
tree /mnt/folderXXr1
```

---
# 3. Quitar disco y probar

Como tenemos un dispositivo RAID1, entonces podemos quitar uno de los discos y comprobar que el sistema sigue funcionando.

## 3.1 Quitar un disco

* Apagamos la MV.
* Quitar en VirtualBox uno de los discos del raid1 (`/dev/sde`).
* Reiniciamos la MV y comprobamos que la información no se ha perdido, aunque el disco no esté.
```
lsblk                 # Muestra info de los discos/particiones
df -hT |grep XX
tree /mnt/folderXXr1
```

## 3.2 Poner el disco

* Volver a poner el disco en la MV, reiniciar.

Vamos a sincronizar los discos y comprobar que todo está correcto.

> Para sincronizar los discos RAID1:
> * [Enlace de interés para arreglar dispositivos RAID1](http://www.seavtec.com/en/content/soporte/documentacion/mdadm-raid-por-software-ensamblar-un-raid-no-activo).

* `mdadm --detail /dev/md/deviceXXr1`, comprobamos que de los dos discos configurados, sólo hay uno.
* `mdadm /dev/md/deviceXXr1 --manage --add /dev/sdX`, añadimos el disco que falta (sdd o sde, depende de cada caso).
* `mdadm --detail /dev/md/deviceXXr1`, comprobamos que están los dos.

Una vez realizado lo anterior, ejecutar los siguientes comandos, y comprobar su salida:

```
date
fdisk -l
cat /proc/mdstat
lsblk
```

---
# 4. Discos dinámicos en Windows

Ahora vamos a practicar las configuraciones RAID software con una MV Windows. Elegimos Windows Server porque así nos aseguramos de tener soporte para implementar RAID5.

**Preparativos**

* Crear una MV con [Windows Server](../../global/configuracion/windows-server.md).

**Teoría**: A continuación podemos una tabla con la traducción de los terminos:

| Nomenclatura Windows | Nomenclatura Standard |
| -------------------- | --------------------- |
| Volumen básico       | Partición             |
| Volumen dinámico     | Partición tipo RAID   |
| Reflejo              | RAID-1                |
| Seccionado           | RAID-0 (Todos los discos de igual tamaño) |
| Distribuido          | LVM (Parecido a RAID-0 pero usando discos de distinto tamaño |

En Windows las particiones normales se llaman `volúmenes básicos`. Para poder hacer RAID tendremos que convertir el volumen básico en dinámico.

## 4.1 Volumen Seccionado (RAID-0)

> Enlaces de interés:
> * Vídeo sobre la [Creacion de un volumen seccionado de Windows](https://www.youtube.com/watch?v=g0TF38JV1Xk)
> * Vídeo sobre [RAID 0, 1 y 5 en Windows Server 2008](https://www.youtube.com/watch?v=qUNvCqWkeBA)

Vamos a crear un volumen *seccionado* (Esto es lo mismo que un RAID0):
* Añadir a la MV 3 discos duros virtuales de 200 MB cada uno.
* Crea un volumen seccionado con un tamaño total de 600 MB, utilizando los discos anteriores.

## 4.2 Volumen Reflejado (RAID-1)

> Enlaces de interés:
> * Vídeo sobre la [Creación de un volumen reflejado en Windows7](https://www.youtube.com/watch?v=UzIR9FHZyEQ).
> * Vídeo sobre [RAID 0, 1 y 5 en Windows Server 2008](https://www.youtube.com/watch?v=qUNvCqWkeBA)
> * Enlace sobre cómo [Configurar unas particiones reflejadas en Windows Server 2008](https://support.microsoft.com/es-es/kb/951985)

Un volumen *Reflejado* (Esto es es lo mismo que un RAID1):
* Usar los 2 discos de 200 MB anteriores para crear un volumen reflejado de 200 MB.
* Crear un fichero `mirror-pruebaXX.txt` en el volumen reflejado. Escribe tu nombre dentro.
* Rompe los discos utilizando la opción adecuada. ¿Qué ocurre?

## 4.3 Pregunta RAID-5

> Enlace de interés:
> * Vídeo sobre [RAID 0, 1 y 5 en Windows Server 2008](https://www.youtube.com/watch?v=qUNvCqWkeBA)

* ¿Qué es RAID-5?
* Investiga acerca de cómo crear en Windows un Raid-5 por software.

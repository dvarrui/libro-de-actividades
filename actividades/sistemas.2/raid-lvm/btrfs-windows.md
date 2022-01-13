```
Curso       : 202122
Area        : Sistemas operativos, copias de seguridad, raid y LVM
Descripción : Practicar copias de seguridad, RAID y LVM en caliente
Requisitos  : OpenSUSE Leap 15, Windows Server
Tiempo      : 8 sesiones
```

# Sistema de ficheros BtrFS y volúmenes dinámicos de Windows

1. [Preparativos](#1-preparativos)
2. [Práctica con BtrFS](#2-practica-conn-btrfs)
3. [Snpashots](#2-snapshots)
4. [Discos dinámicos en Windows](#4-discos-dinámicos-en-windows)

# 1. Preparativos

* MV Opensuse
* Añadir 4 discos de 5GB cada uno. Los llamaremos discoB, discoC, discoD y discoE.

Enlaces de interés:
* [TUTORIAL - Filesystems BTRFS en Linux](https://puerto53.com/linux/filesystems-btrfs/)

# 2. Práctica con BtrFS

La práctica está en su mayoría basada en el siguiente vídeo:
* [VIDEO 1 hora - Guía de administración BTRFS](https://www.youtube.com/watch?v=uD6u5_tgaeE)
* Tomar notas/apuntes de los comandos que se están usando y entender la función de cada uno. Vamos a necesitarlos en los siguientes apartados.
* Resolver todas las dudas antes de pasar al siguiente apartado.

## 2.1 Usando 1 único disco

**BrtFS en modo single**

* Crear partición al discoB y formatear brtfs
* Montar discoB en /mnt
* Crear dos subvolúmenes en /mnt de la siguiente forma:
    * Nombre del subvolumen "data". Los datos estarán duplicados.
    * Nombre del subvolumen "zip". Los datos estarán duplicados y comprimidos.
* Escribir algo información en cada subvolumen dejando más del 60% del disco vacío.
* Comprobar resultado.

**Simular un RAID1 con BrtFS usando un único disco**

* En caliente modificar el sistema de ficheros en modo "duplicado".
* Escribir información en cada subvolumen hasta llenar el discoB completamente.
* Comprobar resultado.

## 2.2 Usando dos discos

**Simular un RAID0 con BrtFS usando 2 discos**

* Añadir discoC al sistema de ficheros "en caliente".
* Configurar el sistema de ficheros en modo RAID0.
* Comprobar resultado.

## 2.3 Usando tres discos

* Vamos a simular un RAID5 con BrtFS usando los 3 discos.
* Añadir el discoD al sistema de ficheros "en caliente".
* Configurar el sistema de ficheros en modo RAID5.
* Comprobar.

## 2.4 Usando 4 discos

* Vamos a simular un RAID6 con BrtFS usando los 4 discos.
* Añadir el discoE al sistema de ficheros "en caliente".
* Configurar el sistema de ficheros en modo RAID6.
* Comprobar.

# 3. Snapshots

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

## 342 Volumen Reflejado (RAID-1)

> Enlaces de interés:
> * Vídeo sobre la [Creación de un volumen reflejado en Windows7](https://www.youtube.com/watch?v=UzIR9FHZyEQ).
> * Vídeo sobre [RAID 0, 1 y 5 en Windows Server 2008](https://www.youtube.com/watch?v=qUNvCqWkeBA)
> * Enlace sobre cómo [Configurar unas particiones reflejadas en Windows Server 2008](https://support.microsoft.com/es-es/kb/951985)

Un volumen *Reflejado* (Esto es es lo mismo que un RAID1):
* Usar los 2 discos de 200 MB anteriores para crear un volumen reflejado de 200 MB.
* Crear un fichero `mirror-pruebaXX.txt` en el volumen reflejado. Escribe tu nombre dentro.
* Apaga la máquina y quita uno de los discos del RAID-1.
* Reinicia y comprueba que seguimos teniendo los datos. El RAID-1 sigue funcionando con un sólo disco.

## 4.3 Pregunta RAID-5

> Enlace de interés:
> * Vídeo sobre [RAID 0, 1 y 5 en Windows Server 2008](https://www.youtube.com/watch?v=qUNvCqWkeBA)

* Usar los 3 discos de 200 MB anteriores para crear un RAID-5.

---
# ANEXO

mkfs.btrfs /dev/sdb /dev/sdc | raid1
mkfs.btrfs -m raid0 /dev/sdb /dev/sdc |raid0
mkfs.btrfs -m raid10 -d raid10 /dev/sdb /dev/sdc /dev/sdd /dev/sde|raid 10
mkfs.btrfs -m single /dev/sdb | single un disco
tail -1 /etc/fstab
df -hP /testbtrfs/
btrfs device add /dev/sdd /testbtrfs/ | Añadir un disco al FS
btrfs filesystem show /testbtrfs/
btrfs filesystem usage /testbtrfs
btrfs device delete /dev/sdd /testbtrfs
En BTRFS el equivalente al PVMOVE de LVM es automático cuando eliminamos un disco.
btrfs device scan
btrfs device scan /dev/sdb
btrfs filesystem balance /testbtrfs
btrfs filesystem resize 2:-100m /testbtrfs/
btrfs filesystem resize 2:max /testbtrfs/
btrfs subvolume create /testbtrfs/subvol1
btrfs subvolume list /testbtrfs
btrfs subvolume snapshot /testbtrfs/subvol1/ /testbtrfs/subvol1/snapshot
rm -r /testbtrfs/subvol1/*

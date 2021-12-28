```
Estado      : EN CONSTRUCCION!!!
Curso       : 202122
Area        : Sistemas operativos, copias de seguridad, raid y LVM
Descripción : Practicar copias de seguridad, RAID y LVM en caliente
Requisitos  : GNU/Linux
Tiempo      : 8 sesiones
```

# BtrFS


# 1. Preparativos

## 1.1 Preparar la máquina virtual

* MV Opensuse
* Añadir 4 discos de 5GB cada uno. Los llamaremos discoB, discoC, discoD y discoE.

# 1.2 Consultar la información

Consultar las siguientes guías:
* [Guía de administración BTRFS](https://www.youtube.com/watch?v=uD6u5_tgaeE)
* [Filesystems BTRFS en Linux](https://puerto53.com/linux/filesystems-btrfs/)
* Tomar notas/apuntes de los comandos que se están usando y entender la función de cada uno. Vamos a necesitarlos en los siguientes apartados.
* Resolver todas las dudas antes de pasar al siguiente apartado.

# 2. Práctica con BtrFS

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

## 2.4 Snapshots

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

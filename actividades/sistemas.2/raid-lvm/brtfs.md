```
Estado      : EN CONSTRUCCION!!!
Curso       : 202122
Area        : Sistemas operativos, copias de seguridad, raid y LVM
Descripción : Practicar copias de seguridad, RAID y LVM en caliente
Requisitos  : GNU/Linux
Tiempo      : 8 sesiones
```

# BtrFS

> Enlaces de interés:
> * https://puerto53.com/linux/filesystems-btrfs/

# 1. Preparativos

## 1.1 Preparar la máquina virtual

* MV Opensuse
* Añadir 3 discos de 5GB cada uno. Los llamaremos discoB, discoC y discoD.

# 1.2 Consultar la información

Consultar el siguiente vídeo:
* [Guía de administración BTRFS](https://www.youtube.com/watch?v=uD6u5_tgaeE)
* Tomar notas/apuntes de los comandos que se están usando y entender la función de cada uno. Vamos a necesitarlos en los siguientes apartados.

# 2. Práctica con BtrFS

## 2.1 Simular un RAID1 con BrtFS usando un único disco

* Crear partición al discoB y formatear brtfs
* Montar discoB en /mnt
* Crear dos subvolúmenes en /mnt de la siguiente forma:
    * Nombre del subvolumen "data". Los datos estarán duplicados.
    * Nombre del subvolumen "zip". Los datos estarán duplicados y comprimidos.
* Escribir información en cada subvolumen hasta llenar el discoB completamente.

## 2.2 Simular un RAID0 con BrtFS usando 2 discos

* Añadir discoC al sistema de ficheros "en caliente" para simular un RAID0.

## 2.3 Simular un RAID5 con BrtFS usando los 3 discos

* Añadir el discoD al sistema de ficheros "en caliente" para simular un RAID5.

## 2.4 Snapshots

---
# ANEXO

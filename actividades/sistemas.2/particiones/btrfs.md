
```
EN CONSTRUCCIÓN!!!
```

# Btrfs

Btrfs (B-tree File System) es un sistema de archivos moderno, de código abierto, diseñado para ser escalable, resistente a errores y fácil de administrar. Está diseñado para soportar múltiples almacenamientos, copias de seguridad, recuperación de errores, administración de memoria y compresión.

Btrfs proporciona una variedad de características útiles:
* Capacidad de administrar volúmenes
* Administración de subvolúmenes
* Replicación de datos
* Administración de espacio de almacenamiento
* Administración de la integridad de los datos
* Administración de estados de archivo

# Btrfs: Trabajando con Subvolúmenes

> Enlaces de interés:
> * https://fedoramagazine.org/working-with-btrfs-subvolumes/
> * https://fedoramagazine.org/working-with-btrfs-general-concepts/

## Introducción

Los subvolúmenes permiten la partición de un sistema de archivos Btrfs en subsistemas de archivos separados. Se pueden montar subvolúmenes como si fueran sistemas de archivos independientes. También se puede definir el espacio máximo que puede ocupar un subvolumen (qgroups) o usar subvolúmenes para incluir o excluir archivos específicamente de las instantáneas.

* `findmnt -no FSTYPE /home`, verificar si el directorio /home/ es Btrfs.
* Vamos a crear un nuevo directorio `workdir` y nos movemos dentro.
* `sudo btrfs subvolumen create subvolXX`, crear un subvolumen Btrfs. Ahora tenemos una nueva carpeta `subvolXX`, que podemos tratar como una carpeta normal.
* `sudo btrfs subvolume list .`, para listar los subvolúmenes.
* `sudo btrfs subvolume list -o .`, limitar la salida a los subvolúmenes debajo de nuestra ubicación actual.

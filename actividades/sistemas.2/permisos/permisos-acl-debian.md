
```
Curso       : 202021, 201819
Area        : Sistemas operativos, permisos ACL
Descripción : Configuración de los permisos ACL en GNU/Linux
Requisitos  : GNU/Linux
Tiempo      :
```

# 1. INFO: Teoría sobre las ACL

Este apartado inicial, sólo es una explicación para leer y entender.

## 1.1 INFO Introducción

Las ACL son listas de control de accesos. Es otra forma de asignar permisos con otro nivel de detalle similar al empleado en los Routers Cisco y en el sistema de ficheros NTFS.

En una MV Debian hay que instalar el paquete `acl`, que es el que contiene los comandos:
* `getfacl`, consultar las ACL.
* `setfacl`, modificar las ACL.

> Veamos un ejemplo del clásico permisos user-group-others:
> ```
> david@quigon:~/tmp/sh$ vdir
> total 4
> -rwxr-xr-x 1 david david 19 2011-02-03 22:52 holamundo*
> ```
>
> Ahora veamos un ejemplo de permisos ACL con getfacl:
> ```
> david@quigon:~/tmp/sh$ getfacl holamundo
> # file: holamundo
> # owner: david
> # group: david
> user::rwx
> group::r-x
> other::r-x
> ```

## 1.2 INFO Ejemplo de activación manual

ACL añade más detalle al sistema clásico de permisos.
Para poder usar el comando `setfacl`, los ficheros deben estar en un sistema de ficheros montado con la opción `acl`.

Por ejemplo si intentamos poner permisos ACL sin haber activado antes la partición en modo `acl` veremos un error como este:  `setfacl: holamundo: La operación no está soportada`

Si quisiéramos activarlos únicamente de manera temporal para
la sesión en la que nos encontramos, ejecutaríamos el siguiente comando:

```
mount /dev/partition -o defaults,acl /punto/de/montaje
```

Podemos usar el comando mount sin parámetros para verificar que todo está montado según nuestras intenciones.

A continuación probamos el comando `setfacl` para añadir permiso de lectura
al usuario invitado sobre el fichero holamundo .

```
david@quigon:~/tmp/sh$ vdir
total 4
-rwxr-xr-x 1 david david 19 2011-02-03 22:52 holamundo*
david@quigon:~/tmp/sh$ setfacl -m u:invitado:r holamundo
```

* Ejecutamos `getfacl` para comprobar que el resultado es el que esperábamos.

```
david@quigon:~/tmp/sh$ getfacl holamundo
# file: holamundo
# owner: david
# group: david
user::rwx
user:invitado:r--
group::r-x
mask::r-x
other::r-x
```

## 1.3 INFO ejemplo de Activación automática

Para activar las ACL en la partición que elijamos, debemos modificar
el fichero `/etc/fstab`, y luego reiniciar el equipo. Este fichero define que
particiones serán montadas automáticamente al iniciar el sistema, y con
qué parámetros se realizará dicho montaje automático.

Ejemplo de como editar el fichero `/etc/fstab` para activar las ACL en las particiones deseadas de manera permanente:

`/dev/partición  /punto/de/montaje ext2 defaults,acl 0 2`

Tras haber modificado /etc/fstab, para que monte una partición con el parámetro acl, debemos reiniciar la máquina (comando reboot).

---

# 2. Práctica de ACL

Ejemplo de rúbrica:

| Sección                  | Muy bien (2) | Regular (1) | Poco adecuado (0) |
| ------------------------ | ------------ | ----------- | ----------------- |
| (2.2) Configuración /etc/fstab | | | |
| (2.4) Comprobaciones | | | .|

En nuestro ejemplo tenemos:
* Partición libre `/dev/sda8` formateada en `ext2`

## 2.1 Preparativos

* En la MV podemos usar alguna partición que tengamos libre.
* Iniciar MV.
* OPCIONAL: Puedes instalar un entorno gráfico en la MV Debian si lo deseas.
    * Entra como usuario root.
    * `apt update`
    * `apt install -y xfce4`, instalar entorno gráfico XFCE4.
    * `reboot`, reiniciar la MV para trabajar con el entorno gráfico.
* `fdisk -l`, comprobar que los discos/particiones que tenemos son correctos.
* Identificar la partición que tenemos libre para usar en esta práctica. El comando `df -hT` nos debe informa que tenemos libre la partición `/dev/sda8`.
* `apt install acl`, instalar el paquete/software que gestiona las ACL.

## 2.2 Montar el dispositivo (partición)

Ahora vamos a crear un nuevo punto de montaje en el fichero de configuración `/etc/fstab`.

* Primero por seguridad, hacer una instantánea de la MV.
* Crear directorio `/mnt/starwars`.
* Abre el fichero `/etc/fstab`.
* Añade nueva línea para la partición que queremos montar en el directorio `/mnt/starwars` con los parámetros para activar ACL, esto es:

```
/dev/sda8  /mnt/starwars  ext2  defaults,acl  0  2
```

* `cat /etc/fstab`.
* Reiniciar el sistema. Si la MV no arranca correctamente volver a la instantánea
anterior y revisar los últimos cambios realizados.
* `df -hT`, comprobar los puntos de montaje en el inicio:

Ya tenemos activo el modo ACL en la partición seleccionada.

## 2.3 Poner permisos

> NOTA:
>
> * Para crear usuarios `adduser` o `useradd`
> * Para crear grupos `addgroup` o `groupadd`
> * Para poner constraseña al usuario luke haremos `passpwd luke`

Crear los grupos y usuarios:
* Crear el grupo `rebels`, con los usuarios `han`, `luke`.
* Crear el grupo `troopers` con los usuarios `trooper1`, `trooper2`.

> NOTA:
>
> * Para poner permiso ACL r al usuario invitado: `setfacl -m u:invitado:r holamundo`
> * Para quitar los permisos ACL al usuario luke: `setfacl -x u:luke holamundo`

Crear carpetas y poner permisos ACL:
* Crear la carpeta `/mnt/starwars/endor` con el usuario `root`:
    * donde la carpeta tendrá lo permisos clásicos 700.
    * donde el grupo `troopers` tienen permisos ACL rwx,
    * donde el usuario `luke` tiene permisos ACL rx.
* Crear la carpeta `/mnt/starwars/xwing` con el usuario `root`,
    * donde la carpeta tendrá lo permisos clásicos 700.
    * donde el usuario `han` tienen permisos ACL rwx.
    * donde el usuario `luke` tienen permisos ACL rx.

## 2.4 Comprobaciones

* Comprobar:
    * `getfacl /mnt/starwars/endor`
    * `getfacl /mnt/starwars/xwing`
* Comprobar las asignaciones de permisos anteriores, entrando con cada usuario y
creando ficheros en cada recurso si se puede.
    * `vdir /mnt/starwars/endor`
    * `vdir /mnt/starwars/xwing`

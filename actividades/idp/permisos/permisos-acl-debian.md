
# 1. Teoría sobre las ACL

## 1.1 Introducción

Las ACL son listas de control de accesos. Esto es otra forma de añadir permisos con otro nivel de
detalle similar al empleado en los Routers Cisco y en el sistema de ficheros NTFS

En una MV Debian hay que instalar el paquete `acl`, que es el que contiene los comandos: getfacl y setfacl.
Con getfacl consultamos las ACL y con setfacl las modificamos.

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

## 1.2 Ejemplo de activación manual

ACL añade más detalle al sistema clásico de permisos.
Para poder usar el comando setfacl, los ficheros deben estar en un sistema de ficheros
montado con la opción acl.

Por ejemplo si intentamos poner permisos ACL sin haber activado antes la partición
veremos un error como este: `setfacl: holamundo: La operación no está soportada`

Si quisiéramos activarlos únicamente de manera temporal para la sesión en la que nos
encontramos ejecutaríamos el siguiente comando: `mount /dev/partition -o defaults,acl /punto/de/montaje`.

Podemos usar el comando mount sin parámetros para verificar que todo está montado según nuestras intenciones.
A continuación probamos el comando setfacl con éxito, añadiendo permiso de lectura al fichero holamundo al usuario invitado.

```
    david@quigon:~/tmp/sh$ vdir
    total 4
    -rwxr-xr-x 1 david david 19 2011-02-03 22:52 holamundo*
    david@quigon:~/tmp/sh$ setfacl -m u:invitado:r holamundo
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

* Comprobamos que el resultado es el que esperábamos, ejecutando nuevamente getfacl.

## 1.3 Ejemplo de Activación automática

Para activar las ACL en la partición que queramos debemos modificar
el fichero /etc/fstab y luego reiniciar el equipo. Este fichero define que
particiones serán montadas automáticamente al iniciar el sistema, y con
qué parámetros se realizará dicha tarea.

Ejemplo de como editar el fichero `/etc/fstab` para activar las ACL en las particiones
deseadas de manera permanente: `/dev/partition /home ext3 defaults,acl 0 2`

Tras haber modificado /etc/fstab, para que monte una partición con el parámetro acl,
hemos reiniciado la máquina (comando reboot).

---

# 2. Práctica de ACL

Realizar las siguientes tareas:
* En la MV Debian, añadir un segundo disco duro de 100MB con una única partición formateada ext3.
* Iniciar MV. Comprobar los discos/particiones: `fdisk -l`
* Crear directorio `/mnt/world`.
* Crear un punto de montaje en `/etc/fstab` para el segundo disco.
Esto es, la partición /dev/sdb1 se montará en el directorio /mnt/world.
* Reiniciar el sistema y comprobar los puntos de montaje. Podemos usar los comandos `df -hT`, o `mount`.
> INFO: Ya tenemos montada en modo ACL la partición /dev/sdb1.
* Crear el grupo `angels`, con los usuarios `angel1`, `angel2`.
* Crear el grupo `deemons` con los usuarios `daemon1`, `daemon2`.
* Crear la carpeta `/mnt/world/heaven` con el usuario `root`, donde
    * `angel1` y `angel2` tienen permisos acl rwx,
    * pero el grupo `daemons` sólo tienen permiso rx.
* Crear la carpeta `/mnt/world/hell` con el usuario `root`,
    * donde `daemon1` y `daemon2` tienen permisos acl rwx.
* Comprobar las asignaciones de permisos anteriores, entrando con cada usuario y
creando ficheros en cada recurso si se puede.
    * `vdir /mnt/world/heaven`
    * `vdir /mnt/world/hell`

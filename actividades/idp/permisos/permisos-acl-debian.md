
# 1. Teoría sobre las ACL

Esta apartado es una explicación. Sólo para leer y entender.

---

## 1.1 Introducción

Las ACL son listas de control de accesos.
Esto es otra forma de añadir permisos con otro nivel de detalle
similar al empleado en los Routers Cisco y en el sistema de ficheros NTFS.

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

--

## 1.2 Ejemplo de activación manual

ACL añade más detalle al sistema clásico de permisos.
Para poder usar el comando `setfacl`, los ficheros deben estar en un sistema de ficheros montado con la opción `acl`.

Por ejemplo si intentamos poner permisos ACL sin haber activado antes la partición en modo `acl` veremos un error como este:  `setfacl: holamundo: La operación no está soportada`

Si quisiéramos activarlos únicamente de manera temporal para
la sesión en la que nos encontramos, ejecutaríamos el siguiente comando: `mount /dev/partition -o defaults,acl /punto/de/montaje`.

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

--

## 1.3 Ejemplo de Activación automática

Para activar las ACL en la partición que queramos debemos modificar
el fichero `/etc/fstab` y luego reiniciar el equipo. Este fichero define que
particiones serán montadas automáticamente al iniciar el sistema, y con
qué parámetros se realizará dicho montaje automático.

Ejemplo de como editar el fichero `/etc/fstab` para activar las ACL en las particiones deseadas de manera permanente: `/dev/sda2  /home ext3 defaults,acl 0 2`

Tras haber modificado /etc/fstab, para que monte una partición con el parámetro acl, debemos reiniciar la máquina (comando reboot).

---

# 2. Práctica de ACL

Realizar las siguientes tareas.
* [Configurar la MV](../../global/configuracion/debian.md).

---

## 2.1 Añadir disco

* En la MV Debian, añadir un segundo disco duro de 100MB con una única partición formateada ext3.
    * Esto debemos saber cómo se hace de las prácticas anteriores.
* Iniciar MV.
* OPCIONAL: Puedes instalar un entorno gráfico en la MV Debian si lo deseas.
    * Entra como usuario root.
    * `apt update`
    * `apt install -y xfce4`
* Comprobamos los discos:
    * `fdisk -l`, comprobar que los discos/particiones son correctos.
    * `df -hT`, comprobar qué particiones están montadas y dónde.

---

## 2.2 Montar el nuevo disco

* Crear directorio `/mnt/starwars`.

Ahora vamos a crear un nuevo punto de montaje en `/etc/fstab` para el segundo disco. Esto es, la partición `/dev/sdb1` que se montará en el directorio `/mnt/starwars`.

* Primero por seguridad, hacer una instantánea de la MV.
* Abre el fichero `/etc/fstab`
* Añade nueva línea para partición sdb1, Montar en el directorio `/mnt/starwars` y con los parámetros.
* `cat /etc/fstab`
* Reiniciar el sistema. Si la MV no arranca correctamente volver a la instantánea
anterior y revisar los últimos cambios realizados.
* Comprobar los puntos de montaje en el inicio:
    * `df -hT`
    * `mount`

¡Ya tenemos montada en modo ACL en la partición sdb1!

---

## 2.3 Poner permisos

Crear los grupos y usuarios:
* Crear el grupo `rebels`, con los usuarios `han`, `luke`.
* Crear el grupo `troopers` con los usuarios `trooper1`, `trooper2`.
Crear carpetas y poner permisos ACL:
* Crear la carpeta `/mnt/starwars/endor` con el usuario `root`:
    * donde la carpeta tendrá lo permisos clásicos 700.
    * donde el grupo `troopers` tienen permisos acl rwx,
    * donde el usuario `luke` tiene permisos acl rx.
* Crear la carpeta `/mnt/starwars/xwing` con el usuario `root`,
    * donde la carpeta tendrá lo permisos clásicos 700.
    * donde el usuario `han` tienen permisos acl rwx.
    * donde el usuario `luke` tienen permisos acl rx.
* Comprobar:
    * `getacl /mnt/starwars/endor`
    * `getacl /mnt/starwars/xwing`
* Comprobar las asignaciones de permisos anteriores, entrando con cada usuario y
creando ficheros en cada recurso si se puede.
    * `vdir /mnt/starwars/endor`
    * `vdir /mnt/starwars/xwing`


```
Estado : EN CONSTRUCCIÓN
```

# Quitar claves en GNU/Linux

Para quitar/cambiar las claves de un sistema desconocido haremos lo siguiente:
* Iniciar máquina con CD-LIVE de Knoppik
* Abrir terminal y convertirnos a super usuario.
* `fdisk -l` para identificar las particiones del disco duro.
* Supongamos que el SO está instalado en /dev/sda2.
* `mount /dev/sda2 /mnt`, montamos la partición para acceder a ella.
* `nano /mnt/etc/shadow`, editamos el fichero de claves de la partición montada.
* Modificamos la línea del usuario que queramos y limpiamos su valor de clave.

> **Veamos un ejemplo:**
>
> Esta es la línea del usuario root, los campos están separados por dos puntos. El segundo campo es la clave encriptada.
> ```
> root:$6$2ze3uZlZ$oIufVC1952pjevn90sPFRvIURLRU/104gcOTN.3tCnsf35InKbzcAKiZfI03.LK
7suBjV0yyre/SJqwEy9.Dp0:15960:0:99999:7:::
> ```
> Modificamos la línea como: `root::15960:0:99999:7:::`
>

* Grabamos los cambios, y reiniciamos el sistema sin el DVD.
* Ahora root tiene la clave vacía.

# Quitar claves en Windows
* Decargar distro GNU/Linux OphCrack.
* Reiniciar máquina Windows con la distro en la unidad de CD/DVD.
* Estará trabajando unos minutos y mostrará las claves que encuentre por el método de la fuerza bruta.

# Poner claves en GRUB2

Si ponemos claves al BootLoader sólo los usuarios autorizados podrán iniciar el SO.
Ver [vídeo](http://www.youtube.com/watch?v=IxdzR8LkwKE).

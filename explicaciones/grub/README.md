
```
Pendiente de comprobar
```

# Arrancar una imagen VHD desde GRUB2

Vamos a crear una nueva entrada en el menú del boot loader GRUB2 para usar un fichero de imagen VHD:
* Iniciamos la MV con GNU/Linux.
* Abrimos un terminal. Nos convertimos en superusuario.
* Ir a `/etc/grub.d` y editamos el fichero: `nano 40_custom`
* Vamos a desactivar la línea `exec tail...` poniendo una almohadilla delante como `#exec tail...`.
* Añadir el siguiente texto:

```
echo "[INFO] Añadiendo entrada VHD" >&2
cat<<EOF
menuentry "VHD Ubuntu, Linux 2.6.31-14-generic" {
	insmod vhd
	vhd vhd0 (hd0,1)/ubuntu-910/ubuntu-910-desktop-i386.vhd --partitions
	linux (vhd0,1)/boot/vmlinuz-2.6.31-14-generic root=/dev/sda1 vloop=/ubuntu-910/ubuntu-910-desktop-i386.vhd quiet splash
	initrd (vhd0,1)/boot/initrd.img-2.6.31-14-generic-vboot
}
EOF
```

Donde :
* `(hd0,1)` indica la partición 1 del primer disco
* `/ubuntu-910/ubuntu-910-desktop-i386.vhd`, ruta al fichero VHD.

> Información:
> * Personalizar versión del kernel con lo que tengamos en nuestro sistema VHD. Ejecutar "vdir /boot" para ver lo ficheros dentro del directorio boot.
> * Personalizar el valor que corresponda para la partición. En nuestro caso será 1 para la partición /dev/sda1.
> * Tenemos que desactivar la línea 'exec tail'. Para ello añadimos una almohadilla (#) al comienzo de la misma.

* Grabamos el fichero 40_custom, ponemos permisos de ejecución (chmod +x 40_custom).
* Hacer copia de seguridad del fichero de configuración: `cp /boot/grub2/grub.cfg /boot/grub2/grub.000`
* Actualizamos los cambios: `grub2-mkconfig -o /boot/grub2/grub.cfg`
* Reiniciamos el sistema y comprobamos los cambios.

---

# Convertir fichero imagen VDI en VHD

* Ejemplo por comandos:`vboxmanage clonehd WinXP.vdi F:\winxp.vhd --format VHD`
* Vídeo [How to Convert VDI to VHD in Virtualbox ](https://www.youtube.com/watch?v=DraC0YmJUas)

---

# Montar una imagen VHD en GRUB2 manualmente

Usando los siguientes comandos de GRUB, se pueden montar los datos de una imagen en formato VHD:

```
grub > insmod ntfs
grub > loopback loop (hd0,1)/test.vhd
grub > ls (loop,1)/
```

Donde:
* `(hd0,1)/test.vhd` es la ruta al fichero imagen VHD.

> NOTA
>
> Sólo funciona con VHDs en modo "fixed" o "static".
> No he podido hacerlo funcionar VHD en modo "dynamic".
>

---

# Arrancar una ISO desde GRUB2

* Vídeo [Boot from ISO files using Grub2 in Ubuntu](https://www.youtube.com/watch?v=Z35zyDpMIMM)

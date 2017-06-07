
```
Pendiente de comprobar
```

# Arrancar una imagen VHD desde GRUB2

Crear una entrada en GRUB2 como la siguiente:

```
menuentry "VHD Ubuntu, Linux 2.6.31-14-generic" {
	insmod vhd
	vhd vhd0 (hd0,1)/ubuntu-910/ubuntu-910-desktop-i386.vhd --partitions
	linux (vhd0,1)/boot/vmlinuz-2.6.31-14-generic root=/dev/sda1 vloop=/ubuntu-910/ubuntu-910-desktop-i386.vhd quiet splash
	initrd (vhd0,1)/boot/initrd.img-2.6.31-14-generic-vboot
}
```

Donde :
* `(hd0,1)` indica la partición 1 del primer disco
* `/ubuntu-910/ubuntu-910-desktop-i386.vhd`, ruta al fichero VHD.

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

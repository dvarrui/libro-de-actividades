
**Pendiente de comprobar**

#Mountar una imagen VHD usando GRUB2

Usando los siguientes comandos de GRUB, se pueden montar los datos de una imagen en formato VHD:

    grub > insmod ntfs
    grub > loopback loop (hd0,1)/test.vhd
    grub > ls (loop,1)/

Donde `test.vhd` es un fichero imagen VHD.

> NOTA
>
> Sólo funciona con VHDs en modo "fixed" o "static".
> No he podido hacerlo funcionar VHD en modo "dynamic".
>

#Arrancar una imagen VHD desde GRUB2
Crear una entrada como la siguiente:
```
menuentry "VHD Ubuntu, Linux 2.6.31-14-generic" {
	insmod vhd
	vhd vhd0 (hd0,1)/ubuntu-910/ubuntu-910-desktop-i386.vhd --partitions
	linux (vhd0,1)/boot/vmlinuz-2.6.31-14-generic root=/dev/sda1 vloop=/ubuntu-910/ubuntu-910-desktop-i386.vhd quiet splash
	initrd (vhd0,1)/boot/initrd.img-2.6.31-14-generic-vboot
}
```

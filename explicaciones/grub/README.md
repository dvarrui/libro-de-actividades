
```
Pendiente de comprobar
* 2017-06-21: Se hacen pruebas del punto 1. Resultado ERROR
```

# 1. Arrancar una imagen VHD Ubuntu desde GRUB2

Vamos a crear una nueva entrada en el menú del boot loader GRUB2 para usar un fichero de imagen VHD:
* Iniciamos la MV con GNU/Linux.
* Abrimos un terminal. Nos convertimos en superusuario.
* Ir a `/etc/grub.d` y editamos el fichero: `nano 40_custom`
* Vamos a desactivar la línea `exec tail...` poniendo una almohadilla delante.

* Este ejemplo requiere cargar el módulo vhd, pero no está instalado:

```
echo "[INFO] Añadiendo entrada VHD GNU/Linux" >&2
cat<<EOF
menuentry "VHD GNU/Linux" {
  insmod vhd
  vhd vhd0 (hd0,1)/vhd/linux.vhd --partitions
  linux (vhd0,1)/boot/vmlinuz root=/dev/sda1 vloop=/vhd/linux.vhd quiet splash
  initrd (vhd0,1)/boot/initrd
}
EOF
```
* Este ejemplo requiere usa loopback:

```
echo "[INFO] Añadiendo entrada VHD GNU/Linux" >&2
cat<<EOF
menuentry "VHD GNU/Linux" {
  loopback loop (hd1,1)/home/vhd/linux.vhd --partitions
  linux (loop)/boot/vmlinuz root=/dev/sda1 vloop=/vhd/linux.vhd quiet splash
  initrd (loop)/boot/initrd
}
EOF
```

Donde:
* `(hd0,1)` indica la partición 1 del primer disco.
* `/vhd/linux.vhd`, ruta al fichero VHD.

> Información:
> * Personalizar versión del kernel con lo que tengamos en nuestro sistema VHD. Ejecutar "vdir /boot" para ver lo ficheros dentro del directorio boot.
> * Personalizar el valor que corresponda para la partición. En nuestro caso será 1 para la partición /dev/sda1.
> * Tenemos que desactivar la línea 'exec tail'. Para ello añadimos una almohadilla (#) al comienzo de la misma.

* Grabamos el fichero 40_custom, ponemos permisos de ejecución (`chmod +x 40_custom`).
* Hacer copia de seguridad del fichero de configuración: `cp /boot/grub2/grub.cfg /boot/grub2/grub.000`
* Actualizamos los cambios:  
   * En OpenSUSE,`grub2-mkconfig -o /boot/grub2/grub.cfg`
	 * En Ubuntu/Debian `update-grub2`.
* Reiniciamos el sistema y comprobamos los cambios.

---

# 2. Convertir fichero imagen VDI en VHD

* Cuando creamos una nueva MV con VirtualBox podemos elegir formato VDI, VHD, etc.
* Si ya tenemos la MV creado con VDI podemos convertirla a VHD por comandos.
Ejemplo: `vboxmanage clonehd WinXP.vdi F:\winxp.vhd --format VHD`
* Vídeo [How to Convert VDI to VHD in Virtualbox ](https://www.youtube.com/watch?v=DraC0YmJUas)

---

# 3. Arrancar una imagen VHD Windows desde GRUB2

Crear una entrada en GRUB2 para arrancar Windows.

**Opción 1**

Enlace de interés:
* [Grub2 + VHD: installation and loading of Windows7](http://developers-club.com/posts/228641/)

**Opción 2**

Propuesta de cambio menuentry (SIN PROBAR):
```
echo "[INFO] Añadiendo entrada Windows VHD" >&2
cat<<EOF
menuentry "VHD Windows" {
  insmod ntfs
  loopback loop (hd0,1)/vhd/windows.vhd
  set root=(loop,1)
  chainloader +1
}
EOF
```

Donde:
* `(hd0,1)` indica la partición 1 del primer disco
* `/vhd/windows.vhd`, ruta al fichero VHD.

---

# 4. Montar una imagen VHD en GRUB2 manualmente

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

# 5. Arrancar una ISO desde GRUB2

* Vídeo [Boot from ISO files using Grub2 in Ubuntu](https://www.youtube.com/watch?v=Z35zyDpMIMM)

---

# ANEXO

```
echo "[INFO] Añadiendo entrada Windows VHD" >&2
cat<<EOF
menuentry "VHD Windows" {
	insmod vhd
	vhd vhd0 (hd0,1)/vhd/windows.vhd --partitions
  set root=(hd0,1)
  chainloader +1
}
EOF
```

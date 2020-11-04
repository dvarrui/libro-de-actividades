
# BootLoader (Debian y WindowsXP)

El *Boot loader* es el programa encargado de la carga el sistema operativo.
Una vez se termina de cargar el sistema, entonces comienza la ejecución del sistema operativo.
GRUB2 es el actual *boot loader* de GNU/Linux, como NTLoader lo es de WindowsXP, y bcdedit de Windows 7, etc.

* Entregar documento en formato ODT o PDF con capturas de pantalla.
* Vamos a trabajar con la MV de la instalación dual.

> Información general:
>
> * El programa GRUB2 es el nuevo gestor de arranque de las distribuciones GNU/Linux, aunque es posible sustituirlo por otros como GRUB Legacy, LILO, etc.
> * En el directorio /boot están los programas y ficheros necesarios para la carga del sistema operativo. Hay estarán los ficheros vmlinuz e initrd: vdir /boot. Necesitaremos estos datos más adelante.
> * Además en el directorio /etc/grub.d se almacenan ficheros que nos permiten personalizar la configuración de GRUB2. En esta práctica vamos a modificar alguno de estos ficheros de configuración de GRUB2.
> * Para consultar el identificador UUID de una partición podemos usar los comandos siguientes: `blkid /dev/sdaX`, o `ls -l /dev/disk/by-uuid`.
>
> Enlaces de interés:
> * [GRUB2 Documentation] (http://www.gnu.org/software/grub/grub-documentation.html)
> * [Tutorial sobre la personalización de GRUB2] (http://linuxzone.es/2012/01/22/la-forma-mas-simple-de-personalizar-tu-grub-y-sin-tocarlo/)

# 1.Debian/Ubuntu. GRUB2

## 1.1 Añadir entrada SO Linux a GRUB2 (Debian/Ubuntu)
* Abrir un terminal y entrar como superusuario (comando su).
* Ir a `/etc/grub.d`
* Editamos el fichero(Script) `nano 40_custom` y vamos a modificarlo para
dar las órdenes necesarias para la creación de una nueva entrada en el menú
de inicio del sistema.
* Añadir las siguientes líneas al fichero:
```
echo "[INFO] Añadiendo GNU/Linux" >&2
cat<<EOF
menuentry "Iniciar GNU/Linux (David 2013)" {
set root=(hd0,X)
linux /boot/vmlinuz-Y root=/dev/sdaZ
initrd /boot/initrd-Y
}
EOF
```

> Información:
>
> * Sustituir X por el número de la partición donde está instalado el SO GNU/Linux. [OJO] GRUB2 numera las particiones secuencialmente empezando desde 1. De modo que ejecutamos el comando "fdisk -l" y contamos las particiones para averiguar su número.
> * Sustituir Y por la versión del kernel que queremos iniciar. Personalizar con lo que tengamos en nuestro sistema. Ejecutar "vdir /boot" para ver lo ficheros dentro del directorio boot.
> * Sustituir Z por el valor que corresponda para la partición raíz del sistema. [INFO] El lugar de root=/dev/sdaZ, se puede poner root=UUID=valor-UUID-de-nuestra-partición-raiz. Tanto UUID como /dev/sda? son dos formas distintas de determinar una partición.
> * [INFO] Tenemos que desactivar la línea 'exec tail'. Para eso añadimos una almohadilla (#) al comienzo de la misma.

Continuamos:
* Guardamos el fichero `/etc/grub.d/40_custom`
* Lo hacemos ejecutable: `chmod +x 40_custom`
* Ejecutamos el comando: `update-grub2` para activar los cmabios de la nueva configuración.

Veamos imagen:

![debian-update-grub2](./images/debian-update-grub2.png)

Si reiniciamos el sistema debemos ver algo como:

![debian-bootmenu](./images/debian-bootmenu.png)

# 1.2 Añadir entrada SO Windows a GRUB2

* Ahora vamos a añadir otra entrada para el sistema Windows.
* Añadir las siguientes líneas al fichero /etc/grub.d/40_custom:

```
echo "[INFO] Añadiendo entrada Windows" >&2
cat<<EOF
menuentry "Iniciar Windows (David 2013)" {
set root=(hd0,X)
chainloader +1
}
EOF
```

> Sustituir X por el número de la partición donde está Windows.
> Actualizamos GRUB2 con el comando "update-grub2"
> Atención a los mensajes de salida por si aparece algún error o warning. Si todo es correcto, reiniciamos el sistema y comprobamos los cambios (Captura del menú de inicio del sistema).

## 1.3 Cambio del aspecto de GRUB2

* Entramos en GNU/Linux. Abrimos terminal como superusuario.
* Ir a /etc/grub.dy editamos el fichero 05_debian_theme
* Vamos a modificar los colores del menú de arranque, cambiando el valor de las opciones menu_color_normal y menu_color_highlight. Los valores black/blue significan color de frente negro y color de fondo azul. Elige tu combinación de colores.
* Escoge una imagen para el fondo del menú GRUB2. Debe estar grabada con formato RGB 8 bits y con extensión tga, png o jpg. Puedes usar GIMP para ayudarte a generar la imagen con estos valores.
* Guardar la imagen como: /boot/grub/nombre-de-la-imagen.png
* Editar el fichero /etc/default/grub y añadir al final: GRUB_BACKGROUND=/boot/grub/nombre-de-la-imagen.png
* Para grabar los cambios hacemos: `update-grub2`
* Reiniciar el sistema y comprobar los resultados. Captura de pantalla.

# 2. Windows XP Loader

Vamos a realizar la práctica con el sistema que tengamos instalado en nuestra instalación Dual.
No es necesario hacerlo con cada Windows.

## 2.1 Crear otra entrada Windows XP

* Iniciar el sistema Windows XP.
* Abrir el fichero C:\boot.ini
* Crear una segunda entrada de SO y para WindowsXP de la siguiente forma:
```
[boot loader]
timeout=30
default=multi...
[operating systems]
multi....
multi...."Windows XP 2"
```
* Se añade una segunda entrada en el apartado de sistemas operativos.
* Reiniciar el sistema y comprobar los cambios.

## 2.2 Crear entrada para Linux en XP

Crear fichero de arranque
* Iniciar el sistema GNU/Linux. Abrir un terminal y entrar como superusuario.
* Ejecutar el comando: "dd if=/dev/sda of=linux.bin bs=512 count=1". Con esto copiamos el contenido del sector 0 en el fichero "linux.bin".
* Con esto obtenemos el fichero "linux.bin" que vamos a copiar en la partición de WindowsXP en la ruta C:\linux.bin.
Copiar fichero a Windows
* Iniciar el sistema Windows XP.
* Abrir el fichero C:\boot.ini
* Crear una segunda entrada de SO y para GNU/Linux, añadiendo la configuración en el apartado de sistemas operativos de la siguiente forma:

```
[boot loader]
timeout=30
default=multi...
[operating systems]
multi....
C:\linux.bin="Iniciar Linux desde NTLoader(WXP)"
```

* Reiniciar el sistema y probar los cargadores de ambos sistemas operativos.

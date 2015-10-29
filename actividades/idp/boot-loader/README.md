
#BootLoader

El *Boot loader* es el programa encargado de la carga el sistema operativo.
Una vez se termina de cargar el sistema, entonces comienza la ejecución del sistema operativo. 
GRUB2 es el actual *boot loader* de GNU/Linux, como NTLoader lo es de WindowsXP, y bcdedit de Windows 7, etc.

* Entregar documento en formato ODT o PDF con capturas de pantalla.
* Sólo elegir 1 GNU/Linux y 1 Windows para la práctica.
* Para GNU/Linux podemos usar alguna de las MV's que ya tenemos: OpenSUSE, Debian. Se supone que todos estos sistemas tienen GRUB2 como bootloader.
* Para Windows podemos usar alguna de la MV's de las que dispongamos.

> Información general:
>
> * El programa GRUB2 es el nuevo gestor de arranque de las distribuciones GNU/Linux, aunque es posible sustituirlo por otros como GRUB Legacy, LILO, etc.
> * En el directorio /boot están los programas y ficheros necesarios para la carga del sistema operativo. Hay estarán los ficheros vmlinuz e initrd: vdir /boot. Necesitaremos estos datos más adelante.
> * Además en el directorio /etc/grub.d se almacenan ficheros que nos permiten personalizar la configuración de GRUB2. En esta práctica vamos a modificar alguno de estos ficheros de configuración de GRUB2.
> * Para consultar el identificador UUID de una partición podemos usar los comandos siguientes: `blkid /dev/sdaX`, o `ls -l /dev/disk/by-uuid`.
>
> Enlaces de interés:
> * [GRUB2 Documentation] (http://www.gnu.org/software/grub/grub-documentation.html)
> * [GRUB2 OpenSUSE Documentation] (http://activedoc.opensuse.org/book/opensuse-reference)
> * [Tutorial sobre la personalización de GRUB2] (http://linuxzone.es/2012/01/22/la-forma-mas-simple-de-personalizar-tu-grub-y-sin-tocarlo/)

#1. OpenSUSE
##1.1 Añadir entrada SO Linux a GRUB2
Vamos a crear una nueva entrada en el menú del boot loader:
* Abrimos un terminal. Nos convertimos en superusuario. Instalamos el editor nano en OpenSuse: `zypper install nano`
* Ir a `/etc/grub.d` y editamos el fichero: `nano 40_custom`
* Añadir las siguientes líneas:
```
echo "Añadiendo GNU/Linux" >&2
cat<<EOF
menuentry "Iniciar GNU/Linux desde GRUB2 (David 2013)" {
linux /boot/vmlinuz-Y root=/dev/sdaZ
initrd /boot/initrd-Y
}
EOF
```

> Información:
> * Sustituir Y por la versión del kernel que queremos iniciar. Personalizar con lo que tengamos en nuestro sistema. Ejecutar "vdir /boot" para ver lo ficheros dentro del directorio boot.
> * Sustituir Z por el valor que corresponda. En nuestro caso será 7 para la partición /dev/sda7.
> * Tenemos que desactivar la línea 'exec tail'. Para ello añadimos una almohadilla (#) al comienzo de la misma.

Veamos un ejemplo con una configuración diferente:

![grub2-menuentry-gnulinux] (./grub2-menuentry-gnulinux.png)

* Grabamos el fichero 40_custom, ponemos permisos de ejecución (chmod +x 40_custom).
* Hacer copia de seguridad del fichero de configuración: `cp /boot/grub2/grub.cfg /boot/grub2/grub.000`
* Actualizamos los cambios: `grub2-mkconfig -o /boot/grub2/grub.cfg`
* Reiniciamos el sistema y comprobamos los cambios. Capturar imagen.

Veamos un ejemplo de un menú de inicio:

![grub2-menu-screen] (./grub2-menu-screen.png)

> Información sobre Yast2:
> * Yast2 es una herramienta gráfica para OpenSUSE que sirve para gestionar el bootloader (GRUB2 en nuestro caso).
> * Entramos en la consola como superusario, y escribimos el comando `yast2 bootloader`, o bien `/sbin/yast2 bootloader &`.
> * Una vez iniciada la aplicación gráfica, ir a "Opciones del cargador de arranque". Ahí podemos configurar diversos aspectos del cargador del sistema de forma cómoda. Como por ejemplo: ocultar el menú, modificar timeout, etc.
>
> Veamos una imagen:
>
> ![grub2-yast2-bootloader] (./grub2-yast2-bootloader.png)
>

##1.2 Añadir entrada SO Windows a GRUB2
Vamos a crear una nueva entrada del menú de carga para el SO Windows:
* Ahora vamos a añadir otra entrada para el sistema Windows.
* Añadir las siguientes líneas al fichero `/etc/grub.d/40_custom`, 
Sustituir X por el número de la partición donde está Windows:
```
echo "Añadiendo entrada Windows" >&2
cat<<EOF
menuentry "Iniciar Windows desde GRUB2" {
set root=(hd0,X)
chainloader +1
}
EOF
```

* Para actualizar los cambios debemos usar el comando: `grub2-mkconfig -o /boot/grub2/grub.cfg`.
* Atención a los mensajes de salida por si aparece algún error o warning.
Si todo es correcto, reiniciamos el sistema y comprobamos los cambios (Captura del menú de inicio del sistema).

##1.3 Cambiar la apariencia
Vamos a cambiar la apariencia del boot loader
* Abrimos terminal como superusuario.
* Editar el fichero /boot/grub2/themes/openSUSE/theme.txt y modificar los colores. Por ejemplo, podríamos modificar las entradas siguientes:
```
    boot_menu -> item_color="#fff" (Entradas de menú en blanco)
    boot_menu -> selected_item-color="#000" (Entradas de menú seleccionada en negro)
```
* Escoge una imagen para el fondo del menú GRUB2. Debe estar grabada con formato RGB 8 bits y con extensión tga, png o jpg. Puedes usar GIMP para ayudarte.
* Guardar la imagen como: /boot/grub2/themes/openSUSE/nombreimagen.png
* Editar fichero /boot/grub2/themes/openSUSE/theme.txt, y modificarlo para poner nuestra imagen: "desktop-image: nombreimagen.png"
* Grabamos el fichero y salimos del editor.
* Para actualizar la configuración de GRUB2 hacemos: "grub2-mkconfig -o /boot/grub2/grub.cfg"
* Reiniciar el sistema y comprobar los resultados. Captura de pantalla.


#2. Windows 7

Pasos previos:
> INFO
>
> * Vamos a realizar la práctica con el sistema que tengamos instalado en nuestra instalación Dual.
> * No es necesario hacerlo con los dos SO Windows.

* Vamos a entrar a Window7.
* En la línea de comandos ejecutamos el comando "bcdedit" para consultar la configuración actual del boot loader. El comando "msconfig" nos muestra también información del arranque.

> Si tenemos problemas con el comando "bcdedit", es posible que se solucionen activando el sistema y manteniéndolo actualizado.

OBJETIVO de la práctica:
* Modificar la configuración del boot loader de Windows7 para que al iniciarse Windows aparezcan dos entradas en el menú de boot. Por ejemplo una para Windows y otra para GNU/Linux, o bien 2 de windows si no tenemos instalación dual.

Enlaces de interés:
* [Documentación "Añadir SO Linux en el arranque de Windows7"] (http://www.taringa.net/posts/linux/14679925/Insertar-linux-en-el-arranque-de-windows.html)
* [Inicio dual de dos sistemas operativos desde Windows] (https://norfipc.com/articulos/dos-sistemas-operativos-inicio-dual.html)
* [Vídeo](https://youtu.be/t1x_ibu9BLE) de cómo cambiar el orden de un sistema operativo en el menú de arranque (Windows 7 y Windows 8)


#ANEXO
A continuación tenemos información de cómo trabajar con el boot loader de Debian y de Windows XP.

##3.Debian/Ubuntu. GRUB2
###3.1 Añadir entrada SO Linux a GRUB2 (Debian/Ubuntu)
* Abrir un terminal y entrar como superusuario (comando su).
* Ir a `/etc/grub.d`
* Editamos el fichero(Script) `nano 40_custom` y vamos a modificarlo para
dar las órdenes necesarias para la creación de una nueva entrada en el menú
de inicio del sistema.
* Añadir las siguientes líneas al fichero:
```
echo "Añadiendo GNU/Linux" >&2
cat<<EOF
menuentry "Iniciar GNU/Linux desde GRUB2" {
set root=(hd0,X)
linux /boot/vmlinuz-Y root=/dev/sdaZ
initrd /boot/initrd-Y
}
EOF
```

> Información:
> * Sustituir X por el número de la partición donde está instalado el SO GNU/Linux. [OJO] GRUB2 numera las particiones secuencialmente empezando desde 1. De modo que ejecutamos el comando "fdisk -l" y contamos las particiones para averiguar su número.
> * Sustituir Y por la versión del kernel que queremos iniciar. Personalizar con lo que tengamos en nuestro sistema. Ejecutar "vdir /boot" para ver lo ficheros dentro del directorio boot.
> * Sustituir Z por el valor que corresponda para la partición raíz del sistema. [INFO] El lugar de root=/dev/sdaZ, se puede poner root=UUID=valor-UUID-de-nuestra-partición-raiz. Tanto UUID como /dev/sda? son dos formas distintas de determinar una partición.
> * [INFO] Tenemos que desactivar la línea 'exec tail'. Para eso añadimos una almohadilla (#) al comienzo de la misma.

Continuamos:
* Guardamos el fichero `/etc/grub.d/40_custom`
* Lo hacemos ejecutable: `chmod +x 40_custom`
* Ejecutamos el comando: `update-grub2` para activar los cmabios de la nueva configuración.

Veamos imagen:

![debian-update-grub2] (./debian-update-grub2.png)

Si reiniciamos el sistema debemos ver algo como:

![debian-bootmenu] (./debian-bootmenu.png)

###3.2 Añadir entrada SO Windows a GRUB2
* Ahora vamos a añadir otra entrada para el sistema Windows.
* Añadir las siguientes líneas al fichero /etc/grub.d/40_custom:
```
echo "Añadiendo entrada Windows" >&2
cat<<EOF
menuentry "Iniciar Windows desde GRUB2" {
set root=(hd0,X)
chainloader +1
}
EOF
```
> Sustituir X por el número de la partición donde está Windows.
> Actualizamos GRUB2 con el comando "update-grub2"
> Atención a los mensajes de salida por si aparece algún error o warning. Si todo es correcto, reiniciamos el sistema y comprobamos los cambios (Captura del menú de inicio del sistema).

###3.3 Cambio del aspecto de GRUB2
* Entramos en GNU/Linux. Abrimos terminal como superusuario.
* Ir a /etc/grub.dy editamos el fichero 05_debian_theme
* Vamos a modificar los colores del menú de arranque, cambiando el valor de las opciones menu_color_normal y menu_color_highlight. Los valores black/blue significan color de frente negro y color de fondo azul. Elige tu combinación de colores.
* Escoge una imagen para el fondo del menú GRUB2. Debe estar grabada con formato RGB 8 bits y con extensión tga, png o jpg. Puedes usar GIMP para ayudarte a generar la imagen con estos valores.
* Guardar la imagen como: /boot/grub/nombre-de-la-imagen.png
* Editar el fichero /etc/default/grub y añadir al final: GRUB_BACKGROUND=/boot/grub/nombre-de-la-imagen.png
* Para grabar los cambios hacemos: `update-grub2`
* Reiniciar el sistema y comprobar los resultados. Captura de pantalla.


##4. Windows XP Loader

Vamos a realizar la práctica con el sistema que tengamos instalado en nuestra instalación Dual. 
No es necesario hacerlo con cada Windows.

###4.1 Crear otra entrada Windows XP
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

###4.2 Crear entrada para Linux en XP
Crear fichero de arranque
* Iniciar el sistema GNU/Linux. Abrir un terminal y entrar como superusuario.
* Ejecutar el comando: "dd if=/dev/sda of=linux.bin bs=512 count=1". Con esto copiamos el contenido del sector 0 en el fichero "linux.bin".
* Con esto obtenemos el fichero "linux.bin" que vamos a copiar en la partición de WindowsXP en la ruta C:\linux.bin.
Copiar fichero a Windows
* Iniciar el sistema Windows XP.
* Abrir el fichero C:\boot.ini
* Crear una segunda entrada de SO y para GNU/Linux, añadiendo la configuración en el apartado de sistemas operativos.
de la siguiente forma:
```
[boot loader]
timeout=30
default=multi...
[operating systems]
multi....
C:\linux.bin="Iniciar Linux desde NTLoader(WXP)"
```
* Reiniciar el sistema y probar los cargadores de ambos sistemas operativos.


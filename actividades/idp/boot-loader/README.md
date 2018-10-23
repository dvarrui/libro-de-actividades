
# BootLoader (OpenSUSE y Windows7)

El *Boot loader* es el programa encargado de la carga el sistema operativo.
Una vez se termina de cargar el sistema, entonces comienza la ejecución del sistema operativo.
GRUB2 es el actual *boot loader* de GNU/Linux, como NTLoader lo es de WindowsXP, y bcdedit de Windows 7, etc.

## Entrega

* Entregar documento en formato ODT o PDF con capturas de pantalla.
* Vamos a trabajar con la MV de la instalación dual.
* W7 debe ser Enterprise.

> Enlaces de interés:
>
> * [GRUB2 Documentation] (http://www.gnu.org/software/grub/grub-documentation.html)
> * [GRUB2 OpenSUSE Documentation] (http://activedoc.opensuse.org/book/opensuse-reference)
> * [Tutorial sobre la personalización de GRUB2] (http://linuxzone.es/2012/01/22/la-forma-mas-simple-de-personalizar-tu-grub-y-sin-tocarlo/)

---

# 1. OpenSUSE

## 1.1 Añadir entrada SO Linux a GRUB2
Vamos a crear una nueva entrada en el menú del boot loader:
* Iniciamos la MV con GNU/Linux.
* Abrimos un terminal. Nos convertimos en superusuario.
* Instalamos el editor nano en OpenSuse: `zypper install nano`
* Ir a `/etc/grub.d` y editamos el fichero: `nano 40_custom`
* Vamos a desactivar la línea `exec tail...` poniendo una almohadilla delante como `#exec tail...`.

> * El programa GRUB2 es el nuevo gestor de arranque de las distribuciones GNU/Linux, aunque es posible sustituirlo por otros como GRUB Legacy, LILO, etc.
> * En el directorio /boot están los programas y ficheros necesarios para la carga del sistema operativo. Hay estarán los ficheros vmlinuz e initrd: vdir /boot. Necesitaremos estos datos más adelante.
> * Además en el directorio /etc/grub.d se almacenan ficheros que nos permiten personalizar la configuración de GRUB2. En esta práctica vamos a modificar alguno de estos ficheros de configuración de GRUB2.
> * Para consultar el identificador UUID de una partición podemos usar los comandos siguientes: `blkid /dev/sdaX`, o `ls -l /dev/disk/by-uuid`.

* Añadir las siguientes líneas:
```
echo "[INFO] Estoy añadiendo entrada GNU/Linux" >&2
cat<<EOF
menuentry "Iniciar GNU/Linux (NOMBRE-DEL-ALUMNO 2018)" {
  linux /boot/vmlinuz root=/dev/sdaZ
  initrd /boot/initrd
}
EOF
```

> Información:
>
> * Sustituir Y por la versión del kernel que queremos iniciar. Personalizar con lo que tengamos en nuestro sistema. Ejecutar "vdir /boot" para ver lo ficheros dentro del directorio boot.
> * Sustituir Z por el valor que corresponda. En nuestro caso será 7 para la partición /dev/sda7.
> * Tenemos que desactivar la línea 'exec tail'. Para ello añadimos una almohadilla (#) al comienzo de la misma.

* Grabamos el fichero 40_custom.
* `chmod +x 40_custom`, ponemos permisos de ejecución.
* Hacer copia de seguridad del fichero de configuración: `cp /boot/grub2/grub.cfg /boot/grub2/grub.000`
* `grub2-mkconfig -o /boot/grub2/grub.cfg`, actualizamos los cambios.
* Reiniciamos el sistema y comprobamos los cambios. Capturar imagen.

Veamos un ejemplo de un menú de inicio:

![grub2-menu-screen](./images/grub2-menu-screen.png)

> **Información sobre Yast2:**
>
> * Yast2 es una herramienta gráfica para OpenSUSE que sirve para gestionar el bootloader (GRUB2 en nuestro caso).
> * Entramos en la consola como superusario, y escribimos el comando `yast2 bootloader`, o bien `/sbin/yast2 bootloader &`.
> * Una vez iniciada la aplicación gráfica, ir a "Opciones del cargador de arranque". Ahí podemos configurar diversos aspectos del cargador del sistema de forma cómoda. Como por ejemplo: ocultar el menú, modificar timeout, etc.

## 1.2 Añadir entrada SO Windows a GRUB2

Vamos a crear una nueva entrada del menú de carga para el SO Windows:
* Iniciamos la MV con GNU/Linux.
* Abrimos el fichero `/etc/grub.d/40_custom`.
    * Añadir las siguientes líneas al final.
    * Sustituir X por el número de la partición donde está Windows:

```
echo "[INFO] Añadiendo entrada Windows" >&2
cat<<EOF
menuentry "Iniciar Windows (NOMBRE-DEL-ALUMNO 2018)" {
  set root=(hd0,X)
  chainloader +1
}
EOF
```

* Grabamos el fichero 40_custom.
* `grub2-mkconfig -o /boot/grub2/grub.cfg`, Actualizar los cambios.
    * Atención a los mensajes de salida por si aparece algún error o warning.
* Si todo es correcto, reiniciamos el sistema y comprobamos los cambios (Captura del menú de inicio del sistema).

---

## 2 Cambiar la apariencia

Vamos a cambiar la apariencia del boot loader
* Abrimos terminal como superusuario.
* Editar el fichero `/boot/grub2/themes/openSUSE/theme.txt` y modificar los colores.
    * Por ejemplo, podríamos modificar las entradas siguientes:

```
boot_menu -> item_color="#fff" (Entradas de menú en blanco)
boot_menu -> selected_item-color="#000" (Entradas de menú seleccionada en negro)
```

* Escoge una imagen para el fondo del menú GRUB2. Debe estar grabada con formato RGB 8 bits y con extensión tga, png o jpg. Puedes usar GIMP para ayudarte.
* Guardar la imagen como: /boot/grub2/themes/openSUSE/nombreimagen.png
* Editar fichero `/boot/grub2/themes/openSUSE/theme.txt`.
    * Modificarlo para poner nuestra imagen: `desktop-image: nombreimagen.png`.
* Grabamos el fichero y salimos del editor.
* `grub2-mkconfig -o /boot/grub2/grub.cfg`, para actualizar la configuración de GRUB2 hacemos.
* Reiniciar el sistema y comprobar los resultados. Captura de pantalla.

> **Enlaces de interés**
>
> * [Agregar nueva entrada al menú de Grub](https://es.opensuse.org/SDB:C%C3%93MO_Agregar_nueva_entrada_de_men%C3%BA_Grub)
> * [Cambiar el tiempo de espera](https://es.opensuse.org/SDB:C%C3%93MO_Cambiar_la_opci%C3%B3n_y_el_tiempo_de_espera_por_defecto_en_GRUB)
> * [Cambiar la pantalla de arranque](https://es.opensuse.org/SDB:C%C3%93MO_Cambiar_la_pantalla_de_arranque_de_Grub)
> * [Personalizar la pantalla de arranque](https://es.opensuse.org/SDB:C%C3%93MO_Personalizar_la_pantalla_de_arranque)
>

---

# 3. Windows 7

> Vamos a realizar la práctica con el sistema que tengamos instalado en nuestra instalación Dual. No es necesario hacerlo con los dos SO Windows.

* Vamos a entrar a Window Enterprise.
* En la línea de comandos ejecutamos el comando `bcdedit` para consultar la configuración actual del boot loader. Si tenemos problemas con el comando, comprobar que tenemos la versión Enterprise del SO Windows.
* El comando "msconfig" nos muestra también información del arranque.

OBJETIVO de la práctica:
* Modificar la configuración del boot loader de Windows7 para que al iniciarse Windows aparezcan dos entradas en el menú de boot. Por ejemplo una para Windows y otra para GNU/Linux, o bien 2 de windows si no tenemos instalación dual.

> **Información sobre BCDEDIT**
> * `bdcedit` Muestra la configuración del boot loader
> * `bcdedit /copy {current} /d "DebugEntry"` Copia la entrada de menú actual en otra nueva
> * `bcdedit /displayorder {49916baf-0e08-11db-9af4-000bdbd316a0} /addlast` Cambiar el orden de las opciones del menú
> * `bcdedit /delete {49916baf-0e08-11db-9af4-000bdbd316a0}` Eliminar una entrada del menú
>
> **Enlaces de interés:**
> * [Adding Boot Entries](https://msdn.microsoft.com/en-us/library/windows/hardware/ff541231%28v=vs.85%29.aspx)
> * [Create new entry with `bdcedit`](http://superuser.com/questions/511582/how-to-use-bcdedit-to-dual-boot-windows-installations)
> * [Documentación "Añadir SO Linux en el arranque de Windows7"] (http://www.taringa.net/posts/linux/14679925/Insertar-linux-en-el-arranque-de-windows.html)
> * [Inicio dual de dos sistemas operativos desde Windows] (https://norfipc.com/articulos/dos-sistemas-operativos-inicio-dual.html)
> * [Vídeo](https://youtu.be/t1x_ibu9BLE) de cómo cambiar el orden de un sistema operativo en el menú de arranque (Windows 7 y Windows 8)

---

# ANEXO

* [Reinstalar GRUB2 en Ubuntu](http://www.guia-ubuntu.org/index.php?title=Recuperar_GRUB)

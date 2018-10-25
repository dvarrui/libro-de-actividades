
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

* Hacer una instantánea de la MV antes de seguir.

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
* `chmod +x 40_custom`, ponemos permisos de ejecución al archivo indicado.
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
    * Modificarlo para poner nuestra imagen: ` + image -> file = nombreimagen.png`.
* Grabamos el fichero y salimos del editor.
    * Si tenemos problemas... lo más probable es que no tengamos permisos suficientes. ¡OJO¡ sólo el usuario root puede escribir en ese directorio.
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

El objetivo es el de modificar la configuración del boot loader de Windows7 para que al iniciarse Windows aparezcan dos entradas en el menú de boot. Por ejemplo una para Windows y otra para GNU/Linux, o bien 2 de windows si no tenemos instalación dual.

## 3.1 Consultar la Información

* Hacer una instantánea de la MV antes de seguir.
* Vamos a entrar a Windows Enterprise.
* Abrir consola `CMD` como administrador.
* `bcdedit` Muestra la configuración del boot loader.

Si aparece este error consulta el siguiente apartado.


Debe salir algo parecido a esto:

```
C:\Windows\system32>bcdedit

Administrador de arranque de Windows
----------------------------------
Identificador {bootmgr}
devicepartition=\Device\HarddiskVolume2
description Windows Boot Manager
localeen-US
inherit {globalsettings}
default {current}
resumeobject{f7492313-139b-11e3-af14-ce9d489bae23}
displayorder{current}
toolsdisplayorder {memdiag}
timeout 30

Cargador de arranque de Windows
-----------------------------
Identificador {current}
devicepartition=C:
path\Windows\system32\winload.exe
description Windows
localezh-CN
inherit {bootloadersettings}
recoverysequence{f7492315-139b-11e3-af14-ce9d489bae23}
recoveryenabled Yes
testsigning Yes
osdevicepartition=C:
systemroot\Windows
resumeobject{f7492313-139b-11e3-af14-ce9d489bae23}
nxOptIn
```

## 3.2 SOLO Si tenemos problemas

Hacer esto SOLO en caso de error.

¿Tienes este error?
```

C:\Users\Solvetic-Vaio>bcdedit
No se pudo abrir el almacén de datos de configuración de arranque (BCD).
Acceso denegado.
```
Comprueba lo siguiente:
* Iniciar CMD como administrador
* La versión de Windows 7 debe ser la Enterprise
* [CHow to fix Windows 7/8/10 boot problem with BCDEDIT](https://atomicit.ca/kb/articles/how-to-fix-windows-7810-boot-problem-with-bcdedit/)

## 3.3 Crear una nueva entrada

* `bcdedit /copy {current} /d "nombre-del-alumno (2018)"` Copia la entrada de menú actual en otra nueva
* Cambiar el orden de las opciones del menú. Ejemplo: `bcdedit /displayorder {49916baf-0e08-11db-9af4-000bdbd316a0} /addlast`

> INFO: El comando "msconfig" nos muestra también información del arranque.
>
> * **Información sobre BCDEDIT**: `bcdedit /delete {49916baf-0e08-11db-9af4-000bdbd316a0}` Eliminar una entrada del menú
>
> **Enlaces de interés:**
> * [Adding Boot Entries](https://msdn.microsoft.com/en-us/library/windows/hardware/ff541231%28v=vs.85%29.aspx)
> * [Create new entry with `bdcedit`](http://superuser.com/questions/511582/how-to-use-bcdedit-to-dual-boot-windows-installations)
> * [Documentación "Añadir SO Linux en el arranque de Windows7"] (http://www.taringa.net/posts/linux/14679925/Insertar-linux-en-el-arranque-de-windows.html)
> * [Inicio dual de dos sistemas operativos desde Windows] (https://norfipc.com/articulos/dos-sistemas-operativos-inicio-dual.html)
> * [Vídeo](https://youtu.be/t1x_ibu9BLE) de cómo cambiar el orden de un sistema operativo en el menú de arranque (Windows 7 y Windows 8)

---

# Dudas

## Reinstalar GRUB2 en OpenSuse

* [Enlace de interés](https://forums.opensuse.org/content.php/128-Re-install-Grub2-from-DVD-Rescue)
* Arrancar la MV con el DVD(iso) de OpenSUSE.
* Elegir arranque con el modo de recate (`Rescue`)
* Entrar con usuario `root`.
* `fdisk -l`, comprobar las particiones del disco.
    * Localiza ña partición donde está instalado el SSOO (Por ejemplo `sda7`)
* `mount /dev/sda7 /mnt`, monto la partición del SSOO del disco duro.
* `mount --bind /dev /mnt/dev`
* `mount --bind /proc /mnt/proc`
* `mount --bind /sys /mnt/sys`
* `chroot /mnt`, cambio al SSOO del disco duro.
* `fdisk -l`, debe seguir funcionando.
* `cp /boot/grub2/grub.cfg /boo/grub2/grub.bak`, copia de seguridad del fichero de configuración.
* `grub2-mkconfig -o /boo/grub2/grub.cfg`, volver a construir el fichero de configuración.
* `grub2-install /dev/sda`, reinstalar grub2 en el disco.

> Esto son dudas frecuentes:
> * [¿Cómo reinstalar el menú de arranque GRUB de openSUSE Linux (Sistema de rescate)?](http://www.icomputo.com/rescuesystemcomoreinstalarelmenudearranquegrubdeopensuselinuxsistemaderescate)
> * [Reinstalar GRUB2 en Ubuntu](http://www.guia-ubuntu.org/index.php?title=Recuperar_GRUB)

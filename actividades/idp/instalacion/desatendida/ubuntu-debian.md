

# Instalación desatendida con Ubuntu usando KickStart

Enlaces de interés:
* Ver [vídeo](https://youtu.be/i2uUIux6_l8)
* [Tutorial kickstart](https://sites.google.com/site/ssoounattended/proyectos/instalacion-desatendida-de-ubuntu-desktop-14-04-utilizando-kickstart)

## Preparativos
* Usar una MV con ubuntu (o Xubuntu que es más ligero).
* Instalar las herramientas siguientes:
    * `isomaster`: Se usa para manejar ficheros con formato ISO.
    * `system-config-kickstart` (Kickstart): Se usa para crear el fichero de respuestas.
* Descargar una ISO de instalación de `Ubuntu Server` dentro de la MV.

## Modificar el menú de arranque
* Usar `isomaster` para extraer el fichero `/isolinux/txt.cfg` de la ISO.
* Consultar el vídeo, para averiguar las líneas que debemos incluir.
    * Comprobar si debemos actualizar/cambiar las rutas a los ficheros `vmlinuz`, `ubuntu-server.seed` e `initrd`, según nuestra ISO.
    * Consultar el siguiente ejemplo de fichero `txt.cfg` a modo de referencia:
```
default desatendida
label desatendida
    menu label ^Ubuntu desatendido nombre-alumnoXX
    kernel /install/vmlinuz
    append file=/cdrom/preseed/ubuntu-server.seed initrd=/install/initrd.gz ks=cdrom:/ks.cfg ---
...
```
* Modificar el fichero `txt.cfg` para incluir una nueva opción de arranque para la instalación desatendida.
* Grabar el fichero `txt.cfg` modificado dentro de la ISO.

## Crear archivo de respuestas
* Usar la herramienta Kickstart para crear el archivo de respuestas.
    * Configuración básica: 
        * Definir idioma: Español
        * Arquitectura objetivo: 64 bits
    * Definir las particiones:
        * swap (tamaño recomendado)
        * /  ext4 (rellenar todo el espacio del disco)
    * Configurar la tarjeta de red.
    * Crear usuario: nombre-alumno
* Guardar el fichero de respuestas como `ks.cfg` (en el escritorio, por ejemplo).

## Crear nueva ISO y comprobar
* Usar `isomaster` para 
    * Guardar el fichero `ks.cfg` dentro de la ISO.
    * Crear la nueva ISO personalizada.
* Comprobar la nueva ISO para instalar una nueva MV.

---

# ANEXO

## Incluir escritorio en la ISO

En Kickstart incluir opciones siguientes:
* Aplicaciones: `Kubuntu Desktop`
* XWindows: KDE

Resumen de los niveles de ejecución:
* Run level 0 is matched by poweroff.target (and runlevel0.target is a symbolic link to poweroff.target).
* Run level 1 is matched by rescue.target (and runlevel1.target is a symbolic link to rescue.target).
* Run level 3 is emulated by multi-user.target (and runlevel3.target is a symbolic link to multi-user.target).
* Run level 5 is emulated by graphical.target (and runlevel5.target is a symbolic link to graphical.target).
* Run level 6 is emulated by reboot.target (and runlevel6.target is a symbolic link to reboot.target).
* Emergency is matched by emergency.target.

Comandos para gestionar el runlevel:
* `systemctl get-default`, para ver el target por defecto.
* `systemctl set-default multi-user.target`, para cambiar el target por defecto.
* `systemctl isolate multi-user.target`, para cambiar a runlevel 3.
* `systemctl isolate graphical.target`, para cambiar a runlevel 5.

## Instalación desatendida con Debian

Enlaces de interés:
* Tutorial de cómo modificar
[debian-installer](http://lihuen.info.unlp.edu.ar/index.php?title=Modificando_debian-installer)
para una instalación desatendida.
* En PDF debian-installer-pag109.pdf (Página 109), se explica en detalle el contenido del fichero de configuración.
* [Instalación desatendida Debian mediante un fichero de configuración](https://www.debian.org/releases/wheezy/ia64/ch04s04.html.es).
* [Proyecto Linux COE](http://linuxcoe.sourceforge.net/)
* [Proyecto Instalinux](http://www.instalinux.com/)
* [Documentación Debian sobre instalaciones desatendidas](http://www.debian.org/releases/stable/mips/ch04s04.html.es)


## Preseed

Instalación desatendida usando Preseed:
* https://jjcurriculumvitae.wordpress.com/2018/02/16/instalacion-desatendida-ubuntu/


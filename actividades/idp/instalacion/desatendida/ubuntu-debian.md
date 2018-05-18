

# Instalación desatendida con Ubuntu usando KickStart

Enlaces de interés:
* Ver [vídeo](https://youtu.be/i2uUIux6_l8)
* [Tutorial kickstart](https://sites.google.com/site/ssoounattended/proyectos/instalacion-desatendida-de-ubuntu-desktop-14-04-utilizando-kickstart)

## Preparativos
* Usar una MV con ubuntu (o Xubuntu que es más ligero).
* Instalar las herramientas siguientes:
    * `isomaster`: Se usa para manejar ficheros con formato ISO.
    * `system-config-kickstar` (Kickstart): Se usa para crear el fichero de respuestas.
* Descargar una ISO de instalación de Ubuntu dentro de la MV.

## Modificar el menú de arranque
* Usar `isomaster` para extraer el fichero `/isolinux/txt.cfg` de la ISO.
* Modificar el fichero `txt.cfg` para incluir una opción de aranque para la instalación desatendida.

## Crear archivo de respuestas
* Usar la herramienta Kickstar para crear el archivo de respuestas.
    * Configuración básica: Definir idioma.
    * Definir las particiones:
        * swap (tamaño recomendado)
        * /  ext4 (usar el resto del disco)
        * Configurar la tarjeta de red.
        * Crear usuario
* Guardar el fichero de respuestas como `ks.cfg` en el escritorio (Por ejemplo).

## Crear nueva ISO y comprobar
* Usar `isomaster` para 
    * Guardar el fichero `ks.cfg` dentro de la ISO.
    * Crear la nueva ISO personalizada
* Comprobar la nueva ISO para instalar una nueva MV.

---

# ANEXO

#3 Instalación desatendida con Debian

Enlaces de interés:
* Tutorial de cómo modificar
[debian-installer](http://lihuen.info.unlp.edu.ar/index.php?title=Modificando_debian-installer)
para una instalación desatendida.
* En PDF debian-installer-pag109.pdf (Página 109), se explica en detalle el contenido del fichero de configuración.
* [Instalación desatendida Debian mediante un fichero de configuración](https://www.debian.org/releases/wheezy/ia64/ch04s04.html.es).
* [Proyecto Linux COE](http://linuxcoe.sourceforge.net/)
* [Proyecto Instalinux](http://www.instalinux.com/)
* [Documentación Debian sobre instalaciones desatendidas](http://www.debian.org/releases/stable/mips/ch04s04.html.es)

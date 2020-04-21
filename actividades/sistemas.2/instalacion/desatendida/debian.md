
```
Curso           : EN CONSTRUCCION!!!
Area            : Sistemas Operativos, instalaciones desatendidas
Descripción     : Instalación desatendida en Debian (preseed.cfg)
Requisitos      : Debian
Tiempo estimado :
```

# Instalación desatendida con Preseed

Enlaces de interés:

Entregar:
* Informe, capturas de imágenes o vídeo.
* Entregar fichero de configuración de respuestas personalizado.

# 1. Preparativos

* Instalar `isomaster` en la máquina real. Esta herramienta se usará para leer y modificar ficheros con formato ISO.

---

# 2. Modificar el menú de arranque

* Usar `isomaster` para extraer el fichero `/isolinux/txt.cfg` de la ISO.
* Consultar el vídeo, para averiguar las líneas que debemos incluir.
    * Comprobar si debemos actualizar/cambiar las rutas a los ficheros `vmlinuz`, `ubuntu-server.seed` e `initrd`, según nuestra ISO.
    * Consultar el siguiente ejemplo de fichero `txt.cfg` a modo de referencia:
```
default desatendida
label desatendida
    menu label ^Ubuntu desatendido nombre-alumnoXX
    kernel /install/vmlinuz
    append file=/cdrom/preseed/ubuntu-server.seed initrd=/install/initrd.gz ks=cdrom:/ks.cfg
...
```
* Modificar el fichero `txt.cfg` para incluir una nueva opción de arranque para la instalación desatendida.
* Grabar el fichero `txt.cfg` modificado dentro de la ISO en la ruta `/isolinux/txt.cfg`.

---
# 3. Crear archivo de respuestas

Usar la herramienta Kickstart para crear el archivo de respuestas.
* Configuración básica:
    * Definir idioma: Español
    * Arquitectura objetivo: 64 bits
* Definir las particiones:
    * swap (tamaño recomendado)
    * /  ext4 (rellenar todo el espacio del disco)
* Configurar la tarjeta de red.
* Crear usuario: nombre-alumno
* Configuración de pantalla:
    * Configurar escritorio `KDE` (Esta opción nos sirve para activar por defecto el entorno gráfico)
    * Activar X Windows en el arranque.
* Configuración de Paquetes
    * Instalar `kubuntu-desktop` (Esta opción nos sirve para instalar el software necesario para el entorno gráfico KDE)
* PostScript:
    ```
    #!/bin/bash
    apt install -y kubuntu-desktop
    ```
* Guardar el fichero de respuestas como `ks.cfg` (en el escritorio, por ejemplo).

---
# 4. Crear nueva ISO y comprobar

* Usar `isomaster` para
    * Guardar el fichero `ks.cfg` dentro de la ISO (En el directorio raíz de la ISO).
    * Crear la nueva ISO personalizada.
* Comprobar la nueva ISO para instalar una nueva MV.

---

# ANEXO


## A2. Instalación desatendida con Debian

Enlaces de interés:
* https://www.librebyte.net/despliegue-de-sistemas/como-instalar-debian-de-forma-automatica-o-desatendida/
* Tutorial de cómo modificar
[debian-installer](http://lihuen.info.unlp.edu.ar/index.php?title=Modificando_debian-installer)
para una instalación desatendida.
* En PDF debian-installer-pag109.pdf (Página 109), se explica en detalle el contenido del fichero de configuración.
* [Instalación desatendida Debian mediante un fichero de configuración](https://www.debian.org/releases/wheezy/ia64/ch04s04.html.es).
* [Proyecto Linux COE](http://linuxcoe.sourceforge.net/)
* [Proyecto Instalinux](http://www.instalinux.com/)
* [Documentación Debian sobre instalaciones desatendidas](http://www.debian.org/releases/stable/mips/ch04s04.html.es)


## A3. Preseed

Instalación desatendida usando Preseed:
* https://jjcurriculumvitae.wordpress.com/2018/02/16/instalacion-desatendida-ubuntu/

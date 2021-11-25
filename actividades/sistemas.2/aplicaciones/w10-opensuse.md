
```
Curso       : 202021
Area        : Sistemas operativos, instalar aplicaciones
Descripción : Diversas formas de instalar aplicaciones en los SSOO
Requisitos  : Windows 10 y OpenSUSE
Tiempo      :
```

---
# Instalar aplicaciones y actualizar el sistema (Windows + OpenSUSE)

En esta actividad vamos a practicar diversas formas de realizar la instalación de aplicaciones en varios sistemas operativos, así como la forma de mantener nuestros sistemas actualizados.

---
# 1. Windows usando el GUI

* Partimos de una [MV con Windows](../../global/configuracion/windows.md).

## 1.1 Agregar característica

El SO Windows viene con software que se puede instalar si se necesita. Este software recibe el nombre de características del sistema.

* Capturar imagen del resultado final.
* Vamos a las `Herramientas de Windows -> Panel de control -> Programas y características -> Activar o desactivar características de Windows`.
* Instalar alguna característica.

## 1.2 Instalar aplicación

Capturar imágenes de los pasos realizados.
* Descargar un programa GnuWin32 de la página oficial [http://gnuwin32.sourceforge.net/packages.html](http://gnuwin32.sourceforge.net/packages.html). Por ejemplo: tree, gcal, stat, file. Hay que elegir la descargar del paquete completo sin el código fuente (NO sources).
* Instalar el programa.
* `cd c:\Program Files (x86)\GnuWin32\bin`. Debe estar el programa instalado con la extensión `.exe`.
* Comprobar su funcionamiento desde un terminal. Los comandos deben funcionar igual que en GNU/Linux.

## 1.3 Instalar un programa MSI

Los ficheros de instalación MSI, son programas de instalación que no nos hacen preguntas durante el proceso porque ya viene configurado con opciones por defecto.

* Descargar un MSI. Por ejemplo:
    * Ejemplo 1: Abre un navegador web y pon URL siguiente `https://download.gnome.org/binaries/`. Descargar el fichero `https://download.gnome.org/binaries/win64/gedit/gedit-VERSION.msi`. Cambiar VERSION por el valor adecuado.
    * Ejemplo 2: https://support.mozilla.org/en-US/kb/deploy-firefox-msi-installers
* Instalar el programa MSI descargado. Recordar un fichero MSI no hace ninguna pregunta durante el proceso de instalación.

# 2. Windows usando los comandos

Capturar imágenes de los pasos realizados.

## 2.1 Instalar desde la terminal Windows al estilo de GNU/Linux

Vamos a instalar la herramienta Chocolatey:
* Ir a la web de Chocolatey (http://chocolatey.org/)
* Ir a la sección Get Started -> Chocolate Install -> Individual. Seguir los pasos para instalar la herramienta (El proceso de instalación consiste en descargar un script de PowerShell (Fichero .ps1) y ejecutarlo).
* Abrir una consola como administrador.
* Ejecutar comando para instalar el programa.
    * Podemos elegir instalar cualquiera de los programas que disponibles (http://chocolatey.org/packages).
    * Por ejemplo, para instalar VLC pondremos `choco install vlc`.

## 2.2 Gestor de paquetes

* Vamos a usar la aplicación [Ninite](https://ninite.com/) para crearnos un paquete de instalación que contenga los siguiente programas:
    * Firefox
    * VLC
    * 7z
    * Steam

# 3. Windows - Actualización del sistema

* Hacer un snapshot de la MV por seguridad.

Vamos a actualizar algunos paquetes:
* Ir a `Windows Update`.
* Consultar las actualizaciones pendientes.
* Elegir 3 y aplicar actualización.

# 4. GNU/Linux usando el GUI

* Partimos de una [MV con OpenSUSE](../../global/configuracion/opensuse.md).

## 4.1 Instalar paquetes

Las distribuciones GNU/Linux como OpenSUSE, usan un gestor de paquetes para instalar/desinstalar software. Esto funciona igual que un AppStore.

> * Enlaces de interés:
>     * [Gestión de software con Yast](https://es.opensuse.org/SDB:Gesti%C3%B3n_de_software_con_YaST)

* Iniciar el gestor de paquetes ( `Inicio -> Yast -> Inst. Software`).
* Instalar por ejemplo algunos de los siguientes programas: `geany`, `gkrellm` y `vlc`.
* Comprobar que funcionan los programas que hemos instalado.

## 4.2 Desinstalar paquetes

* Desinstalar la aplicación con el gestor de paquetes.
* Comprobarlo.

# 5. GNU/Linux usando los comandos

Capturar imágenes de los pasos realizados.

> Enlace de interés:
> * [Zypper](https://es.opensuse.org/Zypper)

## 5.1 Instalar software

* Entramos en la consola como `root`.
* Con `zypper refresh`, nos conectamos con los repositorios de software remotos para
actualziar la lista de software dispomible para instalar.
* Instalar algún programa con el comando `zypper ...` (`man zypper` o [Zypper](https://es.opensuse.org/Zypper) para consultar ayuda).
* `zypper search nombre-programa`, comprobamos que el programa está instalado.
Esto es, debe aparecer el programa y el estado (la primera columna) debe tener el símbolo `i` que significa que está instalado.
* Buscar el programa en el sistema de ficheros: `whereis nombre-programa`
* Ejecutar el programa y ver que funciona.

## 5.2 Desinstalar software

* Desinstalar el programa con `zypper ...`.

> NOTA: Para eliminar las dependencias usamos el parámetro "--clean-deps" a la hora de eliminar el software.

* `zypper se nombre-programa`, comprobar que el programa no está instalado. Esto es, debe aparecer el programa y el estado (la primera columna) debe estar vacía, lo que significa que no está instalado.
* Buscar el programa en el sistema de ficheros: `whereis nombre-programa`, y no encontrarlo.
* Ejecutar el programa y ver que no funciona.

## 5.3 Instalar programa nativo de Windows en GNU/Linux

* Instalar el emulador Windows (`wine`).
* Elegir un de programa Windows. Por ejemplo: Jhonny Simulator.
* Ejecutarlo usando `wine`.

## 5.4 Instalar programa desde rpm

> * `.rpm`, extensión de los ficheros de instalación para los sistemas operativos OpenSUSE y Red Hat.
> * `.deb`, extensión de los ficheros de instalación para los sistemas operativos Debian y Ubuntu.

* Comprobar que el programa `atom` no está disponible en los respositorios.
* Buscamos en la web de [atom.io](https://atom.io) el instalador para nuestro sistema.
* Descargamos el fichero `.rpm`.
* `rpm -i atom-VERSION.rpm`, para instalar el programa mediante el fichero rpm.
* Si la instalación de atom requiere alguna dependencia, ésta hay que instalarla manualmente. Por ejemplo:
    * `zypper search lsb*`, para buscar todos los paquetes lsb "algo".
    * `zypper install lsb`, para instalar el paquete lsb.

> NOTA: El paquete libgconf2 se llama gconf2 en OpenSUSE

Para comprobar que está el paquete instalado:

* `rpm -q atom`
* `atom`

Comprobamos que funciona bien el editor `atom`.

## 5.5 Instalación desde el código fuente

GitHub es una plataforma donde los desarrolladores ponen sus proyectos de forma
pública. Vamos a realizar la instalación de un programa alojado en este repositorio,
desde el código fuente.

* Consultar la lista [Games on GitHub](https://github.com/leereilly/games)
* Dentro de la sección `Native`, elegir un programa  de la lista. Ejemplos:
    * Super Mario Bros Level 1
    * Zelda (https://github.com/solarus-games/zsdx): Este programa requiere la instalación de Solarus (motor de juegos RPG). Para instalar Solarus: `zypper install solarus solarus-gui`. Una vez instalado el motor hay que escribir el comando: `solarus-run data`, dentro de la carpeta descargada.
* Consultar las instrucciones de instalación.
* Descargar el proyecto.
* Instalar el juego.

## 6. GNU/Linux actualización del sistema

* Hacer un snapshot de la MV.
* Entramos en la consola como `root`.
* `zypper refresh`, para actualizar el catálogo de productos software disponible.
* `zypper update`, para actualizar todas las aplicaciones del sistema.

---

# ANEXO

El ANEXO sólo contiene información extra. No hay que realizar ninguna tarea con el contenido de esta sección.

## A.1 Instalación desde las fuentes

Realizar las siguientes tareas:
* Elegir un programa/software/aplicación para instalar desde las fuentes. Ejemplos:
    * [ASEprite](https://github.com/aseprite/aseprite)
    * http://www.juegoslibres.net/linux/ghouls-and-ghost-version-libre.html
    * http://www.valarsoft.com/
    * http://goonies.jorito.net/ (SO recomendado Debian7)
    * http://www.gameover.es/juegos-gratis/
    * [Jhonny_Simulator sources](http://sourceforge.net/projects/johnnysimulator/files/?source=navbar)
    * Si elijen otro programa deben consultarlo con el profesor. Por ejemplo Geany.
    El [código fuente de Geany](https://github.com/geany/geany) está alojado en GitHub.
    * [Instalar node.js en Ubuntu](http://lobotuerto.com/blog/2013/02/19/como-instalar-node-js-en-ubuntu/)
    * [Instalar el editor Atom desde las fuentes alojadas en GitHub](https://github.com/atom/atom/blob/master/docs/build-instructions/linux.md)
* Descargar el código fuente desde internet.
* Realizar la instalación según se indique en el documento README, INSTALL o SETUP.

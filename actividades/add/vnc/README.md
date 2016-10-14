
#1. Introducción
Entrega:
* URL con la ruta al/los archivo/s dentro del repositorio del alumno.
* URl commit del repositorio con la versión entregada.
* Etiquetaremos la entrega en el repositorio Git con `vnc`.

Configurar las máquinas virtuales según este [documento](../../global/configuracion-aula108.md).

#2. Conexiones remotas con VNC

* Capturar imágenes de la instalación y configuración VNC para poder acceder a una máquina remota.
* Vamos a realizar las siguientes conexiones remotas VNC:
    1. Acceder a Windows Server - desde Windows 7
    1. Acceder a Windows Server - desde GNU/Linux OpenSUSE 13.2
    1. Acceder a GNU/Linux OpenSUSE 13.2 - desde GNU/Linux OpenSUSE 13.2 (A lo mejor no hay que instalar el software cliente VNC)
    1. Acceder a GNU/Linux OpenSUSE 13.2 - desde Windows 7

##2.1 Comprobaciones

Capturar imagenes probando las conexiones remotas VNC, para verificar
que se han establecido las conexiones remotas:
* Ejecutar `netstat -ntap` en las MVs GNU/Linux
* Ejecutar `netstat -n` en las MVs Windows

#3. Instalación

##3.1 Instalación en Windows

* `TightVNC` es una herramienta libre disponible para Windows.
* En el servidor VNC usaremos `TightVNC server`.
* En el cliente usaremos `TightVNC viewer`.

> * Si usamos un servidor VNC "Marca-ACME", usar también el cliente "Marca-ACME".
> * Para esta práctica usaremos conexiones SIN cifrar.
> * Leer la documentación sobre conexiones VNC.

##3.2 Instalación en OpenSUSE

* En OpenSUSE se puede instalar/activar el servidor VNC directamente desde `Yast -> VNC`
* `vncviewer` es un cliente VNC que viene en OpenSUSE.
* En la conexion remota, hay que especificar `IP:5901`.
* `vncviewer IP-vnc-server:5901`

> **NOTA**
>
> * Para más información, leer enlace de interés sobre [VNC server OpenSUSE 13.2](https://www.howtoforge.com/tutorial/vnc-server-on-opensuse-13.2/)
> * Como cliente VNC podemos usar también `krdc`.
> * Además de `Yast`, podemos puede usar el comando `vncserver` para
gestionar el servidor VNC.

##3.3 Instalacion en Debian

* En Debian se puede usar `tightvncserver` como VNC server.

> * [VNC GNU/Linux] (http://www.ite.educacion.es/formacion/materiales/85/cd/linux/m5/servidor_vnc.html)

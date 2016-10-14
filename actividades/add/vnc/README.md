
#1. Introducción
Entrega:
* URL con la ruta al/los archivo/s dentro del repositorio del alumno.
* URl commit del repositorio con la versión entregada.
* Etiquetaremos la entrega en el repositorio Git con `vnc`.

Configurar las máquinas virtuales según este [documento](../../global/configuracion-aula108.md).

# 2. Escritorio remoto con VNC

* Leer la documentación sobre conexiones VNC.
* Capturar imágenes de la instalación y configuración VNC para poder acceder a una máquina remota.

> NOTA:
>
> * TightVNC es una herramienta libres disponibles para Windows.
> * Si usan un servidor VNC "Marca-ACME", usar también el cliente "Marca-ACME".
> * Para esta práctica usaremos conexiones SIN cifrar.
>

Capturar imagenes probando las conexiones remotas VNC, para verificar que se
han establecido las conexiones remotas:
* Ejecutar `netstat -ntap` en GNU/Linux
* Ejecutar `netstat -n` en Windows

Comprobar las siguientes conexiones:
1. Acceder a Windows Server - desde Windows 7
1. Acceder a Windows Server - desde GNU/Linux OpenSUSE 13.2
1. Acceder a GNU/Linux OpenSUSE 13.2 - desde GNU/Linux OpenSUSE 13.2 (A lo mejor no hay que instalar el software cliente VNC)
1. Acceder a GNU/Linux OpenSUSE 13.2 - desde Windows 7

> Enlaces de interés
>
> * En OpenSUSE se puede instalar directamente desde `Yast -> VNC`
>     * [VNC server OpenSUSE 13.2](https://www.howtoforge.com/tutorial/vnc-server-on-opensuse-13.2/)
>     * Cliente VNC: `vncviewer` o `krdc`. En la conexion remota, hay que especificar `IP:5901`.
>     * Si no queremos usar `Yast`, se puede usar el comando `vncserver` se usa para
gestionar el servidor VNC.
> * En Debian se puede usar `tightvncserver`
> * [VNC GNU/Linux] (http://www.ite.educacion.es/formacion/materiales/85/cd/linux/m5/servidor_vnc.html)

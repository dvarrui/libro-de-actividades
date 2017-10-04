
# 1. Introducción

Entrega:
* URL con la ruta al archivo del informe dentro del repositorio del alumno.
* URl commit del repositorio con la versión entregada.
* Etiquetaremos la entrega en el repositorio Git con `vnc`.

---

# 2. Conexiones remotas con VNC

* Capturar imágenes de la instalación y configuración VNC para poder acceder a una máquina remota.
* Vamos a realizar las siguientes conexiones remotas VNC:
    1. Acceder a Windows - desde Windows 7/10
    1. Acceder a Windows - desde GNU/Linux OpenSUSE
    1. Acceder a GNU/Linux OpenSUSE - desde GNU/Linux OpenSUSE (A lo mejor no hay que instalar el software cliente VNC)
    1. Acceder a GNU/Linux OpenSUSE - desde Windows 7/10

---

# 3. Instalación en Windows

* Configurar las máquinas virtuales según este [documento](../../global/configuracion/).
* `TightVNC` es una herramienta libre disponible para Windows.
* En el servidor VNC usaremos `TightVNC server`.
* Revisar la configuración del cortafuegos del servidor VNC Windows para permitir VNC.
* En el cliente usaremos `TightVNC viewer`.

> **NOTA**
>
> * Si usamos un servidor VNC "Marca-ACME", usar también el cliente "Marca-ACME".
> * Para esta práctica usaremos conexiones SIN cifrar.
> * Leer la documentación sobre conexiones VNC.

> **Problemas de conexión**
>
> * Refrescar las MAC de la MV.
> * Revisar en la configuración del servidor VNC Windows las opciones de "Access Control".
> * `nmap -Pn IP-VNC-SERVER`, para comprobar que los servicios son visibles
desde fuera de la máquina VNC-SERVER. Deben verse los puertos 5801, 5901, etc.

Capturar imagenes probando las conexiones remotas VNC, para verificar
que se han establecido las conexiones remotas:
* Ejecutar `netstat -n` en las MVs Windows

---

# 4. Instalación en OpenSUSE

* Configurar las máquinas virtuales según este [documento](../../global/configuracion/).
* En OpenSUSE se puede instalar/activar el servidor VNC directamente desde `Yast -> VNC`
    * Permitir conexión remota.
    * Abrir puertos VNC en el cortafuegos.
* `vncviewer` es un cliente VNC que viene con OpenSUSE.
* En la conexion remota, hay que especificar `IP:5901`, `IP:5902`, etc.
* `vncviewer IP-vnc-server:590N`.

> **NOTA**
>
> * Para más información, leer enlace de interés sobre [VNC server OpenSUSE 13.2](https://www.howtoforge.com/tutorial/vnc-server-on-opensuse-13.2/)
> * Como cliente VNC podemos usar también `krdc`.
> * Además de `Yast`, podemos puede usar el comando `vncserver` para
gestionar el servidor VNC.

> **Problemas de conexión**
>
> * Refrescar las MAC de la MV.
> * `nmap -Pn IP-VNC-SERVER`, para comprobar que los servicios son visibles
desde fuera de la máquina VNC-SERVER. Deben verse los puertos 5801, 5901, etc.
> * Revisar si el cortafuegos GNU/Linux está rechazando las conexiones.

Capturar imagenes probando las conexiones remotas VNC, para verificar
que se han establecido las conexiones remotas:
* Ejecutar `netstat -ntap` en las MVs GNU/Linux

---

# ANEXO A

## A.1 Instalacion en Debian

* En Debian se puede usar `tightvncserver` como VNC server.

> * [VNC GNU/Linux] (http://www.ite.educacion.es/formacion/materiales/85/cd/linux/m5/servidor_vnc.html)

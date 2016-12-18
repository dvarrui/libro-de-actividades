
# VirtualBox y Debian

* Configurar [MAC](general.md) de la máquina virtual.

## Instalar las Guest Addittions en Debian

Antes de intalar las Guest Additions, es  mejor verificar que no hace falta.
Si la ventana de la MV se redimensiona bien, funciona el pendrive, y las carpetas compartidas
con el host anfitrión, entonces no hace falta instalar nada.

En caso contrario haremos:
* Ubuntu: Ir a VBox -> Dispositivos -> Instalar.
* Debian/LUbuntu:
    * Ir a VBox -> Dispositivos -> Instalar.
    * Abrir consola como super usuario: `apt-get -y install make gcc linux-headers-$(uname -r)`.
    * Una vez terminada la instalación comprobar que no aparecen mensajes de error.
    * Buscar donde está montado VBOXADDITIONS (`df -hT`), y moverse a dicho directorio.
    * Ejecutar el programa de instalación para linux: `./VBoxLinuxAddittions.run`

> Guest Additions en Debian7
> * VirtualBox -> Iniciar MV Debian
> * MV Debian -> Dispositiovos -> Insertar CD/DVR de las Guest Additions
> * Abrir un terminal, ejecutar `su`
> * `mkdir /tmp/cdrom`
> * `cd /tmp/cdrom`
> * `cp -r /media/cdrom/* .`
> * `chmod +x *`
> * `./VBoxLinuxAddittions.run`
>
> En Debian8 XFCE se requiere instalar previamente los siguientes paquetes:
> `apt-get install gcc make linux-headers-$(uname -r)`


```
* Creado en Junio 2016 para el curso1516
```

#1. Configurar las Guest Adittions

##1.1 Guest Additions en OpenSUSE

* En la consola como root, `zypper in kernel-devel kernel-desktop-devel gcc make`
* Reiniciar equipo
* VirtualBox -> Instalar Guest Additions
* En la consola como root
    * `cd /run/media/....`
    * `./VBoxLinux...`
* Reiniciar el equipo si todo va bien.

Para montar una carpeta compartida
* Configurar en VirtualBox el recurso de carpeta compartida con NOMBRE y PATH-HOST-REAL
* Iniciar la MV
* Entrar en consola como root
    * crear carpeta local PATH-DENTRO-MV
    * `mount -t vboxsf NOMBRE PATH-DE-MV`
* Ya está!

##1.2 Instalar las Guest Addittions en Debian

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


#2. Instalar VirtualBox

#2.1 VirtualBox en OpenSuse 13.2

Problemas en la instalación:
* La instalación por repositorios estándar `zypper in virtualbox` la hizo bien, pero no funcionaba el front-end gráfico virtualboxgtk.
* Descargando el paquete rpm desde la web no funcionaba pero al instalar el paquete se requerían numerosas dependencias.
* Alfinal hubo que descargar el fichero oracle_vbox.asc y luego `sudo rpm --import oracle_vbox.asc`
* Modificar el fichero ` /etc/zypp/repos.d/virtualbox.repo` con el contenido siguiente:
```
[virtualbox]
name=VirtualBox for openSUSE 13.2
baseurl=http://download.virtualbox.org/virtualbox/rpm/opensuse/13.2
type=yum
enabled=1
priority=120
autorefresh=1
gpgcheck=1
gpgkey=https://www.virtualbox.org/download/oracle_vbox.asc
keeppackages=0
```
* Instalar desde los repositorios el nuevo paquete de virtualbox-4.3
* Además hay que instalar los paquetes make, gcc y kernel-devel
* Ejecutamos /etc/init.d/vboxdvr setup
* y listo


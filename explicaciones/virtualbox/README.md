

#VirtualBox

#VirtualBox en una máquina real con OpenSuse 13.2
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

#Guest Additions en Debian7
* VirtualBox -> Iniciar MV Debian
* MV Debian -> Dispositiovos -> Insertar CD/DVR de las Guest Additions
* Abrir un terminal, ejecutar `su`
* `mkdir /tmp/cdrom`
* `cd /tmp/cdrom
* `cp -r /media/cdrom/* .`
* `chmod +x *`
* `./VBoxLinuxAddittions.run`

> En Debian8 XFCE se requiere instalar previamente los siguientes paquetes:
> `apt-get install gcc make linux-headers-$(uname -r)`
>



# VirtualBox y SO OpenSUSE

## Guest Additions en OpenSUSE

* Ir a la MV.
* En la consola como root.
* `zypper in kernel-devel kernel-desktop-devel gcc make`, para instalar paquetes para compilar el código fuente.
* Reiniciar MV.
* En a la máquina real, ir a `VirtualBox -> Instalar Guest Additions`
* Ir a la MV.
* En la consola como root
* `cd /run/media/....`, moverse a la carpeta de la ISO (DVD).
* `./VBoxLinuxAdditions.run`, ejecutar la instalación para GNU/Linux.
* Reiniciar MV si todo va bien.

Para montar una carpeta compartida
* Configurar en VirtualBox el recurso de carpeta compartida con NOMBRE y PATH-HOST-REAL
* Iniciar la MV
* Entrar en consola como root
    * crear carpeta local PATH-DENTRO-MV
    * `mount -t vboxsf NOMBRE PATH-DE-MV`
* Ya está! :+1:

---
# ANEXO

## Instalar VirtualBox en OpenSuse 13.2

> Problemas en la instalación:
>
> * La instalación por repositorios estándar `zypper in virtualbox` la hizo bien,
pero no funcionaba el front-end gráfico virtualboxgtk.
> * Descargando el paquete rpm desde la web no funcionaba pero al instalar el
paquete se requerían numerosas dependencias.

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
* Además hay que instalar los paquetes `make`, `gcc` y `kernel-devel`.
* Ejecutamos `/etc/init.d/vboxdvr setup` y listo.

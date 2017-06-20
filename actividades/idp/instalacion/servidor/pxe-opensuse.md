

# Servidor de instalaciones PXE con OpenSUSE

Hemos utilizado los enlaces que se mencionan más abajo para confeccionar esta práctica.

Enlaces de interés:
* [Puesta en marcha de un servidor PXE con OpenSUSE 13.1](https://es.opensuse.org/SDB:Puesta_en_marcha_de_un_servidor_PXE)
* [Instalación OpenSUSE mediante TFTP y PXE](https://miguelcarmona.com/articulos/instalacion-de-opensuse-por-red-mediante-tftp-pxe)
* Vídeo [LINUX: PXE Installation Server](https://youtu.be/59TwMw_CJwQ)
* Vídeo [LINUX: Installing from the network](https://www.youtube.com/watch?v=mPARmfWizBI)

# 1. Introducción

`Texto extraído del enlace [Puesta en marcha de un servidor PXE con OpenSUSE 13.1](https://es.opensuse.org/SDB:Puesta_en_marcha_de_un_servidor_PXE)`

Vamos a ver cómo openSUSE para montar un servidor PXE en nuestra red local.
Un servidor PXE nos permite iniciar la instalación de un sistema operativo a través
de la red sin necesidad de grabar un disco CD/DVD o utilizar un pendrive.
Un servidor PXE también nos sirve para iniciar un sistema Live u otras herramientas, en nuestras máquinas sin sistema operativo instalado.

Veremos que hay diferentes métodos de arrancar un sistema operativo a través de PXE.

Con un servidor de este tipo en marcha en tu red, únicamente tendrás que descargarte las ISO que quieras usar y averiguar la sintaxis a aplicar en cada caso. A cambio obtienes un método muy rápido de instalación que además prescinde de medios físicos.

> Para evitar problemas de conectividad compruebar la configuración del cortafuegos en el servidor.

---

# 2. Preparativos

Usaremos 2 MV:
* MV1
    * SO OpenSUSE ([Configuración](../../../global/configuracion/opensuse.md)).
    * Nombre de host: `pxe-serverXX`.
    * Dos interfaces de red:
        * Una interfaz externa con IP estática(172.19.XX.31/16).
        * y otra interfaz en red interna (`netint`) con IP estática (192.168.XX.31/24).
* MV2
    * Sin sistema operativo instalado.
    * Una interfaz de red dentro de la red interna.
    * Configurar VirtualBox -> Sistema -> Arranque -> Red/LAN/PXE.

> Prácticamente todos los ordenadores hoy en día soportan arranque mediante PXE.
> Revisa la EFI/BIOS de tu equipo y actívalo.

---

# 2. Servicio DHCP

* `zypper in dhcp-server yast2-dhcp-server`, instalar el servicio.
* Hacemos una copia de los ficheros antes de modificarlos:
    * `cp /etc/sysconfig/dhcpd /etc/sysconfig/dhcp.bak`
    * `cp /etc/dhcp.conf /etc/dhcp.conf.bak`
* Edita el archivo `/etc/sysconfig/dhcpd` y en la línea `DHCPD_INTERFACE=""`
añadir el nombre de interfaz que está en la red interna. Ver ejemplo:

```
## Path:        Network/DHCP/DHCP server
## Description: DHCPv4 server settings
## Type:        string
## Default:     ""
## ServiceRestart: dhcpd
#
# Interface(s) for the DHCPv4 server to listen on.
#
# A special keyword is ANY, it will cause dhcpd to autodetect available
# interfaces.
#
# Examples: DHCPD_INTERFACE="eth0 eth1 eth2"
#           DHCPD_INTERFACE="ANY"
#
DHCPD_INTERFACE="enp0s3"

A continuación edita el fichero /etc/dhcpd.conf Así tengo yo el mío:

option domain-name "CURSO1617";
option domain-name-servers 8.8.4.4, 80.58.61.250;

# default-lease-time 600;
# max-lease-time 7200;

ddns-updates off;
ddns-update-style none;

# If this DHCP server is the official DHCP server for the local
# network, the authoritative directive should be uncommented.
#authoritative;

# Use this to send dhcp log messages to a different log file (you also
# have to hack syslog.conf to complete the redirection).
#log-facility local7;

# Solamente se atenderán peticiones DHCP de tipo PXE
allow booting;
allow bootp;

# Reglas para identificar peticiones DHCP desde clientes PCE y Etherboot

class "pxe" {
match if substring (option vendor-class-identifier, 0, 9) = "PXEClient";
}
class "etherboot" {
match if substring (option vendor-class-identifier, 0, 9) = "Etherboot";
}

# Las direcciones de ese tipo quedarán englobadas en esta subnet
subnet 192.168.XX.0 netmask 255.255.255.0 {
 pool {
  range 192.168.XX.201 192.168.XX.220; # con estas hay de sobra
  filename "pxelinux.0";
  server-name "192.168.XX.31"; # Coincide con la IP del servidor
  next-server 192.168.XX.31; # Dirección del servidor TFTP
  option subnet-mask 255.255.255.0;
  option broadcast-address 192.168.XX.255;
  option routers 192.168.XX.31;
  allow members of "pxe"; # permitido sólo para clientes PXE
  allow members of "etherboot"; # y también para los de etherboot
 }
}
```

Este fichero configura el comportamiento de nuestro servidor DHCP.

> No quites el comentario a la línea #authoritative; si ya hay un servidor DHCP funcionando en tu red.

Nuestro servidor DHCP sólo atenderá las peticiones extendidas del tipo PXE, dejando el resto para el sevidor DHCP de nuestra red. Las peticiones DHCP que nos interesan las filtramos mediante las dos reglas que se han definido.

* En el campo range defino que mi servidor repartirá un máximo de 20 direcciones simultáneas
(que irán desde la 192.168.XX.201 hasta la 192.168.XX.220).
* Al campo filename le damos el valor pxelinux.0 y a los campos server-name y next-server el de la IP que le hayamos dado al servidor.
* Configurar el arranque automático del servicio dhcpd.

---

# 3. Servicio TFTP

* `zypper in atftp yast2-tftp-server`, instalar el servicio.
* Hacemos copia de seguridad del fichero antes de modificarlo:
    * `cp /etc/sysconfig/atftpd /etc/sysconfig/atftpd.bak`
* Editar el archivo `/etc/sysconfig/atftpd`:
```
## Path:    Network/FTP/Atftpd
## Description: ATFTP Configuration
## Type:    string
## Default: "tftp"
#
#  daemon user (tftp)
#
ATFTPD_USER="tftp"

## Type:    string
## Default: "tftp"
#
#  daemon user (tftp)
#
ATFTPD_GROUP="tftp"

## Type:    string
## Default: ""
##
## INFO:
## "--daemon, --user, --group, --logfile" can not be removed/replaced here as
## atftp is started with them as default opts: "--daemon --user atftp --group atftp "
## and
## logging to file is set as default: "--logfile /var/log/atftpd/atftp.log "
#
# atftpd options
#
ATFTPD_OPTIONS="--daemon --user tftp -v"

## Type:    yesno
## Default: no
#
# Use inetd instead of daemon
#
ATFTPD_USE_INETD="no"

## Type:    string
## Default: "/srv/tftpboot"
## was "/tftpboot" but
## "/tftpboot" is not allowed anymore in FHS 2.2.
#
#  TFTP directory must be a world readable/writable directory.
#  By default /srv/tftpboot is assumed.
#
ATFTPD_DIRECTORY="/srv/tftpboot"

## Type:    string
## Default: ""
#
#  Whitespace seperated list of IP addresses which ATFTPD binds to.
#  One instance of the service is started on each IP address.
#  By default atftpd will listen on all available IP addresses/interfaces.
#
ATFTPD_BIND_ADDRESSES=""
```

* El servicio se ejecutará con la cuenta de usuario tftp perteneciente al grupo del mismo nombre, asegúrate de que existen.
* Toma nota también del directorio raíz del servidor TFTP, /srv/tftpboot.
* Configurar el arranque automático del servicio atftpd.

---

# 4. Servicio NFS

* `zypper in nfs-kernel-server yast2-nfs-server`, instalación del servicio.

Vamos a almacenar en el servidor PXE las ISO que queramos lanzar a través de PXE.
* Crear directorio `/mnt/openSUSEXX`.
* Edita el fichero `/etc/fstab` y crea un punto de montaje para la ISO en ese directorio:
`/ruta/a/la/iso/openSUSE-Leap.iso /mnt/opensuseXX/ udf,iso9660 user,auto,loop 0 0`
* `mount -a`, se montan todas las configuraciones definidas en `/etc/fstab`.
* `df -hT`, comprobamos.

Ahora vamos a exportar ese directorio mediante NFS.
* Editar el archivo `/etc/exports`.
* Añadir `/mnt/opensuseXX   *(ro,no_root_squash,async,no_subtree_check)`
* Configurar el arranque automático del servicio nfsserver.
* Reiniciar el servidor NFS.

---

# 5. Preparando el menú de arranque PXE

* `zypper in syslinux`, instalamos software.

* En la raíz del servidor TFTP copiamos los siguientes archivos y creamos un par de directorios:
```
# cd /srv/tftpboot
# mkdir pxelinux.cfg
# mkdir imagenes
# cp /usr/share/syslinux/pxelinux.0 .
# cp /usr/share/syslinux/menu.c32 .
# cp /usr/share/syslinux/reboot.c32
# touch pxelinux.cfg/default
```

> En el directorio `imagenes` crearemos un subdirectorio por cada ISO que queramos arrancar.
En cada uno de ellos almacenaremos el kernel y el ramdisk necesarios.
> El archivo `default` será nuestro menú de arranque.

* Editar el archivo `/stv/tftpboot/pxelinux.cfg/default` y añade lo siguiente:
```
DEFAULT menu.c32
PROMPT 0
TIMEOUT 300
ONTIMEOUT 0
NOESCAPE 1

MENU TITLE Menu de arranque - nombre-alumnoXX

LABEL 0
        MENU LABEL ^0. Arrancar desde el disco duro
        LOCALBOOT 0
        TEXT HELP
        Para arrancar desde el disco duro pulsa Enter.
        ENDTEXT

MENU SEPARATOR

LABEL 1
        MENU LABEL ^1. Reiniciar
        COM32 reboot.c32

MENU SEPARATOR

```

* Guardar los cambios al archivo.
* Reiniciar una máquina cliente (puede que tengas que pulsar la tecla F12 durante el arranque para activar el arranque PXE).
* Comprobar que accedemos al menú PXE. Aunque todavía nos falta un poco más.

---

# 6. Teoría: Sintaxis del menú

Repasemos un poco la sintaxis del fichero que hemos creado:

* DEFAULT menu.c32 define que cargaremos el menú en modo texto.
* PROMPT 0 para mostrar esta ventana sin pulsar ninguna tecla desde que cargue el PXE exitosamente. Prueba a cambiar el 0 por un 1 y ver qué pasa, debes pulsar Enter para que cargue el menú principal.
* TIMEOUT 300 define un tiempo de espera de 30 segundos antes de cargar la opción predeterminada.
* ONTIMEOUT 0 define cuál será la entrada predeterminada del menú. Elegirá la que he nombrado como 0 (pueden usarse nombres, yo he usado números).
* NOESCAPE 1 para evitar la salida del menú si se pulsa la tecla Escape. Como he definido entradas para reiniciar y arrancar desde el disco duro local puedo deshabilitar la salida a través de Escape.
* MENU TITLE Menu de arranque título de la pantalla que aparecerá a modo de cabecera.
* MENU BACKGROUND pxelinux.cfg/wall.jpg ruta y nombre de la imagen que usaremos como fondo del menú. Puede ser distinto para cada ventana, pero recuerda las limitaciones: únicamente cargarán archivos .jpg o .png con una resolución de 640x480 píxeles.
* LABEL 0 sirve para dar nombre a una entrada del menú.
* MENU LABEL ^0. Arrancar desde el disco duro, etiqueta que se mostrará. El símbolo ^ define una tecla rápida de acceso.
* LOCALBOOT 0 con esta orden podemos arrancar la máquina desde el disco duro local.
* TEXT HELP y ENDTEXT lo que escribamos entre estas dos líneas se mostrará en la parte inferior del menú como texto de ayuda al seleccionar cada entrada del menú.

Veamos qué podemos seguir aprendiendo de la sintaxis de estos ficheros:
* Estas dos líneas nos permiten pasar de una pantalla a otra.
    * KERNEL vesamenu.c32
    * APPEND pxelinux.cfg/default
* MENU separator permite introducir una línea vacía.
* LABEL empty define una entrada del menú no seleccionable.
* Una entrada típica para arrancar un sistema operativo incluirá las siguentes líneas:
   * LABEL nombre que le damos a la entrada.
   * MENU LABEL etiqueta que veremos en la pantalla.
   * KERNEL define la ruta y el nombre del kernel a enviar. También podemos usar la palabra LINUX si vamos a cargar un kernel de Linux.
   * INITRD define la ruta y el nombre del ramdisk que se cargará en memoria.
   * APPEND aquí especificaremos los parámetros adicionales de arranque.

---

# 7. Configurar una imagen para instalar

Usaremos una carpeta dentro del TFTP para almacenar los ficheros que necesita
nuestr imagen para arrancar (el kernel y el ramdisk). Estos ficheros hay que copiarlos dentro de nuestro directorio /srv/tftpboot/ para que el servidor PXE los envie a los clientes para que puedan arrancar. En el caso que nos ocupa el kernel es un archivo llamado linux y el ramdisk initrd. Ambos se encuentran dentro de la ISO en la ruta boot/x86_64/loader/. Entonces:

* Crear subdirectorio `/srv/tftpboot/imagenes/opensuseXX`.
* `cp /mnt/opensuseXX/boot/x86_64/loader/linux /srv/tftpboot/imagenes/opensuseXX/`
* `cp /mnt/opensuseXX/boot/x86_64/loader/initrd /srv/tftpboot/imagenes/opensuseXX/`

* Editar el fichero `/srv/tftpboot/pxelinux.cfg/default` y añadir lo siguiente:
```
LABEL 2
        MENU LABEL 2. opensuseXX
        LINUX imagenes/opensuseXX/linux
        INITRD imagenes/opensuseXX/initrd
        APPEND install=nfs://192.168.XX.31/mnt/opensuseXX/ splash=silent ramdisk_size=512000 ramdisk_blocksize=4096 language=es_ES keytable=es quiet quiet showopts
        TEXT HELP
        Instalar opensuseXX - nombre-del-alumno
        ENDTEXT
```

* Iniciar MV cliente y comprobar.

> El ramdisk de openSUSE permite acceder al contenido del DVD a través de NFS, lo cual es mucho más óptimo que hacerlo a través de TFTP. Ya veremos que hay casos en los que podemos pasar como parámetro directamente una ruta a la ISO o, si esta es de pequeño tamaño, cargarla en memoria directamente. Pero en este caso no es lo más eficiente.

---

# ANEXO

## A.1 Poner imagen de fondo

El fichero wall.jpg es una imagen que usaremos de fondo del menú.
Se aceptan los archivos de imagen con un tamaño de 640x480 píxeles y extensiones .jpg o .png.
Si prefieres una interfaz de texto y no quieres cargar una imagen de fondo copia el archivo menu.c32 en lugar de vesamenu.c32.
cp /usr/share/syslinux/vesamenu.c32 .
cp /ruta/al/fichero/wall.jpg pxelinux.cfg/

## A.2 Iniciar ISO clonezilla
```
LABEL 2
        MENU LABEL ^2. Clonezilla Live (64 bits)
        KERNEL imagenes/clonezilla/x64/vmlinuz
        INITRD imagenes/clonezilla/x64/initrd.img
        APPEND boot=live username=user hostname=saucy live-config quiet union=overlayfs noswap edd=on locales=es_ES.UTF-8 keyboard-layouts=es ocs_live_run="ocs-live-general" ocs_live_batch=no video=uvesafb:mode_option=1024x768-32 ip=frommedia splash netboot=nfs nfsroot=192.168.1.200:/mnt/clonezillax64/
```

## A.3 Instalación de Debian

* Descarga la ISO de Debian Live del sitio oficial y cópiala al servidor.
* Móntala en un subdirectorio de /mnt y expórtalo mediante NFS:
* `mkdir /mnt/debian`
* `echo "/ruta/a/la/iso/debian-live-7.2-amd64-kde-desktop.iso /mnt/debian udf,iso9660 user,auto,loop 0 0" >> /etc/fstab`
* `mount -a`
* `echo "/mnt/debian *(ro,no_root_squash,async,no_subtree_check)" >> /etc/exports`
* service nfsserver restart
* Crea un subdirectorio debian en /srv/tftpboot/imagenes y copia dentro los archivos vmlinuz e initrd.img que hay dentro de la ISO:
* mkdir /srv/tftpboot/imagenes/debian-Live
* cp /mnt/debian/live/vmlinuz /srv/tftpboot/imagenes/debian-Live/
* cp /mnt/debian/live/initrd.img /srv/tftpboot/imagenes/debian-Live/
* Edita el archivo /srv/tftpboot/pxelinux.cfg/sistemas y añade la siguiente entrada al final:
```
LABEL 2
       MENU LABEL ^2. Debian 7 Live x64
       KERNEL imagenes/debian-Live/vmlinuz
       INITRD imagenes/debian-Live/initrd.img
       APPEND boot=live config netboot=nfs nfsroot=192.168.1.200:/mnt/debian locales=es_ES.UTF-8 keyboard-layouts=es quiet
       TEXT HELP
       Arranca Debian en modo Live
       ENDTEXT
```

# A.4 Iniciar OpenSUSE en modo Live

```
LABEL 3
       MENU LABEL ^3. openSUSE 13.1 Live x64
       LINUX imagenes/openSUSE-Live/linux
       INITRD imagenes/openSUSE-Live/initrd
       APPEND splash=silent isofrom_device=nfs:192.168.1.200:/ruta/a/la/iso isofrom_system=openSUSE-13.1-KDE-Live-x86_64.iso language=es_ES keytable=es quiet quiet showopts
       TEXT HELP
       Arranca openSUSE en modo Live
       ENDTEXT
```

Los pasos a seguir serían sacar el kernel y el ramdisk a un subdirectorio del servidor TFTP y dejar la ISO en una ruta accesible a través de NFS (no es necesario montarla).
```
# mkdir /tmp/live && mkdir /srv/tftpboot/imagenes/openSUSE-Live
# mount -o loop -t iso9660 /ruta/a/la/iso /tmp/live
# cp /tmp/live/boot/x86_64/loader/linux /srv/tftpboot/imagenes/openSUSE-Live/
# cp /tmp/live/boot/x86_64/loader/initrd /srv/tftpboot/imagenes/openSUSE-Live/
# umount /tmp/live
```

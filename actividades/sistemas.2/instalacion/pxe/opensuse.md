
```
Curso       : 202122
Área        : Sistemas operativos, servidor, instalar, PXE
Descripción : Servidor de instalaciones PXE con OpenSUSE
Requisitos  : GNU/Linux OpenSUSE Leap
Tiempo      : 8 horas
```

# Servidor de instalaciones PXE con OpenSUSE

Enlaces de interés:
* [Instalación de mEDUXa a través de la red del centro PXE](https://www3.gobiernodecanarias.org/educacion/cau_ce/servicios/web/noticias/mEDUXa-instalacion)
* [Puesta en marcha de un servidor PXE con OpenSUSE 13.1](https://es.opensuse.org/SDB:Puesta_en_marcha_de_un_servidor_PXE)
* [Instalación OpenSUSE mediante TFTP y PXE](https://miguelcarmona.com/articulos/instalacion-de-opensuse-por-red-mediante-tftp-pxe)
* Vídeo [LINUX: PXE Installation Server](https://youtu.be/59TwMw_CJwQ)
* Vídeo [LINUX: Installing from the network](https://www.youtube.com/watch?v=mPARmfWizBI)

## Introducción

Texto extraído del enlace [Puesta en marcha de un servidor PXE con OpenSUSE 13.1](https://es.opensuse.org/SDB:Puesta_en_marcha_de_un_servidor_PXE)`

Vamos a montar un servidor PXE con OpenSUSE en nuestra red local.

Un servidor PXE:
* nos permite iniciar la instalación de un sistema operativo a través
de la red sin necesidad de grabar un disco CD/DVD o utilizar un pendrive.
* también nos sirve para iniciar un sistema Live u otras herramientas, en nuestras máquinas sin sistema operativo instalado. En este caso los equipos cliente no requieren de disco duro.

Veremos que hay diferentes métodos de arrancar un sistema operativo a través de PXE.

Con un servidor de este tipo en nuestra red, únicamente tendremos que descargar las ISO que queramos usar y averiguar la sintaxis a aplicar en cada caso. A cambio obtienes un método muy rápido de instalación que además prescinde de medios físicos.

> Ver [ejemplo de PXE](fotos) en funcionamiento.

# 1. Preparativos

Usaremos 2 MV:

| Id | SSOO | Nombre de host | Interfaz 1 externa | Interfaz 2 interna |
| -- | ---- | -------------- | ---------- | ---------- |
| MV1 |[OpenSUSE](../../../global/configuracion/opensuse.md)) | pxe-serverXX | IP estática (172.19.XX.31/16) | Red interna "netintXX" con IP estática (192.168.XX.31/24) |
| MV2 | Sin sistema operativo | | Red interna "netintXX" ||


Todas las tarjetas de red de hoy en día soportan arranque mediante PXE.
Por si acaso habría que revisar la EFI/BIOS del equipo y activarla. En nuestro caso, lo haremos por VirtualBox.

* Ir a la VirtualBox.
* Configurar de la MV 2.
* `VirtualBox -> Sistema -> Arranque -> Red/LAN/PXE`.

Para montar el servicio PXE en la MV1 necesitaremos lo siguiente:
* Servicio DHCP,
* Servicio TFTP y
* Servicio NFS.

# 2. Servicio DHCP

## 2.1 Instalar el servicio DHCP

* Ir la MV1.
* Instalamos el servicio DHCP (`zypper in dhcp-server yast2-dhcp-server`).

## 2.2 Configurar interfaz de uso

Queremos que el servicio PXE sólo se ofrezca por el interfaz de red 2 (El de la red interna).
* Hacemos una copia de los ficheros antes de modificarlos:
    * `cp /etc/sysconfig/dhcpd /etc/sysconfig/dhcp.bak`
    * `cp /etc/dhcp.conf /etc/dhcp.conf.bak`
* Edita el archivo `/etc/sysconfig/dhcpd` y en la línea `DHCPD_INTERFACE=""`
añadir el nombre de interfaz que está en la red interna. Ver ejemplo:
`DHCPD_INTERFACE="enp0s3"`.

> ¿Recuerdas el comando para consultar los nombres de las interfaces de red disponibles?

## 2.3 Configurar DHCP

Este fichero configura el comportamiento de nuestro servidor DHCP.

* Edita el fichero `/etc/dhcpd.conf`:

```
option domain-name "CURSO1617";
option domain-name-servers 8.8.4.4, 80.58.61.250;

ddns-updates off;
ddns-update-style none;

# If this DHCP server is the official DHCP server for the local
# network, the authoritative directive should be uncommented.
#authoritative;

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

> No quites el comentario a la línea #authoritative; si ya hay un servidor DHCP funcionando en tu red.

De esta forma, nuestro servidor DHCP sólo atenderá las peticiones extendidas del tipo PXE, dejando el resto para el sevidor DHCP de nuestra red. Las peticiones DHCP que nos interesan las filtramos mediante las dos reglas que se han definido.

| Atributo | Descripción |
| -------- | ----------- |
| range    | define que el servidor repartirá un máximo de 20 direcciones simultáneas
(que irán desde la 192.168.XX.201 hasta la 192.168.XX.220) |
| filename | toma el valor pxelinux.0 y a los campos server-name y next-server de la IP que le hayamos dado al servidor. |

* Configurar el arranque automático del servicio "dhcpd".

# 3. Servicio TFTP

## 3.1 Instalar el servicio

* `zypper in atftp yast2-tftp-server`, instalar el servicio.

## 3.2 Cambiar la configuración

* Hacemos copia de seguridad del fichero antes de modificarlo:
    * `cp /etc/sysconfig/atftpd /etc/sysconfig/atftpd.bak`
* Editar el archivo `/etc/sysconfig/atftpd`:

```
#  daemon user (tftp)
ATFTPD_USER="tftp"
ATFTPD_GROUP="tftp"

# atftpd options
ATFTPD_OPTIONS="--daemon --user tftp -v"

# Use inetd instead of daemon
ATFTPD_USE_INETD="no"

#  TFTP directory must be a world readable/writable directory.
#  By default /srv/tftpboot is assumed.
ATFTPD_DIRECTORY="/srv/tftpboot"

## Type:    string
## Default: ""
#
#  Whitespace seperated list of IP addresses which ATFTPD binds to.
#  By default atftpd will listen on all available IP addresses/interfaces.
ATFTPD_BIND_ADDRESSES="192.168.XX.31"
```

Con esta configuración:
* El servicio se ejecutará con la cuenta de usuario `tftp` perteneciente al grupo del mismo nombre, asegúrate de que existen.
* Toma nota también del directorio raíz del servidor TFTP, `/srv/tftpboot`.
* Configurar el arranque automático del servicio `atftpd`.

> ¿Recuerdas cómo podemos comprobar si el servicio está levantado?

# 4. Servicio NFS

## 4.1 Instalar el servicio

* `zypper in nfs-kernel-server yast2-nfs-server`, instalación del servicio.

## 4.2 Configurar

Acceder al contenido del fichero ISO:
* Crear directorio `/mnt/opensuse.iso.d`. Este directorio lo vamos a usar para leer el contenido del fichero ISO sin tener que desempaquetarlo.
* Edita el fichero `/etc/fstab` y crea un punto de montaje para la ISO en ese directorio:
`/ruta/a/la/iso/openSUSE-Leap.iso /mnt/opensuse.iso.d/ udf,iso9660 user,auto,loop 0 0`
* `mount -a`, se montan todas las configuraciones definidas en `/etc/fstab`.
* `df -hT`, comprobamos.

> ¿Ves el contenido de la ISO en la carpeta creada?

Ahora vamos a exportar ese directorio mediante NFS. De esta forma, el contenido será accesible por la red LAN.
* Editar el archivo `/etc/exports`.
* Añadir `/mnt/opensuse.iso.d   *(ro,no_root_squash,async,no_subtree_check)`
* Configurar el arranque automático del servicio `nfsserver`.
* Reiniciar el servidor NFS.

# 5. Menú de arranque

Preparando el menú de arranque PXE.

## 5.1 Preparando el menú

* `zypper in syslinux`, instalamos software.
* En la raíz del servidor TFTP copiamos los siguientes archivos y creamos un par de directorios:
```
# mkdir /srv/tftpbootp/xelinux.cfg
# mkdir /srv/tftpboot/imagenes
# cp /usr/share/syslinux/pxelinux.0 /srv/tftpboot
# cp /usr/share/syslinux/menu.c32 /srv/tftpboot
# cp /usr/share/syslinux/reboot.c32 /srv/tftpboot
# touch /srv/tftpboot/pxelinux.cfg/default
```

En el directorio `imagenes` crearemos un subdirectorio por cada ISO que queramos arrancar.
En cada uno de ellos almacenaremos el kernel y el ramdisk necesarios.

El archivo `default` será nuestro menú de arranque.

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

## 5.2 TEORÍA

Repasemos un poco la sintaxis del fichero que hemos creado:

* **DEFAULT menu.c32**, define que cargaremos el menú en modo texto.
* **PROMPT 0**, para mostrar esta ventana sin pulsar ninguna tecla desde que cargue el PXE. Prueba a cambiar el 0 por un 1 y ver qué pasa, debes pulsar Enter para que cargue el menú principal.
* **TIMEOUT 300**, define un tiempo de espera de 30 segundos antes de cargar la opción predeterminada.
* **ONTIMEOUT 0**, define cuál será la entrada predeterminada del menú. Elegirá la que he nombrado como 0 (pueden usarse nombres en lugar de números).
* **NOESCAPE 1**, para evitar la salida del menú si se pulsa la tecla Escape. Como he definido entradas para reiniciar y arrancar desde el disco duro local puedo deshabilitar la salida a través de Escape.
* **MENU TITLE Menu de arranque**,  título de la pantalla que aparecerá a modo de cabecera.
* MENU BACKGROUND pxelinux.cfg/wall.jpg ruta y nombre de la imagen que usaremos como fondo del menú. Puede ser distinto para cada ventana, pero recuerda las limitaciones: únicamente cargarán archivos .jpg o .png con una resolución de 640x480 píxeles.
* **LABEL 0**, sirve para dar nombre a una entrada del menú.
* **MENU LABEL ^0**. Arrancar desde el disco duro, etiqueta que se mostrará. El símbolo ^ define una tecla rápida de acceso.
* **LOCALBOOT 0**, con esta orden podemos arrancar la máquina desde el disco duro local.
* **TEXT HELP** y **ENDTEXT** lo que escribamos entre estas dos líneas se mostrará en la parte inferior del menú como texto de ayuda al seleccionar cada entrada del menú.

Veamos qué podemos seguir aprendiendo de la sintaxis de estos ficheros:
* Estas dos líneas nos permiten pasar de una pantalla a otra.
    * KERNEL vesamenu.c32
    * APPEND pxelinux.cfg/default
* **MENU separator**, permite introducir una línea vacía.
* **LABEL empty**, define una entrada del menú no seleccionable.
* Una entrada típica para arrancar un sistema operativo incluirá las siguientes líneas:
   * LABEL nombre que le damos a la entrada.
   * MENU LABEL etiqueta que veremos en la pantalla.
   * KERNEL define la ruta y el nombre del kernel a enviar. También podemos usar la palabra LINUX si vamos a cargar un kernel de Linux.
   * INITRD define la ruta y el nombre del ramdisk que se cargará en memoria.
   * APPEND aquí especificaremos los parámetros adicionales de arranque.

## 5.3. Probando el menú desde el cliente

> OJO: Para evitar problemas de conectividad comprobar la configuración del cortafuegos en el servidor.

* Reiniciar una máquina cliente. Puede que tengas que pulsar la tecla F12 durante el arranque para seleccionar el arranque PXE.
* Comprobar que accedemos al menú PXE. Aunque todavía nos faltan más opciones.

# 6. Configurar una imagen para instalar

Usaremos una carpeta dentro del TFTP para almacenar los ficheros que necesita
nuestra imagen para arrancar. Esto es: el kernel y el ramdisk.

Estos ficheros hay que copiarlos dentro de nuestro directorio `/srv/tftpboot/` para que el servidor PXE los envie a los clientes para que puedan arrancar.

En el caso que nos ocupa el kernel es un archivo llamado linuxXXX y el ramdisk initrdXXX. Ambos se encuentran dentro de la ISO en la ruta `boot/x86_64/loader/`.

* Crear subdirectorio `/srv/tftpboot/imagenes/opensuseXX`.
* `cp /mnt/opensuse.iso.d/boot/x86_64/loader/linux /srv/tftpboot/imagenes/opensuseXX/`
* `cp /mnt/opensuse.iso.d/boot/x86_64/loader/initrd /srv/tftpboot/imagenes/opensuseXX/`

* Editar el fichero `/srv/tftpboot/pxelinux.cfg/default` y añadir lo siguiente:
```
LABEL 2
        MENU LABEL 2. opensuseXX
        LINUX imagenes/opensuseXX/linux
        INITRD imagenes/opensuseXX/initrd
        APPEND install=nfs://192.168.XX.31/mnt/opensuse.iso.d/ splash=silent ramdisk_size=512000 ramdisk_blocksize=4096 language=es_ES keytable=es quiet quiet showopts
        TEXT HELP
        Instalar openSUSE - nombre-del-alumnoXX
        ENDTEXT
```

* Iniciar MV cliente y comprobar el menú PXE.
* Instalar SO en MV cliente.

El ramdisk de openSUSE permite acceder al contenido del DVD a través de NFS, lo cual es mucho más óptimo que hacerlo a través de TFTP. Ya veremos que hay casos en los que podemos pasar como parámetro directamente una ruta a la ISO o, si ésta es de pequeño tamaño, cargarla en memoria directamente. Pero en este caso no es lo más eficiente.

# 7. Otra ISO

¿Te animas a poner otra ISO de instalación más en el servidor PXE?
¿Qué pasos hay que hacer?


# ANEXO

## A.1 Poner imagen de fondo

* El fichero wall.jpg es una imagen que usaremos de fondo del menú. Se aceptan los archivos de imagen con un tamaño de 640x480 píxeles y extensiones .jpg o .png.
* Si prefieres una interfaz de texto y no quieres cargar una imagen de fondo copia el archivo menu.c32 en lugar de vesamenu.c32.

cp /usr/share/syslinux/vesamenu.c32 .
cp /ruta/al/fichero/wall.jpg pxelinux.cfg/

## A.2 Iniciar ISO clonezilla

```
LABEL 2
        MENU LABEL ^2. Clonezilla Live (64 bits)
        KERNEL imagenes/clonezillaXX/x64/vmlinuz
        INITRD imagenes/clonezillaXX/x64/initrd.img
        APPEND boot=live username=user hostname=saucy live-config quiet union=overlayfs noswap edd=on locales=es_ES.UTF-8 keyboard-layouts=es ocs_live_run="ocs-live-general" ocs_live_batch=no video=uvesafb:mode_option=1024x768-32 ip=frommedia splash netboot=nfs nfsroot=192.168.1.200:/mnt/clonezillax64/
```

## A.3 Ininicar ISO de Debian

* Descarga la ISO de Debian Live del sitio oficial y cópiala al servidor.
* Móntala en un subdirectorio de /mnt y expórtalo mediante NFS:
* `mkdir /mnt/debian.iso.d`
* `echo "/ruta/a/la/iso/debian-live-7.2-amd64-kde-desktop.iso /mnt/debian.iso.d udf,iso9660 user,auto,loop 0 0" >> /etc/fstab`
* `mount -a`
* `echo "/mnt/debian.iso.d *(ro,no_root_squash,async,no_subtree_check)" >> /etc/exports`
* service nfsserver restart
* Crea un subdirectorio debian en `/srv/tftpboot/imagenes/debianXX` y copia dentro los archivos vmlinuz e initrd.img que hay dentro de la ISO:
* mkdir /srv/tftpboot/imagenes/debian-Live
* cp /mnt/debian.iso.d/live/vmlinuz /srv/tftpboot/imagenes/debianXX/
* cp /mnt/debian.iso.d/live/initrd.img /srv/tftpboot/imagenes/debianXX/
* Edita el archivo /srv/tftpboot/pxelinux.cfg/sistemas y añade la siguiente entrada al final:

```
LABEL 2
       MENU LABEL ^2. Debian 7 Live x64
       KERNEL imagenes/debianXX/vmlinuz
       INITRD imagenes/debianXX/initrd.img
       APPEND boot=live config netboot=nfs nfsroot=192.168.XX.31:/mnt/debian.iso.d locales=es_ES.UTF-8 keyboard-layouts=es quiet
       TEXT HELP
         Arranca Debian - nombre-alumnoXX
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

FAQ
INDICE
1. Entrega de los vídeos
2. Instalar las Guest Adittions en Debian
3. Reinstalar GRUB2
4. Repositorios oficiales Debian 6
5. Conversor de formato multimedia
6. Escaneo de puertos


1. Entrega de los vídeos

    Entregar URL con la ruta al vídeo subido a Youtube.
    En el caso de presentar varios vídeos, numerarlos secuencialmente para mantener el orden.
    Debe aparecer en el vídeo: los nombres de los componentes, fecha y título de la actividad y el nombre del centro "IES Puerto de la Cruz".


2. Instalar las Guest Additions en Debian

    Iniciamos la máquina virtual. Entramos con nuestro usuario.
    Vamos a VirtualBox -> Dispositivos -> Instalar GuestAdditions.
    Tenemos un error de ejecución por problemas de permisos.
    Abrir un Terminal y nos ponemos como superusuario (Comando su).
    Ejecutamos: cp -R /media/cdrom /mnt
    Ejecutamos: /mnt/cdrom/VBoxLinuxGuestAdditions.run
    Respondemos "yes"
    Terminamos.
    Si hay problemas por falta de alguna herramienta de compilación, entonces...
    Ejecutamos: apt-get update
    Ejecutamos: apt-get install gcc make linux-headers-$(uname -r)
    Volvemos a proceder a la instalación.
    Terminamos.


3. Reinstalar GRUB2

    http://www.guia-ubuntu.org/index.php?title=Recuperar_GRUB


4. Repositorios Oficiales de Debian 6
Copiar en /etc/apt/sources.list:


### REPOSITORIOS OFICIALES

# Repositorio Oficial
deb http://http.es.debian.org/debian/ squeeze main contrib non-free
deb-src http://http.es.debian.org/debian/ squeeze main contrib non-free

# Repositorio de Seguridad
deb http://security.debian.org/ squeeze/updates main contrib non-free
deb-src http://security.debian.org/ squeeze/updates main contrib non-free
deb http://ftp.es.debian.org/debian/ squeeze-proposed-updates main contrib non-free
deb-src http://ftp.es.debian.org/debian/ squeeze-proposed-updates main contrib non-free

# Repositorio Squeeze - Backports
deb http://backports.debian.org/debian-backports squeeze-backports main

# Repositorio Multimedia - http://www.debian-multimedia.org/
# aptitude install debian-multimedia-keyring
deb http://www.debian-multimedia.org squeeze main non-free
deb-src http://www.debian-multimedia.org squeeze main non-fre

5. Conversor de formato multimedia
Crea un fichero de texto con el nombre "conversor-de-video". Escribe el siguiente contenido dentro del fichero:

#!/bin/bash
echo "[INFO] Realizando conversión de formato..."
mencoder -idx out.ogv -ovc lavc -oac mp3lame -o out.avi
echo "[INFO] Done"

Pon permisos de ejecución al fichero: chmod +x conversor-de-video
Para ejecutarlo, simplemente escribe en la consola: conversor-de-video
¡Ya está!

6. Escaneo de puertos
Desde un cliente podemos comprobar los servicios disponibles de un servidor usando nmap de la siguiente forma:
apt-get install nmap
nmap ip-de-servidor

Esto nos muestra un listado con los servicios abiertos en la ip.

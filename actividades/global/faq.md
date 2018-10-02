

# Preguntas frecuentes

## Reinstalar GRUB2

* http://www.guia-ubuntu.org/index.php?title=Recuperar_GRUB

## Repositorios Oficiales de Debian 6

Copiar en /etc/apt/sources.list:

* Repositorio Oficial
    * deb http://http.es.debian.org/debian/ squeeze main contrib non-free
    * deb-src http://http.es.debian.org/debian/ squeeze main contrib non-free
* Repositorio de Seguridad
    * deb http://security.debian.org/ squeeze/updates main contrib non-free
    * deb-src http://security.debian.org/ squeeze/updates main contrib non-free
    * deb http://ftp.es.debian.org/debian/ squeeze-proposed-updates main contrib non-free
    * deb-src http://ftp.es.debian.org/debian/ squeeze-proposed-updates main contrib non-free
* Repositorio Squeeze - Backports
    * deb http://backports.debian.org/debian-backports squeeze-backports main
* Repositorio Multimedia - http://www.debian-multimedia.org/
    * deb http://www.debian-multimedia.org squeeze main non-free
    * deb-src http://www.debian-multimedia.org squeeze main non-fre
* aptitude install debian-multimedia-keyring

# Conversor de formato multimedia

Crea un fichero de texto con el nombre "conversor-de-video". Escribe el siguiente contenido dentro del fichero:
```
#!/bin/bash
echo "[INFO] Realizando conversión de formato..."
mencoder -idx out.ogv -ovc lavc -oac mp3lame -o out.avi
echo "[INFO] Done"
```
Pon permisos de ejecución al fichero: chmod +x conversor-de-video
Para ejecutarlo, simplemente escribe en la consola: conversor-de-video
¡Ya está!

6. Escaneo de puertos
Desde un cliente podemos comprobar los servicios disponibles de un servidor usando nmap de la siguiente forma:
apt-get install nmap
nmap ip-de-servidor

Esto nos muestra un listado con los servicios abiertos en la ip.

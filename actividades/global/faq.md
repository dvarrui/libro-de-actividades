

# Preguntas frecuentes

## Emoticonos

:eyes:
:+1:
:question:
:pushpin:
:computer:
:card_index:
:octocat:
:dizzy:

## Reinstalar GRUB2

* http://www.guia-ubuntu.org/index.php?title=Recuperar_GRUB

## Conversor de formato multimedia

Crea un fichero de texto con el nombre "conversor-de-video". Escribe el siguiente contenido dentro del fichero:

```bash
#!/bin/bash
echo "[INFO] Realizando conversión de formato..."
mencoder -idx out.ogv -ovc lavc -oac mp3lame -o out.avi
echo "[INFO] Done"
```

* `chmod +x conversor-de-video`, poner permisos de ejecución.
* `./conversor-de-video`, para ejecutarlo el script.

## Escaneo de puertos

Desde un cliente podemos comprobar los servicios disponibles de un servidor usando nmap de la siguiente forma:
```
apt-get install nmap
nmap ip-de-servidor
```
Esto nos muestra un listado con los servicios abiertos en la IP.

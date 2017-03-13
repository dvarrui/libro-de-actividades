
```
EN CONSTRUCCIÓN
```

# 1. Rsync

* Libro "Administración de Sistemas Linux" de ANAYA (Capítulo 11)

## 1.1 Introducción

Rsync transfiere archivos eficientemente por una red a otro sistema, desde el
cual puede recuperarlos en caso de que le ocurra un desastre al sistema local.

La utilidad rsync es programa diseñado para replicar grandes cantidades de datos.
Puede saltarse archivos copiados previamente y fragmentos y encriptar las transferencias
de datos con SSH, haciendo copias de seguridad remota con rsync de manera más
rápida y más segura que con herramientas tradicionales.

## 1.2 Opciones de rsync

Éstas son algunas de las principales opciones de rsync:
* `-g`, preserva los permisos de grupo de los archivos que se están duplicando.
* `-l`, Copia los enlaces simbólicos com enlaces simbólicos.
* `-o`, preserva el usuario de los archivos que se replican.
* `-p`, preserva los permisos de los archivos que se replican.
* `-t`, preserva la hora de modificación de los archivos que se replican.
* `-r`, activa la recursividad, transfiriendo subdirectorios.
* `-a`, es lo mismo que `-Dgloprt`.
* `--partial`, activa las transferencias parciales. Si rsync se para, será
capaz de completar el resto del archivo cuando se reinicie.
* `--progress`, muestra el progreso de la transferencia de archivos.
* `-P`, activa `--partial` y `--progress`.
* `-v`, lista los archivos que se están transfiriendo.
* `-vv`, igual que -v pero también lista los archivos que se ignoran.
* `-vvv`, igual que -vv pero también muestra información de depuración.
* `-n`, muestra los archivos que se transferirán.
* `--rsh='ssh'`, isa SSH para la transferencia. También se puede hacer definiendo
la variable de entorno `RSYNC_RSH` como ssh.
* `-z`, activa la compresión.
* `-H`, preserva los enlaces.
* `-b`, hace copia de todos los archivos destino en lugar de sustituirlos. Se usará
cuando se desea mantener versiones antiguas de cada archivo.

Después de las opciones vienen los parámetros de origen y destino. Las rutas
pueden ser locales o remotas (user@host:path).

---

# 2. Práctica

## 2.1 Instalación

* `rsync --help`, para comprobar si tenemos rsync instalado en el sistema.
También podemos verificarlo con: `whereis rsync`, `zypper info rsync`, etc.
* `zypper install rsync`, instalar rsync en OpenSUSE. Usaremos el comando
`apt-get install rsync`,para instalar en Debian/Ubuntu.

## 2.2 Comando de copia de seguridad

Vamos a invocar comando de rsync para realizar una copia de seguridad del
home de usuario1 en la MV1, hacia
el home del usuario2 en la MV2.

* Comprobar que la copia de ha realizado correctamente.

## 2.3 Crear Script

* Crear fichero `$HOME/backupXX.sh`:

```
#!/bin/bash
export RSYNC_RSH=/usr/bin/ssh
HOSTNAME=172.19.XX.42
USERNAME=$(whomai
cd || exit 1
rsync -aHPvz . $(USERNAME)@${HOSTNAME}:.
```

* Asignar permisos de ejecución al script.
* Comprobar que funciona la copia de seguridad con el script.

## 2.4 Variable de entorno PATH.

* `echo $PATH`, comprobar la variable de entorno PATH.
* Mover el fichero a `$HOME/bin/backupXX`:
* Invocar el programa `backupXX`.

---

# ANEXO

* tar: programa tradicional Unix para crear colecciones comprimidas de archivos;
crea convenientes bloques de datos que puede salvaguardar usango otras herramientas.
* cdrecord/cdrtools: Graba archivos en CD o DVD.
* Amanda: Automatiza las copias en cinta, útil en entornos con grandes cantidades
de datos.
* MySQL tools.

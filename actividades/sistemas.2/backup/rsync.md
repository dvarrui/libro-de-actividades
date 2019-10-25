
```
Estado : EN CONSTRUCCIÓN
```

# 1. Rsync

Más información:
* Libro "Administración de Sistemas Linux" de ANAYA (Capítulo 11)

## 1.1 Introducción

Rsync transfiere archivos eficientemente por una red a otro sistema, desde el cual puede recuperarlos en caso de que le ocurra un desastre al sistema local.

La utilidad rsync es programa diseñado para replicar grandes cantidades de datos. Puede saltarse archivos copiados previamente y fragmentos y encriptar las transferencias de datos con SSH, haciendo copias de seguridad remota con rsync de manera más rápida y más segura que con herramientas tradicionales.

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

Después de las opciones vienen los parámetros de origen y destino. Las rutas pueden ser locales o remotas (user@host:path).

---

# 2. Preparativos

Necesitaremos 2 MV's:
* MV1 con SO GNU/Linux ([Configuración](../../global/configuracion/opensuse.md)).
* MV2 con SO GNU/Linux ([Configuración](../../global/configuracion/opensuse.md)).
    * Modificar hostname de MV2 con `backupXX`.

## 2.1 Instalación

Instalar rsync en las 2 MV's.
* `rsync --help`, para comprobar si tenemos rsync instalado en el sistema.
También podemos verificarlo con: `whereis rsync`, `zypper info rsync`, etc.
* `zypper install rsync`, instalar rsync en OpenSUSE.

> También podemos usar el comando `apt install rsync`, para OpenSUSE o Debian/Ubuntu.

---

# 3. Comando manual

Vamos a usar comando rsync para realizar una copia de seguridad del
home de usuario1 en la MV1, hacia el home del usuario2 en la MV2.

* Ir a MV2.
* Consultar el contenido de `$HOME/usuario2`.
* Instalar el servidor SSH.
* Ir a MV1.
* Comprobar que funciona el acceso SSH a MV2.
* Crear directorio `$HOME/misdatosXX` y dentro guardar los
siguientes ficheros: `file1.txt` (Fichero de texto), `file2.png` (Imagen) y
`file3.ogv` (Vídeo).
* Invocar el comando rsync para copiar `$HOME/misdatosXX` en la MV2.
* Ir a MV2.
* Comprobar que la copia de ha realizado correctamente.
* Ir a MV1.
* Modificar el fichero `$HOME/misdatosXX/file1.txt`
* Crear fichero `$HOME/misdatosXX/file4.odt`.
* Invocar el comando rsync para copiar `$HOME/misdatosXX` en la MV2.
* Ir a MV2.
* Comprobar que la copia de ha realizado correctamente.
* Ir a MV1.
* Borrar fichero `$HOME/misdatosXX/file2.png`
* Invocar el comando rsync para copiar `$HOME/misdatosXX` en la MV2.
* Comprobar que se han actualizado los datos.

---

# 4. Usando un script

Vamos a crearnos un script muy sencillo para ayudarnos a realizar la tarea de copia de seguridad usando rsync. Básicamente hay que poner los comandos que necesitamos en un fichero de texto y luego pondremos permisos de ejecución a dicho fichero.

## 4.1 Crear script

* Ir a MV2. Borrar `/home/usuario2/misdatosXX`.
* Ir a MV1.
* Crear fichero `$HOME/backupXX.sh`:

```
#!/bin/bash
export RSYNC_RSH=/usr/bin/ssh
HOSTNAME=172.19.XX.42
USERNAME=$(whomai)
cd || exit 1
rsync -aHPvz . $(USERNAME)@${HOSTNAME}:.
```

* Asignar permisos de ejecución al script.
* Comprobar que funciona la copia de seguridad con el script.

## 4.2 Variable de entorno PATH.

* Ir a MV2. Borrar los datos.
* Ir a MV1.
* `echo $PATH`, comprobar la variable de entorno PATH.
* Mover el fichero a `$HOME/bin/backupXX`:
* Invocar el programa `backupXX`.
* Comprobar.

---

# 5. Recuparación

## 5.1 Listando archivos en el servidor

Rsync puede ofrecer una lista de los archivos que hay en el servidor de copias de seguridad.

## 5.2 Restaurando archivos perdidos

---

# ANEXO

* tar: programa tradicional Unix para crear colecciones comprimidas de archivos; crea convenientes bloques de datos que puede salvaguardar usando otras herramientas.
* cdrecord/cdrtools: Graba archivos en CD o DVD.
* Amanda: Automatiza las copias en cinta, útil en entornos con grandes cantidades de datos.
* MySQL tools.

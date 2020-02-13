
```
Curso       : 201920
Area        : Sistemas operativos, backup
Descripción : Copias de seguridad usando tar, rsync, Windows
Requisitos  : GNU/Linux, Windows, SSH
Tiempo      : 11 sesiones
```

---
# 1. Copias de seguridad con tar

Propuesta de rúbrica:

| Criterio | Bien(2) | Regular(1)| Falta(0) |
| -------- | ------- | --------- | -------- |
| (4.1) Copia inicial            | | | |
| (4.2) Recuperación de archivos | | | |
| (4.3) Programar las copias     | | | .|

## 1.1 Elegir una de las siguientes MV

Vamos a realizar esta práctica en 1 MV que puede ser:
* SO GNU/Linux ([Configuración](../../global/configuracion)).
* SO Windows ([Configuración](../../global/configuracion)) con [Cygwin](https://www.cygwin.com/) instalado.

## 1.2 Preparativos

Una vez elegida la MV, empezamos:
* `tar --version`, para comprobar que lo tenemos instalado en nuestro sistema.

Ahora vamos a crear la estructura de directorios y ficheros para probar con ellos las copias de seguridad.

| Parámetro | Valor                             |
| --------- | --------------------------------- |
| HOME      | /home/nombre-del-alumno           |
| DOCFOLDER | /home/nombre-del-alumno/mydocs    |
| RECFOLDER | /home/nombre-del-alumno/restaurar |

* Iniciar sesión con nuestro usuario (`nombre-del-alumno`). Estamos en el directorio `/home/nombre-del-alumno`
* Crear directorio DOCFOLDER.
    * Crear archivo DOCFOLDER/a.txt. Dentro escribir nombre completo del alumno.
    * Crear archivo DOCFOLDER/b.txt. Dentro escribir la fecha actual.

Se ha creado lo siguiente:
```
mydocs
├── a.txt
└── b.txt
```

---
# 2 TEORÍA: Aprendiendo a usar el comando tar

Este apartado no hay que hacerlo. Sólo es teoría sobre el comando.

> Enlaces de interés:
> * [Backup y restauración de backups incrementales con tar](http://systemadmin.es/2015/04/backup-y-restauracion-de-backups-incrementales-con-tar)
> * [Backups con tar: fullbackups, incrementales y diferenciales](https://nebul4ck.wordpress.com/2015/03/20/backups-con-tar-full-backups-e-incrementales/)

El comando "tar" sirve para empaquetar. No para comprimir. Pero podemos usarlo en combinación con alguna herramienta de compresión para hacer las dos acciones en un sólo paso.

Crear backup:
* `tar -cvf ...`: Crear fichero empaquetado.
* `tar -cvfz ...`: Crear fichero empaquetado y comprimido con formato gz.
* `tar -cvfa ...`: Crear fichero empaquetado y comprimido.

Consultar contenido del backup:
* `tar -tvf ...`: Muestra el contenido del fichero empaquetado.
* `tar -tvfz ...`: Muestra el contenido del fichero empaquetado y comprimido gz.
* `tar -tvfa ...`: Muestra el contenido del fichero empaquetado y comprimido.

Extraer uno o varios ficheros del backup:
* `tar -xvf ...`: Extrae el contenido del fichero empaquetado.
* Extrae el contenido del fichero empaquetado en dir2.
    * `tar -xvf ... --directory dir2`
    * `tar -xvf ... -C /dir2`
* `tar -xvf backup.tar --directory dir2 file3`: Extrae un archivo (file3) del fichero empaquetado en el directorio dir2. También valdría `tar -xvf backup.tar -C dir2 archivo`.
* `tar -xvfz ...`: Extrae el contenido del fichero empaquetado y comprimido gz.
* `tar -xvfa ...`: Extrae el contenido del fichero empaquetado y comprimido.

Otros parámetros de interés:
* Con el parámetro `-p` los ficheros mantienen su trayectoria absoluta dentro del empaquetado.
* Con el parámetro `-z` podemos forzar compresión "gz".

> Varios:
> * `date +%Y%m%y`: Comando para mostrar la fecha actual en formato AAAAMMDD.
> * `gzip ...`: Comando para hacer la compresión en formato gz.
> * `zip ...`: Comando para hacer la compresión en formato zip.

---
# 3. Copia de seguridad total (full-backup)

Iniciar sesión con nuestro usuario:
* `tar cvf backupXX-1-full.tar mydocs`, parea realizar una copia de seguridad total.
* `tar tvf backupXX-1-full.tar`, comprobar el contenido de la copia de seguridad total.
* Crear archivo DOCFOLDER/c.txt. Escribir dentro el título de tu película favorita.
* Realiza copia seguridad total con el nombre `backupXX-2-full.tar`.
* Comprobar el contenido.

Las copias de seguridad total son sencillas de hacer, pero no son eficientes en cuanto a optimizar el almacenamiento. No era necesario volver a copiar los archivos a.txt, ni b.txt porque no han cambiado.

---
# 4. Copia de seguridad incremental

> Enlaces de interés:
>
> * [ES - Copia incremental con tar](http://systemadmin.es/2015/04/backup-y-restauracion-de-backups-incrementales-con-tar)
> * [EN - Incremental backup using tar command](https://www.unixmen.com/performing-incremental-backups-using-tar/)
> * [Comando tar](https://maslinux.es/comando-tar-comprimir-y-descomprimir-los-archivosdirectorios/)

Tenemos lo siguiente:
```
mydocs
   ├── a.txt
   ├── b.txt
   └── c.txt
```

## 4.1 Copia seguridad inicial

* `tar -g mydocs.snap -cvf backupXX-3-init.tar mydocs`, crear el full-backup inicial indicando el fichero de metadatos (snapshot file).
* A continuación simulamos dos cambios
    * Borrar el archibo DOCFOLDER/b.txt.
    * Crear el archivo DOCFOLDER/d.txt.
* `tar -g mydocs.snap -cvf backupXX-4-inc.tar mydocs`, y hacemos el backup incremental indicando el fichero de metadatos que ya tenemos creado.

> Como podemos comprobar, la copia incremental sólo guarda los cambios realizados desde el último backup. El fichero snap va guardando el estado final de la copia. Esto es, qué ficheros permanecen y cuáles desaparecen.

* Realizar otra copia incremental (snap file `mydocs.snap`), pero usando como nombre de fichero de backup el siguiente: `backupXX-5-inc.tar`
* Comprobar el contenido de `backupXX-5-inc.tar`.

> Sin hacer cambios en los ficheros, cuando volvemos a realizar otra copia incremental (basándonos en el snap file anterior), podemos comprobar... que NO se copia ningún archivo, porque no ha habido ningún cambio.

Podemos comprobar que la recuperación de archivos desde la última copia incremental, no refleja por sí sola, el verdadero estado final del directorio. El último backup incremental sólo permite restaurar los cambios del último "incremento".

## 4.2 Recuperación de los archivos

Para conseguir restaurar el estado final completo del directorio, necesitaremos usar todos los backup. Esto es el full-backup inicial y todos los sucesivos incrementales.

Pasos para una recuperación completa:
1. Primero descomprimir el "full-backup" inicial.
2. Luego aplicar el incremental usando la opción `--incremental`.

* `cd /home/nombre-del-alumno`
* `mkdir restore`
* `tar xvf backupXX-3-init.tar -C restore/`
* `tar --incremental -xvf backupXX-4-inc.tar -C restore/`
* `tar --incremental -xvf backupXX-5-inc.tar -C restore/`
* `tree restore`

Podemos comprobar que ha añadido el fichero creado (d.txt), pero también se ha borrado el fichero (b.txt) que había sido eliminado en el momento de hacer el incremental.

**Curiosidad**

La información final de los archivos que deben persistir entre todos los backups (full o inc) se guarda en el fichero snap. Pero como está en formato binario es difícil de ver, para ello usaremos un visor hexadecimal, de la siguiente forma:

```
$ hexdump mydocs.snap -C
00000000  47 4e 55 20 74 61 72 2d  31 2e 33 32 2d 32 0a 31  |GNU tar-1.32-2.1|
00000010  35 37 32 30 31 31 35 32  34 00 36 31 33 31 35 38  |572011524.613158|
00000020  34 37 32 00 30 00 31 35  37 32 30 31 31 33 31 34  |472.0.1572011314|
00000030  00 34 30 38 35 34 39 34  32 36 00 32 30 36 36 00  |.408549426.2066.|
00000040  36 38 34 32 31 36 00 6d  79 64 6f 63 73 00 4e 61  |684216.mydocs.Na|
00000050  2e 74 78 74 00 4e 63 2e  74 78 74 00 4e 64 2e 74  |.txt.Nc.txt.Nd.t|
00000060  78 74 00 00 00                                    |xt...|
00000065
```

De la salida anterior, comprobamos que los ficheros que persisten son:
* mydocs/a.txt
* mydocs/c.txt
* mydocs/d.txt

Conclusiones:
* Las copias totales (full-backup) son sencillas de hacer, pero desaprovechamos espacio duplicando archivos que no cambian.
* Las copias incrementales (inc) permiten optimizar el espacio de almacenamiento no duplicando archivos, pero por contra, a la hora de recuperar nos lleva más trabajo.

## 4.3 Programar la copias

> Enlaces de interés:
> * [Cómo utilizar crontab para programar tareas](https://www.redeszone.net/2017/01/09/utilizar-cron-crontab-linux-programar-tareas/)

Vamos a crear una configuración (crontab) para que las copias de seguridad se realicen de forma automática.

* `crontab -l`, vemos que no hay ninguna configuración creada.
* `crontab -e`, se nos abre un editor.
* Pulsar `i`(insert) para activar el modo de empezar a escribir.

> Información para configurar crontab:
> * m: minuto
> * h: hora
> * mon: mes
> * dow: día del mes
> * dom: día de la semana (0=domingo, 1=lunes, etc)
> * Comando a ejecutar

* Escribir algo parecido a lo siguiente:
```
45 10 * * 1   tar -g /home/user/mydocs.snap -cvfz /home/user/crontabXX-lun.tar.gz /home/user/mydocs
45 10 * * 2   tar -g /home/user/mydocs.snap -cvfz /home/user/crontabXX-mar.tar.gz /home/user/mydocs
45 10 * * 3   tar -g /home/user/mydocs.snap -cvfz /home/user/crontabXX-mie.tar.gz /home/user/mydocs
45 10 * * 4   tar -g /home/user/mydocs.snap -cvfz /home/user/crontabXX-jue.tar.gz /home/user/mydocs
45 10 * * 5   tar -g /home/user/mydocs.snap -cvfz /home/user/crontabXX-vie.tar.gz /home/user/mydocs
```

Esta configuración programa una copia de seguridad del directorio `/home/user/mydocs` a las 10:45. Los lunes hace un backup total y de martes a viernes se hacen copias incrementales.

* Pulsar la tecla `ESC`.
* Escribir: , `:`, `wq`. Así grabamos(w=write) y salimos (q=quiet) del editor de crontab.
* `crontab -l`, para consultar que las tareas están programadas correctamente.

---
# 5. Copias diferenciales

> Enlace de interés:
> * [Backups con tar](https://nebul4ck.wordpress.com/2015/03/20/backups-con-tar-full-backups-e-incrementales/)

* Partimos de que ya se ha realizado un "full-backup".
* Con el comando siguiente crearemos la copia diferencial de la copia completa que hemos realizado: `tar -cvf backupXX-7-diff.tar mydocs/* -N 13-feb-18`.

Para realizar backups diferenciales con tar usaremos su opción -N. Lo que nos permite esta opción es ordenar a tar que solo archive aquellos datos que han cambiado desde una determinada fecha, hasta la fecha de ejecución del comando.

> Nota: Si pasados unos días, volviésemos a crear otra copia diferencial "backupXX-8-diff" con la misma fecha, esta copia contendría también los cambios reflejados en las copias diferenciales anteriores. Por esta razón su tamaño va aumentando en comparación con los backups incrementales.

Por ahora estamos guardando las copias de seguridad en el equipo local. Es aconsejable  guardar las copias en otro equipo de nuestra red. Podríamos guardar los ficheros de las copias de seguridad en un servidor remoto usando smb/cifs, scp, etc.

---
# 6. Entorno gráfico

En Windows como en GNU/Linux, suele haber una herramienta de entorno gráfico para realizar copias de seguridad. Es una herramienta mucho más limitada que las que tenemos en los comandos.

Hacer una copia de seguridad de la carpeta `C:\Users\nombre-del-alumno\Documents`, usando la herramienta que viene por defecto en Windows.

---
# 7. rsync

`ESTO NO HAY QUE HACERLO. ES PARA COMENTAR EN CLASE`

Ahora vamos a usar la herramienta `rsync` para hacer réplicas de nuestros ficheros.

> Más información:
> * Libro "Administración de Sistemas Linux" de ANAYA (Capítulo 11)

## 7.1 Introducción

Rsync transfiere archivos eficientemente por una red a otro sistema, desde el cual puede recuperarlos en caso de que le ocurra un desastre al sistema local.

La utilidad rsync es programa diseñado para replicar grandes cantidades de datos. Puede saltarse archivos copiados previamente y fragmentos y encriptar las transferencias de datos con SSH, haciendo copias de seguridad remota con rsync de manera más rápida y más segura que con herramientas tradicionales.

**Opciones de rsync**

Éstas son algunas de las principales opciones de rsync:

| Oopción | Descripción |
| ------- | ----------- |
| -g      | Preserva los permisos de grupo de los archivos que se están duplicando |
| -l      | Copia los enlaces simbólicos con enlaces simbólicos |
| -o      | Preserva el usuario de los archivos que se replican |
| -p      | Preserva los permisos de los archivos que se replican |
| -t      | Preserva la hora de modificación de los archivos que se replican |
| -r      | Activa la recursividad, transfiriendo subdirectorios |
| -a      | Es lo mismo que "-Dgloprt" |
| --partial | Activa las transferencias parciales. Si rsync se para, será capaz de completar el resto del archivo cuando se reinicie |
| --progress | Muestra el progreso de la transferencia de archivos |
| -P      | Activa "--partial" y "--progress" |
| -v      | Lista los archivos que se están transfiriendo |
| -vv     | Igual que -v pero también lista los archivos que se ignoran |
| -vvv    | Igual que -vv pero también muestra información de depuración |
| -n      | Muestra los archivos que se transferirán |
| --rsh='ssh' | Usa SSH para la transferencia. También se puede hacer definiendo la variable de entorno RSYNC_RSH como ssh |
| -z      | Activa la compresión |
| -H      | Preserva los enlaces |
| -b`     | Hace copia de todos los archivos destino en lugar de sustituirlos. Se usará cuando se desea mantener versiones antiguas de cada archivo |

Después de las opciones vienen los parámetros de origen y destino. Las rutas pueden ser locales o remotas (`user@host:path`).

## 7.2 Preparativos

* `whereis rsync`, para comprobar si tenemos rsync instalado en el sistema. También podemos verificarlo con: `rsync --version`, `rsync --help`, `zypper info rsync`, etc.
* Si no está debemos instalarlo:
    * `zypper install rsync`, instalar rsync en OpenSUSE.
    * También podemos usar el comando `apt install rsync`, para OpenSUSE o Debian/Ubuntu.

## 7.3 rsync en local

* Crear la siguiente estructura de ficheros en nuestro directorio HOME:
```
├── mydocs
│   ├── a.txt
│   ├── b.txt
│   └── c.txt
└── replica
```
* `rsync -aP mydocs replica`, para crear una réplica exacta de `mydocs` en el directorio `replica`.
* Si repetimos el proceso (`rsync -aP mydocs replica`) no copia nada porque no es necesario.
* Crear `mydocs/d.txt` en local.
* Volver a replicar. Comprobar que sólo se replican lo cambios.
* Eliminar `mydocs/b.txt` en local.
* Ejecutar `rsync -aP --delete mydocs replica`. Comprobamos que sólo se replican lo cambios. En este caso se replica la eliminación del archivo.

## 7.4 rsync remoto

* Crear MV2 con servidor SSH activo.
* Volver a la MV1.
* `ssh usuario2@ip-mv2`, comprobamos que funciona el acceso remoto SSH a la MV2.
* `exit` para salir de la sesión SSH.

Estamos en la MV1.
* Partimos de la siguiente estructura de ficheros en nuestro directorio HOME:

```
mydocs
├── a.txt
├── c.txt
└── d.txt
```

* `rsync -aP --delete mydocs usuario2@ip-mv2:/home/usuario2/`, para replicar los datos del directorio `mydocs` de mi máquina local, al directorio `/home/usuario2`, de la máquina remota `ip-mv2`.
* Crear `mydocs/b.txt`
* Eliminar `mydocs/d.txt`.
* Volver a replicar al servidor remoto.

---
# ANEXO


```
Curso     : 201920
Estado    : Nuevo
Objetivo  : Copias de seguridad usando tar
Requisitos:
```

---

# Copias de seguridad con tar

> Enlaces de interés:
> * [Backup y restauración de backups incrementales con tar](http://systemadmin.es/2015/04/backup-y-restauracion-de-backups-incrementales-con-tar)
> * [Backups con tar: fullbackups, incrementales y diferenciales](https://nebul4ck.wordpress.com/2015/03/20/backups-con-tar-full-backups-e-incrementales/)

Elegir una de las siguientes MV:
1. SO GNU/Linux
2. SO Windows + Cygwin

---
# 1. Preparativos

| Parámetro | Valor                             |
| --------- | --------------------------------- |
| HOME      | /home/nombre-del-alumno           |
| DOCFOLDER | /home/nombre-del-alumno/mydocs    |
| RECFOLDER | /home/nombre-del-alumno/restaurar |

* `tar --version`, para comprobar que lo tenemos instalado en nuestro sistema.
* Crear directorio DOCFOLDER.
    * Crear archivo DOCFOLDER/a.txt. Dentro escribir nombre completo del alumno.
    * Crear archivo DOCFOLDER/b.txt. Dentro escribir la fecha actual.
* `cd /home/nombre-del-alumno`

```
mydocs
├── a.txt
└── b.txt
```

---
# 2. Copia de seguridad total (full-backup)

* `tar cvf backupXX-1-full.tar mydocs`, parea realizar una copia de seguridad total.
* `tar tvc backupXX-1-full.tar`, comprobar el contenido de la copia de seguridad total.
* Crear archivo DOCFOLDER/c.txt. Escribir dentro el título de tu película favorita.
* Realiza copia seguridad total con el nombre `backupXX-2-full.tar`.
* Comprueba el contenido.

Las copias de seguridad total son sencillas de hacer, pero no son eficientes en cuanto a optimizar el almacenamiento. No era necesario volver a copiar los archivos a.txt, ni b.txt porque no han cambiado.

---
# 3. Copia de seguridad incremental

Tenemos lo siguiente:
```
mydocs
   ├── a.txt
   ├── b.txt
   └── c.txt
```

## 3.1 Copia seguridad inicial

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

*(Seguir al siguiente apartado)*

## 3.2 Recuperación de los archivos

Para conseguir restaurar el estado final completo del directorio, necesitaremos usar todos los backup. Esto es el full-backup inicial y todos los sucesivos incrementales.

Pasos para una recuperación completa:
1. Primero descomprimir el full-bakcip inicial.
2. Luego aplicar el incremental usando la opción `--incremental`.

* `cd /home/nombre-del-alumno`
* `mkdir restore`
* `tar xvf backupXX-3-init.tar -C restore/`
* `tar --incremental -xvf backupXX-4-inc.tar -C restore/`
* `tar --incremental -xvf backupXX-5-inc.tar -C restore/`
* `tree restore`

Podemos comprobar que ha añadido el fichero creado (d.txt), pero también se ha borrado el fichero eliminado (b.txt) en el momento de hacer el incremental.

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

---
# 4. Copias diferenciales

Una copia "diferencial" copia o guarda todos los cambios producidos desde la última copia "total". Comprobarlo.

---
# 5. Comparativa

Hacer una copia usando la herramienta que viene por defecto en Windows y comparar con "tar".


```
En CONSTRUCCIÓN!!!
```

# Samba y Cron: Copia de seguridad Samba

El objetivo final de la práctica, es el de crear un script, en un cliente GNU/Linux,
que automatice el proceso de copia de seguridad usando cron y samba.

# Montar el recurso remoto

Ir al equipo cliente:
* Crear la carpeta `/mnt/samba`.
* Montar el recurso `//ip-servidor-samba/alumno1` en /mnt/samba.

NOTA: Este es el recurso compartido [homes] del usuario en el equipo remoto. Recordar los comandos smbclient y smbmount. Ejemplo:
smbmount //ip-servidor-samba/alumno1 /mnt/samba -o user=alumno1,password=clavedealumno1

smbmount //ip-servidor-samba/alumno1 /mnt/samba -o username=alumno1%clavedealumno1

## Hacer la copia de seguridad

Ir al equipo cliente:
* Crear la copia de seguridad en `/mnt/samba/backup`. Recordar el comando tar utilizado.

El fichero de copia de seguridad debe cambiar su nombre en cada ejecución. Ejemplo: copia-de-seguridad-AAAAMMDD.tar.gz, donde AAAA es el año, MM el mes y DD el día de la copia. En los apuntes de backup está resuelto. Consultar el comando `date +%Y%m%d`.

La copia de seguridad es de los datos del cliente Samba, pero el fichero tar.gz de debe mantener en el servidor Samba.

## Desmontar el recurso remoto

Y finalmente, desmontar el recurso compartido samba (usar el comando `umount`).

## Crear un script

Una vez que nos ha funcionado lo anterior, vamos a crear un script para automatizar el proceso.

* Crear el fichero `/usr/local/bin/copia-en-samba`
* Este fichero será un script que contendrá los comandos anteriores para:
    * Montar recurso remoto.
    * Hacer un tar con la copia de seguridad.
    * Y desmontar el recurso remoto.
* No olvidemos dar permisos de ejecución al script.
* Lanzar el script para comprobar que funciona bien.

## Automatizar la tarea con Cron

Ahora vamos a programar el script con cron para que se ejecute automáticamente todos los días a una hora prefijada.

* Usar el comando `crontab -e` para programar la ejecución del script.
* Comprobar que efectivamente todo funciona correctamente.

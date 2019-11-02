
```
Curso      :  
Objetivo   : Crear servicio con System V
Requisitos : Debian
```

----
# Crear un servicio con Upstart

El objetivo de la práctica es la de crear nuestro propio servicio.

---
# 1. Introducción

Vamos a crear un script demonio mykupd.
* Consultar [script de ejemplo base](files/mykupd.rb)
* El script de ejemplo base se ha creado usando ruby como lenguaje de scripting. Es posible usar otros como bash, sh, o PowerShell. Si se va a usar otro diferente se deberá hablar antes con el profesor.
* Hay que ampliar el ejemplo base con lo siguiente.

Funciones del demonio:
* El script mykupd será un demonio para "matar" procesos de los usuarios que estén en nuestra "lista negra" de usuarios (USERS) o procesos (PROCESS).
    * Tendremos una lista (black-list) con los usuarios que NO tienen permitido iniciar procesos.
    * Definir una restricción horaria (timetable).
* El script rkupd iniciará un bucle de la siguiente forma:
    * Esperar 5 segundos antes de seguir (sleep INTERVAL)
    * Compruebar que ningún usuario de la lista negra tiene procesos activos. Comando de ejemplo: "ps -u USER -o pid|grep -v 'PID'"
    Una vez tenemos localizados los PID de los procesos, los matamos (Comando de ejemplo: kill -9 PID).

> INFORMACIóN
>
> * El comando "lsof -ni" o "netstat", dan información de los programas que están haciendo uso de los puertos del equipo.
> * El comando "service --status-all" en GNU/Linux nos muestra información de todos los servicios.
> * El gestor de servicios por defecto del sistema operativo puede ser Systemd, SystemV u Upstart.

---
# 2. Creando nuestro demonio

* Iniciar SO Debian.
* Crear la carpeta  `/etc/mykup`, y un fichero `/etc/mykup/mykup.log` vacío.
* Crear un script `/usr/local/bin/mykupd` que será nuestro demonio. Este script será un bucle que ejecutará una tarea de forma repetitiva.
* Dar permisos ejecución al [script de ejemplo](files/mykupd.rb)
* `/usr/local/bin/mykupd &`, para iniciarlo en background (Segundo plano).
* `ps -ef |grep mykupd`, Comprobar que el demonio está en ekecución.
* En la consola ejecutar `cat /etc/mykup/mykup.log`, para ver los mensajes que aparecen cada intervalo de tiempo.
* `rm /etc/mykup/running`, para indicarle al demonio/servicio que pare.
* Comprobar que el demonio/servicio ha terminado.

> Si aparece un error de permiso denegado al tratar de ejecutar un script, suele deberse a que NO se han puesto permisos de ejecución al citado fichero.

---
# 3. Configurar el demonio dentro de Upstart

Ahora vamos a configurar el servicio, al estilo Upstart.

* Si tenemos una MV Debian. La clonamos por seguridad. Luego instalamos el paquete "upstart".
* Crear un fichero `/etc/init/mykup.conf` con el siguiente contenido:

```
#
# Ejemplo /etc/init/mykup.conf
#
description "Servicio MYKUP"

start on runlevel [2345]
stop on runlevel [!2345]

pre-start script
test -x /usr/local/bin/mykupd || { stop; exit 0; }
end script

exec /usr/local/bin/mykupd
```

* `service mykup start`, probar que el servicio funciona correctamente.
* Además comprobar que el servicio se inicia automáticamente al entrar en runlevel 2.
* Modificar `/etc/init/mykup.conf`, para quitar servicio de runlevel 2 y comprobarlo.

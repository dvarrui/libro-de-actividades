
```
Curso      :  
Objetivo   : Crear servicio con System V
Requisitos : Debian
```

----
# Crear un servicio con System V

El objetivo de la práctica es la de crear nuestro propio servicio.

---
# 1. Script demonio

* Iniciar SO Debian.
* Crear la carpeta  `/etc/mylog`, y un fichero `/etc/mylog/mylog.log` vacío.
* Crear un script `/usr/local/bin/mylogd` que será nuestro demonio. Este script será un bucle que ejecutará una tarea de forma repetitiva.
* Dar permisos ejecución al script. A elegir:
    * [Ejemplo escrito en Bash](files/mylogd.sh)
    * [Ejemplo escrito en Ruby](files/mylogd.rb)

* `/usr/local/bin/mylogd &`, para iniciarlo en background (Segundo plano).
* `ps -ef |grep mylogd`, Comprobar que el demonio está en ekecución.
* En la consola ejecutar `cat /etc/mylog/mylog.log`, para ver los mensajes que aparecen cada intervalo de tiempo.
* `rm /etc/mylog/running`, para indicarle al demonio/servicio que pare.
* Comprobar que el demonio/servicio ha terminado.

> Si aparece un error de permiso denegado al tratar de ejecutar un script, suele deberse a que NO se han puesto permisos de ejecución al citado fichero.

---
# 2 Script de control

Ahora vamos a crear un script `/etc/init.d/mylog`. Será un script que controlará el inicio/parada de nuestro demonio, al estilo System V.

**Fichero de control en Bash**

Si lo hacemos en Bash, podemos crear inicialmente nuestro fichero a partir del ejemplo /etc/init.d/skeleton. Y posteriormente lo podemos modificar según nuestras necesidades. Veamos recomendaciones para modificarlo:

```
#!/bin/bash
### BEGIN INIT INFO
# Provides: mylog
# Required-Start: $remote_fs $syslog
# Required-Stop: $remote_fs $syslog
# Default-Start: 2 3 4 5
# Default-Stop: 0 1 6
# Should-Start: $network
# Should-Stop: $network
# Short-Description: Start kup daemon
### END INIT INFO

[...]
NAME=kupd
DAEMON=/usr/local/bin/mylogd
...
SCRIPTNAME=/etc/init.d/mylog
[...]

do_start() {
#Acción para iniciar el servicio
/usr/local/bin/mylogd &
}

do_stop() {
#Acción para parar el servicio
rm /etc/mylog/running
}

[...]
```

**Fichero de control en Ruby**

* Consultar ejemplo de [fichero kup en Ruby](files/mylogd.rb)
* Dar permisos ejecución al script.
* Probar que el guión de inicio/parada funciona correctamente con nuestro servicio, mediante `/etc/init.d/mylog [start|stop|status]`.

---
# 3. Inicio automático del servicio

* `runlevel` muestra el nivel de ejecución actual. Suele ser un 2 o un 5, por defecto, dependiendo el sistema operativo.
* `insserv mylog`, modifica el inicio del servicio por defecto (Consultar información sobre insserv). Comprobar que nuestro servicio se inicia automáticamente al entrar en runlevel 2.

> Recordar la creación de enlaces simbólicos (Comando ln -s ...) del curso pasado sirve para este también.

* Comprobar el inicio automático del servicio.
* Quitar nuestro servicio de runlevel 2 y comprobarlo.

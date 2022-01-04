
```
Curso       : 202122
Area        : Sistemas operativos, scripting, servicios, procesos
Descripción : Crear script para matar un procesos de usuarios.
Requisitos  : GNU/Linux, comandos de procesos, Ruby
Tiempo      : 5 sesiones
```

# Script: kill-one-user-app

Matar 1 proceso de 1 usuario.

# 1. Introducción

## 1.1 Entrega

* Crear scripts ruby llamados **kill-one-user-app-vX.rb**. Donde X es el número de la versión.
* Entregar también el fichero de configuración de crontab.
* Crear un informe explicando brevemente la actividad.
* La entrega la realizaremos a través del repositorio Git.
* Al terminar etiquetaremos la entrega con *kill-one-user-app* (git tag...).

## 1.2 TEORÍA sobre procesos

> Enlace de interés:
> * [Linux processes and daemons](https://linux-audit.com/running-processes-and-daemons-on-linux-systems)

## 1.3 Estilo de codificación

* La primera línea del script debe contener `#!/usr/bin/env ruby`. OJO el script debe tener permisos de ejecución.
* Los nombres de las variables serán sustantivos que describan correctamente el contenido de la misma.
* Las variables se escriben en minúscula (Por ejemplo: `name`). Si fueran varias palabras se usa el guión bajo (Por ejemplo: `company_name`).
* Los nombres de las funciones/métodos serán verbos que describan correctamente las acciones que realiza.
* El sangrado en Ruby son dos espacios.
* Sólo dejar en el script código que funcione.
* Borrar las líneas de código que no se estén usando.
* Si no se está seguro de borrar una línea, podemos desactivarla como comentario. Añadiendo `#` al comienzo de la línea. Esto es temporal pero se eliminará en la entrega final.
* Cuando un script termina de forma correcta devuelve 0 (`exit 0`), en caso contrario se devuelve un valor distinto de cero (`exit 1`). Todos los comandos funcionan de esta forma. Por ejemplo:

Si hacemos un ping a una IP que responde, vemos que funciona correctamente, se devuelve un 0:

```
> ping 8.8.8.8 -c 1
PING 8.8.8.8 (8.8.8.8) 56(84) bytes of data.
64 bytes from 8.8.8.8: icmp_seq=1 ttl=113 time=34.5 ms

--- 8.8.8.8 ping statistics ---
1 packets transmitted, 1 received, 0% packet loss, time 0ms
rtt min/avg/max/mdev = 34.502/34.502/34.502/0.000 ms
> echo $?
0
```

Si hacemos un ping a una IP que no responde, entonces no funciona correctamente, y se devuelve un 1:

```
> ping 192.168.42.66 -c 1
PING 192.168.42.66 (192.168.42.66) 56(84) bytes of data.

--- 192.168.42.66 ping statistics ---
1 packets transmitted, 0 received, 100% packet loss, time 0ms

> echo $?
1

```

# 2. Crear un script (versión 1)

## 2.1 Descripción

* Crear un script *kill-one-user-app-v1.rb*.
* La función de este script será la de buscar una aplicación (ps -ef) de un usuario determinado, y matar dicho proceso (kill).

## 2.2 Tarea del script

Parámetros del script

| Parámetro | Descripción |
| --------- | ----------- |
| APPNAME   | Nombre de la aplicación que queremos matar |
| USERNAME  | Nombre del usuario que está ejecutando dicha aplicación |

* Al comienzo del script debemos comprobar que somos el usuario root (`whoami`). En caso contrario mostramos un mensaje por pantalla y terminamos el script (`exit 1`).
* Por ahora los parámetros que necesitamos los pondremos en variables al comienzo del script.
* Para nuestras pruebas, crearemos un usuario `demoXX`. El programa que vamos a matar en nuestras pruebas será `htop`, por ejemplo.
* Al comienzo del script se mostrará un mensaje con la hora actual (date).
* En el caso de detectar que el usuario USERNAME tiene en ejecución la aplicación APPNAME:
    * procederemos a matar dicho proceso.
    * y mostraremos un mensaje en pantalla indicando que se ha borrado el proceso. Mostrar información de la siguiente forma "[KILLED] PID : APPNAME : USERNAME".
* El script terminará.

Funciones que vamos a necesitar:
* Para separar las líneas de la salida de un comando y obtener un Array hacemos split("\n").
* Para separar las palabras dentro de una línea, donde el separador es el espacio, usaremos split().

## 2.3 Programar la tarea con watch

> El comando "watch" se puede usar para ejecutar un comando/script de forma periódica.

Terminal 1:
* Usar el comando "watch" para invocar cada 10 segundos el script **kill-one-user-app.rb**.

Terminal 2:
* Iniciamos sesión como "demoXX" e iniciamos el programa "htop".

Terminal 1:
* Vemos como el script muestra un mensaje indicando que ha realizado su función.

Terminal 2:
* El proceso "htop" debe estar parado.

## 2.4 Programar la tarea

* Vamos a modificar APPNAME para que sea el programa `Firefox`.
* Programar el proceso (crontab) para que se ejecute cada 5 minutos desde las 13 hasta las 14 horas. De modo que durante ese intervalo de tiempo el script matará la aplicación del usuario.
* Comprobarlo.

# 3. Script versión 2:  Paso por argumentos

* Crear una copia del script anterior con el nombre *kill-one-user-app-v2.rb*.
* Ahora vamos a modificar el script para que los parámetros anteriores no estén codificados de forma estática dentro del script, sino que los pasaremos por argumentos de la siguiente forma. Ejemplo donde pasamos 2 argumentos al script:

```
> ./kill-one-user-app.rb USERNAME APPNAME
```

> NOTA: Los argumentos se pasan separados por espacios

* En Ruby los argumentos que se pasan por comandos los tenemos dentro de una variable tipo Array o lista llamada ARGV. Por ejemplo ARGV[0] es el primer argumento, etc.
* Al comienzo del script debemos verificar que hemos recibido 2 argumentos (ARGV.size == 2), en caso contrario mostramos un mensaje de ayuda en pantalla y terminamos el script.

# ANEXO

* [Procno - Un monitor de procesos para linux.](https://victorhckinthefreeworld.com/2021/12/23/procno-un-monitor-de-procesos-para-linux/)

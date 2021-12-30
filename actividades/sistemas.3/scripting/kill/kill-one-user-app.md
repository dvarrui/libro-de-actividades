
```
Curso       : 202122
Area        : Sistemas operativos, scripting, servicios, procesos
Descripción : Crear script para matar un procesos de usuarios.
Requisitos  : GNU/Linux, comandos de procesos, Ruby
Tiempo      : 5 sesiones
```

# Script: kill-one-user-app

Matar 1 proceso de 1 usuario.

# 1. Entrega

* Crear un script ruby llamado **kill-one-user-app.rb**.
* Entregar también el fichero de configuración de crontab.
* Crear un informe explicando brevemente la actividad.
* La entrega la realizaremos a través del repositorio Git.
* Al terminar etiquetaremos la entrega con *kill-one-user-app* (git tag...).

# 2. Crear un script

## 2.1 Descripción

* Crear un script *kill-one-user-app.rb*.
* La función de este script será la de buscar una aplicación (ps -ef) de un usuario determinado, y matar dicho proceso (kill).

## 2.2 Tarea del script

Parámetros del script
| Parámetro | Descripción |
| --------- | ----------- |
| APPNAME   | Nombre de la aplicación que queremos matar |
| USERNAME  | Nombre del usuario que está ejecutando dicha aplicación |

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

## 2.5 Paso por argumentos

* Ahora vamos a modificar el script para los parámetros anteriores no estén codificados de forma estática dentro del script, sino que los pasaremos por argumentos de la siguiente forma. Ejemplo:

```
> ./kill-one-user-app.rb USERNAME APPNAME
```

* En Ruby los argumentos que se pasan por comandos los tenemos dentro de una variable tipo Array o lista llamada ARGV. Por ejemplo ARGV[0] es el primer argumento, etc.

# ANEXO

* [Procno - Un monitor de procesos para linux.](https://victorhckinthefreeworld.com/2021/12/23/procno-un-monitor-de-procesos-para-linux/)

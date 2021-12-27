
```
Curso       : 202122
Area        : Sistemas operativos, scripting, servicios, procesos
Descripción : Crear script para matar un procesos de usuarios.
Requisitos  : GNU/Linux, Ruby
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

# 2. Descripción

* Crear un script *kill-one-user-app.rb*.
* La función de este script será la de buscar una aplicación (ps -ef) de un usuario determinado, y matar dicho proceso (kill).

## 2.1 Parámetros del script

| Parámetro | Descripción |
| --------- | ----------- |
| APPNAME   | Nombre de la aplicación que queremos matar |
| USERNAME  | Nombre del usuario que está ejecutando dicha aplicación |
* Inicialmente los parámetros anteriores los pondremos en variables al comienzo del script.
* Para las pruebas, crearemos un usuario `demoXX`. El programa que vamos a matar en nuestras pruebas será `Firefox`, por ejemplo.

Funciones que vamos a necesitar:
* Para separar las líneas de la salida de un comando y obtener un Array hacemos split("\n").
* Para separar las palabras dentro de una línea, donde el seprador es el espacio, usaremos split().

## 2.2 Programar la tarea

* Programar el proceso (crontab) para que se ejecute cada 5 minutos desde las 13 hasta las 14 horas. De modo que durante ese intervalo de tiempo el script matará la aplicación del usuario.

## 2.3 Paso por argumentos

* Ahora vamos a modificar el script para los parámetros anteriores no estén codificados de forma estática dentro del script, sino que los pasaremos por argumentos de la siguiente forma. Ejemplo:

```
> ./kill-one-user-app.rb USERNAME APPNAME
```

* En Ruby los argumentos que se pasan por comandos los tenemos dentro de una variable tipo Array o lista llamada ARGV. Por ejemplo ARGV[0] es el primer argumento, etc.

# ANEXO

* [Procno - Un monitor de procesos para linux.](https://victorhckinthefreeworld.com/2021/12/23/procno-un-monitor-de-procesos-para-linux/) 

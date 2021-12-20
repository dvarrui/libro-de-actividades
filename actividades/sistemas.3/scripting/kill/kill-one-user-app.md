
```
Curso       : 202122
Area        : Sistemas operativos, scripting, servicios, procesos
Descripción : Crear script para matar un procesos de usuarios.
Requisitos  : GNU/Linux, Ruby
Tiempo      : 6 sesiones
```

# Script: kill-one-user-app

Matar 1 proceso de 1 usuario

## 1. Entrega

* Vamos a crear un script ruby llamado **kill-one-user-app.rb**.
* La entrega la realizaremos a través del repositorio Git.
* Al terminar etiquetaremos la entrega con *kill-one-user-app*.

## 2. Descripción

* Crear un script *kill-one-user-app.rb*.
* La función de este script será la de matar (kill) el proceso asociado a la aplicación (APPNAME) de un usuario determinado (USERNAME).
* Parámetros del script:
| Parámetro | Descripción |
| --------- | ----------- |
| APPNAME   | Nombre de la aplicación que queremos matar |
| USERNAME  | Nombre del usuario que está ejecutando dicha aplicación |
* Para las pruebas, crearemos un usuario `demoXX`. El programa que vamos a matar en nuestras pruebas será `Firefox`, por ejemplo.

## Programar la tarea

* Programar el proceso (crontab) para que se ejecute cada 5 minutos desde las 13 hasta las 14 horas.

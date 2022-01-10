
```
Curso       : 201920
Area        : Sistemas operativos, comandos, scripting
Descripción : Introducción a los script para solucionar problemas.
              Estructura secuencial
Requisitos  : Bash, Ruby
Tiempo      :
```

# Scripting: Estructura secuencial

# 1. INFO: Explicación del profesor usando ejemplos

**Ejemplo script secuencial**. Hacer explicación en clase usando el siguiente ejemplo:
* [ejemplo1-mkdir.sh](files/ejemplo1-mkdir.sh)
* [ejemplo2-rm.sh](files/ejemplo2-rm.sh)
* [ejemplo3-mkdir.sh](files/ejemplo3-mkdir.sh)
* [ejemplo4-rm.sh](files/ejemplo4-rm.sh)

# 2. Crear usuarios y borrar usuarios en Windos

## 2.1 Crear el script

Vamos a crear 2 scripts que usando la estructura iterativa van a realizar la siguiente tareas:
* Ir a una MV con Windows
* Hacer script `crear-usuariosXX.bat` en shell script para crear un número de 10 usuarios en el sistema.
    * Los usuarios que se van a crear tendrán los siguientes nombres: "nombre-alumno1w", "nombre-alumno2w", etc.
    * La password del cada usuario será igual a su nombre. Por ejemplo el usuario "david42w", tendrá la clave "david42w".
* Hacer script `borrar-usuariosXX.bat` en shell script para borrar un número de 10 usuarios del sistema.
    * Los usuarios que se van a borrar tendrán los siguientes nombres: "nombre-alumno1w", "nombre-alumno2w", etc.

## 2.2 Comprobar

* Estamos en la MV Windows
* Abrir un terminal CMD.
* Consultar la lista de usuarios (net... Lo debes recordar de la Unidad 2).
* Ejecutar el script de creación.
* Consultar la lista de usuarios.
* Ejecutar el script de borrado.
* Consultar la lista de usuarios.

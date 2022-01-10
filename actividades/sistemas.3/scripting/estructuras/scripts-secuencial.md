
```
Curso       : 201920
Area        : Sistemas operativos, comandos, scripting
Descripción : Introducción a los script para solucionar problemas.
              Estructura secuencial
Requisitos  : Bash, Ruby
Tiempo      :
```

# Scripting: Estructura secuencial

# 1. Teoría

Explicación del profesor usando ejemplos

**Ejemplo script secuencial**. Hacer explicación en clase usando el siguiente ejemplo:
* [ejemplo1-mkdir.sh](files/ejemplo1-mkdir.sh)
* [ejemplo2-rm.sh](files/ejemplo2-rm.sh)
* [ejemplo3-mkdir.sh](files/ejemplo3-mkdir.sh)
* [ejemplo4-rm.sh](files/ejemplo4-rm.sh)
* [ejemplo5-ruby.rb](files/ejemplo5-ruby.rb)

# 2. Práctica en Bash

Crear usuarios y borrar usuarios

## 2.1 Recordatorio de Bash

* Recordar que el script comienza con `#!/usr/bin/env bash`
* Para ejecutar un comando del sistema hacemos `COMANDO-DEL-SISTEMA`.
* Se usa `exit 0` para acabar el programa OK y `exit 1` para terminar con error.

## 2.2 Crear el script

Vamos a crear 2 scripts que usando la estructura secuencial.
* Ir a una MV con GNU/Linux.
* Hacer script `crear-usuariosXX.sh` en Bash para crear un número de 10 usuarios en el sistema.
    * Los usuarios que se van a crear tendrán los siguientes nombres: "nombre-alumno1g", "nombre-alumno2g", etc.
    * La password del cada usuario será igual a su nombre. Por ejemplo el usuario "david42g", tendrá la clave "david42g".
* Hacer script `borrar-usuariosXX.sh` en Bash para borrar un número de 10 usuarios del sistema.
    * Los usuarios que se van a borrar tendrán los siguientes nombres: "nombre-alumno1g", "nombre-alumno2g", etc.

## 2.2 Comprobar

* Estamos en la MV.
* Abrir un terminal.
* Consultar la lista de usuarios inicialmente.
* Ejecutar el script de creación y consultar la lista de usuarios.
* Ejecutar el script de borrado y consultar la lista de usuarios.

# 3. Práctica en Ruby

Crear usuarios y borrar usuarios

## 3.1 Recordatorio de Ruby

* Recordar que el script comienza con `#!/usr/bin/env ruby`
* Para ejecutar un comando del sistema hacemos `system("COMANDO-DEL-SISTEMA")`.
* Se usa `exit 0` para acabar el programa OK y `exit 1` para terminar con error.

## 3.2 Crear el script

Vamos a crear 2 scripts que usando la estructura secuencial.
* Ir a una MV con GNU/Linux.
* Hacer script `crear-usuariosXX.rb` en Ruby para crear un número de 10 usuarios en el sistema.
    * Los usuarios que se van a crear tendrán los siguientes nombres: "nombre-alumno1g", "nombre-alumno2g", etc.
    * La password del cada usuario será igual a su nombre. Por ejemplo el usuario "david42g", tendrá la clave "david42g".
* Hacer script `borrar-usuariosXX.rb` en Ruby para borrar un número de 10 usuarios del sistema.
    * Los usuarios que se van a borrar tendrán los siguientes nombres: "nombre-alumno1g", "nombre-alumno2g", etc.

## 3.3 Comprobar

* Estamos en la MV.
* Abrir un terminal.
* Consultar la lista de usuarios inicialmente.
* Ejecutar el script de creación y consultar la lista de usuarios.
* Ejecutar el script de borrado y consultar la lista de usuarios.

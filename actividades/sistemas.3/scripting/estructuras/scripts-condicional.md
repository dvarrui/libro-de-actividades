
```
Curso       : 202122
Area        : Sistemas operativos, comandos, scripting
Descripción : Introducción a los script para solucionar problemas.
              Estructura condicional
Requisitos  : Bash, Ruby
Tiempo      :
```

# Scripring: Estructura condicional

# 1. INFO: Explicación del profesor usando ejemplos

**Ejemplo script secuencial**. Hacer explicación en clase usando el siguiente ejemplo:
* [ejemplo1-mkdir.sh](files/ejemplo1-mkdir.sh)
* [ejemplo2-rm.sh](files/ejemplo2-rm.sh)
* [ejemplo3-mkdir.sh](files/ejemplo3-mkdir.sh)
* [ejemplo4-rm.sh](files/ejemplo4-rm.sh)

# 2. Práctica en Bash

Crear usuarios y borrar usuarios en GNU/Linux

## 2.1 Recordatorio de Bash

* Recordar que el script comienza con `#!/usr/bin/env bash`
* Para ejecutar un comando del sistema hacemos `COMANDO-DEL-SISTEMA`.
* Se usa `exit 0` para acabar el programa OK y `exit 1` para terminar con error.
* Ejemplo de condicional en Bash:
```
if [ CONDICION ]; then
  HACER ALGO
fi
```
* Se usa `read` para preguntar un valor al usuario.
* Se usa `$1` para leer el primer argumento, `$2` el segundo. etc.

## 2.2 Crear el script

Vamos a crear un script usando la estructura condicional.

* Ir a una MV con GNU/Linux.
* Hacer script `elegirXXuser.sh` en Bash.
    * El script preguntará al usuario qué quiere hacer: (c) Crear usuarios o (b) Borrar usuarios.
    * Si se elige (c) se ejecuta el script secuencial `crear-usuariosXX.sh`.
    * Si se elige (b) se ejecuta el script secuencial `borrar-usuariosXX.sh`.

## 2.3 Comprobar

* Estamos en la MV GNU/Linux.
* Abrir un terminal.
* Consultar contenido inicial de /etc/passwd.
* Ejecutar el script eligiendo c y consultar contenido de /etc/passwd.
* Ejecutar el script eligiendo b y consultar contenido de /etc/passwd.
* Ejecutar el script eligiendo d. ¿Qué sucede?

## 2.4 Modificar el script

Vamos a modificar script usando la estructura condicional.

* Ir a una MV con GNU/Linux.
* Hacer script `elegirXXvar.sh` en Bash.
    * El script tendrá una variable que podrá tomar el valor `c` o `b` sin preguntar al usuario.
    * Si se elige (c) se ejecuta el script secuencial `crear-usuariosXX.sh`.
    * Si se elige (b) se ejecuta el script secuencial `borrar-usuariosXX.sh`.
* Comprobarlo.

# 3. Práctica en Ruby

Crear usuarios y borrar usuarios en GNU/Linux

## 3.1 Recordatorio de Ruby

* Recordar que el script comienza con `#!/usr/bin/env ruby`
* Para ejecutar un comando del sistema hacemos `system("COMANDO-DEL-SISTEMA")`.
* Se usa `exit 0` para acabar el programa OK y `exit 1` para terminar con error.
* Ejemplo de condicional en Ruby:
```
if (CONDICION)
  HACER ALGO
else
  HACER OTRA COSA
end
```
* Se usa `gets` para preguntar un valor al usuario de forma interactiva.
* Se usa `ARGV[0]` para leer el primer argumento, `ARGV[1]` para el segundo, etc.

## 3.2 Crear el script

Vamos a crear un script usando la estructura condicional.

* Ir a una MV con GNU/Linux.
* Hacer script `elegirXX.rb` en Ruby.
    * El script preguntará al usuario qué quiere hacer: (1) Crear usuarios o (2) Borrar usuarios.
    * Si se elige (1) se ejecuta el script secuencial `crear-usuariosXX.rb`.
    * Si se elige (2) se ejecuta el script secuencial `borrar-usuariosXX.rb`.

## 3.3 Comprobar

* Estamos en la MV GNU/Linux.
* Abrir un terminal.
* Consultar contenido inicial de /etc/passwd.
* Ejecutar el script eligiendo 1 y consultar contenido de /etc/passwd.
* Ejecutar el script eligiendo 2 y consultar contenido de /etc/passwd.
* Ejecutar el script eligiendo 0. ¿Qué sucede?

## 3.4 Modificar el script

Vamos a modificar script usando la estructura condicional.

* Ir a una MV con GNU/Linux.
* Hacer script `elegirXXarg.rb` en Bash.
    * El script leerá los argumentos a la hora de ejecutarse.
    * Si se elige (c) se ejecuta el script secuencial `crear-usuariosXX.rb`.
    * Si se elige (b) se ejecuta el script secuencial `borrar-usuariosXX.rb`.
* Comprobarlo.

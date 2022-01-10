
```
Curso       : 202122
Area        : Sistemas operativos, comandos, scripting
Descripción : Introducción a los script para solucionar problemas.
              Estructura iterativa
Requisitos  : Bash, Ruby
Tiempo      :
```

# Scripting: Estructura iterativa

# 1. Teoría

## 1.1 Ejemplos

**Ejemplos: Find and copy**. Hacer explicación en clase usando los siguientes ejemplos:
* [backup1-iterar.sh](files/backup1-iterar.sh)
* [backup2-filtrar.sh](files/backup2-filtrar.sh)
* [backup3-argumentos.sh](files/backup3-argumentos.sh)
* [backup4-ruby.rb](files/backup4-ruby.rb)
* [backup5-windows.rb](files/backup5-windows.rb)

# 2. Práctica Bash

Vamos crear usuarios y borrar usuarios en GNU/Linux

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
* Ejemplo de bucle en Bash:

```
for i in VALORES do
  HACER ALGO CON CADA i
done
```

## 2.1 Crear scripts

Vamos a crear scripts usando la estructura iterativa.
* Ir a una MV con GNU/Linux.
* Hacer la segunda versión del script `crear-usuariosXXv2.sh` pero usando la estructura iterativa.
* Hacer la segunda versión del script `borrar-usuariosXXv2.sh` pero usando la estructura iterativa.
* Hacer la segunda versión del script `elegirXXv2.sh` pero en este caso se modificará para invocar a los scripts nuevos que acabamos de crear (v2).

## 2.2 Comprobar

* Estamos en la MV GNU/Linux.
* Abrir un terminal.
* Consultar contenido inicial de /etc/passwd.
* Ejecutar el script eligiendo 1 y consultar contenido de /etc/passwd.
* Ejecutar el script eligiendo 2 y consultar contenido de /etc/passwd.
* Ejecutar el script eligiendo 0. ¿Qué sucede?

# 3. Práctica Ruby

Vamos crear usuarios y borrar usuarios en GNU/Linux

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
* Ejemplo de bucle en Ruby:

```
for i in VALORES do
  HACER ALGO CON CADA i
end
```

## 3.2 Crear scripts

Vamos a crear scripts usando la estructura iterativa.
* Ir a una MV con GNU/Linux.
* Hacer la segunda versión del script `crear-usuariosXXv2.rb` pero usando la estructura iterativa.
* Hacer la segunda versión del script `borrar-usuariosXXv2.rb` pero usando la estructura iterativa.
* Hacer la segunda versión del script `elegirXXv2.rb` pero en este caso se modificará para invocar a los scripts nuevos que acabamos de crear (v2).

## 3.3 Comprobar

* Estamos en la MV GNU/Linux.
* Abrir un terminal.
* Consultar contenido inicial de /etc/passwd.
* Ejecutar el script eligiendo 1 y consultar contenido de /etc/passwd.
* Ejecutar el script eligiendo 2 y consultar contenido de /etc/passwd.
* Ejecutar el script eligiendo 0. ¿Qué sucede?


```
Curso       : 202122
Area        : Sistemas operativos, comandos, scripting
Descripción : Introducción a los script para solucionar problemas.
              Estructura iterativa
Requisitos  : Bash, Ruby
Tiempo      : 4
```

# Scripting: Estructura iterativa

# 1. Teoría para LEER y ENTENDER

## 1.1 Solucionar problemas

Si encuentras problemas en un script, porque tienes fallos y/o errores, y no detectas el fallo.
1. Lee bien los mensaje de error y piensa qué lo pudo causar. Compruébalo.
2. Haz un debug del script (sigue leyendo).

Para depurar un script hay varias técnicas.
1. Poner mensajes por pantalla cada ciertas líneas a ver qué pasa o
1. Poner comentarios en las líneas e irlas quitando poco a poco o
1. **Ejecutar el script paso a paso**.

Técnicar para depurar:
* [Técnicas para depurar Bash](https://atareao.es/tutorial/scripts-en-bash/depurar-en-bash/)
* [En Ruby usamos la gema pry-byebug](https://rubygems.org/gems/pry-byebug)

## 1.2 Entrega

Entregar un informe por la plataforma GitHub, junto con los scripts que se hayan creado.
* Todos los scripts que se suban al GitHub  deben funcionar.
* Si algún script se sube al GitHub y no funciona... debe renombrarse como "devel-nombredelscript".

> ACLARACIÓN:
> * El informe va a tener pocas frases si la cosa va bien (_"hago esto por esto... y aquí pongo esto para que pase esto... etc"_) y  se entiende el script.
> * Si no se entiende habrá que acompañarlo de más texto explicativo.
> * Si va mal hay que indicar los problemas

## 1.3 Explicación del profesor usando ejemplos

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

## 2.2 Crear scripts

Vamos a crear scripts usando la estructura iterativa, condicional y secuencial.
* Ir a una MV con GNU/Linux.
* Hacer la segunda versión del script `crear-usuariosXXiter.sh` pero usando la estructura iterativa.
    * Comenzar el script ejecutando `figlet nombre-alumnoXX`.
* Hacer la segunda versión del script `borrar-usuariosXXiter.sh` pero usando la estructura iterativa.
    * Comenzar el script ejecutando `figlet nombre-alumnoXX`.
* Hacer la segunda versión del script `elegirXXarg.sh` pero en este caso se modificará para invocar a los scripts nuevos que acabamos de modificar para que sean interactivos.
* Añadir una opción nueva "e" para consultar el estado actual de los usuarios.

## 2.3 Comprobar

* Estamos en la MV GNU/Linux.
* Abrir un terminal.
* Ejecutar el script eligiendo "e".
* Ejecutar el script eligiendo "c".
* Ejecutar el script eligiendo "e".
* Ejecutar el script eligiendo "b".
* Ejecutar el script eligiendo "e".

# 3. Práctica Ruby

Vamos crear usuarios y borrar usuarios en GNU/Linux

## 3.1 INFO: Recordatorio de Ruby

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
* OPCIONAL: Si quieres ser un auténtico "rubista", aquí tienes la forma de iterar estilo Ruby 100%. ¡Suerte!

```
VALORES.each do |i|
  HACER ALGO CON CADA i
end
```

O también...

```
VALORES.each { |i| HACER ALGO CON CADA i }
```

> Lo mejor de Ruby... tienes muchos caminos. Elige el que te haga más feliz. ;-)

## 3.2 Crear scripts

Vamos a crear scripts usando la estructura iterativa.
* Ir a una MV con GNU/Linux.
* Hacer la segunda versión del script `crear-usuariosXXiter.rb` pero usando la estructura iterativa.
* Hacer la segunda versión del script `borrar-usuariosXXiter.rb` pero usando la estructura iterativa.
* Hacer la segunda versión del script `elegirXXarg.rb` pero en este caso se modificará para invocar a los scripts nuevos que acabamos de crear.
* Añadir una opción nueva "e" para consultar el estado actual de los usuarios.

## 3.3 Comprobar

* Estamos en la MV GNU/Linux.
* Abrir un terminal.
* Ejecutar el script eligiendo "e".
* Ejecutar el script eligiendo "c".
* Ejecutar el script eligiendo "e".
* Ejecutar el script eligiendo "b".
* Ejecutar el script eligiendo "e".

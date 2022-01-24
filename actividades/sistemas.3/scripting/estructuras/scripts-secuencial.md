
```
Curso       : 202122
Area        : Sistemas operativos, comandos, scripting
Descripción : Usar script con estructura secuencial para solucionar problemas.
              Crear y borrar usuarios del sistema.
Requisitos  : Bash, Ruby
Tiempo      : 2 sessiones
```

# Scripting: Estructura secuencial

# 1. Teoría

## 1.1 Script de shell - Wikipedia, la enciclopedia libre

Leamos y entendamos la definición:
* Wikipedia - [Script de shell/lenguaje de programación]((https://es.m.wikipedia.org/wiki/Script_de_shell)
* Consulta las dudas en clase.

## 1.2 Entrega

Entregar un informe por la plataforma GitHub, junto con los scripts que se hayan creado.
* Todos los scripts que se suban al GitHub deben funcionar.
* Si algún script se sube al GitHub y no funciona... debe renombrarse como "devel-nombredelscript".

> ACLARACIÓN:
> * El informe va a tener pocas frases si la cosa va bien (_"hago esto por esto... y aquí pongo esto para que pase esto... etc"_) y  se entiende el script.
> * Si no se entiende habrá que acompañarlo de más texto explicativo.
> * Si va mal hay que indicar los problemas

## 1.3 Ejemplos

Explicación del profesor usando ejemplos

**Ejemplo script secuencial**: Hacer explicación en clase usando los ejemplos:
* [ejemplo1-mkdir.sh](files/ejemplo1-mkdir.sh)
* [ejemplo2-rm.sh](files/ejemplo2-rm.sh)
* [ejemplo3-mkdir.sh](files/ejemplo3-mkdir.sh)
* [ejemplo4-rm.sh](files/ejemplo4-rm.sh)
* [ejemplo5-ruby.rb](files/ejemplo5-ruby.rb)

---
# 2. Práctica en Bash

_¡Empezamos a trabajar!_

Crear usuarios y borrar usuarios mediante un script Bash.

## 2.1 Recordatorio de Bash

* Recordar que el script comienza con `#!/usr/bin/env bash`
* Para ejecutar un comando del sistema escribimos el `COMANDO-DEL-SISTEMA`.
* Se usa `exit 0` para acabar el programa OK y `exit 1` para terminar con error.

## 2.2 Crear el script

Vamos a crear 2 scripts que usando la estructura secuencial.
* Ir a una MV con GNU/Linux.
* Hacer script `crear-usuariosXX.sh` en Bash para crear un número de 10 usuarios en el sistema.
    * Los usuarios que se van a crear tendrán los siguientes nombres: "nombre-alumno1b", "nombre-alumno2b", etc.
    * La password de cada usuario será igual a su nombre. Por ejemplo el usuario "david42g", tendrá la clave "david42g".
* Hacer script `borrar-usuariosXX.sh` en Bash para borrar los 10 usuarios anteriores.
  * Cuando el script termine ejecuta `cowsay "¡Usuarios borrados!"`

## 2.2 Comprobar

* Estamos en la MV.
* Abrir un terminal.
* Consultar la lista de usuarios del sistema. Sólo hace falta ver los que tienen la shell bash (Pista: usar cat y grep).
* Ejecutar el script de creación y consultar la lista de usuarios. Comprobar que funciona la creación.
* Ejecutar el script de borrado y consultar la lista de usuarios. Comprobar que funciona el borrado.

# 3. Práctica en Ruby

Crear usuarios y borrar usuarios con un script Ruby.

## 3.1 Recordatorio de Ruby

* Recordar que el script comienza con `#!/usr/bin/env ruby`
* Para ejecutar un comando del sistema hacemos `system("COMANDO-DEL-SISTEMA")`.
* Se usa `exit 0` para acabar el programa OK y `exit 1` para terminar con error.

## 3.2 Crear el script

Vamos a crear 2 scripts que usando la estructura secuencial.
* Ir a una MV con GNU/Linux.
* Hacer script `crear-usuariosXX.rb` en Ruby para crear un número de 10 usuarios en el sistema.
    * Los usuarios que se van a crear tendrán los siguientes nombres: "nombre-alumno1r", "nombre-alumno2r", etc.
    * La password del cada usuario será igual a su nombre. Por ejemplo el usuario "david42r", tendrá la clave "david42r".
* Hacer script `borrar-usuariosXX.rb` en Ruby para borrar los usuarios anteriores.
    * Cuando el script termine ejecuta `xcowsay "¡Usuarios borrados!"`

## 3.3 Comprobar

* Estamos en la MV.
* Abrir un terminal.
* Consultar la lista de usuarios antes de empezar. Sólo necesitamos ver los usuarios con shell Bash (Pista: usar cat y grep).
* Ejecutar el script de creación y consultar la lista de usuarios. Comprobar que el script ha funcionado.
* Ejecutar el script de borrado y consultar la lista de usuarios. Comprobar que el script ha funcionado.

# ANEXO


Recomendaciones
1. Sólo secuencial. NO bucle no condicionales
2. La valirable "us" no significa nada ponle otro nombre con más sentido.
3. Veo que has usado colores... bien. Si vas a poner colores en Ruby hazlo con esto:
   https://rubygems.org/gems/colorize
4. Compruebas el usuario por /home/usuario pero luego lo haces por /etc/passwd
   Unifícalo mejor.

   Mira el comando "id" a ver si te sirve.
   O el comando "getent passwd" o el comando "getent shadow"

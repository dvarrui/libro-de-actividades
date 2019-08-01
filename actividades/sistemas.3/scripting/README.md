
```
Descripción: Un poco de teoría sobre scripting
```
---

# Scripting

Enlaces de interés
* ¿Cómo crear [scripts que no requieran la intervencion del usuario]
 (https://es.opensuse.org/Scripts_sin_intervencion_del_usuario)?
https://es.opensuse.org/Scripts_sin_intervencion_del_usuario

# 1. Teoría

## 1.1 ¿Qué es? y ¿Para qué sirve?

Scripting es una técnica que emplearemos para automatizar tareas.
Cuando tengamos algún cometido que repetimos con cierta frecuencia... es el momento de plantearse el resolverlo _"de una vez para siempre jamás"_. Esto es, crear un script usando las técnicas de scripting.

Seguro que alguien dirá: "Bueno, eso de scripting...eso es programar". Vale. Si. Scripting es programar pero programar no es scripting. Hay determinadas _"técnicas/habilidades/características"_ que son específicas de los programadores de scripts que no tienen el resto de programadores.

Hacer un script es crear un fichero de texto plano con permisos de ejecución. En dicho fichero pondremos básicamente órdenes al sistema operativo (comandos) para realizar algún trabajo determinado.

Los scripts NO se compilan, pero SI se interpretan. Además para hacer un script podremos usar:
* estructuras de programación condicionales (if-then)
* Estructuras de control iterativas o bucles (for, while, etc.)
* Reutilizar código (Importar bibliotecas)
* Etc.

El cometido típico de un script es el de resolver problemas de administración de sistemas operativos pero... al final son programas... y con los programas puedes hacer casi lo que te imagines. ;-)

## 1.2  Herramientas o lenguajes de Scripting

Básicamente lo que necesitaremos será conocer los comandos del sistema y para ejecutarlos usaremos la shell del sistema. A continuación pongo una lista (no exaustiva) de varios tipos de herramientas para crear scripts:
* Bash
* Ruby
* Python
* PowerShell
* BAT
* etc.

---

# ANEXO

* [Scripts básicos en Bash](https://www.ochobitshacenunbyte.com/2019/07/17/scripts-basicos-en-bash)

## HackerHighSchool Lesson 2: Presentación y Objetivos

http://www.hackerhighschool.org/lessons/HHS_es2_Comandos_Esenciales.v2.pdf

Tanto si recuerdas a Hugh Jackman en la película Operación Swordfish
o a Trinity hackeando un sistema UNIX en Matrix Reloaded,
cuando piensas en un hacker, lo imaginas trabajando con la línea de comandos.
Y por una buena razón.

Puedes hacer cosas formidables con la interfaz de línea de comandos (CLI).
No necesitas ser un maestro usándola, pero deberías sentirte cómodo trabajando con ella.

Una vez que hayas aprendido los conceptos fundamentales del CLI, podrás comenzar a utilizar estos comandos en archivos de texto
(los llamados scripts); es la forma más sencilla de programar.

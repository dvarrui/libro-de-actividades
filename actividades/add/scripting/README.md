
# Scripting

---

# ¿Qué es? y ¿Para qué sirve?

Scripting es una técnica que emplearemos para automatizar tareas.
Cuando tengamos algún cometido que repetimos con cierta frecuencia... es el momento de plantearse el resolverlo _"de una vez para siempre jamás"_. Esto es, crear un script usando las técnicas de scripting.

Seguro que alguien dirá: "Bueno, eso de scripting...eso es programar". Vale. Si. Scripting es programar pero programar no es scripting. Hay determinadas _"técnicas/habilidades/características"_ que son específicas de los programadores de scripts que no tienen el resto de programadores.

Hacer un script es crear un fichero de texto plano con permisos de ejecución. En dicho fichero pondremos básicamente órdenes al sistema operativo (comandos) para realizar algún trabajo determinado.

Los scripts NO se compilan, pero SI se interpretan. Además para hacer un script podremos usar:
* estructuras de programación condicionales (if-then)
* Estructuras de control iterativas o bucles (for, while, etc.)
* Importar bibliotecas
* Etc.

El cometido típico de un script es el de resolver problemas de administración de sistemas operativos pero... al final son programas... y con los programas puedes hacer casi lo que te imagines. ;-)

---

# Herramientas o lenguajes de Scripting

Básicamente lo que necesitaremos será conocer los comandos del sistema y para ejecutarlos usaremos la shell del sistema. A continuación pongo una lista (no exaustiva) de varios tipos de herramientas para crear scripts:
* Bash
* PowerShell
* Python
* Ruby
* BAT
* etc.

---

# Vamos paso a paso

Empezamos a partir de un problema (de administración de sistemas operativos) que queremos automatizar o simplicar mediante un script.

* Definir o acotar el problema: Define con claridad qué quieres resolver, cúal es el problema. Hazte con una lista de comandos que crees que te puede ayudar.
* Divide y vencerás.
    * Si el problema es muy complejo (para nosotros) entonces divídelo en trozos más sencillos hasta que te quede un problema pequeño que sepas hacer.
    * Cuando tengamos resueltos los problemas pequeños, unimos las soluciones y ya tenemos resuelto el problema gordo.
* Crea un fichero de texto y escribe comentarios con tu plan de acción:
```
# Problema : XXX
# Autor    : David Vargas
# Fecha    : 20190108
#
# Para resolver el problema XXX tengo que hacer:
#
# 1. bla bla bla
#    Lista de comandos que me pueden ayudar...  
#
# 2. bla bla bla
#    Lista de comandos que me pueden ayudar...  
#
# 3. bla bla bla
#    Lista de comandos que me pueden ayudar...  
#
#
# ¡Y listo!
```
* Ahora vamos a ir paso por paso.
* Pon los comandos que necesitamos para resolver el punto 1. Cuando creas que lo tienes, ejecuta y prueba. Funciona: ¡bien! sigue al paso 2. ¿No funciona? Pues seguimos en el punto 1.
* A veces si estamos perdidos ayuda imprimir mensajes en pantalla para saber qué está ocurriendo o utilizar técnicas de depuración de programas (ya las veremos dentro de un rato). Para imprimir un mensaje en pantalla:
```
echo "Hola Mundo!"           # Bash

puts "Hola Mundo!"           # Ruby
`echo "Hola Mundo!"`         # Ruby
system('echo "Hola Mundo!"') # Ruby

print("Hola Mundo!")         # Python
```
* Cuando termines el paso 1, vamos al paso 2... y así sucesivamente. Siempre probando a ver qué tal va todo.

---

# Actividades

Vamos a hacer las siguientes actividades:

| Id | Problema a resolver | Realizado por |
| -- | ------------------- | ------------- |
| S1 | Elegiremos alguna actividad que ya hayamos realizado para resolverla con un script | En grupo entre el profesor y los alumnos |
| S2 | Los alumnos elegirán alguna actividad que ya hayamos realizado en clase 1º o 2º | Por cada alumno de forma individual |

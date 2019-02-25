
# Scripting con Ruby

# 1. Teoría: Consultar el siguiente material

* Consultar el material para aprender paso a paso.
    * [Charla: Ruby scripting](https://github.com/dvarrui/proyectos-de-ejemplo/tree/master/charlas/ruby/scripting)
* Consultar y resolver las dudas en clase.

---

# 2. Teoría: Vamos paso a paso

Empezamos a partir de un problema (de administración de sistemas operativos) que queremos automatizar o simplificar mediante un script.

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

# 3. Actividad

* Plantear un problema para resolver. Ideas:
    * Elegir alguna actividad que ya hayamos realizado en clase de 1º o 2º.
    * Elegir un problema o tarea de Sysadmin que repotamos con frecuencia.
    * Otros
* Requisitos del script:
    * Paso de argumentos al script. Dos funciones como mínimo.
    * Resolver un problema de Sysadmin (Si tienes dudas... consulta al profesor)
    * Usar comandos propios del sistema operativo (Elige el sistema operativo que desees).
    * Usar variables.
    * Usar estructura if-then.
* Mejoras:
    * Usar estructura def (para definir bloques de código).
    * Separar funciones/métodos (def) a una librería externa.
    * Tener las acciones de hacer y deshacer.

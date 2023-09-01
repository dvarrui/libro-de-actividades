```
EN CONSTRUCCIÓN!!!
```

# GDScript

GDScript es un lenguaje de programación integrado en el motor de videojuegos GodotEngine. Además de GDScript, Godot permite usar otros lenguajes de programación como CSharp o VisualScript. Pero para aprender a programar de forma sencilla GDScript será el más adecuado.

Para empezar a hacer nuestros primeros programas vamos a necesitar un **entorno** para ejecutarlos:
* Podemos usar [Godot Playground](https://gd.tumeo.space/)
* O bien descargar e instalar el motor [GodotEngine de la página web](https://godotengine.org/).

> Enlaces de interés:
> * [Doc GDScript basics](https://docs.godotengine.org/es/stable/tutorials/scripting/gdscript/gdscript_basics.html)

## Videotutorial GDSCript

Curso de GD Script desde cero (Irwin Rodríguez):
1. [Variables y Comentarios](https://www.youtube.com/watch?v=xmgXQb3O3ec&list=PLlFOKoWJ_eWbOl80xcanh9RC-WqlmgtIW)
2. [Sentencia IF/ELIF/ELSE](https://www.youtube.com/watch?v=H46WcOmiULA&list=PLlFOKoWJ_eWbOl80xcanh9RC-WqlmgtIW&index=2)
3. [Estructura match](https://www.youtube.com/watch?v=Cq-Yd6H3MaI&list=PLlFOKoWJ_eWbOl80xcanh9RC-WqlmgtIW&index=3)
4. [Iteradores - for](https://www.youtube.com/watch?v=7J_bVdcYk5g&list=PLlFOKoWJ_eWbOl80xcanh9RC-WqlmgtIW&index=4)
5. [Iteradores - while](https://www.youtube.com/watch?v=ly86XguJMBc&list=PLlFOKoWJ_eWbOl80xcanh9RC-WqlmgtIW&index=5)
6. [Arrays](https://www.youtube.com/watch?v=kLwXIHYtLtw&list=PLlFOKoWJ_eWbOl80xcanh9RC-WqlmgtIW&index=6)
7. [Diccionarios](https://www.youtube.com/watch?v=JyL0p3Ap40w&list=PLlFOKoWJ_eWbOl80xcanh9RC-WqlmgtIW&index=7)
8. [El operador ternario](https://www.youtube.com/watch?v=cEdUozWX8-k&list=PLlFOKoWJ_eWbOl80xcanh9RC-WqlmgtIW&index=8)
9. [Funciones](https://www.youtube.com/watch?v=tlyPuiFTxHc&list=PLlFOKoWJ_eWbOl80xcanh9RC-WqlmgtIW&index=9)
10. [Funciones que retornan valor](https://www.youtube.com/watch?v=VUE9LFQsMTU&list=PLlFOKoWJ_eWbOl80xcanh9RC-WqlmgtIW&index=10)
11. [Introducción al editor](https://www.youtube.com/watch?v=RNeMHxxynxM&list=PLlFOKoWJ_eWbOl80xcanh9RC-WqlmgtIW&index=11)

## Comentarios y variables

Un **script** es cada fichero que contiene instrucciones de programación. Un programa puede ser un script o un conjunto de scripts.

Los **comentarios** son líneas de texto que se incluyen en el script a modo de ayuda o información para el programador, y nunca se ejecutan. Lo ideal sería escribir los comentarios en inglés para facilitar su lectura a cualquier programador. Podemos tener comentarios de una línea usando `#` al principio, comentarios multilínea usando `"""` en la primera y en la última línea de comentarios.

Las **variables** en programación se pueden ver como "etiquetas" o "nombres" que otorgamos a determinados datos/objetos dentro de un programa. Lo datos más básicos son los llamados **datos primitivos**, esto es números enteros, números con decimales, cadenas de texto, boolean (verdadero/falso). Veamos ejemplo:

```python
var age = 16        # La variable age se asocia a un dato de número entero 16
var pi = 3.14       # La variable pi se asocia a un dato de número con decimales
var name = "Obiwan" # La variable name se asocia a una cadena de texto
```

> Habitualmente usaremos nombres de variables en minúsculas y en inglés.

GDScript es un lenguaje dinámico.

## Funciones

* `extends Node2D` se usa para indicar que tenemos la definición de un objeto que hereda de la clase `Node2D`.
* La función `_ready()` se invoca una sola vez, cuando el objeto/nodo entra en la escena activa.
* Los nombres de las funciones comienzan por el símbolo `_` cuando son funciones privadas del objeto/nodo.

## Videotutorial de Godot 4

Introducción a Godot 4 de "Leedeo Studio":
4. [GDScript 2.0](https://www.youtube.com/watch?v=x9N1gw2qWEQ&t=115s)
5. [Autoload o singleton](https://www.youtube.com/watch?v=zOxcxXhB6DI)
6. [Señales](https://www.youtube.com/watch?v=BXkJeEGZr4o)
7. [Depurar código GDScript 2.0](https://www.youtube.com/watch?v=VtJmmE_FGIk)
8. [Función de movimiento global](https://www.youtube.com/watch?v=V020M2DabXo)

```python
var axis : Vector2

func get_axis() -> Vector2:
    axis.x = int(Input.is_action_pressed("ui_right")) - int(Input.is_action_pressed("ui_left"))
    axis.y = int(Input.is_action_pressed("ui_up")) - int(Input.is_action_pressed("ui_down"))
    return axis.normalized()
```

9. [Diseño de interfaces](https://www.youtube.com/watch?v=5Pz1pvjjCfI)

# ANEXO

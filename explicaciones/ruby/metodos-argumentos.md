
# Métodos y argumentos

## Método

Un método es como ponerle un nombre a un bloque de código. Cada vez que queramos
invocar la ejecución de dicho bloque usamos su nombre.

Primero definimos el método (usando def ... end), y luego lo usamos mediante el nombre
del método. Ejemplo:

Definimos el método:
```
def greet
  puts "Hello!"
  puts "How are you doing?"
end
```

Ejecutamos el método:
```
greet
```
Los métodos son muy útiles para hacer el código más legible y para no repetir bloques.

## Paso de argumentos

Para que el método trabaje con datos diferentes cada vez que lo ejecutamos debemos
pasarle los datos para usar. Los llamamos argumentos del método. Veamos ejemplo:

```
def greet(name)
  puts "Hello #{name}!"
  puts "How are you doing?"
end

greet "Obi-wan"
```

Normalmente los métodos no pueden acceder a las variables definidas fuera (Excepto las
variables globales), por ello las pasamos por argumentos del método.

```
name="Darth Maul"

greet "Obi-wan" #Esto muestra Obi-wam

puts name #Esto muestra Darth-Maul
```

## Varios argumentos
Podemos pasar varios argumentos a un método. Veamos ejemplo:

```
def greet(name,group)
  puts "Hello #{name}!"
  puts "How are #{group} today?"
end

greet "Obiwan", "Jedi"
greet "Dath-Maul", "Sith"
```

## Argumentos por defecto
A veces es cómodo no especificar argumentos pero otras sí.
Usamos los valores por defecto en los argumentos.

```
def greet(name,group="Jedi")
  puts "Hello #{name}!"
  puts "How are #{group} today?"
end

greet "Obiwan"
greet "Dath-Maul", "Sith"
```

## Variables globales
Las variables globales necesarios) son variables que pueden ser
accedidas por todos los métodos del programa sin necesidad de
pasarse por argumentos.

Sólo deben usarse cuando sea estrictamente necesario.

```
$film="starwars"

def greet(name)
  puts "Hello #{name}!"
  puts "We are into #{$film} world!"
end

greet "Quigon-Jinn"
```

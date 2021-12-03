
# 1. Introducción

* `apt-get install ruby`: Instalación de ruby en Debian-Ubuntu
* `ruby -v`: Comprobar la versión instalada de ruby
* `irb`: Entrar en el intérprete interactivo. CTRL+D para salir

## Variables
```
a=13
a.class => Fixnum

b=7
b.class => Fixnum

texto='Hola Mundo!'
texto.class => String

puts texto #Mostrar variable en la pantalla
```

# 2. Métodos, Array e iteraciones

```
ri String          # Consultar la documentación de la clase String
ri String#downcase # Consultar la documentación del método downcase de la clase String

a = `pwd` #Ejecutar un comando y guardar la salida en la variable <a> como String
b = system("pwd") #Ejecuta un comando y devuelve true/false indicando si se ha ejecutado correctamente o no

a = [ 'hola', 'que', 'tal' ]
a.class => Array
```

Ejemplo1:
```
#!/usr/bin/ruby
# encoding: utf-8
# Ejemplo bucle clásico

nombres = [ 'juan', 'carmen', 'elena' ]

i=0
while(i<nombres.size) do
  puts "Hola "+nombres[i]+", ¿qué tal estás?"
  i = i+1
end
```

Ejemplo2:
```
#!/usr/bin/ruby
# encoding: utf-8
# Ejemplo bucle con iterador

nombres = [ 'juan', 'carmen', 'elena' ]

nombres.each do |nombre|
  puts "Hola "+nombre+", ¿qué tal estás?"
end
```

Ejemplo3:
```
#!/usr/bin/ruby
# encoding: utf-8
# Ejemplo bucle con iterador

nombres = [ 'juan', 'carmen', 'elena' ]

nombres.each { |n| puts "Hola "+n+", ¿qué tal estás?" }
```

Ejemplo4:
```
#!/usr/bin/ruby
# encoding: utf-8

resultado = `ls /var`
lista = resultado.split("\n") #Ahora lista es un array con los nombres de los directorios

lista.each do |n|
  puts "* Nombre de la carpeta => "+n
end

```

# Enlaces de interés

* [Ruby para sysadmin](http://devopsanywhere.blogspot.com.es/2011/09/how-ruby-is-beating-python-in-battle.html)

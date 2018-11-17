#!/usr/bin/ruby
# la linea de arriba puede que se tenga que ajustar en
# nuestro sistema a la localización de ruby

$JUEGO_VERSION="0.4"
 
# usamos dos "librerías":
# para procesar los parámetros del programa
require 'parsearg.rb'
# y bueno, ¿para qué será esto? ;)
require 'sdl.rb'
 
require 'juegos/version.03/funciones.rb'


# definimos otras funciones
# por ejemplo como_usar
def como_usar()
  puts "Uso: #{$0} (opciones)\n\n"
  puts "Con opciones:\n"
  puts "\t--fullscreen\n\n"
end

def main()
  # código de la función principal
  # ¿qué función explica cómo usar el programa?
  $USAGE = "como_usar"
  
  inicializar
  entrar_bucle_juego
end

# comienza el juego
if $0 == __FILE__
  main
end 


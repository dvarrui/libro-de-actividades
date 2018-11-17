#!/usr/bin/ruby
# la linea de arriba puede que se tenga que ajustar en
# nuestro sistema a la localización de ruby
 
# usamos dos "librerías":
# para procesar los parámetros del programa
require 'parsearg.rb'
# y bueno, ¿para qué será esto? ;)
require 'sdl.rb'
 
def main()
  puts "[DVR] main..."
  # código de la función principal
  # ¿qué función explica cómo usar el programa?
  $USAGE = "como_usar"
  
  inicializar
end

def inicializar 
  puts "[DVR] inicializar..."
  # solo una opción: --fullscreen
  # si hubieran más las pondríamos a continuación, separando con
  # comas: "fullscreen", "nosound"
  # (no he encontrado buena documentación de parseArgs)
  parseArgs(0, nil, nil, "fullscreen")


  # inicializa SDL - se debe hacer antes de usar la librería
  # solo usamos vídeo por ahora
  SDL.init(SDL::INIT_VIDEO)
 
  # Cambiamos el título de la ventana
  SDL::WM.setCaption("Plantilla SDL", "")
 
  # ahora vamos a poner el modo de vídeo
  # no usaremos flags en el modo de vídeo por defecto
  sdlVideoFlags = 0
 
  # ¿pantalla completa?
  if $OPT_fullscreen
	# añade el flag a lo que ya haya
	sdlVideoFlags |= SDL::FULLSCREEN
  end
 
  # ponemos el modo de vídeo a 640x480 16bpp
  pantalla = SDL.setVideoMode(640, 480, 16,  sdlVideoFlags)
 
  # ¿algún problema?
  if pantalla == nil
	STDERR.puts "Problemas con setVideoMode\n"
	exit(-1)
  end
 
  # no queremos ver el ratón
  SDL::Mouse.hide
 
  # por ahora ya vale, espera a que se pulse enter en
  # la consola donde ejecutamos el programa (si no estamos
  # en pantalla completa!)
  
  # Esto se pone de momento en la versión 1 para mantener 
  # visible la pantalla. 
  gets() if !$OPT_fullscreen
  puts "[DVR] Fin de inicializar"
end

 
def como_usar()
  # definimos otras funciones
  # por ejemplo como_usar
  puts "Uso: #{$0} (opciones)\n\n"
  puts "Con opciones:\n"
  puts "\t--fullscreen\n\n"
end
 
# comienza el juego
if $0 == __FILE__
  main
end 
 
# fin del fichero 


#!/usr/bin/ruby
# la linea de arriba puede que se tenga que ajustar en
# nuestro sistema a la localización de ruby
 
# usamos dos "librerías":
# para procesar los parámetros del programa
require 'parsearg.rb'
# y bueno, ¿para qué será esto? ;)
require 'sdl.rb'
 

def main()
  # código de la función principal
  # ¿qué función explica cómo usar el programa?
  $USAGE = "como_usar"
  
  inicializar
  entrar_bucle_de_eventos
end

def inicializar 
  # solo una opción: --fullscreen
  # si hubieran más las pondríamos a continuación, separando con
  # comas: "fullscreen", "nosound"
  # (no he encontrado buena documentación de parseArgs)
  parseArgs(0, nil, nil, "fullscreen")


  # inicializa SDL - se debe hacer antes de usar la librería
  # solo usamos vídeo por ahora
  SDL.init(SDL::INIT_VIDEO)
 
  # Cambiamos el título de la ventana
  SDL::WM.setCaption("Plantilla SDL", "juegos/iconos/info.png")
 
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
end

def entrar_bucle_de_eventos
  # el bucle para procesar eventos
  ev = SDL::Event.new
 
  listo = false
  while ! listo
    # primero: eventos inmediatos
	# mientras hayan eventos, los procesamos
	while ev.poll != 0
      # hay que procesar QUIT
	  if ev.type == SDL::Event::QUIT
		puts "quit!\n";
		listo = true
	  end
		
	  # si se pulsa ESC, nos vamos
	  if ev.type == SDL::Event::KEYDOWN && ev.keySym == SDL::Key::ESCAPE
		# se ha pulsado una tecla y además es ESC!
		puts "ESC!\n";
		listo = true
      end
	end
	
	# segundo: teclas pulsadas
	# hay que hacer un scan lo primero de todo
	SDL::Key.scan
	
	# verificamos algunas teclas
	if SDL::Key.press?(SDL::Key::UP)
		puts "se pulsa arriba!\n";
	elsif SDL::Key.press?(SDL::Key::DOWN)
		puts "se pulsa abajo!\n";
	end
	
	if SDL::Key.press?(SDL::Key::RIGHT)
		puts "se pulsa derecha!\n";
	elsif SDL::Key.press?(SDL::Key::LEFT)
		puts "se pulsa izquierda!\n";
	end
  end
end
 
# definimos otras funciones
# por ejemplo como_usar
def como_usar()
  puts "Uso: #{$0} (opciones)\n\n"
  puts "Con opciones:\n"
  puts "\t--fullscreen\n\n"
end
 
# comienza el juego
if $0 == __FILE__
  main
end 

#ESQUEMA GENERAL
#inicializar
#
#while ! terminar
#
#	captura_eventos
# 
#	if toca_redibujar
#		actualiza_estados
#		dibuja
#	end
#end
#
 
# fin del fichero 


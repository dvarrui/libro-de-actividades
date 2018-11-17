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
  SDL::WM.setCaption("Plantilla SDL ", "juegos/iconos/info.png")
 
  # ahora vamos a poner el modo de vídeo
  # no usaremos flags en el modo de vídeo por defecto
  sdlVideoFlags = 0
 
  # ¿pantalla completa?
  if $OPT_fullscreen
	# añade el flag a lo que ya haya
	sdlVideoFlags |= SDL::FULLSCREEN
  end
 
  # ponemos el modo de vídeo a 640x480 16bpp
  $pantalla = SDL.setVideoMode(640, 480, 16,  sdlVideoFlags)
 
  # ¿algún problema?
  if $pantalla == nil
	STDERR.puts "Problemas con setVideoMode\n"
	exit(-1)
  end
 
  # no queremos ver el ratón
  SDL::Mouse.hide
end

def capturar_eventos(ev)
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
	if SDL::Key.press?(SDL::Key::UP) && $luz < 255
      $luz += 1
	elsif SDL::Key.press?(SDL::Key::DOWN) && $luz > 0
      $luz -= 1
	end
	
	if SDL::Key.press?(SDL::Key::RIGHT)
		puts "se pulsa derecha!\n";
	elsif SDL::Key.press?(SDL::Key::LEFT)
		puts "se pulsa izquierda!\n";
	end
         
    listo
end

def toca_redibujar?(tickAntes)
  tickDespues=SDL.getTicks
  # toca dibujar?
  if tickDespues-tickAntes >= 1000/60 
    tickAntes = tickDespues
    return true
  end
  return false
end

def dibujar
  $pantalla.fillRect(0, 0, 640, 480, $pantalla.mapRGB($luz, $luz, $luz))

  icono=SDL::LoadBMP("juegos/iconos/info.bmp")
  SDL::BlitSurface(icono,nil,$pantalla,nil)

  # actualizamos toda la pantalla
  $pantalla.updateRect(0, 0, 0, 0)
end

def entrar_bucle_juego
  # el bucle para procesar eventos
  ev = SDL::Event.new
  # antes de entrar en bucle, para calcular FPS en la primera vuelta
  tickAntes=SDL.getTicks
  $luz = 0

  listo = false
  while ! listo
    listo = capturar_eventos(ev)
    if toca_redibujar?(tickAntes)
      dibujar
    end
  end
end
 

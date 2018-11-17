=begin
Fichero: modulos/graficos.rb
Autor : David Vargas <dvargas@canarias.org>
Versión : 0.2.2 20080124
Descripción: Librería que facilita el acceso a la parte gráfica de SDL
=end
 
require 'sdl'
require 'modulos/global'
require 'modulos/texto'
require 'clases/imagen'


#<p>Este módulo sirve de interfaz entre el juego y las librerías SDL.</p>
module Graficos
  
  #<p>Inicializa todo lo que tiene que ver con la parte gráfica.
  #Incluyendo las librerías SDL.</p>
  def self.iniciar_sdl
    ancho=Global.get(:ANCHO)
    alto=Global.get(:ALTO)
    titulo=Global.get(:titulo)
    SDL.init( SDL::INIT_VIDEO )
    @pantalla=SDL::setVideoMode(ancho,alto,16,SDL::SWSURFACE)
    
    #Creamos varias capas
    @capas = {}
    3.times do |i|
      @capas[i]=SDL::Surface.new(SDL::SWSURFACE,ancho,alto,@pantalla.display_format)
      @capas[i].setColorKey( SDL::SRCCOLORKEY || SDL::RLEACCEL ,@capas[i][0,0])
    end

    SDL::WM::setCaption(titulo,titulo)
    SDL::TTF.init #Activar True Type Font
  end

  #<p>Carga todas las imagenes contenidas en <b>fichero</b>.</p>
  #<p>Imágenes de 48x48.</p>
  def self.cargar_imagenes(fichero)
    @imagenes = Imagen.new.get_array(fichero)
    @imagenes.each do |item|
      imagen = SDL::Surface.load(Global.get(:RUTA_IMG)+item.nombre)
      imagen.setColorKey( SDL::SRCCOLORKEY || SDL::RLEACCEL ,imagen[0,0])
      item.imagen= imagen.displayFormat
    end
  end
  
  def self.get_imagen(index)
    return @imagenes[index]
  end
  
  #<p>Comprueba si se ha dado la orden de salir.</p>
  #<ul>
  #  <li>Tecla <b>ESC</b></li>
  #  <li>Cerrar la ventana.</li>
  #</ul>
  def self.se_ha_pulsado_salir?
    #Detectar los eventos
    while event = SDL::Event2.poll
      case event
      when SDL::Event2::Quit
        return true
      when SDL::Event2::KeyDown
        return true if event.sym == SDL::Key::ESCAPE
      end
    end
    return false
  end
  
  def self.esperar_y_salir
    while !se_ha_pulsado_salir? do
      #Pintar
    end
  end
  
  def self.pintar
    mostrar_autor
     #mostrar_todas_las_imagenes
    @pantalla.flip
  end
  
  def self.mostrar_autor
    Texto.set :normal
    Texto.superficie= @pantalla
    x=34;y=31
    Texto.escribir('David Vargas Ruiz', x, y)
    Texto.escribir('Simulador de pruebas...', x, y+1)
    Texto.escribir('Versión 0.1.0', x, y+2)   
  end
  
  def self.mostrar_todas_las_imagenes
    @imagenes.each do |i|
      SDL.blitSurface(i.imagen,0,0,i.ancho,i.alto,@pantalla,rand(Global.get(:ANCHO)),rand(Global.get(:ALTO)))
    end
    print "Se han cargado ",@imagenes.size," imágenes.\n"
    print @imagenes[0].ancho, "x", @imagenes[0].alto,"\n"
  end
end

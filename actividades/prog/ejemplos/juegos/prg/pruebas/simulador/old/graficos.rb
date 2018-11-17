#
#Autor : David Vargas <dvargas@canarias.org>
#Versión : 0.2.0
#Fecha UM : 20080108
 
require 'sdl'
require 'modulos/definicion'
require 'modulos/texto'

#<p>Este módulo sirve de interfaz entre el juego y las librerías SDL.</p>
class Graficos
  attr_reader :ANCHO, :ALTO, :pantalla
  attr_accessor :titulo_ventana
  
  #Inicializar SDL    
  def iniciar_sdl
    SDL.init( SDL::INIT_VIDEO )
    @ANCHO=640
    @ALTO=480
    @pantalla=SDL::setVideoMode(@ANCHO,@ALTO,16,SDL::SWSURFACE)
    SDL::WM::setCaption(@titulo_ventana,@titulo_ventana)
    SDL::TTF.init #Activar True Type Font
  end
  
  def mostrar_autor
    Texto.set :normal
    Texto.superficie= @pantalla
    x=34;y=31
    Texto.escribir('David Vargas Ruiz', x, y)
    Texto.escribir('Simulador de pruebas...', x, y+1)
    Texto.escribir('Versión 0.1.0', x, y+2)   
    get_imagen
    @pantalla.flip
  end
  
  def get_imagen
    imagen = SDL::Surface.load(Definicion.get(:RUTA_IMG)+'computer.png')
    imagen.setColorKey( SDL::SRCCOLORKEY || SDL::RLEACCEL ,imagen[0,0])
    @imagen = imagen.displayFormat
    SDL.blitSurface(@imagen,0,0,@imagen.w,@imagen.h,@pantalla,40,40)
  end
  #<p>Comprueba si se ha dado la orden de salir.</p>
  #<ul>
  #  <li>Tecla <b>ESC</b></li>
  #  <li>Cerrar la ventana.</li>
  #</ul>
  def se_ha_pulsado_salir?
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
  
  def esperar_y_salir
     while !se_ha_pulsado_salir? do
      #Pintar
    end
  end
end

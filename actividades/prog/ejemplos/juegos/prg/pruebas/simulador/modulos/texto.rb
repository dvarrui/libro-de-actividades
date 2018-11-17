=begin
Fichero: modulos/texto.rb
Autor: David Vargas <dvargas@canarias.org>
Versión: 0.1.0 20080119
Descripción: Librería que simplifica dibujar textos SDL
=end
 
require 'modulos/global'

module Texto
  
  def self.get(tipo)
    font = SDL::TTF.open(Global.get(:RUTA_TTF)+'sample.ttf',12)
    font.style = SDL::TTF::STYLE_NORMAL
    return font    
  end
  
  def self.set(tipo)
    @font = get(tipo)
  end
  
  def self.superficie=(n)
    @pantalla=n
  end
  
  def self.escribir(texto, columna, fila)
    @font.drawSolidUTF8(@pantalla,texto,14*columna,14*fila,255,255,255)
  end
end

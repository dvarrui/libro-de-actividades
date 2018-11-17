# 
# To change this template, choose Tools | Templates
# and open the template in the editor.
 

class Accion
  attr_reader :personaje, :game
  
  def initialize(personaje,game,x,y)
    @personaje = personaje
    @game = game
    @x = x
    @y = y
  end
  
  def self.rep
    return [self.class.nombre_corto, @x, @y]
  end
  
  #Ahora no se usa el parámetro de entrada pero imagino que más adelante
  #sí se usará.
  def self.rango(personaje); 1; end
  def self.objetivo?(personaje,otro); personaje.enemigo?(otro); end
  
  def self.generar(personaje,game)
    mapa = game.mapa
    cerca = mapa.posiciones_cercanas(rango(personaje),personaje.x,personaje.y)
    objetivos = cerca.find_all {|x,y| objetivo?(personaje,mapa.personajes[x,y])}
    return objetivos.collect {|x,y| self.new(personaje,game,x,y)}
  end
  
  def call
    raise NotImplementedError
  end
  
  def objetivo
    #TODO: Originamente ponía game pero creo que es @game
    return @game.mapa.personajes[@x,@y]
  end
  
end

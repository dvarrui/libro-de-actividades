
require 'clases/matriz'

class Mapa
  attr_reader :terreno, :personajes
  
  def initialize(clave, layout)
    filas = layout.split("\n")
    #Esto quita todos los espacios iniciales
    filas.collect! {|f| f.gsub(/\s+/, '').split(//)}
    y = filas.size
    x = filas[0].size
    
    @terreno = Matriz.new(x,y)
    @personajes = Matriz.new(x,y)
    
    filas.each_with_index do |fila, y|
      fila.each_with_index do |g, x|
        @terreno[x,y] = clave[g]        
      end
    end
  end
  
  def ubicar(personaje,x,y)
    #Método Map.place
    @personajes[x,y] = personaje
    personaje.x=x
    personaje.y=y
  end
  
  def mover(old_x, old_y, new_x, new_y)
    raise ErrorLocalizacionOcupada.new(new_x,new_y) if @personajes[new_x,new_y]
    @personajes[new_x,new_y] = @personajes[old_x,old_y]
    @personajes[old_x,old_y] = nil
  end
  
  def all_posiciones
    @terreno.all_posiciones
  end
  
  def esta_cerca?(distancia,x1,y1,x2,y2)
    (x1 - x2).abs + (y1 -y2).abs <= distancia
  end
  
  def posiciones_cercanas(distancia, x ,y)
    all_posiciones.find_all {|x2,y2| esta_cerca?(distancia,x,y,x2,y2)}
  end
  
  def rep
    return [@terreno.rep,@personajes.rep]
  end
end

class ErrorLocalizacionOcupada < Exception
  
end


class Terreno
  attr_reader :nombre
  
  #Crea un trozo de terreno con un nombre.
  def initialize(nombre)
    @nombre = nombre
  end
  
  #Devuelve el nombre del terreno como un array.
  def rep
    [@nombre]
  end
end




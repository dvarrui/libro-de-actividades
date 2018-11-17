
 

class Eleccion
  attr_reader :rep
  
  def initialize(*rep, &accion)
    @rep, @accion = rep, accion
  end
  
  def call(*args, &proc)
    @accion.call(*args, &proc)
  end
end

# 
# Definición de una clase con atributos, constructor y un método
#

print "Ejecutando",$0,"\n"

class Persona
  def initialize(nombre)
    @nombre = nombre
  end
  
  #Método para saludar
  def saludar
    "Hola, mi nombre es #{@nombre}."
  end
end

p = Persona.new("David")
puts p.saludar

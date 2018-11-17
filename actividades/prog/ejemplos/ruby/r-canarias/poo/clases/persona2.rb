# 
# Definición de una clase con atributos, constructor y un método
#

print "Ejecutando",$0,"\n"

class Persona
  #Métodos get/set para al atributo
  attr_accessor :nombre
  
  def initialize(nombre)
    @nombre = nombre
  end
  
  #Método para saludar
  def saludar
    "Hola, mi nombre es #{@nombre}."
  end
end

p = Persona.new("")
p.nombre="David"
puts p.saludar

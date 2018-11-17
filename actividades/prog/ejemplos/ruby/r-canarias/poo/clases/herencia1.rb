#!/usr/bin/ruby

print "Ejecutando",$0,"\n"

class Persona
  def initialize(nombre)
    @nombre = nombre
  end
  def saludar
    "Hola, mi nombre es #{@nombre}."
  end
end

david = Persona.new('David')

puts david.saludar


class Japones < Persona
  def initialize
   super('Yukihiro Matsumoto')
  end
end
puts Japones.new.saludar

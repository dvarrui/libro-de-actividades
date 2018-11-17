#!/usr/bin/ruby

puts "[#{$0}] Antes de definir la clase animal"

class Animal
  puts "  [Clase Animal] Estamos dentro"

  attr_accessor :nombre
  def info
    puts "Hola soy Animal: "+@nombre
  end

  puts "  [Clase Animal] Saliendo de dentro de la clase animal"
end

puts "[#{$0}] Después de definir la clase animal\n\n"

a = Animal.new
a.nombre = "Gato"
a.info
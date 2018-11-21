#!/usr/bin/ruby

class Animal
  attr_accessor :nombre

  def info
    puts "Hola soy Animal: "+@nombre
  end

end

a = Animal.new
a.nombre = "Perro"
a.info


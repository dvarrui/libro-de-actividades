puts "Simulando la herencia múltiple\n\n"

module Modulo03
  def info
    puts '* Esto es un método añadido para '+self.class.name
  end

  def self.saludar(nombre="Mundo")
    puts "* Hola #{nombre}"
  end
end

class Animal
  include Modulo03
end

puts 'Método de instancia o de objeto:'
Animal.new.info

class Maquina
  include Modulo03
end

Maquina.new.info

puts "\nMétodo estático o de clase:"
Modulo03::saludar